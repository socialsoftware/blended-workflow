package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface.WorkletAdapter;

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
	
	

}
