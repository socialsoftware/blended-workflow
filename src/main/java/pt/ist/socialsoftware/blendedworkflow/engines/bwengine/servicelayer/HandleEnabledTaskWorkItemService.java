package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

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
		new TaskWorkItem(this.bwInstance, task);
	}

}
