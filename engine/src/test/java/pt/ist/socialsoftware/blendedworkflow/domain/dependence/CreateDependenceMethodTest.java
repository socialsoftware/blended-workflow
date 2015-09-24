package pt.ist.socialsoftware.blendedworkflow.domain.dependence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateDependenceMethodTest extends TeardownRollbackTest {
    private static String DEPENDENCE = "Entity name" + "."
            + "rolename.attribute";

    private Entity entity = null;
    private Attribute attribute = null;
    private AttributeGroup attributeGroup = null;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification("SpecId", "My spec",
                "author", "description", "version", "UID");
        entity = new Entity(spec.getDataModel(), "Entity name", false);
        attributeGroup = new AttributeGroup(spec.getDataModel(), entity,
                "name", true);
        attribute = new Attribute(spec.getDataModel(), entity, null, "name",
                AttributeType.BOOLEAN, true, false, false);
    }

    @Test
    public void successEntity() {
        entity.createDependence(DEPENDENCE);

        assertEquals(1, entity.getDependenceSet().size());
        assertEquals(DEPENDENCE,
                entity.getDependenceSet().stream().findFirst().get().getPath());
    }

    @Test
    public void successAttributeGroup() {
        attributeGroup.createDependence(DEPENDENCE);

        assertEquals(1, attributeGroup.getDependenceSet().size());
        assertEquals(DEPENDENCE, attributeGroup.getDependenceSet().stream()
                .findFirst().get().getPath());
    }

    @Test
    public void successAttribute() {
        attribute.createDependence(DEPENDENCE);

        assertEquals(1, attribute.getDependenceSet().size());
        assertEquals(DEPENDENCE, attribute.getDependenceSet().stream()
                .findFirst().get().getPath());
    }

}
