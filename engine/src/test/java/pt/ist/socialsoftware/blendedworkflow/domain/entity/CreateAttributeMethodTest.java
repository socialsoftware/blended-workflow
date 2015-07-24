package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateAttributeMethodTest extends BWDomainAndServiceTest {
    private static String ATT_NAME_ONE = "Attribute name one";
    private static String ATT_NAME_TWO = "Attribute name two";
    private static String ATT_NAME_THREE = "Attribute name three";
    private static String ATT_GROUP_NAME = "Attribute group name";
    private static String EXISTS_NAME = "Attribute name exist";
    private static String EMPTY_NAME = "";

    private BWEntity entity;
    private BWAttributeGroup attGroup;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification("SpecId", "My spec",
                "author", "description", "version", "UID");
        entity = new BWEntity(spec.getDataModel(), "Entity name", false);
        new BWAttribute(spec.getDataModel(), entity, null, EXISTS_NAME,
                AttributeType.BOOLEAN, true, false, false);
        attGroup = new BWAttributeGroup(spec.getDataModel(), entity,
                ATT_GROUP_NAME, true);
    }

    @Test
    public void success() {
        BWAttribute att1 = entity.createAttribute(null, ATT_NAME_ONE,
                AttributeType.STRING, true);
        BWAttribute att2 = entity.createAttribute(null, ATT_NAME_TWO,
                AttributeType.BOOLEAN, false);
        BWAttribute att3 = entity.createAttribute(null, ATT_NAME_THREE,
                AttributeType.NUMBER, false);

        assertEquals(4, entity.getAttributesSet().size());
        assertEquals(ATT_NAME_ONE, att1.getName());
        assertEquals(att1, entity.getAttribute(ATT_NAME_ONE).orElse(null));
        assertEquals(att2, entity.getAttribute(ATT_NAME_TWO).orElse(null));
        assertEquals(att3, entity.getAttribute(ATT_NAME_THREE).orElse(null));
        assertEquals(AttributeType.STRING, att1.getType());
        assertEquals(AttributeType.BOOLEAN, att2.getType());
        assertEquals(AttributeType.NUMBER, att3.getType());

    }

    @Test
    public void successWithGroup() {
        BWAttribute att1 = entity.createAttribute(attGroup, ATT_NAME_ONE,
                AttributeType.STRING, true);

        assertEquals(2, entity.getAttributesSet().size());
        assertEquals(ATT_NAME_ONE, att1.getName());
        assertEquals(att1, entity.getAttribute(ATT_NAME_ONE).orElse(null));
        assertEquals(AttributeType.STRING, att1.getType());

        assertEquals(1, attGroup.getAttributeSet().size());
        assertEquals(att1, attGroup.getAttribute(ATT_NAME_ONE).orElse(null));
    }

    @Test
    public void existsName() {
        try {
            entity.createAttribute(null, EXISTS_NAME, AttributeType.NUMBER,
                    true);
            fail("Able to create an attribute with the same name");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
        }
    }

    @Test
    public void emptyName() {
        try {
            entity.createAttribute(null, EMPTY_NAME, AttributeType.BOOLEAN,
                    true);
            fail("Able to create an attribute with empty name");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
        }
    }

    @Test
    public void nullName() {
        try {
            entity.createAttribute(null, null, AttributeType.STRING, true);
            fail("Able to create an attribute with null name");
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
        }
    }

}
