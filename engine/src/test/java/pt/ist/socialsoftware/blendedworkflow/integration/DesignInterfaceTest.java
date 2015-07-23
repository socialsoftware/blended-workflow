package pt.ist.socialsoftware.blendedworkflow.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

public class DesignInterfaceTest {
    private DesignInterface designInterface;

    private static final Logger logger = LoggerFactory
            .getLogger(DesignInterfaceTest.class);

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
        designInterface.createSpecification(
                new SpecificationDTO("id1", "First Specification"));
        designInterface.createSpecification(
                new SpecificationDTO("id2", "Second Specification"));

        designInterface.createEntity(
                new EntityDTO("First Specification", "First Entity", false));
        designInterface.createEntity(
                new EntityDTO("First Specification", "Second Entity", false));

        designInterface.createAttribute(new AttributeDTO("First Specification",
                "First Entity", "att1", "Boolean"));
        designInterface.createAttribute(new AttributeDTO("First Specification",
                "First Entity", "att2", "String"));
        designInterface.createAttribute(new AttributeDTO("First Specification",
                "First Entity", "att3", "Number"));

        designInterface.createRelation(new RelationDTO("First Specification",
                "First Entity", "first", "1", "Second Entity", "second", "*"));

    }

}
