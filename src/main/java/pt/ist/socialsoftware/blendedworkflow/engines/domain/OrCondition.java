package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class OrCondition extends OrCondition_Base {

	public OrCondition(Condition one, Condition two) {
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
		return new OrCondition(getConditionOne().cloneCondition(goalModelInstance), getConditionTwo().cloneCondition(goalModelInstance));
	}
	
	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new OrCondition(getConditionOne().cloneCondition(taskModelInstance), getConditionTwo().cloneCondition(taskModelInstance));
	}

	@Override
	public
	void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		getConditionOne().assignAttributeInstances(goalWorkItem,conditionType);
		getConditionTwo().assignAttributeInstances(goalWorkItem, conditionType);
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
//		return getConditionOne().getRdrCondition(type) + " | " + getConditionTwo().getRdrCondition(type);
//	}
	
	/**
	 * OR | T | F | S
	 * --------------
	 *  T | T | T | T
	 * --------------
	 *  F | T | F | F
	 * --------------
	 *  S | T | F | S
	 */
	@Override
	public String getRdrTrueCondition() {
		String condition = "((" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrFalseCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrTrueCondition() + "))";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		String condition = "((" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrFalseCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ") | ";
		condition += "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrFalseCondition() + "))";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		return "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ")";
	}
	
	/**
	 * NEW
	 */
	@Override
	public String getRdrUndefinedConditionNEW() {
		return "(" + getConditionOne().getRdrUndefinedConditionNEW() + " & " + getConditionTwo().getRdrUndefinedConditionNEW() + ")";
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		return "(" + getConditionOne().getRdrSkippedConditionNEW() + " & " + getConditionTwo().getRdrSkippedConditionNEW() + ")";
	}

	@Override
	public String getRdrTrueConditionNEW() {
		return "(" + getConditionOne().getRdrTrueConditionNEW() + " | " + getConditionTwo().getRdrTrueConditionNEW() + ")";
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return "(" + getConditionOne().getRdrFalseConditionNEW() + " | " + getConditionTwo().getRdrFalseConditionNEW() + ")";
	}
	
	@Override
	public String toString() {
		return getConditionOne().toString() + " or " + getConditionTwo().toString();
	}
	
	@Override
	public Boolean existExistEntity() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}

	/******************************
	 * Evaluate
	 ******************************/
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getConditionOne().evaluateWithWorkItem(goalWorkItem, conditionType).OR(getConditionTwo().evaluateWithWorkItem(goalWorkItem, conditionType));
	}
	
	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance) {
		return getConditionOne().evaluateWithDataModel(entityInstance).OR(getConditionTwo().evaluateWithDataModel(entityInstance));
	}
}
