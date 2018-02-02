package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFour;
	Attribute attributeFive;
	RelationBW relation;
	Rule rule;
	Goal topGoal;
	Goal childGoalOne;
	Goal childGoalTwo;
	Goal childGoalTwoOne;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, "att3", AttributeType.BOOLEAN,
				true, false, false);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityTwo, "att4", AttributeType.STRING, true,
				false, false);

		this.entityThree = new Entity(this.spec.getDataModel(), "Entity three name", false);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH);

		this.rule = new Rule(this.entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getDataModel().checkPaths();

		this.spec.getConditionModel().generateConditions();

		this.topGoal = new Goal(this.spec.getGoalModel(), TOP_GOAL);
		this.childGoalOne = new Goal(this.spec.getGoalModel(), CHILD_GOAL_ONE);
		this.childGoalTwo = new Goal(this.spec.getGoalModel(), CHILD_GOAL_TWO);
		this.childGoalTwoOne = new Goal(this.spec.getGoalModel(), CHILD_GOAL_TWO_ONE);
		this.topGoal.addSubGoal(this.childGoalOne);
		this.topGoal.addSubGoal(this.childGoalTwo);
		this.childGoalTwo.addSubGoal(this.childGoalTwoOne);

		this.topGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.topGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.childGoalOne.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.childGoalTwo.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		this.childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.childGoalTwoOne.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));

		this.childGoalOne.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH));

		this.topGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relation, ROLENAME_TWO));
		this.childGoalTwo.addEntityInvariantCondition(MulCondition.getMulCondition(this.relation, ROLENAME_ONE));

		this.childGoalOne.addAttributeInvariantCondition(this.rule);
		this.childGoalTwo.addAttributeInvariantCondition(this.rule);
	}

	@Test
	public void sourceDoesNotContainCondition() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));

		try {
			this.spec.getGoalModel().extractSibling(this.childGoalOne, CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
			assertEquals("checkConditionsExistInSource:" + "DEF(" + ENTITY_ONE_NAME + "." + this.attributeTwo.getName()
					+ ")", bwe.getMessage());
		}
	}

	@Test
	public void cannotExtractSiblingOfTopGoal() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));

		try {
			this.spec.getGoalModel().extractSibling(this.topGoal, "secondTop", successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

	@Test
	public void sourceCannotEndWithEmptySuccessCondition() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));

		try {
			this.spec.getGoalModel().extractSibling(this.childGoalOne, CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractShouldNotBeEmpty() {
		Set<DefProductCondition> successConditions = new HashSet<>();

		try {
			this.spec.getGoalModel().extractSibling(this.childGoalTwo, CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void cannotSeparateDefAttFromDefEnt() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));

		try {
			this.spec.getGoalModel().extractSibling(this.childGoalTwo, CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

	@Test
	public void cannotSeparateDefEntFromDefAtt() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));

		try {
			this.spec.getGoalModel().extractSibling(this.childGoalTwo, CHILD_GOAL_THREE, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

	@Test
	public void successOne() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));

		Goal childGoalThree = this.spec.getGoalModel().extractSibling(this.childGoalTwo, CHILD_GOAL_THREE,
				successConditions);

		assertEquals(3, this.topGoal.getSubGoalSet().size());

		assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
		assertEquals(this.topGoal, childGoalThree.getParentGoal());
		assertEquals(0, childGoalThree.getSubGoalSet().size());
		assertEquals(1, childGoalThree.getSuccessConditionSet().size());
		assertTrue(childGoalThree.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo)));
		assertEquals(0, childGoalThree.getActivationConditionSet().size());
		assertEquals(0, childGoalThree.getEntityInvariantConditionSet().size());
		assertEquals(0, childGoalThree.getAttributeInvariantConditionSet().size());

		assertEquals(CHILD_GOAL_TWO, this.childGoalTwo.getName());
		assertEquals(this.topGoal, this.childGoalTwo.getParentGoal());
		assertEquals(1, this.childGoalTwo.getSubGoalSet().size());
		assertEquals(2, this.childGoalTwo.getSuccessConditionSet().size());
		assertTrue(this.childGoalTwo.getSuccessConditionSet()
				.contains(DefEntityCondition.getDefEntityCondition(this.entityTwo)));
		assertTrue(this.childGoalTwo.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeThree)));
		assertEquals(1, this.childGoalTwo.getActivationConditionSet().size());
		assertEquals(DEPENDENCE_PATH,
				this.childGoalTwo.getActivationConditionSet().stream().findFirst().get().getPath().getValue());
		assertEquals(1, this.childGoalTwo.getEntityInvariantConditionSet().size());
		assertEquals(ENTITY_ONE_NAME, this.childGoalTwo.getEntityInvariantConditionSet().stream().findFirst().get()
				.getTargetEntity().getName());
		assertEquals(0, this.childGoalTwo.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void successTwo() {
		this.childGoalTwo = this.topGoal.getGoalModel().mergeGoals(CHILD_GOAL_TWO, this.childGoalTwo,
				this.childGoalTwoOne);

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));

		Goal childGoalThree = this.spec.getGoalModel().extractSibling(this.childGoalTwo, CHILD_GOAL_THREE,
				successConditions);

		assertEquals(3, this.topGoal.getSubGoalSet().size());

		assertEquals(CHILD_GOAL_THREE, childGoalThree.getName());
		assertEquals(this.topGoal, childGoalThree.getParentGoal());
		assertEquals(0, childGoalThree.getSubGoalSet().size());
		assertEquals(3, childGoalThree.getSuccessConditionSet().size());
		assertTrue(childGoalThree.getSuccessConditionSet()
				.contains(DefEntityCondition.getDefEntityCondition(this.entityTwo)));
		assertTrue(childGoalThree.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeThree)));
		assertTrue(childGoalThree.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeFour)));
		assertEquals(1, childGoalThree.getActivationConditionSet().size());
		assertEquals(DEPENDENCE_PATH,
				childGoalThree.getActivationConditionSet().stream().findFirst().get().getPath().getValue());
		assertEquals(1, childGoalThree.getEntityInvariantConditionSet().size());
		assertEquals(ENTITY_ONE_NAME,
				childGoalThree.getEntityInvariantConditionSet().stream().findFirst().get().getTargetEntity().getName());
		assertEquals(0, childGoalThree.getAttributeInvariantConditionSet().size());

		assertEquals(CHILD_GOAL_TWO, this.childGoalTwo.getName());
		assertEquals(this.topGoal, this.childGoalTwo.getParentGoal());
		assertEquals(0, this.childGoalTwo.getSubGoalSet().size());
		assertEquals(1, this.childGoalTwo.getSuccessConditionSet().size());
		assertTrue(this.childGoalTwo.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo)));
		assertEquals(0, this.childGoalTwo.getActivationConditionSet().size());
		assertEquals(0, this.childGoalTwo.getEntityInvariantConditionSet().size());
		assertEquals(0, this.childGoalTwo.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

}