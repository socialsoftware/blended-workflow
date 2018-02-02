package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
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
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ExtractParentGoalTest extends TeardownRollbackTest {
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ENTITY_TWO_NAME = "Entity two name";
	private static final String ENTITY_ONE_NAME = "Entity one name";
	private static final String TOP_GOAL = "topGoal";
	private static final String RULE_CONDITION = "rule";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String CHILD_GOAL_TWO = "childGoalTwo";
	private static final String CHILD_GOAL_ONE = "childGoalOne";
	private static final String NEW_PARENT_GOAL = "NewParent";

	Specification spec;
	GoalModel goalModel;
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

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		this.goalModel = this.spec.getGoalModel();

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
		this.childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));

		this.childGoalOne.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH));

		this.topGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relation, ROLENAME_TWO));
		this.childGoalTwo.addEntityInvariantCondition(MulCondition.getMulCondition(this.relation, ROLENAME_ONE));

		this.childGoalOne.addAttributeInvariantCondition(rule);
		this.childGoalTwo.addAttributeInvariantCondition(rule);

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void cannotExtractAllConditions() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));

		try {
			this.goalModel.extractParent(this.childGoalOne, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractCanBeEmpty() {
		Set<DefProductCondition> successConditions = new HashSet<>();

		Goal newGoal = this.goalModel.extractParent(this.childGoalOne, NEW_PARENT_GOAL, successConditions);

		assertEquals(0, newGoal.getSuccessConditionSet().size());
		assertEquals(1, this.childGoalOne.getSuccessConditionSet().size());

		assertEquals(this.topGoal, newGoal.getParentGoal());
		assertEquals(newGoal, this.childGoalOne.getParentGoal());
	}

	@Test
	public void extractParentFromTop() {
		this.topGoal = this.goalModel.mergeGoals("newTop", this.topGoal, this.childGoalOne);

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));

		Goal newGoal = this.goalModel.extractParent(this.topGoal, NEW_PARENT_GOAL, successConditions);

		assertEquals(1, newGoal.getSuccessConditionSet().size());
		assertEquals(1, this.topGoal.getSuccessConditionSet().size());

		assertEquals(null, newGoal.getParentGoal());
		assertEquals(newGoal, this.topGoal.getParentGoal());
	}

	@Test
	public void dependenceContraintError() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));

		try {
			this.goalModel.extractParent(this.childGoalTwo, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

	@Test
	public void entityAttributeContraintError() {
		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));

		try {
			this.goalModel.extractParent(this.childGoalTwo, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

}
