package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import jvstm.Atomic;

public class CheckInWorkItemService {

	private static Logger log = Logger.getLogger("CheckInWorkItemService");
	private WorkItem workItem;

	public CheckInWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		log.info("WorkItem " + this.workItem.getID() + " checkedIn");

		this.workItem.notifyCheckedIn();
	}

}