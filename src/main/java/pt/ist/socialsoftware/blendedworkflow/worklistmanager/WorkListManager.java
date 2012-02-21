package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

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
		// update list of workitems being presented
	}
}
