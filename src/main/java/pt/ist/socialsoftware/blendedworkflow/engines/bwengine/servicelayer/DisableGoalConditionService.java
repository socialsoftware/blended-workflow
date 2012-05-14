package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;

public class DisableGoalConditionService implements Callable<String> {

	private static Logger log = Logger.getLogger("DisableGoalConditionService");
	
	private GoalWorkItem goalWorkItem;
	private Condition condition;

	public DisableGoalConditionService (long workItemOID, long conditionOID) {
		this.goalWorkItem = AbstractDomainObject.fromOID(workItemOID);;
		this.condition = AbstractDomainObject.fromOID(conditionOID);
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		this.goalWorkItem.removeActivateConditions(condition);
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}

}
