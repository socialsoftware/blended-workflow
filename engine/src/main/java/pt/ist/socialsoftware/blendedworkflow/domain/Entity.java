package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;

public class Entity extends Entity_Base {
	private static Logger logger = LoggerFactory.getLogger(Entity.class);

	@Override
	public void setName(String name) {
		checkEntityName(name);
		super.setName(name);
	}

	public Entity(DataModel dataModel, String name, boolean exists) {
		setDataModel(dataModel);
		setName(name);
		setExists(exists);
		setEntityInstanceCounter(0);
	}

	private void checkEntityName(String name) {
		if ((name == null) || (name.equals("")))
			throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);

		checkUniqueEntityName(name);
	}

	private void checkUniqueEntityName(String name) throws BWException {
		boolean exists = getDataModel().getEntitySet().stream()
				.anyMatch(ent -> (ent.getName() != null) && (ent.getName().equals(name)));
		if (exists)
			throw new BWException(BWErrorType.INVALID_ENTITY_NAME, name);
	}

	@Override
	public ProductType getProductType() {
		return ProductType.ENTITY;
	}

	public void checkUniqueElementName(String name) {
		if (getAttributeSet().stream().anyMatch(a -> a.getName() != null && a.getName().equals(name)))
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);

		if (getRuleSet().stream().anyMatch(r -> r.getName() != null && r.getName().equals(name)))
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);

		if (getRelationOneSet().stream().anyMatch(r -> r.getRoleNameTwo() != null && r.getRoleNameTwo().equals(name)))
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);

		if (getRelationTwoSet().stream().anyMatch(r -> r.getRoleNameOne() != null && r.getRoleNameOne().equals(name)))
			throw new BWException(BWErrorType.DUPLICATE_NAME, getEntity().getName() + "." + name);

	}

	public AttributeBasic createAttribute(AttributeGroup attGroup, String name, AttributeType type,
			boolean isMandatory) {
		return new AttributeBasic(getDataModel(), this, attGroup, name, type, isMandatory, false, false);
	}

	public AttributeGroup createAttributeGroup(String name, boolean isMandatory) {
		return new AttributeGroup(getDataModel(), this, name, isMandatory);
	}

	public Rule createRule(String name, Condition condition) {
		return new Rule(this, name, condition);
	}

	public Rule getRule(String name) {
		return getRuleSet().stream().filter(rule -> rule.getName().equals(name)).findFirst().orElse(null);
	}

	public RelationBW createRelation(String name, String roleNameOne, Cardinality cardinalityOne, Entity entityTwo,
			String roleNameTwo, Cardinality cardinalityTwo) {
		return new RelationBW(getDataModel(), name, this, roleNameOne, cardinalityOne, false, entityTwo, roleNameTwo,
				cardinalityTwo, false);
	}

	public void cloneEntity(DataModelInstance dataModelInstance) throws BWException {
		Entity newEntity = new Entity(dataModelInstance, getName(), false);
		for (AttributeBasic attribute : getAttributeBasicSet()) {
			attribute.cloneAttribute(dataModelInstance, newEntity);
		}

		for (EntityInstance ei : getEntityInstancesSet()) {
			ei.cloneEntityInstance(dataModelInstance, newEntity);
		}
	}

	/**
	 * Create and assign EntityInstances and AttributesInstances to Workitems
	 */
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, AttributeBasic attribute,
			ConditionType conditionType) {
		EntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();

		if (this.equals(entityContext)) {
			entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
		} else {
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}

			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceTwoRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				} else {
					for (RelationInstance r2 : relationEntityInstanceContext
							.getEntityInstanceTwoRelationInstancesSet()) {
						if (r2.getEntityInstanceOne().getEntity().equals(this)) {
							r2.getEntityInstanceOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
		}
	}

	public void assignAttributeInstances(TaskWorkItem taskWorkItem, AttributeBasic attribute,
			ConditionType conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();
		if (getEntityInstancesSet().isEmpty()) {
			// EntityInstance entityInstance = new
			// EntityInstance(dataModelInstance, this); //OLD
			EntityInstance entityInstance = new EntityInstance(this);
			entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);

			// FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		} else {
			for (EntityInstance entityInstance : getEntityInstancesSet()) { // FIXME:
																			// only
																			// 1
																			// entityInstance
				entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}
		}
	}

	public void assignAllAttributeInstances(GoalWorkItem goalWorkItem, Entity entity, ConditionType conditionType) {
		EntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();

		if (this.equals(entityContext)) {
			for (AttributeBasic attribute : entityContext.getAttributeBasicSet()) {
				if (attribute.getIsKeyAttribute()) {
					entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}
		} else {
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					for (AttributeBasic attribute : relationEntityContext.getAttributeBasicSet()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute,
									conditionType);
						}
					}
				}
			}

			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceTwoRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					for (AttributeBasic attribute : relationEntityContext.getAttributeBasicSet()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute,
									conditionType);
						}
					}
				}
			}
		}
	}

	public void assignAllAttributeInstances(TaskWorkItem taskWorkItem, Entity entity, ConditionType conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();

		if (getEntityInstancesSet().isEmpty()) {
			// EntityInstance entityInstance = new
			// EntityInstance(dataModelInstance, this);
			EntityInstance entityInstance = new EntityInstance(this);
			for (AttributeBasic attribute : getAttributeBasicSet()) {
				if (attribute.getIsKeyAttribute())
					entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}

			// FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		} else {
			for (EntityInstance entityInstance : getEntityInstancesSet()) { // FIXME:
																			// Only
																			// 1
																			// entityInstance
				for (AttributeBasic attribute : getAttributeBasicSet()) {
					if (attribute.getIsKeyAttribute())
						entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
				}
			}
		}
	}

	private void createRelationInstances(DataModelInstance dataModelInstance, EntityInstance entityInstance) {
		Entity relationEntityTwo = null;
		EntityInstance relationEntityInstanceTwo = null;
		// Relation Type Exists?
		if (this.getRelationCount() > 0) {
			for (RelationBW relation : this.getRelationSet()) {
				// Get the other relation entity
				for (Entity entity : relation.getEntitySet()) {
					if (!this.getName().equals(entity.getName())) {
						relationEntityTwo = entity; // entity2
					}
				}
				// Relations instances already exists?
				if (relation.getRelationInstancesSet().isEmpty()) {
					if (relationEntityTwo.getEntityInstancesSet().isEmpty()) {
						// relationEntityInstanceTwo = new
						// EntityInstance(dataModelInstance, relationEntityTwo);
						relationEntityInstanceTwo = new EntityInstance(relationEntityTwo);
					} else {
						for (EntityInstance entityInstance1 : relationEntityTwo.getEntityInstancesSet()) { // FIXME:
																											// only
																											// 1
																											// entityInstance
							relationEntityInstanceTwo = entityInstance1;
						}
					}
					// Create Relation Instance and re-call the method for the 2
					// entity
					EntityInstance relationInstanceOne;
					EntityInstance relationInstanceTwo;

					if (entityInstance.getEntity().equals(relation.getEntityOne())) {
						relationInstanceOne = entityInstance;
						relationInstanceTwo = relationEntityInstanceTwo;
					} else {
						relationInstanceOne = relationEntityInstanceTwo;
						relationInstanceTwo = entityInstance;
					}
					new RelationInstance(relation, relationInstanceOne, relationInstanceTwo,
							relationInstanceOne.getNewRelationInstanceID());
					// if (entityInstance.getEntity().getName().equals("?")) {
					// relationEntityTwo.createRelationInstances(dataModelInstance,
					// relationEntityInstanceTwo);
					// }
				}
			}
		}
	}

	public int getNewEntityInstanceId() {
		setEntityInstanceCounter(getEntityInstanceCounter() + 1);
		return getEntityInstanceCounter();
	}

	public Optional<AttributeBasic> getAttribute(String name) {
		return getAttributeBasicSet().stream().filter(att -> att.getName().equals(name)).findFirst();
	}

	public EntityInstance getEntityInstance(String ID) {
		for (EntityInstance entityInstance : getEntityInstancesSet()) {
			if (entityInstance.getID().equals(ID)) {
				return entityInstance;
			}
		}
		return null;
	}

	public Set<RelationBW> getRelationSet() {
		Set<RelationBW> relations = new HashSet<RelationBW>(this.getRelationOneSet());
		relations.addAll(getRelationTwoSet());
		return relations;
	}

	public int getRelationCount() {
		return getRelationOneSet().size() + getRelationTwoSet().size();
	}

	public Optional<AttributeGroup> getAttributeGroup(String name) {
		return getAttributeGroupSet().stream().filter(attGroup -> attGroup.getName().equals(name)).findFirst();
	}

	@Override
	public void delete() {
		setDataModel(null);
		getRuleSet().stream().forEach(rule -> rule.delete());
		getAttributeGroupSet().stream().forEach(attGroup -> attGroup.delete());
		getAttributeSet().stream().forEach(att -> att.delete());
		getRelationOneSet().stream().forEach(rel -> rel.delete());
		getRelationTwoSet().stream().forEach(rel -> rel.delete());
		if (getDefEntityCondition() != null)
			getDefEntityCondition().delete();

		super.delete();
	}

	@Override
	public Entity getEntity() {
		return this;
	}

	@Override
	public Product getNext(List<String> pathLeft, String path) {
		// log.debug("getNext {}:{}", path, pathLeft);

		if (pathLeft.isEmpty())
			return this;

		String element = pathLeft.get(0);
		Optional<AttributeGroup> oBwAttGroup = getAttributeGroup(element);
		if (oBwAttGroup.isPresent()) {
			pathLeft.remove(0);
			return oBwAttGroup.get().getNext(pathLeft, path);
		}

		Optional<AttributeBasic> oBwAtt = getAttribute(element);
		if (oBwAtt.isPresent()) {
			pathLeft.remove(0);
			return oBwAtt.get().getNext(pathLeft, path);
		}

		Optional<RelationBW> oBwRel = getRelationSet().stream()
				.filter(rel -> (rel.getRoleNameOne().equals(element) && rel.getEntityTwo() == this)
						|| (rel.getRoleNameTwo().equals(element) && rel.getEntityOne() == this))
				.findFirst();
		if (oBwRel.isPresent()) {
			pathLeft.remove(0);
			return oBwRel.get().getEntitybyRolename(element).getNext(pathLeft, path);
		}

		throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
	}

	@Override
	public EntityDTO getDTO() {
		EntityDTO entityDTO = new EntityDTO();
		entityDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		entityDTO.setExtId(getExternalId());
		entityDTO.setProductType(getProductType().name());
		entityDTO.setName(getName());
		entityDTO.setExists(getExists());

		return entityDTO;
	}

	public Set<MulCondition> getMultConditions() {
		return Stream.concat(getRelationOneSet().stream(), getRelationTwoSet().stream())
				.flatMap(r -> r.getMulConditionSet().stream()).filter(m -> m.getSourceEntity() == this)
				.collect(Collectors.toSet());
	}

	@Override
	public DefProductCondition getDefCondition() {
		return DefEntityCondition.getDefEntity(this);
	}

	public Set<AttributeBasic> getAttributeBasicSet() {
		return getAttributeSet().stream().filter(AttributeBasic.class::isInstance).map(AttributeBasic.class::cast)
				.collect(Collectors.toSet());
	}

	public Set<AttributeGroup> getAttributeGroupSet() {
		return getAttributeSet().stream().filter(AttributeGroup.class::isInstance).map(AttributeGroup.class::cast)
				.collect(Collectors.toSet());
	}

	@Override
	public String getFullPath() {
		return getName();
	}

	@Override
	public boolean isCreatedTogether(Product product) {
		return this == product;
	}

	public Entity getEntityByRolename(String rolename) {
		return getRelationSet().stream()
				.filter(r -> (r.getEntityOne() == this && r.getRoleNameTwo().equals(rolename))
						|| (r.getEntityTwo() == this && r.getRoleNameOne().equals(rolename)))
				.map(r -> r.getEntityOne() == this ? r.getEntityTwo() : r.getEntityOne()).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ROLE_NAME, getName() + "." + rolename));
	}

	@Override
	public boolean cannotBeDefinedBefore(Product source) {
		return this == source;
	}

}