package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.OldBWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldDataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.OldEntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.OldMaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItem;

public class CreateGoalInstanceService implements Callable<String> {

	private static Logger log = LoggerFactory.getLogger("CreateGoalInstanceService");

	private final OldBWInstance bwInstance;
	private final Goal goal;
	private final Set<String> activateConditionsOID;
	private Set<DefPathCondition> activateConditions;
	private final Set<String> maintainGoalsOID;
	private Set<OldMaintainGoal> maintainGoals;
	private final Map<String, String> entitiesOID;
	private final Set<RelationBW> relations;

	// private ArrayList<String> relationsOID;

	// get relation
	public CreateGoalInstanceService(String bwInstanceOID, String goalOID, Set<String> activateConditionsOID,
			Set<String> maintainGoalsOID, Map<String, String> entitiesOID) {
		this.bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
		this.goal = FenixFramework.getDomainObject(goalOID);
		this.activateConditionsOID = activateConditionsOID;
		this.maintainGoalsOID = maintainGoalsOID;
		this.entitiesOID = entitiesOID;
		this.relations = new HashSet<RelationBW>();
	}

	@Override
	public String call() throws Exception {
		log.info("Start");

		Transaction.begin();

		OldDataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();

		// Get Key Relations if the Context is new
		if (this.entitiesOID.get(this.goal.getEntityContext().getExternalId()) == null) {
			for (RelationBW relation : this.goal.getEntityContext().getRelationSet()) {
				Entity one = relation.getEntityOne();
				Entity two = relation.getEntityTwo();
				if (this.goal.getEntityContext().equals(one) && relation.getIsTwoKeyEntity()) {
					this.relations.add(relation);
				}
				if (this.goal.getEntityContext().equals(two) && relation.getIsOneKeyEntity()) {
					this.relations.add(relation);
				}
			}
		}

		// Get SubGoals Relations if the goal has subgoals
		OldGoalModelInstance goalModelInstance = this.bwInstance.getGoalModelInstance();
		this.relations.addAll(goalModelInstance.getSubGoalsRelations(this.goal));

		// Create EntityInstances that do not exist
		for (Map.Entry<String, String> entry : entitiesOID.entrySet()) {
			if (entry.getValue() == null) {
				Entity entity = FenixFramework.getDomainObject(entry.getKey());
				OldEntityInstance newEntityInstance = new OldEntityInstance(entity);
				entry.setValue(newEntityInstance.getExternalId());
			}
		}

		// Parse Activate Conditions
		if (this.activateConditionsOID == null) {
			this.activateConditions = new HashSet<DefPathCondition>(this.goal.getActivationConditionSet());
		} else {
			this.activateConditions = new HashSet<DefPathCondition>();
			for (String activateConditionOID : this.activateConditionsOID) {
				DefPathCondition activateCondition = FenixFramework.getDomainObject(activateConditionOID);
				this.activateConditions.add(activateCondition);
			}
		}

		// Parse Maintain Goals
		if (this.maintainGoalsOID == null) {
			this.maintainGoals = new HashSet<OldMaintainGoal>(
					this.bwInstance.getGoalModelInstance().getAchieveGoalAssociatedMaintainGoals(this.goal));
		} else {
			this.maintainGoals = new HashSet<OldMaintainGoal>();
			for (String maintainGoalOID : this.maintainGoalsOID) {
				OldMaintainGoal maintainGoal = FenixFramework.getDomainObject(maintainGoalOID);
				this.maintainGoals.add(maintainGoal);
			}
		}

		// Create RelationInstances
		for (RelationBW relation : this.relations) {
			OldEntityInstance entityInstanceOne = FenixFramework
					.getDomainObject(this.entitiesOID.get(relation.getEntityOne().getExternalId()));
			OldEntityInstance entityInstanceTwo = FenixFramework
					.getDomainObject(this.entitiesOID.get(relation.getEntityTwo().getExternalId()));

			dataModelInstance.createRelationInstance(this.bwInstance, entityInstanceOne, entityInstanceTwo);
		}

		// Create GoalWorkitems for the goal and its subgoals
		createGoalWorkItems(this.bwInstance, this.goal, this.activateConditions, this.maintainGoals);

		// Evaluate New Workitem
		evaluateNew();

		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";

	}

