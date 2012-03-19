package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal.GoalState;

public class GoalWorkItem extends GoalWorkItem_Base {

	public GoalWorkItem(BWInstance bwInstance, Goal goal) {
		setBwInstance(bwInstance);
		setGoal(goal);
		setID(goal.getName() + "." + bwInstance.getNewWorkItemID()); // Id: GoalName.#
		goal.getCondition().assignAttributeInstances(this);
		createConstrainViolationWorkItemArguments();
		notifyConstrainViolation();
	}

	private void createConstrainViolationWorkItemArguments() {
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, null);
			addConstrainViolationWorkItemArguments(workItemArgument);
		}
	}

	@Override
	public void notifyEnabled() {
		setState(WorkItemState.ENABLED);
	}

	@Override
	public void notifyCompleted() {
		setState(WorkItemState.COMPLETED);
		getGoal().setState(GoalState.ACHIEVED);
		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}

	@Override
	public void notifySkipped() {
		setState(WorkItemState.SKIPPED);
		getGoal().setState(GoalState.SKIPPED);
		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}

	@Override
	public void notifyPending() {
		setState(WorkItemState.PENDING);
		BlendedWorkflow.getInstance().getWorkListManager().notifyPendingWorkItem(this);
	}

	@Override
	public void notifyConstrainViolation() {
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}

}
