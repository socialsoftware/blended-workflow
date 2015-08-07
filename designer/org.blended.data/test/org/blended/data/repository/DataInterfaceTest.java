package org.blended.data.repository;

import org.blended.common.common.And;
import org.blended.common.common.Attribute;
import org.blended.common.common.AttributeValue;
import org.blended.common.common.BoolConstant;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.Constraint;
import org.blended.common.common.Entity;
import org.blended.common.common.Greater;
import org.blended.common.common.IntConstant;
import org.blended.common.common.Specification;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.data.data.DataFactory;
import org.blended.data.data.DataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataInterfaceTest {
    private DataInterface dataInterface;

    private static final Logger logger = LoggerFactory
            .getLogger(DataInterfaceTest.class);

    private static String EXISTS_SPEC_ID = "ID0";
    private static String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
    private static String EXISTS_ENTITY_NAME = "Exists Entity Name";
    private static String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";

    @Before
    public void setUp() {
        logger.debug("LocalSystemTest::setUp");
        dataInterface = DataInterface.getInstance();
    }

    @After
    public void tearDown() {
        logger.debug("LocalSystemTest::tearDown");
        dataInterface.deleteSpecification(EXISTS_SPEC_ID);
    }

    @Test
    public void walktrough() {

        CommonFactory commonFactory = CommonFactory.eINSTANCE;
        DataFactory dataFactory = DataFactory.eINSTANCE;
        DataModel eDataModel;

        eDataModel = dataFactory.createDataModel();

        Specification eSpec = commonFactory.createSpecification();
        eSpec.setName(EXISTS_SPEC_NAME);
        eDataModel.setSpecification(eSpec);

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

        dataInterface = DataInterface.getInstance();

        BWNotification notification = dataInterface
                .loadDataModel(EXISTS_SPEC_ID, eDataModel);

    }

}
