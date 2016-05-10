package pt.ist.socialsoftware.blendedworkflow.domain.activityworkitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckArguentsCompletelyDefinedMethodTest extends TeardownRollbackTest {
	private static final String ATTRIBUTE_ONE = "AttributeOne";
	private static final String ENTITY_ONE = "EntityOne";
	private static final String MY_SPEC = "My spec";
	private static final String NAME = "name";

	Specification spec = null;
	Entity entity;
	Attribute attribute;
	Activity activity = null;

	WorkflowInstance workflowInstance = null;
	ActivityWorkItem activityWorkItem;
	EntityInstance entityInstance;
	AttributeInstance attributeInstance;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		entity = new Entity(spec.getDataModel(), ENTITY_ONE, false);
		attribute = new Attribute(spec.getDataModel(), entity, ATTRIBUTE_ONE, AttributeType.STRING, false, false,
				false);
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(attribute);

		activity = new Activity(spec.getActivityModel(), NAME, "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_ONE));
		activity.addPostCondition(defAttributeCondition);

		workflowInstance = new WorkflowInstance(spec, NAME);
		activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		entityInstance = new EntityInstance(workflowInstance, entity);
		attributeInstance = new AttributeInstance(entityInstance, attribute, "value");
	}

	@Test
	public void success() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem,
				DefPathCondition.getDefPathCondition(spec, ENTITY_ONE));
		preWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPreCondition(preWorkItemArgument);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(attribute));
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		activityWorkItem.checkArgumentsCompletelyDefined();
	}

	@Test
	public void preConditionNotDefined() {
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(attribute));
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.checkArgumentsCompletelyDefined();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.PRE_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

	@Test
	public void preConditionWronglyDefined() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem,
				DefPathCondition.getDefPathCondition(spec, ENTITY_ONE + "." + ATTRIBUTE_ONE));
		preWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPreCondition(preWorkItemArgument);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(attribute));
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.checkArgumentsCompletelyDefined();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.PRE_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

	@Test
	public void postConditionNotDefined() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem,
				DefPathCondition.getDefPathCondition(spec, ENTITY_ONE));
		preWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		try {
			activityWorkItem.checkArgumentsCompletelyDefined();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.POST_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

}
