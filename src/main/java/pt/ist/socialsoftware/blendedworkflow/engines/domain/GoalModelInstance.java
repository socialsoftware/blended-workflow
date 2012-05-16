package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class GoalModelInstance extends GoalModelInstance_Base {

	/**
	 * Check goalTree to get enabled WorkItems
	 */
//	public void getEnabledWorkItems() {
//		for (AchieveGoal goal : getAchieveGoals()) {
//			goal.checkState(getBwInstance());
//		}
//	}

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
	
	public TripleStateBool evaluateMaintainGoals(GoalWorkItem goalWorkItem) {
		//TODO: evaluate only affected maintainGoals
//		ConditionType conditionType;
//		if (goalWorkItem.getState().equals(WorkItemState.PRE_GOAL)) {
//			conditionType = ConditionType.ACTIVATE;
//		} else {
//			conditionType = ConditionType.SUCESS;
//		}
		
		Entity goalContext = goalWorkItem.getAchieveGoal().getEntityContext();
		for (MaintainGoal maintainGoal : getMaintainGoals()) {
			Entity maintainGoalContext = maintainGoal.getMaintainGoalEntityContext();
			if (maintainGoalContext.equals(goalContext) && maintainGoal.getState().equals(GoalState.ENABLED)) {
				TripleStateBool result = maintainGoal.getMaintainCondition().evaluateWithDataModel(null);
				if (result == TripleStateBool.FALSE) {
					return TripleStateBool.FALSE;
				} else 	if (result == TripleStateBool.SKIPPED) {
					return TripleStateBool.SKIPPED;
				} 
			}
		}
		return TripleStateBool.TRUE;
	}

}