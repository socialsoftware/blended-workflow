package pt.ist.socialsoftware.blendedworkflow.domain;

public abstract class DefProductCondition extends DefProductCondition_Base {

	@Override
	public void delete() {
		getWorkItemSet().stream().forEach(wi -> wi.delete());

		setActivityWithPostCondition(null);

		setSuccessConditionGoal(null);

		getPath().delete();

		super.delete();
	}

	public Product getTargetOfPath() {
		return getPath().getTarget();
	}

	public abstract Entity getSourceOfPath();

}
