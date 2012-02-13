package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class GoalModel extends GoalModel_Base {

	public GoalModelInstance cloneGoalModel() throws BlendedWorkflowException {
		GoalModelInstance newGoalModelInstance = new GoalModelInstance();
		for (Goal goal : getGoals()) {
			goal.cloneGoal(newGoalModelInstance);
		}

		for (Goal goal : getGoals()) {
			for (Goal subgoal : goal.getSubGoals()) {
				newGoalModelInstance.getGoal(goal.getName()).addSubGoals(newGoalModelInstance.getGoal(subgoal.getName()));
			}
		}
		return newGoalModelInstance;
	}
}