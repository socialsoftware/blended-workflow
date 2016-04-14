package pt.ist.socialsoftware.blendedworkflow.domain.entityinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateEntityInstanceTest extends TeardownRollbackTest {
	private static final String NAME = "name";
	private static final String OTHER_ENTITY_NAME = "otherEntityName";
	Specification spec = null;
	WorkflowInstance workflowInstance = null;
	Entity entity = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		workflowInstance = new WorkflowInstance(spec, NAME);
		entity = new Entity(spec.getDataModel(), "entityName", false);
	}

	@Test
	public void success() {
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entity);

		assertEquals(workflowInstance, entityInstance.getWorkflowInstance());
		assertEquals(entity, entityInstance.getEntity());
	}

	@Test
	public void entityDoesNotBelongToSpecification() {
		Specification otherSpec = new Specification("OtherSpecId", "Other spec", "author", "description", "version",
				"UID");
		Entity otherEntity = new Entity(otherSpec.getDataModel(), OTHER_ENTITY_NAME, false);

		try {
			new EntityInstance(workflowInstance, otherEntity);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CREATE_ENTITY_INSTANCE, bwe.getError());
			assertEquals(NAME + ":" + OTHER_ENTITY_NAME, bwe.getMessage());

		}
	}

}
