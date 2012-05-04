package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class SkipWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("SkipWorkItemService");
	private WorkItem workItem;

	public SkipWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		for (WorkItemArgument workItemArgument : this.workItem.getConstrainViolationWorkItemArguments()) {
			workItemArgument.setState(DataState.SKIPPED);
			workItemArgument.setValue("$SKIPPED$");
		}

		this.workItem.notifySkip();
		Transaction.commit();
		log.info("END");
		return "SkipWorkItemService:Sucess";
	}	

}