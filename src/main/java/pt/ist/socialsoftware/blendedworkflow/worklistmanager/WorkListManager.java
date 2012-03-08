package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class WorkListManager {
	
	private static WorkListManager workListManager = null;

	public static WorkListManager getInstance() {
		if (workListManager == null) {
			workListManager = new WorkListManager();
		}
		return workListManager;
	}

	public void notifyEnabledWorkItem(WorkItem workItem) {
		// TODO: update list of workitems being presented
		// FIXME: must be a service
	}
	
	// Presentation methods
	void loadBWSpecification() {
		new LoadBWSpecificationService("");
	}
	
	void createBWInstance() {
		new CreateBWInstanceService("");
	}
	
	void checkInWorkItem() {
		new CheckInWorkItemService("");
	}
	
	void skipWorkItem() {
		new SkipWorkItemService("");
	}
	
	void createGoal() {
		new CreateGoalService("");
	}
	
	void getEnabledWorkItems() {
		// TODO:
	}
	
}
