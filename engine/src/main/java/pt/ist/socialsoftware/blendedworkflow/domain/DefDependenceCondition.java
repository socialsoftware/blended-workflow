package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefDependenceConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DefDependenceCondition extends DefDependenceCondition_Base {

    public static DefDependenceCondition getDefDependence(Specification spec,
            String path) {
        Dependence dependence = spec.getDataModel().getDependenceSet().stream()
                .filter(d -> d.getPath().equals(path)).findFirst()
                .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                        "DEF(" + path + ")"));

        return DefDependenceCondition.getDefDependence(dependence);
    }

    public static DefDependenceCondition getDefDependence(
            Dependence dependence) {
        DefDependenceCondition defDependenceCondition = dependence
                .getDefDependenceCondition();
        if (defDependenceCondition == null)
            defDependenceCondition = new DefDependenceCondition(dependence);

        return defDependenceCondition;
    }

    private DefDependenceCondition(Dependence dependence) {
        setDependence(dependence);
        setPath(dependence.getPath());
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
        Set<Entity> entitySet = new HashSet<Entity>();
        if (getTarget() instanceof Entity)
            entitySet.add((Entity) getTarget());

        return entitySet;
    }

    @Override
    public Set<AttributeBasic> getAttributeBasicSet() {
        Set<AttributeBasic> attributeSet = new HashSet<AttributeBasic>();
        if (getTarget() instanceof AttributeBasic)
            attributeSet.add((AttributeBasic) getTarget());
        else if (getTarget() instanceof AttributeGroup)
            attributeSet.addAll(
                    ((AttributeGroup) getTarget()).getAttributeBasicSet());

        return attributeSet;
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
        return (getTarget() instanceof Entity);
    }

    @Override
    public Boolean existCompareAttributeToValue() {
        return false;
    }

    @Override
    public Boolean existTrue() {
        return false;
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
        return "DEF(" + getDependence().getPath() + ")";
    }

    public Product getTarget() {
        return getDependence().getTarget();
    }

    @Override
    public void delete() {
        setDependence(null);
        super.delete();
    }

    public DefDependenceConditionDTO getDTO() {
        DefDependenceConditionDTO dto = new DefDependenceConditionDTO();
        dto.setSpecId(
                getDependence().getDataModel().getSpecification().getSpecId());
        dto.setPath(getPath());

        return dto;
    }

    @Override
    public ExpressionDTO getDTO(String specId) {
        return new ExpressionDTO(specId, BooleanOperator.ATT_DEF, getPath());
    }
}
