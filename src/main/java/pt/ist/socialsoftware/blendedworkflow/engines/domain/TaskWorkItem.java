package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

public class TaskWorkItem extends TaskWorkItem_Base {

	public TaskWorkItem(BWInstance bwInstance, Task task) {
		setBwInstance(bwInstance);
		setTask(task);
		setID(task.getName() + "." + bwInstance.getNewWorkItemID()); //Id: TaskName.#
		setState(WorkItemState.ENABLED);
		task.setState(TaskState.ENABLED);
		task.getPreConstraint().assignAttributeInstances(this, "pre");
		task.getPostConstraint().assignAttributeInstances(this, "post");
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
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

}