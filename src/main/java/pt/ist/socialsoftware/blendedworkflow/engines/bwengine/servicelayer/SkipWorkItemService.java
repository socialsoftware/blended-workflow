package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class SkipWorkItemService {

	private String bwInstanceID;
	private String workItemID;
	private BWInstance bwInstance;
	private WorkItem workItem;

	public SkipWorkItemService (String bwInstanceID, String workItemID) {
		this.bwInstanceID = bwInstanceID;
		this.workItemID = workItemID;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		this.workItem = bwInstance.getWorkItem(this.workItemID);
		// change attributes state to skipped
		this.workItem.setAttributeState(DataState.SKIPPED);
		// change workItem state to skipped
		this.workItem.notifyWorkItemSkipped();					
	}
}
