package pt.ist.socialsoftware.blendedworkflow.core.domain.activitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
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
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class AddActivityMethodTest extends TeardownRollbackTest {
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String NEW_TASK_NAME = "NewTaskName";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ENTITY_EXISTS = "EntityExists";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FIVE_NAME = "att5";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String ATTRIBUTE_SIX_NAME = "att6";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String ROLENAME_EXISTS = "theExists";
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";
	private static final String RULE_THREE_NAME = "ruleThree";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Entity entityExists;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFour;
	Attribute attributeFive;
	Attribute attributeSix;
	RelationBW relation;
	RelationBW relationExists;
	Rule ruleOne;
	Rule ruleTwo;
	Rule ruleThree;

	ActivityModel taskModel;
	Activity taskOne;
	Activity taskTwo;
	Activity taskThree;

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

		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FOUR_NAME,
				AttributeType.NUMBER, false, false, false);
		this.attributeSix = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_SIX_NAME,
				AttributeType.NUMBER, false, false, false);

		this.entityExists = new Entity(this.spec.getDataModel(), ENTITY_EXISTS, true);
		this.attributeFive = new Attribute(this.spec.getDataModel(), this.entityExists, ATTRIBUTE_FIVE_NAME,
				AttributeType.NUMBER, true, false, false);

		this.relationExists = new RelationBW(this.spec.getDataModel(), "nameTwo", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entityExists, ROLENAME_EXISTS, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH_ONE);

		this.ruleOne = new Rule(this.entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.ruleTwo = new Rule(this.entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOUR_NAME),
						new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_SIX_NAME),
						ComparisonOperator.EQUAL));

		this.ruleThree = new Rule(this.entityOne, RULE_THREE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec,
								ENTITY_ONE_NAME + "." + ROLENAME_EXISTS + "." + ATTRIBUTE_FIVE_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getConditionModel().generateConditions();

		this.taskModel = this.spec.getActivityModel();

		this.taskOne = new Activity(this.spec.getActivityModel(), TASK_ONE, "Description");
		this.taskOne.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_EXISTS));
		this.taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.relationExists, this.relationExists.getRolenameOne()));
		this.taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.relationExists, this.relationExists.getRolenameTwo()));
		this.taskOne.addRuleInvariant(this.ruleOne);
		this.taskOne.addRuleInvariant(this.ruleThree);

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
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		this.taskThree.addRuleInvariant(this.ruleTwo);

		this.taskModel.checkModel();
	}

	@Test
	public void emptyPostConditionSet() {
		this.taskThree.removePostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));

		try {
			Set<DefProductCondition> postConditions = new HashSet<>();
			this.taskModel.addActivity(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("empty post condition set", bwe.getMessage());
		}
	}

	@Test
	public void allActivitiesWereCreated() {
		try {
			Set<DefProductCondition> postConditions = new HashSet<>();
			postConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
			this.taskModel.addActivity(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("condition already used", bwe.getMessage());
		}
	}

	@Test
	public void conditionBelongsToOtherPostCondition() {
		this.taskThree.removePostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		try {
			Set<DefProductCondition> postConditions = new HashSet<>();
			postConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
			this.taskModel.addActivity(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("condition already used", bwe.getMessage());
		}
	}

}
