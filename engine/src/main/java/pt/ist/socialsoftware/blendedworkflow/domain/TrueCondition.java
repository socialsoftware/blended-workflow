package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class TrueCondition extends TrueCondition_Base {

    public TrueCondition() {
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        return new TrueCondition();
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        return new TrueCondition();
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
    }

    @Override
    void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
    }

    @Override
    public Set<BWEntity> getEntities() {
        return new HashSet<BWEntity>();
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        return new HashSet<BWAttribute>();
    }

    @Override
    public HashMap<BWAttribute, String> getcompareConditionValues() {
        return new HashMap<BWAttribute, String>();
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
        return "True";
    }

    @Override
    public Boolean existExistEntity() {
        return false;
    }

    @Override
    public Boolean existCompareAttributeToValue() {
        return false;
    }

    /******************************
     * Evaluate
     ******************************/
    @Override
    public TripleStateBool evaluate(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        return TripleStateBool.TRUE;
    }

    @Override
    public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        return TripleStateBool.TRUE;
    }

    @Override
    public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        return TripleStateBool.TRUE;
    }

    @Override
    public Boolean existTrue() {
        return true;
    }

    @Override
    public String getExpressionPath() {
        return super.getExpressionPath() + "." + "true";
    }

}
