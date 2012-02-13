package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class TaskWorkItem extends TaskWorkItem_Base {

	public TaskWorkItem(BWInstance bwInstance, String taskInstanceId) {
		setBwInstance(bwInstance);
		setTaskInstanceId(taskInstanceId);
		setId(getTaskInstanceId() + "." + getNewWorkItemId()); //Id: TaskInstanceId.#
		setState(WorkItemState.ENABLED);
	}

	@Override
	public String getElementId() {
		return getTaskInstanceId();
	}

}