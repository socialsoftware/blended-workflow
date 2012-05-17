package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class CreateGoalInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateGoalInstanceService");
	
	private BWInstance bwInstance;
	private AchieveGoal goal;
	private Long entityContextOID;
	private EntityInstance entityContext;

	public CreateGoalInstanceService (long bwInstanceOID, long goalOID, Long entityContextOID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goal = AbstractDomainObject.fromOID(goalOID);
		this.entityContextOID = entityContextOID;

	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");

		Transaction.begin();
		
		if (entityContextOID == null) {
//			Transaction.begin();
			DataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();
			Entity entityContext = this.goal.getEntityContext();
			this.entityContext = new EntityInstance(dataModelInstance, entityContext);
//			Transaction.commit();
		} else {
			this.entityContext = AbstractDomainObject.fromOID(entityContextOID);
		}	
		
		
		generateGoalWorkItems(this.bwInstance, this.goal, this.entityContext);
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}
	
	private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal goal, EntityInstance parentEntityInstanceContext) {
		Boolean alreadyExists = false;
		//If parent goal has activate workItems change state to GOAL_PENDING
		if (goal.getParentGoal().getGoalWorkItemsCount() > 0) {
			for (GoalWorkItem goalWorkItem : goal.getParentGoal().getGoalWorkItems()) {
				if (goalWorkItem.getState().equals(WorkItemState.ENABLED) || goalWorkItem.getState().equals(WorkItemState.PRE_GOAL)) {
					goalWorkItem.notifyPending();
				}
			}
		}

		//No workItems then create
		if (goal.getGoalWorkItemsCount() == 0) {
			new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext);
		} else {
			//Exists a workItem with the same context
			for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
				if (goalWorkItem.getEntityInstanceContext().equals(parentEntityInstanceContext)) {
					alreadyExists = true;
				}
			}
			
			// If does not create
			if (!alreadyExists) {
				new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext);
			}
			else {
				log.info("GoalWorkItem associated with " + parentEntityInstanceContext.getID() + " already exists.");
			}
		}

		//Generate WorkItems for subgoals
		for (AchieveGoal subGoal : goal.getSubGoals()) {
			EntityInstance subGoalEntityContext = null;
			
			Entity parentEntityContext = goal.getEntityContext();
			Entity subEntityContext = subGoal.getEntityContext();
			//Context is the same
			if (subEntityContext.equals(parentEntityContext)) {
				subGoalEntityContext = parentEntityInstanceContext;
			} else {
				//FIXME
//				log.info("SubGoal context diferent than its parentGoal.");
//				//FIXME: EntityInstance Relation must exist!
//				for (RelationInstance relationInstance : parentEntityInstanceContext.getRelationInstances()) {
//					
//					//Relation EntityOne = than check EntityTwo
//					if (relationInstance.getEntityOne() == parentEntityInstanceContext) {
//						if (relationInstance.getEntityTwo().getEntity() == subEntityContext){
//							subGoalEntityContext = relationInstance.getEntityTwo();
//						}
//					}
//					//Relation EntityTwo = than check EntityOne
//					if (relationInstance.getEntityTwo() == parentEntityInstanceContext) {
//						if (relationInstance.getEntityOne().getEntity() == subEntityContext){
//							subGoalEntityContext = relationInstance.getEntityOne();
//						}
//					}
//				}
			}
			
			// Recursive call for subgoals
			generateGoalWorkItems(bwInstance, subGoal, subGoalEntityContext);
		}		
	}
}
