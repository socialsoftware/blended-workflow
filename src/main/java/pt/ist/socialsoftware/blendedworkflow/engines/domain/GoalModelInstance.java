package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashSet;
import java.util.Set;

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

	/**
	 * Check all GoalWorkItems in the ACTIVATED state, due to the achievement or skipping of a goalWorkItem.
	 */
	public void checkActivatedWorkItems() {
		for (AchieveGoal goal : getAchieveGoals()) {
			goal.checkActivated(getBwInstance());
		}
	}
	
	/**
	 * Given an AchieveGoal, gets all related MaintainGoals.
	 * @param achieveGoal An AchieveGoal.
	 * @return a set containing all the related MaintainGoals.
	 */
	public Set<MaintainGoal> getAchieveGoalAssociatedMaintainGoals(AchieveGoal achieveGoal){
		Set<MaintainGoal> maintainGoals = new HashSet<MaintainGoal>();
		Set<Attribute> achieveAttributes = new HashSet<Attribute>();
		Set<Entity> achieveEntities = new HashSet<Entity>();
		
		//Get AchieveGoal Conditions Data
		for (Condition ac : achieveGoal.getActivateConditions()) {
			achieveAttributes.addAll(ac.getAttributes());
			achieveEntities.addAll(ac.getEntities());
		}
		achieveAttributes.addAll(achieveGoal.getSucessCondition().getAttributes());
		achieveEntities.addAll(achieveGoal.getSucessCondition().getEntities());
		
		for (MaintainGoal maintainGoal : getMaintainGoals()) {
			Set<Attribute> maintainAttributes = new HashSet<Attribute>();
			Set<Entity> maintainEntities = new HashSet<Entity>();
			
			//Get MaintainGoal Condition Data
			maintainAttributes.addAll(maintainGoal.getMaintainCondition().getAttributes());
			maintainEntities.addAll(maintainGoal.getMaintainCondition().getEntities());
			
			achieveAttributes.retainAll(maintainAttributes);
			achieveEntities.retainAll(maintainEntities);
			
			if (!achieveAttributes.isEmpty() || !achieveEntities.isEmpty()) {
				maintainGoals.add(maintainGoal);
			}
		}
		
		return maintainGoals;
	}
}