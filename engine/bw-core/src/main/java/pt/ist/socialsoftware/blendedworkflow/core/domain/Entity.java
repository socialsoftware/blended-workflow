package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDto;

public class Entity extends Entity_Base {
	private static Logger logger = LoggerFactory.getLogger(Entity.class);

	@Override
	public void setName(String name) {
		checkEntityName(name);
		super.setName(name);
	}

	@Override
	public void setMandatory(boolean mandatory) {
		checkUniqueMandatoryEntity(mandatory);
		super.setMandatory(mandatory);
	}

	public Entity() {

	}

	public Entity(DataModel dataModel, String name, boolean exists) {
		this(dataModel, name, exists, false);
	}

	public Entity(DataModel dataModel, String name, boolean exists, boolean mandatory) {
		setDataModel(dataModel);
		setName(name);
		setExists(exists);
		setMandatory(mandatory);
	}

	private void checkEntityName(String name) {
		if (name == null || name.equals("")) {
			throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);
		}

		checkUniqueEntityName(name);
	}

	private void checkUniqueMandatoryEntity(boolean mandatory) {
		if (mandatory && getDataModel().getEntitySet().stream().anyMatch(e -> e.getMandatory())) {
			throw new BWException(BWErrorType.NOT_UNIQUE_MANDATORY_ENTITY, getName());
		}
	}

	private void checkUniqueEntityName(String name) throws BWException {
		boolean exists = getDataModel().getEntitySet().stream()
				.anyMatch(ent -> ent.getName() != null && ent.getName().equals(name));
		if (exists) {
			throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);
		}
	}

	@Override
	public ProductType getProductType() {
		return ProductType.ENTITY;
	}

	public void checkUniqueElementName(String name) {
		if (getAttributeSet().stream().anyMatch(a -> a.getName() != null && a.getName().equals(name))) {
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);
		}

		if (getRuleSet().stream().anyMatch(r -> r.getName() != null && r.getName().equals(name))) {
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);
		}

		if (getRelationOneSet().stream().anyMatch(r -> r.getRolenameTwo() != null && r.getRolenameTwo().equals(name))) {
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);
		}

		if (getRelationTwoSet().stream().anyMatch(r -> r.getRolenameOne() != null && r.getRolenameOne().equals(name))) {
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);
		}

	}

	public Attribute createAttribute(String name, AttributeType type, boolean isMandatory) {
		return new Attribute(getDataModel(), this, name, type, isMandatory);
	}

	public Rule createRule(String name, Condition condition) {
		return new Rule(this, name, condition);
	}

	public Rule getRule(String name) {
		return getRuleSet().stream().filter(rule -> rule.getName().equals(name)).findFirst().orElse(null);
	}

	public RelationBW createRelation(String name, String roleNameOne, String cardinalityOne, Entity entityTwo,
			String roleNameTwo, String cardinalityTwo) {
		return new RelationBW(getDataModel(), name, this, roleNameOne, cardinalityOne, false, entityTwo, roleNameTwo,
				cardinalityTwo, false);
	}

	public Optional<Attribute> getAttribute(String name) {
		return getAttributeSet().stream().filter(att -> att.getName().equals(name)).findFirst();
	}

	public Set<Entity> getEntitiesInRelation() {
		Set<Entity> entities = new HashSet<>();
		for (RelationBW relation : getRelationSet()) {
			if (relation.getEntityOne() == this) {
				entities.add(relation.getEntityTwo());
			} else {
				entities.add(relation.getEntityOne());
			}
		}
		return entities;
	}

	public Set<RelationBW> getRelationSet() {
		Set<RelationBW> relations = new HashSet<>(this.getRelationOneSet());
		relations.addAll(getRelationTwoSet());
		return relations;
	}

	public int getRelationCount() {
		return getRelationOneSet().size() + getRelationTwoSet().size();
	}

	@Override
	public void delete() {
		setDataModel(null);

		getEntityInstanceSet().stream().forEach(ei -> ei.delete());

		getRuleSet().stream().forEach(rule -> rule.delete());
		getAttributeSet().stream().forEach(att -> att.delete());
		getRelationOneSet().stream().forEach(rel -> rel.delete());
		getRelationTwoSet().stream().forEach(rel -> rel.delete());
		if (getDefEntityCondition() != null) {
			getDefEntityCondition().delete();
		}

		super.delete();
	}

	@Override
	public Entity getEntity() {
		return this;
	}

	@Override
	public Product getNext(List<String> pathLeft, String path) {
		// log.debug("getNext {}:{}", path, pathLeft);

		if (pathLeft.isEmpty()) {
			return this;
		}

		String element = pathLeft.get(0);
		Optional<Attribute> oBwAtt = getAttribute(element);
		if (oBwAtt.isPresent()) {
			pathLeft.remove(0);
			return oBwAtt.get().getNext(pathLeft, path);
		}

		Optional<RelationBW> oBwRel = getRelationSet().stream()
				.filter(rel -> rel.getRolenameOne().equals(element) && rel.getEntityTwo() == this
						|| rel.getRolenameTwo().equals(element) && rel.getEntityOne() == this)
				.findFirst();
		if (oBwRel.isPresent()) {
			pathLeft.remove(0);
			return oBwRel.get().getEntitybyRolename(element).getNext(pathLeft, path);
		}

		throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
	}

	@Override
	public EntityDto getDTO() {
		EntityDto entityDto = new EntityDto();
		entityDto.setSpecId(getDataModel().getSpecification().getSpecId());
		entityDto.setExtId(getExternalId());
		entityDto.setProductType(getProductType().name());
		entityDto.setName(getName());
		entityDto.setExists(getExists());
		entityDto.setMandatory(getMandatory());

		return entityDto;
	}

	public Set<MulCondition> getMulConditions() {
		return getRelationSet().stream().flatMap(r -> r.getMulConditionSet().stream())
				.filter(m -> m.getSourceEntity() == this).collect(Collectors.toSet());
	}

	@Override
	public DefProductCondition getDefCondition() {
		return DefEntityCondition.getDefEntityCondition(this);
	}

	@Override
	public String getFullPath() {
		return getName();
	}

	public Entity getEntityByRolename(String rolename) {
		return getRelationSet().stream()
				.filter(r -> r.getEntityOne() == this && r.getRolenameTwo().equals(rolename)
						|| r.getEntityTwo() == this && r.getRolenameOne().equals(rolename))
				.map(r -> r.getEntityOne() == this ? r.getEntityTwo() : r.getEntityOne()).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ROLE_NAME, getName() + "." + rolename));
	}

}