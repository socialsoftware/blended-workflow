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
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", MY_SPEC);
		this.entity = new Entity(this.spec.getDataModel(), ENTITY_ONE, false);
		this.defEntityCondition = DefEntityCondition.getDefEntityCondition(this.entity);
		this.attribute = new Attribute(this.spec.getDataModel(), this.entity, ATTRIBUTE_ONE, AttributeType.STRING,
				false);
		this.defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(this.attribute);
		this.defPathCondition = DefPathCondition.getDefPathCondition(this.spec, ENTITY_ONE + "." + ENT_TWO_ROLE);
		this.entityTwo = new Entity(this.spec.getDataModel(), "EntityTwo", false);
		this.relationBW = new RelationBW(this.spec.getDataModel(), "name", this.entity, ENT_ONE_ROLE, Cardinality.ONE,
				false, this.entityTwo, ENT_TWO_ROLE, "2..4", false);
		Entity entityThree = new Entity(this.spec.getDataModel(), "EntityThree", false);
		new RelationBW(this.spec.getDataModel(), "otheName", this.entityTwo, ENT_TWO_ROLE, Cardinality.ONE, false,
				entityThree, ENT_THREE_ROLE, "2..4", false);

		this.activity = new Activity(this.spec.getActivityModel(), NAME, "description");
		this.activity.addPreCondition(this.defPathCondition);
		this.activity.addPostCondition(this.defAttributeCondition);

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
		this.entityInstance = new EntityInstance(this.workflowInstance, this.entity);
		this.attributeInstance = new AttributeInstance(this.entityInstance, this.attribute, "value");
	}

	@Test
	public void pastAttributeInstanceDefForEntityDef() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, this.defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.preConditionsHold();
		assertTrue(result);
	}

	@Test
	public void pastEntityInstanceDefForEntityDef() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, this.defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = activityWorkItem.preConditionsHold();
		assertTrue(result);
	}

	@Test
	public void pastAttributeInstanceForMissingProductInstance() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);

		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, this.defPathCondition);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo);
		preWorkItemArgument.addProductInstance(entityInstanceTwo);
		activityWorkItem.addPreCondition(preWorkItemArgument);

		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem,
				this.defAttributeCondition);
		postWorkItemArgument.addProductInstance(this.attributeInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);

		try {
			activityWorkItem.preConditionsHold();
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, bwe.getError());
		}
	}

}
