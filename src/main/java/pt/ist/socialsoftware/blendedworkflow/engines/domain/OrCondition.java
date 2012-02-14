package pt.ist.socialsoftware.blendedworkflow.engines.domain;

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
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getConditionOne().assignAttributeInstances(goalWorkItem);
		getConditionTwo().assignAttributeInstances(goalWorkItem);
	}
}
