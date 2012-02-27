package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class TaskModel extends TaskModel_Base {
    
	public void cloneTaskModel(TaskModelInstance newTaskModelInstance) throws BlendedWorkflowException {
		for (Task task : getTasks()) {
			task.cloneTask(newTaskModelInstance);
		}
	}

	public Task getTask(String name) throws BlendedWorkflowException {
		for (Task task : getTasks()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BlendedWorkflowException("Exception @TaskModel: The Task name: " + name + " does not exist.");
	}
    
}
