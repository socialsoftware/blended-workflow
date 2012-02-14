package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class DataModel extends DataModel_Base {

	public enum DataState {DEFINED, UNDEFINED, SKIPPED}

	public DataModelInstance cloneDataModel() throws BlendedWorkflowException {
		DataModelInstance newDataModelInstance = new DataModelInstance();
		Entity relationEntityOne;
		Entity relationEntityTwo;

		for (Entity entity : getEntities()) {
			entity.cloneEntity(newDataModelInstance);
		}

		// Get relation -> Get new Entities -> clone with new Entities
		for (Relation relation : getRelations()) { 
			relationEntityOne = newDataModelInstance.getEntity(relation.getEntityOne().getName());
			relationEntityTwo = newDataModelInstance.getEntity(relation.getEntityTwo().getName());
			relation.cloneRelation(newDataModelInstance, relationEntityOne, relationEntityTwo);
		}
		return newDataModelInstance;
	}

	public Entity getEntity(String name) {
		for (Entity entity : getEntities()) {
			if (entity.getName().equals(name)) {
				return entity;
			}
		}
		return null; // Needed by ConditionParser to create new Entity
	}

//	public Attribute getAttribute(String name) {
//		for (Attribute attribute : getAttributes()) {
//			if (attribute.getName().equals(name)) {
//				return attribute;
//			}
//		}
//		return null; // Needed by ConditionParser to create new Attribute
//	}

}