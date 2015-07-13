package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.blended.data.data.Attribute;
import org.blended.data.data.DataFactory;
import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.blended.data.data.Specification;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;

public class LoadDataModelServiceTest extends BWDomainAndServiceTest {
    private static final String EXISTS_SPEC_ID = "ID0";
    private static final String NEW_SPEC_ID = "ID1";
    private static final String NEW_SPEC_NAME = "Doctor Appointment";
    private static final String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
    private static final String ENTITY_NAME = "Entity Name";
    private static final String EXISTS_ENTITY_NAME = "Exists Entity Name";
    private static final String ATTRIBUTE_NAME = "Attribute Name";
    private static final String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";

    DataFactory dataFactory = DataFactory.eINSTANCE;
    DataModel eDataModel;

    BWDataModel existingDataModel;

    DesignInterface designInterface;
    Entity eEnt;

    @Override
    public void populate4Test() {
        designInterface = DesignInterface.getInstance();

        BWSpecification spec = new BWSpecification(EXISTS_SPEC_ID,
                EXISTS_SPEC_NAME, "author", "description", "version", "UID");
        existingDataModel = getBlendedWorkflow().getSpecByName(EXISTS_SPEC_NAME)
                .get().getDataModel();

        BWEntity entity = new BWEntity(existingDataModel, EXISTS_ENTITY_NAME);
        new BWAttribute(existingDataModel, EXISTS_ATTRIBUTE_NAME, entity,
                BWAttribute.AttributeType.NUMBER, false, false);

        eDataModel = dataFactory.createDataModel();
    }

    @Test
    public void newSpec() {
        Specification eSpec = dataFactory.createSpecification();
        eSpec.setName(NEW_SPEC_NAME);
        eDataModel.setSpecification(eSpec);

        BWNotification notification = designInterface.loadDataModel(NEW_SPEC_ID,
                eDataModel);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecByName(NEW_SPEC_NAME)
                .get();
        assertNotNull(spec);
        assertEquals(NEW_SPEC_ID, spec.getSpecId());
        assertEquals(NEW_SPEC_NAME, spec.getName());
    }

    @Test
    public void successSpecExist() {
        Specification eSpec = dataFactory.createSpecification();
        eSpec.setName(EXISTS_SPEC_NAME);
        eDataModel.setSpecification(eSpec);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow()
                .getSpecByName(EXISTS_SPEC_NAME).get();
        assertNotNull(spec);
        assertEquals(EXISTS_SPEC_ID, spec.getSpecId());
        assertEquals(EXISTS_SPEC_NAME, spec.getName());
    }

    @Test
    public void successCreateAndDeleteEntity() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME).orElse(null);
        assertNull(entity);
    }

    @Test
    public void successCreateAndMaintainEntity() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);
        Entity eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
    }

    @Test
    public void successCreateAndDeleteAttribute() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = dataFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Boolean");

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        assertEquals(1, entity.getAttributesSet().size());
        assertNotNull(entity.getAttribute(ATTRIBUTE_NAME));
        assertEquals(AttributeType.BOOLEAN,
                entity.getAttribute(ATTRIBUTE_NAME).getType());
        assertNull(entity.getAttribute(EXISTS_ATTRIBUTE_NAME));
    }

    @Test
    public void successCreateAndMaintainAttribute() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = dataFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Boolean");
        eAtt = dataFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("Number");

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(2, entity.getAttributesSet().size());

        assertNotNull(entity.getAttribute(ATTRIBUTE_NAME));
        assertEquals(AttributeType.BOOLEAN,
                entity.getAttribute(ATTRIBUTE_NAME).getType());

        assertNotNull(entity.getAttribute(EXISTS_ATTRIBUTE_NAME));
        assertEquals(AttributeType.NUMBER,
                entity.getAttribute(EXISTS_ATTRIBUTE_NAME).getType());

    }

    @Test
    public void successMaintainAttributeAndChangeType() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = dataFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("String");

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(1, entity.getAttributesSet().size());

        assertEquals(AttributeType.STRING,
                entity.getAttribute(EXISTS_ATTRIBUTE_NAME).getType());

    }

}
