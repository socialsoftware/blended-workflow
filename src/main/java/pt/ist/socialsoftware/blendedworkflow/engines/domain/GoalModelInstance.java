package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
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
		ConditionType conditionType;
		if (goalWorkItem.getState().equals(WorkItemState.PRE_GOAL)) {
			conditionType = ConditionType.ACTIVATE;
		} else {
			conditionType = ConditionType.SUCESS;
		}
		
		for (MaintainGoal maintainGoal : getMaintainGoals()) {
			TripleStateBool result = maintainGoal.getMaintainCondition().evaluate(goalWorkItem, conditionType);
			if (result == TripleStateBool.FALSE) {
				return TripleStateBool.FALSE;
			} else 	if (result == TripleStateBool.SKIPPED) {
				return TripleStateBool.SKIPPED;
			} 
		}
		return TripleStateBool.TRUE;
	}

}