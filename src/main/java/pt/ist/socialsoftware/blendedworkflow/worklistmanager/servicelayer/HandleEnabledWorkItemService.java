package pt.ist.socialsoftware.blendedworkflow.worklistmanager.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class HandleEnabledWorkItemService {
	
	private WorkItem workItem;
	
	public HandleEnabledWorkItemService (WorkItem workItem) {
		this.workItem = workItem;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// Create clone workitems? or/and
		// Create cloned attributes instances
	}


}
