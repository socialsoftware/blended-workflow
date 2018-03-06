package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import sun.misc.REException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PositionTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;
    private Unit _unit;
    private List<Role> _roles;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _unit = new Unit(_resourceModel, "Unit", null);
        _roles = Arrays.asList(
            new Role(_resourceModel,"Role1", null),
            new Role(_resourceModel,"Role2", null)
        );
    }

    @Test
    public void success() throws RMException {
        new Position(_resourceModel, "Test", "Test", _unit);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals(position.getName(), "Test");
        assertEquals("Test", position.getDescription());
        assertEquals(position.getUnit().getName(), _unit.getName());
        assertTrue(position.getRoleSet().isEmpty());
        assertTrue(position.getCanDelegateWorkToSet().isEmpty());
        assertNull(position.getReportsTo());
    }

    @Test
    public void testPositionCreationWithRoles() throws RMException {
        new Position(_resourceModel, "Test", "Test", _unit, _roles, new ArrayList<>(), null);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals(position.getName(), "Test");
        assertEquals(position.getUnit().getName(), _unit.getName());

        assertNotNull(position.getRoleSet());
        assertEquals(position.getRoleSet().size(), _roles.size());

        position.getRoleSet().stream().forEach(r -> {
            if (!_roles.stream().anyMatch(rol -> rol.getName().equals(r.getName()))) {
                fail();
            }
        });

        assertTrue(position.getCanDelegateWorkToSet().isEmpty());
        assertNull(position.getReportsTo());
    }

    @Test
    public void testPositionCreationWithReports() throws RMException {
        Position reports = new Position(_resourceModel, "Pos1R", "Test", _unit);

        new Position(_resourceModel, "Test", "Test", _unit, new ArrayList<>(), new ArrayList<>(), reports);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals(position.getName(), "Test");
        assertEquals(position.getUnit().getName(), _unit.getName());

        assertTrue(position.getRoleSet().isEmpty());
        assertTrue(position.getCanDelegateWorkToSet().isEmpty());

        assertNotNull(position.getReportsTo());
        assertEquals(position.getReportsTo().getName(), reports.getName());
    }

    @Test
    public void testPositionCreationWithDelegates() throws RMException {
        List<Position> positions = Arrays.asList(
                new Position(_resourceModel, "Pos1", "Test", _unit),
                new Position(_resourceModel, "Pos2", "Test", _unit)
        );

        new Position(_resourceModel, "Test", "Test", _unit, new ArrayList<>(), positions, null);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals(position.getName(), "Test");
        assertEquals(position.getUnit().getName(), _unit.getName());

        assertTrue(position.getRoleSet().isEmpty());
        assertNull(position.getReportsTo());

        assertNotNull(position.getCanDelegateWorkToSet());
        assertEquals(position.getCanDelegateWorkToSet().size(), positions.size());

        position.getCanDelegateWorkToSet().stream().forEach(p -> {
            if (!positions.stream().anyMatch(pos -> pos.getName().equals(p.getName()))) {
                fail();
            }
        });
    }

    @Test
    public void testPositionCreationWithAll() throws RMException {
        List<Position> delegates = Arrays.asList(
                new Position(_resourceModel, "Pos1D", "Test", _unit),
                new Position(_resourceModel, "Pos2D", "Test", _unit)
        );

        Position reports = new Position(_resourceModel, "Pos1R", "Test", _unit);

        new Position(_resourceModel, "Test", "Test", _unit, _roles, delegates, reports);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals(position.getName(), "Test");
        assertEquals(position.getUnit().getName(), _unit.getName());

        assertNotNull(position.getRoleSet());
        assertEquals(position.getRoleSet().size(), _roles.size());

        position.getRoleSet().stream().forEach(r -> {
            if (!_roles.stream().anyMatch(rol -> rol.getName().equals(r.getName()))) {
                fail();
            }
        });

        assertNotNull(position.getCanDelegateWorkToSet());
        assertEquals(position.getCanDelegateWorkToSet().size(), delegates.size());

        position.getCanDelegateWorkToSet().stream().forEach(p -> {
            if (!delegates.stream().anyMatch(pos -> pos.getName().equals(p.getName()))) {
                fail();
            }
        });

        assertEquals(reports.getName(), position.getReportsTo().getName());
    }

    @Test(expected = RMException.class)
    public void testTwoPositionsWithSameName() throws RMException {
        new Position(_resourceModel, "Test", "Test", _unit);
        new Position(_resourceModel, "Test", "Test", _unit);
    }

    @Test(expected = RMException.class)
    public void testPositionWithNoName() throws RMException {
        new Position(_resourceModel, null, "Test", _unit);
    }

    @Test(expected = RMException.class)
    public void testPositionWithNoUnit() throws RMException {
        new Position(_resourceModel, "Test", "Test", null);
    }
}