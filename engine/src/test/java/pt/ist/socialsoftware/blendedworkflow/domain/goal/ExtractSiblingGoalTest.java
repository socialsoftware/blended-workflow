package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ExtractSiblingGoalTest extends TeardownRollbackTest {
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

    BWSpecification spec;
    BWEntity entityOne;
    BWEntity entityTwo;
    BWEntity entityThree;
    BWAttribute attributeOne;
    BWAttribute attributeTwo;
    BWAttribute attributeThree;
    BWAttribute attributeFour;
    BWAttribute attributeFive;
    BWRelation relation;
    BWRule rule;
    Goal topGoal;
    Goal childGoalOne;
    Goal childGoalTwo;
    Goal childGoalTwoOne;

    @Override
    public void populate4Test() throws BWException {
        spec = new BWSpecification("SpecId", "My spec", "author", "description",
                "version", "UID");

        entityOne = new BWEntity(spec.getDataModel(), ENTITY_ONE_NAME, false);
        attributeOne = new BWAttribute(spec.getDataModel(), entityOne, null,
                "att1", AttributeType.NUMBER, true, false, false);
        attributeTwo = new BWAttribute(spec.getDataModel(), entityOne, null,
                ATTRIBUTE_NAME_TWO, AttributeType.NUMBER, true, false, false);

        entityTwo = new BWEntity(spec.getDataModel(), ENTITY_TWO_NAME, false);
        attributeThree = new BWAttribute(spec.getDataModel(), entityTwo, null,
                "att3", AttributeType.BOOLEAN, true, false, false);
        attributeFour = new BWAttribute(spec.getDataModel(), entityTwo, null,
                "att4", AttributeType.STRING, true, false, false);

        entityThree = new BWEntity(spec.getDataModel(), "Entity three name",
                false);

        relation = new BWRelation(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);

        BWDependence dependence = new BWDependence(spec.getDataModel(),
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

        topGoal.addSuccessCondition(DEFEntityCondition.getDEFEntity(entityOne));
        topGoal.addSuccessCondition(
                DEFEntityCondition.getDEFEntity(entityThree));
        childGoalOne.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeOne));
        childGoalTwo.addSuccessCondition(
                DEFEntityCondition.getDEFEntity(entityTwo));
        childGoalTwo.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));
        childGoalTwo.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeThree));
        childGoalTwoOne.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeFour));

        childGoalOne.addActivationCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));

        topGoal.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_TWO));
        childGoalTwo.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_ONE));

        rule = new BWRule(spec.getDataModel(), RULE_CONDITION,
                new Comparison(new BWAttributeValueExpression(attributeOne),
                        new BWAttributeValueExpression(attributeTwo),
                        ComparisonOperator.EQUAL));

        childGoalOne.addAttributeInvariantCondition(rule);
        childGoalTwo.addAttributeInvariantCondition(rule);
    }

    @Test
    public void sourceDoesNotContainCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeTwo));

        try {
            childGoalOne.extractSibling(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkConditionsExistInSource:" + "DEF("
                    + attributeTwo.getName() + ")", bwe.getMessage());
        }
    }

    @Test
    public void cannotExtractSiblingOfTopGoal() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DEFEntityCondition.getDEFEntity(entityThree));

        try {
            topGoal.extractSibling("secondTop", successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
        }
    }

    @Test
    public void sourceCannotEndWithEmptySuccessCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeOne));

        try {
            childGoalOne.extractSibling(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
        }
    }

    @Test
    public void conditionsToExtractShouldNotBeEmpty() {
        Set<Condition> successConditions = new HashSet<Condition>();

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
        }
    }

    @Test
    public void cannotSeparateDefAttFromDefEnt() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeThree));

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkSiblingsAttributeConstraintBasic:"
                    + entityTwo.getName(), bwe.getMessage());
        }
    }

    @Test
    public void cannotSeparateDefEntFromDefAtt() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DEFEntityCondition.getDEFEntity(entityTwo));

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
            assertEquals("checkSiblingsAttributeConstraintBasic:"
                    + entityTwo.getName(), bwe.getMessage());
        }
    }

    @Test
    public void successOne() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeTwo));

        Goal childGoalThree = childGoalTwo.extractSibling(CHILD_GOAL_THREE,
                successConditions);

        assertEquals(3, topGoal.getSubGoalSet().size());

        assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
        assertEquals(topGoal, childGoalThree.getParentGoal());
        assertEquals(0, childGoalThree.getSubGoalSet().size());
        assertEquals(1, childGoalThree.getSuccessConditionSet().size());
        assertTrue(childGoalThree.getSuccessConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(0, childGoalThree.getActivationConditionSet().size());
        assertEquals(0, childGoalThree.getEntityInvariantConditionSet().size());
        assertEquals(1,
                childGoalThree.getAttributeInvariantConditionSet().size());
        assertTrue(childGoalThree.getAttributeInvariantConditionSet()
                .contains(rule));

        assertEquals(CHILD_GOAL_TWO, childGoalTwo.getName());
        assertEquals(topGoal, childGoalTwo.getParentGoal());
        assertEquals(1, childGoalTwo.getSubGoalSet().size());
        assertEquals(2, childGoalTwo.getSuccessConditionSet().size());
        assertTrue(childGoalTwo.getSuccessConditionSet()
                .contains(DEFEntityCondition.getDEFEntity(entityTwo)));
        assertTrue(childGoalTwo.getSuccessConditionSet().contains(
                DEFAttributeCondition.getDEFAttribute(attributeThree)));
        assertEquals(1, childGoalTwo.getActivationConditionSet().size());
        assertTrue(childGoalTwo.getActivationConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(1, childGoalTwo.getEntityInvariantConditionSet().size());
        assertEquals(ENTITY_TWO_NAME,
                childGoalTwo.getEntityInvariantConditionSet().stream()
                        .findFirst().get().getEntity().getName());
        assertEquals(0,
                childGoalTwo.getAttributeInvariantConditionSet().size());
    }

    @Test
    public void successTwo() {
        childGoalTwo = topGoal.getGoalModel().mergeGoals(CHILD_GOAL_TWO,
                childGoalTwo, childGoalTwoOne);

        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DEFEntityCondition.getDEFEntity(entityTwo));
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeThree));
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeFour));

        Goal childGoalThree = childGoalTwo.extractSibling(CHILD_GOAL_THREE,
                successConditions);

        assertEquals(3, topGoal.getSubGoalSet().size());

        assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
        assertEquals(topGoal, childGoalThree.getParentGoal());
        assertEquals(0, childGoalThree.getSubGoalSet().size());
        assertEquals(3, childGoalThree.getSuccessConditionSet().size());
        assertTrue(childGoalThree.getSuccessConditionSet()
                .contains(DEFEntityCondition.getDEFEntity(entityTwo)));
        assertTrue(childGoalThree.getSuccessConditionSet().contains(
                DEFAttributeCondition.getDEFAttribute(attributeThree)));
        assertTrue(childGoalThree.getSuccessConditionSet().contains(
                DEFAttributeCondition.getDEFAttribute(attributeFour)));
        assertEquals(1, childGoalThree.getActivationConditionSet().size());
        assertTrue(childGoalThree.getActivationConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(1, childGoalThree.getEntityInvariantConditionSet().size());
        assertEquals(ENTITY_TWO_NAME,
                childGoalThree.getEntityInvariantConditionSet().stream()
                        .findFirst().get().getEntity().getName());
        assertEquals(0,
                childGoalThree.getAttributeInvariantConditionSet().size());

        assertEquals(CHILD_GOAL_TWO, childGoalTwo.getName());
        assertEquals(topGoal, childGoalTwo.getParentGoal());
        assertEquals(0, childGoalTwo.getSubGoalSet().size());
        assertEquals(1, childGoalTwo.getSuccessConditionSet().size());
        assertTrue(childGoalTwo.getSuccessConditionSet()
                .contains(DEFAttributeCondition.getDEFAttribute(attributeTwo)));
        assertEquals(0, childGoalTwo.getActivationConditionSet().size());
        assertEquals(0, childGoalTwo.getEntityInvariantConditionSet().size());
        assertEquals(1,
                childGoalTwo.getAttributeInvariantConditionSet().size());
        assertTrue(childGoalTwo.getAttributeInvariantConditionSet()
                .contains(rule));

    }

}