package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;

public class CreateEntityServiceTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    BWSpecification spec;

    @Override
    public void populate4Test() throws BWException {
        spec = new BWSpecification(SPEC_ID, "name", "author", "description",
                "version", "UID");
        new BWEntity(spec.getDataModel(), DUP_NAME, false);
    }

    @Test
    public void success() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(new EntityDTO(SPEC_ID, ENTITY_NAME, false));

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        assertNotNull(entity);
        assertEquals(ENTITY_NAME, entity.getName());
        assertFalse(entity.getExists());
    }

    @Test
    public void nonExistSpecId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(new EntityDTO(NON_EXIST, ENTITY_NAME, false));

        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptySpecId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(new EntityDTO(EMPTY_NAME, ENTITY_NAME, true));

        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullSpecIdId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(new EntityDTO(null, ENTITY_NAME, false));

        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

    @Test
    public void entityExists() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(new EntityDTO(SPEC_ID, DUP_NAME, false));

        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(DUP_NAME, notification.getError().get(0).getValue());
    }

}
