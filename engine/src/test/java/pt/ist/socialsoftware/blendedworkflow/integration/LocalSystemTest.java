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
        logger.info("LocalSystemTest::setUp");
        designInterface = DesignInterface.getInstance();
    }

    @After
    @Atomic
    public void tearDown() {
        logger.info("LocalSystemTest::tearDown");
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
    }

}
