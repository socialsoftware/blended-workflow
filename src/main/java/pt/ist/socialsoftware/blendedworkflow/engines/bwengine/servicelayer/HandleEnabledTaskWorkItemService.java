package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

public class HandleEnabledTaskWorkItemService {

	private BWInstance bwInstance;
	private String taskName;

	public HandleEnabledTaskWorkItemService(BWInstance bwInstance, String taskName) {
		this.bwInstance = bwInstance;
		this.taskName = taskName;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
		Task task = taskModelInstance.getTask(this.taskName);
		
		if (task.getTaskWorkItem() == null) {
			new TaskWorkItem(this.bwInstance, task);
		}
		else {
			task.setState(TaskState.ENABLED);
			TaskWorkItem taskWorkItem = task.getTaskWorkItem();
			taskWorkItem.setState(WorkItemState.ENABLED);
			
			WorkListManager.getInstance().notifyEnabledWorkItem(taskWorkItem);
		}
	}

}
