package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal.MaintainGoalState;

public class ManageGoalConditionService implements Callable<String> {

	private static Logger log = Logger.getLogger("DisableGoalConditionService");
	
	private GoalWorkItem goalWorkItem;
	private Condition condition;
	private MaintainGoal maintainGoal;
	private MaintainGoalState state;

	/**
	 * Remove an AchieveGoal ActivateCondition.
	 * @param workItemOID the workItem OID.
	 * @param conditionOID the condition OID.
	 */
	public ManageGoalConditionService (long workItemOID, long conditionOID) {
		this.goalWorkItem = AbstractDomainObject.fromOID(workItemOID);
		this.condition = AbstractDomainObject.fromOID(conditionOID);
		this.maintainGoal = null;
		this.state = null;
	}
	
	/**
	 * Enable/Disable BWInstance MaintainGoal Conditions.
	 * @param maintainGoalOID
	 */
	public ManageGoalConditionService (long maintainGoalOID, MaintainGoalState state) {
		this.goalWorkItem = null;
		this.condition = null;
		this.maintainGoal = AbstractDomainObject.fromOID(maintainGoalOID);
		this.state = state;
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		
		if (this.maintainGoal == null) {
			
			for (Condition activateCondition : this.goalWorkItem.getActivateConditions()) {
				if (activateCondition.equals(condition)) {
					this.goalWorkItem.removeActivateConditions(condition);	
				}
			}
			
			for (Condition maintainCondition : this.goalWorkItem.getMaintainConditions()) {
				if (maintainCondition.equals(condition)) {
					this.goalWorkItem.removeMaintainConditions(condition);	
				}
			}
			
		} else {
			this.maintainGoal.setState(this.state);
		}
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}

}
