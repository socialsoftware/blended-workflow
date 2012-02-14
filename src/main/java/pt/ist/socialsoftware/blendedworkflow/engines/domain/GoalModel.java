package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class GoalModel extends GoalModel_Base {

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
}