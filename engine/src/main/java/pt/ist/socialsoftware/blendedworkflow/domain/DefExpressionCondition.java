package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DefExpressionCondition extends DefExpressionCondition_Base {

    public DefExpressionCondition(DataModel dataModel, String path) {
        setPath(path);
        setProduct(dataModel.getTargetOfPath(path));
    }

    @Override
    Condition cloneCondition(GoalModelInstance goalModelInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    Condition cloneCondition(TaskModelInstance taskModelInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        // TODO Auto-generated method stub

    }

    @Override
    void assignAttributeInstances(TaskWorkItem taskWorkItem,
            ConditionType conditionType) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<Entity> getEntities() {
        Set<Entity> entities = new HashSet<Entity>();
        if (getProduct() instanceof Entity) {
            entities.add((Entity) getProduct());
        }

        return entities;
    }

    @Override
    public Set<AttributeBasic> getAttributeBasicSet() {
        Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
        if (getProduct() instanceof AttributeBasic) {
            attributes.add((AttributeBasic) getProduct());
        } else if (getProduct() instanceof AttributeGroup) {
            attributes.addAll(
                    ((AttributeGroup) getProduct()).getAttributeBasicSet());
        }

        return attributes;
    }

    @Override
    public HashMap<AttributeBasic, String> getcompareConditionValues() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getRdrUndefinedCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getRdrSkippedCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getRdrTrueCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getRdrFalseCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean existExistEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean existCompareAttributeToValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean existTrue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TripleStateBool evaluate(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem,
            ConditionType conditionType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
            GoalWorkItem goalWorkItem, ConditionType conditionType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSubPath() {
        return "DEF(" + getPath() + ")";
    }

    @Override
    public void delete() {
        setProduct(null);
        super.delete();
    }

}
