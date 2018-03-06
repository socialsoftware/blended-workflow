package pt.ist.socialsoftware.blendedworkflow.core.domain;

public abstract class DefProductCondition extends DefProductCondition_Base {

	@Override
	public void delete() {
		getPostWorkItemArgumentSet().stream().forEach(wi -> wi.delete());

		setActivityWithPostCondition(null);

		setSuccessConditionGoal(null);

		getPath().delete();

		super.delete();
	}

	public Product getTargetOfPath() {
		return getPath().getTarget();
	}

	public abstract Entity getSourceOfPath();

	public boolean isAttribute() {
		return getSourceOfPath() != getTargetOfPath();
	}

	public boolean isEntity() {
		return getSourceOfPath() == getTargetOfPath();
	}

}
