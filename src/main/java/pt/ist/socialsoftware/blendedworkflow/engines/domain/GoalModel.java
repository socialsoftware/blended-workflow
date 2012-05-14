package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class GoalModel extends GoalModel_Base {

	/**
	 * Clone the GoalModel tree
	 */
	public void cloneGoalModel(GoalModelInstance newGoalModelInstance) throws BlendedWorkflowException {
		for (MaintainGoal maintainGoal: getMaintainGoals()) {
			maintainGoal.cloneGoal(newGoalModelInstance);
		}
		
		for (AchieveGoal goal : getAchieveGoals()) {
			goal.cloneGoal(newGoalModelInstance);
		}

		for (AchieveGoal goal : getAchieveGoals()) {
			for (AchieveGoal subgoal : goal.getSubGoals()) {
				newGoalModelInstance.getGoal(goal.getName()).addSubGoals(newGoalModelInstance.getGoal(subgoal.getName()));
			}
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
	
	public MaintainGoal getMaintainGoal(String name) throws BlendedWorkflowException {
		for (MaintainGoal goal : getMaintainGoals()) {
			if (goal.getName().equals(name)) {
				return goal;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME, name);
	}
}