package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;

public class CreateEntityServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification("id1", SPEC_NAME, "author",
                "description", "version", "UID");
        new BWEntity(spec.getDataModel(), DUP_NAME);
    }

    @Test
    public void success() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(SPEC_NAME, ENTITY_NAME);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecByName(SPEC_NAME)
                .get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        assertNotNull(entity);
        assertEquals(ENTITY_NAME, entity.getName());
    }

    @Test
    public void nonExistentSpecification() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(NON_EXIST, ENTITY_NAME);

        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptySpecName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(EMPTY_NAME, ENTITY_NAME);

        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(null, ENTITY_NAME);

        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

    @Test
    public void entityExists() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createEntity(SPEC_NAME, DUP_NAME);

        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(DUP_NAME, notification.getError().get(0).getValue());
    }

}
