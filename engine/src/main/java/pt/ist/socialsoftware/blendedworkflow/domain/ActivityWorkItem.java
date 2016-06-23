package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityWorkItemDTO;

public class ActivityWorkItem extends ActivityWorkItem_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getActivity());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setActivity(Activity activity) {
		checkConsistency(getWorkflowInstance(), activity);
		super.setActivity(activity);
	}

	public ActivityWorkItem(WorkflowInstance workflowInstance, Activity activity) {
		setWorkflowInstance(workflowInstance);
		setCounter(workflowInstance.incLogCounter());
		setActivity(activity);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Activity activity) {
		if (workflowInstance != null && activity != null) {
			if (workflowInstance.getSpecification() != activity.getActivityModel().getSpecification()) {
				throw new BWException(BWErrorType.WORK_ITEM_CONSISTENCY, workflowInstance.getSpecification().getName()
						+ "<>" + activity.getActivityModel().getSpecification().getName());
			}
		}
	}

	@Override
	public void delete() {
		setActivity(null);
		super.delete();
	}

	public boolean holds() {
		checkArgumentsCompletelyDefined();

		return preConditionsHold() && postConditionsHold();
	}

	public boolean preConditionsHold() {
		return getPostConditionSet().stream().allMatch(wia -> getActivity().preHolds(wia, getPreConditionSet()));
	}

	public boolean postConditionsHold() {
		return getPostConditionSet().stream().allMatch(wia -> getActivity().postHolds(wia));
	}

	public void checkArgumentsCompletelyDefined() {
		checkCompleteSetOfPreConditions();

		checkCompleteSetOfPostConditions();
	}

	private void checkCompleteSetOfPostConditions() {
		if (getActivity().getPostConditionSet().size() > getPostConditionSet().size()) {
			throw new BWException(BWErrorType.POST_WORK_ITEM_ARGUMENT, "Number of elements "
					+ getActivity().getPostConditionSet().size() + "<" + getPostConditionSet().size());
		} else {
			for (DefProductCondition defProductCondition : getActivity().getPostConditionSet()) {
				if (!getPostConditionSet().stream()
						.anyMatch(pwia -> pwia.getDefProductCondition() == defProductCondition)) {
					throw new BWException(BWErrorType.POST_WORK_ITEM_ARGUMENT,
							"Non existing def product: " + defProductCondition.getPath().getValue());
				}
			}
		}
	}

	private void checkCompleteSetOfPreConditions() {
		if (getActivity().getPreConditionSet().size() > getPreConditionSet().size()) {
			throw new BWException(BWErrorType.PRE_WORK_ITEM_ARGUMENT, "Number of elements "
					+ getActivity().getPreConditionSet().size() + ">" + getPreConditionSet().size());
		} else {
			for (DefPathCondition defPathCondition : getActivity().getPreConditionSet()) {
				if (!getPreConditionSet().stream().anyMatch(pwia -> pwia.getDefPathCondition() == defPathCondition)) {
					throw new BWException(BWErrorType.PRE_WORK_ITEM_ARGUMENT,
							"Non existing def path: " + defPathCondition.getPath().getValue());
				}
			}
		}
	}

	public ActivityWorkItemDTO getDTO() {
		ActivityWorkItemDTO activityWorkItemDTO = new ActivityWorkItemDTO();
		activityWorkItemDTO.setActivityName(getActivity().getName());
		activityWorkItemDTO.setTimestamp(getCounter());
		activityWorkItemDTO
				.setPreArguments(
						getPreConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDTO().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining(",")));
		activityWorkItemDTO
				.setPostArguments(
						getPostConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDTO().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining(",")));

		return activityWorkItemDTO;
	}
}
