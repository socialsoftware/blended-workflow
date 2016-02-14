package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ExtractSiblingGoalTest extends TeardownRollbackTest {
	private static final String ENTITY_TWO_NAME = "Entity two name";
	private static final String ENTITY_ONE_NAME = "Entity one name";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String TOP_GOAL = "topGoal";
	private static final String RULE_CONDITION = "rule";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String CHILD_GOAL_TWO = "childGoalTwo";
	private static final String CHILD_GOAL_ONE = "childGoalOne";
	private static final String CHILD_GOAL_THREE = "childGoalThree";
	private static final String CHILD_GOAL_TWO_ONE = "childGoalTwoOne";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	AttributeBasic attributeOne;
	AttributeBasic attributeTwo;
	AttributeBasic attributeThree;
	AttributeBasic attributeFour;
	AttributeBasic attributeFive;
	RelationBW relation;
	Rule rule;
	Goal topGoal;
	Goal childGoalOne;
	Goal childGoalTwo;
	Goal childGoalTwoOne;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		attributeTwo = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new AttributeBasic(spec.getDataModel(), entityTwo, null, "att3", AttributeType.BOOLEAN, true,
				false, false);
		attributeFour = new AttributeBasic(spec.getDataModel(), entityTwo, null, "att4", AttributeType.STRING, true,
				false, false);

		entityThree = new Entity(spec.getDataModel(), "Entity three name", false);

		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		Dependence dependence = new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH);
		dependence.check();

		topGoal = new Goal(spec.getGoalModel(), TOP_GOAL);
		childGoalOne = new Goal(spec.getGoalModel(), CHILD_GOAL_ONE);
		childGoalTwo = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO);
		childGoalTwoOne = new Goal(spec.getGoalModel(), CHILD_GOAL_TWO_ONE);
		topGoal.addSubGoal(childGoalOne);
		topGoal.addSubGoal(childGoalTwo);
		childGoalTwo.addSubGoal(childGoalTwoOne);

		topGoal.addSuccessCondition(DefEntityCondition.getDefEntity(entityOne));
		topGoal.addSuccessCondition(DefEntityCondition.getDefEntity(entityThree));
		childGoalOne.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeOne));
		childGoalTwo.addSuccessCondition(DefEntityCondition.getDefEntity(entityTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeThree));
		childGoalTwoOne.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeFour));

		childGoalOne.addActivationCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH));

		topGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relation, ROLENAME_TWO));
		childGoalTwo.addEntityInvariantCondition(MulCondition.getMulCondition(relation, ROLENAME_ONE));

		rule = new Rule(entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		childGoalOne.addAttributeInvariantCondition(rule);
		childGoalTwo.addAttributeInvariantCondition(rule);
	}

	@Test
	public void sourceDoesNotContainCondition() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeTwo));

		try {
			childGoalOne.extractSibling(CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
			assertEquals(
					"checkConditionsExistInSource:" + "DEF(" + ENTITY_ONE_NAME + "." + attributeTwo.getName() + ")",
					bwe.getMessage());
		}
	}

	@Test
	public void cannotExtractSiblingOfTopGoal() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefEntityCondition.getDefEntity(entityThree));

		try {
			topGoal.extractSibling("secondTop", successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void sourceCannotEndWithEmptySuccessCondition() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeOne));

		try {
			childGoalOne.extractSibling(CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractShouldNotBeEmpty() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();

		try {
			childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void cannotSeparateDefAttFromDefEnt() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeThree));

		try {
			childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
			assertEquals("checkSiblingsAttributeConstraintBasic:" + entityTwo.getName(), bwe.getMessage());
		}
	}

	@Test
	public void cannotSeparateDefEntFromDefAtt() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefEntityCondition.getDefEntity(entityTwo));

		try {
			childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
			assertEquals("checkSiblingsAttributeConstraintBasic:" + entityTwo.getName(), bwe.getMessage());
		}
	}

	@Test
	public void successOne() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeTwo));

		Goal childGoalThree = childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);

		assertEquals(3, topGoal.getSubGoalSet().size());

		assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
		assertEquals(topGoal, childGoalThree.getParentGoal());
		assertEquals(0, childGoalThree.getSubGoalSet().size());
		assertEquals(1, childGoalThree.getSuccessConditionSet().size());
		assertTrue(
				childGoalThree.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttribute(attributeTwo)));
		assertEquals(0, childGoalThree.getActivationConditionSet().size());
		assertEquals(0, childGoalThree.getEntityInvariantConditionSet().size());
		assertEquals(0, childGoalThree.getAttributeInvariantConditionSet().size());

		assertEquals(CHILD_GOAL_TWO, childGoalTwo.getName());
		assertEquals(topGoal, childGoalTwo.getParentGoal());
		assertEquals(1, childGoalTwo.getSubGoalSet().size());
		assertEquals(2, childGoalTwo.getSuccessConditionSet().size());
		assertTrue(childGoalTwo.getSuccessConditionSet().contains(DefEntityCondition.getDefEntity(entityTwo)));
		assertTrue(
				childGoalTwo.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttribute(attributeThree)));
		assertEquals(1, childGoalTwo.getActivationConditionSet().size());
		assertEquals(DEPENDENCE_PATH, childGoalTwo.getActivationConditionSet().stream().findFirst().get().getPath());
		assertEquals(1, childGoalTwo.getEntityInvariantConditionSet().size());
		assertEquals(ENTITY_TWO_NAME,
				childGoalTwo.getEntityInvariantConditionSet().stream().findFirst().get().getEntity().getName());
		assertEquals(0, childGoalTwo.getAttributeInvariantConditionSet().size());
	}

	@Test
	public void successTwo() {
		childGoalTwo = topGoal.getGoalModel().mergeGoals(CHILD_GOAL_TWO, childGoalTwo, childGoalTwoOne);

		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefEntityCondition.getDefEntity(entityTwo));
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeThree));
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeFour));

		Goal childGoalThree = childGoalTwo.extractSibling(CHILD_GOAL_THREE, successConditions);

		assertEquals(3, topGoal.getSubGoalSet().size());

		assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
		assertEquals(topGoal, childGoalThree.getParentGoal());
		assertEquals(0, childGoalThree.getSubGoalSet().size());
		assertEquals(3, childGoalThree.getSuccessConditionSet().size());
		assertTrue(childGoalThree.getSuccessConditionSet().contains(DefEntityCondition.getDefEntity(entityTwo)));
		assertTrue(childGoalThree.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttribute(attributeThree)));
		assertTrue(
				childGoalThree.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttribute(attributeFour)));
		assertEquals(1, childGoalThree.getActivationConditionSet().size());
		assertEquals(DEPENDENCE_PATH, childGoalThree.getActivationConditionSet().stream().findFirst().get().getPath());
		assertEquals(1, childGoalThree.getEntityInvariantConditionSet().size());
		assertEquals(ENTITY_TWO_NAME,
				childGoalThree.getEntityInvariantConditionSet().stream().findFirst().get().getEntity().getName());
		assertEquals(0, childGoalThree.getAttributeInvariantConditionSet().size());

		assertEquals(CHILD_GOAL_TWO, childGoalTwo.getName());
		assertEquals(topGoal, childGoalTwo.getParentGoal());
		assertEquals(0, childGoalTwo.getSubGoalSet().size());
		assertEquals(1, childGoalTwo.getSuccessConditionSet().size());
		assertTrue(childGoalTwo.getSuccessConditionSet().contains(DefAttributeCondition.getDefAttribute(attributeTwo)));
		assertEquals(0, childGoalTwo.getActivationConditionSet().size());
		assertEquals(0, childGoalTwo.getEntityInvariantConditionSet().size());
		assertEquals(0, childGoalTwo.getAttributeInvariantConditionSet().size());

	}

}