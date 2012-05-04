package pt.ist.socialsoftware.blendedworkflow.shared;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class PrintBWSpecification {
	
	private static Logger log = Logger.getLogger("PrintBWSpecification");

	private static BlendedWorkflow blendedWorkflow;
	private static BWSpecification bwSpecification;
	private static DataModel dataModelTemplate;
	private static GoalModel goalModel;
	private static TaskModel taskModel;
	private static BWInstance bwInstance;
	private static DataModelInstance dataModelInstance;
	private static GoalModelInstance goalModelInstance;
	private static TaskModelInstance taskModelInstance;

	private PrintBWSpecification() {}

	public static void all(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		bwInstances(bwSpecificationName);
		dataModelTemplate(bwSpecificationName);
		dataModelType(bwSpecificationName);
		goalModelTemplate(bwSpecificationName);
		goalModelType(bwSpecificationName);
		taskModelTemplate(bwSpecificationName);
		taskModelType(bwSpecificationName);
		dataModelInstances(bwSpecificationName);
		workItemsWithAttributtes(bwSpecificationName);
	}

	private static void init(String bwSpecificationName) throws BlendedWorkflowException {
		blendedWorkflow = BlendedWorkflow.getInstance();
		bwSpecification = blendedWorkflow.getBWSpecification(bwSpecificationName);
		dataModelTemplate = bwSpecification.getDataModel();
		goalModel = bwSpecification.getGoalModel();
		taskModel = bwSpecification.getTaskModel();

		bwInstance = blendedWorkflow.getBWInstance(bwSpecificationName + ".1"); // FIXME only 1 instance
		dataModelInstance = bwInstance.getDataModelInstance();
		goalModelInstance = bwInstance.getGoalModelInstance();
		taskModelInstance = bwInstance.getTaskModelInstance();
	}

	public static void bwInstances(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("BWInstances:");
		log.info("----------------------------------------------------------");
		log.info(bwSpecification.getName() + " has " + bwSpecification.getBwInstancesCount() + " instance." );
		log.info(bwInstance.getID());
	}

	public static void dataModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("DataModel Template");
		log.info("----------------------------------------------------------");
		for (Entity entity : dataModelTemplate.getEntities()) {
			log.info("Entity \"" + entity.getName() + "\"");
			for (Attribute attribute : entity.getAttributes()) {
				log.info("Attribute \"" + attribute.getName() + "\"");
			}
			log.info("----------------------------------------------------------");
		}
		for (Relation relation : dataModelTemplate.getRelations()) {
			log.info("Relation \"" + relation.getName() + "\"");
		}
	}

	public static void dataModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("DataModel Type");
		log.info("----------------------------------------------------------");
		for (Entity entity : dataModelInstance.getEntities()) {
			log.info("Entity \"" + entity.getName() + "\"");
			for (Attribute attribute : entity.getAttributes()) {
				log.info("Attribute \"" + attribute.getName() + "\"");
			}
			log.info("----------------------------------------------------------");
		}
		for (Relation relation : dataModelInstance.getRelations()) {
			log.info("Relation \"" + relation.getName() + "\"");
		}
	}

	public static void goalModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("GoalModel Template");
		log.info("----------------------------------------------------------");
		for (Goal goal : goalModel.getGoals()) {
			log.info("Goal \"" + goal.getName() + "\" is \"" + goal.getState() + "\" has " + goal.getSubGoals().size() + " subgoals.");
//			log.info("Condition " + goal.getCondition().getClass());
			log.info("User: " + goal.getUser() + " Role:" + goal.getRole());
		}
	}

	public static void goalModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("GoalModel Type");
		log.info("----------------------------------------------------------");
		for (Goal goal : goalModelInstance.getGoals()) {
			log.info("Goal \"" + goal.getName() + "\" is \"" + goal.getState() + "\" has " + goal.getSubGoals().size() + " subgoals.");
//			log.info("Condition " + goal.getCondition().getClass());
			log.info("User: " + goal.getUser() + " Role:" + goal.getRole());
		}
	}
	
	public static void taskModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("TaskModel Template");
		log.info("----------------------------------------------------------");
		for (Task task : taskModel.getTasks()) {
			log.info("Task \"" + task.getName() + "\" is \"" + task.getState() + "\".");
			log.info("Pre Condition " + task.getPreConstraint().getClass() + " Post Condition " + task.getPostConstraint().getClass());
			log.info("User: " + task.getUser() + " Role:" + task.getRole());
		}
	}

	public static void taskModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("TaskModel Type");
		log.info("----------------------------------------------------------");
		for (Task task : taskModelInstance.getTasks()) {
			log.info("Task \"" + task.getName() + "\" is \"" + task.getState() + "\".");
			log.info("Pre Condition " + task.getPreConstraint().getClass() + " Post Condition " + task.getPostConstraint().getClass());
			log.info("User: " + task.getUser() + " Role:" + task.getRole());
		}
	}

	public static void dataModelInstances(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("DataModel Instance");
		log.info("----------------------------------------------------------");
		for (Entity entity : dataModelInstance.getEntities()) {
			log.info("Entity \"" + entity.getName() + "\" has " + entity.getEntityInstancesCount() + " instances.");
			if (entity.getEntityInstancesCount()>0) {
				for (EntityInstance entityInstance : entity.getEntityInstances()) {
					log.info("EntityInstance \"" + entityInstance.getID() + "\" is associated with " + entityInstance.getAttributeInstancesCount() + " AttributesInstances.");
					for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
						log.info("AttributeInstance \"" + attributeInstance.getID() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");					}
				}
			}
			log.info("----------------------------------------------------------");
		}
		for (Relation relation : dataModelInstance.getRelations()) {
			log.info("Relation \"" + relation.getName() + "\" has " + relation.getRelationInstancesCount() + " instances.");
			if(relation.getRelationInstancesCount()>0) {
				for (RelationInstance relationInstance : relation.getRelationInstances()) {
					log.info("RelationInstance \"" + relationInstance.getID() + "\"");
				}
			}
		}
	}	

	public static void workItems(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("WorkItems");
		log.info("----------------------------------------------------------");
		for (WorkItem workitem : bwInstance.getWorkItems()) {
			log.info("WorkItem \"" + workitem.getID() + "\" is " + workitem.getState() + " and is associated with " + workitem.getContraintViolationAttributeInstancesCount() + " attributeInstances.");
		}
	}
	
	public static void workItemsWithAttributtes(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		log.info("**************************************************************");
		log.info("WorkItems");
		log.info("----------------------------------------------------------");
		for (WorkItem workitem : bwInstance.getWorkItems()) {
			log.info("WorkItem \"" + workitem.getID() + "\" is " + workitem.getState() + " and is associated with " + workitem.getContraintViolationAttributeInstancesCount() + " attributeInstances.");
			if (workitem.getContraintViolationAttributeInstancesCount()>0) {
				for (AttributeInstance attributeInstance : workitem.getContraintViolationAttributeInstances()) {
					log.info("AttributeInstance \"" + attributeInstance.getID() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");					}
			}
		}
	}
}