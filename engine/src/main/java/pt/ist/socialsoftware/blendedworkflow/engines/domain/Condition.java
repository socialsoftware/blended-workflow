package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public abstract class Condition extends Condition_Base {

	public enum ConditionType {PRE_CONDITION, POS_CONDITION, ACTIVATE_CONDITION, SUCESS_CONDITION, MAINTAIN_CONDITION};
	public enum ConditionResult {TRUE, FALSE, SKIPPED};
	
	public Condition and(Condition one, Condition other) {
		return new AndCondition(one, other);
	}

	public Condition or(Condition one, Condition other) {
		return new OrCondition(one, other);
	}

	public Condition not(Condition condition) {
		return new NotCondition(condition);
	}
	
	abstract Condition cloneCondition(GoalModelInstance goalModelInstance);
	
	abstract Condition cloneCondition(TaskModelInstance taskModelInstance);

	public abstract void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType);
	
	abstract void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType);

	public abstract Set<Entity> getEntities();

	public abstract Set<Attribute> getAttributes();
	
	public abstract HashMap<Attribute,String> getcompareConditionValues();

	public abstract String getRdrUndefinedCondition();
	public abstract String getRdrSkippedCondition();
	public abstract String getRdrTrueCondition();
	public abstract String getRdrFalseCondition();
	
	
	public abstract String toString();
	public abstract Boolean existExistEntity();
	public abstract Boolean existCompareAttributeToValue();
	public abstract Boolean existTrue();
	
	/******************************
	 * Evaluate
	 ******************************/
	public abstract TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType);
	public abstract TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType);
	public abstract TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem, ConditionType conditionType);
}
