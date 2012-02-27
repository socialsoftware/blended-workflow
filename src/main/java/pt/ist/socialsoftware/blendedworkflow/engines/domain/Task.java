package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Task extends Task_Base {
	
	public enum TaskState {DEACTIVATED, ENABLED, SKIPPED, ACHIEVED};
    
    public Task(TaskModel taskModel, String name, Condition preConstrain,Condition postConstrain) throws BlendedWorkflowException {
		checkUniqueTaskName(taskModel, name);
		setTaskModel(taskModel);
		setName(name);
		setPreConstraint(preConstrain);
		setPostConstraint(postConstrain);
		setState(TaskState.DEACTIVATED);
    }
    
	private void checkUniqueTaskName(TaskModel taskModel, String name) throws BlendedWorkflowException {
		for (Task task : taskModel.getTasks()) {
			if (task.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @Task: The Task name: " + name + "already exists.");
			}
		}
	}
	
	public void cloneTask(TaskModelInstance taskModelInstance) throws BlendedWorkflowException {
		Condition newPreCondition = null;
		Condition preCondition = getPreConstraint();
		Condition newPostCondition = null;
		Condition postCondition = getPreConstraint();
		if (preCondition != null && postCondition != null) {
			newPreCondition = preCondition.cloneCondition(taskModelInstance);
			newPostCondition = postCondition.cloneCondition(taskModelInstance);
		}
		new Task(taskModelInstance, getName(), newPreCondition, newPostCondition);
	}
    
}
