package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;

public class CreateAttributeServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";
    private static final String BOOLEAN = "Boolean";
    private static final String STRING = "String";
    private static final String NUMBER = "Number";

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        BWEntity ent = new BWEntity(spec.getDataModel(), ENTITY_NAME, false);
        new BWAttribute(spec.getDataModel(), DUP_NAME, ent,
                AttributeType.NUMBER, false, false);
    }

    @Test
    public void success() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_ID, ENTITY_NAME, ATTRIBUTE_NAME, NUMBER);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        BWAttribute att = entity.getAttribute(ATTRIBUTE_NAME).orElse(null);
        assertNotNull(att);
        assertEquals(ATTRIBUTE_NAME, att.getName());
        assertEquals(AttributeType.NUMBER, att.getType());
    }

    @Test
    public void nonExistentSpecification() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(NON_EXIST, ENTITY_NAME, ATTRIBUTE_NAME,
                        BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptySpecId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(EMPTY_NAME, ENTITY_NAME, ATTRIBUTE_NAME,
                        STRING);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullSpecId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(null, ENTITY_NAME, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_SPECIFICATION_ID,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

    @Test
    public void nonExistentEntity() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_ID, NON_EXIST, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptyEntityName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_ID, EMPTY_NAME, ATTRIBUTE_NAME, STRING);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullEntityName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_ID, null, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

}
