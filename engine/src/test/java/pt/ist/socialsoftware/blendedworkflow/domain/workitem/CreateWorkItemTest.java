package pt.ist.socialsoftware.blendedworkflow.domain.workitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateWorkItemTest extends TeardownRollbackTest {
	private static final String MY_SPEC = "My spec";
	private static final String YOUR_SPEC = "Your spec";
	private static final String NAME = "name";
	private static final String OTHER_ENTITY_NAME = "otherEntityName";

	Specification spec = null;
	Goal goal = null;
	Activity activity = null;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		goal = new Goal(spec.getGoalModel(), NAME);
		activity = new Activity(spec.getActivityModel(), NAME, "description");

		workflowInstance = new WorkflowInstance(spec, NAME);
	}

	@Test
	public void successGoal() {
		GoalWorkItem goalWorkItem = new GoalWorkItem(workflowInstance, goal);

		assertEquals(workflowInstance, goalWorkItem.getWorkflowInstance());
		assertEquals(goal, goalWorkItem.getGoal());
	}

	@Test
	public void successActivity() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		assertEquals(workflowInstance, activityWorkItem.getWorkflowInstance());
		assertEquals(activity, activityWorkItem.getActivity());
	}

	@Test
	public void failGoal() {
		Specification otherSpec = new Specification("SpecIdOther", YOUR_SPEC, "author", "description", "version",
				"UID");
		Goal otherGoal = new Goal(otherSpec.getGoalModel(), NAME);

		try {
			new GoalWorkItem(workflowInstance, otherGoal);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_CONSISTENCY, bwe.getError());
			assertEquals(MY_SPEC + "<>" + YOUR_SPEC, bwe.getMessage());
		}
	}

	@Test
	public void failActivity() {
		Specification otherSpec = new Specification("SpecIdOther", YOUR_SPEC, "author", "description", "version",
				"UID");
		Activity otherActivity = new Activity(otherSpec.getActivityModel(), NAME, "description");

		try {
			new ActivityWorkItem(workflowInstance, otherActivity);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_CONSISTENCY, bwe.getError());
			assertEquals(MY_SPEC + "<>" + YOUR_SPEC, bwe.getMessage());
		}
	}

}
