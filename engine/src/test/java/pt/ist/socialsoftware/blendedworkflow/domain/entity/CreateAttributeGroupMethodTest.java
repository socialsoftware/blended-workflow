package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateAttributeGroupMethodTest extends TeardownRollbackTest {
    private static String ATT_GROUP_NAME = "Attribute group name";
    private static String EXISTS_NAME = "Exists name";

    private Entity entity = null;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification("SpecId", "My spec",
                "author", "description", "version", "UID");
        entity = new Entity(spec.getDataModel(), "Entity name", false);
        new AttributeGroup(spec.getDataModel(), entity, EXISTS_NAME, true);
    }

    @Test
    public void success() {
        AttributeGroup attGroup = entity.createAttributeGroup(ATT_GROUP_NAME,
                false);

        assertEquals(2, entity.getAttributeGroupSet().size());
        assertEquals(ATT_GROUP_NAME, attGroup.getName());
    }

    @Test
    public void existsName() {
        try {
            entity.createAttributeGroup(EXISTS_NAME, true);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ATTRIBUTE_GROUP_NAME,
                    bwe.getError());
        }
    }

}
