package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class TaskModelInstance extends TaskModelInstance_Base {
	
	public Task getTask(String name) throws BlendedWorkflowException {
		for (Task task : getTasks()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_TASK_NAME, name);
	}
}
