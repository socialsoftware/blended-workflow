package pt.ist.socialsoftware.blendedworkflow.domain.workitemargument;

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
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		Entity entity = new Entity(spec.getDataModel(), ENTITY_NAME, false);
		Attribute attribute = new Attribute(spec.getDataModel(), entity, ATTRIBUTE_NAME, AttributeType.STRING, false,
				false, false);
		defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(attribute);
		goal = new Goal(spec.getGoalModel(), NAME);
		defPathCondition = DefPathCondition.getDefPathCondition(spec, ENTITY_NAME + "." + ATTRIBUTE_NAME);
		goal.addActivationCondition(defPathCondition);
		activity = new Activity(spec.getActivityModel(), NAME, "description");
		activity.addPreCondition(defPathCondition);

		workflowInstance = new WorkflowInstance(spec, NAME);
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entity);
		attributeInstance = new AttributeInstance(entityInstance, attribute, VALUE);
		goalWorkItem = new GoalWorkItem(workflowInstance, goal);
		activityWorkItem = new ActivityWorkItem(workflowInstance, activity);
	}

	@Test
	public void successPreGoal() {
		PreWorkItemArgument workItemArgument = new PreWorkItemArgument(goalWorkItem, defPathCondition);
		workItemArgument.addProductInstance(attributeInstance);

		assertEquals(goalWorkItem, workItemArgument.getWorkItemOfPre());
		assertEquals(defPathCondition, workItemArgument.getDefPathCondition());
		assertTrue(workItemArgument.getProductInstanceSet().contains(attributeInstance));
	}

	@Test
	public void successPostGoal() {
		PostWorkItemArgument workItemArgument = new PostWorkItemArgument(goalWorkItem, defAttributeCondition);

		assertEquals(goalWorkItem, workItemArgument.getWorkItemOfPost());
		assertEquals(defAttributeCondition, workItemArgument.getDefProductCondition());
		assertTrue(workItemArgument.getProductInstanceSet().isEmpty());
	}

	@Test
	public void successPreActivity() {
		PreWorkItemArgument workItemArgument = new PreWorkItemArgument(activityWorkItem, defPathCondition);
		workItemArgument.addProductInstance(attributeInstance);

		assertEquals(activityWorkItem, workItemArgument.getWorkItemOfPre());
		assertEquals(defPathCondition, workItemArgument.getDefPathCondition());
		assertTrue(workItemArgument.getProductInstanceSet().contains(attributeInstance));
	}

	@Test
	public void successPostActivity() {
		PostWorkItemArgument workItemArgument = new PostWorkItemArgument(activityWorkItem, defAttributeCondition);

		assertEquals(activityWorkItem, workItemArgument.getWorkItemOfPost());
		assertEquals(defAttributeCondition, workItemArgument.getDefProductCondition());
		assertTrue(workItemArgument.getProductInstanceSet().isEmpty());
	}

	@Test
	public void failPreProductConsistency() {
		Entity otherEntity = new Entity(spec.getDataModel(), OTHER_ENTITY_NAME, false);
		EntityInstance otherEntityInstance = new EntityInstance(workflowInstance, otherEntity);

		try {
			PreWorkItemArgument workItemArgument = new PreWorkItemArgument(goalWorkItem, defPathCondition);
			workItemArgument.addProductInstance(otherEntityInstance);

			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKITEMARGUMENT_CONSISTENCY, bwe.getError());
			assertEquals(ATTRIBUTE_NAME + "<>" + OTHER_ENTITY_NAME, bwe.getMessage());
		}
	}

	@Test
	public void failPreInstanceConsistency() {
		WorkflowInstance otherWorkflowInstance = new WorkflowInstance(spec, OTHER_NAME);
		GoalWorkItem otherGoalWorkItem = new GoalWorkItem(otherWorkflowInstance, goal);

		try {
			PreWorkItemArgument workItemArgument = new PreWorkItemArgument(otherGoalWorkItem, defPathCondition);
			workItemArgument.addProductInstance(attributeInstance);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKITEMARGUMENT_CONSISTENCY, bwe.getError());
			assertEquals(OTHER_NAME + "<>" + NAME, bwe.getMessage());
		}
	}

}
