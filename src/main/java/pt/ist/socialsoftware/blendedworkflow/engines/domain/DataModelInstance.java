package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class DataModelInstance extends DataModelInstance_Base {

	public DataModelInstance() {
		super();
	}

	public EntityInstance getEntityInstance(String id) throws BlendedWorkflowException {
		for (Entity entity : getEntities()) {
			for (EntityInstance entityInstance : entity.getEntityInstances()) {
				if (entityInstance.getId().equals(id)) {
					return entityInstance;
				}
			}
		}
		throw new BlendedWorkflowException("Exception @DataModelInstance: The EntityInstance id: " + id + " does not exist.");
	}

}