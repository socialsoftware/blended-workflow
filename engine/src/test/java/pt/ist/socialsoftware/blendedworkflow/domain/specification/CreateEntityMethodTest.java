package pt.ist.socialsoftware.blendedworkflow.domain.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateEntityMethodTest extends BWDomainAndServiceTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    BWDataModel dataModel = null;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        dataModel = spec.getDataModel();
        new BWEntity(dataModel, DUP_NAME, false);
    }

    @Test
    public void success() throws BWException {
        dataModel.createEntity(ENTITY_NAME, true);

        BWSpecification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        assertNotNull(entity);
        assertEquals(ENTITY_NAME, entity.getName());
        assertTrue(entity.getExists());
    }

    @Test
    public void duplicateName() throws BWException {
        try {
            dataModel.createEntity(DUP_NAME, false);
            fail("duplicateName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
        }
    }

    @Test
    public void emptyName() throws BWException {
        try {
            dataModel.createEntity(EMPTY_NAME, false);
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
        }
    }

    @Test
    public void nullName() throws BWException {
        try {
            dataModel.createEntity(null, false);
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ENTITY_NAME, bwe.getError());
        }
    }

}
