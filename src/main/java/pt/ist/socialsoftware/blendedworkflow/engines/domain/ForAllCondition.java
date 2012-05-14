package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

//TODO:CLASS:ForAllCondition
public class ForAllCondition extends ForAllCondition_Base {
    
    public  ForAllCondition() {
        super();
    }
    
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem) {
		return null;
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return null;
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return null;
	}

	@Override
	public
	void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
	}

	@Override
	public Set<Entity> getEntities() {
		return null;
	}

	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		return null;
	}

	@Override
	public String getRdrUndefinedConditionNEW() {
		return null;
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		return null;
	}

	@Override
	public String getRdrTrueConditionNEW() {
		return null;
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return null;
	}
	
	@Override
	public String toString() {
		return "ForALL?";
	}
    
	@Override
	public Boolean existExistEntity() {
		return false;
	}
}
