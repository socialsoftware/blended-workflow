package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class DataModel extends DataModel_Base {

	public enum DataState {
		DEFINED, UNDEFINED, SKIPPED
	}

	/**
	 * Clone the DataModel.
	 */
	public DataModelInstance cloneDataModel() throws BlendedWorkflowException {
		DataModelInstance newDataModelInstance = new DataModelInstance();
		Entity relationEntityOne;
		Entity relationEntityTwo;

		for (Entity entity : getEntitiesSet()) {
			entity.cloneEntity(newDataModelInstance);
		}

		// Get relation -> Get new Entities -> Clone with new Entities
		for (Relation relation : getRelationsSet()) {
			relationEntityOne = newDataModelInstance.getEntity(relation
					.getEntityOne().getName());
			relationEntityTwo = newDataModelInstance.getEntity(relation
					.getEntityTwo().getName());
			relation.cloneRelation(newDataModelInstance, relationEntityOne,
					relationEntityTwo);
		}
		return newDataModelInstance;
	}

	public Entity getEntity(String name) {
		for (Entity entity : getEntitiesSet()) {
			if (entity.getName().equals(name)) {
				return entity;
			}
		}
		return null;
	}

	public Relation getRelation(String name) {
		for (Relation relation : getRelationsSet()) {
			if (relation.getName().equals(name)) {
				return relation;
			}
		}
		return null;
	}

}