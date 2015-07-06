package pt.ist.socialsoftware.blendedworkflow.domain.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class CreateEntityMethodTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    DataModel dataModel = null;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_NAME, "author",
                "description", "version", "UID");
        dataModel = spec.getDataModel();
        new Entity(dataModel, DUP_NAME);
    }

    @Test
    public void success() throws BWException {
        dataModel.createEntity(ENTITY_NAME);

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .get();
        Entity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        assertNotNull(entity);
        assertEquals(ENTITY_NAME, entity.getName());
    }

    @Test
    public void duplicateName() throws BWException {
        try {
            dataModel.createEntity(DUP_NAME);
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        try {
            dataModel.createEntity(EMPTY_NAME);
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        try {
            dataModel.createEntity(null);
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

}
