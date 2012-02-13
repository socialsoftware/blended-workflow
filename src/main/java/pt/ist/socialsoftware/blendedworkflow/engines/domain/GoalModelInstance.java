package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class GoalModelInstance extends GoalModelInstance_Base {

	public GoalModelInstance(){
		super();
	}

	public void getEnabledWorkItems() {
		for (Goal goal : getGoals()) {
			goal.checkState(getBwInstance());
		}
	}

	public Goal getGoal(String name) throws BlendedWorkflowException {
		for (Goal goal : getGoals()) {
			if (goal.getName().equals(name)) {
				return goal;
			}
		}
		throw new BlendedWorkflowException("Exception @GoalModelInstance: The Goal name: " + name + " does not exist.");
	}

}