package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;

public class RedoGoalWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("RedoGoalService");

	private GoalWorkItem goalWorkItem;
	private String userID;

	public RedoGoalWorkItemService(long goalWorkItemOID, String userID) {
		this.goalWorkItem = AbstractDomainObject.fromOID(goalWorkItemOID);
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		
		if (goalWorkItem.getState().equals(GoalState.SKIPPED) || goalWorkItem.getState().equals(GoalState.ACHIEVED)) {
			User user = BlendedWorkflow.getInstance().getOrganizationalModel().getUser(this.userID);
			goalWorkItem.setUser(user);
			goalWorkItem.notifyReActivated(null);
		}
		
		Transaction.commit();
		log.info("END");
		return "RedoGoalService:Sucess";
	}
}