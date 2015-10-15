package pt.ist.socialsoftware.blendedworkflow.domain;

public abstract class DefProductCondition extends DefProductCondition_Base {

    @Override
    public void delete() {
        getTaskWithPreConditionSet().stream()
                .forEach(t -> t.removePreCondition(this));
        setTaskWithPostCondition(null);

        setActivationConditionGoal(null);
        setSuccessConditionGoal(null);

        super.delete();
    }

}
