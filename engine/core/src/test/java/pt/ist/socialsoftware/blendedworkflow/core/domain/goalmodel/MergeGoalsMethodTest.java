package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true, false, false);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH);

		Rule rule = new Rule(this.entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getDataModel().checkPaths();

		this.spec.getConditionModel().generateConditions();

		this.topGoal = new Goal(this.spec.getGoalModel(), TOP_GOAL);
		this.childGoalOne = new Goal(this.spec.getGoalModel(), CHILD_GOAL_ONE);
		this.childGoalTwo = new Goal(this.spec.getGoalModel(), CHILD_GOAL_TWO);
		this.topGoal.addSubGoal(this.childGoalOne);
		this.topGoal.addSubGoal(this.childGoalTwo);

		this.topGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.childGoalOne.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.childGoalTwo.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		this.childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));

		this.topGoal.applyConditions();
		this.childGoalOne.applyConditions();
		this.childGoalTwo.applyConditions();

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void siblingsMerge() {
		Goal merged = this.spec.getGoalModel().mergeGoals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, this.childGoalOne,
				this.childGoalTwo);

		assertFalse(this.spec.getGoalModel().existsGoal(CHILD_GOAL_ONE));
		assertFalse(this.spec.getGoalModel().existsGoal(CHILD_GOAL_TWO));
		assertEquals(this.topGoal, merged.getParentGoal());
		assertEquals(CHILD_GOAL_ONE + CHILD_GOAL_TWO, merged.getName());
		assertEquals(0, merged.getSubGoalSet().size());
		assertEquals(4, merged.getSuccessConditionSet().size());
		assertTrue(merged.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertTrue(merged.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(this.entityTwo)));
		assertTrue(merged.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo)));
		assertEquals(0, merged.getActivationConditionSet().size());
		assertEquals(1, merged.getEntityInvariantConditionSet().size());
		assertTrue(merged.getEntityInvariantConditionSet()
				.contains(MulCondition.getMulCondition(this.relation, ROLENAME_ONE)));
		assertEquals(0, merged.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void parentChildMerge() {
		Goal result = this.spec.getGoalModel().mergeGoals(TOP_GOAL, this.childGoalOne, this.topGoal);

		assertFalse(this.spec.getGoalModel().existsGoal(CHILD_GOAL_ONE));
		assertTrue(this.spec.getGoalModel().existsGoal(TOP_GOAL));
		assertEquals(this.topGoal, result);

		assertEquals(1, result.getSubGoalSet().size());
		assertTrue(result.getSubGoalSet().contains(this.childGoalTwo));
		assertEquals(2, result.getSuccessConditionSet().size());
		assertTrue(result.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(this.entityOne)));
		assertTrue(result.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertEquals(0, result.getActivationConditionSet().size());
		assertEquals(1, result.getEntityInvariantConditionSet().size());
		assertTrue(result.getEntityInvariantConditionSet()
				.contains(MulCondition.getMulCondition(this.relation, ROLENAME_TWO)));
		assertEquals(1, result.getAttributeInvariantConditionSet().size());
		assertTrue(result.getAttributeInvariantConditionSet().contains(this.entityOne.getRule(RULE_CONDITION)));

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void parentChildConflict() {
		try {
			this.spec.getGoalModel().mergeGoals("Name", this.topGoal, this.childGoalTwo);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}

	}

}
