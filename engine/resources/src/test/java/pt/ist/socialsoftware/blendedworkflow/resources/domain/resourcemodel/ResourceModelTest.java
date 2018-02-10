package pt.ist.socialsoftware.blendedworkflow.resources.domain.resourcemodel;

import javafx.geometry.Pos;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ResourceModelTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    private Capability _capability1;

    private Role _role1;
    private Role _role2;

    private Unit _unit1;

    private Position _position1;
    private Position _position2;
    private Position _position3;
    private Position _position4;
    private Position _position5;
    private Position _position6;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _capability1 = new Capability(_resourceModel, "Cap1_Pop", null);

        _role1 = new Role(_resourceModel, "Role1_Pop", null);
        _role2 = new Role(_resourceModel, "Role2_Pop", null);

        _unit1 = new Unit(_resourceModel, "Unit1_Pop", null);

        _position1 = new Position(_resourceModel, "Pos1_Pop", "test", _unit1);
        _position2 = new Position(_resourceModel, "Pos2_Pop", "test", _unit1);
        _position3 = new Position(_resourceModel, "Pos3_Pop", "test", _unit1);
        _position4 = new Position(_resourceModel, "Pos4_Pop", "test", _unit1);
        _position5 = new Position(_resourceModel, "Pos5_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position2, _position3), _position6);
        _position6 = new Position(_resourceModel, "Pos6_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position1, _position5), _position3);
    }

    @Test
    public void success() throws RMException {
        designer.createResourceModel(spec.getSpecId());
        assertNotNull(spec.getResourceModel());
    }

    @Test
    public void testCleanResourceModel() throws RMException {
        _resourceModel.clean();

        assertTrue(_resourceModel.getCapabilitySet().size() == 0);
        assertTrue(_resourceModel.getPositionSet().size() == 0);
        assertTrue(_resourceModel.getUnitSet().size() == 0);
        assertTrue(_resourceModel.getRoleSet().size() == 0);
        // assertTrue(_resourceModel.getPersonSet().size() == 0);

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

    @Test
    public void testAddPosition() throws RMException {
        _resourceModel.addPosition("Test",
                "Test",
                _unit1.getName());

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(position);
        assertEquals("Test", position.getName());
        assertEquals("Test", position.getDescription());
        assertEquals(_unit1.getName(), position.getUnit().getName());
    }

    @Test
    public void testInitPositionWithRoles() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        _resourceModel.initPosition("Pos1_Pop",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                new ArrayList<>(),
                null);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Pos1_Pop"))
                .findFirst().get();

        assertNotNull(position.getRoleSet());
        assertEquals(2, position.getRoleSet().size());

        position.getRoleSet().stream().forEach(r -> {
            if (!roles.stream().anyMatch(rol -> rol.getName().equals(r.getName()))) {
                fail();
            }
        });
    }

    @Test
    public void testInitPositionWithDelegates() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        List<Position> delegates = Arrays.asList(_position1, _position2);
        _resourceModel.initPosition("Pos1_Pop",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                delegates.stream().map(p -> p.getName()).collect(Collectors.toList()),
                null);

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Pos1_Pop"))
                .findFirst().get();

        assertNotNull(position.getCanDelegateWorkToSet());
        assertEquals(2, position.getCanDelegateWorkToSet().size());

        position.getCanDelegateWorkToSet().stream().forEach(r -> {
            if (delegates.stream().noneMatch(pos -> pos.getName().equals(r.getName()))) {
                fail();
            }
        });
    }

    @Test
    public void testInitPositionReports() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        List<Position> delegates = Arrays.asList(_position1, _position2);
        _resourceModel.initPosition("Pos1_Pop",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                delegates.stream().map(p -> p.getName()).collect(Collectors.toList()),
                _position3.getName());

        Position position = _resourceModel.getPositionSet()
                .stream()
                .filter(p -> p.getName().equals("Pos1_Pop"))
                .findFirst().get();


        assertEquals(_position3.getName(), position.getReportsTo().getName());
    }

    @Test(expected = RMException.class)
    public void testAddPositionWithInvalidUnit() throws RMException {
        _resourceModel.addPosition("Pos1_Pop",
                "Test",
                "FakeName");
    }

    @Test(expected = RMException.class)
    public void testInitPositionWithInvalidRole() throws RMException {
        List<Position> delegates = Arrays.asList(_position1, _position2);
        _resourceModel.initPosition("Pos1_Pop",
                Arrays.asList("FakeName"),
                delegates.stream().map(p -> p.getName()).collect(Collectors.toList()),
                _position3.getName());
    }

    @Test(expected = RMException.class)
    public void testAddPositionWithInvalidDelegatesPosition() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        _resourceModel.initPosition("Pos1_Pop",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                Arrays.asList("FakeName"),
                _position3.getName());
    }

    @Test(expected = RMException.class)
    public void testAddPositionWithInvalidReportsPosition() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        List<Position> delegates = Arrays.asList(_position1, _position2);
        _resourceModel.initPosition("Pos1_Pop",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                delegates.stream().map(p -> p.getName()).collect(Collectors.toList()),
                "FakeName");
    }

    @Test(expected = RMException.class)
    public void testInitPositionWithInvalidName() throws RMException {
        List<Role> roles = Arrays.asList(_role1, _role2);
        List<Position> delegates = Arrays.asList(_position1, _position2);
        _resourceModel.initPosition("TestName",
                roles.stream().map(r -> r.getName()).collect(Collectors.toList()),
                delegates.stream().map(p -> p.getName()).collect(Collectors.toList()),
                "FakeName");
    }
}