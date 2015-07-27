package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class MULCondition extends MULCondition_Base {

    public static MULCondition getMulCondition(BWRelation relation,
            String rolename) {
        for (MULCondition mulCondition : relation.getMulConditionSet()) {
            if (mulCondition.getRolename().equals(rolename))
                return mulCondition;
        }

        if (relation.getMulConditionSet().size() >= 2)
            throw new BWException(BWErrorType.INVALID_ROLE_NAME, rolename);

        if (relation.getRoleNameOne().equals(rolename))
            return new MULCondition(relation, 1);

        if (relation.getRoleNameTwo().equals(rolename))
            return new MULCondition(relation, 2);

        assert(false);
        return null;
    }

    private MULCondition(BWRelation relation, int side) {
        setBwRelation(relation);
        setSide(side);
    }

    public String getRolename() {
        if (getSide() == 1)
            return getBwRelation().getRoleNameOne();
        else
            return getBwRelation().getRoleNameTwo();
    }

    public Cardinality getCardinality() {
        if (getSide() == 1)
            return getBwRelation().getCardinalityOne();
        else
            return getBwRelation().getCardinalityTwo();
    }

    @Override
    public void delete() {
        setBwRelation(null);

        deleteDomainObject();
    }

    @Override
    public String getSubPath() {
        return "MUL("
                + getBwRelation().getSourceOfRolename(getRolename()).getName()
                + "."
                + getBwRelation().getCardinalityByRolename(getRolename()).name()
                + ")";
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
    public Set<BWEntity> getEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<BWAttribute, String> getcompareConditionValues() {
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

}
