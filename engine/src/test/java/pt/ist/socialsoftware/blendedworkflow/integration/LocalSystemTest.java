package pt.ist.socialsoftware.blendedworkflow.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;

public class LocalSystemTest {
    private DesignInterface designInterface;

    private static final Logger logger = LoggerFactory
            .getLogger(LocalSystemTest.class);

    @Before
    @Atomic
    public void setUp() {
        logger.debug("LocalSystemTest::setUp");
        designInterface = DesignInterface.getInstance();
    }

    @After
    @Atomic
    public void tearDown() {
        logger.debug("LocalSystemTest::tearDown");
        BlendedWorkflow.getInstance().delete();
    }

    @Test
    public void walktrough() {
        designInterface.createSpecification("id1", "First Specification");
        designInterface.createSpecification("id2", "Second Specification");

        designInterface.createEntity("First Specification", "First Entity",
                false);
        designInterface.createEntity("First Specification", "Second Entity",
                false);

        designInterface.createAttribute("First Specification", "First Entity",
                "att1", "Boolean");
        designInterface.createAttribute("First Specification", "First Entity",
                "att2", "String");
        designInterface.createAttribute("First Specification", "First Entity",
                "att3", "Number");

        designInterface.createRelation("First Specification", "First Entity",
                "first", "1", "Second Entity", "second", "*");

        // String EXISTS_SPEC_ID = "ID0";
        // String NEW_SPEC_ID = "ID1";
        // String NEW_SPEC_NAME = "Doctor Appointment";
        // String EXISTS_SPEC_NAME = "Old Doctor Appointment Specification";
        // String ENTITY_NAME = "Entity Name";
        // String EXISTS_ENTITY_NAME = "Exists Entity Name";
        // String ATTRIBUTE_NAME = "Attribute Name";
        // String EXISTS_ATTRIBUTE_NAME = "Exists Attribute Name";
        // String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";
        //
        // CommonFactory commonFactory = CommonFactory.eINSTANCE;
        // DataFactory dataFactory = DataFactory.eINSTANCE;
        // DataModel eDataModel;
        //
        // BWDataModel existingDataModel;
        //
        // DesignInterface designInterface;
        // Entity eEnt;
        //
        // designInterface = DesignInterface.getInstance();
        //
        // eDataModel = dataFactory.createDataModel();
        //
        // Specification eSpec = commonFactory.createSpecification();
        // eSpec.setName(EXISTS_SPEC_NAME);
        // eDataModel.setSpecification(eSpec);
        //
        // Entity eEntOne = commonFactory.createEntity();
        // eEntOne.setName(EXISTS_ENTITY_NAME);
        // eEntOne.setExists(false);
        // eDataModel.getEntities().add(eEntOne);
        // Attribute eAtt = commonFactory.createAttribute();
        // eEntOne.getAttributes().add(eAtt);
        // eAtt.setName(EXISTS_ATTRIBUTE_NAME);
        // eAtt.setType("Number");
        //
        // Constraint constraint = commonFactory.createConstraint();
        // eDataModel.getConstraint().add(constraint);
        //
        // And andExpression = commonFactory.createAnd();
        // constraint.setConstraint(andExpression);
        //
        // Greater greaterExpression = commonFactory.createGreater();
        // andExpression.setLeft(greaterExpression);
        //
        // IntConstant intExpression = commonFactory.createIntConstant();
        // greaterExpression.setLeft(intExpression);
        // intExpression.setName(6);
        //
        // AttributeValue attValueExpression = commonFactory
        // .createAttributeValue();
        // greaterExpression.setRight(attValueExpression);
        // attValueExpression
        // .setName(EXISTS_ENTITY_NAME + "." + EXISTS_ATTRIBUTE_NAME);
        //
        // BoolConstant boolConstant = commonFactory.createBoolConstant();
        // andExpression.setRight(boolConstant);
        // boolConstant.setName("true");
        //
        // BWNotification notification = designInterface
        // .loadDataModel(EXISTS_SPEC_ID, eDataModel);

    }

}
