package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

public class Comparison extends Comparison_Base {

	private static Logger log = LoggerFactory.getLogger(Comparison.class);

	public static enum ComparisonOperator {
		EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL
	}

	public Comparison(Expression leftExpression, Expression rightExpression, ComparisonOperator comparator) {
		setLeftExpression(leftExpression);
		setRightExpression(rightExpression);
		setComparator(comparator);
		checkConsistency();
	}

	private void checkConsistency() {
		log.debug("type:{}, left expression:{}, right expression:{}", getComparator(), getLeftExpression(),
				getRightExpression());
		if ((getLeftExpression() == null) || (getRightExpression() == null)) {
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
		}

		if (!getLeftExpression().getType().equals(getRightExpression().getType())) {
			throw new BWException(BWErrorType.INCONSISTENT_TYPE, getSubPath());
		}
	}

	public Comparison(Attribute attribute, String operator, String value) {
		setAttributeOfComparison(attribute);
		setOperator(operator);
		setValue(value);
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<>();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		if (getAttributeOfComparison() != null) {
			Set<Attribute> attributes = new HashSet<>();
			attributes.add(getAttributeOfComparison());
			return attributes;
		} else {
			return Stream
					.concat(getRightExpression().getAttributes().stream(), getLeftExpression().getAttributes().stream())
					.collect(Collectors.toSet());
		}
	}

	@Override
	public Set<Path> getPathSet() {
		if (getAttributeOfComparison() != null) {
			Set<Path> attributes = new HashSet<>();
			attributes.add(new Path(getDataModel(),
					getAttributeOfComparison().getEntity().getName() + "." + getAttributeOfComparison().getName()));
			return attributes;
		} else {
			return Stream.concat(getRightExpression().getPathSet().stream(), getLeftExpression().getPathSet().stream())
					.collect(Collectors.toSet());
		}
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> result = new HashMap<>();
		result.put(getAttributeOfComparison(), getValue());
		return result;
	}

	@Override
	public String getRdrUndefinedCondition() {
		String condition = "(";
		String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
		String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
		String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String attributeName = getAttributeOfComparison().getName().replaceAll(" ", "");
		String entityName = getAttributeOfComparison().getEntity().getName().replaceAll(" ", "");
		String value = getValue();
		if (getValue().equals("$TODAY$")) {
			value = "" + "$TODAY$".hashCode();
		}

		condition += entityName + "_" + attributeName + " " + getOperator() + " " + value + ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(!" + getRdrTrueCondition() + ")";
	}

	@Override
	public String toString() {
		return getSubPath();
	}

	@Override
	public Boolean existExistEntity() {
		return false;
	}

	@Override
	public Boolean existTrue() {
		return false;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return true;
	}

	@Override
	public void delete() {
		setAttributeOfComparison(null);
		if (getLeftExpression() != null) {
			getLeftExpression().delete();
		}
		if (getRightExpression() != null) {
			getRightExpression().delete();
		}
		super.delete();

	}

	@Override
	public String getSubPath() {
		String left = getLeftExpression() != null ? getLeftExpression().getSubPath() : "NULL";
		String right = getRightExpression() != null ? getRightExpression().getSubPath() : "NULL";
		return getComparator() + "(" + left + "," + right + ")";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, getComparator(), getLeftExpression().getDTO(specId),
				getRightExpression().getDTO(specId));
	}

}
