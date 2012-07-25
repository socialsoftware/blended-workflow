package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class CreateGoalInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("XXX");

	private BWInstance bwInstance;
	private AchieveGoal goal;
	private Long entityContextOID;
	private EntityInstance entityContext;
	private ArrayList<Long> activateConditionsOID;
	private ArrayList<Long> maintainGoalsOID;
	private ArrayList<Long> relationsEntityInstancesOID;
	private List<Condition> activateConditions;
	private Set<MaintainGoal> maintainGoals; 

	public CreateGoalInstanceService (long bwInstanceOID, long goalOID, Long entityContextOID, ArrayList<Long> activateConditionsOID, ArrayList<Long> maintainGoalsOID, ArrayList<Long> relationsEntityInstancesOID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.goal = AbstractDomainObject.fromOID(goalOID);
		this.entityContextOID = entityContextOID;
		this.activateConditionsOID = activateConditionsOID;
		this.maintainGoalsOID = maintainGoalsOID;
		this.relationsEntityInstancesOID = relationsEntityInstancesOID;
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
		
		//Parse Conditions
		if (this.activateConditionsOID == null) {
			this.activateConditions = this.goal.getActivateConditions();
		} else {
			this.activateConditions = new ArrayList<Condition>();
			for (Long activateConditionOID : this.activateConditionsOID) {
				Condition activateCondition = AbstractDomainObject.fromOID(activateConditionOID);
				this.activateConditions.add(activateCondition);
			}
		}

		if (this.maintainGoalsOID == null) {
			this.maintainGoals = this.bwInstance.getGoalModelInstance().getAchieveGoalAssociatedMaintainGoals(this.goal);
		} else {
			this.maintainGoals = new HashSet<MaintainGoal>();
			for (Long maintainGoalOID : this.maintainGoalsOID) {
				MaintainGoal maintainGoal = AbstractDomainObject.fromOID(maintainGoalOID);
				this.maintainGoals.add(maintainGoal);
			}
		}
		
		//FIXME: Create RelationsInstances
//		defineSimpleRelations();
		createRelationInstances();
		generateGoalWorkItems(this.bwInstance, this.goal, this.entityContext, this.activateConditions, this.maintainGoals);

		//TODO: Ask User SubGoals Context.
//		if (neededEntityInstances.size() > 0) {
//			BlendedWorkflow.getInstance().getBwManager().notifyNeededEntityInstances(this.entityContext, this.neededEntityInstances);
//		}

		evaluateNew();
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}

	private void evaluateNew() {
		GoalWorkItem parentGoalWorkItem = null;
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem instanceof GoalWorkItem) {
				parentGoalWorkItem = (GoalWorkItem) workItem;
				if (parentGoalWorkItem.getAchieveGoal().equals(this.goal)) {
					break;
				}
			}
		}
		
		if (parentGoalWorkItem != null) {
			parentGoalWorkItem.evaluate();
		}
		
		if (parentGoalWorkItem != null && !parentGoalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
			for (WorkItem workItem : this.bwInstance.getWorkItems()) {
				if (workItem instanceof GoalWorkItem) {
					GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
					if ((goalWorkItem != parentGoalWorkItem) && goalWorkItem.getState().equals(GoalState.NEW)) {
						goalWorkItem.evaluate();
					}
				}
			}
		}
	}

	/**
	 * FIXME: Generate GoalWorkItems for All goals.
	 */
	private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal goal, EntityInstance parentEntityInstanceContext, List<Condition> activateConditions, Set<MaintainGoal> maintainGoals) {
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		//FIXME: If parent goal has activated workItems change state to ACTIVATED
		if (goal.getParentGoal().getGoalWorkItemsCount() > 0) {
			for (GoalWorkItem goalWorkItem : goal.getParentGoal().getGoalWorkItems()) {
				if (goalWorkItem.getState().equals(GoalState.ENABLED) || goalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
					goalWorkItem.notifyActivated();
				}
			}
		}
		
		//Create GoalWorkItem
		if (!goalWorkItemWithTheSameContext(goal, parentEntityInstanceContext)) {
			new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext, activateConditions, maintainGoals);
		} else {
			log.info("GoalWorkItem associated with " + parentEntityInstanceContext.getID() + " already exists.");
		}
		
		//Generate GoalWorkItems for subgoals
		for (AchieveGoal subGoal : goal.getSubGoals()) {
			Entity parentEntityContext = goal.getEntityContext();
			Entity subEntityContext = subGoal.getEntityContext();
			EntityInstance subGoalEntityInstanceContext = null;
	
			//Context is the same
			if (subEntityContext.equals(parentEntityContext)) {
				subGoalEntityInstanceContext = parentEntityInstanceContext;
			} else {
				log.info("SubGoal context diferent than its parentGoal.");
				
				//Search for existing RelationInstances

				//FIXME: Search for Relations
				for (Relation relationAll : parentEntityContext.getRelations()) {
					Entity relationOne = relationAll.getEntityOne();
					Entity relationTwo = relationAll.getEntityTwo();

					//Search EntityOne side.
					if (parentEntityContext.equals(relationOne) && subEntityContext.equals(relationTwo)) {
						if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
							if (!subEntityContext.getEntityInstances().isEmpty()) {
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
							if (!subEntityContext.getEntityInstances().isEmpty()) {
								subGoalEntityInstanceContext = subEntityContext.getEntityInstances().get(0);
							} else {
								subGoalEntityInstanceContext = new EntityInstance(dataModelInstance, relationTwo);
							}
							new RelationInstance(relationAll, subGoalEntityInstanceContext, parentEntityInstanceContext, subGoalEntityInstanceContext.getNewRelationInstanceID());
						}
					}
				}
			}

			//Recursive call for all subGoals
			List<Condition> subGoalActivateConditions = subGoal.getActivateConditions();
			Set<MaintainGoal> subGoalMaintainGoals = bwInstance.getGoalModelInstance().getAchieveGoalAssociatedMaintainGoals(subGoal);
			generateGoalWorkItems(bwInstance, subGoal, subGoalEntityInstanceContext, subGoalActivateConditions, subGoalMaintainGoals);
		}		
	}

	private Boolean goalWorkItemWithTheSameContext(AchieveGoal goal, EntityInstance parentEntityInstanceContext) {
		Boolean alreadyExists = false;
		for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
			if (goalWorkItem.getEntityInstanceContext().equals(parentEntityInstanceContext)) {
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}
	
	private void createRelationInstances() {
		DataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();
		for (Long entityTwoOID : this.relationsEntityInstancesOID) {
			EntityInstance entityTwo = AbstractDomainObject.fromOID(entityTwoOID);
			dataModelInstance.createRelationInstance(this.bwInstance, this.entityContext, entityTwo);
		}
	}
	
	//FIXME: Define RelationInstances for Relations with Cardinality different from * in both sides.
//	private void defineSimpleRelations() {	
//		for (Relation relationAll : entityContext.getEntity().getRelations()) {
//			Entity relationOne = relationAll.getEntityOne();
//			Entity relationTwo = relationAll.getEntityTwo();
//
//			if (entityContext.getEntity().equals(relationOne)) {
////				if (!relationAll.getCardinalityTwo().equals(Cardinality.MANY) && !relationAll.getCardinalityTwo().equals(Cardinality.MANY)) {
////					long entityTwoOID = relationAll.getEntityTwo().getEntityInstances().get(0).getOID();
////					BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), this.entityContext.getOID(), entityTwoOID);
////				}
//			}
//
//			if (entityContext.getEntity().equals(relationTwo)) {
//				if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
//					long entityOneOID = relationAll.getEntityOne().getEntityInstances().get(0).getOID();
////					BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), entityOneOID, this.entityContext.getOID());
//					log.debug("---------------------->" + relationAll.getName());
//					addRelationInstance(this.bwInstance.getOID(), entityOneOID, this.entityContext.getOID());
//				}
//			}
//		}
//	}
}
