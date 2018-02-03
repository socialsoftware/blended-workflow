package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

public class OrCondition extends OrCondition_Base {

	public OrCondition(Condition one, Condition two) {
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
		attributesOne.addAll(getConditionTwo().getAttributeSet());
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
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> attributesOne = getConditionOne().getcompareConditionValues();
		HashMap<Attribute, String> attributesTwo = getConditionTwo().getcompareConditionValues();
		attributesOne.putAll(attributesTwo);
		return attributesOne;
	}

	@Override
	public String getRdrUndefinedCondition() {
		return "(" + getConditionOne().getRdrUndefinedCondition() + " & " + getConditionTwo().getRdrUndefinedCondition()
				+ ")";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "(" + getConditionOne().getRdrSkippedCondition() + " & " + getConditionTwo().getRdrSkippedCondition()
				+ ")";
	}

	@Override
	public String getRdrTrueCondition() {
		return "(" + getConditionOne().getRdrTrueCondition() + " | " + getConditionTwo().getRdrTrueCondition() + ")";
	}

	@Override
	public String getRdrFalseCondition() {
		return "(" + getConditionOne().getRdrFalseCondition() + " | " + getConditionTwo().getRdrFalseCondition() + ")";
	}

	@Override
	public String toString() {
		return getConditionOne().toString() + " or " + getConditionTwo().toString();
	}

	@Override
	public Boolean existExistEntity() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
	}

	@Override
	public Boolean existTrue() {
		return getConditionOne().existExistEntity() || getConditionTwo().existExistEntity();
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
		return "OR(" + left + "," + right + ")";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.OR, getLeftCondition().getExpressionDTO(specId),
				getRightCondition().getExpressionDTO(specId));
	}

}