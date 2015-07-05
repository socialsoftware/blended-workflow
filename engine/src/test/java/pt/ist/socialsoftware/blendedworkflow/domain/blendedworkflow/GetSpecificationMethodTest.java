package pt.ist.socialsoftware.blendedworkflow.domain.blendedworkflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.integration.LocalSystemTest;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetSpecificationMethodTest extends BWDomainAndServiceTest {
    private static final Logger logger = LoggerFactory
            .getLogger(LocalSystemTest.class);

    private static final String SPEC_NAME = "Spec Name";
    private static final String NON_EXIST = "Non Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        new Specification(SPEC_NAME, "author", "description", "version", "UID");
    }

    @Test
    public void success() throws BWException {
        logger.info("GetSpecificationMethod::sucess");

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_NAME, spec.getName());
    }

    @Test
    public void nonExistName() throws BWException {
        Optional<Specification> spec = getBlendedWorkflow()
                .getSpecification(NON_EXIST);

        assertFalse(spec.isPresent());
    }

    @Test
    public void emptyName() throws BWException {
        Optional<Specification> spec = getBlendedWorkflow()
                .getSpecification(EMPTY_NAME);

        assertFalse(spec.isPresent());
    }

    @Test
    public void nullName() throws BWException {
        Optional<Specification> spec = getBlendedWorkflow()
                .getSpecification(null);

        assertFalse(spec.isPresent());
    }

}
