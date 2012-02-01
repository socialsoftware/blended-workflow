package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class WorkletAdapter {
	
	private static WorkletAdapter instance;

	public static WorkletAdapter getInstance() {
		if (instance == null) {
			return new WorkletAdapter();
		}
		return instance;
	}
	
	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		// get values
		// convert values to Element
		// invoke worklet gateway
	}

}
