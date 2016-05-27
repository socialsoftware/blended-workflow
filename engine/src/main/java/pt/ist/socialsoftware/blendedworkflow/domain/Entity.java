package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
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
		setOLDEntityInstanceCounter(0);
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

	public Attribute createAttribute(String name, AttributeType type, boolean isMandatory) {
		return new Attribute(getDataModel(), this, name, type, isMandatory, false, false);
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

	public void cloneEntity(OldDataModelInstance dataModelInstance) throws BWException {
		Entity newEntity = new Entity(dataModelInstance, getName(), false);
		for (Attribute attribute : getAttributeSet()) {
			attribute.cloneAttribute(dataModelInstance, newEntity);
		}

		for (OldEntityInstance ei : getOldEntityInstanceSet()) {
			ei.cloneEntityInstance(dataModelInstance, newEntity);
		}
	}

	/**
	 * Create and assign EntityInstances and AttributesInstances to Workitems
	 */
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, Attribute attribute,
			ConditionType conditionType) {
		OldEntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();

		if (this.equals(entityContext)) {
			entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
		} else {
			for (OldRelationInstance relationInstance : entityInstanceContext
					.getEntityInstanceOneRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				OldEntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}

			for (OldRelationInstance relationInstance : entityInstanceContext
					.getEntityInstanceTwoRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				OldEntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				} else {
					for (OldRelationInstance r2 : relationEntityInstanceContext
							.getEntityInstanceTwoRelationInstancesSet()) {
						if (r2.getEntityInstanceOne().getEntity().equals(this)) {
							r2.getEntityInstanceOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
		}
	}

	public void assignAttributeInstances(OldTaskWorkItem taskWorkItem, Attribute attribute,
			ConditionType conditionType) {
		OldDataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();
		if (getOldEntityInstanceSet().isEmpty()) {
			// EntityInstance entityInstance = new
			// EntityInstance(dataModelInstance, this); //OLD
			OldEntityInstance entityInstance = new OldEntityInstance(this);
			entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);

			// FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		} else {
			for (OldEntityInstance entityInstance : getOldEntityInstanceSet()) { // FIXME:
				// only
				// 1
				// entityInstance
				entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}
		}
	}

	public void assignAllAttributeInstances(OldGoalWorkItem goalWorkItem, Entity entity, ConditionType conditionType) {
		OldEntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();

		if (this.equals(entityContext)) {
			for (Attribute attribute : entityContext.getAttributeSet()) {
				if (attribute.getIsKeyAttribute()) {
					entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}
		} else {
			for (OldRelationInstance relationInstance : entityInstanceContext
					.getEntityInstanceOneRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				OldEntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					for (Attribute attribute : relationEntityContext.getAttributeSet()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute,
									conditionType);
						}
					}
				}
			}

			for (OldRelationInstance relationInstance : entityInstanceContext
					.getEntityInstanceTwoRelationInstancesSet()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				OldEntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					for (Attribute attribute : relationEntityContext.getAttributeSet()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute,
									conditionType);
						}
					}
				}
			}
		}
	}

	public void assignAllAttributeInstances(OldTaskWorkItem taskWorkItem, Entity entity, ConditionType conditionType) {
		OldDataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();

		if (getOldEntityInstanceSet().isEmpty()) {
			// EntityInstance entityInstance = new
			// EntityInstance(dataModelInstance, this);
			OldEntityInstance entityInstance = new OldEntityInstance(this);
			for (Attribute attribute : getAttributeSet()) {
				if (attribute.getIsKeyAttribute())
					entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}

			// FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		} else {
			for (OldEntityInstance entityInstance : getOldEntityInstanceSet()) { // FIXME:
				// Only
				// 1
				// entityInstance
				for (Attribute attribute : getAttributeSet()) {
					if (attribute.getIsKeyAttribute())
						entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
				}
			}
		}
	}

	private void createRelationInstances(OldDataModelInstance dataModelInstance, OldEntityInstance entityInstance) {
		Entity relationEntityTwo = null;
		OldEntityInstance relationEntityInstanceTwo = null;
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
				if (relation.getOldRelationInstanceSet().isEmpty()) {
					if (relationEntityTwo.getOldEntityInstanceSet().isEmpty()) {
						// relationEntityInstanceTwo = new
						// EntityInstance(dataModelInstance, relationEntityTwo);
						relationEntityInstanceTwo = new OldEntityInstance(relationEntityTwo);
					} else {
						for (OldEntityInstance entityInstance1 : relationEntityTwo.getOldEntityInstanceSet()) { // FIXME:
							// only
							// 1
							// entityInstance
							relationEntityInstanceTwo = entityInstance1;
						}
					}
					// Create Relation Instance and re-call the method for the 2
					// entity
					OldEntityInstance relationInstanceOne;
					OldEntityInstance relationInstanceTwo;

					if (entityInstance.getEntity().equals(relation.getEntityOne())) {
						relationInstanceOne = entityInstance;
						relationInstanceTwo = relationEntityInstanceTwo;
					} else {
						relationInstanceOne = relationEntityInstanceTwo;
						relationInstanceTwo = entityInstance;
					}
					new OldRelationInstance(relation, relationInstanceOne, relationInstanceTwo,
							relationInstanceOne.getNewRelationInstanceID());
					// if (entityInstance.getEntity().getName().equals("?")) {
					// relationEntityTwo.createRelationInstances(dataModelInstance,
					// relationEntityInstanceTwo);
					// }
				}
			}
		}
	}

	public int getNewOldEntityInstanceId() {
		setOLDEntityInstanceCounter(getOLDEntityInstanceCounter() + 1);
		return getOLDEntityInstanceCounter();
	}

	public Optional<Attribute> getAttribute(String name) {
		return getAttributeSet().stream().filter(att -> att.getName().equals(name)).findFirst();
	}

	public OldEntityInstance getEntityInstance(String ID) {
		for (OldEntityInstance entityInstance : getOldEntityInstanceSet()) {
			if (entityInstance.getID().equals(ID)) {
				return entityInstance;
			}
		}
		return null;
	}

	public Set<Entity> getEntitiesInRelation() {
		Set<Entity> entities = new HashSet<Entity>();
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
		Set<RelationBW> relations = new HashSet<RelationBW>(this.getRelationOneSet());
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
		Optional<Attribute> oBwAtt = getAttribute(element);
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
				.filter(r -> (r.getEntityOne() == this && r.getRoleNameTwo().equals(rolename))
						|| (r.getEntityTwo() == this && r.getRoleNameOne().equals(rolename)))
				.map(r -> r.getEntityOne() == this ? r.getEntityTwo() : r.getEntityOne()).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ROLE_NAME, getName() + "." + rolename));
	}

}