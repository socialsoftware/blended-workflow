package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService {

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow bw = BlendedWorkflow.getInstance();
		BWSpecification bwSpecification = bw.getBWSpecification("Medical Appointment");

		// BWInstance
		BWInstance bwInstance = new BWInstance(bwSpecification);
		System.out.println("Created BWInstance with id the \"" + bwInstance.getId() + "\"");
		System.out.println("DataModelInstance:");

		// EntityInstances
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		EntityInstance newEntityInstance = null;
		AttributeInstance attributeInstanceHackName = null;
		AttributeInstance attributeInstanceHackAge = null; 
		System.out.println("Entities");
		for (Entity entity : dataModelInstance.getEntities()) {
			newEntityInstance = new EntityInstance(dataModelInstance, entity);
			System.out.println("Entity \"" + entity.getName() + "\" gots " + entity.getEntityInstancesCount() + " instances, the last was created with the id \"" + newEntityInstance.getId() + "\"");

			// AttributeInstances
			for (Attribute attribute : entity.getAttributes()) {
				AttributeInstance newAttributeInstance = new AttributeInstance(attribute, newEntityInstance.getNewAttributeInstanceId());

				// FIXME Associate workitem with data
				System.out.println("Attribute \"" + attribute.getName() + "\" gots " + attribute.getAttributeInstancesCount() + " instances, the last was created with the id \"" + newAttributeInstance.getId() + "\"");
				if (attribute.getName().equals("Name")) {
					attributeInstanceHackName = newAttributeInstance;
				}
				if (attribute.getName().equals("Age")) {
					attributeInstanceHackAge = newAttributeInstance;
				}
			}
		}

		// RelationInstances
		System.out.println("Relations");
		Entity relationEntityOne;
		Entity relationEntityTwo;
		EntityInstance relationEntityInstanceOne;
		EntityInstance relationEntityInstanceTwo;
		for (Relation relation : dataModelInstance.getRelations()) {
			relationEntityOne = dataModelInstance.getEntity(relation.getEntityOne().getName()); // Relation EntityOne
			relationEntityTwo = dataModelInstance.getEntity(relation.getEntityTwo().getName()); // Relation EntityTwo

			relationEntityInstanceOne = relationEntityOne.getEntityInstance(); // EntityInstanceOne
			relationEntityInstanceTwo = relationEntityTwo.getEntityInstance(); // EntityInstanceTwo

			RelationInstance newRelationInstance = new RelationInstance(relation, relationEntityInstanceOne, relationEntityInstanceTwo, newEntityInstance.getNewRelationInstanceId());
			System.out.println("Relation \"" + relation.getName() + "\" gots " + relation.getRelationInstancesCount() + " instances, the last was created with the id \"" + newRelationInstance.getId() + "\"");
		}

		System.out.println("Goal Model:");
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (Goal goal : goalModelInstance.getGoals())
			System.out.println("Goal \"" + goal.getName() + "\" has " + goal.getSubGoals().size() + " subgoals.");

		// Create GoalWorkItems
		goalModelInstance.getEnabledWorkItems();

		// Print Created WorkItems
		System.out.println("Goal WorkItems:");
		for (WorkItem workItem : bwInstance.getWorkItems()) {
			System.out.println (workItem.getId());
			// FIXME Associate workitem with data
			if (workItem.getId().equals("Diagnose.1")) { 
				workItem.addAttributeInstances(attributeInstanceHackName);
			}
			if (workItem.getId().equals("Examine.1")) {
				workItem.addAttributeInstances(attributeInstanceHackAge);
			}
		}
	}

}