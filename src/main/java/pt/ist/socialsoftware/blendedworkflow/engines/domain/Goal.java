package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Goal extends Goal_Base {

	public enum GoalState {DEACTIVATED, ENABLED, ACHIEVED};

	public Goal(GoalModel goalModel, String name, Condition condition) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setCondition(condition);
		setState(GoalState.DEACTIVATED);
	}

	public Goal(GoalModel goalModel, Goal parentGoal, String name, Condition condition) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setCondition(condition);
		setParentGoal(parentGoal);
		setState(GoalState.DEACTIVATED);
	}

	private void checkUniqueGoalName(GoalModel goalModel, String name) throws BlendedWorkflowException {
		for (Goal goal : goalModel.getGoals()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @Goal: The Goal name: " + name + "already exists.");
			}
		}
	}

	public void cloneGoal(GoalModelInstance goalModelInstance) throws BlendedWorkflowException {
		Condition newCondition = null;
		Condition condition = getCondition();
		if (condition != null) {
			newCondition = condition.cloneCondition(goalModelInstance);
		}
		new Goal(goalModelInstance, getName(), newCondition);
	}

	public void checkState(BWInstance bwInstance) {
		int subgoalsAchievedCount = 0;

		if (getState() != GoalState.ENABLED) {
			if (getSubGoalsCount() > 0) { 
				for (Goal goal : getSubGoals()) {
					if (goal.getState() == GoalState.ACHIEVED) {
						subgoalsAchievedCount++;
					}
				}
				if (getSubGoalsCount() == subgoalsAchievedCount) { // SubGoals achieved
					setState(GoalState.ENABLED);
					new GoalWorkItem(bwInstance, this);
				}
			}
			else { // No SubGoals
				if (getState() == GoalState.DEACTIVATED) {
					setState(GoalState.ENABLED);
					new GoalWorkItem(bwInstance, this);
				}
			}
		}
	}

}