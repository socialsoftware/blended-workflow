package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class AssociationGoal extends AssociationGoal_Base {
	@Override
	public void addSuccessCondition(DefProductCondition defProductCondition) {
		throw new BWException(BWErrorType.INCONSISTENT_ASSOCIATION_GOAL,
				"An association goal cannot have success conditions");
	}

	public AssociationGoal(GoalModel goalModel, String name, Set<MulCondition> mulConditionSet) {
		setGoalModel(goalModel);
		setName(name);

		for (MulCondition mulCondition : mulConditionSet) {
			addEntityInvariantCondition(mulCondition);
		}
	}

	public Goal initAssociationGoal() {
		checkMulConditions();

		applyActivationConditionsForAssociationGoal();

		return this;
	}

	private void checkMulConditions() {
		if (getEntityInvariantConditionSet().isEmpty()) {
			throw new BWException(BWErrorType.INCONSISTENT_ASSOCIATION_GOAL,
					"association goal has no multiplicity condition");
		}

		for (MulCondition mulCondition : getEntityInvariantConditionSet()) {
			if (!getEntityInvariantConditionSet().contains(mulCondition.getSymmetricMulCondition())) {
				throw new BWException(BWErrorType.INCONSISTENT_ASSOCIATION_GOAL,
						"association goal has no symmetric multiplicity condition of " + mulCondition.getPath());
			}
		}
	}

	@Override
	public void checkType() {
		if (!getSuccessConditionSet().isEmpty() || !getAttributeInvariantConditionSet().isEmpty()) {
			throw new BWException(BWErrorType.INCONSISTENT_ASSOCIATION_GOAL,
					"association goal has success condition: " + getName());
		}
	}

	@Override
	public void delete() {
		getEntityInvariantConditionSet().stream().forEach(mul -> removeEntityInvariantCondition(mul));

		super.delete();
	}

	@Override
	public Set<EntityInstance> getEntityInstanceContext(WorkflowInstance workflowInstance, Entity contextEntity) {
		// instances where activation conditions hold
		Set<EntityInstance> instanceContext = workflowInstance.getEntityInstanceSet(contextEntity).stream()
				.filter(ei -> ei.holdsDefPathConditions(getActivationConditionSetForContextEntity(contextEntity)))
				.collect(Collectors.toSet());

		// entity instance can be associated due to invariant mulconditions
		instanceContext = instanceContext.stream()
				.filter(ei -> ei.canBeAssociatedWithNewEntityInstance(getEntityInvariantConditionSet()))
				.collect(Collectors.toSet());

		// there are enough instances in the context to enable the goal
		int instanceContextSize = instanceContext.size();
		if (!getEntityInvariantConditionSet().stream().filter(m -> m.getTargetEntity() == contextEntity)
				.allMatch(m -> m.getCardinality().getMinValue() <= instanceContextSize)) {
			instanceContext.clear();
		}

		return instanceContext;
	}

}
