package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class RedoGoalWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("RedoGoalService");

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private BWInstance bwInstance;
	private GoalWorkItem goalWorkItem;
	private String userID;

	public RedoGoalWorkItemService(long bwInstanceOID, long goalWorkItemOID, String userID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		
		if (goalWorkItem.getState().equals(WorkItemState.SKIPPED) || goalWorkItem.getState().equals(WorkItemState.COMPLETED)) {
			goalWorkItem.updateOutputWorkItemArguments();
			goalWorkItem.setState(WorkItemState.RE_ACTIVATED);
			
			String date = dateFormat.format(Calendar.getInstance().getTime());
			this.bwInstance.getLog().addLogRecords(new LogRecord(date, "ReEnabled", "[GOAL] " + this.goalWorkItem.getID(), this.userID));
			
			BlendedWorkflow.getInstance().getWorkListManager().notifyReEnabledWorkItem(goalWorkItem);
		}
		
		Transaction.commit();
		log.info("END");
		return "RedoGoalService:Sucess";
	}
}