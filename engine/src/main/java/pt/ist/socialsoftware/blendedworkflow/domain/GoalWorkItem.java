package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GoalWorkItem extends GoalWorkItem_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getGoal());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setGoal(Goal goal) {
		checkConsistency(getWorkflowInstance(), goal);
		super.setGoal(goal);
	}

	public GoalWorkItem(WorkflowInstance workflowInstance, Goal goal) {
		setWorkflowInstance(workflowInstance);
		setGoal(goal);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Goal goal) {
		if (workflowInstance != null && goal != null) {
			if (workflowInstance.getSpecification() != goal.getGoalModel().getSpecification()) {
				throw new BWException(BWErrorType.WORKITEM_CONSISTENCY, workflowInstance.getSpecification().getName()
						+ "<>" + goal.getGoalModel().getSpecification().getName());
			}
		}
	}

	@Override
	public void delete() {
		setGoal(null);
		super.delete();
	}

}
