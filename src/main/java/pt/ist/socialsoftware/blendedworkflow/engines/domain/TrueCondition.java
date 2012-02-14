package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class TrueCondition extends TrueCondition_Base {

	public TrueCondition() {
		super();
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new TrueCondition();
	}

}