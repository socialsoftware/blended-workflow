package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class SkipWorkItemService {
	
	private WorkItem workItem;

	public SkipWorkItemService (WorkItem workItem) {
		this.workItem = workItem;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
//		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(workItem);
		this.workItem.notifyWorkItemSkipped();
	}

}