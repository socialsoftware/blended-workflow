package org.blended.data.repinterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.blended.common.common.And;
import org.blended.common.common.Association;
import org.blended.common.common.Attribute;
import org.blended.common.common.AttributeGroup;
import org.blended.common.common.AttributeValue;
import org.blended.common.common.BoolConstant;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.Constraint;
import org.blended.common.common.Entity;
import org.blended.common.common.Greater;
import org.blended.common.common.IntConstant;
import org.blended.common.common.Specification;
import org.blended.data.data.DataFactory;
import org.blended.data.data.DataModel;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWNumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
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

    CommonFactory commonFactory = CommonFactory.eINSTANCE;
    DataFactory dataFactory = DataFactory.eINSTANCE;
    DataModel eDataModel;

    BWDataModel existingDataModel;

    DataInterface dataInterface;
    Entity eEnt;

    @Override
    public void populate4Test() {
        dataInterface = DataInterface.getInstance();

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
        Specification eSpec = commonFactory.createSpecification();
        eSpec.setName(NEW_SPEC_NAME);
        eDataModel.setSpecification(eSpec);

        BWNotification notification = dataInterface.loadDataModel(NEW_SPEC_ID,
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
        Specification eSpec = commonFactory.createSpecification();
        eSpec.setName(EXISTS_SPEC_NAME);
        eDataModel.setSpecification(eSpec);

        BWNotification notification = dataInterface
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
        eEnt = commonFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eEnt.setExists(true);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = dataInterface
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
        eEnt = commonFactory.createEntity();
        eEnt.setName(ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        Entity eEnt = commonFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);

        BWNotification notification = dataInterface
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
        eEnt = commonFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = commonFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Boolean");

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertTrue(existingDataModel.getEntitiesSet().contains(entity));
        assertEquals(1, entity.getAttributesSet().size());
        assertNotNull(entity.getAttribute(ATTRIBUTE_NAME));
        assertEquals(AttributeType.BOOLEAN,
                entity.getAttribute(ATTRIBUTE_NAME).orElse(null).getType());
        assertNull(entity.getAttribute(EXISTS_ATTRIBUTE_NAME).orElse(null));
    }

    @Test
    public void successCreateAndMaintainAttribute() {
        eEnt = commonFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = commonFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Boolean");
        eAtt = commonFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("Number");

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(2, entity.getAttributesSet().size());

        assertNotNull(entity.getAttribute(ATTRIBUTE_NAME));
        assertEquals(AttributeType.BOOLEAN,
                entity.getAttribute(ATTRIBUTE_NAME).orElse(null).getType());

        assertNotNull(entity.getAttribute(EXISTS_ATTRIBUTE_NAME));
        assertEquals(AttributeType.NUMBER, entity
                .getAttribute(EXISTS_ATTRIBUTE_NAME).orElse(null).getType());

    }

    @Test
    public void successMaintainAttributeAndChangeType() {
        eEnt = commonFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        Attribute eAtt = commonFactory.createAttribute();
        eEnt.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("String");

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(1, entity.getAttributesSet().size());

        assertEquals(AttributeType.STRING, entity
                .getAttribute(EXISTS_ATTRIBUTE_NAME).orElse(null).getType());
    }

    @Test
    public void successRelation() {
        Entity eEntOne = commonFactory.createEntity();
        eEntOne.setName(ENTITY_NAME);
        eEntOne.setExists(false);
        eDataModel.getEntities().add(eEntOne);
        Entity eEntTwo = commonFactory.createEntity();
        eEntTwo.setName(EXISTS_ENTITY_NAME);
        eEntTwo.setExists(false);
        eDataModel.getEntities().add(eEntTwo);
        Association eAssoc = commonFactory.createAssociation();
        eAssoc.setEntity1(eEntOne);
        eAssoc.setName1("role1");
        eAssoc.setCardinality1("*");
        eAssoc.setEntity2(eEntTwo);
        eAssoc.setName2("role2");
        eAssoc.setCardinality2("0..1");
        eDataModel.getAssociations().add(eAssoc);

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        assertEquals(1, existingDataModel.getRelationsSet().size());
    }

    @Test
    public void successCreateAttributeGroup() {
        eEnt = commonFactory.createEntity();
        eEnt.setName(EXISTS_ENTITY_NAME);
        eEnt.setExists(false);
        eDataModel.getEntities().add(eEnt);
        AttributeGroup eAttGroup = commonFactory.createAttributeGroup();
        eAttGroup.setName(ATTRIBUTE_GROUP_NAME);
        eEnt.getAttributes().add(eAttGroup);
        Attribute eAtt = commonFactory.createAttribute();
        eAttGroup.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Number");

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());

        BWEntity entity = existingDataModel.getEntity(EXISTS_ENTITY_NAME)
                .orElse(null);
        assertEquals(1, entity.getAttributeGroupSet().size());
        assertEquals(1, entity.getAttributesSet().size());

        BWAttributeGroup attGroup = entity
                .getAttributeGroup(ATTRIBUTE_GROUP_NAME).orElse(null);

        assertEquals(ATTRIBUTE_GROUP_NAME, attGroup.getName());
        assertEquals(ATTRIBUTE_NAME,
                attGroup.getAttribute(ATTRIBUTE_NAME).orElse(null).getName());
        assertEquals(AttributeType.NUMBER,
                attGroup.getAttribute(ATTRIBUTE_NAME).orElse(null).getType());
        assertEquals(AttributeType.NUMBER,
                entity.getAttribute(ATTRIBUTE_NAME).orElse(null).getType());
    }

    @Test
    public void successCreateDependence() {
        String DEPENDENCE_ONE = "role2" + "." + ATTRIBUTE_NAME;
        String DEPENDENCE_TWO = "role1" + "." + EXISTS_ATTRIBUTE_NAME;

        Entity eEntOne = commonFactory.createEntity();
        eEntOne.setName(EXISTS_ENTITY_NAME);
        eEntOne.setExists(false);
        eDataModel.getEntities().add(eEntOne);
        Attribute eAtt = commonFactory.createAttribute();
        eEntOne.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("Number");
        eAtt.getDependsOn().add(DEPENDENCE_ONE);

        Entity eEntTwo = commonFactory.createEntity();
        eEntTwo.setName(ENTITY_NAME);
        eEntTwo.setExists(false);
        eDataModel.getEntities().add(eEntTwo);
        eAtt = commonFactory.createAttribute();
        eEntTwo.getAttributes().add(eAtt);
        eAtt.setName(ATTRIBUTE_NAME);
        eAtt.setType("Number");
        eAtt.getDependsOn().add(DEPENDENCE_TWO);

        Association eAssoc = commonFactory.createAssociation();
        eAssoc.setEntity1(eEntOne);
        eAssoc.setName1("role1");
        eAssoc.setCardinality1("*");
        eAssoc.setEntity2(eEntTwo);
        eAssoc.setName2("role2");
        eAssoc.setCardinality2("0..1");
        eDataModel.getAssociations().add(eAssoc);

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        notification.getError().stream()
                .map(err -> err.getType() + "-" + err.getValue())
                .forEach(System.out::println);

        assertFalse(notification.hasErrors());

        assertEquals(DEPENDENCE_TWO + "," + DEPENDENCE_ONE,
                existingDataModel.getDependenceSet().stream()
                        .map(dep -> dep.getPath()).sorted()
                        .collect(Collectors.joining(",")));
    }

    @Test
    public void newCreateComparatorRule() {
        Entity eEntOne = commonFactory.createEntity();
        eEntOne.setName(EXISTS_ENTITY_NAME);
        eEntOne.setExists(false);
        eDataModel.getEntities().add(eEntOne);
        Attribute eAtt = commonFactory.createAttribute();
        eEntOne.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("Number");

        Constraint constraint = commonFactory.createConstraint();
        eDataModel.getConstraint().add(constraint);

        IntConstant intExpression = commonFactory.createIntConstant();
        intExpression.setName(6);

        AttributeValue attValueExpression = commonFactory
                .createAttributeValue();
        attValueExpression
                .setName(EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME);

        Greater greaterExpression = commonFactory.createGreater();
        greaterExpression.setLeft(intExpression);
        greaterExpression.setRight(attValueExpression);
        constraint.setConstraint(greaterExpression);

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(1, spec.getDataModel().getRuleSet().size());
        BWRule rule = spec.getDataModel().getRuleSet().stream()
                .collect(Collectors.toList()).get(0);
        assertTrue(rule.getCondition() instanceof Comparison);
        Comparison comparison = (Comparison) rule.getCondition();
        assertEquals(Comparison.ComparisonOperator.GREATER,
                comparison.getComparator());
        BWExpression leftExpression = comparison.getLeftExpression();
        BWExpression righExpression = comparison.getRightExpression();

        BWNumberLiteral literal = (BWNumberLiteral) leftExpression;
        assertEquals(6, literal.getValue());

        BWAttributeValueExpression attValue = (BWAttributeValueExpression) righExpression;
        BWEntity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME)
                .get();
        BWAttribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
        assertEquals(att, attValue.getAttribute());
    }

    @Test
    public void newCreateAndConstraint() {
        Entity eEntOne = commonFactory.createEntity();
        eEntOne.setName(EXISTS_ENTITY_NAME);
        eEntOne.setExists(false);
        eDataModel.getEntities().add(eEntOne);
        Attribute eAtt = commonFactory.createAttribute();
        eEntOne.getAttributes().add(eAtt);
        eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        eAtt.setType("Number");

        Constraint constraint = commonFactory.createConstraint();
        eDataModel.getConstraint().add(constraint);

        And andExpression = commonFactory.createAnd();
        constraint.setConstraint(andExpression);

        Greater greaterExpression = commonFactory.createGreater();
        andExpression.setLeft(greaterExpression);

        IntConstant intExpression = commonFactory.createIntConstant();
        greaterExpression.setLeft(intExpression);
        intExpression.setName(6);

        AttributeValue attValueExpression = commonFactory
                .createAttributeValue();
        greaterExpression.setRight(attValueExpression);
        attValueExpression
                .setName(EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME);

        BoolConstant boolConstant = commonFactory.createBoolConstant();
        andExpression.setRight(boolConstant);
        boolConstant.setName("true");

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

        assertFalse(notification.hasErrors());
        BWSpecification spec = getBlendedWorkflow().getSpecById(EXISTS_SPEC_ID)
                .get();
        assertNotNull(spec);
        assertEquals(1, spec.getDataModel().getRuleSet().size());
        BWRule rule = spec.getDataModel().getRuleSet().stream()
                .collect(Collectors.toList()).get(0);
        assertTrue(rule.getCondition() instanceof AndCondition);
        AndCondition andCondition = (AndCondition) rule.getCondition();
        Condition leftCondition = andCondition.getLeftCondition();
        Condition rightCondition = andCondition.getRightCondition();
        assertTrue(leftCondition instanceof Comparison);
        assertTrue(rightCondition instanceof TrueCondition);

        Comparison comparison = (Comparison) leftCondition;
        assertEquals(Comparison.ComparisonOperator.GREATER,
                comparison.getComparator());
        BWExpression leftExpression = comparison.getLeftExpression();
        BWExpression righExpression = comparison.getRightExpression();

        BWNumberLiteral literal = (BWNumberLiteral) leftExpression;
        assertEquals(6, literal.getValue());

        BWAttributeValueExpression attValue = (BWAttributeValueExpression) righExpression;
        BWEntity entity = spec.getDataModel().getEntity(EXISTS_ENTITY_NAME)
                .get();
        BWAttribute att = entity.getAttribute(EXISTS_ATTRIBUTE_NAME).get();
        assertEquals(att, attValue.getAttribute());
    }

}
