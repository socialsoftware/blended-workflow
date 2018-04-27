package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;

import static org.junit.Assert.*;

public class CapabilityTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignResourcesInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignResourcesInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());
    }

    @Test
    public void success() throws RMException {
        new Capability(_resourceModel, "Test", "Test123");

        Capability capability = _resourceModel.getCapabilitySet()
                .stream()
                .filter(cap -> cap.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(capability);
        assertEquals(capability.getName(), "Test");
        assertEquals(capability.getDescription(), "Test123");
    }

    @Test(expected = RMException.class)
    public void testTwoCapabilitiesWithSameName() throws RMException {
        new Capability(_resourceModel, "Test", "Test123");
        new Capability(_resourceModel, "Test", "Test123");
    }

    @Test(expected = RMException.class)
    public void testCapabilityWithNoName() throws RMException {
        new Capability(_resourceModel, null, null);
    }

    @Test
    public void testCapabilityWithNoDescription() throws RMException {
        Capability capability = new Capability(_resourceModel, "Test", null);
        assertNull(capability.getDescription());
    }
}