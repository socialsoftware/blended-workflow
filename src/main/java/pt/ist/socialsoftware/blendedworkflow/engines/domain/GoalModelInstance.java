package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class GoalModelInstance extends GoalModelInstance_Base {

	public AchieveGoal getGoal(String name) throws BlendedWorkflowException {
		for (AchieveGoal goal : getAchieveGoals()) {
			if (goal.getName().equals(name)) {
				return goal;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME, name);
	}

	public void checkPedingWorkItems() {
		for (AchieveGoal goal : getAchieveGoals()) {
			goal.checkPending(getBwInstance());
		}
	}
}