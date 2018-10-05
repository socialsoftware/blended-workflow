package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import org.joda.time.DateTime;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;

public class GoalWorkItem extends GoalWorkItem_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getGoal());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setGoal(Goal goal) {
		checkConsistency(getWorkflowInstance(), goal);
		super.setGoal(goal);
	}

	public GoalWorkItem(WorkflowInstance workflowInstance, Goal goal) {
		setWorkflowInstance(workflowInstance);
		setCounter(workflowInstance.incLogCounter());
		setDate(DateTime.now());
		setGoal(goal);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Goal goal) {
		if (workflowInstance != null && goal != null) {
			if (workflowInstance.getSpecification() != goal.getGoalModel().getSpecification()) {
				throw new BWException(BWErrorType.WORK_ITEM_CONSISTENCY, workflowInstance.getSpecification().getName()
						+ "<>" + goal.getGoalModel().getSpecification().getName());
			}
		}
	}

	@Override
	public void delete() {
		setGoal(null);
		super.delete();
	}

	@Override
	protected Set<DefPathCondition> definedPreConditions() {
		return getGoal().getActivationConditionSet();
	}

	@Override
	public boolean postConditionsHold() {
		if (getPostConditionSet().size() == 0 && getGoal().getSuccessConditionSet().size() != 0) {
			throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, "missing argument");
		}

		getPostConditionSet().stream().allMatch(wia -> wia.productExistsAndHasCorrectType());

		getPostConditionSet().stream().allMatch(wia -> wia.attributeIsDefined());

		// only verifies the relations if the it they are all defined, which
		// means that are either defined in a super-class or in the goal
		getPostConditionSet().stream().allMatch(wia -> wia.relationInstancesAreDefined(
				getGoal().getMulConditionsThatShouldHold(wia.getDefProductCondition().getTargetOfPath())));

		return true;
	}

	@Override
	public GoalWorkItemDto getDto() {
		GoalWorkItemDto goalWorkItemDTO = new GoalWorkItemDto();
		goalWorkItemDTO.setName(getGoal().getName());
		goalWorkItemDTO.setType("Goal");

		fillDto(goalWorkItemDTO);

		return goalWorkItemDTO;
	}

}
