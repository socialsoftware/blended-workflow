package pt.ist.socialsoftware.blendedworkflow.core.domain.workitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateWorkItemTest extends TeardownRollbackTest {
	private static final String MY_SPEC = "My spec";
	private static final String YOUR_SPEC = "Your spec";
	private static final String NAME = "name";

	Specification spec = null;
	ProductGoal goal = null;
	Activity activity = null;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", MY_SPEC);
		this.goal = new ProductGoal(this.spec.getGoalModel(), NAME, new HashSet<>());
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
		Specification otherSpec = new Specification("SpecIdOther", YOUR_SPEC);
		Goal otherGoal = new ProductGoal(otherSpec.getGoalModel(), NAME, null);

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
		Specification otherSpec = new Specification("SpecIdOther", YOUR_SPEC);
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
