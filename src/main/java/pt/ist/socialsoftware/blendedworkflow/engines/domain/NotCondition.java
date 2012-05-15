package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class NotCondition extends NotCondition_Base {

	public NotCondition(Condition condition) {
		setCondition(condition);
	}
	
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getCondition().evaluate(goalWorkItem, conditionType).NOT();
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new NotCondition(getCondition().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new NotCondition(getCondition().cloneCondition(taskModelInstance));
	}
	
	@Override
	public
	void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		getCondition().assignAttributeInstances(goalWorkItem, conditionType);
	}
	
	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getCondition().assignAttributeInstances(taskWorkItem, conditionType);
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
		return new HashMap<Attribute, String>();
	}
	
//	@Override
//	public String getRdrCondition(String type) {
//		return "(! " + getCondition() + ")";
//	}
	
	/**
	 * TO TEST
	 */
	@Override
	public String getRdrTrueCondition() { 
		return getCondition().getRdrFalseCondition();
	}

	@Override
	public String getRdrFalseCondition() { 
		return getCondition().getRdrTrueCondition();
	}

	@Override
	public String getRdrSkippedCondition() { 
		return getCondition().getRdrSkippedCondition();
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
	
	@Override
	public String toString() {
		return "!(" + getCondition().toString() + ")";
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}

	

}
