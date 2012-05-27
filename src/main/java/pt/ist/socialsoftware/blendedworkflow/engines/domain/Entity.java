package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Entity extends Entity_Base {
	
	private static Logger log = Logger.getLogger("--------------->");

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
			//FIXME:TEST
			log.info(goalWorkItem.getID() + " with context:" + entityContext.getName() + " # from" + this.getName());
			log.info(entityInstanceContext.getID() + " has RelationInstance1Count." + entityInstanceContext.getEntityInstanceOneRelationInstancesCount());
			log.info(entityInstanceContext.getID() + " has RelationInstance2Count." + entityInstanceContext.getEntityInstanceTwoRelationInstancesCount());
			
			log.info("Search1 RelationInstances");
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				log.info("RelationEntity2: " + relationEntityContext.getName() + " =" + this.getName());
				if (relationEntityContext.equals(this)) {
					log.info("Context Found equal to 1 assign");
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}

			log.info("Search2 RelationInstances");
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceTwoRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceOne().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceOne();
				log.info("RelationEntity1" + relationEntityContext.getName() + " =" + this.getName());
				if (relationEntityContext.equals(this)) {
					log.info("Context Found equal to 2 assign");
					relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				} else {
					for (RelationInstance r2 : relationEntityInstanceContext.getEntityInstanceTwoRelationInstances()) {
						log.info("--I" + r2.getEntityInstanceOne().getEntity().getName() + " =" + this.getName());
						if (r2.getEntityInstanceOne().getEntity().equals(this)) {
							log.info("r2");
							r2.getEntityInstanceOne().assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
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

			//FIXME: bad hack
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
		log.info("GoalWorkItem" + goalWorkItem.getID() + "Entity" + entity.getName() + "conditionType" + conditionType);
		EntityInstance entityInstanceContext = goalWorkItem.getEntityInstanceContext();
		Entity entityContext = entityInstanceContext.getEntity();
		
		if (this.equals(entityContext)) {
			for (Attribute attribute : entityContext.getAttributes()) {
				if (attribute.getIsKeyAttribute()) {
					entityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
				}
			}
		} else {
			//FIXME:TEST
			log.info("Condition Context is diferent from Goal Context." + entityContext.getName());
			log.info("RelationInstance1Count." + entityInstanceContext.getEntityInstanceOneRelationInstancesCount());
			log.info("RelationInstance2Count." + entityInstanceContext.getEntityInstanceTwoRelationInstancesCount());
			for (RelationInstance relationInstance : entityInstanceContext.getEntityInstanceOneRelationInstances()) {
				Entity relationEntityContext = relationInstance.getEntityInstanceTwo().getEntity();
				EntityInstance relationEntityInstanceContext = relationInstance.getEntityInstanceTwo();
				if (relationEntityContext.equals(this)) {
					log.info("1=");
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
				log.info("REC" + relationEntityContext.getName() + " this" + this.getName());
				if (relationEntityContext.equals(this)) {
					log.info("2=");
					for (Attribute attribute : relationEntityContext.getAttributes()) {
						if (attribute.getIsKeyAttribute()) {
							relationEntityInstanceContext.assignAttributeInstances(goalWorkItem, attribute, conditionType);
						}
					}
				}
			}
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

			//FIXME: bad hack
			if (!taskWorkItem.getTask().getName().equals("Booking")) {
				createRelationInstances(dataModelInstance, entityInstance);
			}
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
//		log.info("createRelationInstances: init: " + entityInstance.getID());
		Entity relationEntityTwo = null;
		EntityInstance relationEntityInstanceTwo = null;
		// Relation Type Exists?
//		log.info("createRelationInstances: getRelationsCount: " + this.getRelationsCount());
		if (this.getRelationsCount() > 0) { 
			for (Relation relation : this.getRelations()) {
//				log.info("createRelationInstances: for1: " + relation.getName());
				// Get the other relation entity
				for (Entity entity : relation.getEntities()) {
					if (!this.getName().equals(entity.getName())) {
//						log.info("createRelationInstances: relationEntityTwo: " + entity);
						relationEntityTwo = entity; // entity2
					}
				}
				// Relations instances already exists?
//				log.info("createRelationInstances: relation.getRelationInstances().isEmpty(): " + relation.getRelationInstances().isEmpty());
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
//					log.info("createRelationInstances: new RelationInstance: " + relation.getName() + relationInstanceOne.getID() + relationInstanceTwo.getID());
					new RelationInstance(relation, relationInstanceOne, relationInstanceTwo, relationInstanceOne.getNewRelationInstanceID());
//					if (entityInstance.getEntity().getName().equals("?")) {
//					relationEntityTwo.createRelationInstances(dataModelInstance, relationEntityInstanceTwo);
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