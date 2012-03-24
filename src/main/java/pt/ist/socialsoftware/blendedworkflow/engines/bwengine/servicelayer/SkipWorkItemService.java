package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class SkipWorkItemService {
	
	private WorkItem workItem;

	public SkipWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
//		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(workItem);
		this.workItem.notifyWorkItemSkipped();
		
		this.workItem.getBwInstance().getLog().addLogRecords(new LogRecord("Date","Skipped", this.workItem.getID(), "Author"));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(workItem);
	}

}