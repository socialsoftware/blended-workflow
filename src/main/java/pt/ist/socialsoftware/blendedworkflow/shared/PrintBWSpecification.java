package pt.ist.socialsoftware.blendedworkflow.shared;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class PrintBWSpecification {
	
//	private static Logger log = Logger.getLogger("PrintBWSpecification");

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

		bwInstance = blendedWorkflow.getBWInstance(bwSpecificationName + ".1"); //TODO: only 1 instance
		dataModelInstance = bwInstance.getDataModelInstance();
		goalModelInstance = bwInstance.getGoalModelInstance();
		taskModelInstance = bwInstance.getTaskModelInstance();
	}

	public static void bwInstances(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("BWInstances:");
		System.out.println("----------------------------------------------------------");
		System.out.println(bwSpecification.getName() + " has " + bwSpecification.getBwInstancesCount() + " instance." );
		System.out.println(bwInstance.getID());
	}

	public static void dataModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("DataModel Template");
		System.out.println("----------------------------------------------------------");
		for (Entity entity : dataModelTemplate.getEntities()) {
			System.out.println("Entity \"" + entity.getName() + "\" has " + entity.getEntityInstancesCount() + " Instances");
			for (EntityInstance ei : entity.getEntityInstances()) {
				System.out.println("EntityInstance \"" + ei.getID() + "\" is " + ei.getState());
			}
			
			for (Attribute attribute : entity.getAttributes()) {
				System.out.println("Attribute \"" + attribute.getName() + "\"");
			}
			System.out.println("----------------------------------------------------------");
		}
		for (Relation relation : dataModelTemplate.getRelations()) {
			System.out.println("Relation \"" + relation.getName() + "\"");
		}
	}

	public static void dataModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("DataModel Type");
		System.out.println("----------------------------------------------------------");
		for (Entity entity : dataModelInstance.getEntities()) {
			System.out.println("Entity \"" + entity.getName() + "\"");

			for (Attribute attribute : entity.getAttributes()) {
				System.out.println("Attribute \"" + attribute.getName() + "\"");
			}
			System.out.println("----------------------------------------------------------");
		}
		for (Relation relation : dataModelInstance.getRelations()) {
			System.out.println("Relation \"" + relation.getName() + "\"");
		}
	}

	public static void goalModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("GoalModel Template");
		System.out.println("----------------------------------------------------------");
		for (AchieveGoal goal : goalModel.getAchieveGoals()) {
			System.out.println("Goal \"" + goal.getName() + "\" has \"" + goal.getGoalWorkItemsCount() + "\" WorkItems and has " + goal.getSubGoalsCount() + " subgoals.");
			for (Condition ac : goal.getActivateConditions()) {
				System.out.println("ActivateCondition: " + ac);	
			}
			System.out.println("SucessCondition: " + goal.getSucessCondition());
			System.out.println("User: " + goal.getUser().getID() + " Role: " + goal.getRole().getName());
		}
	}

	public static void goalModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("GoalModel Type");
		System.out.println("----------------------------------------------------------");
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			System.out.println("Goal \"" + goal.getName() + "\" has \"" + goal.getGoalWorkItemsCount() + "\" WorkItems and has " + goal.getSubGoals().size() + " subgoals.");
			for (Condition ac : goal.getActivateConditions()) {
				System.out.println("ActivateCondition: " + ac);	
			}
			System.out.println("SucessCondition: " + goal.getSucessCondition());
			System.out.println("User: " + goal.getUser().getID() + " Role: " + goal.getRole().getName());
		}
	}
	
	public static void taskModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("TaskModel Template");
		System.out.println("----------------------------------------------------------");
		for (Task task : taskModel.getTasks()) {
			System.out.println("Task \"" + task.getName() + "\".");
			System.out.println("Pre Condition: " + task.getPreConstraint());
			System.out.println("Post Condition: " + task.getPostConstraint());
			System.out.println("User: " + task.getUser().getID() + " Role: " + task.getRole().getName());
		}
	}

	public static void taskModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("TaskModel Type");
		System.out.println("----------------------------------------------------------");
		for (Task task : taskModelInstance.getTasks()) {
			System.out.println("Task \"" + task.getName() + "\".");
			System.out.println("Pre Condition: " + task.getPreConstraint());
			System.out.println("Post Condition: " + task.getPostConstraint());
			System.out.println("User: " + task.getUser().getID() + " Role: " + task.getRole().getName());
		}
	}

	public static void dataModelInstances(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("DataModel Instance");
		System.out.println("----------------------------------------------------------");
		for (Entity entity : dataModelInstance.getEntities()) {
			System.out.println("Entity \"" + entity.getName() + "\" has " + entity.getEntityInstancesCount() + " instances.");
			if (entity.getEntityInstancesCount()>0) {
				for (EntityInstance entityInstance : entity.getEntityInstances()) {
					System.out.println("EntityInstance \"" + entityInstance.getID() + "\" is associated with " + entityInstance.getAttributeInstancesCount() + " AttributesInstances.");
					for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
						System.out.println("AttributeInstance \"" + attributeInstance.getID() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");					}
				}
			}
			System.out.println("----------------------------------------------------------");
		}
		for (Relation relation : dataModelInstance.getRelations()) {
			System.out.println("Relation \"" + relation.getName() + "\" has " + relation.getRelationInstancesCount() + " instances.");
			System.out.println("E1 \"" + relation.getEntityOne().getName() + "\"");
			System.out.println("E2 \"" + relation.getEntityTwo().getName() + "\"");
			if(relation.getRelationInstancesCount()>0) {
				for (RelationInstance relationInstance : relation.getRelationInstances()) {
					System.out.println("RelationInstance \"" + relationInstance.getID() + "\"");
					System.out.println("EI1 \"" + relationInstance.getEntityInstanceOne().getID() + "\"");
					System.out.println("EI2 \"" + relationInstance.getEntityInstanceTwo().getID() + "\"");
				}
			}
		}
	}	

	public static void workItems(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("WorkItems");
		System.out.println("----------------------------------------------------------");
		for (WorkItem workitem : bwInstance.getWorkItems()) {
			if (workitem instanceof GoalWorkItem) {
				GoalWorkItem goalWorkItem = (GoalWorkItem) workitem;
				System.out.println("GoalWorkItem \"" + workitem.getID() + "\" is " + goalWorkItem.getState() + " and is associated with " + workitem.getOutputAttributeInstancesCount() + " attributeInstances.");
			}
			
			if (workitem instanceof TaskWorkItem) {
				TaskWorkItem taskWorkItem = (TaskWorkItem) workitem;
				System.out.println("GoalWorkItem \"" + workitem.getID() + "\" is " + taskWorkItem.getState() + " and is associated with " + workitem.getOutputAttributeInstancesCount() + " attributeInstances.");
			}
		}
	}
	
	public static void workItemsWithAttributtes(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("WorkItems with Attributes");
		System.out.println("----------------------------------------------------------");
		for (WorkItem workitem : bwInstance.getWorkItems()) {
			if (workitem instanceof TaskWorkItem) {
				TaskWorkItem taskWorkItem = (TaskWorkItem) workitem;
				System.out.println("TaskWorkItem \"" + workitem.getID() + "\" is " + taskWorkItem.getState());
			}
			if (workitem instanceof GoalWorkItem) {
				GoalWorkItem goalWorkItem = (GoalWorkItem) workitem;
				System.out.println("GoalWorkItem \"" + workitem.getID() + "\" is " + goalWorkItem.getState());
			}

			System.out.println("Input: Associated with " + workitem.getInputAttributeInstancesCount() + " attributeInstances.");
			if (workitem.getInputAttributeInstancesCount()>0) {
				for (AttributeInstance attributeInstance : workitem.getInputAttributeInstances()) {
					System.out.println("EI" + attributeInstance.getEntityInstance().getID() + "AttributeInstance \"" + attributeInstance.getID() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");
				}
			}
			
			System.out.println("Output: Associated with " + workitem.getOutputAttributeInstancesCount() + " attributeInstances.");
			if (workitem.getOutputAttributeInstancesCount()>0) {
				for (AttributeInstance attributeInstance : workitem.getOutputAttributeInstances()) {
					System.out.println("EI: " + attributeInstance.getEntityInstance().getID() + " AttributeInstance \"" + attributeInstance.getID() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");
				}
			}
		}
	}
}