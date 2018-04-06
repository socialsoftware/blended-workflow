package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Condition.BooleanOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Expression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Expression.ExpressionAtom;
import pt.ist.socialsoftware.blendedworkflow.core.domain.NumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.RuleDTO;

public class CreateRuleServiceTest extends TeardownRollbackTest {
	private static final String EXISTS_SPEC_ID = "ID0";
	private static final String RULE_NAME = "Rule name";
	private static final String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
	private static final String ENTITY_NAME = "Entity Name";
	private static final String EXISTS_ENTITY_NAME = "Exists Entity Name";
	private static final String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";
	private static final String EXISTS_ATTRIBUTE_NAME_STRING = "Exists Attribute Name String";
	private static final String EXISTS_ATTRIBUTE_NAME_BOOLEAN = "Exists Attribute Name Boolean";

	DesignInterface designInterface;
	DataModel existingDataModel;

	Entity existsEntity;

	@Override
	public void populate4Test() {
		this.designInterface = DesignInterface.getInstance();

		Specification spec = new Specification(EXISTS_SPEC_ID, EXISTS_SPEC_NAME);
		this.existingDataModel = spec.getDataModel();

		this.existsEntity = new Entity(this.existingDataModel, EXISTS_ENTITY_NAME, false);
		Entity entityTwo = new Entity(this.existingDataModel, ENTITY_NAME, false);
		new Attribute(this.existingDataModel, this.existsEntity, EXISTS_ATTRIBUTE_NAME, AttributeType.NUMBER, true);
		new Attribute(this.existingDataModel, this.existsEntity, EXISTS_ATTRIBUTE_NAME_STRING, AttributeType.STRING,
				false);
		new Attribute(this.existingDataModel, this.existsEntity, EXISTS_ATTRIBUTE_NAME_BOOLEAN, AttributeType.BOOLEAN,
				false);

		new RelationBW(this.existingDataModel, "relation", this.existsEntity, "role1", Cardinality.ZERO_OR_ONE, false,
				entityTwo, "role2", Cardinality.ONE, false);
	}

	@Test
	public void successNewCreateComparatorRule() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
				new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"), new ExpressionDTO(EXISTS_SPEC_ID,
						ExpressionAtom.ATT_VALUE, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));

		Specification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID).get();
		assertNotNull(spec);
		assertEquals(1, this.existsEntity.getRuleSet().size());
		Rule rule = this.existsEntity.getRuleSet().stream().collect(Collectors.toList()).get(0);
		assertTrue(rule.getCondition() instanceof Comparison);
		Comparison comparison = (Comparison) rule.getCondition();
		assertEquals(Comparison.ComparisonOperator.GREATER, comparison.getComparator());
		Expression leftExpression = comparison.getLeftExpression();
		Expression righExpression = comparison.getRightExpression();

		NumberLiteral literal = (NumberLiteral) leftExpression;
		assertEquals(6, literal.getValue());

		AttributeValueExpression attValue = (AttributeValueExpression) righExpression;
		Entity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME).get();
		Attribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
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

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));

		Specification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID).get();
		assertNotNull(spec);
		assertEquals(1, this.existsEntity.getRuleSet().size());
		Rule rule = this.existsEntity.getRuleSet().stream().collect(Collectors.toList()).get(0);
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
		Attribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
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

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test(expected = BWException.class)
	public void failCauseofWrongRoleName() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								ENTITY_NAME + "." + "rolex" + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, ENTITY_NAME, RULE_NAME, expDTO));
	}

	public void failCauseofWrongInitialPath() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.INT, "6"),
						new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.ATT_VALUE,
								ENTITY_NAME + "." + "rolex" + "." + EXISTS_ATTRIBUTE_NAME)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "true"));

		try {
			this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
		}
	}

	@Test
	public void expressionWithNot() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT, new ExpressionDTO(EXISTS_SPEC_ID,
				BooleanOperator.ATT_VALUE, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME_BOOLEAN));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithNotAndAttAndDef() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.AND,
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT,
						new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.ATT_VALUE,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME_BOOLEAN)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "false"));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithNotAndAttAndNotEqual() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.NOT_EQUAL,
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.NOT,
						new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.ATT_VALUE,
								EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME_BOOLEAN)),
				new ExpressionDTO(EXISTS_SPEC_ID, BooleanOperator.BOOL, "false"));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

	@Test
	public void expressionWithGreaterAndAttributeAndStringLiteral() {
		ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, ComparisonOperator.GREATER,
				new ExpressionDTO(EXISTS_SPEC_ID, ExpressionAtom.STRING, "today"), new ExpressionDTO(EXISTS_SPEC_ID,
						ExpressionAtom.ATT_VALUE, EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME_STRING));

		this.designInterface.createRule(new RuleDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, RULE_NAME, expDTO));
	}

}
