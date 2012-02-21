package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class HandleTaskPreConditionEventService {

	private WorkItemRecord workItemRecord;
	private String type;

	public HandleTaskPreConditionEventService(WorkItemRecord workItemRecord, String type) {
		this.workItemRecord = workItemRecord;
		this.type = type;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		
		// TODO: We trust that the taskWorkItem was already created
		WorkItem taskWorkItem = blendedWorkflow.getBWInstance(workItemRecord.getCaseID()).getWorkItem(workItemRecord.getID());
		
		if (this.type.equals("TRUE")) {
			taskWorkItem.notifyEnabled();
		} else if (this.type.equals("SKIPPED")) {
			taskWorkItem.notifyPreTask();
		} else {
			throw new BlendedWorkflowException("Precondition is FALSE");
		}

	}


	
}
