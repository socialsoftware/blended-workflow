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
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new TrueCondition();
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new TrueCondition();
	}
	
	@Override
	public
	void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {}
	
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

	@Override
	public String getRdrUndefinedCondition() {
		return "True";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "True";
	}

	@Override
	public String getRdrTrueCondition() {
		return "True";
	}

	@Override
	public String getRdrFalseCondition() {
		return "True";
	}
	
	@Override
	public String toString() {
		return "True";
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return TripleStateBool.TRUE;
	}
	
	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return TripleStateBool.TRUE;
	}
	
	

}
