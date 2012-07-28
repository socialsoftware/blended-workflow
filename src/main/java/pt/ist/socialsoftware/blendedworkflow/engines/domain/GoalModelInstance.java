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

		for (MaintainGoal maintainGoal : getMaintainGoals()) {
			//Get AchieveGoal Conditions Data
			Set<Attribute> achieveAttributes = new HashSet<Attribute>();
			Set<Entity> achieveEntities = new HashSet<Entity>();
			for (Condition ac : achieveGoal.getActivateConditions()) {
				achieveAttributes.addAll(ac.getAttributes());
				achieveEntities.addAll(ac.getEntities());
			}
			achieveAttributes.addAll(achieveGoal.getSucessCondition().getAttributes());
			achieveEntities.addAll(achieveGoal.getSucessCondition().getEntities());
			
			//Get MaintainGoal Condition Data
			Set<Attribute> maintainAttributes = new HashSet<Attribute>();
			Set<Entity> maintainEntities = new HashSet<Entity>();
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
	
	/**
	 * Given an AchieveGoal, gets all related MaintainGoals.
	 * @param achieveGoal An AchieveGoal.
	 * @return a set containing all the related MaintainGoals.
	 */
	public Set<AchieveGoal> getAchieveGoalAssociatedAchieveGoals(AchieveGoal achieveGoal){
		Set<AchieveGoal> achieveGoalsRESULT = new HashSet<AchieveGoal>();
		
		for (AchieveGoal ag : getAchieveGoals()) {
			if (!achieveGoal.equals(ag)) {
				//Given AchieveGoal Conditions Data
				Set<Attribute> achieveAttributes = new HashSet<Attribute>();
				Set<Entity> achieveEntities = new HashSet<Entity>();
				achieveAttributes.addAll(achieveGoal.getSucessCondition().getAttributes());
				achieveEntities.addAll(achieveGoal.getSucessCondition().getEntities());

				//For AchieveGoal Conditions Data
				Set<Attribute> agAttributes = new HashSet<Attribute>();
				Set<Entity> agEntities = new HashSet<Entity>();
				for (Condition ac : ag.getActivateConditions()) {
					agAttributes.addAll(ac.getAttributes());
					agEntities.addAll(ac.getEntities());
				}
				agAttributes.addAll(ag.getSucessCondition().getAttributes());
				agEntities.addAll(ag.getSucessCondition().getEntities());
				
				achieveAttributes.retainAll(agAttributes);
				achieveEntities.retainAll(agEntities);

				if (!achieveAttributes.isEmpty() || !achieveEntities.isEmpty()) {
					achieveGoalsRESULT.add(ag);
				}
			}
		}
		return achieveGoalsRESULT;
	}
	
	public Set<Relation> getSubGoalsRelations(AchieveGoal topGoal) {
		Set<Relation> result = new HashSet<Relation>();
		
		DataModelInstance dataModelInstance = getBwInstance().getDataModelInstance();
		for (AchieveGoal subGoal : topGoal.getSubGoals()) {
			result.addAll(dataModelInstance.getRelations(topGoal.getEntityContext(), subGoal.getEntityContext()));
		}
		
		return result;
	}
}