package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;

import static org.junit.Assert.*;

public class RoleTest extends TeardownRollbackTest {
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
        new Role(_resourceModel, "Test", "Test123");

        Role role = _resourceModel.getRoleSet()
                .stream()
                .filter(r -> r.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(role);
        assertEquals(role.getName(), "Test");
        assertEquals(role.getDescription(), "Test123");
    }

    @Test(expected = RMException.class)
    public void testTwoRolesWithSameName() throws RMException {
        new Role(_resourceModel, "Test", "Test123");
        new Role(_resourceModel, "Test", "Test123");
    }

    @Test(expected = RMException.class)
    public void testRoleWithNoName() throws RMException {
        new Role(_resourceModel, null, null);
    }

    @Test
    public void testRoleWithNoDescription() throws RMException {
        Role role = new Role(_resourceModel, "Test", null);
        assertNull(role.getDescription());
    }
}