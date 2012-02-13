package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
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

		System.out.println("Skip WorkItem: \"" + this.workItem.getId() + "\"");
		System.out.println("WorkItem \"" + this.workItem.getId() + "\" was \"" + this.workItem.getState() + "\" .");

		this.workItem.notifyWorkItemSkipped();

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
