package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.apache.naming.factory.ResourceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.WorkflowInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractDocExampleTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ActivityWorkflowTest extends AbstractDocExampleTest {
    public static final String USERNAME = "John";
    public static final String PASSWORD = "John";
    private Logger logger = LoggerFactory.getLogger(ActivityWorkflowTest.class);

    private final String SPEC_ID = "DoctorAppointment";
    private final String WORKFLOW_ID = "workflow-1";
    private WorkflowInstance instance;

    private Specification spec;
    private ExecutionResourcesInterface edi;

    @Override
    public void populate4Test() {
        super.populate4Test();

        spec = BlendedWorkflow.getInstance().getSpecById(SPEC_ID).get();
        instance = new WorkflowInstance(spec, WORKFLOW_ID);
        edi = ExecutionResourcesInterface.getInstance();
    }

    @Test
    public void testGetNextActivities() throws Exception {
        String token = extractToken(USERNAME, PASSWORD);

        mockMvc.perform(
                    get("/specs/" + SPEC_ID + "/instances/" + instance.getName() + "/activityworkitem/next")
                            .header(HEADER_STRING, constructToken(token))
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetNextActivitiesWithInvalidUser() throws Exception {
        String token = extractToken("William", "William");

        mockMvc.perform(
                get("/specs/" + SPEC_ID + "/instances/" + instance.getName() + "/activityworkitem/next")
                        .header(HEADER_STRING, constructToken(token))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
