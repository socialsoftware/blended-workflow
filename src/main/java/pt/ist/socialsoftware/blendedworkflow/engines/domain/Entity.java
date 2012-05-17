package pt.ist.socialsoftware.blendedworkflow.engines.domain;

//import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Entity extends Entity_Base {
	
//	private static Logger log = Logger.getLogger("Entity");

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
			//FIXME
//			log.error("Condition Context is diferent from Goal Context.");
//			for (RelationInstance relationInstance : entityInstanceContext.getRelationInstances()) {
//				//Relation EntityOne = than check EntityTwo
//				if (relationInstance.getEntityOne() == entityInstanceContext) {
//					if (relationInstance.getEntityTwo().getEntity() == this){
//						relationInstance.getEntityTwo().assignAttributeInstances(goalWorkItem, attribute, conditionType);
//					}
//				}
//				//Relation EntityTwo = than check EntityOne
//				if (relationInstance.getEntityTwo() == entityInstanceContext) {
//					if (relationInstance.getEntityOne().getEntity() == this){
//						relationInstance.getEntityOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
//					}
//				}
//			}
		}
		
		
//		DataModelInstance dataModelInstance = goalWorkItem.getBwInstance().getDataModelInstance();
//		if (getEntityInstances().isEmpty()) {
//			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
//			entityInstance.assignAttributeInstances(goalWorkItem, attribute, conditionType);
//
//			createRelationInstances(dataModelInstance, entityInstance);
//		}
//		else {
//			for (EntityInstance entityInstance : getEntityInstances()) { // only 1 entityInstance
//				entityInstance.assignAttributeInstances(goalWorkItem, attribute, conditionType);
//			}
//		}
	}
	
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, Attribute attribute, String conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();
		if (getEntityInstances().isEmpty()) {
			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
			entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);

			createRelationInstances(dataModelInstance, entityInstance);
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
			//FIXME
//			log.error("Condition Context is diferent from Goal Context.");
//			for (RelationInstance relationInstance : entityInstanceContext.getRelationInstances()) {
//				//Relation EntityOne = than check EntityTwo
//				if (relationInstance.getEntityOne() == entityInstanceContext) {
//					if (relationInstance.getEntityTwo().getEntity() == this){
//						for (Attribute attribute : entityContext.getAttributes()) {
//							if (attribute.getIsKeyAttribute()) {
//								relationInstance.getEntityTwo().assignAttributeInstances(goalWorkItem, attribute, conditionType);
//							}
//						}
//					}
//				}
//				//Relation EntityTwo = than check EntityOne
//				if (relationInstance.getEntityTwo() == entityInstanceContext) {
//					if (relationInstance.getEntityOne().getEntity() == this){
//						for (Attribute attribute : entityContext.getAttributes()) {
//							if (attribute.getIsKeyAttribute()) {
//								relationInstance.getEntityOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
//							}
//						}
//					}
//				}
//			}
		}
		
//		DataModelInstance dataModelInstance = goalWorkItem.getBwInstance().getDataModelInstance();
//		if (getEntityInstances().isEmpty()) {
//			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
//			for (Attribute attribute : getAttributes()) {
//				if (attribute.getIsKeyAttribute())
//				entityInstance.assignAttributeInstances(goalWorkItem, attribute, conditionType);
//			}
//
//			createRelationInstances(dataModelInstance, entityInstance);
//		}
//		else {
//			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME only 1 entityInstance
//				for (Attribute attribute : getAttributes()) {
//					if (attribute.getIsKeyAttribute())
//					entityInstance.assignAttributeInstances(goalWorkItem, attribute, conditionType);
//				}
//			}
//		}
	}

	public void assignAllAttributeInstances(TaskWorkItem taskWorkItem, Entity entity, String conditionType) {
		DataModelInstance dataModelInstance = taskWorkItem.getBwInstance().getDataModelInstance();

		if (getEntityInstances().isEmpty()) {
			EntityInstance entityInstance = new EntityInstance(dataModelInstance, this);
			for (Attribute attribute : getAttributes()) {
				if (attribute.getIsKeyAttribute())
				entityInstance.assignAttributeInstances(taskWorkItem, attribute, conditionType);
			}

			createRelationInstances(dataModelInstance, entityInstance);
		}
		else {
			for (EntityInstance entityInstance : getEntityInstances()) { //FIXME only 1 entityInstance
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
					EntityInstance relationInstanceOne;
					EntityInstance relationInstanceTwo;
					
					//TODO:test
					if (entityInstance.getEntity().equals(relation.getEntityOne())) {
						relationInstanceOne = entityInstance;
						relationInstanceTwo = relationEntityInstanceTwo;
					} else {
						relationInstanceOne = relationEntityInstanceTwo;
						relationInstanceTwo = entityInstance;
					}
					
					new RelationInstance(relation, relationInstanceOne, relationInstanceTwo, relationInstanceOne.getNewRelationInstanceID());
					relationEntityTwo.createRelationInstances(dataModelInstance, relationEntityInstanceTwo);
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