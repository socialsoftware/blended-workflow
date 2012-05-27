package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.HashMap;
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
		
		if (entityContextOID == null) {
			DataModelInstance dataModelInstance = this.bwInstance.getDataModelInstance();
			Entity entityContext = this.goal.getEntityContext();
			this.entityContext = new EntityInstance(dataModelInstance, entityContext);
		} else {
			this.entityContext = AbstractDomainObject.fromOID(entityContextOID);
		}
		
		//create relations for 1-1 relations
		defineSimpleRelations();
		
		generateGoalWorkItems(this.bwInstance, this.goal, this.entityContext);
		
//		if (neededEntityInstances.size() > 0) {
//			BlendedWorkflow.getInstance().getBwManager().notifyNeededEntityInstances(this.entityContext, this.neededEntityInstances);
//		}
		
		Transaction.commit();
		log.info("END");
		return "CreateGoalInstanceService:Sucess";
	}
	
	private void generateGoalWorkItems(BWInstance bwInstance, AchieveGoal goal, EntityInstance parentEntityInstanceContext) {
		log.info("GOAL: " + goal.getName() + " with Instance: " + parentEntityInstanceContext.getID());
		//Print RelationInstances:
		for (Relation r : this.bwInstance.getDataModelInstance().getRelations()) {
//			log.info(r.getName() + ": " + r.getEntityOne().getName() + "|" + r.getEntityTwo().getName());
			for (RelationInstance ri : r.getRelationInstances()) {
				log.info(ri.getID() + ": " + ri.getEntityInstanceOne().getID() + "|" + ri.getEntityInstanceTwo().getID());
			}
		}
		Boolean alreadyExists = false;
		GoalWorkItem newGoalWorkItem = null;
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		
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
			newGoalWorkItem = new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext);
//			newGoalWorkItem.setEntityInstanceContext(parentEntityInstanceContext);
		} else {
			//Exists a workItem with the same context
			for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
				if (goalWorkItem.getEntityInstanceContext().equals(parentEntityInstanceContext)) {
					alreadyExists = true;
				}
			}
			
			// If does not create
			if (!alreadyExists) {
				newGoalWorkItem = new GoalWorkItem(bwInstance, goal, parentEntityInstanceContext);
//				newGoalWorkItem.setEntityInstanceContext(parentEntityInstanceContext);
			}
			else {
				log.info("GoalWorkItem associated with " + parentEntityInstanceContext.getID() + " already exists.");
			}
		}

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
				
				//procurar se existe entityinstance ligada akele dados
//				for (RelationInstance relationInstanceOne : parentEntityInstanceContext.getEntityInstanceOneRelationInstances()) {
//				}
//				for (RelationInstance relationInstanceTwo : parentEntityInstanceContext.getEntityInstanceOneRelationInstances()) {			
//				}		
				
				//senao procura na relation pela relation e dado
				for (Relation relationAll : parentEntityContext.getRelations()) {
					Entity relationOne = relationAll.getEntityOne();
					Entity relationTwo = relationAll.getEntityTwo();
					
					// encontra a segunda
					if (parentEntityContext.equals(relationOne) && subEntityContext.equals(relationTwo)) {
						// se a relacoes nao tiver * em nenhum lado
						if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
							// se ja existir instancias
							if (subEntityContext.getEntityInstancesCount() > 0) {
								subGoalEntityInstanceContext = subEntityContext.getEntityInstances().get(0);
							} else {
								//create new
								subGoalEntityInstanceContext = new EntityInstance(dataModelInstance, relationTwo);
							}
							
							new RelationInstance(relationAll, parentEntityInstanceContext, subGoalEntityInstanceContext, parentEntityInstanceContext.getNewRelationInstanceID());
						}
					}
					
					// encontra a primeira
					if (parentEntityContext.equals(relationTwo) && subEntityContext.equals(relationOne)) {
						// se a relacoes nao tiver * em nenhum lado
						if (!relationAll.getCardinalityOne().equals(Cardinality.MANY) && !relationAll.getCardinalityOne().equals(Cardinality.MANY)) {
							// se ja existir instancias
							if (subEntityContext.getEntityInstancesCount() > 0) {
								subGoalEntityInstanceContext = subEntityContext.getEntityInstances().get(0);
							} else {
								//create new
								subGoalEntityInstanceContext = new EntityInstance(dataModelInstance, relationTwo);
							}
							new RelationInstance(relationAll, subGoalEntityInstanceContext, parentEntityInstanceContext, subGoalEntityInstanceContext.getNewRelationInstanceID());
						}
					}
					
					
				}
				//contar relations e ver tipo
				//se so existe uma relacao com o dado e relacao card for diferente de * e/ou *
					// infere automaticament
				//else tem de pedir
//				neededEntityInstances.put(entity, relation);
				
				//LEGACY
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
			generateGoalWorkItems(bwInstance, subGoal, subGoalEntityInstanceContext);
		}		
	}
	
	private void defineSimpleRelations() {
		log.info("******************1");
		if (entityContext.getEntity().getName().equals("Medical Prescription")) {
			log.info("******************2");
			long episodeOID = this.bwInstance.getDataModelInstance().getEntity("Episode").getEntityInstance("Episode.1").getOID();
			BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), episodeOID, this.entityContext.getOID());

		}
		
		if (entityContext.getEntity().getName().equals("Prescription Medication")) {
			log.info("******************23");
			long episodeOID = this.bwInstance.getDataModelInstance().getEntity("Medical Prescription").getEntityInstance("Medical Prescription.1").getOID();
			BlendedWorkflow.getInstance().getBwManager().addRelationInstance(this.bwInstance.getOID(), episodeOID, this.entityContext.getOID());

		}
		
//		for (Relation relation : entityContext.getRelations()) {
//			if (!relation.getCardinalityOne().equals(Cardinality.MANY) && !relation.getCardinalityOne().equals(Cardinality.MANY)) {
//				
//			}
//			
//			
//		}
		
//		
//			Entity relationEntityTwo = null;
//			EntityInstance relationEntityInstanceTwo = null;
//			// Relation Type Exists?
//			if (this.getRelationsCount() > 0) { 
//				for (Relation relation : this.getRelations()) {
//					// Get the other relation entity
//					for (Entity entity : relation.getEntities()) {
//						if (!this.getName().equals(entity.getName())) {
//							relationEntityTwo = entity; // entity2
//						}
//					}
//					// Relations instances already exists?
//					if (relation.getRelationInstances().isEmpty()) {
//						if (relationEntityTwo.getEntityInstances().isEmpty()){
//							relationEntityInstanceTwo = new EntityInstance(dataModelInstance, relationEntityTwo);
//						}
//						else {
//							for (EntityInstance entityInstance1 : relationEntityTwo.getEntityInstances()) { //FIXME only 1 entityInstance
//								relationEntityInstanceTwo = entityInstance1;
//							}
//						}
//						// Create Relation Instance and re-call the method for the 2 entity
//						EntityInstance relationInstanceOne;
//						EntityInstance relationInstanceTwo;
//						
//						if (entityInstance.getEntity().equals(relation.getEntityOne())) {
//							relationInstanceOne = entityInstance;
//							relationInstanceTwo = relationEntityInstanceTwo;
//						} else {
//							relationInstanceOne = relationEntityInstanceTwo;
//							relationInstanceTwo = entityInstance;
//						}
//						new RelationInstance(relation, relationInstanceOne, relationInstanceTwo, relationInstanceOne.getNewRelationInstanceID());
//
////						relationEntityTwo.createRelationInstances(dataModelInstance, relationEntityInstanceTwo);

	}
}
