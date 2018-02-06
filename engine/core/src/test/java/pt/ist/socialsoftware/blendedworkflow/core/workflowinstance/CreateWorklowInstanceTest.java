package pt.ist.socialsoftware.blendedworkflow.core.workflowinstance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateWorklowInstanceTest extends TeardownRollbackTest {
	private static final String NAME = "name";

	Specification spec;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
	}

	@Test
	public void createInstance() {
		WorkflowInstance wi = new WorkflowInstance(this.spec, NAME);

		assertEquals(1, this.spec.getWorkflowInstanceSet().size());
		assertEquals(NAME, wi.getName());
	}

	@Test
	public void createInstanceDuplicateName() {
		new WorkflowInstance(this.spec, NAME);

		try {
			new WorkflowInstance(this.spec, NAME);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(NAME, bwe.getMessage());
		}
	}

	@Test
	public void createInstanceNullName() {
		try {
			new WorkflowInstance(this.spec, null);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
		}
	}

	@Test
	public void createInstanceEmptyName() {
		try {
			new WorkflowInstance(this.spec, "");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, bwe.getError());
		}
	}

}
