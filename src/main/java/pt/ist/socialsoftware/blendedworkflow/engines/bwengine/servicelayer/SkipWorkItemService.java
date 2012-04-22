package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.apache.log4j.Logger;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class SkipWorkItemService {
	
	private static Logger log = Logger.getLogger("SkipWorkItemService");
	private WorkItem workItem;

	public SkipWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		log.info("WorkItem " + this.workItem.getID() + " skipped");
		
		for (WorkItemArgument workItemArgument : this.workItem.getConstrainViolationWorkItemArguments()) {
			workItemArgument.setState(DataState.SKIPPED);
		}
		
		this.workItem.notifySkip();
	}

}