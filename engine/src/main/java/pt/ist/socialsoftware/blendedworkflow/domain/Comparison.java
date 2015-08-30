package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class Comparison extends Comparison_Base {

    private static Logger log = LoggerFactory.getLogger(Comparison.class);

    public static enum ComparisonOperator {
        EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL
    }

    public Comparison(BWExpression leftExpression, BWExpression rightExpression,
            ComparisonOperator comparator) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
        setComparator(comparator);
        checkConsistency();
    }

    private void checkConsistency() {
        log.debug("type:{}, left expression:{}, right expression:{}",
                getComparator(), getLeftExpression(), getRightExpression());
        if ((getLeftExpression() == null) || (getRightExpression() == null))
            throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION,
                    getSubPath());

        if (!getLeftExpression().getType()
                .equals(getRightExpression().getType()))
            throw new BWException(BWErrorType.INCONSISTENT_TYPE, getSubPath());
    }

    public Comparison(BWAttribute attribute, String operator, String value) {
        setAttributeOfComparison(attribute);
        setOperator(operator);
        setValue(value);
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        DataModelInstance dataModelInstance = goalModelInstance.getBwInstance()
                .getDataModelInstance();
        BWEntity entity = dataModelInstance
                .getEntity(getAttributeOfComparison().getEntity().getName()).get();
        BWAttribute attribute = entity.getAttribute(getAttributeOfComparison().getName())
                .orElse(null);
        return new Comparison(attribute, getOperator(), getValue());
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        DataModelInstance dataModelInstance = taskModelInstance.getBwInstance()
                .getDataModelInstance();
        BWEntity entity = dataModelInstance
                .getEntity(getAttributeOfComparison().getEntity().getName()).get();
        BWAttribute attribute = entity.getAttribute(getAttributeOfComparison().getName())
                .orElse(null);
        return new Comparison(attribute, getOperator(), getValue());
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        getAttributeOfComparison().getEntity().assignAttributeInstances(goalWorkItem,
                getAttributeOfComparison(), conditionType);
    }

    @Override
    public void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
        getAttributeOfComparison().getEntity().assignAttributeInstances(taskWorkItem,
                getAttributeOfComparison(), conditionType);
    }

    @Override
    public Set<BWEntity> getEntities() {
        return new HashSet<BWEntity>();
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        Set<BWAttribute> attribute = new HashSet<BWAttribute>();
        attribute.add(getAttributeOfComparison());
        return attribute;
    }

    @Override
    public HashMap<BWAttribute, String> getcompareConditionValues() {
        HashMap<BWAttribute, String> result = new HashMap<BWAttribute, String>();
        result.put(getAttributeOfComparison(), getValue());
        return result;
    }

    @Override
    public String getRdrUndefinedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
        String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ",
                "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.UNDEFINED + ")";
        return condition;
    }

    @Override
    public String getRdrSkippedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
        String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ",
                "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.SKIPPED + ")";
        return condition;
    }

    @Override
    public String getRdrTrueCondition() {
        String condition = "(";
        String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
        String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ",
                "");
        String value = getValue();
        if (getValue().equals("$TODAY$")) {
            value = "" + "$TODAY$".hashCode();
        }

        condition += entityName + "_" + attributeName + " " + getOperator()
                + " " + value + ")";
        return condition;
    }

    @Override
    public String getRdrFalseCondition() {
        return "(!" + getRdrTrueCondition() + ")";
    }

    @Override
    public String toString() {
        return "compareAttributeTo(" + getAttributeOfComparison().getEntity().getName()
                + "." + getAttributeOfComparison().getName() + " " + getOperator() + " "
                + getValue() + ")";
    }

    @Override
    public Boolean existExistEntity() {
        return false;
    }

    @Override
    public Boolean existTrue() {
        return false;
    }

    /******************************
     * Evaluate
     ******************************/
    @Override
    public TripleStateBool evaluate(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        // TODO:In progress...

        // Input/Output Arguments
        Set<WorkItemArgument> arguments = null;
        if (conditionType.equals(ConditionType.ACTIVATE_CONDITION)) {
            arguments = goalWorkItem.getInputWorkItemArgumentsSet();
        } else if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
            arguments = goalWorkItem.getOutputWorkItemArgumentsSet();
        }
        if (arguments != null) {
            for (WorkItemArgument workItemArgument : arguments) {
                BWAttribute workItemAttribute = workItemArgument
                        .getAttributeInstance().getAttribute();
                BWAttribute conditionAttribute = getAttributeOfComparison();
                if (workItemAttribute == conditionAttribute) {
                    if (workItemArgument.getState()
                            .equals(DataState.UNDEFINED)) {
                        return TripleStateBool.FALSE;
                    } else if (workItemArgument.getState()
                            .equals(DataState.SKIPPED)) {
                        return TripleStateBool.SKIPPED;
                    } else {
                        if (evaluateComparation(workItemArgument.getValue())) {
                            return TripleStateBool.TRUE;
                        } else {
                            return TripleStateBool.FALSE;
                        }
                    }
                }
            }
        }
        return TripleStateBool.FALSE;
    }

    // Legacy
    @Override
    public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        Set<WorkItemArgument> arguments = null;
        if (conditionType.equals(ConditionType.ACTIVATE_CONDITION)) {
            arguments = goalWorkItem.getInputWorkItemArgumentsSet();
        } else if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
            arguments = goalWorkItem.getOutputWorkItemArgumentsSet();
        }
        if (arguments != null) {
            for (WorkItemArgument workItemArgument : arguments) {
                BWAttribute workItemAttribute = workItemArgument
                        .getAttributeInstance().getAttribute();
                BWAttribute conditionAttribute = getAttributeOfComparison();
                if (workItemAttribute == conditionAttribute) {
                    if (workItemArgument.getState()
                            .equals(DataState.UNDEFINED)) {
                        return TripleStateBool.FALSE;
                    } else if (workItemArgument.getState()
                            .equals(DataState.SKIPPED)) {
                        return TripleStateBool.SKIPPED;
                    } else {
                        if (evaluateComparation(workItemArgument.getValue())) {
                            return TripleStateBool.TRUE;
                        } else {
                            return TripleStateBool.FALSE;
                        }
                    }
                }
            }
        }
        return TripleStateBool.FALSE;
    }

    @Override
    public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        if (entityInstance == null) {
            return evaluateWithWorkItem(goalWorkItem, conditionType);
        }

        else {
            for (AttributeInstance attributeInstance : entityInstance
                    .getAttributeInstancesSet()) {
                if (attributeInstance.getAttribute().equals(getAttributeOfComparison())) {
                    DataState state = getWorkItemState(attributeInstance,
                            goalWorkItem, conditionType);
                    if (state == null) {
                        state = attributeInstance.getState();
                    }

                    if (state.equals(DataState.UNDEFINED)) {
                        return TripleStateBool.FALSE;
                    } else if (state.equals(DataState.SKIPPED)) {
                        return TripleStateBool.SKIPPED;
                    } else {
                        String value = getWorkItemValue(attributeInstance,
                                goalWorkItem, conditionType);
                        if (value == null) {
                            value = attributeInstance.getValue();
                        }

                        if (evaluateComparation(value)) {
                            return TripleStateBool.TRUE;
                        } else {
                            return TripleStateBool.FALSE;
                        }
                    }
                }
            }
            return TripleStateBool.FALSE;
        }
    }

    private String getWorkItemValue(AttributeInstance attributeInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        // List<WorkItemArgument> arguments = null;
        // if (conditionType.equals(ConditionType.ACTIVATE)) {
        // arguments = goalWorkItem.getInputWorkItemArguments();
        // } else if (conditionType.equals(ConditionType.SUCESS)) {
        // arguments = goalWorkItem.getOutputWorkItemArguments();
        // }
        // for (WorkItemArgument workItemArgument : arguments) {
        if (goalWorkItem != null) {
            for (WorkItemArgument workItemArgument : goalWorkItem
                    .getOutputWorkItemArgumentsSet()) {
                if (workItemArgument.getAttributeInstance()
                        .equals(attributeInstance)) {
                    return workItemArgument.getValue();
                }
            }
        }
        return null;
    }

    private DataState getWorkItemState(AttributeInstance attributeInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        // List<WorkItemArgument> arguments = null;
        // if (conditionType.equals(ConditionType.ACTIVATE)) {
        // arguments = goalWorkItem.getInputWorkItemArguments();
        // } else if (conditionType.equals(ConditionType.SUCESS)) {
        // arguments = goalWorkItem.getOutputWorkItemArguments();
        // }
        // for (WorkItemArgument workItemArgument : arguments) {
        if (goalWorkItem != null) {
            for (WorkItemArgument workItemArgument : goalWorkItem
                    .getOutputWorkItemArgumentsSet()) {
                if (workItemArgument.getAttributeInstance()
                        .equals(attributeInstance)) {
                    return workItemArgument.getState();
                }
            }
        }
        return null;
    }

    private boolean evaluateComparation(String evaluateValue) {
        String conditionValueString = getValue();

        if (conditionValueString.equals("$TODAY$")) {
            conditionValueString = BlendedWorkflow.getInstance().getToday();
        }

        if (((getAttributeOfComparison().getType().equals(AttributeType.STRING)
                || (getAttributeOfComparison().getType().equals(AttributeType.BOOLEAN))))) {
            if (getOperator().equals("=")) {
                if (evaluateValue.equals(conditionValueString)) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals("!=")) {
                if (!evaluateValue.equals(conditionValueString)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                log.error("Invalid operator for String or Boolean type.");
                return false;
            }
        } else if (getAttributeOfComparison().getType().equals(AttributeType.NUMBER)) {
            Integer value = Integer.parseInt(evaluateValue);
            Integer conditionValue = Integer.parseInt(getValue());
            if (getOperator().equals("<")) {
                if (value < conditionValue) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals("<=")) {
                if (value <= conditionValue) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals("==")) {
                if (value.equals(conditionValue)) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals(">")) {
                if (value > conditionValue) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals(">=")) {
                if (value >= conditionValue) {
                    return true;
                } else {
                    return false;
                }
            } else if (getOperator().equals("!=")) {
                if (value.intValue() != conditionValue.intValue()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                log.error("Invalid operator for Number type.");
                return false;
            }
        } else {
            log.error("Invalid operator for String or Boolean type.");
            return false;
        }
    }

    @Override
    public Boolean existCompareAttributeToValue() {
        return true;
    }

    @Override
    public void delete() {
        setAttributeOfComparison(null);
        getLeftExpression().delete();
        getRightExpression().delete();
        super.delete();

    }

    @Override
    public String getSubPath() {
        String left = getLeftExpression() != null
                ? getLeftExpression().getSubPath() : "NULL";
        String right = getRightExpression() != null
                ? getRightExpression().getSubPath() : "NULL";
        return getComparator() + "(" + left + "," + right + ")";
    }

}
