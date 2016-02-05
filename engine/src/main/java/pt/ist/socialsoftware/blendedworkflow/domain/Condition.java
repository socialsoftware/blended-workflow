package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public abstract class Condition extends Condition_Base {

	public enum BooleanOperator {
		AND, OR, NOT, ATT_VALUE, BOOL, PATH_DEF
	};

	public enum ConditionType {
		PRE_CONDITION, POS_CONDITION, ACTIVATE_CONDITION, SUCESS_CONDITION, MAINTAIN_CONDITION
	};

	public enum ConditionResult {
		TRUE, FALSE, SKIPPED
	};

	public Condition and(Condition one, Condition other) {
		return new AndCondition(one, other);
	}

	public Condition or(Condition one, Condition other) {
		return new OrCondition(one, other);
	}

	public Condition not(Condition condition) {
		return new NotCondition(condition);
	}

	abstract Condition cloneCondition(GoalModelInstance goalModelInstance);

	abstract Condition cloneCondition(TaskModelInstance taskModelInstance);

	public abstract void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType);

	abstract void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType);

	public abstract Set<Entity> getEntities();

	public abstract Set<AttributeBasic> getAttributeBasicSet();

	public abstract HashMap<AttributeBasic, String> getcompareConditionValues();

	public abstract String getRdrUndefinedCondition();

	public abstract String getRdrSkippedCondition();

	public abstract String getRdrTrueCondition();

	public abstract String getRdrFalseCondition();

	@Override
	public abstract String toString();

	public abstract Boolean existExistEntity();

	public abstract Boolean existCompareAttributeToValue();

	public abstract Boolean existTrue();

	/******************************
	 * Evaluate
	 ******************************/
	public abstract TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType);

	public abstract TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType);

	public abstract TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType);

	public void delete() {
		setRule(null);

		setAndLeftCondition(null);
		setAndRightCondition(null);
		setOrLeftCondition(null);
		setOrRightCondition(null);
		setNotCondition(null);
		setLeftBoolComparison(null);
		setRightBoolComparison(null);

		deleteDomainObject();
	}

	public DataModel getDataModel() {
		if (getRule() != null)
			return getRule().getDataModel();
		if (getAndLeftCondition() != null)
			return getAndLeftCondition().getDataModel();
		if (getAndRightCondition() != null)
			return getAndLeftCondition().getDataModel();
		if (getOrLeftCondition() != null)
			return getOrLeftCondition().getDataModel();
		if (getOrRightCondition() != null)
			return getOrLeftCondition().getDataModel();
		if (getNotCondition() != null)
			return getNotCondition().getDataModel();
		assert (false);
		return null;
	}

	public abstract String getSubPath();

	public abstract ExpressionDTO getDTO(String specId);

	public abstract Set<String> getPathSet();

}
