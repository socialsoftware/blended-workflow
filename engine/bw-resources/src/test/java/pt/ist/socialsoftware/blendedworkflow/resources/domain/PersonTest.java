package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PersonTest extends TeardownRollbackTest{
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignResourcesInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;
    private Unit _unit;
    private List<Role> _roles;
    private List<Position> _positions;
    private List<Capability> _capabilities;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDto(SPEC_ID, SPEC_NAME));
        designer = DesignResourcesInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _unit = new Unit(_resourceModel, "Unit", null);
        _roles = Arrays.asList(
                new Role(_resourceModel,"Role1", null),
                new Role(_resourceModel,"Role2", null)
        );

        _positions = Arrays.asList(
                new Position(_resourceModel, "Pos1", null, _unit, _roles, new ArrayList<>(), null),
                new Position(_resourceModel, "Pos2", null, _unit, _roles, new ArrayList<>(), null),
                new Position(_resourceModel, "Pos3", null, _unit, _roles, new ArrayList<>(), null)
        );

        _capabilities = Arrays.asList(
                new Capability(_resourceModel, "Cap1", null),
                new Capability(_resourceModel, "Cap2", null)
        );
    }

    @Test
    public void success() throws RMException {
        new Person(_resourceModel, "Test", "Test");

        Person person = _resourceModel.getPersonSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(person);
        assertEquals(person.getName(), "Test");
        assertEquals("Test", person.getDescription());
        assertTrue(person.getPositionSet().isEmpty());
        assertTrue(person.getCapabilitySet().isEmpty());
    }

    @Test
    public void testPersonCreationWithPositions() throws RMException {
        new Person(_resourceModel, "Test", "Test", _positions, new ArrayList<>());

        Person person = _resourceModel.getPersonSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(person);
        assertEquals(person.getName(), "Test");
        assertEquals("Test", person.getDescription());
        assertTrue(person.getCapabilitySet().isEmpty());

        assertNotNull(person.getPositionSet());
        assertEquals(person.getPositionSet().size(), _positions.size());
        person.getPositionSet().stream().forEach(p -> {
            if (!_positions.stream().anyMatch(pos -> pos.getName().equals(p.getName()))) {
                fail();
            }
        });
    }
    @Test
    public void testPersonCreationWithCapabilities() throws RMException {
        new Person(_resourceModel, "Test", "Test", new ArrayList<>(), _capabilities);

        Person person = _resourceModel.getPersonSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(person);
        assertEquals(person.getName(), "Test");
        assertEquals("Test", person.getDescription());
        assertTrue(person.getPositionSet().isEmpty());

        assertNotNull(person.getCapabilitySet());
        assertEquals(person.getCapabilitySet().size(), _capabilities.size());
        person.getCapabilitySet().stream().forEach(c -> {
            if (!_capabilities.stream().anyMatch(cap -> cap.getName().equals(c.getName()))) {
                fail();
            }
        });
    }

    @Test
    public void testPositionCreationWithAll() throws RMException {
        new Person(_resourceModel, "Test", "Test", _positions, _capabilities);

        Person person = _resourceModel.getPersonSet()
                .stream()
                .filter(p -> p.getName().equals("Test"))
                .findFirst().get();

        assertNotNull(person);
        assertEquals(person.getName(), "Test");
        assertEquals("Test", person.getDescription());

        assertNotNull(person.getPositionSet());
        assertEquals(person.getPositionSet().size(), _positions.size());
        person.getPositionSet().stream().forEach(p -> {
            if (!_positions.stream().anyMatch(pos -> pos.getName().equals(p.getName()))) {
                fail();
            }
        });

        assertNotNull(person.getCapabilitySet());
        assertEquals(person.getCapabilitySet().size(), _capabilities.size());
        person.getCapabilitySet().stream().forEach(c -> {
            if (!_capabilities.stream().anyMatch(cap -> cap.getName().equals(c.getName()))) {
                fail();
            }
        });
    }

    @Test(expected = RMException.class)
    public void testTwoPersonsWithSameName() throws RMException {
        new Person(_resourceModel, "Test", "Test");
        new Person(_resourceModel, "Test", "Test");
    }

    @Test(expected = RMException.class)
    public void testPersonWithNoName() throws RMException {
        new Person(_resourceModel, null, "Test");
    }

    @Test
    public void testDelete() throws RMException {
        Person person = new Person(_resourceModel, "Test", "Test");

        int numberPerson = _resourceModel.getPersonSet().size();

        person.delete();

        assertEquals(numberPerson - 1, _resourceModel.getPersonSet().size());

        assertFalse(_resourceModel.getPersonSet().stream().anyMatch(p -> p.getName().equals("Test")));
    }
}