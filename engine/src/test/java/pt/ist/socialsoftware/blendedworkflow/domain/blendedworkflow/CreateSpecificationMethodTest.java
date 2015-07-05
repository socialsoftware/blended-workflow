package pt.ist.socialsoftware.blendedworkflow.domain.blendedworkflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class CreateSpecificationMethodTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        new Specification(DUP_NAME, "author", "description", "version", "UID");
    }

    @Test
    public void success() throws BWException {
        getBlendedWorkflow().createSpecification(SPEC_NAME);

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_NAME, spec.getName());
    }

    @Test
    public void duplicateName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(DUP_NAME);
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(EMPTY_NAME);
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(null);
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

}
