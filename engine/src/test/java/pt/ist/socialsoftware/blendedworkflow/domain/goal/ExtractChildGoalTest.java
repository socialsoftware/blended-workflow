package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ExtractChildGoalTest extends TeardownRollbackTest {
    private static final String ATTRIBUTE_NAME_TWO = "att2";
    private static final String ENTITY_TWO_NAME = "Entity two name";
    private static final String ENTITY_ONE_NAME = "Entity one name";
    private static final String TOP_GOAL = "topGoal";
    private static final String RULE_CONDITION = "rule";
    private static final String ROLENAME_ONE = "theOne";
    private static final String ROLENAME_TWO = "theTwo";
    private static final String CHILD_GOAL_TWO = "childGoalTwo";
    private static final String CHILD_GOAL_ONE = "childGoalOne";
    private static final String CHILD_GOAL_THREE = "childGoalThree";
    private static final String CHILD_GOAL_TWO_ONE = "childGoalTwoOne";
    private static final String CHILD_GOAL_TWO_ONE_ONE = "childGoalTwoOneOne";
    private static final String CHILD_GOAL_TWO_TWO = "childGoalTwoTwo";

    Specification spec;
    Entity entityOne;
    Entity entityTwo;
    Attribute attributeOne;
    Attribute attributeTwo;
    Attribute attributeThree;
    Attribute attributeFour;
    RelationBW relation;
    Goal topGoal;
    Goal childGoalOne;
    Goal childGoalTwo;
    Goal childGoalTwoOne;

    @Override
    public void populate4Test() throws BWException {
        spec = new Specification("SpecId", "My spec", "author", "description",
                "version", "UID");

        entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
        attributeOne = new Attribute(spec.getDataModel(), entityOne, null,
                "att1", AttributeType.NUMBER, true, false, false);
        attributeTwo = new Attribute(spec.getDataModel(), entityOne, null,
                ATTRIBUTE_NAME_TWO, AttributeType.NUMBER, true, false, false);

        entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
        attributeThree = new Attribute(spec.getDataModel(), entityTwo, null,
                "att3", AttributeType.BOOLEAN, true, false, false);
        attributeFour = new Attribute(spec.getDataModel(), entityTwo, null,
                "att4", AttributeType.STRING, true, false, false);

        relation = new RelationBW(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);

        Dependence dependence = new Dependence(spec.getDataModel(),
                attributeThree, ENTITY_TWO_NAME + "." + ROLENAME_ONE + "."
                        + ATTRIBUTE_NAME_TWO);
        dependence.check();

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
        childGoalTwo.addSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeThree));
        childGoalTwoOne.addSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeFour));

        childGoalOne.addActivationCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));

        topGoal.addEntityInvariantCondition(
                MulCondition.getMulCondition(relation, ROLENAME_TWO));
        childGoalTwo.addEntityInvariantCondition(
                MulCondition.getMulCondition(relation, ROLENAME_ONE));

        Rule rule = new Rule(spec.getDataModel(), RULE_CONDITION,
                new Comparison(new AttributeValueExpression(attributeOne),
                        new AttributeValueExpression(attributeTwo),
                        ComparisonOperator.EQUAL));

        childGoalOne.addAttributeInvariantCondition(rule);
        childGoalTwo.addAttributeInvariantCondition(rule);
    }

    @Test
    public void conditionsToExtractShouldNotBeEmpty() {
        Set<Condition> successConditions = new HashSet<Condition>();

        try {
            topGoal.extractChild(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
        }
    }

    @Test
    public void parentCannotEndUpWithEmptySuccessCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DefAttributeCondition.getDefAttribute(attributeFour));

        try {
            childGoalTwoOne.extractChild(CHILD_GOAL_TWO_ONE_ONE,
                    successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
        }
    }

    @Test
    public void parentDoesNotContainCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DefEntityCondition.getDefEntity(entityTwo));

        try {
            childGoalTwoOne.extractChild(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkConditionsExistInSource:" + "DEF("
                    + entityTwo.getName() + ")", bwe.getMessage());
        }

    }

    @Test
    public void defAttributeInParent() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DefEntityCondition.getDefEntity(entityTwo));

        try {
            childGoalTwo.extractChild(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkParentChildAttributeConstraint:"
                    + entityTwo.getName(), bwe.getMessage());
        }

    }

    @Test
    public void defAttributeInChild() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DefEntityCondition.getDefEntity(entityTwo));

        childGoalTwo.removeSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeThree));
        childGoalTwoOne.addSuccessCondition(
                DefAttributeCondition.getDefAttribute(attributeFour));

        try {
            childGoalTwo.extractChild(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkParentChildAttributeConstraint:"
                    + entityTwo.getName(), bwe.getMessage());
        }

    }

    @Test
    public void parentAttributeDependsOnChildAttribute() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DefAttributeCondition.getDefAttribute(attributeTwo));

        try {
            childGoalTwo.extractChild(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkDependenceConstraint:" + attributeTwo.getName(),
                    bwe.getMessage());
        }

    }

    @Test
    public void successAndOnlyChangesSuccCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DefAttributeCondition.getDefAttribute(attributeThree));

        Goal newGoal = childGoalTwo.extractChild(CHILD_GOAL_TWO_TWO,
                successConditions);

        assertEquals(CHILD_GOAL_TWO_TWO, newGoal.getName());
        assertEquals(childGoalTwo, newGoal.getParentGoal());
        assertEquals(0, newGoal.getSubGoalSet().size());
        assertEquals(1, newGoal.getSuccessConditionSet().size());
        assertTrue(newGoal.getSuccessConditionSet().contains(
                DefAttributeCondition.getDefAttribute(attributeThree)));
        assertEquals(1, newGoal.getActivationConditionSet().size());
        assertEquals(DefAttributeCondition.getDefAttribute(attributeTwo),
                newGoal.getActivationConditionSet().stream().findFirst().get());
        assertEquals(0, newGoal.getEntityInvariantConditionSet().size());
        assertEquals(0, newGoal.getAttributeInvariantConditionSet().size());

        assertEquals(2, childGoalTwo.getSuccessConditionSet().size());
        assertFalse(childGoalTwo.getSuccessConditionSet().contains(
                DefAttributeCondition.getDefAttribute(attributeThree)));
        assertEquals(0, childGoalTwo.getActivationConditionSet().size());
        assertEquals(1, childGoalTwo.getEntityInvariantConditionSet().size());
        assertEquals(MulCondition.getMulCondition(relation, ROLENAME_ONE),
                childGoalTwo.getEntityInvariantConditionSet().stream()
                        .findFirst().get());
        assertEquals(1,
                childGoalTwo.getAttributeInvariantConditionSet().size());

    }

}
