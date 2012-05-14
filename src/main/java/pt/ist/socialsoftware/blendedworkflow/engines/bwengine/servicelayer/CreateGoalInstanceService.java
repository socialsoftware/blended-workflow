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

public class CreateGoalInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateGoalInstanceService");
	
	private BWInstance bwInstance;
	private AchieveGoal goal;
	private EntityInstance entityContext;

	public CreateGoalInstanceService (long bwInstanceOID, long goalOID, Long entityContextOID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goal = AbstractDomainObject.fromOID(goalOID);
		if (entityContextOID == null) {
			Transaction.begin();
			DataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();
			Entity entityContext = this.goal.getEntityContext();
			this.entityContext = new EntityInstance(dataModelInstance, entityContext);
			Transaction.commit();
		} else {
			this.entityContext = AbstractDomainObject.fromOID(entityContextOID);
		}		
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		generateGoalWorkItems(this.bwInstance, this.goal, this.entityContext);
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}
	
	private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal goal, EntityInstance entityContext) {
		Boolean alreadyExists = false;
//		System.out.println("IN BWI:" + bwInstance.getID() + " GN:" + goal.getName() + " C:" + entityContext.getID());
		if (goal.getGoalWorkItemsCount() == 0) {
//			System.out.println(goal.getName() + " - no workitems for: " + entityContext.getOID());
			new GoalWorkItem(bwInstance, goal, entityContext);
		} else {
			for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
				if (goalWorkItem.getEntityInstanceContext().equals(entityContext)) {
					alreadyExists = true;
				}
			}
			
			if (alreadyExists) {
//				System.out.println(goal.getName() + " - already exists for: " + entityContext.getOID());
			}
			else {
				new GoalWorkItem(bwInstance, goal, entityContext);
			}
		}

		for (AchieveGoal subGoal : goal.getSubGoals()) {
			EntityInstance subGoalEntityContext = null;
			if (subGoal.getEntityContext().equals(goal.getEntityContext())) {
				subGoalEntityContext = entityContext;
			} else {
				// TODO: subgoal tem context diferent?
			}
			generateGoalWorkItems(bwInstance, subGoal, subGoalEntityContext);
			
		}		
	}
}
