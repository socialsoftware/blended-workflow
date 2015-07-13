package pt.ist.socialsoftware.blendedworkflow.domain.blendedworkflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.integration.LocalSystemTest;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetSpecificationMethodTest extends BWDomainAndServiceTest {
    private static final Logger logger = LoggerFactory
            .getLogger(LocalSystemTest.class);

    private static final String SPEC_ID = "SpecId";
    private static final String SPEC_NAME = "Spec Name";
    private static final String NON_EXIST = "Non Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        new BWSpecification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");
    }

    @Test
    public void success() throws BWException {
        logger.info("GetSpecificationMethod::sucess");

        BWSpecification spec = getBlendedWorkflow().getSpecByName(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_NAME, spec.getName());
    }

    @Test
    public void nonExistName() throws BWException {
        Optional<BWSpecification> spec = getBlendedWorkflow()
                .getSpecByName(NON_EXIST);

        assertFalse(spec.isPresent());
    }

    @Test
    public void emptyName() throws BWException {
        Optional<BWSpecification> spec = getBlendedWorkflow()
                .getSpecByName(EMPTY_NAME);

        assertFalse(spec.isPresent());
    }

    @Test
    public void nullName() throws BWException {
        Optional<BWSpecification> spec = getBlendedWorkflow()
                .getSpecByName(null);

        assertFalse(spec.isPresent());
    }

}