	private void createGoalWorkItems(OldBWInstance bwInstance, Goal goal, Set<DefPathCondition> activateConditions,
			Set<OldMaintainGoal> maintainGoals) {
		// NOTE: If parent goal has activated workItems change their state to
		// ACTIVATED
		if (goal.getParentGoal().getOldGoalWorkItemSet().size() > 0) {
			for (OldGoalWorkItem goalWorkItem : goal.getParentGoal().getOldGoalWorkItemSet()) {
				if (goalWorkItem.getState().equals(GoalState.ENABLED)
						|| goalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
					goalWorkItem.notifyActivated();
				}
			}
		}

		// Create GoalWorkItem
		OldEntityInstance goalEntityInstanceContext = FenixFramework
				.getDomainObject(this.entitiesOID.get(goal.getEntityContext().getExternalId()));
		if (!goal.goalWorkItemsExistForTheContext(goalEntityInstanceContext)) {
			new OldGoalWorkItem(bwInstance, goal, goalEntityInstanceContext, activateConditions, maintainGoals);
		} else {
			log.info("GoalWorkItem associated with " + goalEntityInstanceContext.getID() + " already exists.");
		}

		// Recursive call for all subgoals
		for (Goal subGoal : goal.getSubGoalSet()) {
			Set<DefPathCondition> subGoalActivateConditions = new HashSet<DefPathCondition>(
					subGoal.getActivationConditionSet());
			Set<OldMaintainGoal> subGoalMaintainGoals = new HashSet<OldMaintainGoal>(
					bwInstance.getGoalModelInstance().getAchieveGoalAssociatedMaintainGoals(subGoal));
			createGoalWorkItems(bwInstance, subGoal, subGoalActivateConditions, subGoalMaintainGoals);
		}
	}

	private void evaluateNew() {
		OldGoalWorkItem parentGoalWorkItem = null;
		for (OldWorkItem workItem : this.bwInstance.getWorkItemsSet()) {
			if (workItem instanceof OldGoalWorkItem) {
				parentGoalWorkItem = (OldGoalWorkItem) workItem;
				if (parentGoalWorkItem.getGoal().equals(this.goal)) {
					break;
				}
			}
		}

		if (parentGoalWorkItem != null) {
			parentGoalWorkItem.evaluate();
		}

		if (parentGoalWorkItem != null && !parentGoalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
			for (OldWorkItem workItem : this.bwInstance.getWorkItemsSet()) {
				if (workItem instanceof OldGoalWorkItem) {
					OldGoalWorkItem goalWorkItem = (OldGoalWorkItem) workItem;
					if ((goalWorkItem != parentGoalWorkItem) && goalWorkItem.getState().equals(GoalState.NEW)) {
						goalWorkItem.evaluate();
					}
				}
			}
		}
	}

	/*****************************
	 * LEGACY
	 ****************************/
	// private String entityContextOID;
	// private EntityInstance entityContext;
	// private ArrayList<String> relationsEntityInstancesOID;

