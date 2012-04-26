package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CheckInWorkItemService {
	
	private static Logger log = Logger.getLogger("CheckInWorkItemService");
	private WorkItem workItem;

	public CheckInWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
//		ThreadPool.getThreadExecutor().execute(Thread.currentThread());
		log.info("CIWS:execute:BEGIN " + Thread.currentThread().getId());
		log.info("WorkItem " + this.workItem.getID() + " checkedIn");
		this.workItem.notifyCheckedIn();
		log.info("CIWS:execute:END" + Thread.currentThread().getId());
		
//		Thread.yield();
//		BlendedWorkflow.getInstance().getWorkletAdapter().createNewTaskWorkItems();
//		BlendedWorkflow.getInstance().getWorkletAdapter().processNewTaskWorkItems();
	}

}