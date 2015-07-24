package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

public class LoadDataModelSpecificationServiceTest
        extends BWDomainAndServiceTest {
    private static final String EXISTS_SPEC_ID = "ID0";
    private static final String NEW_SPEC_ID = "ID1";
    private static final String NEW_SPEC_NAME = "Doctor Appointment";
    private static final String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String EXISTS_ENTITY_NAME = "Exists Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";
    private static final String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";

    AtomicDesignInterface designInterface;
    BWDataModel existingDataModel;

    @Override
    public void populate4Test() {
        designInterface = AtomicDesignInterface.getInstance();

        new BWSpecification(EXISTS_SPEC_ID, EXISTS_SPEC_NAME, "author",
                "description", "version", "UID");
        existingDataModel = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get().getDataModel();

        BWEntity entity = new BWEntity(existingDataModel, EXISTS_ENTITY_NAME,
                false);
        new BWAttribute(existingDataModel, entity, null, EXISTS_ATTRIBUTE_NAME,
                BWAttribute.AttributeType.NUMBER, true, false, false);

        BWRelation relation = new BWRelation(existingDataModel, "relation",
                entity, "role1", Cardinality.ZERO_OR_ONE, false, entity,
                "role2", Cardinality.ONE, false);
    }

    @Test
    public void newSpec() {
        designInterface.loadDataSpecification(
                new SpecificationDTO(NEW_SPEC_ID, NEW_SPEC_NAME));

        BWSpecification spec = getBlendedWorkflow().getSpecById(NEW_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(NEW_SPEC_ID, spec.getSpecId());
        assertEquals(NEW_SPEC_NAME, spec.getName());
    }

    @Test
    public void successSpecExist() {
        designInterface.loadDataSpecification(
                new SpecificationDTO(EXISTS_SPEC_ID, EXISTS_SPEC_NAME));

        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(EXISTS_SPEC_ID, spec.getSpecId());
        assertEquals(EXISTS_SPEC_NAME, spec.getName());
    }

}
