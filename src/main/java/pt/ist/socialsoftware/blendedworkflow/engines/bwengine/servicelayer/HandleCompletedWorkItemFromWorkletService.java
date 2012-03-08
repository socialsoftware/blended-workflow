package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class HandleCompletedWorkItemFromWorkletService {
	
	private TaskWorkItem taskWorkItem;
	private String result;

	public HandleCompletedWorkItemFromWorkletService(TaskWorkItem taskWorkItem, String result) {
		this.taskWorkItem = taskWorkItem;
		this.result = result;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		if (result.equals("TRUE")) {
			this.taskWorkItem.notifyCompleted();
		}
		else if (result.equals("SKIPPED")) {
			this.taskWorkItem.notifySkipped();
		}
		else 
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER);
	}

}
