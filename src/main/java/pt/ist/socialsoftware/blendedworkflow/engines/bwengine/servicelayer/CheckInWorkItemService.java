package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.HashMap;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import jvstm.Atomic;

public class CheckInWorkItemService {

	private WorkItem workItem;
	private HashMap<String, String> values;

	public CheckInWorkItemService (WorkItem workItem, HashMap<String, String> values) {
		this.workItem = workItem;
		this.values = values;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
//		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(workItem);
		this.workItem.notifyWorkItemCheckedIn(this.values);
	}

}