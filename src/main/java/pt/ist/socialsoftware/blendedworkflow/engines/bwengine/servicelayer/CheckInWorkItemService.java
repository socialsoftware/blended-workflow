package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import jvstm.Transaction;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class CheckInWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("CheckInWorkItemService");
	private final WorkItem workItem;

	public CheckInWorkItemService(String workItemOID) {
		this.workItem = FenixFramework.getDomainObject(workItemOID);
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