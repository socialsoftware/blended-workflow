package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;

import static org.junit.Assert.*;

public class UnitTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignResourcesInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDto(SPEC_ID, SPEC_NAME));
        designer = DesignResourcesInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());
    }

    @Test
    public void success() throws RMException {
        new Unit(_resourceModel, "Test", "Test123");

        Unit unit = _resourceModel.getUnitSet()
                .stream()
                .filter(u -> u.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(unit);
        assertEquals(unit.getName(), "Test");
        assertEquals(unit.getDescription(), "Test123");
    }

    @Test(expected = RMException.class)
    public void testTwoRolesWithSameName() throws RMException {
        new Unit(_resourceModel, "Test", "Test123");
        new Unit(_resourceModel, "Test", "Test123");
    }

    @Test(expected = RMException.class)
    public void testRoleWithNoName() throws RMException {
        new Unit(_resourceModel, null, null);
    }

    @Test
    public void testRoleWithNoDescription() throws RMException {
        Unit unit = new Unit(_resourceModel, "Test", null);
        assertNull(unit.getDescription());
    }
}