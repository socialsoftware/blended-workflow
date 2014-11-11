package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class TaskModel extends TaskModel_Base {

	/**
	 * Clone the TaskModel
	 */
	public void cloneTaskModel(TaskModelInstance newTaskModelInstance)
			throws BlendedWorkflowException {
		for (Task task : getTasksSet()) {
			task.cloneTask(newTaskModelInstance);
		}
	}

	public Task getTask(String name) throws BlendedWorkflowException {
		for (Task task : getTasksSet()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BlendedWorkflowException(
				BlendedWorkflowError.NON_EXISTENT_TASK_NAME, name);
	}

}
