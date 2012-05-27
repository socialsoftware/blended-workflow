package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
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
	
	public void getEnabledWorkItems() throws BlendedWorkflowException {
		for (Task task : getTasks()) {
			if (task.getName().equals("Booking") && task.getState().equals(TaskState.DEACTIVATED)) {
				TaskWorkItem taskWorkItem = new TaskWorkItem(getBwInstance(), task);
				taskWorkItem.notifyEnabled();
			} else
			// First Task and DEACTIVATED?
			if (task.getPrevious().equals("") && task.getState().equals(TaskState.DEACTIVATED)) {
				TaskWorkItem taskWorkItem = new TaskWorkItem(getBwInstance(), task);
				BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemPreConstraint(taskWorkItem);
			} else if (!task.getPrevious().equals("") && task.getState().equals(TaskState.DEACTIVATED)) {
				// All previous Tasks ACHIEVED?
				int pCount = 0;
				String previous = task.getPrevious();
				String[] elementArr = previous.split(",");
				for (int i = 0; i< elementArr.length ; i++) {
					Task ptask = getTask(elementArr[i]);
					if (ptask.getState().equals(TaskState.ACHIEVED) || ptask.getState().equals(TaskState.SKIPPED)) {
						pCount++;
					}
				}
				if (pCount == elementArr.length) {
					TaskWorkItem taskWorkItem = new TaskWorkItem(getBwInstance(), task);
					BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemPreConstraint(taskWorkItem);
				}
			}
		}
	}

}
