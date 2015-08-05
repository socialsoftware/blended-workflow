package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
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

    AtomicDesignInterface designInterface;

    BWSpecification spec;

    @Override
    public void populate4Test() throws BWException {
        designInterface = AtomicDesignInterface.getInstance();

        spec = new BWSpecification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");

        BWEntity entity = new BWEntity(spec.getDataModel(), ENTITY_NAME_ONE,
                false);
        BWEntity entityTwo = new BWEntity(spec.getDataModel(), ENTITY_NAME_TWO,
                false);
        new BWAttribute(spec.getDataModel(), entity, null, ATTRIBUTE_NAME_ONE,
                BWAttribute.AttributeType.NUMBER, true, false, false);
        new BWAttribute(spec.getDataModel(), entity, null, ATTRIBUTE_NAME_TWO,
                BWAttribute.AttributeType.STRING, false, false, false);

        new BWRelation(spec.getDataModel(), "relation", entity, ROLE_ONE,
                Cardinality.ZERO_OR_ONE, false, entityTwo, ROLE_TWO,
                Cardinality.ONE, false);
    }

    @Test
    public void successAttribute() throws BWException {
        ProductDTO productDTO = designInterface.getSourceOfPath(SPEC_ID,
                ENTITY_NAME_TWO + "." + ROLE_ONE + "." + ATTRIBUTE_NAME_ONE);

        assertEquals(SPEC_ID, productDTO.specDTO.getSpecId());
        assertEquals(ProductType.ENTITY, productDTO.type);
        assertEquals(ENTITY_NAME_TWO, productDTO.entityDTO.getName());
        assertEquals(null, productDTO.attributeGroupDTO);
        assertEquals(null, productDTO.attributeDTO);
    }

}