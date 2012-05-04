package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class CheckInWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("CheckInWorkItemService");
	private WorkItem workItem;

	public CheckInWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();		
		this.workItem.notifyCheckedIn();
		Transaction.commit();
		log.info("END");
		return "CheckInWorkItemService:Sucess";
	}

}