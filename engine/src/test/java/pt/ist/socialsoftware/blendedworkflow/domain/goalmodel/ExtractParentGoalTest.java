package pt.ist.socialsoftware.blendedworkflow.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
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
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		goalModel = spec.getGoalModel();

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_ONE_NAME, AttributeType.NUMBER, true,
				false, false);
		attributeTwo = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_TWO_NAME, AttributeType.NUMBER, true,
				false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new Attribute(spec.getDataModel(), entityTwo, "att3", AttributeType.BOOLEAN, true, false,
				false);
		attributeFour = new Attribute(spec.getDataModel(), entityTwo, "att4", AttributeType.STRING, true, false, false);

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

		topGoal.addSuccessCondition(DefEntityCondition.getDefEntity(entityOne));
		childGoalOne.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeOne));
		childGoalTwo.addSuccessCondition(DefEntityCondition.getDefEntity(entityTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeTwo));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeThree));
		childGoalTwo.addSuccessCondition(DefAttributeCondition.getDefAttribute(attributeFour));

		childGoalOne.addActivationCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH));

		topGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relation, ROLENAME_TWO));
		childGoalTwo.addEntityInvariantCondition(MulCondition.getMulCondition(relation, ROLENAME_ONE));

		childGoalOne.addAttributeInvariantCondition(rule);
		childGoalTwo.addAttributeInvariantCondition(rule);

		spec.getGoalModel().checkModel();
	}

	@Test
	public void cannotExtractAllConditions() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeOne));

		try {
			goalModel.extractParent(childGoalOne, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractCanBeEmpty() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();

		Goal newGoal = goalModel.extractParent(childGoalOne, NEW_PARENT_GOAL, successConditions);

		assertEquals(0, newGoal.getSuccessConditionSet().size());
		assertEquals(1, childGoalOne.getSuccessConditionSet().size());

		assertEquals(topGoal, newGoal.getParentGoal());
		assertEquals(newGoal, childGoalOne.getParentGoal());
	}

	@Test
	public void extractParentFromTop() {
		topGoal = goalModel.mergeGoals("newTop", topGoal, childGoalOne);

		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefEntityCondition.getDefEntity(entityOne));

		Goal newGoal = goalModel.extractParent(topGoal, NEW_PARENT_GOAL, successConditions);

		assertEquals(1, newGoal.getSuccessConditionSet().size());
		assertEquals(1, topGoal.getSuccessConditionSet().size());

		assertEquals(null, newGoal.getParentGoal());
		assertEquals(newGoal, topGoal.getParentGoal());
	}

	@Test
	public void dependenceContraintError() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefEntityCondition.getDefEntity(entityTwo));
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeThree));

		try {
			goalModel.extractParent(childGoalTwo, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

	@Test
	public void entityAttributeContraintError() {
		Set<DefProductCondition> successConditions = new HashSet<DefProductCondition>();
		successConditions.add(DefAttributeCondition.getDefAttribute(attributeFour));

		try {
			goalModel.extractParent(childGoalTwo, NEW_PARENT_GOAL, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}
	}

}
