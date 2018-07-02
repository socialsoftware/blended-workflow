package pt.ist.socialsoftware.blendedworkflow.core.domain.activitymodel;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HelperMethodsTest extends TeardownRollbackTest {
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String ATTRIBUTE_FIVE_NAME = "att5";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";

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
	Rule ruleOne;
	Rule ruleTwo;

	ActivityModel taskModel;
	Activity taskOne;
	Activity taskTwo;
	Activity taskThree;

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

		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FOUR_NAME,
				AttributeType.NUMBER, false);
		this.attributeFive = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FIVE_NAME,
				AttributeType.NUMBER, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH_ONE);

		this.ruleOne = new Rule(this.entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.ruleTwo = new Rule(this.entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FIVE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOUR_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getConditionModel().generateConditions();

		this.taskModel = this.spec.getActivityModel();

		this.taskOne = new Activity(this.spec.getActivityModel(), TASK_ONE, "Description");
		this.taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.taskOne.addRuleInvariant(this.ruleOne);

		this.taskTwo = new Activity(this.spec.getActivityModel(), TASK_TWO, "Description");
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE_NAME));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.taskTwo
				.addMultiplicityInvariant(MulCondition.getMulCondition(this.relation, this.relation.getRolenameOne()));
		this.taskTwo
				.addMultiplicityInvariant(MulCondition.getMulCondition(this.relation, this.relation.getRolenameTwo()));

		this.taskThree = new Activity(this.spec.getActivityModel(), TASK_THREE, "Description");
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_TWO_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_THREE_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH_ONE));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		this.taskThree.addRuleInvariant(this.ruleTwo);

	}

	@Test
	public void activityPrecedesActivity() {
		boolean result = this.taskModel.checkActivityPrecedesActivity(this.taskOne, this.taskThree);

		assertTrue(result);
	}

	@Test
	public void activityDoesNotPrecedesActivity() {
		boolean result = this.taskModel.checkActivityPrecedesActivity(this.taskThree, this.taskOne);

		assertFalse(result);
	}

}
