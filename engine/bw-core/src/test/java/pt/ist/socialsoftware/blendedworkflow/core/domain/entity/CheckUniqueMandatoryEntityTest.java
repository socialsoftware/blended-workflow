package pt.ist.socialsoftware.blendedworkflow.core.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CheckUniqueMandatoryEntityTest extends TeardownRollbackTest {
	private static final String ENTITY_NAME_ONE = "EntityOne";
	private static final String ENTITY_NAME_TWO = "EntityTwo";

	Specification spec = null;
	private Entity entityOne = null;
	private final Entity entityTwo = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_NAME_ONE, false, true);
	}

	@Test
	public void failTwoMandatoryEntities() {
		try {
			new Entity(this.spec.getDataModel(), ENTITY_NAME_TWO, false, true);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.NOT_UNIQUE_MANDATORY_ENTITY, bwe.getError());
		}
	}

}
