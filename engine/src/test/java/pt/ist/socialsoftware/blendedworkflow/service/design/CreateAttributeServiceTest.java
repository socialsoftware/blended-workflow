package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class CreateAttributeServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_NAME, "author",
                "description", "version", "UID");
        Entity ent = new Entity(spec.getDataModel(), ENTITY_NAME);
        new Attribute(spec.getDataModel(), DUP_NAME, ent, AttributeType.NUMBER,
                false, false);
    }

    @Test
    public void success() throws BWException {
        CreateAttributeService service = new CreateAttributeService(SPEC_NAME,
                ENTITY_NAME, ATTRIBUTE_NAME, AttributeType.NUMBER);
        service.execute();

        Specification spec = getBlendedWorkflow().getSpecification(SPEC_NAME)
                .get();
        Entity entity = spec.getDataModel().getEntity(ENTITY_NAME).get();
        Attribute att = entity.getAttribute(ATTRIBUTE_NAME);
        assertNotNull(att);
        assertEquals(ATTRIBUTE_NAME, att.getName());
        assertEquals(AttributeType.NUMBER, att.getType());
    }

    @Test
    public void nonExistentSpecification() throws BWException {
        CreateAttributeService service = new CreateAttributeService(NON_EXIST,
                ENTITY_NAME, ATTRIBUTE_NAME, AttributeType.BOOLEAN);

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
        CreateAttributeService service = new CreateAttributeService(EMPTY_NAME,
                ENTITY_NAME, ATTRIBUTE_NAME, AttributeType.STRING);

        try {
            service.execute();
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullSpecName() throws BWException {
        CreateAttributeService service = new CreateAttributeService(null,
                ENTITY_NAME, ATTRIBUTE_NAME, AttributeType.BOOLEAN);

        try {
            service.execute();
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_SPECIFICATION_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nonExistentEntity() throws BWException {
        CreateAttributeService service = new CreateAttributeService(SPEC_NAME,
                NON_EXIST, ATTRIBUTE_NAME, AttributeType.BOOLEAN);

        try {
            service.execute();
            fail("non exist entity");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void emptyEntityName() throws BWException {
        CreateAttributeService service = new CreateAttributeService(SPEC_NAME,
                EMPTY_NAME, ATTRIBUTE_NAME, AttributeType.STRING);

        try {
            service.execute();
            fail("emptyName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

    @Test
    public void nullEntityName() throws BWException {
        CreateAttributeService service = new CreateAttributeService(SPEC_NAME,
                null, ATTRIBUTE_NAME, AttributeType.BOOLEAN);

        try {
            service.execute();
            fail("nullName");
        } catch (BWException bwe) {
            assertEquals(BlendedWorkflowError.INVALID_ENTITY_NAME,
                    bwe.getError());
        }
    }

}
