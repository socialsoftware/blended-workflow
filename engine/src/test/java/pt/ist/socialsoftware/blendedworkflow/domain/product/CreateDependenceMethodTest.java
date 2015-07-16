package pt.ist.socialsoftware.blendedworkflow.domain.product;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateDependenceMethodTest extends BWDomainAndServiceTest {
    private static String DEPENDENCE = "rolename.attribute";

    private BWEntity entity = null;
    private BWAttribute attribute = null;
    private BWAttributeGroup attributeGroup = null;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification("SpecId", "My spec",
                "author", "description", "version", "UID");
        entity = new BWEntity(spec.getDataModel(), "Entity name", false);
        attributeGroup = new BWAttributeGroup(spec.getDataModel(), entity,
                "name");
        attribute = new BWAttribute(spec.getDataModel(), "name", entity,
                AttributeType.BOOLEAN, false, false);
    }

    @Test
    public void successEntity() {
        entity.createDependence(DEPENDENCE);

        assertEquals(1, entity.getDependenceSet().size());
        assertEquals(DEPENDENCE, entity.getDependenceSet().stream().findFirst()
                .get().getValue());
    }

    @Test
    public void successAttributeGroup() {
        attributeGroup.createDependence(DEPENDENCE);

        assertEquals(1, attributeGroup.getDependenceSet().size());
        assertEquals(DEPENDENCE, attributeGroup.getDependenceSet().stream()
                .findFirst().get().getValue());
    }

    @Test
    public void successAttribute() {
        attribute.createDependence(DEPENDENCE);

        assertEquals(1, attribute.getDependenceSet().size());
        assertEquals(DEPENDENCE, attribute.getDependenceSet().stream()
                .findFirst().get().getValue());
    }

}
