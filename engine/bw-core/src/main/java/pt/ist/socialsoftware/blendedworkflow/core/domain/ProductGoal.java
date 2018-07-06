package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ProductGoal extends ProductGoal_Base {

	public ProductGoal(GoalModel goalModel, String name, Set<DefProductCondition> defProductConditionSet) {
		setGoalModel(goalModel);
		setName(name);

		for (DefProductCondition defProductCondition : defProductConditionSet) {
			addSuccessCondition(defProductCondition);
		}
	}

	public Goal initProductGoal() {
		applyActivationConditionsForProductGoal();
		applyRuleConditions();

		return this;
	}

	@Override
	public void checkType() {
		if (!getEntityInvariantConditionSet().isEmpty()) {
			throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "product goal has mul conditions: " + getName());
		}
	}

	@Override
	public void delete() {
		getSuccessConditionSet().stream().forEach(suc -> removeSuccessCondition(suc));
		getAttributeInvariantConditionSet().stream().forEach(rule -> removeAttributeInvariantCondition(rule));

		super.delete();
	}

}
