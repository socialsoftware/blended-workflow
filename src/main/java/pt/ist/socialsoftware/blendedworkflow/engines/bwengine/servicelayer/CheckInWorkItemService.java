package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.HashMap;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import jvstm.Atomic;

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
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		this.workItem = bwInstance.getWorkItem(this.workItemID);

		System.out.println("CheckIn WorkItem: \"" + this.workItem.getId() + "\"");
		System.out.println("WorkItem \"" + this.workItem.getId() + "\" was \"" + this.workItem.getState() + "\" ."); 

		this.workItem.notifyWorkItemCheckedIn(this.values);

		System.out.println("WorkItem \"" + this.workItem.getId() + "\" is now \"" + this.workItem.getState() + "\" .");
		System.out.println("Actual Enabled WorkItems:");
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem.getState() == WorkItemState.ENABLED) {
				System.out.println(workItem.getId());
			}
		}
		System.out.println("ReCheck Enabled WorkItems:"); // TODO call this method after workitemState changed to achieved
		bwInstance.getGoalModelInstance().getEnabledWorkItems();
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem.getState() == WorkItemState.ENABLED) {
				System.out.println(workItem.getId());
			}
		}

	}

}