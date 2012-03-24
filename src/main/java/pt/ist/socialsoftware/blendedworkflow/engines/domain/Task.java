package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Task extends Task_Base {
	
	public enum TaskState {DEACTIVATED, ENABLED, SKIPPED, ACHIEVED};
    
    public Task(TaskModel taskModel, String name, String description, Condition preConstrain,Condition postConstrain) throws BlendedWorkflowException {
		checkUniqueTaskName(taskModel, name);
		setTaskModel(taskModel);
		setName(name);
		setPreConstraint(preConstrain);
		setPostConstraint(postConstrain);
		setState(TaskState.DEACTIVATED);
		setDescription(description);
    }
    
	private void checkUniqueTaskName(TaskModel taskModel, String name) throws BlendedWorkflowException {
		for (Task task : taskModel.getTasks()) {
			if (task.getName().equals(name)) {
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_TASK_NAME, name);
			}
		}
	}
	
	public void cloneTask(TaskModelInstance taskModelInstance) throws BlendedWorkflowException {
		Condition newPreCondition = null;
		Condition preCondition = getPreConstraint();
		Condition newPostCondition = null;
		Condition postCondition = getPostConstraint();
		if (preCondition != null && postCondition != null) {
			newPreCondition = preCondition.cloneCondition(taskModelInstance);
			newPostCondition = postCondition.cloneCondition(taskModelInstance);
		}
		new Task(taskModelInstance, getName(), getDescription(), newPreCondition, newPostCondition);
	}
    
}
