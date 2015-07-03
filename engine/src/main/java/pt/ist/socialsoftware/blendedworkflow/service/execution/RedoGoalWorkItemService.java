package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import jvstm.Transaction;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;

public class RedoGoalWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("RedoGoalService");

	private final GoalWorkItem goalWorkItem;
	private final String userID;

	public RedoGoalWorkItemService(String goalWorkItemOID, String userID) {
		this.goalWorkItem = FenixFramework.getDomainObject(goalWorkItemOID);
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();

		if (goalWorkItem.getState().equals(GoalState.SKIPPED)
				|| goalWorkItem.getState().equals(GoalState.ACHIEVED)) {
			User user = BlendedWorkflow.getInstance().getOrganizationalModel()
					.getUser(this.userID);
			goalWorkItem.setUser(user);
			goalWorkItem.notifyReActivated(null);
		}

		Transaction.commit();
		log.info("END");
		return "RedoGoalService:Sucess";
	}
}