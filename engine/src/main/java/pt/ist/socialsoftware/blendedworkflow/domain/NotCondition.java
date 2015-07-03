package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class NotCondition extends NotCondition_Base {

    public NotCondition(Condition condition) {
        setCondition(condition);
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        return new NotCondition(
                getCondition().cloneCondition(goalModelInstance));
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        return new NotCondition(
                getCondition().cloneCondition(taskModelInstance));
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        getCondition().assignAttributeInstances(goalWorkItem, conditionType);
    }

    @Override
    void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
        getCondition().assignAttributeInstances(taskWorkItem, conditionType);
    }

    @Override
    public Set<Entity> getEntities() {
        return null;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return null;
    }

    @Override
    public HashMap<Attribute, String> getcompareConditionValues() {
        return new HashMap<Attribute, String>();
    }

    @Override
    public String getRdrUndefinedCondition() {
        return "True";
    }

    @Override
    public String getRdrSkippedCondition() {
        return "True";
    }

    @Override
    public String getRdrTrueCondition() {
        return "True";
    }

    @Override
    public String getRdrFalseCondition() {
        return "True";
    }

    @Override
    public String toString() {
        return "!(" + getCondition().toString() + ")";
    }

    @Override
    public Boolean existExistEntity() {
        return false;
    }

    /******************************
     * Evaluate
     ******************************/
    @Override
    public TripleStateBool evaluate(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        return getCondition().evaluate(goalWorkItem, conditionType).NOT();
    }

    @Override
    public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        return getCondition().evaluateWithWorkItem(goalWorkItem, conditionType)
                .NOT();
    }

    @Override
    public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        return getCondition().evaluateWithWorkItem(goalWorkItem, conditionType)
                .NOT();
    }

    @Override
    public Boolean existCompareAttributeToValue() {
        return false;
    }

    @Override
    public Boolean existTrue() {
        return false;
    }

}
