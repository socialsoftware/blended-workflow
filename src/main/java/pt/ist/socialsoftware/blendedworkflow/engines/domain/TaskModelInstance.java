package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class TaskModelInstance extends TaskModelInstance_Base {
    
	public Task getTask(String name) throws BlendedWorkflowException {
		for (Task task : getTasks()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BlendedWorkflowException("Exception @TaskModelInstance: The Task name: " + name + " does not exist.");
	}
    
}
