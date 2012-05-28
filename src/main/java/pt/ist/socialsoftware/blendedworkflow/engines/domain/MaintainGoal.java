package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class MaintainGoal extends MaintainGoal_Base {
	
	public enum GoalState {DEACTIVATED, ENABLED};
    
	public MaintainGoal(GoalModel goalModel, String name, String description, Condition condition, Entity context) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setMaintainCondition(condition);
		setMaintainGoalEntityContext(context);
		setState(GoalState.ENABLED);
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
		Condition newMaintainCondition = null;
		Condition condition = getMaintainCondition();
		if (condition != null) {
			newMaintainCondition = condition.cloneCondition(goalModelInstance);
		}
		
		//Get EntityTypeContext from Template
		BWInstance bwInstance = goalModelInstance.getBwInstance();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity newEntityContext = null;
		for (Entity entity : dataModelInstance.getEntities()) {
			if (getMaintainGoalEntityContext().getName().equals(entity.getName())) {
				newEntityContext = entity;
			}
		}
		
		new MaintainGoal(goalModelInstance, getName(), getDescription(), newMaintainCondition, newEntityContext);	
	}
}
