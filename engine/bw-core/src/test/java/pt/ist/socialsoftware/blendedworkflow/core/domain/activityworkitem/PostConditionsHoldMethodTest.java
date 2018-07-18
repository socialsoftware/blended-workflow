package pt.ist.socialsoftware.blendedworkflow.core.domain.activityworkitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class PostConditionsHoldMethodTest extends TeardownRollbackTest {
	private static final String ENT_TWO_ROLE = "entTwoRole";
	private static final String ENT_ONE_ROLE = "entOneRole";
	private static final String ATTRIBUTE_ONE = "AttributeOne";
	private static final String ENTITY_ONE = "EntityOne";
	private static final String MY_SPEC = "My spec";
	private static final String NAME = "name";

	Specification spec = null;
	Entity entity;
	Entity entityTwo;
	DefEntityCondition defEntityCondition;
	Attribute attribute;
	DefAttributeCondition defAttributeCondition;
	DefPathCondition defPathCondition;
	RelationBW relationBW;

	Activity activity = null;

	WorkflowInstance workflowInstance = null;
	ActivityWorkItem activityWorkItem;
	EntityInstance entityInstance;
	AttributeInstance attributeInstance;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", MY_SPEC);
		this.entity = new Entity(this.spec.getDataModel(), ENTITY_ONE, false);
		this.defEntityCondition = DefEntityCondition.getDefEntityCondition(this.entity);
		this.attribute = new Attribute(this.spec.getDataModel(), this.entity, ATTRIBUTE_ONE, AttributeType.STRING,
				false);
		this.defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(this.attribute);
		this.defPathCondition = DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE);
		this.entityTwo = new Entity(this.spec.getDataModel(), "EntityTwo", false);
		this.relationBW = new RelationBW(this.spec.getDataModel(), "name", this.entity, ENT_ONE_ROLE, Cardinality.ONE,
				false, this.entityTwo, ENT_TWO_ROLE, "2..4", false);
		MulCondition.createMulConditions(this.relationBW);

		this.activity = new Activity(this.spec.getActivityModel(), NAME, "description");
		this.activity.addPreCondition(this.defPathCondition);
		this.activity.addPostCondition(this.defAttributeCondition);
		this.activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationBW, ENT_ONE_ROLE));
		this.activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationBW, ENT_TWO_ROLE));

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		this.entityInstance = new EntityInstance(this.workflowInstance, this.entity);
		this.attributeInstance = new AttributeInstance(this.entityInstance, this.attribute, "value");
	}

	@Test
	public void attributeInstanceHolds() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void attributeInstanceHoldsFailsInstance() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.postConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, bwe.getError());
		}

	}

	@Test
	public void attributeInstanceHoldsFailsNoValue() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		this.attributeInstance.setValue(null);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.postConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_DEFINED, bwe.getError());
		}

	}

	@Test
	public void attributeInstanceHoldsFailsValueEmpty() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		this.attributeInstance.setValue("");
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.postConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_DEFINED, bwe.getError());
		}

	}

	@Test
	public void entityInstanceHolds() {
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		EntityInstance entityInstanceThree = new EntityInstance(this.workflowInstance, this.entityTwo);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceThree, ENT_TWO_ROLE, this.relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void entityInstanceHoldsFailBellowCardinality() {
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.postConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
		}
	}

	@Test
	public void entityInstanceHoldsPassEqualTopCardinality() {
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		EntityInstance entityInstanceThree = new EntityInstance(this.workflowInstance, this.entityTwo);
		EntityInstance entityInstanceFour = new EntityInstance(this.workflowInstance, this.entityTwo);
		EntityInstance entityInstanceFive = new EntityInstance(this.workflowInstance, this.entityTwo);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceThree, ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceFour, ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceFive, ENT_TWO_ROLE, this.relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

}
