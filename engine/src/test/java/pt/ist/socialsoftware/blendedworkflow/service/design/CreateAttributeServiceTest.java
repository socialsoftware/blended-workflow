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
    private static final String SPEC_NAME = "Spec Name";
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
        BWSpecification spec = new BWSpecification("id1", SPEC_NAME, "author",
                "description", "version", "UID");
        BWEntity ent = new BWEntity(spec.getDataModel(), ENTITY_NAME);
        new BWAttribute(spec.getDataModel(), DUP_NAME, ent,
                AttributeType.NUMBER, false, false);
    }

    @Test
    public void success() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_NAME, ENTITY_NAME, ATTRIBUTE_NAME,
                        NUMBER);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecByName(SPEC_NAME)
                .get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        BWAttribute att = entity.getAttribute(ATTRIBUTE_NAME);
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
        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptySpecName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(EMPTY_NAME, ENTITY_NAME, ATTRIBUTE_NAME,
                        STRING);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullSpecName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(null, ENTITY_NAME, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_SPECIFICATION_NAME,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

    @Test
    public void nonExistentEntity() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_NAME, NON_EXIST, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptyEntityName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_NAME, EMPTY_NAME, ATTRIBUTE_NAME, STRING);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullEntityName() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(SPEC_NAME, null, ATTRIBUTE_NAME, BOOLEAN);

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.INVALID_ENTITY_NAME,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

}
