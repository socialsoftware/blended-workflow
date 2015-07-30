package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;

public class CreateDependenceServiceTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "ID1";
    private static final String SPEC_NAME = "Doctor Appointment";
    private static final String ENTITY_NAME = "Patinet";
    private static final String ATTRIBUTE_GROUP_NAME = "Medication";
    private static final String ATTRIBUTE_NAME = "Portion";
    private static final String DEPENDENCE_ONE = ENTITY_NAME + "."
            + "dependenceOne";
    private static final String DEPENDENCE_TWO = ENTITY_NAME + "."
            + "dependenceTwo";

    AtomicDesignInterface designInterface;
    BWDataModel dataModel;

    @Override
    public void populate4Test() throws BWException {
        designInterface = AtomicDesignInterface.getInstance();

        new BWSpecification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");
        dataModel = getBlendedWorkflow().getSpecById(SPEC_ID).get()
                .getDataModel();

        BWEntity entity = new BWEntity(dataModel, ENTITY_NAME, false);
        BWAttributeGroup group = new BWAttributeGroup(dataModel, entity,
                ATTRIBUTE_GROUP_NAME, false);
        new BWAttribute(dataModel, entity, group, ATTRIBUTE_NAME,
                BWAttribute.AttributeType.NUMBER, true, false, false);
        entity.createDependence(DEPENDENCE_ONE);
    }

    @Test
    public void successCreateEntityDependence() {
        designInterface.createDependence(
                new DependenceDTO(SPEC_ID, ENTITY_NAME, DEPENDENCE_TWO));

        assertEquals(2, dataModel.getEntity(ENTITY_NAME).get()
                .getDependenceSet().size());
        assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO,
                dataModel.getDependenceSet().stream().map(dep -> dep.getPath())
                        .sorted().collect(Collectors.joining(",")));
    }

    @Test
    public void successCreateAttributeGroupDependence() {
        designInterface.createDependence(new DependenceDTO(SPEC_ID, ENTITY_NAME,
                ProductType.ATTRIBUTE_GROUP, ATTRIBUTE_GROUP_NAME,
                DEPENDENCE_TWO));

        assertEquals(1, dataModel.getEntity(ENTITY_NAME).get()
                .getDependenceSet().size());
        assertEquals(1,
                dataModel.getEntity(ENTITY_NAME).get()
                        .getAttributeGroup(ATTRIBUTE_GROUP_NAME).get()
                        .getDependenceSet().size());
        assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO,
                dataModel.getDependenceSet().stream().map(dep -> dep.getPath())
                        .sorted().collect(Collectors.joining(",")));
    }

    @Test
    public void successCreateAttributeDependence() {
        designInterface.createDependence(new DependenceDTO(SPEC_ID, ENTITY_NAME,
                ProductType.ATTRIBUTE, ATTRIBUTE_NAME, DEPENDENCE_TWO));

        assertEquals(1, dataModel.getEntity(ENTITY_NAME).get()
                .getDependenceSet().size());
        assertEquals(1, dataModel.getEntity(ENTITY_NAME).get()
                .getAttribute(ATTRIBUTE_NAME).get().getDependenceSet().size());
        assertEquals(DEPENDENCE_ONE + "," + DEPENDENCE_TWO,
                dataModel.getDependenceSet().stream().map(dep -> dep.getPath())
                        .sorted().collect(Collectors.joining(",")));
    }

}
