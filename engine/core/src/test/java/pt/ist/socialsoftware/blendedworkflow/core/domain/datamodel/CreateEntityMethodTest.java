package pt.ist.socialsoftware.blendedworkflow.core.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateEntityMethodTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "Spec ID";
	private static final String ENTITY_NAME = "Entity Name";
	private static final String DUP_NAME = "Exists Name";
	private static final String EMPTY_NAME = "";

	DataModel dataModel = null;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification(SPEC_ID, "name");
		this.dataModel = spec.getDataModel();
		new Entity(this.dataModel, DUP_NAME, false);
	}

	@Test
	public void success() throws BWException {
		this.dataModel.createEntity(ENTITY_NAME, true, false);

		Specification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
		Entity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
		assertNotNull(entity);
		assertEquals(ENTITY_NAME, entity.getName());
		assertTrue(entity.getExists());
	}

	@Test
	public void duplicateName() throws BWException {
		try {
			this.dataModel.createEntity(DUP_NAME, false, false);
			fail("duplicateName");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
		}
	}

	@Test
	public void emptyName() throws BWException {
		try {
			this.dataModel.createEntity(EMPTY_NAME, false, false);
			fail("emptyName");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
		}
	}

	@Test
	public void nullName() throws BWException {
		try {
			this.dataModel.createEntity(null, false, false);
			fail("nullName");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
		}
	}

}
