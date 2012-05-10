package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class TrueCondition extends TrueCondition_Base {
    
    public TrueCondition() {
        super();
    }
    
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem) {
		return TripleStateBool.TRUE;
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
	public Set<Entity> getEntities() {
		return new HashSet<Entity>();
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return new HashSet<Attribute>();
	}
	
	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}

//	@Override
//	public String getRdrCondition(String type) {
//		return "True";
//	}
	
	/**
	 * TO TEST
	 */
	@Override
	public String getRdrTrueCondition() { 
		return "True";
	}

	@Override
	public String getRdrFalseCondition() { 
		return "True";
	}

	@Override
	public String getRdrSkippedCondition() { 
		return "True";
	}

	/**
	 * NEW
	 */
	@Override
	public String getRdrUndefinedConditionNEW() {
		return "True";
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		return "True";
	}

	@Override
	public String getRdrTrueConditionNEW() {
		return "True";
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return "True";
	}
	
	

}
