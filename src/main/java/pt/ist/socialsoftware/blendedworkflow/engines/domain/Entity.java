package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

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
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_ENTITY_NAME, name);
			}
		}
	}

	public void cloneEntity(DataModelInstance dataModelInstance) throws BlendedWorkflowException {
		Entity newEntity = new Entity(dataModelInstance, getName());
		for (Attribute attribute : getAttributes()) {
			attribute.cloneAttribute(dataModelInstance, newEntity);
		}
		
		for (EntityInstance ei : getEntityInstances()) {
			ei.cloneEntityInstance(dataModelInstance, newEntity);
		}
	}

	/**
	 * Create and assign EntityInstances and AttributesInstances to Workitems
	 */
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, Attribute attribute, ConditionType conditionType) {
		EntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();
		
		if (this.equals(entityContext)) {
			entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
		} else {
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}

			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceTwoRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				} else {
					for (RelationInstance r2 : relationEntityInstanceContext.getEntityInstanceTwoRelationInstances()) {
						if (r2.getEntityInstanceOne().getEntity().equals(this)) {
							r2.getEntityInstanceOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
		}
	}
	
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, Attribute attribute, ConditionType conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();
		if (getEntityInstances().isEmpty()) {
//			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this); //OLD
			EntityInstance entityInstance = new EntityInstance(this);
			entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);

			//FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME: only 1 entityInstance
				entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}
		}
	}

	public void assignAllAttributeInstances(GoalWorkItem goalWorkItem, Entity entity, ConditionType conditionType) {
		EntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();
		
		if (this.equals(entityContext)) {
			for (Attribute attribute : entityContext.getAttributes()) {
				if (attribute.getIsKeyAttribute()) {
					entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}
		} else {
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					for (Attribute attribute : relationEntityContext.getAttributes()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}

			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceTwoRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				if (relationEntityContext.equals(this)) {
					for (Attribute attribute : relationEntityContext.getAttributes()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
		}
	}

	public void assignAllAttributeInstances(TaskWorkItem taskWorkItem, Entity entity, ConditionType conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();

		if (getEntityInstances().isEmpty()) {
//			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
			EntityInstance entityInstance = new EntityInstance(this);
			for (Attribute attribute : getAttributes()) {
				if (attribute.getIsKeyAttribute())
				entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}

			//FIXME: Bad Hack!
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME: Only 1 entityInstance
				for (Attribute attribute : getAttributes()) {
					if (attribute.getIsKeyAttribute())
					entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
				}
			}
		}
	}

	private void createRelationInstances(DataModelInstance dataModelInstance, EntityInstance entityInstance) {
		Entity relationEntityTwo = null;
		EntityInstance relationEntityInstanceTwo = null;
		//Relation Type Exists?
		if (this.getRelationsCount() > 0) { 
			for (Relation relation : this.getRelations()) {
				//Get the other relation entity
				for (Entity entity : relation.getEntities()) {
					if (!this.getName().equals(entity.getName())) {
						relationEntityTwo = entity; // entity2
					}
				}
				//Relations instances already exists?
				if (relation.getRelationInstances().isEmpty()) {
					if (relationEntityTwo.getEntityInstances().isEmpty()){
//						relationEntityInstanceTwo = new EntityInstance(dataModelInstance, relationEntityTwo);
						relationEntityInstanceTwo = new EntityInstance(relationEntityTwo);
					}
					else {
						for (EntityInstance entityInstance1 : relationEntityTwo.getEntityInstances()) { //FIXME: only 1 entityInstance
							relationEntityInstanceTwo = entityInstance1;
						}
					}
					//Create Relation Instance and re-call the method for the 2 entity
					EntityInstance relationInstanceOne;
					EntityInstance relationInstanceTwo;
					
					if (entityInstance.getEntity().equals(relation.getEntityOne())) {
						relationInstanceOne = entityInstance;
						relationInstanceTwo = relationEntityInstanceTwo;
					} else {
						relationInstanceOne = relationEntityInstanceTwo;
						relationInstanceTwo = entityInstance;
					}
					new RelationInstance(relation, relationInstanceOne, relationInstanceTwo, relationInstanceOne.getNewRelationInstanceID());
//					if (entityInstance.getEntity().getName().equals("?")) {
//						relationEntityTwo.createRelationInstances(dataModelInstance, relationEntityInstanceTwo);
//					}
				}
			}
		}
	}
	
	public int getNewEntityInstanceId () {
		setEntityInstanceCounter(getEntityInstanceCounter()+1);
		return getEntityInstanceCounter();
	}

	public Attribute getAttribute(String name) {
		for (Attribute attribute : getAttributes()) {
			if (attribute.getName().equals(name))
				return attribute;
		}
		return null;
	}

	public EntityInstance getEntityInstance(String ID) {
		for (EntityInstance entityInstance : getEntityInstances()) {
			if (entityInstance.getID().equals(ID)) {
				return entityInstance;
			}
		}
		return null;
	}

}