package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeBoolCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BinaryExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BoolComparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Expression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.FalseCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.NumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.core.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.StringLiteral;
import pt.ist.socialsoftware.blendedworkflow.core.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BinaryExpression.BinaryOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Condition.BooleanOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Expression.ExpressionAtom;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionDTO {
	private static Logger logger = LoggerFactory.getLogger(ExpressionDTO.class);

	private enum Type {
		AND, OR, NOT, EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL, PLUS, MINUS, MUL, DIV, ATT_VALUE, STRING, INT, BOOL;

	}

	public static boolean isBoolExp(Type type) {
		Set<Type> bools = new HashSet<>(Arrays.asList(Type.AND, Type.OR, Type.NOT, Type.EQUAL, Type.NOT_EQUAL,
				Type.GREATER, Type.GREATER_EQUAL, Type.SMALLER, Type.SMALLER_EQUAL, Type.BOOL));
		return bools.contains(type);
	}

	private String specId;
	private String type;
	// when unary value
	private String value;
	// when unary not
	private ExpressionDTO unaryExpression;
	// when binary
	private ExpressionDTO leftExpression;
	private ExpressionDTO rightExpression;

	public ExpressionDTO() {
	}

	public ExpressionDTO(String specId, ComparisonOperator comparator, ExpressionDTO leftExpDTO,
			ExpressionDTO righExpDTO) {
		this.specId = specId;
		this.type = getTypeForComparisonOperator(comparator);
		this.value = null;
		this.unaryExpression = null;
		this.leftExpression = leftExpDTO;
		this.rightExpression = righExpDTO;
	}

	public ExpressionDTO(String specId, BinaryOperator operator, ExpressionDTO leftExpDTO, ExpressionDTO righExpDTO) {
		this.specId = specId;
		this.type = getTypeForBinaryOperator(operator);
		this.value = null;
		this.unaryExpression = null;
		this.leftExpression = leftExpDTO;
		this.rightExpression = righExpDTO;
	}

	public ExpressionDTO(String specId, BooleanOperator operator, ExpressionDTO leftExpDTO, ExpressionDTO righExpDTO) {
		this.specId = specId;
		this.type = getTypeForBooleanOperator(operator);
		this.value = null;
		this.unaryExpression = null;
		this.leftExpression = leftExpDTO;
		this.rightExpression = righExpDTO;
	}

	public ExpressionDTO(String specId, BooleanOperator operator, String path) {
		this.specId = specId;
		this.type = getTypeForBooleanOperator(operator);
		this.value = path;
		this.unaryExpression = null;
		this.leftExpression = null;
		this.rightExpression = null;
	}

	public ExpressionDTO(String specId, ExpressionAtom operator, String path) {
		this.specId = specId;
		this.type = getTypeForExpressionAtom(operator);
		this.value = path;
		this.unaryExpression = null;
		this.leftExpression = null;
		this.rightExpression = null;
	}

	public ExpressionDTO(String specId, BooleanOperator operator, ExpressionDTO expDTO) {
		this.specId = specId;
		this.type = getTypeForBooleanOperator(operator);
		this.value = null;
		this.unaryExpression = expDTO;
		this.leftExpression = null;
		this.rightExpression = null;
	}

	private String getTypeForExpressionAtom(ExpressionAtom operator) {
		switch (operator) {
		case ATT_VALUE:
			return Type.ATT_VALUE.toString();
		case INT:
			return Type.INT.toString();
		case STRING:
			return Type.STRING.toString();
		default:
			assert false;
			return null;
		}
	}

	private String getTypeForBooleanOperator(BooleanOperator operator) {
		switch (operator) {
		case AND:
			return Type.AND.name();
		case NOT:
			return Type.NOT.name();
		case OR:
			return Type.OR.name();
		case ATT_VALUE:
			return Type.ATT_VALUE.name();
		case BOOL:
			return Type.BOOL.name();
		default:
			assert false;
			return null;
		}
	}

	private String getTypeForComparisonOperator(ComparisonOperator comparator) {
		switch (comparator) {
		case EQUAL:
			return Type.EQUAL.name();
		case GREATER:
			return Type.GREATER.name();
		case GREATER_EQUAL:
			return Type.GREATER_EQUAL.name();
		case NOT_EQUAL:
			return Type.NOT_EQUAL.name();
		case SMALLER:
			return Type.SMALLER.name();
		case SMALLER_EQUAL:
			return Type.SMALLER_EQUAL.name();
		default:
			assert false;
			return null;
		}
	}

	private String getTypeForBinaryOperator(BinaryOperator operator) {
		switch (operator) {
		case DIV:
			return Type.DIV.name();
		case MINUS:
			return Type.MINUS.name();
		case MUL:
			return Type.MUL.name();
		case PLUS:
			return Type.PLUS.name();
		default:
			assert false;
			return null;
		}
	}

	@Override
	public String toString() {
		if (this.value != null)
			return getType() + "(" + getValue() + ")";
		else if (this.unaryExpression != null)
			return getType() + "(" + getUnaryExpression().toString() + ")";
		else
			return "(" + getLeftExpression().toString() + " " + getType() + " " + getRightExpression().toString() + ")";
	}

	public Condition buildCondition(Specification spec) {
		switch (Type.valueOf(this.getType())) {
		case AND:
			return new AndCondition(getLeftExpression().buildCondition(spec),
					getRightExpression().buildCondition(spec));
		case OR:
			return new OrCondition(getLeftExpression().buildCondition(spec), getRightExpression().buildCondition(spec));
		case NOT:
			return new NotCondition(getUnaryExpression().buildCondition(spec));
		case ATT_VALUE:
			return new AttributeBoolCondition(spec, getValue());
		case EQUAL:
			if (ExpressionDTO.isBoolExp(Type.valueOf(getLeftExpression().getType())))
				return new BoolComparison(getLeftExpression().buildCondition(spec),
						getRightExpression().buildCondition(spec), ComparisonOperator.EQUAL);
			else
				return new Comparison(getLeftExpression().buildExpression(spec),
						getRightExpression().buildExpression(spec), ComparisonOperator.EQUAL);
		case NOT_EQUAL:
			if (ExpressionDTO.isBoolExp(Type.valueOf(getLeftExpression().getType())))
				return new BoolComparison(getLeftExpression().buildCondition(spec),
						getRightExpression().buildCondition(spec), ComparisonOperator.NOT_EQUAL);
			else
				return new Comparison(getLeftExpression().buildExpression(spec),
						getRightExpression().buildExpression(spec), ComparisonOperator.NOT_EQUAL);
		case GREATER:
			return new Comparison(getLeftExpression().buildExpression(spec), getRightExpression().buildExpression(spec),
					ComparisonOperator.GREATER);
		case GREATER_EQUAL:
			return new Comparison(getLeftExpression().buildExpression(spec), getRightExpression().buildExpression(spec),
					ComparisonOperator.GREATER_EQUAL);
		case SMALLER:
			return new Comparison(getLeftExpression().buildExpression(spec), getRightExpression().buildExpression(spec),
					ComparisonOperator.SMALLER);
		case SMALLER_EQUAL:
			return new Comparison(getLeftExpression().buildExpression(spec), getRightExpression().buildExpression(spec),
					ComparisonOperator.SMALLER_EQUAL);
		case BOOL:
			if (getValue().toLowerCase().equals("true"))
				return new TrueCondition();
			if (getValue().toLowerCase().equals("false"))
				return new FalseCondition();
			assert (false);
			return null;
		default:
			assert (false);
			return null;
		}
	}

	private Expression buildExpression(Specification spec) {
		switch (Type.valueOf(this.getType())) {
		case PLUS:
			return new BinaryExpression(getLeftExpression().buildExpression(spec),
					getRightExpression().buildExpression(spec), BinaryOperator.PLUS);
		case MINUS:
			return new BinaryExpression(getLeftExpression().buildExpression(spec),
					getRightExpression().buildExpression(spec), BinaryOperator.MINUS);
		case MUL:
			return new BinaryExpression(getLeftExpression().buildExpression(spec),
					getRightExpression().buildExpression(spec), BinaryOperator.MUL);
		case DIV:
			return new BinaryExpression(getLeftExpression().buildExpression(spec),
					getRightExpression().buildExpression(spec), BinaryOperator.DIV);
		case ATT_VALUE:
			return new AttributeValueExpression(spec, getValue());
		case INT:
			return new NumberLiteral(Integer.parseInt(getValue()));
		case STRING:
			return new StringLiteral(getValue());
		default:
			assert (false);
			return null;
		}
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecIdId(String specId) {
		this.specId = specId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ExpressionDTO getUnaryExpression() {
		return unaryExpression;
	}

	public void setUnaryExpression(ExpressionDTO unaryExpression) {
		this.unaryExpression = unaryExpression;
	}

	public ExpressionDTO getLeftExpression() {
		return leftExpression;
	}

	public void setLeftExpresssion(ExpressionDTO leftExpresssion) {
		this.leftExpression = leftExpresssion;
	}

	public ExpressionDTO getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(ExpressionDTO rightExpression) {
		this.rightExpression = rightExpression;
	}

}
