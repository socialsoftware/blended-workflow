package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class CreateGoalInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateGoalInstanceService");

	private BWInstance bwInstance;
	private AchieveGoal goal;
	private Long entityContextOID;
	private EntityInstance entityContext;
//	private HashMap<Entity, Relation> neededEntityInstances;

	public CreateGoalInstanceService (long bwInstanceOID, long goalOID, Long entityContextOID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goal = AbstractDomainObject.fromOID(goalOID);
		this.entityContextOID = entityContextOID;

	}

	@Override
	public String call() throws Exception {
		log.info("Start");

		Transaction.begin();
		//Create new EntityInstance
		if (entityContextOID == null) {
			DataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();
			Entity entityContext = this.goal.getEntityContext();
			this.entityContext = new EntityInstance(dataModelInstance, entityContext);
		} else {
			this.entityContext = AbstractDomainObject.fromOID(entityContextOID);
		}

		//Create RelationsInstances
		defineSimpleRelations();

		generateGoalWorkItems(this.bwInstance, this.goal, this.entityContext);

		//TODO: Ask User SubGoals Context.
//		if (neededEntityInstances.size() > 0) {
//			BlendedWorkflow.getInstance().getBwManager().notifyNeededEntityInstances(this.entityContext, this.neededEntityInstances);
//		}

		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}

	/**
	 * TODO: Refactor.
	 * Generate GoalWorkItems for All goals.
	 */
	private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal goal, EntityInstance parentEntityInstanceContext) {
		Boolean alreadyExists = false;
//		GoalWorkItem newGoalWorkItem = null;
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		/*************
		 * Goal
		 *************/
		//If parent goal has activated workItems change state to GOAL_PENDING
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
//			newGoalWorkItem.setEntityInstanceContext(parentEntityInstanceContext);
		} else {
			//Exists a workItem with the same context
			for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
				if (goalWorkItem.getEntityInstanceContext().equals(parentEntityInstanceContext)) {
					alreadyExists = true;
				}
			}

			if (!alreadyExists) {
				new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext);
//				newGoalWorkItem.setEntityInstanceContext(parentEntityInstanceContext);
			}
			else {
				log.info("GoalWorkItem associated with " + parentEntityInstanceContext.getID() + " already exists.");
			}
		}

		/*************
		 * SubGoals
		 *************/
		//Generate WorkItems for subgoals
		for (AchieveGoal subGoal : goal.getSubGoals()) {
			EntityInstance subGoalEntityInstanceContext = null;

			Entity parentEntityContext = goal.getEntityContext();
			Entity subEntityContext = subGoal.getEntityContext();
			//Context is the same
			if (subEntityContext.equals(parentEntityContext)) {
				subGoalEntityInstanceContext = parentEntityInstanceContext;
			} else {
				log.info("SubGoal context diferent than its parentGoal.");

				//Search for existing RelationInstances
//				for (RelationInstance relationInstanceOne : parentEntityInstanceContext.getEntityInstanceOneRelationInstances()) {
//				}
//				for (RelationInstance relationInstanceTwo : parentEntityInstanceContext.getEntityInstanceOneRelationInstances()) {			
//				}		

				//Search for Relations
				for (Relation relationAll : parentEntityContext.getRelations()) {
					Entity relationOne = relationAll.getEntityOne();
					Entity relationTwo = relationAll.getEntityTwo();

					//Search EntityOne side.
					if (parentEntityContext.equals(relationOne) && subEntityContext.equals(relationTwo)) {
						if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
							if (subEntityContext.getEntityInstancesCount() > 0) {
								subGoalEntityInstanceContext = subEntityContext.getEntityInstances().get(0);
							} else {
								subGoalEntityInstanceContext = new EntityInstance(dataModelInstance, relationTwo);
							}
							new RelationInstance(relationAll, parentEntityInstanceContext, subGoalEntityInstanceContext, parentEntityInstanceContext.getNewRelationInstanceID());
						}
					}

					//Search EntityTwo side.
					if (parentEntityContext.equals(relationTwo) && subEntityContext.equals(relationOne)) {
						if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
							if (subEntityContext.getEntityInstancesCount() > 0) {
								subGoalEntityInstanceContext = subEntityContext.getEntityInstances().get(0);
							} else {
								subGoalEntityInstanceContext = new EntityInstance(dataModelInstance, relationTwo);
							}
							new RelationInstance(relationAll, subGoalEntityInstanceContext, parentEntityInstanceContext, subGoalEntityInstanceContext.getNewRelationInstanceID());
						}
					}


				}

				//TODO:
//				neededEntityInstances.put(entity, relation);
			}

			//Recursive call for subGoals
			generateGoalWorkItems(bwInstance, subGoal, subGoalEntityInstanceContext);
		}		
	}

	/**
	 * TODO: Refactor.
	 * Define RelationInstances for Relations with Cardinality different from * in both sides.
	 */
	private void defineSimpleRelations() {
		if (entityContext.getEntity().getName().equals("Medical Prescription")) {
			long episodeOID = this.bwInstance.getDataModelInstance().getEntity("Episode").getEntityInstance("Episode.1").getOID();
			BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), episodeOID, this.entityContext.getOID());

		}

		if (entityContext.getEntity().getName().equals("Prescription Medication")) {
			long episodeOID = this.bwInstance.getDataModelInstance().getEntity("Medical Prescription").getEntityInstance("Medical Prescription.1").getOID();
			BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), episodeOID, this.entityContext.getOID());

		}
	}
}
