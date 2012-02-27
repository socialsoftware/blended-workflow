package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Entity extends Entity_Base {

	public Entity(DataModel dataModel, String name) throws BlendedWorkflowException {
		checkUniqueEntityName(dataModel,name);
		setDataModel(dataModel);
		setName(name);
		setEntityInstanceCounter(0);

	}

	private void checkUniqueEntityName(DataModel dataModel, String name) throws BlendedWorkflowException {
		for (Entity entity : dataModel.getEntities()) {
			if (entity.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @Entity: The Entity name: " + name + "already exists.");
			}
		}
	}

	public void cloneEntity(DataModelInstance dataModelInstance) throws BlendedWorkflowException {
		Entity newEntity = new Entity(dataModelInstance, getName());
		for (Attribute attribute : getAttributes()) {
			attribute.cloneAttribute(dataModelInstance, newEntity);
		}
	}

	public int getNewEntityInstanceId () {
		setEntityInstanceCounter(getEntityInstanceCounter()+1);
		return getEntityInstanceCounter();
	}

	// Used by CreateBWInstanceService - only 1 instance exists
	public EntityInstance getFirstEntityInstance () {
		return getEntityInstances().get(0);
	}

	public Attribute getAttribute(String name) {
		for (Attribute attribute : getAttributes()) {
			if (attribute.getName().equals(name))
				return attribute;
		}
		return null;
	}

	public void assignAttributeInstances(GoalWorkItem goalWorkItem, Attribute attribute) {
		DataModelInstance dataModelInstance = goalWorkItem.getBwInstance().getDataModelInstance();

		if (getEntityInstances().isEmpty()) {
			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
			entityInstance.assignAttributeInstances(goalWorkItem, attribute);

			createRelationInstances(dataModelInstance, entityInstance);
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME only 1 entityInstance
				entityInstance.assignAttributeInstances(goalWorkItem, attribute);
			}
		}
	}

	public void assignAllAttributeInstances(GoalWorkItem goalWorkItem, Entity entity) {
		DataModelInstance dataModelInstance = goalWorkItem.getBwInstance().getDataModelInstance();

		if (getEntityInstances().isEmpty()) {
			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
			for (Attribute attribute : getAttributes()) {
				entityInstance.assignAttributeInstances(goalWorkItem, attribute);
			}

			createRelationInstances(dataModelInstance, entityInstance);
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME only 1 entityInstance
				for (Attribute attribute : getAttributes()) {
					entityInstance.assignAttributeInstances(goalWorkItem, attribute);
				}
			}
		}
	}

	private void createRelationInstances(DataModelInstance dataModelInstance, EntityInstance entityInstance) {
		Entity relationEntityTwo = null;
		EntityInstance relationEntityInstanceTwo = null;
		// Relation Type Exists?
		if (this.getRelationsCount() > 0) { 
			for (Relation relation : this.getRelations()) {
				// Get the other relation entity
				for (Entity entity : relation.getEntities()) {
					if (!this.getName().equals(entity.getName())) {
						relationEntityTwo = entity; // entity2
					}
				}
				// Relations instances already exists?
				if (relation.getRelationInstances().isEmpty()) {
					if (relationEntityTwo.getEntityInstances().isEmpty()){
						relationEntityInstanceTwo = new EntityInstance(dataModelInstance, relationEntityTwo);
					}
					else {
						for (EntityInstance entityInstance1 : relationEntityTwo.getEntityInstances()) { //FIXME only 1 entityInstance
							relationEntityInstanceTwo = entityInstance1;
						}
					}
					// Create Relation Instance and re-call the method for the 2 entity
					new RelationInstance(relation, entityInstance, relationEntityInstanceTwo, entityInstance.getNewRelationInstanceId());
					relationEntityTwo.createRelationInstances(dataModelInstance, relationEntityInstanceTwo);
				}
			}
		}
	}

}