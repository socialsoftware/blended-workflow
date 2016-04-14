package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class EntityInstance extends EntityInstance_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getEntity());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setEntity(Entity entity) {
		checkConsistency(getWorkflowInstance(), entity);
		super.setEntity(entity);
	}

	public EntityInstance(WorkflowInstance workflowInstance, Entity entity) {
		setWorkflowInstance(workflowInstance);
		setEntity(entity);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Entity entity) {
		if (workflowInstance != null && entity != null) {
			if (workflowInstance.getSpecification() != entity.getDataModel().getSpecification()) {
				throw new BWException(BWErrorType.CREATE_ENTITY_INSTANCE, workflowInstance.getName() + ":" + entity.getName());
			}
		}
	}

}
