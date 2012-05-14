package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class EnableGoalWorkItemsService implements Callable<String> {

	private static Logger log = Logger.getLogger("EnableGoalWorkItemsService");
	
	private BWInstance bwInstance;

	public EnableGoalWorkItemsService (long bwInstanceOID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem.getClass().equals(GoalWorkItem.class) && workItem.getState().equals(WorkItemState.ACTIVATED)) {
				GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
//				System.out.println("CreateInput for :"  + workItem.getID() + " AC: " + goalWorkItem.getActivateConditionsCount() + " SC: " + goalWorkItem.getSucessCondition().getOID());
				
				for (Condition activateCondition : goalWorkItem.getActivateConditions()) {
					activateCondition.assignAttributeInstances(goalWorkItem, ConditionType.ACTIVATE);
				}
				
				goalWorkItem.createInputWorkItemArguments();
				goalWorkItem.updateInputWorkItemArguments();
				goalWorkItem.evaluate(true);
			}
		}
		
		Transaction.commit();
		log.info("END");
		return "EnableGoalWorkItemsService:Sucess";
	}

}
