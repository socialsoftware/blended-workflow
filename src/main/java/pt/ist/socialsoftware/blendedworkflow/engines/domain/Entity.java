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
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME only 1 entityInstance
				for (Attribute attribute : getAttributes()) {
					entityInstance.assignAttributeInstances(goalWorkItem, attribute);
				}
			}
		}
	}



}