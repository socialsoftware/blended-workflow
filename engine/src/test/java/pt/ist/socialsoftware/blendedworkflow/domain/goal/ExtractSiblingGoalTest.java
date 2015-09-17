package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
    private static final String CHILD_GOAL_TWO_TWO = "childGoalTwoTwo";
    private static final String ATTRIBUTE_NAME_TWO = "att2";
    private static final String ENTITY_TWO_NAME = "Entity two name";
    private static final String ENTITY_ONE_NAME = "Entity one name";
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
    BWAttribute attributeFour;
    BWRelation relation;
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
        childGoalOne.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeOne));
        childGoalTwo.addSuccessCondition(
                DEFEntityCondition.getDEFEntity(entityTwo));
        childGoalTwo.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));
        childGoalTwo.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeThree));

        childGoalOne.addActivationCondition(
                DEFAttributeCondition.getDEFAttribute(attributeTwo));

        topGoal.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_TWO));
        childGoalTwo.addEntityInvariantCondition(
                MULCondition.getMulCondition(relation, ROLENAME_ONE));

        BWRule rule = new BWRule(spec.getDataModel(), RULE_CONDITION,
                new Comparison(new BWAttributeValueExpression(attributeOne),
                        new BWAttributeValueExpression(attributeTwo),
                        ComparisonOperator.EQUAL));

        childGoalOne.addAttributeInvariantCondition(rule);
        childGoalTwo.addAttributeInvariantCondition(rule);
    }

    @Test
    public void DefAttributeInParent() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DEFEntityCondition.getDEFEntity(entityTwo));

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_CHILD, bwe.getError());
            assertEquals(entityTwo.getName(), bwe.getMessage());
        }

    }

    @Test
    public void DefAttributeInChild() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions.add(DEFEntityCondition.getDEFEntity(entityTwo));

        childGoalTwo.removeSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeThree));
        childGoalTwoOne.addSuccessCondition(
                DEFAttributeCondition.getDEFAttribute(attributeFour));

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_CHILD, bwe.getError());
            assertEquals(entityTwo.getName(), bwe.getMessage());
        }

    }

    @Test
    public void parentAttributeDependsOnChildAttribute() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeTwo));

        try {
            childGoalTwo.extractSibling(CHILD_GOAL_TWO_TWO, successConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_EXTRACT_CHILD, bwe.getError());
            assertEquals(attributeTwo.getName(), bwe.getMessage());
        }

    }

    @Test
    public void successAndOnlyChangesSuccCondition() {
        Set<Condition> successConditions = new HashSet<Condition>();
        successConditions
                .add(DEFAttributeCondition.getDEFAttribute(attributeThree));

        Goal newGoal = childGoalTwo.extractSibling(CHILD_GOAL_TWO_TWO,
                successConditions);

        assertEquals(CHILD_GOAL_TWO_TWO, newGoal.getName());
        assertEquals(childGoalTwo, newGoal.getParentGoal());
        assertEquals(0, newGoal.getSubGoalSet().size());
        assertEquals(1, newGoal.getSuccessConditionSet().size());
        assertTrue(newGoal.getSuccessConditionSet().contains(
                DEFAttributeCondition.getDEFAttribute(attributeThree)));
        assertEquals(1, newGoal.getActivationConditionSet().size());
        assertEquals(DEFAttributeCondition.getDEFAttribute(attributeTwo),
                newGoal.getActivationConditionSet().stream().findFirst().get());
        assertEquals(0, newGoal.getEntityInvariantConditionSet().size());
        assertEquals(0, newGoal.getAttributeInvariantConditionSet().size());

        assertEquals(2, childGoalTwo.getSuccessConditionSet().size());
        assertFalse(childGoalTwo.getSuccessConditionSet().contains(
                DEFAttributeCondition.getDEFAttribute(attributeThree)));
        assertEquals(0, childGoalTwo.getActivationConditionSet().size());
        assertEquals(1, childGoalTwo.getEntityInvariantConditionSet().size());
        assertEquals(MULCondition.getMulCondition(relation, ROLENAME_ONE),
                childGoalTwo.getEntityInvariantConditionSet().stream()
                        .findFirst().get());
        assertEquals(1,
                childGoalTwo.getAttributeInvariantConditionSet().size());

    }

}
