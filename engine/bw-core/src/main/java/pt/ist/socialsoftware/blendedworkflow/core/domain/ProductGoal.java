package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ProductGoal extends ProductGoal_Base {
	@Override
	public void addEntityInvariantCondition(MulCondition mulCondition) {
		throw new BWException(BWErrorType.INCONSISTENT_PRODUCT_GOAL,
				"A product goal cannot have multiplicity conditions");
	}

	public ProductGoal(GoalModel goalModel, String name, Set<DefProductCondition> defProductConditionSet) {
		setGoalModel(goalModel);
		setName(name);

		for (DefProductCondition defProductCondition : defProductConditionSet) {
			addSuccessCondition(defProductCondition);
		}
	}

	public Goal initProductGoal() {
		if (getSuccessConditionSet().isEmpty()) {
			throw new BWException(BWErrorType.INCONSISTENT_PRODUCT_GOAL,
					"A product goal should have a success conditions");
		}
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

	@Override
	public Set<EntityInstance> getEntityInstanceContext(WorkflowInstance workflowInstance, Entity contextEntity) {
		// instances where activation conditions hold
		Set<EntityInstance> instanceContext = workflowInstance.getEntityInstanceSet(contextEntity).stream()
				.filter(ei -> ei.holdsDefPathConditions(getActivationConditionSetForContextEntity(contextEntity)))
				.collect(Collectors.toSet());

		// none of activation conditions attributes are defined in the instance contexts
		instanceContext = instanceContext.stream().filter(ei -> ei.attributesNotDefined(getSuccessAttributes()))
				.collect(Collectors.toSet());

		return instanceContext;
	}

}
