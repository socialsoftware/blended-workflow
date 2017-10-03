package pt.ist.socialsoftware.blendedworkflow.workflowinstance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateWorklowInstanceTest extends TeardownRollbackTest {
	private static final String NAME = "name";

	Specification spec;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
	}

	@Test
	public void createInstance() {
		WorkflowInstance wi = new WorkflowInstance(spec, NAME);

		assertEquals(1, spec.getWorkflowInstanceSet().size());
		assertEquals(NAME, wi.getName());
	}

	@Test
	public void createInstanceDuplicateName() {
		new WorkflowInstance(spec, NAME);

		try {
			new WorkflowInstance(spec, NAME);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(NAME, bwe.getMessage());
		}
	}

	@Test
	public void createInstanceNullName() {
		try {
			new WorkflowInstance(spec, null);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
		}
	}

	@Test
	public void createInstanceEmptyName() {
		try {
			new WorkflowInstance(spec, "");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
		}
	}

}
