package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DEFAttributeCondition extends DEFAttributeCondition_Base {

    public static DEFAttributeCondition getDEFAttributeCondition(
            BWAttribute attribute) {
        DEFAttributeCondition defAttributeCondition = attribute
                .getDefAttributeCondition();
        if (defAttributeCondition == null)
            defAttributeCondition = new DEFAttributeCondition(attribute);
        return defAttributeCondition;
    }

    public static DEFAttributeCondition getDEFAttributeCondition(
            BWAttributeGroup attributeGroup) {
        DEFAttributeCondition defAttributeCondition = attributeGroup
                .getDefAttributeCondition();
        if (defAttributeCondition == null)
            defAttributeCondition = new DEFAttributeCondition(attributeGroup);
        return defAttributeCondition;
    }

    private DEFAttributeCondition(BWAttribute attribute) {
        setConditionModel(attribute.getEntity().getDataModel()
                .getSpecification().getConditionModel());
        setAttributeOfDef(attribute);
    }

    private DEFAttributeCondition(BWAttributeGroup attributeGroup) {
        setConditionModel(attributeGroup.getEntity().getDataModel()
                .getSpecification().getConditionModel());
        setAttributeGroup(attributeGroup);
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        DataModelInstance dataModelInstance = goalModelInstance.getBwInstance()
                .getDataModelInstance();
        BWEntity entity = dataModelInstance
                .getEntity(getAttributeOfDef().getEntity().getName()).get();
        BWAttribute attribute = entity.getAttribute(getAttributeOfDef().getName())
                .orElse(null);
        return new DEFAttributeCondition(attribute);
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        DataModelInstance dataModelInstance = taskModelInstance.getBwInstance()
                .getDataModelInstance();
        BWEntity entity = dataModelInstance
                .getEntity(getAttributeOfDef().getEntity().getName()).get();
        BWAttribute attribute = entity.getAttribute(getAttributeOfDef().getName())
                .orElse(null);
        return new DEFAttributeCondition(attribute);
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        getAttributeOfDef().getEntity().assignAttributeInstances(goalWorkItem,
                getAttributeOfDef(), conditionType);
    }

    @Override
    public void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
        getAttributeOfDef().getEntity().assignAttributeInstances(taskWorkItem,
                getAttributeOfDef(), conditionType);
    }

    @Override
    public Set<BWEntity> getEntities() {
        return new HashSet<BWEntity>();
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        Set<BWAttribute> attribute = new HashSet<BWAttribute>();
        attribute.add(getAttributeOfDef());
        return attribute;
    }

    @Override
    public HashMap<BWAttribute, String> getcompareConditionValues() {
        return new HashMap<BWAttribute, String>();
    }

    @Override
    public String getRdrUndefinedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
        String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ",
                "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.UNDEFINED + ")";
        return condition;
    }

    @Override
    public String getRdrSkippedCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
        String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ",
                "");

        condition += entityName + "_" + attributeName + "_State = "
                + DataState.SKIPPED + ")";
        return condition;
    }

    @Override
    public String getRdrTrueCondition() {
        String condition = "(";
        String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
        String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ",
                "");

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
        if (getAttributeOfDef() != null && getAttributeOfDef().getEntity() != null
                && getAttributeOfDef().getEntity().getName() != null)
            return "existsAttribute(" + getAttributeOfDef().getEntity().getName()
                    + "." + getAttributeOfDef().getName() + ")";
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
                BWAttribute workItemAttribute = workItemArgument
                        .getAttributeInstance().getAttribute();
                BWAttribute conditionAttribute = getAttributeOfDef();
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
            BWAttribute attribute = attributeInstance.getAttribute();
            BWAttribute conditionAttribute = getAttributeOfDef();

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
        setAttributeGroup(null);
        super.delete();
    }

    public AttributeType getType() {
        return AttributeType.BOOLEAN;
    }

    @Override
    public String getSubPath() {
        String subPath = getAttributeOfDef() != null ? getAttributeOfDef().getName()
                : getAttributeGroup().getName();
        return "DEF(" + subPath + ")";
    }

    public DefAttributeConditionDTO getDTO() {
        DefAttributeConditionDTO defConditionDTO = new DefAttributeConditionDTO();
        defConditionDTO
                .setSpecId(getConditionModel().getSpecification().getSpecId());
        if (getAttributeOfDef() != null) {
            defConditionDTO.setAttributeExtId(getAttributeOfDef().getExternalId());
            defConditionDTO.setMandatory(getAttributeOfDef().getIsMandatory());
        }
        if (getAttributeGroup() != null) {
            defConditionDTO.setAttributeGroupExtId(
                    getAttributeGroup().getExternalId());
            defConditionDTO.setMandatory(getAttributeGroup().getIsMandatory());

        }

        return defConditionDTO;
    }

}
