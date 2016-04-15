package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ActivityWorkItem extends ActivityWorkItem_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getActivity());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setActivity(Activity activity) {
		checkConsistency(getWorkflowInstance(), activity);
		super.setActivity(activity);
	}

	public ActivityWorkItem(WorkflowInstance workflowInstance, Activity activity) {
		setWorkflowInstance(workflowInstance);
		setActivity(activity);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Activity activity) {
		if (workflowInstance != null && activity != null) {
			if (workflowInstance.getSpecification() != activity.getActivityModel().getSpecification()) {
				throw new BWException(BWErrorType.WORKITEM_CONSISTENCY, workflowInstance.getSpecification().getName() + "<>"
						+ activity.getActivityModel().getSpecification().getName());
			}
		}
	}

	@Override
	public void delete() {
		setActivity(null);
		super.delete();
	}

}
