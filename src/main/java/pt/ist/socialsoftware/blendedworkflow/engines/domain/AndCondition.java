package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class AndCondition extends AndCondition_Base {

	public AndCondition(Condition one, Condition other) {
		addConditions(one);
		addConditions(other);
	}

}
