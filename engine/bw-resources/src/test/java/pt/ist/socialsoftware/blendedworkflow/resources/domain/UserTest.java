package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UserTest extends TeardownRollbackTest {
    private static final String SPEC_ID_1 = "Spec ID 1";
    private static final String SPEC_ID_2 = "Spec ID 2";
    private static final String SPEC_NAME = "Spec Name 1";
    private DesignResourcesInterface designer;

    private Specification spec1;
    private ResourceModel resourceModel1;
    private BlendedWorkflow bw;
    private Specification spec2;
    private ResourceModel resourceModel2;

    @Override
    public void populate4Test() throws BWException {
        bw = BlendedWorkflow.getInstance();
        designer = DesignResourcesInterface.getInstance();

        spec1 = pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID_1, SPEC_NAME));
        resourceModel1 = designer.createResourceModel(spec1.getSpecId());

        spec2 = pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID_2, SPEC_NAME));
        resourceModel2 = designer.createResourceModel(spec2.getSpecId());
    }

    @Test
    public void testUserCreationWithOneSpec() {
        resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 1);
    }

    @Test
    public void testUserCreationWithTwoSpecsSamePerson() {
        resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        resourceModel2.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 1);
    }

    @Test
    public void testUserCreationWithTwoSpecsDiffPerson() {
        resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        resourceModel1.addPerson("Test2", "Test", new ArrayList(), new ArrayList());
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test2")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 2);
    }

    @Test
    public void testDeletePersonTwoUsers() {
        resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        resourceModel1.addPerson("Test2", "Test", new ArrayList(), new ArrayList()).delete();
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertTrue(!bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test2")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 1);
    }

    @Test
    public void testDeleteOnePersonOneUser() {
        resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        resourceModel2.addPerson("Test", "Test", new ArrayList(), new ArrayList()).delete();
        assertTrue(bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 1);
    }

    @Test
    public void testDeleteTwoPersonOneUser() {
        Person person1 = resourceModel1.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        Person person2 = resourceModel2.addPerson("Test", "Test", new ArrayList(), new ArrayList());
        person1.delete();
        person2.delete();
        assertTrue(!bw.getUsersSet().stream().filter(u -> u.getUsername().equals("Test")).findFirst().isPresent());
        assertEquals(bw.getUsersSet().size(), 0);
    }
}