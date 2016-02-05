package pt.ist.socialsoftware.blendedworkflow.domain;

public abstract class DefProductCondition extends DefProductCondition_Base {

	@Override
	public void delete() {
		setTaskWithPostCondition(null);

		setActivationConditionGoal(null);
		setSuccessConditionGoal(null);

		super.delete();
	}

	public abstract Product getTargetOfPath();

	public abstract String getPath();

}
