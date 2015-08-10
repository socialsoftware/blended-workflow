package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;

public class CreateAttributeServiceTest extends TeardownRollbackTest {
    private static Logger log = LoggerFactory
            .getLogger(CreateAttributeServiceTest.class);

    private static final String SPEC_ID = "Spec ID";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";
    private static final String BOOLEAN = "Boolean";
    private static final String STRING = "String";
    private static final String NUMBER = "Number";

    BWEntity ent;
    String dataModelExtId;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        dataModelExtId = spec.getDataModel().getExternalId();
        ent = new BWEntity(spec.getDataModel(), ENTITY_NAME, false);
        new BWAttribute(spec.getDataModel(), ent, null, DUP_NAME,
                AttributeType.NUMBER, false, false, false);
    }

    @Test
    public void success() throws BWException {
        log.debug("success ent.getExternalId():{}", ent.getExternalId());

        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(new AttributeDTO(dataModelExtId,
                        ent.getExternalId(), null, ATTRIBUTE_NAME,
                        AttributeType.NUMBER.toString(), true));

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
        BWEntity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        BWAttribute att = entity.getAttribute(ATTRIBUTE_NAME).orElse(null);
        assertNotNull(att);
        assertEquals(ATTRIBUTE_NAME, att.getName());
        assertEquals(AttributeType.NUMBER, att.getType());
    }

    @Test
    public void nonExistsEntityExtId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(new AttributeDTO(dataModelExtId, NON_EXIST,
                        null, ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(),
                        false));

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.NOT_FOUND,
                notification.getError().get(0).getType());
        assertEquals(NON_EXIST, notification.getError().get(0).getValue());
    }

    @Test
    public void emptyEntityExtId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(new AttributeDTO(dataModelExtId, EMPTY_NAME,
                        null, ATTRIBUTE_NAME, AttributeType.STRING.toString(),
                        true));

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.NOT_FOUND,
                notification.getError().get(0).getType());
        assertEquals(EMPTY_NAME, notification.getError().get(0).getValue());
    }

    @Test
    public void nullEntityExtId() throws BWException {
        BWNotification notification = DesignInterface.getInstance()
                .createAttribute(new AttributeDTO(dataModelExtId, null, null,
                        ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(),
                        false));

        assertTrue(notification.hasErrors());
        assertEquals(BWErrorType.NOT_FOUND,
                notification.getError().get(0).getType());
        assertEquals(null, notification.getError().get(0).getValue());
    }

}
