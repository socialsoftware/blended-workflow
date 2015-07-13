package pt.ist.socialsoftware.blendedworkflow.domain.blendedworkflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateSpecificationMethodTest extends BWDomainAndServiceTest {
    private static final String SPEC_ID = "SpecId";
    private static final String DUP_ID = "SpecIdDouble";
    private static final String SPEC_NAME = "Spec Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        new BWSpecification(DUP_ID, DUP_NAME, "author", "description", "version",
                "UID");
    }

    @Test
    public void success() throws BWException {
        getBlendedWorkflow().createSpecification(SPEC_ID, SPEC_NAME);

        BWSpecification spec = getBlendedWorkflow().getSpecByName(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_ID, spec.getSpecId());
        assertEquals(SPEC_NAME, spec.getName());
    }

    @Test
    public void duplicateName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(SPEC_ID, DUP_NAME);
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(SPEC_ID, EMPTY_NAME);
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(SPEC_ID, null);
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void duplicateSpecId() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(DUP_ID, SPEC_NAME);
            fail("duplicateId");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_ID, bwe.getError());
        }
    }

    @Test
    public void emptyId() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(EMPTY_NAME, SPEC_NAME);
            fail("emptyId");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_ID, bwe.getError());
        }
    }

    @Test
    public void nullId() throws BWException {
        try {
            getBlendedWorkflow().createSpecification(null, SPEC_NAME);
            fail("nullId");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_SPECIFICATION_ID, bwe.getError());
        }
    }

}
