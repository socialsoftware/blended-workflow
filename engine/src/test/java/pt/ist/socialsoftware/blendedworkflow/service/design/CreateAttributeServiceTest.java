package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
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

    Entity ent;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        ent = new Entity(spec.getDataModel(), ENTITY_NAME, false);
        new AttributeBasic(spec.getDataModel(), ent, null, DUP_NAME,
                AttributeType.NUMBER, false, false, false);
    }

    @Test
    public void success() throws BWException {
        log.debug("success ent.getExternalId():{}", ent.getExternalId());

        DesignInterface.getInstance()
                .createAttribute(new AttributeDTO(SPEC_ID, ent.getExternalId(),
                        null, ATTRIBUTE_NAME, AttributeType.NUMBER.toString(),
                        true));

        Specification spec = getBlendedWorkflow().getSpecById(SPEC_ID).get();
        Entity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        AttributeBasic att = entity.getAttribute(ATTRIBUTE_NAME).orElse(null);
        assertNotNull(att);
        assertEquals(ATTRIBUTE_NAME, att.getName());
        assertEquals(AttributeType.NUMBER, att.getType());
    }

    @Test
    public void nonExistsEntityExtId() throws BWException {
        try {
            DesignInterface.getInstance()
                    .createAttribute(new AttributeDTO(SPEC_ID, NON_EXIST, null,
                            ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(),
                            false));
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
            assertEquals(NON_EXIST, bwe.getMessage());
        }

    }

    @Test
    public void emptyEntityExtId() throws BWException {
        try {
            DesignInterface.getInstance()
                    .createAttribute(new AttributeDTO(SPEC_ID, EMPTY_NAME, null,
                            ATTRIBUTE_NAME, AttributeType.STRING.toString(),
                            true));
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
            assertEquals(EMPTY_NAME, bwe.getMessage());
        }

    }

    @Test
    public void nullEntityExtId() throws BWException {
        try {
            DesignInterface.getInstance()
                    .createAttribute(new AttributeDTO(SPEC_ID, null, null,
                            ATTRIBUTE_NAME, AttributeType.BOOLEAN.toString(),
                            false));
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.NOT_FOUND, bwe.getError());
            assertEquals(null, bwe.getMessage());
        }

    }

}
