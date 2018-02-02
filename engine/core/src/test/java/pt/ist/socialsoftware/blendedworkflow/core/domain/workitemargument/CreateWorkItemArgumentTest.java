package pt.ist.socialsoftware.blendedworkflow.core.domain.workitemargument;

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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateWorkItemArgumentTest extends TeardownRollbackTest {
	private static final String OTHER_NAME = "otherName";
	private static final String OTHER_ENTITY_NAME = "otherEntityName";
	private static final String ATTRIBUTE_NAME = "attributeName";
	private static final String ENTITY_NAME = "EntityName";
	private static final String VALUE = "value";
	private static final String MY_SPEC = "My spec";
	private static final String NAME = "name";

	Specification spec = null;
	DefAttributeCondition defAttributeCondition = null;
	DefPathCondition defPathCondition = null;
	Goal goal = null;
	Activity activity = null;

	WorkflowInstance workflowInstance = null;
	AttributeInstance attributeInstance = null;
	GoalWorkItem goalWorkItem = null;
	ActivityWorkItem activityWorkItem = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		Entity entity = new Entity(this.spec.getDataModel(), ENTITY_NAME, false);
		Attribute attribute = new Attribute(this.spec.getDataModel(), entity, ATTRIBUTE_NAME, AttributeType.STRING,
				false, false, false);
		this.defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(attribute);
		this.goal = new Goal(this.spec.getGoalModel(), NAME);
		this.defPathCondition = DefPathCondition.getDefPathCondition(this.spec, ENTITY_NAME + "." + ATTRIBUTE_NAME);
		this.goal.addActivationCondition(this.defPathCondition);
		this.activity = new Activity(this.spec.getActivityModel(), NAME, "description");
		this.activity.addPreCondition(this.defPathCondition);

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, entity);
		this.attributeInstance = new AttributeInstance(entityInstance, attribute, VALUE);
		this.goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);
		this.activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);
	}

	@Test
	public void successPreGoal() {
		PreWorkItemArgument workItemArgument = new PreWorkItemArgument(this.goalWorkItem, this.defPathCondition);
		workItemArgument.addProductInstance(this.attributeInstance);

		assertEquals(this.goalWorkItem, workItemArgument.getWorkItemOfPre());
		assertEquals(this.defPathCondition, workItemArgument.getDefPathCondition());
		assertTrue(workItemArgument.getProductInstanceSet().contains(this.attributeInstance));
	}

	@Test
	public void successPostGoal() {
		PostWorkItemArgument workItemArgument = new PostWorkItemArgument(this.goalWorkItem, this.defAttributeCondition);

		assertEquals(this.goalWorkItem, workItemArgument.getWorkItemOfPost());
		assertEquals(this.defAttributeCondition, workItemArgument.getDefProductCondition());
		assertTrue(workItemArgument.getProductInstanceSet().isEmpty());
	}

	@Test
	public void successPreActivity() {
		PreWorkItemArgument workItemArgument = new PreWorkItemArgument(this.activityWorkItem, this.defPathCondition);
		workItemArgument.addProductInstance(this.attributeInstance);

		assertEquals(this.activityWorkItem, workItemArgument.getWorkItemOfPre());
		assertEquals(this.defPathCondition, workItemArgument.getDefPathCondition());
		assertTrue(workItemArgument.getProductInstanceSet().contains(this.attributeInstance));
	}

	@Test
	public void successPostActivity() {
		PostWorkItemArgument workItemArgument = new PostWorkItemArgument(this.activityWorkItem,
				this.defAttributeCondition);

		assertEquals(this.activityWorkItem, workItemArgument.getWorkItemOfPost());
		assertEquals(this.defAttributeCondition, workItemArgument.getDefProductCondition());
		assertTrue(workItemArgument.getProductInstanceSet().isEmpty());
	}

	@Test
	public void failPreProductConsistency() {
		Entity otherEntity = new Entity(this.spec.getDataModel(), OTHER_ENTITY_NAME, false);
		EntityInstance otherEntityInstance = new EntityInstance(this.workflowInstance, otherEntity);

		try {
			PreWorkItemArgument workItemArgument = new PreWorkItemArgument(this.goalWorkItem, this.defPathCondition);
			workItemArgument.addProductInstance(otherEntityInstance);

			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, bwe.getError());
			assertEquals(ATTRIBUTE_NAME + "<>" + OTHER_ENTITY_NAME, bwe.getMessage());
		}
	}

	@Test
	public void failPreInstanceConsistency() {
		WorkflowInstance otherWorkflowInstance = new WorkflowInstance(this.spec, OTHER_NAME);
		GoalWorkItem otherGoalWorkItem = new GoalWorkItem(otherWorkflowInstance, this.goal);

		try {
			PreWorkItemArgument workItemArgument = new PreWorkItemArgument(otherGoalWorkItem, this.defPathCondition);
			workItemArgument.addProductInstance(this.attributeInstance);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, bwe.getError());
			assertEquals(OTHER_NAME + "<>" + NAME, bwe.getMessage());
		}
	}

}
