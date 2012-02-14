package pt.ist.socialsoftware.blendedworkflow.engines.domain;

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
		System.out.println("**********AND");
		return new AndCondition(getConditionOne().cloneCondition(goalModelInstance), getConditionTwo().cloneCondition(goalModelInstance));
	}
	

}
