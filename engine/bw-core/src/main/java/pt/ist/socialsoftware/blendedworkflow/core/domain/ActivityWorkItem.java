package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;

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

	@Override
	protected Set<DefPathCondition> definedPreConditions() {
		return getActivity().getPreConditionSet();
	}

	@Override
	public boolean postConditionsHold() {
		if (getPostConditionSet().size() == 0) {
			throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, "missing argument");
		}

		getPostConditionSet().stream().allMatch(wia -> wia.productExistsAndHasCorrectType());

		getPostConditionSet().stream().allMatch(wia -> wia.attributeIsDefined());

		getPostConditionSet().stream().allMatch(wia -> wia.relationInstancesAreDefined(
				getActivity().getMulConditionsThatShouldHold(wia.getDefProductCondition().getTargetOfPath())));

		return true;
	}

	public ActivityWorkItemDTO getDTO() {
		ActivityWorkItemDTO activityWorkItemDTO = new ActivityWorkItemDTO();
		activityWorkItemDTO.setName(getActivity().getName());

		super.fillDTO(activityWorkItemDTO);

		return activityWorkItemDTO;
	}

}
