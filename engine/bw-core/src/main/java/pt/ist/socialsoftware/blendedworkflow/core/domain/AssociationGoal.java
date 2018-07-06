package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class AssociationGoal extends AssociationGoal_Base {

	public AssociationGoal(GoalModel goalModel, String name, Set<MulCondition> mulConditionSet) {
		setGoalModel(goalModel);
		setName(name);

		for (MulCondition mulCondition : mulConditionSet) {
			addEntityInvariantCondition(mulCondition);
		}
	}

	public Goal initAssociationGoal() {
		applyActivationConditionsForAssociationGoal();

		return this;
	}

	@Override
	public void checkType() {
		if (!getSuccessConditionSet().isEmpty() || !getAttributeInvariantConditionSet().isEmpty()) {
			throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL,
					"association goal has success condition: " + getName());
		}
	}

	@Override
	public void delete() {
		getEntityInvariantConditionSet().stream().forEach(mul -> removeEntityInvariantCondition(mul));

		super.delete();
	}
}
