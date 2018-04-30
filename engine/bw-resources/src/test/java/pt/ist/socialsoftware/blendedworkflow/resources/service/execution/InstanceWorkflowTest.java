package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractSpecTest1Test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InstanceWorkflowTest extends AbstractSpecTest1Test {
    private Logger logger = LoggerFactory.getLogger(InstanceWorkflowTest.class);

    private final String SPECID = "spec-test-1";

    @Test
    public void testGetInstancesAuthorization() throws Exception {
        mockMvc.perform(get("/specs/" + SPECID + "/instances/")).andExpect(status().isUnauthorized());
    }
}
