package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class Goal extends Goal_Base {
	
	public enum GoalState {ACHIEVED};
    
    public  Goal(String id, String name) {
        setId(id);
        setName(name);
        setState(GoalState.ACHIEVED);
    }
    
}
