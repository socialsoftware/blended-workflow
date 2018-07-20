package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class AndCondition extends AndCondition_Base {

	public AndCondition(Condition one, Condition two) {
		setLeftCondition(one);
		setRightCondition(two);
		checkConsistency();
	}

	private void checkConsistency() {
		if ((getLeftCondition() == null) || (getRightCondition() == null)) {
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
		}
	}

	public Condition getConditionOne() {
		return getLeftCondition();
	}

	public Condition getConditionTwo() {
		return getRightCondition();
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entitiesOne = getConditionOne().getEntities();
		Set<Entity> entitiesTwo = getConditionTwo().getEntities();
		entitiesOne.addAll(entitiesTwo);
		return entitiesOne;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		Set<Attribute> attributesOne = getConditionOne().getAttributeSet();
		Set<Attribute> attributesTwo = getConditionTwo().getAttributeSet();
		attributesOne.addAll(attributesTwo);
		return attributesOne;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> pathsOne = getConditionOne().getPathSet();
		Set<Path> pathsTwo = getConditionTwo().getPathSet();
		pathsOne.addAll(pathsTwo);
		return pathsOne;
	}

	@Override
	public void delete() {
		getLeftCondition().delete();
		getRightCondition().delete();

		super.delete();
	}

	@Override
	public String getSubPath() {
		String left = getLeftCondition() != null ? getLeftCondition().getSubPath() : "NULL";
		String right = getRightCondition() != null ? getRightCondition().getSubPath() : "NULL";
		return "AND(" + right + "," + left + ")";
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
		return new ExpressionDto(specId, BooleanOperator.AND, getLeftCondition().getExpressionDTO(specId),
				getRightCondition().getExpressionDTO(specId));
	}

}
