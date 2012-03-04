package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

public class TaskWorkItem extends TaskWorkItem_Base {

	public TaskWorkItem(BWInstance bwInstance, String taskInstanceID) {
		setBwInstance(bwInstance);
		setTaskInstanceID(taskInstanceID);
		setID(getTaskInstanceID() + "." + bwInstance.getNewWorkItemID()); //Id: TaskInstanceId.#
		setState(WorkItemState.PRE_CONSTRAINT);
	}

	@Override
	public String getElementID() {
		return getTaskInstanceID();
	}

	@Override
	public void notifyEnabled() {
		setState(WorkItemState.ENABLED);
		WorkListManager.getInstance().notifyEnabledWorkItem(this);
	}

	@Override
	public void notifyCompleted() {
		setState(WorkItemState.COMPLETED);
		
	}

	@Override
	public void notifySkipped() {
		setState(WorkItemState.SKIPPED);
	}

	@Override
	public void notifyPreTask() {
		setState(WorkItemState.PRE_TASK);
		WorkListManager.getInstance().notifyEnabledWorkItem(this);
	}

}