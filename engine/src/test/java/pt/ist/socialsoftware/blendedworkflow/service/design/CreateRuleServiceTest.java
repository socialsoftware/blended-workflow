package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.BooleanOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Expression;
import pt.ist.socialsoftware.blendedworkflow.domain.Expression.ExpressionAtom;
import pt.ist.socialsoftware.blendedworkflow.domain.NumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

public class CreateRuleServiceTest extends TeardownRollbackTest {
	private static final String EXISTS_SPEC_ID = "ID0";
	private static final String RULE_NAME = "Rule name";
	private static final String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
	private static final String ENTITY_NAME = "Entity Name";
	private static final String EXISTS_ENTITY_NAME = "Exists Entity Name";
	private static final String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";
	private static final String EXISTS_ATTRIBUTE_NAME_STRING = "Exists Attribute Name String";

	DesignInterface designInterface;
	DataModel existingDataModel;

	Entity existsEntity;

	@Override
	public void populate4Test() {
		designInterface = DesignInterface.getInstance();

		Specification spec = new Specification(EXISTS_SPEC_ID, EXISTS_SPEC_NAME, "author", "description", "version",
				"UID");
		existingDataModel = spec.getDataModel();

		existsEntity = new Entity(existingDataModel, EXISTS_ENTITY_NAME, false);
		Entity entityTwo = new Entity(existingDataModel, ENTITY_NAME, false);
		new AttributeBasic(existingDataModel, existsEntity, null, EXISTS_ATTRIBUTE_NAME, AttributeType.NUMBER, true,
				false, false);
		new AttributeBasic(existingDataModel, existsEntity, null, EXISTS_ATTRIBUTE_NAME_STRING, AttributeType.STRING,
				false, false, false);

		new RelationBW(existingDataModel, "relation", existsEntity, "role1", Cardinality.ZERO_OR_ONE, false, entityTwo,
				"role2", Cardinality.ONE, false);
	}

	@Test
	public void successNewCreateComparatorRule() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
				new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"), new ExpressionDTO(EXISTS_SPEC_ID,
						ExpressionAtom.ATT_VALUE, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));

		Specification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID).get();
		assertNotNull(spec);
		assertEquals(1, existsEntity.getRuleSet().size());
		Rule rule = existsEntity.getRuleSet().stream().collect(Collectors.toList()).get(0);
		assertTrue(rule.getCondition() instanceof Comparison);
		Comparison comparison = (Comparison) rule.getCondition();
		assertEquals(Comparison.ComparisonOperator.GREATER, comparison.getComparator());
		Expression leftExpression = comparison.getLeftExpression();
		Expression righExpression = comparison.getRightExpression();

		NumberLiteral literal = (NumberLiteral) leftExpression;
		assertEquals(6, literal.getValue());

		AttributeValueExpression attValue = (AttributeValueExpression) righExpression;
		Entity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME).get();
		AttributeBasic att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
		assertEquals(att, attValue.getAttribute());
	}

	@Test
	public void successNewCreateAndConstraint() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));

		Specification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID).get();
		assertNotNull(spec);
		assertEquals(1, existsEntity.getRuleSet().size());
		Rule rule = existsEntity.getRuleSet().stream().collect(Collectors.toList()).get(0);
		assertTrue(rule.getCondition() instanceof AndCondition);
		AndCondition andCondition = (AndCondition) rule.getCondition();
		Condition leftCondition = andCondition.getLeftCondition();
		Condition rightCondition = andCondition.getRightCondition();
		assertTrue(leftCondition instanceof Comparison);
		assertTrue(rightCondition instanceof TrueCondition);

		Comparison comparison = (Comparison) leftCondition;
		assertEquals(Comparison.ComparisonOperator.GREATER, comparison.getComparator());
		Expression leftExpression = comparison.getLeftExpression();
		Expression righExpression = comparison.getRightExpression();

		NumberLiteral literal = (NumberLiteral) leftExpression;
		assertEquals(6, literal.getValue());

		AttributeValueExpression attValue = (AttributeValueExpression) righExpression;
		Entity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME).get();
		AttributeBasic att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
		assertEquals(att, attValue.getAttribute());
	}

	@Test(expected = BWException.class)
	public void failCauseofWrongAttributeName() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME + "x")),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test(expected = BWException.class)
	public void failCauseofWrongRoleName() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								ENTITY_NAME + "." + "rolex" + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, ENTITY_NAME, RULE_NAME, expDTO));
	}

	public void failCauseofWrongInitialPath() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								ENTITY_NAME + "." + "rolex" + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		try {
			designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

	@Test
	public void expressionWithNotAndDef() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT, new ExpressionDTO(EXISTS_SPEC_ID,
				BooleanOperator.PATH_DEF, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithNotAndAndDef() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT,
						new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.PATH_DEF,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "false"));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithNotAndDefAndNotEqual() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.NOT_EQUAL,
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT,
						new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.PATH_DEF,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "false"));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithGreaterAndAttributeAndStringLiteral() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
				new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.STRING, "today"), new ExpressionDTO(EXISTS_SPEC_ID,
						ExpressionAtom.ATT_VALUE, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME_STRING));

		designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

}
