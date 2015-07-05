package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class CreateEntityServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_NAME, "author",
                "description", "version", "UID");
        new Entity(spec.getDataModel(), DUP_NAME);
    }

    @Test
    public void success() throws BWException {
        CreateEntity service = new CreateEntity(SPEC_NAME, ENTITY_NAME);
        service.execute();

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .get();
        Entity entity = spec.getDataModel().getEntity(ENTITY_NAME);
        assertNotNull(entity);
        assertEquals(ENTITY_NAME, entity.getName());
    }

    @Test
    public void nonExistentSpecification() throws BWException {
        CreateEntity service = new CreateEntity(NON_EXIST, ENTITY_NAME);

        try {
            service.execute();
            fail("non exist specification");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptySpecName() throws BWException {
        CreateEntity service = new CreateEntity(EMPTY_NAME, ENTITY_NAME);

        try {
            service.execute();
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        CreateEntity service = new CreateEntity(null, ENTITY_NAME);

        try {
            service.execute();
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

}
