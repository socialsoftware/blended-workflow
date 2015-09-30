package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DefAttributeCondition extends DefAttributeCondition_Base {

    public static DefAttributeCondition getDefAttribute(Attribute attribute) {
        DefAttributeCondition defAttributeCondition = attribute
                .getDefAttributeCondition();
        if (defAttributeCondition == null)
            defAttributeCondition = new DefAttributeCondition(attribute);
        return defAttributeCondition;
    }

    private DefAttributeCondition(Attribute attribute) {
        setConditionModel(attribute.getEntity().getDataModel()
                .getSpecification().getConditionModel());
        setAttributeOfDef(attribute);
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        DataModelInstance dataModelInstance = goalModelInstance.getBwInstance()
                .getDataModelInstance();
        Entity entity = dataModelInstance
                .getEntity(getAttributeOfDef().getEntity().getName()).get();
        AttributeBasic attribute = entity
                .getAttribute(getAttributeOfDef().getName()).orElse(null);
        return new DefAttributeCondition(attribute);
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        DataModelInstance dataModelInstance = taskModelInstance.getBwInstance()
                .getDataModelInstance();
        Entity entity = dataModelInstance
                .getEntity(getAttributeOfDef().getEntity().getName()).get();
        AttributeBasic attribute = entity
                .getAttribute(getAttributeOfDef().getName()).orElse(null);
        return new DefAttributeCondition(attribute);
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        getAttributeOfDef().getEntity().assignAttributeInstances(goalWorkItem,
                (AttributeBasic) getAttributeOfDef(), conditionType);
    }

    @Override
    public void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
        getAttributeOfDef().getEntity().assignAttributeInstances(taskWorkItem,
                (AttributeBasic) getAttributeOfDef(), conditionType);
    }

    @Override
    public Set<Entity> getEntities() {
        return new HashSet<Entity>();
    }

    @Override
    public Set<AttributeBasic> getAttributeBasicSet() {
        return getAttributeOfDef().getAttributeBasicSet();
    }

    @Override
    public HashMap<AttributeBasic, String> getcompareConditionValues() {
        return new HashMap<AttributeBasic, String>();
    }

    @Override
    public String getRdrUndefinedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ",
                "");
        String entityName = getAttributeOfDef().getEntity().getName()
                .replaceAll(" ", "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.UNDEFINED + ")";
        return condition;
    }

    @Override
    public String getRdrSkippedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ",
                "");
        String entityName = getAttributeOfDef().getEntity().getName()
                .replaceAll(" ", "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.SKIPPED + ")";
        return condition;
    }

    @Override
    public String getRdrTrueCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ",
                "");
        String entityName = getAttributeOfDef().getEntity().getName()
                .replaceAll(" ", "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.DEFINED + ")";
        return condition;
    }

    @Override
    public String getRdrFalseCondition() {
        return "(FALSE_NODE = FALSE)";
    }

    @Override
    public String toString() {
        if (getAttributeOfDef() != null
                && getAttributeOfDef().getEntity() != null
                && getAttributeOfDef().getEntity().getName() != null)
            return "existsAttribute("
                    + getAttributeOfDef().getEntity().getName() + "."
                    + getAttributeOfDef().getName() + ")";
        return "DEFAttributeCondition: attribute or entity with empty value";
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
        // TODO:Refactor
        return TripleStateBool.FALSE;
    }

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
                AttributeBasic workItemAttribute = workItemArgument
                        .getAttributeInstance().getAttribute();
                Attribute conditionAttribute = getAttributeOfDef();
                if (workItemAttribute == conditionAttribute) {
                    if (workItemArgument.getState().equals(DataState.SKIPPED)) {
                        return TripleStateBool.SKIPPED;
                    } else if (workItemArgument.getState()
                            .equals(DataState.UNDEFINED)) {
                        return TripleStateBool.FALSE;
                    }
                }
            }
        }
        return TripleStateBool.TRUE;
    }

    @Override
    public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        for (AttributeInstance attributeInstance : entityInstance
                .getAttributeInstancesSet()) {
            AttributeBasic attribute = attributeInstance.getAttribute();
            Attribute conditionAttribute = getAttributeOfDef();

            if (attribute == conditionAttribute) {
                DataState state = getWorkItemState(attributeInstance,
                        goalWorkItem, conditionType);
                if (state == null) {
                    state = attributeInstance.getState();
                }

                if (state.equals(DataState.SKIPPED)) {
                    return TripleStateBool.SKIPPED;
                } else if (attributeInstance.getState()
                        .equals(DataState.UNDEFINED)) {
                    return TripleStateBool.FALSE;
                }
            }
        }
        return TripleStateBool.TRUE;
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

    @Override
    public Boolean existCompareAttributeToValue() {
        return false;
    }

    @Override
    public void delete() {
        setConditionModel(null);
        setAttributeOfDef(null);
        super.delete();
    }

    public AttributeType getType() {
        return AttributeType.BOOLEAN;
    }

    @Override
    public String getSubPath() {
        return "DEF(" + getAttributeOfDef().getName() + ")";
    }

    public DefAttributeConditionDTO getDTO() {
        DefAttributeConditionDTO defConditionDTO = new DefAttributeConditionDTO();
        defConditionDTO
                .setSpecId(getConditionModel().getSpecification().getSpecId());
        defConditionDTO.setAttributeExtId(getAttributeOfDef().getExternalId());
        defConditionDTO.setMandatory(getAttributeOfDef().getIsMandatory());

        return defConditionDTO;
    }

}