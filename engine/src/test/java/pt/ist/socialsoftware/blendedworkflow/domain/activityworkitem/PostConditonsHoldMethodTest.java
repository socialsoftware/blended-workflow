package pt.ist.socialsoftware.blendedworkflow.domain.activityworkitem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PostConditonsHoldMethodTest extends TeardownRollbackTest {
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
		spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		entity = new Entity(spec.getDataModel(), ENTITY_ONE, false);
		defEntityCondition = DefEntityCondition.getDefEntityCondition(entity);
		attribute = new Attribute(spec.getDataModel(), entity, ATTRIBUTE_ONE, AttributeType.STRING, false, false,
				false);
		defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(attribute);
		defPathCondition = DefPathCondition.getDefPathCondition(spec, ENTITY_ONE);
		entityTwo = new Entity(spec.getDataModel(), "EntityTwo", false);
		relationBW = new RelationBW(spec.getDataModel(), "name", entity, ENT_ONE_ROLE, Cardinality.ONE, false,
				entityTwo, ENT_TWO_ROLE, "2..4", false);
		MulCondition.createMUlConditions(relationBW);

		activity = new Activity(spec.getActivityModel(), NAME, "description");
		activity.addPreCondition(defPathCondition);
		activity.addPostCondition(defAttributeCondition);
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(relationBW, ENT_ONE_ROLE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(relationBW, ENT_TWO_ROLE));

		workflowInstance = new WorkflowInstance(spec, NAME);
		activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		entityInstance = new EntityInstance(workflowInstance, entity);
		attributeInstance = new AttributeInstance(entityInstance, attribute, "value");
	}

	@Test
	public void attributeInstanceHolds() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void attributeInstanceHoldsFailsInstance() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertFalse(result);
	}

	@Test
	public void attributeInstanceHoldsFailsNoValue() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		postWorkItemArgument.addProductInstance(attributeInstance);
		attributeInstance.setValue(null);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertFalse(result);
	}

	@Test
	public void attributeInstanceHoldsFailsValueEmpty() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		postWorkItemArgument.addProductInstance(attributeInstance);
		attributeInstance.setValue("");
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertFalse(result);
	}

	@Test
	public void entityInstanceHolds() {
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, entityInstanceTwo, relationBW);
		new RelationInstance(entityInstance, entityInstanceThree, relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void entityInstanceHoldsFailBellowCardinality() {
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, entityInstanceTwo, relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertFalse(result);
	}

	@Test
	public void entityInstanceHoldsFailAboveCardinality() {
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceFour = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceFive = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceSix = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, entityInstanceTwo, relationBW);
		new RelationInstance(entityInstance, entityInstanceThree, relationBW);
		new RelationInstance(entityInstance, entityInstanceFour, relationBW);
		new RelationInstance(entityInstance, entityInstanceFive, relationBW);
		new RelationInstance(entityInstance, entityInstanceSix, relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertFalse(result);
	}

	@Test
	public void entityInstanceHoldsPassEqualTopCardinality() {
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceFour = new EntityInstance(workflowInstance, entityTwo);
		EntityInstance entityInstanceFive = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, entityInstanceTwo, relationBW);
		new RelationInstance(entityInstance, entityInstanceThree, relationBW);
		new RelationInstance(entityInstance, entityInstanceFour, relationBW);
		new RelationInstance(entityInstance, entityInstanceFive, relationBW);

		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.postConditionsHold();

		assertTrue(result);
	}

}
