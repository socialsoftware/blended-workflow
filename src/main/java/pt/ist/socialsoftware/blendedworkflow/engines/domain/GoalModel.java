package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class GoalModel extends GoalModel_Base {

	/**
	 * Clone the GoalModel tree
	 */
	public void cloneGoalModel(GoalModelInstance newGoalModelInstance) throws BlendedWorkflowException {
		for (Goal goal : getGoals()) {
			goal.cloneGoal(newGoalModelInstance);
		}

		for (Goal goal : getGoals()) {
			for (Goal subgoal : goal.getSubGoals()) {
				newGoalModelInstance.getGoal(goal.getName()).addSubGoals(newGoalModelInstance.getGoal(subgoal.getName()));
			}
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