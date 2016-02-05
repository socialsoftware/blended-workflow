package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class BoolComparison extends BoolComparison_Base {

	public BoolComparison(Condition leftCondition, Condition rightCondition, ComparisonOperator comparator) {
		setLeftCondition(leftCondition);
		setRightCondition(rightCondition);
		setComparator(comparator);
		checkConsistency();
	}

	private void checkConsistency() {
		if ((getLeftCondition() == null) || (getRightCondition() == null))
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
	}

	@Override
	public void delete() {
		getLeftCondition().delete();
		getRightCondition().delete();
		super.delete();
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Entity> getEntities() {
		return Stream.concat(getRightCondition().getEntities().stream(), getLeftCondition().getEntities().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		return Stream.concat(getRightCondition().getAttributeBasicSet().stream(),
				getLeftCondition().getAttributeBasicSet().stream()).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getPathSet() {
		return Stream.concat(getRightCondition().getPathSet().stream(), getLeftCondition().getPathSet().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public HashMap<AttributeBasic, String> getcompareConditionValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrUndefinedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existExistEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existTrue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubPath() {
		String left = getLeftCondition() != null ? getLeftCondition().getSubPath() : "NULL";
		String right = getRightCondition() != null ? getRightCondition().getSubPath() : "NULL";
		return getComparator().name() + "(" + left + "," + right + ")";
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, getComparator(), getLeftCondition().getDTO(specId),
				getRightCondition().getDTO(specId));
	}

}
