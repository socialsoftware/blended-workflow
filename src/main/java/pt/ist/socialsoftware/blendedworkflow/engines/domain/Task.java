package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.ArrayList;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Task extends Task_Base {
	
	public enum TaskState {DEACTIVATED, ENABLED, SKIPPED, ACHIEVED};
    
    public Task(TaskModel taskModel, String name, String description, Condition preConstrain,Condition postConstrain, String previous) throws BlendedWorkflowException {
		checkUniqueTaskName(taskModel, name);
		setTaskModel(taskModel);
		setName(name);
		setPreConstraint(preConstrain);
		setPostConstraint(postConstrain);
		setState(TaskState.DEACTIVATED);
		setDescription(description);
		setPrevious(previous);
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
		new Task(taskModelInstance, getName(), getDescription(), newPreCondition, newPostCondition, getPrevious());
	}
	
	public String getPostConstraintData () {
		String dataString = getPostConstraint().getData();
		String r ="";
		
		String[] elementArr = dataString.split("\\.");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < elementArr.length ; i++) {
			String element = elementArr[i];
			if (!result.contains(element)) {
				result.add(element);
			}
		}
		
		if (result.size() == 1) {
			r += result.get(0);
		}
		else {
			for (int i = 0; i < result.size()-1 ; i++) {
				r += result.get(i) + ", ";
			}
			r += result.get(result.size()-1);
		}
		return r;
	}
	
	public String getPreConstraintData () {
		String dataString = getPreConstraint().getData();		
		String r ="";
		
		String[] elementArr = dataString.split("\\.");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < elementArr.length ; i++) {
			String element = elementArr[i];
			if (!result.contains(element)) {
				result.add(element);
			}
		}
		
		if (result.size() == 1) {
			r += result.get(0);
		}
		else {
			for (int i = 0; i < result.size()-1 ; i++) {
				r += result.get(i) + ", ";
			}
			r += result.get(result.size()-1);
		}
		return r;
	}
    
}
