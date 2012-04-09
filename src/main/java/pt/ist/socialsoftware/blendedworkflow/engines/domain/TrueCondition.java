package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.Set;

public class TrueCondition extends TrueCondition_Base {
    
    public TrueCondition() {
        super();
    }
    
	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new TrueCondition();
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new TrueCondition();
	}
	
	@Override
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {}
	
	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {}
	
	@Override
	String getData() {
		return "";
	}
	
	@Override
	public String getString() {
		return "true";
	}
	
	@Override
	public Set<Entity> getEntities() {
		return null;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}
    
}
