package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

public class GetSourceOfPathServiceTest extends TeardownRollbackTest {
    private static final String ROLE_TWO = "role2";
    private static final String ROLE_ONE = "role1";
    private static final String SPEC_ID = "ID0";
    private static final String NEW_SPEC_ID = "ID1";
    private static final String RULE_NAME = "Rule name";
    private static final String NEW_SPEC_NAME = "Doctor Appointment";
    private static final String SPEC_NAME = "Old Doctor Appointment Specification";
    private static final String ENTITY_NAME_TWO = "Entity Name";
    private static final String ENTITY_NAME_ONE = "Exists Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String ATTRIBUTE_NAME_ONE = "Exists Attribute Name";
    private static final String ATTRIBUTE_NAME_TWO = "Exists Attribute Name String";
    private static final String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";

    DesignInterface designInterface;

    Specification spec;

    @Override
    public void populate4Test() throws BWException {
        designInterface = DesignInterface.getInstance();

        spec = new Specification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");

        Entity entity = new Entity(spec.getDataModel(), ENTITY_NAME_ONE,
                false);
        Entity entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO,
                false);
        new AttributeBasic(spec.getDataModel(), entity, null, ATTRIBUTE_NAME_ONE,
                AttributeType.NUMBER, true, false, false);
        new AttributeBasic(spec.getDataModel(), entity, null, ATTRIBUTE_NAME_TWO,
                AttributeType.STRING, false, false, false);

        new RelationBW(spec.getDataModel(), "relation", entity, ROLE_ONE,
                Cardinality.ZERO_OR_ONE, false, entityTwo, ROLE_TWO,
                Cardinality.ONE, false);
    }

    @Test
    public void successAttribute() throws BWException {
        ProductDTO productDTO = designInterface.getSourceOfPath(SPEC_ID,
                ENTITY_NAME_TWO + "." + ROLE_ONE + "." + ATTRIBUTE_NAME_ONE);

        Product product = FenixFramework
                .getDomainObject(productDTO.getExtId());

        assertEquals(ProductType.ENTITY, product.getProductType());

        Entity entity = (Entity) product;

        assertEquals(SPEC_ID,
                entity.getDataModel().getSpecification().getSpecId());
        assertEquals(ENTITY_NAME_TWO, entity.getName());
    }

}