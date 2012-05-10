package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class GoalModelInstance extends GoalModelInstance_Base {

	/**
	 * Check goalTree to get enabled WorkItems
	 */
	public void getEnabledWorkItems() {
		for (AchieveGoal goal : getAchieveGoals()) {
			goal.checkState(getBwInstance());
		}
	}

	public AchieveGoal getGoal(String name) throws BlendedWorkflowException {
		for (AchieveGoal goal : getAchieveGoals()) {
			if (goal.getName().equals(name)) {
				return goal;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME, name);
	}

	public void checkPedingWorkItems() {
		System.out.println("X");
		for (AchieveGoal goal : getAchieveGoals()) {
			System.out.println("X1");
			goal.checkPending(getBwInstance());
		}
	}

}