	/*
	 * public CreateGoalInstanceService (String bwInstanceOID, String goalOID,
	 * String entityContextOID, ArrayList<String> activateConditionsOID,
	 * ArrayList<String> maintainGoalsOID, ArrayList<String>
	 * relationsEntityInstancesOID) { this.bwInstance =
	 * FenixFramework.getDomainObject(bwInstanceOID); this.goal =
	 * FenixFramework.getDomainObject(goalOID); this.entityContextOID =
	 * entityContextOID; this.activateConditionsOID = activateConditionsOID;
	 * this.maintainGoalsOID = maintainGoalsOID;
	 * this.relationsEntityInstancesOID = relationsEntityInstancesOID; }
	 * 
	 * @Override public String call() throws Exception { log.info("Start");
	 * 
	 * Transaction.begin(); //Create new EntityInstance if (entityContextOID ==
	 * null) { DataModelInstance dataModelInstance =
	 * this.bwInstance.getDataModelInstance(); Entity entityContext =
	 * this.goal.getEntityContext(); this.entityContext = new
	 * EntityInstance(dataModelInstance, entityContext); } else {
	 * this.entityContext = FenixFramework.getDomainObject(entityContextOID); }
	 * 
	 * //Parse Conditions if (this.activateConditionsOID == null) {
	 * this.activateConditions = this.goal.getActivateConditions(); } else {
	 * this.activateConditions = new ArrayList<Condition>(); for (String
	 * activateConditionOID : this.activateConditionsOID) { Condition
	 * activateCondition = FenixFramework.getDomainObject(activateConditionOID);
	 * this.activateConditions.add(activateCondition); } }
	 * 
	 * if (this.maintainGoalsOID == null) { this.maintainGoals =
	 * this.bwInstance.
	 * getGoalModelInstance().getAchieveGoalAssociatedMaintainGoals(this.goal);
	 * } else { this.maintainGoals = new HashSet<MaintainGoal>(); for (String
	 * maintainGoalOID : this.maintainGoalsOID) { MaintainGoal maintainGoal =
	 * FenixFramework.getDomainObject(maintainGoalOID);
	 * this.maintainGoals.add(maintainGoal); } }
	 * 
	 * //FIXME: Create RelationsInstances // defineSimpleRelations();
	 * createRelationInstances(); generateGoalWorkItems(this.bwInstance,
	 * this.goal, this.entityContext, this.activateConditions,
	 * this.maintainGoals);
	 * 
	 * //TODO: Ask User SubGoals Context. // if (neededEntityInstances.size() >
	 * 0) { //
	 * BlendedWorkflow.getInstance().getBwManager().notifyNeededEntityInstances
	 * (this.entityContext, this.neededEntityInstances); // }
	 * 
	 * evaluateNew(); Transaction.commit(); log.info("END"); return
	 * "CreateGoalInstanceService:Sucess"; }
	 */

