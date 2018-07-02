package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

import static org.junit.Assert.*;

public class HelperMethodsTest extends TeardownRollbackTest {
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
		this.spec = new Specification("SpecId", "My spec");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH);

		new Rule(this.entityOne, RULE_CONDITION,
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
	public void goalIsParent() {
		boolean result = this.spec.getGoalModel().checkGoalIsParentOfGoal(this.topGoal, this.childGoalTwo);

		assertTrue(result);
	}

	@Test
	public void goalIsNotParent() {
		boolean result = this.spec.getGoalModel().checkGoalIsParentOfGoal(this.childGoalTwo, this.topGoal);

		assertFalse(result);
	}

}
