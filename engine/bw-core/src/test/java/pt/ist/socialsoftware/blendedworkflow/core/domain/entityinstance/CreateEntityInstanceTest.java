package pt.ist.socialsoftware.blendedworkflow.core.domain.entityinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateEntityInstanceTest extends TeardownRollbackTest {
	private static final String NAME = "name";
	private static final String OTHER_ENTITY_NAME = "otherEntityName";
	Specification spec = null;
	WorkflowInstance workflowInstance = null;
	Entity entity = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.entity = new Entity(this.spec.getDataModel(), "entityName", false);
	}

	@Test
	public void success() {
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entity, ProductInstanceState.DEFINED);

		assertEquals(this.workflowInstance, entityInstance.getWorkflowInstance());
		assertEquals(this.entity, entityInstance.getEntity());
	}

	@Test
	public void entityDoesNotBelongToSpecification() {
		Specification otherSpec = new Specification("OtherSpecId", "Other spec");
		Entity otherEntity = new Entity(otherSpec.getDataModel(), OTHER_ENTITY_NAME, false);

		try {
			new EntityInstance(this.workflowInstance, otherEntity, ProductInstanceState.DEFINED);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ENTITYINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(NAME + ":" + OTHER_ENTITY_NAME, bwe.getMessage());

		}
	}

}