	/*
	 * private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal
	 * goal, EntityInstance parentEntityInstanceContext, List<Condition>
	 * activateConditions, Set<MaintainGoal> maintainGoals) { DataModelInstance
	 * dataModelInstance = bwInstance.getDataModelInstance();
	 * 
	 * //FIXME: If parent goal has activated workItems change state to ACTIVATED
	 * if (goal.getParentGoal().getGoalWorkItemsCount() > 0) { for (GoalWorkItem
	 * goalWorkItem : goal.getParentGoal().getGoalWorkItems()) { if
	 * (goalWorkItem.getState().equals(GoalState.ENABLED) ||
	 * goalWorkItem.getState().equals(GoalState.PRE_GOAL)) {
	 * goalWorkItem.notifyActivated(); } } }
	 * 
	 * //Create GoalWorkItem if (!goalWorkItemWithTheSameContext(goal,
	 * parentEntityInstanceContext)) { new GoalWorkItem(bwInstance, goal,
	 * parentEntityInstanceContext, activateConditions, maintainGoals); } else {
	 * log.info("GoalWorkItem associated with " +
	 * parentEntityInstanceContext.getID() + " already exists."); }
	 * 
	 * //Generate GoalWorkItems for subgoals for (AchieveGoal subGoal :
	 * goal.getSubGoals()) { Entity parentEntityContext =
	 * goal.getEntityContext(); Entity subEntityContext =
	 * subGoal.getEntityContext(); EntityInstance subGoalEntityInstanceContext =
	 * null;
	 * 
	 * //Context is the same if (subEntityContext.equals(parentEntityContext)) {
	 * subGoalEntityInstanceContext = parentEntityInstanceContext; } else {
	 * log.info("SubGoal context diferent than its parentGoal.");
	 * 
	 * //Search for existing RelationInstances
	 * 
	 * //FIXME: Search for Relations for (Relation relationAll :
	 * parentEntityContext.getRelations()) { Entity relationOne =
	 * relationAll.getEntityOne(); Entity relationTwo =
	 * relationAll.getEntityTwo();
	 * 
	 * //Search EntityOne side. if (parentEntityContext.equals(relationOne) &&
	 * subEntityContext.equals(relationTwo)) { if
	 * (!relationAll.getCardinalityOne().equals(Cardinality.MANY) &&
	 * !relationAll.getCardinalityOne().equals(Cardinality.MANY)) { if
	 * (!subEntityContext.getEntityInstances().isEmpty()) {
	 * subGoalEntityInstanceContext =
	 * subEntityContext.getEntityInstances().get(0); } else {
	 * subGoalEntityInstanceContext = new EntityInstance(dataModelInstance,
	 * relationTwo); } new RelationInstance(relationAll,
	 * parentEntityInstanceContext, subGoalEntityInstanceContext,
	 * parentEntityInstanceContext.getNewRelationInstanceID()); } } //Search
	 * EntityTwo side. if (parentEntityContext.equals(relationTwo) &&
	 * subEntityContext.equals(relationOne)) { if
	 * (!relationAll.getCardinalityOne().equals(Cardinality.MANY) &&
	 * !relationAll.getCardinalityOne().equals(Cardinality.MANY)) { if
	 * (!subEntityContext.getEntityInstances().isEmpty()) {
	 * subGoalEntityInstanceContext =
	 * subEntityContext.getEntityInstances().get(0); } else {
	 * subGoalEntityInstanceContext = new EntityInstance(dataModelInstance,
	 * relationTwo); } new RelationInstance(relationAll,
	 * subGoalEntityInstanceContext, parentEntityInstanceContext,
	 * subGoalEntityInstanceContext.getNewRelationInstanceID()); } } } }
	 * 
	 * //Recursive call for all subGoals List<Condition>
	 * subGoalActivateConditions = subGoal.getActivateConditions();
	 * Set<MaintainGoal> subGoalMaintainGoals =
	 * bwInstance.getGoalModelInstance()
	 * .getAchieveGoalAssociatedMaintainGoals(subGoal);
	 * generateGoalWorkItems(bwInstance, subGoal, subGoalEntityInstanceContext,
	 * subGoalActivateConditions, subGoalMaintainGoals); } }
	 */

	/*
	 * private void createRelationInstances() { DataModelInstance
	 * dataModelInstance = this.bwInstance.getDataModelInstance(); for (String
	 * entityTwoOID : this.relationsEntityInstancesOID) { EntityInstance
	 * entityTwo = FenixFramework.getDomainObject(entityTwoOID);
	 * dataModelInstance.createRelationInstance(this.bwInstance,
	 * this.entityContext, entityTwo); } }
	 */
	// FIXME: Define RelationInstances for Relations with Cardinality different
	// from * in both sides. DELETE
	// private void defineSimpleRelations() {
	// for (Relation relationAll : entityContext.getEntity().getRelations()) {
	// Entity relationOne = relationAll.getEntityOne();
	// Entity relationTwo = relationAll.getEntityTwo();
	//
	// if (entityContext.getEntity().equals(relationOne)) {
	// // if (!relationAll.getCardinalityTwo().equals(Cardinality.MANY) &&
	// !relationAll.getCardinalityTwo().equals(Cardinality.MANY)) {
	// // String entityTwoOID =
	// relationAll.getEntityTwo().getEntityInstances().get(0).getOID();
	// //
	// BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(),
	// this.entityContext.getOID(), entityTwoOID);
	// // }
	// }
	//
	// if (entityContext.getEntity().equals(relationTwo)) {
	// if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) &&
	// !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
	// String entityOneOID =
	// relationAll.getEntityOne().getEntityInstances().get(0).getOID();
	// //
	// BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(),
	// entityOneOID, this.entityContext.getOID());
	// log.debug("---------------------->" + relationAll.getName());
	// addRelationInstance(this.bwInstance.getOID(), entityOneOID,
	// this.entityContext.getOID());
	// }
	// }
	// }
	// }
}
