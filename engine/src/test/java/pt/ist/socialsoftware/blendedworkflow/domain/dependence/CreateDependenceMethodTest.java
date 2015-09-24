package pt.ist.socialsoftware.blendedworkflow.domain.dependence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateDependenceMethodTest extends TeardownRollbackTest {
    private static String DEPENDENCE = "Entity name" + "."
            + "rolename.attribute";

    private Entity entity = null;
    private AttributeBasic attributeBasic = null;
    private AttributeGroup attributeGroup = null;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification("SpecId", "My spec", "author",
                "description", "version", "UID");
        entity = new Entity(spec.getDataModel(), "Entity name", false);
        attributeGroup = new AttributeGroup(spec.getDataModel(), entity, "att1",
                true);
        attributeBasic = new AttributeBasic(spec.getDataModel(), entity, null,
                "att2", AttributeType.BOOLEAN, true, false, false);
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
        attributeBasic.createDependence(DEPENDENCE);

        assertEquals(1, attributeBasic.getDependenceSet().size());
        assertEquals(DEPENDENCE, attributeBasic.getDependenceSet().stream()
                .findFirst().get().getPath());
    }

}
