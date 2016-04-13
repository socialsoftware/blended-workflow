package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class TrueCondition extends TrueCondition_Base {

	public TrueCondition() {
	}

	@Override
	Condition cloneCondition(OldGoalModelInstance goalModelInstance) {
		return new TrueCondition();
	}

	@Override
	Condition cloneCondition(OldTaskModelInstance taskModelInstance) {
		return new TrueCondition();
	}

	@Override
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
	}

	@Override
	void assignAttributeInstances(OldTaskWorkItem taskWorkItem, ConditionType conditionType) {
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<Entity>();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<Attribute>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<Path>();
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}

	@Override
	public String getRdrUndefinedCondition() {
		return "True";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "True";
	}

	@Override
	public String getRdrTrueCondition() {
		return "True";
	}

	@Override
	public String getRdrFalseCondition() {
		return "True";
	}

	@Override
	public String toString() {
		return "True";
	}

	@Override
	public Boolean existExistEntity() {
		return false;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluate(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		return TripleStateBool.TRUE;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		return TripleStateBool.TRUE;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(OldEntityInstance entityInstance, OldGoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		return TripleStateBool.TRUE;
	}

	@Override
	public Boolean existTrue() {
		return true;
	}

	@Override
	public String getSubPath() {
		return "true";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.BOOL, "true");
	}

}
