package pt.ist.socialsoftware.blendedworkflow.core.domain.activityworkitem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", MY_SPEC);
		this.entity = new Entity(this.spec.getDataModel(), ENTITY_ONE, false);
		this.attribute = new Attribute(this.spec.getDataModel(), this.entity, ATTRIBUTE_ONE, AttributeType.STRING,
				false);
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(this.attribute);

		this.activity = new Activity(this.spec.getActivityModel(), NAME, "description");
		this.activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE));
		this.activity.addPostCondition(defAttributeCondition);

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		this.entityInstance = new EntityInstance(this.workflowInstance, this.entity, ProductInstanceState.DEFINED);
		this.attributeInstance = new AttributeInstance(this.entityInstance, this.attribute, "value", ProductInstanceState.DEFINED);
	}

	@Test
	public void success() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this.activityWorkItem,
				DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE));
		preWorkItemArgument.addProductInstance(this.entityInstance);
		this.activityWorkItem.addPreCondition(preWorkItemArgument);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(this.activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(this.attribute));
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		this.activityWorkItem.addPostCondition(postWorkItemArgument);

		// activityWorkItem.checkArgumentsCompletelyDefined(activity.getPreConditionSet(),
		// activity.getPostConditionSet());
	}

	@Test
	public void preConditionNotDefined() {
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(this.activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(this.attribute));
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		this.activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			// activityWorkItem.checkArgumentsCompletelyDefined(activity.getPreConditionSet(),
			// activity.getPostConditionSet());
			// fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.PRE_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

	@Test
	public void preConditionWronglyDefined() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this.activityWorkItem,
				DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE + "." + ATTRIBUTE_ONE));
		preWorkItemArgument.addProductInstance(this.attributeInstance);
		this.activityWorkItem.addPreCondition(preWorkItemArgument);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(this.activityWorkItem,
				DefAttributeCondition.getDefAttributeCondition(this.attribute));
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		this.activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			// activityWorkItem.checkArgumentsCompletelyDefined(activity.getPreConditionSet(),
			// activity.getPostConditionSet());
			// fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.PRE_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

	@Test
	public void postConditionNotDefined() {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this.activityWorkItem,
				DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE));
		preWorkItemArgument.addProductInstance(this.entityInstance);
		this.activityWorkItem.addPreCondition(preWorkItemArgument);

		try {
			// activityWorkItem.checkArgumentsCompletelyDefined(activity.getPreConditionSet(),
			// activity.getPostConditionSet());
			// fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.POST_WORK_ITEM_ARGUMENT, bwe.getError());

		}
	}

}
