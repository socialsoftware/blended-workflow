package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateSpecificationServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";

    @Override
    public void populate4Test() throws BWException {
    }

    @Test
    public void success() throws BWException {

        CreateSpecificationService service = new CreateSpecificationService(SPEC_NAME);

        service.execute();

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .orElse(null);
        assertNotNull(spec);
        assertEquals(SPEC_NAME, spec.getName());
    }

}
