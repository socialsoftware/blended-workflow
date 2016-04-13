package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class AndCondition extends AndCondition_Base {

	public AndCondition(Condition one, Condition two) {
		setLeftCondition(one);
		setRightCondition(two);
		checkConsistency();
	}

	private void checkConsistency() {
		if ((getLeftCondition() == null) || (getRightCondition() == null))
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
	}

	public Condition getConditionOne() {
		return getLeftCondition();
	}

	public Condition getConditionTwo() {
		return getRightCondition();
	}

	@Override
	Condition cloneCondition(OldGoalModelInstance goalModelInstance) {
		return new AndCondition(getConditionOne().cloneCondition(goalModelInstance),
				getConditionTwo().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(OldTaskModelInstance taskModelInstance) {
		return new AndCondition(getConditionOne().cloneCondition(taskModelInstance),
				getConditionTwo().cloneCondition(taskModelInstance));
	}

	@Override
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		getConditionOne().assignAttributeInstances(goalWorkItem, conditionType);
		getConditionTwo().assignAttributeInstances(goalWorkItem, conditionType);
	}

	@Override
	void assignAttributeInstances(OldTaskWorkItem taskWorkItem, ConditionType conditionType) {
		getConditionOne().assignAttributeInstances(taskWorkItem, conditionType);
		getConditionTwo().assignAttributeInstances(taskWorkItem, conditionType);
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
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> attributesOne = getConditionOne().getcompareConditionValues();
		HashMap<Attribute, String> attributesTwo = getConditionTwo().getcompareConditionValues();
		attributesOne.putAll(attributesTwo);
		return attributesOne;
	}

	@Override
	public String getRdrUndefinedCondition() {
		return "(" + getConditionOne().getRdrUndefinedCondition() + " | " + getConditionTwo().getRdrUndefinedCondition()
				+ ")";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "(" + getConditionOne().getRdrSkippedCondition() + " | " + getConditionTwo().getRdrSkippedCondition()
				+ ")";
	}

	@Override
	public String getRdrTrueCondition() {
		return "(" + getConditionOne().getRdrTrueCondition() + " & " + getConditionTwo().getRdrTrueCondition() + ")";
	}

	@Override
	public String getRdrFalseCondition() {
		return "(" + getConditionOne().getRdrFalseCondition() + " & " + getConditionTwo().getRdrFalseCondition() + ")";
	}

	@Override
	public String toString() {
		return getConditionOne().toString() + " and " + getConditionTwo().toString();
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

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluate(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getConditionOne().evaluate(goalWorkItem, conditionType)
				.AND(getConditionTwo().evaluate(goalWorkItem, conditionType));
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		return getConditionOne().evaluateWithWorkItem(goalWorkItem, conditionType)
				.AND(getConditionTwo().evaluateWithWorkItem(goalWorkItem, conditionType));
	}

	@Override
	public TripleStateBool evaluateWithDataModel(OldEntityInstance entityInstance, OldGoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		return getConditionOne().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType)
				.AND(getConditionTwo().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType));

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
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.AND, getLeftCondition().getExpressionDTO(specId),
				getRightCondition().getExpressionDTO(specId));
	}

}
