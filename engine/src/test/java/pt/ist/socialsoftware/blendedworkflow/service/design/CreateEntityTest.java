package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.service.BWServiceTest;

public class CreateEntityTest extends BWServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_NAME = "Entity Name";
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
    public void duplicateName() throws BWException {
        CreateEntity service = new CreateEntity(SPEC_NAME, DUP_NAME);

        try {
            service.execute();
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        CreateEntity service = new CreateEntity(SPEC_NAME, EMPTY_NAME);

        try {
            service.execute();
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        CreateEntity service = new CreateEntity(SPEC_NAME, null);

        try {
            service.execute();
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

}
