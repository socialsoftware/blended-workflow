package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class OrCondition extends OrCondition_Base {

	public OrCondition(Condition one, Condition other) {
		addConditions(one);
		addConditions(other);
	}

}
