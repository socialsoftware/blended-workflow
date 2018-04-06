package pt.ist.socialsoftware.blendedworkflow.core.domain.activitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CheckConsistencyMethodTest extends TeardownRollbackTest {
	private static final String EXISTS_ENTITY = "ExistsEntity";
	private static final String ATTRIBUTE_FOURTWO_NAME = "att42";
	private static final String ATTRIBUTE_FOURONE_NAME = "att41";
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String ANOTHER_TASK = "anotherTask";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String ROLENAME_THREE = "theThree";
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";
	private static final String DEPENDENCE_PATH = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Entity existsEntity;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFourOne;
	Attribute attributeFourTwo;
	RelationBW existsRelationOne;
	RelationBW existsRelationTwo;
	RelationBW existsRelationThree;
	Rule ruleOne;
	Rule ruleTwo;

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

		this.existsRelationOne = new RelationBW(this.spec.getDataModel(), "nameOne", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);
		this.attributeFourOne = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FOURONE_NAME,
				AttributeType.NUMBER, false);
		this.attributeFourTwo = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FOURTWO_NAME,
				AttributeType.NUMBER, false);

		this.existsRelationTwo = new RelationBW(this.spec.getDataModel(), "nameTwo", this.entityTwo, ROLENAME_TWO,
				Cardinality.ONE, false, this.entityThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH);

		this.ruleOne = new Rule(this.entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.ruleTwo = new Rule(this.entityThree, RULE_TWO_NAME,
				new Comparison(
						new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURTWO_NAME),
						ComparisonOperator.EQUAL));

		this.existsEntity = new Entity(this.spec.getDataModel(), EXISTS_ENTITY, true);
		this.existsRelationThree = new RelationBW(this.spec.getDataModel(), "other", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.existsEntity, "existingEntity", Cardinality.ZERO_MANY, false);

		this.spec.getConditionModel().generateConditions();

		this.taskOne = new Activity(this.spec.getActivityModel(), TASK_ONE, "Description");
		this.taskOne.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, EXISTS_ENTITY));
		this.taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationThree, this.existsRelationThree.getRolenameOne()));
		this.taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationThree, this.existsRelationThree.getRolenameTwo()));
		this.taskOne.addRuleInvariant(this.ruleOne);

		this.taskTwo = new Activity(this.spec.getActivityModel(), TASK_TWO, "Description");
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE_NAME));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameOne()));
		this.taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameTwo()));
		this.taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationTwo, this.existsRelationTwo.getRolenameOne()));
		this.taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationTwo, this.existsRelationTwo.getRolenameTwo()));

		this.taskThree = new Activity(this.spec.getActivityModel(), TASK_THREE, "Description");
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_TWO_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_THREE_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFourOne));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFourTwo));
		this.taskThree.addRuleInvariant(this.ruleTwo);

	}

	@Test
	public void taskWithoutDefsPost() {
		new Activity(this.spec.getActivityModel(), ANOTHER_TASK, "Description");

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NO_DEF_CONDITION_IN_POST, bwe.getError());
			assertEquals(ANOTHER_TASK, bwe.getMessage());
		}
	}

	@Test
	public void defConditionNotAppliedToPost() {
		this.taskOne.removePostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("DEF(" + ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME + ")", bwe.getMessage());
		}
	}

	@Test
	public void defGroupConditionNotAppliedToPost() {
		this.taskThree.removePostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFourOne));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("DEF(" + ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURONE_NAME + ")", bwe.getMessage());
		}
	}

	@Test
	public void mulConditionNotAppliedToPost() {
		this.taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameTwo()));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals(MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameTwo())
					.getSubPath(), bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionNotAppliedToPost() {
		this.taskOne.removeRuleInvariant(this.ruleOne);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals(RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void defAttributeDoesNotDependOnDefEntity() {
		this.taskThree.removePreCondition(ENTITY_TWO_NAME);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.MISSING_DEF_IN_PRE, bwe.getError());
			assertEquals(TASK_THREE + ":" + ENTITY_TWO_NAME + "." + ATTRIBUTE_THREE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void defAttributeDoesNotEnforceDependenceConstraint() {
		this.taskThree.removePreCondition(DEPENDENCE_PATH);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.MISSING_DEF_IN_PRE, bwe.getError());
			assertEquals(TASK_THREE + ":" + ATTRIBUTE_THREE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void mulConditionIsWronglyLocated() {
		this.taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameOne()));
		this.taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameOne()));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_ONE + ":" + ENTITY_TWO_NAME + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void mulConditionDoesNotHaveDefEntity() {
		this.taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameOne()));
		this.taskThree.addMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationOne, this.existsRelationOne.getRolenameOne()));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_THREE + ":" + ENTITY_TWO_NAME + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionIsWronglyLocated() {
		this.taskOne.removeRuleInvariant(this.ruleOne);
		this.taskThree.addRuleInvariant(this.ruleOne);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_THREE + ":" + RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionDoesNotHaveDefAttribute() {
		this.taskOne.removeRuleInvariant(this.ruleOne);
		this.taskTwo.addRuleInvariant(this.ruleOne);
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec,
				DefAttributeCondition.getDefAttributeCondition(this.attributeOne).getPath().getValue()));
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec,
				DefAttributeCondition.getDefAttributeCondition(this.attributeTwo).getPath().getValue()));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_TWO + ":" + RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionDoesNotHaveDefAttributeGroup() {
		this.taskThree.removeRuleInvariant(this.ruleTwo);
		this.taskTwo.addRuleInvariant(this.ruleTwo);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_TWO + ":" + RULE_TWO_NAME, bwe.getMessage());
		}
	}

	@Test
	public void success() {
		this.spec.getActivityModel().checkModel();
	}

	@Test
	public void existsEntitySuccess() {
		this.spec.getActivityModel().checkModel();
	}

	@Test
	public void existsEntityFailureNoPreCondition() {
		this.taskOne.removePreCondition(EXISTS_ENTITY);

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_ONE + ":" + EXISTS_ENTITY + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void existsEntityFailureNoMulCondition() {
		this.taskOne.removeMultiplicityInvariant(
				MulCondition.getMulCondition(this.existsRelationThree, this.existsRelationThree.getRolenameOne()));

		try {
			this.spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("MUL(" + EXISTS_ENTITY + "." + ROLENAME_ONE + "," + Cardinality.ONE + ")", bwe.getMessage());
		}
	}

}
