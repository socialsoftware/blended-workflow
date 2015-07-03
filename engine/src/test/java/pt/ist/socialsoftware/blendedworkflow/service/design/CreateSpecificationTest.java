package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.service.BWServiceTest;

public class CreateSpecificationTest extends BWServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        new Specification(DUP_NAME, "author", "description", "version", "UID");
    }

    @Test
    public void success() throws BWException {

        CreateSpecification service = new CreateSpecification(SPEC_NAME);

        service.execute();

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_NAME, spec.getName());
    }

    @Test
    public void duplicateName() throws BWException {
        CreateSpecification service = new CreateSpecification(DUP_NAME);

        try {
            service.execute();
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        CreateSpecification service = new CreateSpecification(EMPTY_NAME);

        try {
            service.execute();
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        CreateSpecification service = new CreateSpecification(null);

        try {
            service.execute();
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

}
