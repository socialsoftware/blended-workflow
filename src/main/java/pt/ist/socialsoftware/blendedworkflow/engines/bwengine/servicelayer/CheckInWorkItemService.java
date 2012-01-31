package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.HashMap;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;

public class CheckInWorkItemService {

	private String bwInstanceID;
	private String workItemID;
	private HashMap<String,String> values;
	private BWInstance bwInstance;
	private WorkItem workItem;

	public CheckInWorkItemService (String bwInstanceID, String workItemID, HashMap<String, String> values) {
		this.bwInstanceID = bwInstanceID;
		this.workItemID = workItemID;
		this.values = values;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(bwInstanceID);
		this.workItem = bwInstance.getWorkItem(workItemID);
		this.workItem.getAttributeInstance();
		
		// obtain data
		
		//change data objects
		
		// no
		workItem.setState(WorkItemState.COMPLETED);					
	}

}
