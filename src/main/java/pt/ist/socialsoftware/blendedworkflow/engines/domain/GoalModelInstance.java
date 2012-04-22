package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class GoalModelInstance extends GoalModelInstance_Base {

	/**
	 * Check goalTree to get enabled WorkItems
	 */
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
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME, name);
	}

}