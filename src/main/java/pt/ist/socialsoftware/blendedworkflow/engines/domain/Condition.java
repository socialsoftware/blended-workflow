package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public abstract class Condition extends Condition_Base {

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

	abstract void assignAttributeInstances(GoalWorkItem goalWorkItem);
	
	abstract void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType);

}
