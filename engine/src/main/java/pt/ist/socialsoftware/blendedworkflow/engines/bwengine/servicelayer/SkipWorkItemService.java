package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import jvstm.Transaction;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;

public class SkipWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("SkipWorkItemService");
	private final WorkItem workItem;

	public SkipWorkItemService(String workItemOID) {
		this.workItem = FenixFramework.getDomainObject(workItemOID);
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		for (WorkItemArgument workItemArgument : this.workItem
				.getOutputWorkItemArgumentsSet()) {
			workItemArgument.setState(DataState.SKIPPED);
			workItemArgument.setValue("$SKIPPED$");
		}

		this.workItem.notifyCheckedIn();
		Transaction.commit();
		log.info("END");
		return "SkipWorkItemService:Sucess";
	}

}