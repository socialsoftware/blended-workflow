package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser;

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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class PrintBWSpecification {

	private static BlendedWorkflow blendedWorkflow;
	private static BWSpecification bwSpecification;
	private static DataModel dataModelTemplate;
	private static GoalModel goalModel;
	private static BWInstance bwInstance;
	private static DataModelInstance dataModelInstance;
	private static GoalModelInstance goalModelInstance;

	private PrintBWSpecification() {}

	public static void all(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		bwInstances(bwSpecificationName);
		dataModelTemplate(bwSpecificationName);
		dataModelType(bwSpecificationName);
		goalModelTemplate(bwSpecificationName);
		goalModelType(bwSpecificationName);
		dataModelInstances(bwSpecificationName);
		workItemsWithAttributtes(bwSpecificationName);
	}

	private static void init(String bwSpecificationName) throws BlendedWorkflowException {
		blendedWorkflow = BlendedWorkflow.getInstance();
		bwSpecification = blendedWorkflow.getBWSpecification(bwSpecificationName);
		dataModelTemplate = bwSpecification.getDataModel();
		goalModel = bwSpecification.getGoalModel();

		bwInstance = blendedWorkflow.getBWInstance(bwSpecificationName + ".1"); // FIXME only 1 instance
		dataModelInstance = bwInstance.getDataModelInstance();
		goalModelInstance = bwInstance.getGoalModelInstance();
	}

	public static void bwInstances(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("BWInstances:");
		System.out.println("----------------------------------------------------------");
		System.out.println(bwSpecification.getName() + " has " + bwSpecification.getBwInstancesCount() + " instance." );
		System.out.println(bwInstance.getId());
	}

	public static void dataModelTemplate(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("DataModel Template");
		System.out.println("----------------------------------------------------------");
		for (Entity entity : dataModelTemplate.getEntities()) {
			System.out.println("Entity \"" + entity.getName() + "\"");
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
		for (Goal goal : goalModel.getGoals()) {
			System.out.println("Goal \"" + goal.getName() + "\" is \"" + goal.getState() + "\" has " + goal.getSubGoals().size() + " subgoals.");
			System.out.println("Condition " + goal.getCondition().getClass());
		}
	}

	public static void goalModelType(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("GoalModel Type");
		System.out.println("----------------------------------------------------------");
		for (Goal goal : goalModelInstance.getGoals()) {
			System.out.println("Goal \"" + goal.getName() + "\" is \"" + goal.getState() + "\" has " + goal.getSubGoals().size() + " subgoals.");
			System.out.println("Condition " + goal.getCondition().getClass());
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
					System.out.println("EntityInstance \"" + entityInstance.getId() + "\" is associated with " + entityInstance.getAttributeInstancesCount() + " AttributesInstances.");
					for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
						System.out.println("AttributeInstance \"" + attributeInstance.getId() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");					}
				}
			}
			System.out.println("----------------------------------------------------------");
		}
		for (Relation relation : dataModelInstance.getRelations()) {
			System.out.println("Relation \"" + relation.getName() + "\" has " + relation.getRelationInstancesCount() + " instances.");
			if(relation.getRelationInstancesCount()>0) {
				for (RelationInstance relationInstance : relation.getRelationInstances()) {
					System.out.println("RelationInstance \"" + relationInstance.getId() + "\"");
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
			System.out.println("WorkItem \"" + workitem.getId() + "\" is " + workitem.getState() + " and is associated with " + workitem.getAttributeInstancesCount() + " attributeInstances.");
		}
	}
	
	public static void workItemsWithAttributtes(String bwSpecificationName) throws BlendedWorkflowException {
		init(bwSpecificationName);
		System.out.println("**************************************************************");
		System.out.println("WorkItems");
		System.out.println("----------------------------------------------------------");
		for (WorkItem workitem : bwInstance.getWorkItems()) {
			System.out.println("WorkItem \"" + workitem.getId() + "\" is " + workitem.getState() + " and is associated with " + workitem.getAttributeInstancesCount() + " attributeInstances.");
			if (workitem.getAttributeInstancesCount()>0) {
				for (AttributeInstance attributeInstance : workitem.getAttributeInstances()) {
					System.out.println("AttributeInstance \"" + attributeInstance.getId() + "\" with value \"" + attributeInstance.getValue() + "\" and state " + attributeInstance.getState() + ".");					}
			}
		}
	}
}