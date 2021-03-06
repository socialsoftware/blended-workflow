package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class BoolComparison extends BoolComparison_Base {

	public BoolComparison(Condition leftCondition, Condition rightCondition, ComparisonOperator comparator) {
		setLeftCondition(leftCondition);
		setRightCondition(rightCondition);
		setComparator(comparator);
		checkConsistency();
	}

	private void checkConsistency() {
		if ((getLeftCondition() == null) || (getRightCondition() == null)) {
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
		}
	}

	@Override
	public void delete() {
		getLeftCondition().delete();
		getRightCondition().delete();
		super.delete();
	}

	@Override
	public Set<Entity> getEntities() {
		return Stream.concat(getRightCondition().getEntities().stream(), getLeftCondition().getEntities().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return Stream
				.concat(getRightCondition().getAttributeSet().stream(), getLeftCondition().getAttributeSet().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Path> getPathSet() {
		return Stream.concat(getRightCondition().getPathSet().stream(), getLeftCondition().getPathSet().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public String getSubPath() {
		String left = getLeftCondition() != null ? getLeftCondition().getSubPath() : "NULL";
		String right = getRightCondition() != null ? getRightCondition().getSubPath() : "NULL";
		return getComparator().name() + "(" + left + "," + right + ")";
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
		return new ExpressionDto(specId, getComparator(), getLeftCondition().getExpressionDTO(specId),
				getRightCondition().getExpressionDTO(specId));
	}

}
