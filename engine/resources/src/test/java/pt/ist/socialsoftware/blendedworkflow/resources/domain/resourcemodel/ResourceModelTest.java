package pt.ist.socialsoftware.blendedworkflow.resources.domain.resourcemodel;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Unit;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

import static org.junit.Assert.*;

public class ResourceModelTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());
    }

    @Test
    public void success() throws RMException {
        designer.createResourceModel(spec.getSpecId());
        assertNotNull(spec.getResourceModel());
    }

    @Test
    public void testAddCapability() throws RMException {
        _resourceModel.addCapability("Test", "Test description");

        Capability capability = _resourceModel.getCapabilitySet()
                        .stream()
                        .filter(cap -> cap.getName().equals("Test"))
                        .findFirst().get();

        assertNotNull(capability);
        assertEquals(capability.getName(), "Test");
        assertEquals(capability.getDescription(), "Test description");
    }


    @Test
    public void testAddRole() throws RMException {
        _resourceModel.addRole("Test", "Test description");

        Role role = _resourceModel.getRoleSet()
                .stream()
                .filter(r -> r.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(role);
        assertEquals(role.getName(), "Test");
        assertEquals(role.getDescription(), "Test description");
    }

    @Test
    public void testAddUnit() throws RMException {
        _resourceModel.addUnit("Test", "Test description");

        Unit unit = _resourceModel.getUnitSet()
                .stream()
                .filter(u -> u.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(unit);
        assertEquals(unit.getName(), "Test");
        assertEquals(unit.getDescription(), "Test description");
    }

}