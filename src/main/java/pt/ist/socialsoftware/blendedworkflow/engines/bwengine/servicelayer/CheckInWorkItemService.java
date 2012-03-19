package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import jvstm.Atomic;

public class CheckInWorkItemService {

	private WorkItem workItem;

	public CheckInWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
//		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(workItem);
		
		// FIXME:
		this.workItem.notifyWorkItemCheckedIn();
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(workItem); 
	}

}