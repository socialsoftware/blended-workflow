package pt.ist.socialsoftware.blendedworkflow.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO.Type;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

public class DesignInterfaceTest {
    private AtomicDesignInterface designInterface;

    private static final Logger logger = LoggerFactory
            .getLogger(DesignInterfaceTest.class);

    public static String EXISTS_SPEC_ID = "id1";
    public static String EXISTS_ENTITY_NAME = "First Entity";
    public static String EXISTS_ATTRIBUTE_NAME_STRING = "att2";

    @Before
    @Atomic
    public void setUp() {
        logger.debug("LocalSystemTest::setUp");
        designInterface = AtomicDesignInterface.getInstance();
    }

    @After
    @Atomic
    public void tearDown() {
        logger.debug("LocalSystemTest::tearDown");
        BlendedWorkflow.getInstance().delete();
    }

    @Test
    public void walktrough() {
        designInterface.createSpecification(
                new SpecDTO(EXISTS_SPEC_ID, "First Specification"));
        designInterface.createSpecification(
                new SpecDTO("id2", "Second Specification"));

        designInterface.createEntity(
                new EntityDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME, false));
        designInterface.createEntity(
                new EntityDTO(EXISTS_SPEC_ID, "Second Entity", false));

        designInterface.createAttribute(new AttributeDTO(EXISTS_SPEC_ID,
                EXISTS_ENTITY_NAME, "att1", "Boolean", true));
        designInterface.createAttribute(
                new AttributeDTO(EXISTS_SPEC_ID, EXISTS_ENTITY_NAME,
                        EXISTS_ATTRIBUTE_NAME_STRING, "String", false));
        designInterface.createAttribute(new AttributeDTO(EXISTS_SPEC_ID,
                EXISTS_ENTITY_NAME, "att3", "Number", true));

        designInterface.createRelation(new RelationDTO(EXISTS_SPEC_ID,
                "Relation name", EXISTS_ENTITY_NAME, "first", "1",
                "Second Entity", "second", "*"));

        ExpressionDTO expDTO = new ExpressionDTO(EXISTS_SPEC_ID, Type.GREATER,
                new ExpressionDTO(EXISTS_SPEC_ID, Type.STRING, "today"),
                new ExpressionDTO(EXISTS_SPEC_ID, Type.ATT_VALUE,
                        EXISTS_ENTITY_NAME + "."
                                + EXISTS_ATTRIBUTE_NAME_STRING));

        designInterface
                .createRule(new RuleDTO(EXISTS_SPEC_ID, "myRule", expDTO));

    }

}
