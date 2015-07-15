package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.blended.data.data.Association;
import org.blended.data.data.Attribute;
import org.blended.data.data.AttributeGroup;
import org.blended.data.data.DataFactory;
import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.blended.data.data.Specification;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
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
    private static final String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";

    DataFactory dataFactory = DataFactory.eINSTANCE;
    DataModel eDataModel;

    BWDataModel existingDataModel;

    DesignInterface designInterface;
    Entity eEnt;

    @Override
    public void populate4Test() {
        designInterface = DesignInterface.getInstance();

        new BWSpecification(EXISTS_SPEC_ID, EXISTS_SPEC_NAME, "author",
                "description", "version", "UID");
        existingDataModel = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get().getDataModel();

        BWEntity entity = new BWEntity(existingDataModel, EXISTS_ENTITY_NAME,
                false);
        new BWAttribute(existingDataModel, EXISTS_ATTRIBUTE_NAME, entity,
                BWAttribute.AttributeType.NUMBER, false, false);

        BWRelation relation = new BWRelation(existingDataModel, "relation",
                entity, "role1", Cardinality.ZERO_OR_ONE, false, entity,
                "role2", Cardinality.ONE, false);

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
        BWSpecification spec = getBlendedWorkflow().getSpecById(NEW_SPEC_ID)
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
        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(EXISTS_SPEC_ID, spec.getSpecId());
        assertEquals(EXISTS_SPEC_NAME, spec.getName());
    }

    @Test
    public void successCreateAndDeleteEntity() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eEnt.setExists(true);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        assertTrue(entity.getExists());
        entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME).orElse(null);
        assertNull(entity);
    }

    @Test
    public void successCreateAndMaintainEntity() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        Entity eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        assertFalse(entity.getExists());
        entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME).orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        assertFalse(entity.getExists());
    }

    @Test
    public void successCreateAndDeleteAttribute() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
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
        eEnt.setExists(false);
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
        eEnt.setExists(false);
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

    @Test
    public void successRelation() {
        Entity eEntOne = dataFactory.createEntity();
        eEntOne.setName(ENTITY_NAME);
        eEntOne.setExists(false);
        eDataModel.getEntities().add(eEntOne);
        Entity eEntTwo = dataFactory.createEntity();
        eEntTwo.setName(EXISTS_ENTITY_NAME);
        eEntTwo.setExists(false);
        eDataModel.getEntities().add(eEntTwo);
        Association eAssoc = dataFactory.createAssociation();
        eAssoc.setEntity1(eEntOne);
        eAssoc.setName1("role1");
        eAssoc.setCardinality1("*");
        eAssoc.setEntity2(eEntTwo);
        eAssoc.setName2("role2");
        eAssoc.setCardinality2("0..1");
        eDataModel.getAssociations().add(eAssoc);

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        assertEquals(1, existingDataModel.getRelationsSet().size());
    }

    @Test
    public void successCreateAttributeGroup() {
        eEnt = dataFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        AttributeGroup eAttGroup = dataFactory.createAttributeGroup();
        // eAttGroup.setName(ATTRIBUTE_GROUP_NAME);
        eEnt.getAttributes().add(eAttGroup);
        Attribute eAtt = dataFactory.createAttribute();
        eAttGroup.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Number");

        BWNotification notification = designInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(1, entity.getAttributeGroupSet().size());
        assertEquals(1, entity.getAttributesSet().size());

        BWAttributeGroup attGroup = entity.getAttributeGroup("TODEFINE")
                .orElse(null);

        assertEquals("TODEFINE", attGroup.getName());
        assertEquals(ATTRIBUTE_NAME,
                attGroup.getAttribute(ATTRIBUTE_NAME).getName());
        assertEquals(AttributeType.NUMBER,
                attGroup.getAttribute(ATTRIBUTE_NAME).getType());
        assertEquals(AttributeType.NUMBER,
                entity.getAttribute(ATTRIBUTE_NAME).getType());
    }

}
