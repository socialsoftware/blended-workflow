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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class RedoGoalService implements Callable<String> {

	private static Logger log = Logger.getLogger("RedoGoalService");

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private BWInstance bwInstance;
	private AchieveGoal goal;
	private String userID;

	public RedoGoalService(long bwInstanceOID, long goalOID, String userID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goal = AbstractDomainObject.fromOID(goalOID);
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		
		GoalWorkItem goalWorkItem = goal.getGoalWorkItem();
		if (goalWorkItem.getState().equals(WorkItemState.SKIPPED) || goalWorkItem.getState().equals(WorkItemState.COMPLETED)) {
			goalWorkItem.updateConstrainViolationWorkItemArguments();
			goalWorkItem.setState(WorkItemState.RE_ACTIVATED);
			
			String date = dateFormat.format(Calendar.getInstance().getTime());
			this.bwInstance.getLog().addLogRecords(new LogRecord(date, "ReEnabled", "[GOAL] " + this.goal.getName(), this.userID));
			
			BlendedWorkflow.getInstance().getWorkListManager().notifyReEnabledWorkItem(goalWorkItem);
		}
		
		Transaction.commit();
		log.info("END");
		return "RedoGoalService:Sucess";
	}
}