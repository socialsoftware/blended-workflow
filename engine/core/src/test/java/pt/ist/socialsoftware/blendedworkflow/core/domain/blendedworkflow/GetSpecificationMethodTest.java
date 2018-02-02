package pt.ist.socialsoftware.blendedworkflow.core.domain.blendedworkflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.integration.RestDesignInterfaceTest;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetSpecificationMethodTest extends TeardownRollbackTest {
	private static final Logger logger = LoggerFactory.getLogger(RestDesignInterfaceTest.class);

	private static final String SPEC_ID = "SpecId";
	private static final String SPEC_NAME = "Spec Name";
	private static final String NON_EXIST = "Non Exists Id";
	private static final String EMPTY_NAME = "";

	@Override
	public void populate4Test() throws BWException {
		new Specification(SPEC_ID, SPEC_NAME, "author", "description", "version", "UID");
	}

	@Test
	public void success() throws BWException {
		logger.info("GetSpecificationMethod::sucess");

		Specification spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
		assertNotNull(spec);
		assertEquals(SPEC_NAME, spec.getName());
	}

	@Test
	public void nonExistId() throws BWException {
		Optional<Specification> spec = getBlendedWorkflow().getSpecById(NON_EXIST);

		assertFalse(spec.isPresent());
	}

	@Test
	public void emptyId() throws BWException {
		Optional<Specification> spec = getBlendedWorkflow().getSpecById(EMPTY_NAME);

		assertFalse(spec.isPresent());
	}

	@Test
	public void nullId() throws BWException {
		Optional<Specification> spec = getBlendedWorkflow().getSpecById(null);

		assertFalse(spec.isPresent());
	}

}
