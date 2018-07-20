package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

public class CreateSpecificationServiceTest extends TeardownRollbackTest {
	private static final String SPEC_ID = "Spec ID";
	private static final String SPEC_NAME = "Spec Name";

	private final ModulesFactory factory = new ModulesFactory();

	@Override
	public void populate4Test() throws BWException {
	}

	@Test
	public void success() throws BWException {
		this.factory.createDesignInterface().createSpecification(new SpecDto(SPEC_ID, SPEC_NAME));

		Specification spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
		assertNotNull(spec);
		assertEquals(SPEC_ID, spec.getSpecId());
		assertEquals(SPEC_NAME, spec.getName());
	}

}
