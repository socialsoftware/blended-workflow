package pt.ist.socialsoftware.blendedworkflow.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MergeGoalsMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String TOP_GOAL = "topGoal";
	private static final String RULE_CONDITION = "rule";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String CHILD_GOAL_TWO = "childGoalTwo";
	private static final String CHILD_GOAL_ONE = "childGoalOne";
	private static final String DEPENDENCE_PATH = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_ONE_NAME;

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	RelationBW relation;
	Goal topGoal;
	Goal childGoalOne;
	Goal childGoalTwo;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_ONE_NAME, AttributeType.NUMBER, true,
				false, false);
		attributeTwo = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_TWO_NAME, AttributeType.NUMBER, true,
				false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new Attribute(spec.getDataModel(), entityTwo, ATTRIBUTE_THREE_NAME, AttributeType.BOOLEAN,
				true, false, false);

		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH);

		Rule rule = new Rule(entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		spec.getDataModel().checkPaths();

		spec.getConditionModel().generateConditions();

		topGoal = new Goal(spec.getGoalModel(), TOP_GOAL);
		childGoalOne = new Goal(spec.getGoalModel(), CHILD_GOAL_ONE);
		childGoalTwo = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO);
		topGoal.addSubGoal(childGoalOne);
		topGoal.addSubGoal(childGoalTwo);

		topGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entityOne));
		childGoalOne.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attributeOne));
		childGoalTwo.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entityTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attributeTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attributeThree));

		topGoal.applyConditions();
		childGoalOne.applyConditions();
		childGoalTwo.applyConditions();

		spec.getGoalModel().checkModel();
	}

	@Test
	public void siblingsMerge() {
		Goal merged = spec.getGoalModel().mergeGoals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, childGoalOne, childGoalTwo);

		assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_ONE));
		assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_TWO));
		assertEquals(topGoal, merged.getParentGoal());
		assertEquals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, merged.getName());
		assertEquals(0, merged.getSubGoalSet().size());
		assertEquals(4, merged.getSuccessConditionSet().size());
		assertTrue(merged.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttributeCondition(attributeOne)));
		assertTrue(merged.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(entityTwo)));
		assertTrue(merged.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttributeCondition(attributeTwo)));
		assertEquals(0, merged.getActivationConditionSet().size());
		assertEquals(1, merged.getEntityInvariantConditionSet().size());
		assertTrue(
				merged.getEntityInvariantConditionSet().contains(MulCondition.getMulCondition(relation, ROLENAME_ONE)));
		assertEquals(0, merged.getAttributeInvariantConditionSet().size());

		spec.getGoalModel().checkModel();
	}

	@Test
	public void parentChildMerge() {
		Goal result = spec.getGoalModel().mergeGoals(TOP_GOAL, childGoalOne, topGoal);

		assertFalse(spec.getGoalModel().existsGoal(CHILD_GOAL_ONE));
		assertTrue(spec.getGoalModel().existsGoal(TOP_GOAL));
		assertEquals(topGoal, result);

		assertEquals(1, result.getSubGoalSet().size());
		assertTrue(result.getSubGoalSet().contains(childGoalTwo));
		assertEquals(2, result.getSuccessConditionSet().size());
		assertTrue(result.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(entityOne)));
		assertTrue(result.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttributeCondition(attributeOne)));
		assertEquals(0, result.getActivationConditionSet().size());
		assertEquals(1, result.getEntityInvariantConditionSet().size());
		assertTrue(
				result.getEntityInvariantConditionSet().contains(MulCondition.getMulCondition(relation, ROLENAME_TWO)));
		assertEquals(1, result.getAttributeInvariantConditionSet().size());
		assertTrue(result.getAttributeInvariantConditionSet().contains(entityOne.getRule(RULE_CONDITION)));

		spec.getGoalModel().checkModel();
	}

	@Test
	public void parentChildConflict() {
		try {
			spec.getGoalModel().mergeGoals("Name", topGoal, childGoalTwo);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}

	}

}
