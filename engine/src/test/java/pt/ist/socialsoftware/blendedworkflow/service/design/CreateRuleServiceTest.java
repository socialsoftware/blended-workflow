package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWNumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO.Type;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

public class CreateRuleServiceTest extends TeardownRollbackTest {
    private static final String EXISTS_SPEC_ID = "ID0";
    private static final String NEW_SPEC_ID = "ID1";
    private static final String RULE_NAME = "Rule name";
    private static final String NEW_SPEC_NAME = "Doctor Appointment";
    private static final String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String EXISTS_ENTITY_NAME = "Exists Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";
    private static final String EXISTS_ATTRIBUTE_NAME_STRING = "Exists Attribute Name String";
    private static final String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";

    AtomicDesignInterface designInterface;
    BWDataModel existingDataModel;

    @Override
    public void populate4Test() {
        designInterface = AtomicDesignInterface.getInstance();

        BWSpecification spec = new BWSpecification(EXISTS_SPEC_ID,
                EXISTS_SPEC_NAME, "author", "description", "version", "UID");
        existingDataModel = spec.getDataModel();

        BWEntity entity = new BWEntity(existingDataModel, EXISTS_ENTITY_NAME,
                false);
        BWEntity entityTwo = new BWEntity(existingDataModel, ENTITY_NAME,
                false);
        new BWAttribute(existingDataModel, entity, null, EXISTS_ATTRIBUTE_NAME,
                AttributeType.NUMBER, true, false, false);
        new BWAttribute(existingDataModel, entity, null,
                EXISTS_ATTRIBUTE_NAME_STRING, AttributeType.STRING, false,
                false, false);

        BWRelation relation = new BWRelation(existingDataModel, "relation",
                entity, "role1", Cardinality.ZERO_OR_ONE, false, entityTwo,
                "role2", Cardinality.ONE, false);
    }

    @Test
    public void successNewCreateComparatorRule() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.GREATER,
                new ExpressionDTO(existingDataModel.getExternalId(), Type.INT,
                        "6"),
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.ATT_VALUE,
                        EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));

        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(1, spec.getDataModel().getRuleSet().size());
        BWRule rule = spec.getDataModel().getRuleSet().stream()
                .collect(Collectors.toList()).get(0);
        assertTrue(rule.getCondition() instanceof Comparison);
        Comparison comparison = (Comparison) rule.getCondition();
        assertEquals(Comparison.ComparisonOperator.GREATER,
                comparison.getComparator());
        BWExpression leftExpression = comparison.getLeftExpression();
        BWExpression righExpression = comparison.getRightExpression();

        BWNumberLiteral literal = (BWNumberLiteral) leftExpression;
        assertEquals(6, literal.getValue());

        BWAttributeValueExpression attValue = (BWAttributeValueExpression) righExpression;
        BWEntity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME)
                .get();
        BWAttribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
        assertEquals(att, attValue.getAttribute());
    }

    @Test
    public void successNewCreateAndConstraint() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.AND,
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.GREATER,
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.INT, "6"),
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.ATT_VALUE,
                                EXISTS_ENTITY_NAME + "."
                                        + EXISTS_ATTRIBUTE_NAME)),
                new ExpressionDTO(existingDataModel.getExternalId(), Type.BOOL,
                        "true"));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));

        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(1, spec.getDataModel().getRuleSet().size());
        BWRule rule = spec.getDataModel().getRuleSet().stream()
                .collect(Collectors.toList()).get(0);
        assertTrue(rule.getCondition() instanceof AndCondition);
        AndCondition andCondition = (AndCondition) rule.getCondition();
        Condition leftCondition = andCondition.getLeftCondition();
        Condition rightCondition = andCondition.getRightCondition();
        assertTrue(leftCondition instanceof Comparison);
        assertTrue(rightCondition instanceof TrueCondition);

        Comparison comparison = (Comparison) leftCondition;
        assertEquals(Comparison.ComparisonOperator.GREATER,
                comparison.getComparator());
        BWExpression leftExpression = comparison.getLeftExpression();
        BWExpression righExpression = comparison.getRightExpression();

        BWNumberLiteral literal = (BWNumberLiteral) leftExpression;
        assertEquals(6, literal.getValue());

        BWAttributeValueExpression attValue = (BWAttributeValueExpression) righExpression;
        BWEntity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME)
                .get();
        BWAttribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
        assertEquals(att, attValue.getAttribute());
    }

    @Test(expected = BWException.class)
    public void failCauseofWrongAttributeName() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.AND,
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.GREATER,
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.INT, "6"),
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.ATT_VALUE,
                                EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME
                                        + "x")),
                new ExpressionDTO(existingDataModel.getExternalId(), Type.BOOL,
                        "true"));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

    @Test(expected = BWException.class)
    public void failCauseofWrongRoleName() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.AND,
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.GREATER,
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.INT, "6"),
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.ATT_VALUE,
                                ENTITY_NAME + "." + "rolex" + "."
                                        + EXISTS_ATTRIBUTE_NAME)),
                new ExpressionDTO(existingDataModel.getExternalId(), Type.BOOL,
                        "true"));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

    @Test
    public void expressionWithNotAndDef() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.NOT,
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.ATT_DEF,
                        EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

    @Test
    public void expressionWithNotAndAndDef() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.AND,
                new ExpressionDTO(existingDataModel.getExternalId(), Type.NOT,
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.ATT_DEF,
                                EXISTS_ENTITY_NAME + "."
                                        + EXISTS_ATTRIBUTE_NAME)),
                new ExpressionDTO(existingDataModel.getExternalId(), Type.BOOL,
                        "false"));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

    @Test
    public void expressionWithNotAndDefAndNotEqual() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.NOT_EQUAL,
                new ExpressionDTO(existingDataModel.getExternalId(), Type.NOT,
                        new ExpressionDTO(existingDataModel.getExternalId(),
                                Type.ATT_DEF,
                                EXISTS_ENTITY_NAME + "."
                                        + EXISTS_ATTRIBUTE_NAME)),
                new ExpressionDTO(existingDataModel.getExternalId(), Type.BOOL,
                        "false"));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

    @Test
    public void expressionWithGreaterAndAttributeAndStringLiteral() {
        ExpressionDTO expDTO = new ExpressionDTO(
                existingDataModel.getExternalId(), Type.GREATER,
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.STRING, "today"),
                new ExpressionDTO(existingDataModel.getExternalId(),
                        Type.ATT_VALUE, EXISTS_ENTITY_NAME + "."
                                + EXISTS_ATTRIBUTE_NAME_STRING));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, RULE_NAME, expDTO));
    }

}
