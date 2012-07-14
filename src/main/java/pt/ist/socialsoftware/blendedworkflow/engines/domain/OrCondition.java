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
	
	@Override
	public String getRdrUndefinedCondition() {
		return "(" + getConditionOne().getRdrUndefinedCondition() + " & " + getConditionTwo().getRdrUndefinedCondition() + ")";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrSkippedCondition() + ")";
	}

	@Override
	public String getRdrTrueCondition() {
		return "(" + getConditionOne().getRdrTrueCondition() + " | " + getConditionTwo().getRdrTrueCondition() + ")";
	}

	@Override
	public String getRdrFalseCondition() {
		return "(" + getConditionOne().getRdrFalseCondition() + " | " + getConditionTwo().getRdrFalseCondition() + ")";
	}
	
	@Override
	public String toString() {
		return getConditionOne().toString() + " or " + getConditionTwo().toString();
	}
	
	@Override
	public Boolean existExistEntity() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}
	
	@Override
	public Boolean existCompareAttributeToValue() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}
	
	@Override
	public Boolean existTrue(){
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}

	/******************************
	 * Evaluate
	 ******************************/
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getConditionOne().evaluateWithWorkItem(goalWorkItem, conditionType).OR(getConditionTwo().evaluateWithWorkItem(goalWorkItem, conditionType));
	}
	
	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getConditionOne().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType).OR(getConditionTwo().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType));
	}
}
