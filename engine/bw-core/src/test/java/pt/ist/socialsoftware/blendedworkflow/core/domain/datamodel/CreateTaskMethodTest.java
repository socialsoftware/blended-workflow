package pt.ist.socialsoftware.blendedworkflow.core.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateTaskMethodTest extends TeardownRollbackTest {
	private static final String ANOTHER_DESCRIPTION = "another description";
	private static final String SPEC_ID = "Spec ID";
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";

	ActivityModel taskModel;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification(SPEC_ID, "name");
		this.taskModel = spec.getActivityModel();
		new Activity(this.taskModel, TASK_ONE, "this is a description");
	}

	@Test
	public void success() {
		Activity task = this.taskModel.createActivity(TASK_TWO, ANOTHER_DESCRIPTION);

		assertEquals(TASK_TWO, task.getName());
		assertEquals(ANOTHER_DESCRIPTION, task.getDescription());
	}

	@Test
	public void duplicateName() {
		try {
			this.taskModel.createActivity(TASK_ONE, ANOTHER_DESCRIPTION);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ACTIVITY_NAME, bwe.getError());
			assertEquals(TASK_ONE, bwe.getMessage());
		}
	}

}
