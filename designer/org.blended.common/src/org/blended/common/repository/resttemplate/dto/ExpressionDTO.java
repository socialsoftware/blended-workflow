package org.blended.common.repository.resttemplate.dto;

import org.blended.common.common.And;
import org.blended.common.common.AttributeValue;
import org.blended.common.common.BoolConstant;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.Div;
import org.blended.common.common.Equal;
import org.blended.common.common.Expression;
import org.blended.common.common.Greater;
import org.blended.common.common.GreaterEqual;
import org.blended.common.common.IntConstant;
import org.blended.common.common.Minus;
import org.blended.common.common.Mul;
import org.blended.common.common.Not;
import org.blended.common.common.NotEqual;
import org.blended.common.common.Or;
import org.blended.common.common.PathDefinition;
import org.blended.common.common.Plus;
import org.blended.common.common.Smaller;
import org.blended.common.common.SmallerEqual;
import org.blended.common.common.StringConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionDTO {
	private static Logger logger = LoggerFactory.getLogger(ExpressionDTO.class);

	// copy from the equivalent in engine ExpressionDTO
	public enum Type {
		AND("AND"), OR("OR"), NOT("NOT"), PATH_DEF("DEF"), EQUAL("=="), NOT_EQUAL("!="), GREATER(">"), GREATER_EQUAL(
				">="), SMALLER("<"), SMALLER_EQUAL("<="), PLUS("+"), MINUS("-"), MUL("*"), DIV("/"), ATT_VALUE(
						"ATT_VALUE"), STRING("String"), INT("int"), BOOL("boolean");

		private String value;

		private Type(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
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

	public ExpressionDTO(String specId, Type type, ExpressionDTO leftExpresssion, ExpressionDTO rightExpression) {
		this.specId = specId;
		this.type = type.name();
		this.value = null;
		this.unaryExpression = null;
		this.leftExpression = leftExpresssion;
		this.rightExpression = rightExpression;
	}

	public ExpressionDTO(String specId, Type type, ExpressionDTO unaryExpression) {
		this.specId = specId;
		this.type = type.name();
		this.value = null;
		this.unaryExpression = unaryExpression;
		this.leftExpression = null;
		this.rightExpression = null;
	}

	public ExpressionDTO(String specId, Type type, String value) {
		this.specId = specId;
		this.type = type.name();
		this.value = value;
		this.unaryExpression = null;
		this.leftExpression = null;
		this.rightExpression = null;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
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

	public void setLeftExpression(ExpressionDTO leftExpression) {
		this.leftExpression = leftExpression;
	}

	public ExpressionDTO getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(ExpressionDTO rightExpression) {
		this.rightExpression = rightExpression;
	}

	@Override
	public String toString() {
		if (this.value != null)
			return getType() + "(" + getValue() + ")";
		else if (this.unaryExpression != null)
			return getType() + "(" + getUnaryExpression().toString() + ")";
		else
			return getType() + "(" + getLeftExpression().toString() + "," + getRightExpression().toString() + ")";
	}

	public static ExpressionDTO buildExpressionDTO(String specId, Expression expression) {
		if (expression instanceof And) {
			And andExpression = (And) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.AND,
					buildExpressionDTO(specId, andExpression.getLeft()),
					buildExpressionDTO(specId, andExpression.getRight()));
		} else if (expression instanceof Or) {
			Or orExpression = (Or) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.OR, buildExpressionDTO(specId, orExpression.getLeft()),
					buildExpressionDTO(specId, orExpression.getRight()));
		} else if (expression instanceof Not) {
			Not notExpression = (Not) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.NOT,
					buildExpressionDTO(specId, notExpression.getExpression()));
		} else if (expression instanceof PathDefinition) {
			PathDefinition defExpression = (PathDefinition) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.PATH_DEF, defExpression.getPath());
		} else if (expression instanceof Equal) {
			Equal equalExpression = (Equal) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.EQUAL,
					buildExpressionDTO(specId, equalExpression.getLeft()),
					buildExpressionDTO(specId, equalExpression.getRight()));
		} else if (expression instanceof NotEqual) {
			NotEqual notEqualExpression = (NotEqual) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.NOT_EQUAL,
					buildExpressionDTO(specId, notEqualExpression.getLeft()),
					buildExpressionDTO(specId, notEqualExpression.getRight()));
		} else if (expression instanceof Greater) {
			Greater greaterExpression = (Greater) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.GREATER,
					buildExpressionDTO(specId, greaterExpression.getLeft()),
					buildExpressionDTO(specId, greaterExpression.getRight()));
		} else if (expression instanceof GreaterEqual) {
			GreaterEqual greaterEqualExpression = (GreaterEqual) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.GREATER_EQUAL,
					buildExpressionDTO(specId, greaterEqualExpression.getLeft()),
					buildExpressionDTO(specId, greaterEqualExpression.getRight()));
		} else if (expression instanceof Smaller) {
			Smaller smallerExpression = (Smaller) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.SMALLER,
					buildExpressionDTO(specId, smallerExpression.getLeft()),
					buildExpressionDTO(specId, smallerExpression.getRight()));
		} else if (expression instanceof SmallerEqual) {
			SmallerEqual smallerEqualExpression = (SmallerEqual) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.SMALLER_EQUAL,
					buildExpressionDTO(specId, smallerEqualExpression.getLeft()),
					buildExpressionDTO(specId, smallerEqualExpression.getRight()));
		} else if (expression instanceof Plus) {
			Plus castedExpression = (Plus) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.PLUS,
					buildExpressionDTO(specId, castedExpression.getLeft()),
					buildExpressionDTO(specId, castedExpression.getRight()));
		} else if (expression instanceof Minus) {
			Minus castedExpression = (Minus) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.MINUS,
					buildExpressionDTO(specId, castedExpression.getLeft()),
					buildExpressionDTO(specId, castedExpression.getRight()));
		} else if (expression instanceof Mul) {
			Mul castedExpression = (Mul) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.MUL,
					buildExpressionDTO(specId, castedExpression.getLeft()),
					buildExpressionDTO(specId, castedExpression.getRight()));
		} else if (expression instanceof Div) {
			Div castedExpression = (Div) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.DIV,
					buildExpressionDTO(specId, castedExpression.getLeft()),
					buildExpressionDTO(specId, castedExpression.getRight()));
		} else if (expression instanceof AttributeValue) {
			AttributeValue attValue = (AttributeValue) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.ATT_VALUE, attValue.getName());
		} else if (expression instanceof StringConstant) {
			StringConstant castedExpression = (StringConstant) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.STRING, castedExpression.getName());
		} else if (expression instanceof IntConstant) {
			IntConstant castedExpression = (IntConstant) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.INT, String.valueOf(castedExpression.getName()));
		} else if (expression instanceof BoolConstant) {
			BoolConstant castedExpression = (BoolConstant) expression;
			return new ExpressionDTO(specId, ExpressionDTO.Type.BOOL, castedExpression.getName());
		}
		assert (false);
		return null;
	}

	public Expression buildExpression() {
		// logger.debug("buildExpression() type:{}, value:{}", getType(),
		// getValue());
		CommonFactory factory = CommonFactory.eINSTANCE;
		switch (Type.valueOf(getType())) {
		case AND:
			And and = factory.createAnd();
			and.setLeft(getLeftExpression().buildExpression());
			and.setRight(getRightExpression().buildExpression());
			return and;
		case OR:
			Or or = factory.createOr();
			or.setLeft(getLeftExpression().buildExpression());
			or.setRight(getRightExpression().buildExpression());
			return or;
		case NOT:
			Not not = factory.createNot();
			not.setExpression(getUnaryExpression().buildExpression());
			return not;
		case EQUAL:
			Equal equal = factory.createEqual();
			equal.setLeft(getLeftExpression().buildExpression());
			equal.setRight(getRightExpression().buildExpression());
			return equal;
		case NOT_EQUAL:
			NotEqual notEqual = factory.createNotEqual();
			notEqual.setLeft(getLeftExpression().buildExpression());
			notEqual.setRight(getRightExpression().buildExpression());
			return notEqual;
		case GREATER:
			Greater greater = factory.createGreater();
			greater.setLeft(getLeftExpression().buildExpression());
			greater.setRight(getRightExpression().buildExpression());
			return greater;
		case GREATER_EQUAL:
			GreaterEqual greaterEqual = factory.createGreaterEqual();
			greaterEqual.setLeft(getLeftExpression().buildExpression());
			greaterEqual.setRight(getRightExpression().buildExpression());
			return greaterEqual;
		case SMALLER:
			Smaller smaller = factory.createSmaller();
			smaller.setLeft(getLeftExpression().buildExpression());
			smaller.setRight(getRightExpression().buildExpression());
			return smaller;
		case SMALLER_EQUAL:
			SmallerEqual smallerEqual = factory.createSmallerEqual();
			smallerEqual.setLeft(getLeftExpression().buildExpression());
			smallerEqual.setRight(getRightExpression().buildExpression());
			return smallerEqual;
		case ATT_VALUE:
			AttributeValue attributeValue = factory.createAttributeValue();
			attributeValue.setName(getValue());
			return attributeValue;
		case PLUS:
			Plus plus = factory.createPlus();
			plus.setLeft(getLeftExpression().buildExpression());
			plus.setRight(getRightExpression().buildExpression());
			return plus;
		case MINUS:
			Minus minus = factory.createMinus();
			minus.setLeft(getLeftExpression().buildExpression());
			minus.setRight(getRightExpression().buildExpression());
			return minus;
		case MUL:
			Mul mul = factory.createMul();
			mul.setLeft(getLeftExpression().buildExpression());
			mul.setRight(getRightExpression().buildExpression());
			return mul;
		case DIV:
			Div div = factory.createDiv();
			div.setLeft(getLeftExpression().buildExpression());
			div.setRight(getRightExpression().buildExpression());
			return div;
		case INT:
			IntConstant intConstant = factory.createIntConstant();
			intConstant.setName(Integer.parseInt(getValue()));
			return intConstant;
		case STRING:
			StringConstant stringConstant = factory.createStringConstant();
			stringConstant.setName(getValue());
			return stringConstant;
		case BOOL:
			BoolConstant boolConstant = factory.createBoolConstant();
			boolConstant.setName(getValue());
			return boolConstant;
		default:
			assert false;
			return null;
		}
	}

}
