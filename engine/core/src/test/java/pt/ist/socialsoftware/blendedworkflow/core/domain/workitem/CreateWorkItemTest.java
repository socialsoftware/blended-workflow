package pt.ist.socialsoftware.blendedworkflow.core.domain.workitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		this.goal = new Goal(this.spec.getGoalModel(), NAME);
		this.activity = new Activity(this.spec.getActivityModel(), NAME, "description");

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
	}

	@Test
	public void successGoal() {
		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);

		assertEquals(this.workflowInstance, goalWorkItem.getWorkflowInstance());
		assertEquals(this.goal, goalWorkItem.getGoal());
	}

	@Test
	public void successActivity() {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(this.workflowInstance, this.activity);

		assertEquals(this.workflowInstance, activityWorkItem.getWorkflowInstance());
		assertEquals(this.activity, activityWorkItem.getActivity());
	}

	@Test
	public void failGoal() {
		Specification otherSpec = new Specification("SpecIdOther", YOUR_SPEC, "author", "description", "version",
				"UID");
		Goal otherGoal = new Goal(otherSpec.getGoalModel(), NAME);

		try {
			new GoalWorkItem(this.workflowInstance, otherGoal);
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
			new ActivityWorkItem(this.workflowInstance, otherActivity);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORK_ITEM_CONSISTENCY, bwe.getError());
			assertEquals(MY_SPEC + "<>" + YOUR_SPEC, bwe.getMessage());
		}
	}

}
