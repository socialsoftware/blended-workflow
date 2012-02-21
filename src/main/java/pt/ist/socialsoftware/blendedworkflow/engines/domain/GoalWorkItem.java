package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal.GoalState;

public class GoalWorkItem extends GoalWorkItem_Base {

	public GoalWorkItem(BWInstance bwInstance, Goal goal) {
		setBwInstance(bwInstance);
		setGoal(goal);
		setId(goal.getName() + "." + bwInstance.getNewWorkItemId()); // Id: GoalName.#
		goal.getCondition().assignAttributeInstances(this);
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
	}

	@Override
	public String getElementId() {
		return getGoal().getName();
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
	public void notifyPreTask() {}



}
