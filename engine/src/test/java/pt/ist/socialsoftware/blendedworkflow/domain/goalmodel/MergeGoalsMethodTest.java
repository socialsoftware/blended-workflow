package pt.ist.socialsoftware.blendedworkflow.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MergeGoalsMethodTest extends TeardownRollbackTest {
    private static final String CHILD_GOAL_TWO_ONE = "childGoalTwoOne";
    private static final String TOP_GOAL = "topGoal";
    private static final String RULE_CONDITION = "rule";
    private static final String ROLENAME_ONE = "theOne";
    private static final String ROLENAME_TWO = "theTwo";
    private static final String CHILD_GOAL_TWO = "childGoalTwo";
    private static final String CHILD_GOAL_ONE = "childGoalOne";

    BWSpecification spec;
    BWEntity entityOne;
    BWEntity entityTwo;
    BWAttribute attributeOne;
    BWAttribute attributeTwo;
    BWAttribute attributeThree;
    BWRelation relation;
    Goal topGoal;
    Goal childGoalOne;
    Goal childGoalTwo;
    Goal childGoalTwoOne;

    @Override
    public void populate4Test() throws BWException {
        spec = new BWSpecification("SpecId", "My spec", "author", "description",
                "version", "UID");

        entityOne = new BWEntity(spec.getDataModel(), "Entity one name", false);
        attributeOne = new BWAttribute(spec.getDataModel(), entityOne, null,
                "att1", AttributeType.BOOLEAN, true, false, false);
        attributeTwo = new BWAttribute(spec.getDataModel(), entityOne, null,
                "att2", AttributeType.NUMBER, true, false, false);

        entityTwo = new BWEntity(spec.getDataModel(), "Entity two name", false);
        attributeThree = new BWAttribute(spec.getDataModel(), entityTwo, null,
                "att3", AttributeType.BOOLEAN, true, false, false);

        relation = new BWRelation(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);

        topGoal = new Goal(spec.getGoalModel(), TOP_GOAL);
        childGoalOne = new Goal(spec.getGoalModel(), CHILD_GOAL_ONE);
        childGoalTwo = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO);
        childGoalTwoOne = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO_ONE);
        topGoal.addSubGoal(childGoalOne);
        topGoal.addSubGoal(childGoalTwo);
        childGoalTwo.addSubGoal(childGoalTwoOne);

        topGoal.addSuccessCondition(DEFEntityCondition.getDEFEntity(entityOne));
        childGoalOne.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeOne));
        childGoalTwo.addSuccessCondition(
                DEFEntityCondition.getDEFEntity(entityTwo));
        childGoalTwo.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));

        childGoalOne.addActivationCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));

        topGoal.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_TWO));
        childGoalTwo.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_ONE));

        BWRule rule = new BWRule(spec.getDataModel(), RULE_CONDITION, null);

        childGoalOne.addAttributeInvariantCondition(rule);
        childGoalTwo.addAttributeInvariantCondition(rule);
    }

    @Test
    public void siblingsMerge() {
        Goal merged = spec.getGoalModel().mergeGoals(
                CHILD_GOAL_ONE + CHILD_GOAL_TWO, childGoalOne, childGoalTwo);

        assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_ONE));
        assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_TWO));
        assertEquals(topGoal, merged.getParentGoal());
        assertEquals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, merged.getName());
        assertEquals(1, merged.getSubGoalSet().size());
        assertTrue(merged.getSubGoalSet().contains(childGoalTwoOne));
        assertEquals(3, merged.getSuccessConditionSet().size());
        assertTrue(merged.getSuccessConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeOne)));
        assertTrue(merged.getSuccessConditionSet()
                .contains(DEFEntityCondition.getDEFEntity(entityTwo)));
        assertTrue(merged.getSuccessConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(0, merged.getActivationConditionSet().size());
        assertEquals(1, merged.getEntityInvariantConditionSet().size());
        assertTrue(merged.getEntityInvariantConditionSet().contains(
                MULCondition.getMulCondition(relation, ROLENAME_ONE)));
        assertEquals(1, merged.getAttributeInvariantConditionSet().size());
        assertTrue(merged.getAttributeInvariantConditionSet()
                .contains(spec.getDataModel().getRule(RULE_CONDITION)));
    }

    @Test
    public void parentChildMerge() {
        Goal result = spec.getGoalModel().mergeGoals(TOP_GOAL, childGoalTwo,
                topGoal);

        assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_TWO));
        assertTrue(spec.getGoalModel().existsGoal(TOP_GOAL));
        assertEquals(topGoal, result);

        assertEquals(2, result.getSubGoalSet().size());
        assertTrue(result.getSubGoalSet().contains(childGoalOne));
        assertTrue(result.getSubGoalSet().contains(childGoalTwoOne));
        assertEquals(3, result.getSuccessConditionSet().size());
        assertTrue(result.getSuccessConditionSet()
                .contains(DEFEntityCondition.getDEFEntity(entityOne)));
        assertTrue(result.getSuccessConditionSet()
                .contains(DEFEntityCondition.getDEFEntity(entityTwo)));
        assertTrue(result.getSuccessConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(0, result.getActivationConditionSet().size());
        assertEquals(2, result.getEntityInvariantConditionSet().size());
        assertTrue(result.getEntityInvariantConditionSet().contains(
                MULCondition.getMulCondition(relation, ROLENAME_ONE)));
        assertTrue(result.getEntityInvariantConditionSet().contains(
                MULCondition.getMulCondition(relation, ROLENAME_TWO)));
        assertEquals(1, result.getAttributeInvariantConditionSet().size());
        assertTrue(result.getAttributeInvariantConditionSet()
                .contains(spec.getDataModel().getRule(RULE_CONDITION)));

    }

    @Test
    public void siblingsMergeNameAlreadyExits() {
        new Goal(spec.getGoalModel(), CHILD_GOAL_ONE + CHILD_GOAL_TWO);

        try {
            spec.getGoalModel().mergeGoals(CHILD_GOAL_ONE + CHILD_GOAL_TWO,
                    childGoalOne, childGoalTwo);
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_GOAL_NAME, bwe.getError());
            assertEquals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, bwe.getMessage());
        }
    }

    @Test
    public void nonSiblingsAndNonParentChildGoals() {
        try {
            spec.getGoalModel().mergeGoals("Name", childGoalOne,
                    childGoalTwoOne);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.UNMERGEABLE_GOALS, bwe.getError());
            assertEquals(null, bwe.getMessage());
        }
    }

    @Test
    public void parentChildConflict() {
        try {
            spec.getGoalModel().mergeGoals("Name", topGoal, childGoalOne);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.UNMERGEABLE_GOALS, bwe.getError());
        }

    }

}
