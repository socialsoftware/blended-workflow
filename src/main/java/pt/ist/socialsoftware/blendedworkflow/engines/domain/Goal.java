package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Goal extends Goal_Base {
	
	public enum GoalState {ACHIEVED};
    
    public Goal(GoalModel goalModel, String name) throws BlendedWorkflowException {
    	checkUniqueGoalName(goalModel, name);
    	setGoalModel(goalModel);
        setName(name);
        setState(GoalState.ACHIEVED);
    }
    
    public void cloneGoal(GoalModelInstance goalModelInstance) throws BlendedWorkflowException {
    	new Goal(goalModelInstance, this.getName());
    }
    
    private void checkUniqueGoalName(GoalModel goalModel, String name) throws BlendedWorkflowException {
    	for (Goal goal : goalModel.getGoal()) {
    		if (goal.getName().equals(name)) {
    			throw new BlendedWorkflowException("@Goal-chechUniqueGoalName");
    		}
    	}
    }
    
}
