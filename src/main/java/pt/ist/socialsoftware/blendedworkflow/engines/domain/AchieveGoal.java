package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.Set;

//import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class AchieveGoal extends AchieveGoal_Base {

//	private Logger log = Logger.getLogger("Goal");
	
//	public AchieveGoal(){
//		super();
//	}



	/**
	 * Create the GoalTree root Goal.
	 */
	public AchieveGoal(GoalModel goalModel, String name, String description, Condition condition, Entity context) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setSucessCondition(condition);
		setParentGoal(null);
		setEntityContext(context);
	}

	/**
	 * Create a Goal.
	 */
	public AchieveGoal(GoalModel goalModel, AchieveGoal parentGoal, String name,String description, Condition condition, Entity context) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setSucessCondition(condition);
		setParentGoal(parentGoal);
		setEntityContext(context);
	}

	private void checkUniqueGoalName(GoalModel goalModel, String name) throws BlendedWorkflowException {
		for (AchieveGoal goal : goalModel.getAchieveGoals()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_GOAL_NAME, name);
			}
		}
		for (MaintainGoal goal : goalModel.getMaintainGoals()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_GOAL_NAME, name);
			}
		}
	}

	public void cloneGoal(GoalModelInstance goalModelInstance) throws BlendedWorkflowException {
		Condition newSucessCondition = null;
		Condition condition = getSucessCondition();
		if (condition != null) {
			newSucessCondition = condition.cloneCondition(goalModelInstance);
		}
		
		// Get EntityTypeContext from Template
		BWInstance bwInstance = goalModelInstance.getBwInstance();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity newEntityContext = null;
		for (Entity entity : dataModelInstance.getEntities()) {
			if (getEntityContext().getName().equals(entity.getName())) {
				newEntityContext = entity;
			}
		}
		
		AchieveGoal newGoal = new AchieveGoal(goalModelInstance, getName(), getDescription(), newSucessCondition, newEntityContext);
		newGoal.setUser(getUser());
		newGoal.setRole(getRole());
		
		for (Condition activateCondition: getActivateConditions()) {
			Condition newActivateCondition = activateCondition.cloneCondition(goalModelInstance);
			newGoal.addActivateConditions(newActivateCondition);
		}		
	}

	/**
	 * Get the Goal condition data to use in the use interface.
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData() {
		Set<Entity> entities = getSucessCondition().getEntities();
		Set<Attribute> attributes = getSucessCondition().getAttributes();
		String dataString = "";

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1 || count < entities.size()-1) {
				dataString += entity.getName();
			} else {
				dataString += entity.getName() + ", ";
			}
			count++;
		}
		return dataString;
	}

	/**
	 * Get the subGoals conditions data to use in the use interface.
	 * @return a string with the condition data entities.
	 */
	public String getSubGoalsData() {
		String dataString = "";
		Boolean first = true;
		for (AchieveGoal subGoal : getSubGoals()) {
			String subGoalDataString = subGoal.getConstraintData();
			if (first) {
				dataString += subGoalDataString;
			}
			else {
				dataString += ", " + subGoalDataString;
			}
		}
		return dataString;
	}

	/**********************
	 * Check Goals to create new WorkItems.
	 **********************/
//	public void checkState(BWInstance bwInstance) {
//		int subgoalsAchievedCount = 0;
//
//		if (getState() == GoalState.DEACTIVATED) {
//			if (getSubGoalsCount() > 0) { 
//				for (AchieveGoal goal : getSubGoals()) {
//					if ( (goal.getState() == GoalState.ACHIEVED) || (goal.getState() == GoalState.SKIPPED)) {
//						subgoalsAchievedCount++;
//					}
//				}
//				if (getSubGoalsCount() == subgoalsAchievedCount) { // SubGoals achieved
//					setState(GoalState.ENABLED);
//					if (getParentGoal() != null && getParentGoal().getState().equals(GoalState.ENABLED)) {
//						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.GOAL_PENDING)) {
//							getParentGoal().getGoalWorkItem().notifyConstrainViolation();
//						}
//					}
//					else {
//						new GoalWorkItem(bwInstance, this);
//					}
//				}
//			}
//			else { // No SubGoals
//				if (getState() == GoalState.DEACTIVATED) {
//					setState(GoalState.ENABLED);
//					if (!getParentGoal().equals(null) && getParentGoal().getState().equals(GoalState.ENABLED)) {
//						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.GOAL_PENDING)) {
//							getParentGoal().getGoalWorkItem().notifyConstrainViolation();
//						}
//					}
//					else {
//						new GoalWorkItem(bwInstance, this);
//					}
//				}
//			}
//		}
//	}
//
//	public void updateParentGoal() {
//		AchieveGoal parentGoal = getParentGoal();
//		if (parentGoal.getState().equals(GoalState.ENABLED)) {
//			parentGoal.setState(GoalState.DEACTIVATED);
//			parentGoal.getGoalWorkItem().notifyPending();
//		}
//	}

	public void checkPending(BWInstance bwInstance) {
		if (getGoalWorkItems() != null) {
			for (GoalWorkItem goalWorkItem : getGoalWorkItems()) {
				if (goalWorkItem.getState().equals(WorkItemState.GOAL_PENDING)) {
					goalWorkItem.evaluate(true);
				}
			}
		}
	}
		
//		int subgoalsAchievedCount = 0;	
//				for (Goal goal : getSubGoals()) {
//					if ((goal.getState() == GoalState.ACHIEVED) || (goal.getState() == GoalState.SKIPPED)) {
//						subgoalsAchievedCount++;
//					}
//				}
//				if (getSubGoalsCount() == subgoalsAchievedCount) {
//					getGoalWorkItem().notifyEnabled();
//				}
//			}
//		}
//	}
}