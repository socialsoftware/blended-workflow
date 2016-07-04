package pt.ist.socialsoftware.blendedworkflow.domain.activitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_ONE_NAME, AttributeType.NUMBER, true,
				false, false);
		attributeTwo = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_TWO_NAME, AttributeType.NUMBER, true,
				false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new Attribute(spec.getDataModel(), entityTwo, ATTRIBUTE_THREE_NAME, AttributeType.BOOLEAN,
				true, false, false);

		existsRelationOne = new RelationBW(spec.getDataModel(), "nameOne", entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		entityThree = new Entity(spec.getDataModel(), ENTITY_THREE_NAME, false);
		attributeFourOne = new Attribute(spec.getDataModel(), entityThree, ATTRIBUTE_FOURONE_NAME, AttributeType.NUMBER,
				false, false, false);
		attributeFourTwo = new Attribute(spec.getDataModel(), entityThree, ATTRIBUTE_FOURTWO_NAME, AttributeType.NUMBER,
				false, false, false);

		existsRelationTwo = new RelationBW(spec.getDataModel(), "nameTwo", entityTwo, ROLENAME_TWO, Cardinality.ONE,
				false, entityThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH);

		ruleOne = new Rule(entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		ruleTwo = new Rule(entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURONE_NAME),
						new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURTWO_NAME),
						ComparisonOperator.EQUAL));

		existsEntity = new Entity(spec.getDataModel(), EXISTS_ENTITY, true);
		existsRelationThree = new RelationBW(spec.getDataModel(), "other", entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, existsEntity, "existingEntity", Cardinality.ZERO_MANY, false);

		spec.getConditionModel().generateConditions();

		taskOne = new Activity(spec.getActivityModel(), TASK_ONE, "Description");
		taskOne.addPreCondition(DefPathCondition.getDefPathCondition(spec, EXISTS_ENTITY));
		taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(entityOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeTwo));
		taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationThree, existsRelationThree.getRolenameOne()));
		taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationThree, existsRelationThree.getRolenameTwo()));
		taskOne.addRuleInvariant(ruleOne);

		taskTwo = new Activity(spec.getActivityModel(), TASK_TWO, "Description");
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_ONE_NAME));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entityTwo));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entityThree));
		taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameOne()));
		taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameTwo()));
		taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationTwo, existsRelationTwo.getRolenameOne()));
		taskTwo.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationTwo, existsRelationTwo.getRolenameTwo()));

		taskThree = new Activity(spec.getActivityModel(), TASK_THREE, "Description");
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_TWO_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_THREE_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeThree));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeFourOne));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeFourTwo));
		taskThree.addRuleInvariant(ruleTwo);

	}

	@Test
	public void taskWithoutDefsPost() {
		new Activity(spec.getActivityModel(), ANOTHER_TASK, "Description");

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NO_DEF_CONDITION_IN_POST, bwe.getError());
			assertEquals(ANOTHER_TASK, bwe.getMessage());
		}
	}

	@Test
	public void defConditionNotAppliedToPost() {
		taskOne.removePostCondition(DefAttributeCondition.getDefAttributeCondition(attributeTwo));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("DEF(" + ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME + ")", bwe.getMessage());
		}
	}

	@Test
	public void defGroupConditionNotAppliedToPost() {
		taskThree.removePostCondition(DefAttributeCondition.getDefAttributeCondition(attributeFourOne));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("DEF(" + ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURONE_NAME + ")", bwe.getMessage());
		}
	}

	@Test
	public void mulConditionNotAppliedToPost() {
		taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameTwo()));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals(
					MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameTwo()).getSubPath(),
					bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionNotAppliedToPost() {
		taskOne.removeRuleInvariant(ruleOne);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals(RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void defAttributeDoesNotDependOnDefEntity() {
		taskThree.removePreCondition(ENTITY_TWO_NAME);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.MISSING_DEF_IN_PRE, bwe.getError());
			assertEquals(TASK_THREE + ":" + ENTITY_TWO_NAME + "." + ATTRIBUTE_THREE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void defAttributeDoesNotEnforceDependenceConstraint() {
		taskThree.removePreCondition(DEPENDENCE_PATH);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.MISSING_DEF_IN_PRE, bwe.getError());
			assertEquals(TASK_THREE + ":" + ATTRIBUTE_THREE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void mulConditionIsWronglyLocated() {
		taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameOne()));
		taskOne.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameOne()));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_ONE + ":" + ENTITY_TWO_NAME + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void mulConditionDoesNotHaveDefEntity() {
		taskTwo.removeMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameOne()));
		taskThree.addMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationOne, existsRelationOne.getRolenameOne()));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_THREE + ":" + ENTITY_TWO_NAME + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionIsWronglyLocated() {
		taskOne.removeRuleInvariant(ruleOne);
		taskThree.addRuleInvariant(ruleOne);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_THREE + ":" + RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionDoesNotHaveDefAttribute() {
		taskOne.removeRuleInvariant(ruleOne);
		taskTwo.addRuleInvariant(ruleOne);
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec,
				DefAttributeCondition.getDefAttributeCondition(attributeOne).getPath().getValue()));
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec,
				DefAttributeCondition.getDefAttributeCondition(attributeTwo).getPath().getValue()));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_TWO + ":" + RULE_ONE_NAME, bwe.getMessage());
		}
	}

	@Test
	public void ruleConditionDoesNotHaveDefAttributeGroup() {
		taskThree.removeRuleInvariant(ruleTwo);
		taskTwo.addRuleInvariant(ruleTwo);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_RULE_CONDITION, bwe.getError());
			assertEquals(TASK_TWO + ":" + RULE_TWO_NAME, bwe.getMessage());
		}
	}

	@Test
	public void success() {
		spec.getActivityModel().checkModel();
	}

	@Test
	public void existsEntitySuccess() {
		spec.getActivityModel().checkModel();
	}

	@Test
	public void existsEntityFailureNoPreCondition() {
		taskOne.removePreCondition(EXISTS_ENTITY);

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_MUL_CONDITION, bwe.getError());
			assertEquals(TASK_ONE + ":" + EXISTS_ENTITY + "." + ROLENAME_ONE, bwe.getMessage());
		}
	}

	@Test
	public void existsEntityFailureNoMulCondition() {
		taskOne.removeMultiplicityInvariant(
				MulCondition.getMulCondition(existsRelationThree, existsRelationThree.getRolenameOne()));

		try {
			spec.getActivityModel().checkModel();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, bwe.getError());
			assertEquals("MUL(" + EXISTS_ENTITY + "." + ROLENAME_ONE + "," + Cardinality.ONE + ")", bwe.getMessage());
		}
	}

}
