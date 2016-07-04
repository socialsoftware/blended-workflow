package pt.ist.socialsoftware.blendedworkflow.domain.activityworkitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PreConditionsHoldMethodTest extends TeardownRollbackTest {
	private static final String ENT_THREE_ROLE = "entThreeRole";
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
		defPathCondition = DefPathCondition.getDefPathCondition(spec, ENTITY_ONE + "." + ENT_TWO_ROLE);
		entityTwo = new Entity(spec.getDataModel(), "EntityTwo", false);
		relationBW = new RelationBW(spec.getDataModel(), "name", entity, ENT_ONE_ROLE, Cardinality.ONE, false,
				entityTwo, ENT_TWO_ROLE, "2..4", false);
		Entity entityThree = new Entity(spec.getDataModel(), "EntityThree", false);
		RelationBW relationBWTwo = new RelationBW(spec.getDataModel(), "otheName", entityTwo, ENT_TWO_ROLE,
				Cardinality.ONE, false, entityThree, ENT_THREE_ROLE, "2..4", false);

		activity = new Activity(spec.getActivityModel(), NAME, "description");
		activity.addPreCondition(defPathCondition);
		activity.addPostCondition(defAttributeCondition);

		workflowInstance = new WorkflowInstance(spec, NAME);
		activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
		entityInstance = new EntityInstance(workflowInstance, entity);
		attributeInstance = new AttributeInstance(entityInstance, attribute, "value");
	}

	@Test
	public void pastAttributeInstanceDefForEntityDef() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, relationBW);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.preConditionsHold();
		assertTrue(result);
	}

	@Test
	public void pastEntityInstanceDefForEntityDef() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, relationBW);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.preConditionsHold();
		assertTrue(result);
	}

	@Test
	public void pastAttributeInstanceForMissingProductInstance() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);
		postWorkItemArgument.addProductInstance(attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.preConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, bwe.getError());
		}
	}

}
