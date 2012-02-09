package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class GoalModel extends GoalModel_Base {
        
    public GoalModelInstance cloneGoalModel() throws BlendedWorkflowException {
    	GoalModelInstance newGoalModelInstance = new GoalModelInstance();
    	
    	for (Goal goal : getGoal()) {
    		goal.cloneGoal(newGoalModelInstance);
    	}    
 
    	return newGoalModelInstance;
    }
}
