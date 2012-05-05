package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

public class AndCondition extends AndCondition_Base {

	public AndCondition(Condition one, Condition two) {
		addConditions(one);
		addConditions(two);
	}
	
	public Condition getConditionOne(){
		return getConditions().get(0);
	}
	
	public Condition getConditionTwo(){
		return getConditions().get(1);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new AndCondition(getConditionOne().cloneCondition(goalModelInstance), getConditionTwo().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new AndCondition(getConditionOne().cloneCondition(taskModelInstance), getConditionTwo().cloneCondition(taskModelInstance));
	}
	
	@Override
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getConditionOne().assignAttributeInstances(goalWorkItem);
		getConditionTwo().assignAttributeInstances(goalWorkItem);
	}
	
	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getConditionOne().assignAttributeInstances(taskWorkItem, conditionType);
		getConditionTwo().assignAttributeInstances(taskWorkItem, conditionType);
	}
	
	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entitiesOne = getConditionOne().getEntities();
		Set<Entity> entitiesTwo = getConditionTwo().getEntities();
		entitiesOne.addAll(entitiesTwo);
		return entitiesOne;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attributesOne = getConditionOne().getAttributes();
		Set<Attribute> attributesTwo = getConditionTwo().getAttributes();
		attributesOne.addAll(attributesTwo);
		return attributesOne;
	}
	
	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> attributesOne = getConditionOne().getcompareConditionValues();
		HashMap<Attribute, String> attributesTwo = getConditionTwo().getcompareConditionValues();
		attributesOne.putAll(attributesTwo);
		return attributesOne;
	}
	
//	@Override
//	public String getRdrCondition(String type) {
//		if (type == "DEFINED") {
//			return getConditionOne().getRdrCondition(type) + " & " + getConditionTwo().getRdrCondition(type);
//		} else {
//			return getConditionOne().getRdrCondition(type) + " | " + getConditionTwo().getRdrCondition(type);
//		}
//	}
	
	/**
	 * AND| T | F | S   
	 * --------------
	 *  T | T | F | S
	 * --------------
	 *  F | F | F | S
	 * --------------
	 *  S | S | S | S
	 */
	@Override
	public String getRdrTrueCondition() {
		return "(" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ")";
	}

	@Override
	public String getRdrFalseCondition() {
		String condition = "((" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrFalseCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrFalseCondition() + "))";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "((" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrFalseCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + "))";
		return condition;
	}
	
	/**
	 * NEW
	 */
	@Override
	public String getRdrUndefinedConditionNEW() {
		return "(" + getConditionOne().getRdrUndefinedConditionNEW() + " | " + getConditionTwo().getRdrUndefinedConditionNEW() + ")";
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		return "(" + getConditionOne().getRdrSkippedConditionNEW() + " | " + getConditionTwo().getRdrSkippedConditionNEW() + ")";
	}

	@Override
	public String getRdrTrueConditionNEW() {
		return "(" + getConditionOne().getRdrTrueConditionNEW() + " & " + getConditionTwo().getRdrTrueConditionNEW() + ")";
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return "(" + getConditionOne().getRdrFalseConditionNEW() + " & " + getConditionTwo().getRdrFalseConditionNEW() + ")";
	}
	
	
}
