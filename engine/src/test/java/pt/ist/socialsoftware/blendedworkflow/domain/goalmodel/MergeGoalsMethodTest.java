package pt.ist.socialsoftware.blendedworkflow.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
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

    Specification spec;
    Entity entityOne;
    Entity entityTwo;
    AttributeBasic attributeOne;
    AttributeBasic attributeTwo;
    AttributeBasic attributeThree;
    RelationBW relation;
    Goal topGoal;
    Goal childGoalOne;
    Goal childGoalTwo;
    Goal childGoalTwoOne;

    @Override
    public void populate4Test() throws BWException {
        spec = new Specification("SpecId", "My spec", "author", "description",
                "version", "UID");

        entityOne = new Entity(spec.getDataModel(), "Entity one name", false);
        attributeOne = new AttributeBasic(spec.getDataModel(), entityOne, null,
                "att1", AttributeType.BOOLEAN, true, false, false);
        attributeTwo = new AttributeBasic(spec.getDataModel(), entityOne, null,
                "att2", AttributeType.NUMBER, true, false, false);

        entityTwo = new Entity(spec.getDataModel(), "Entity two name", false);
        attributeThree = new AttributeBasic(spec.getDataModel(), entityTwo, null,
                "att3", AttributeType.BOOLEAN, true, false, false);

        relation = new RelationBW(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);

        topGoal = new Goal(spec.getGoalModel(), TOP_GOAL);
        childGoalOne = new Goal(spec.getGoalModel(), CHILD_GOAL_ONE);
        childGoalTwo = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO);
        childGoalTwoOne = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO_ONE);
        topGoal.addSubGoal(childGoalOne);
        topGoal.addSubGoal(childGoalTwo);
        childGoalTwo.addSubGoal(childGoalTwoOne);

        topGoal.addSuccessCondition(DefEntityCondition.getDefEntity(entityOne));
        childGoalOne.addSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeOne));
        childGoalTwo.addSuccessCondition(
                DefEntityCondition.getDefEntity(entityTwo));
        childGoalTwo.addSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));

        childGoalOne.addActivationCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));

        topGoal.addEntityInvariantCondition(
                MulCondition.getMulCondition(relation, ROLENAME_TWO));
        childGoalTwo.addEntityInvariantCondition(
                MulCondition.getMulCondition(relation, ROLENAME_ONE));

        Rule rule = new Rule(spec.getDataModel(), RULE_CONDITION, null);

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
                .contains(DefAttributeCondition.getDefAttribute(attributeOne)));
        assertTrue(merged.getSuccessConditionSet()
                .contains(DefEntityCondition.getDefEntity(entityTwo)));
        assertTrue(merged.getSuccessConditionSet()
                .contains(DefAttributeCondition.getDefAttribute(attributeTwo)));
        assertEquals(0, merged.getActivationConditionSet().size());
        assertEquals(1, merged.getEntityInvariantConditionSet().size());
        assertTrue(merged.getEntityInvariantConditionSet().contains(
                MulCondition.getMulCondition(relation, ROLENAME_ONE)));
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
                .contains(DefEntityCondition.getDefEntity(entityOne)));
        assertTrue(result.getSuccessConditionSet()
                .contains(DefEntityCondition.getDefEntity(entityTwo)));
        assertTrue(result.getSuccessConditionSet()
                .contains(DefAttributeCondition.getDefAttribute(attributeTwo)));
        assertEquals(0, result.getActivationConditionSet().size());
        assertEquals(2, result.getEntityInvariantConditionSet().size());
        assertTrue(result.getEntityInvariantConditionSet().contains(
                MulCondition.getMulCondition(relation, ROLENAME_ONE)));
        assertTrue(result.getEntityInvariantConditionSet().contains(
                MulCondition.getMulCondition(relation, ROLENAME_TWO)));
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
