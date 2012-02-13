package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class GoalWorkItem extends GoalWorkItem_Base {

	public GoalWorkItem(BWInstance bwInstance, Goal goal) {
		setBwInstance(bwInstance);
		setGoal(goal);
		setId(goal.getName() + "." + getNewWorkItemId()); // Id: GoalName.#
		setState(WorkItemState.ENABLED);
	}

	@Override
	public String getElementId() {
		return getGoal().getName();
	}

}
