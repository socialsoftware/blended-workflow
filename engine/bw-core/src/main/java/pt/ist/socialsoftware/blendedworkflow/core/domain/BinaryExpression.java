package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDTO;

public class BinaryExpression extends BinaryExpression_Base {

	public static enum BinaryOperator {
		PLUS, MINUS, MUL, DIV
	}

	public BinaryExpression(Expression leftExpression, Expression rightExpression, BinaryOperator operator) {
		setLeftExpression(leftExpression);
		setRightExpression(rightExpression);
		setOperator(operator);
		checkConsistency();
	}

	private void checkConsistency() {
		if ((getLeftExpression() == null) || (getRightExpression() == null)) {
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getOperator().name());
		}

		if (!getLeftExpression().getType().equals(getRightExpression().getType())) {
			throw new BWException(BWErrorType.INCONSISTENT_TYPE, getOperator().name());
		}

		if (!getLeftExpression().getType().equals(AttributeType.NUMBER)) {
			throw new BWException(BWErrorType.INCONSISTENT_TYPE, getOperator().name());
		}
	}

	@Override
	public void delete() {
		getLeftExpression().delete();
		getRightExpression().delete();
		super.delete();
	}

	@Override
	public AttributeType getType() {
		checkConsistency();

		return getLeftExpression().getType();
	}

	@Override
	public String getSubPath() {
		String left = getLeftExpression() != null ? getLeftExpression().getSubPath() : "NULL";
		String right = getRightExpression() != null ? getRightExpression().getSubPath() : "NULL";
		return getOperator().name() + "(" + left + "," + right + ")";
	}

	@Override
	public Set<Attribute> getAttributes() {
		return Stream
				.concat(getRightExpression().getAttributes().stream(), getLeftExpression().getAttributes().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> pathsOne = getRightExpression().getPathSet();
		Set<Path> pathsTwo = getLeftExpression().getPathSet();
		pathsOne.addAll(pathsTwo);

		return pathsOne;
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, getOperator(), getLeftExpression().getDTO(specId),
				getRightExpression().getDTO(specId));
	}

}
