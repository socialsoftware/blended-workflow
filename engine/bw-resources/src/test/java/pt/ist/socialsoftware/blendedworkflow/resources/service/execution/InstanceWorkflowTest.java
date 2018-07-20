package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

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
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkflowInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractDocExampleTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InstanceWorkflowTest extends AbstractDocExampleTest {
    public static final String USERNAME = "John";
    public static final String PASSWORD = "John";
    private Logger logger = LoggerFactory.getLogger(InstanceWorkflowTest.class);

    private final String SPEC_ID = "DoctorAppointment";
    private final String WORKFLOW_ID = "workflow-1";
    private final String WORKFLOW_ID_FOR_TESTS = "workflow-2";
    private WorkflowInstanceDto _workflowInstanceDto;
    private WorkflowInstance instance;

    private Specification spec;

    @Override
    public void populate4Test() {
        super.populate4Test();

        _workflowInstanceDto = new WorkflowInstanceDto();
        _workflowInstanceDto.setSpecId(SPEC_ID);
        _workflowInstanceDto.setName(WORKFLOW_ID_FOR_TESTS);

        spec = BlendedWorkflow.getInstance().getSpecById(SPEC_ID).get();
        instance = new WorkflowInstance(spec,WORKFLOW_ID);
    }

    @Test
    public void testGetInstances() throws Exception {
        String token = extractToken(USERNAME, PASSWORD);

        mockMvc.perform(
                    get("/specs/" + SPEC_ID + "/instances/")
                            .header(HEADER_STRING, constructToken(token))
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + json(instance.getDTO()) + "]"));
    }

    @Test
    public void testGetInstancesWithoutLogin() throws Exception {
        mockMvc.perform(get("/specs/" + SPEC_ID + "/instances/")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateInstance() throws Exception {
        String token = extractToken(USERNAME, PASSWORD);

        mockMvc.perform(
                post("/specs/" + SPEC_ID + "/instances/")
                        .header(HEADER_STRING, constructToken(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(_workflowInstanceDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(json(_workflowInstanceDto)));
    }

    @Test
    public void testCreateInstanceWithoutLogin() throws Exception {
        mockMvc.perform(
                post("/specs/" + SPEC_ID + "/instances/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(_workflowInstanceDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetInstance() throws Exception {
        String token = extractToken(USERNAME, PASSWORD);

        mockMvc.perform(
                get("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/")
                        .header(HEADER_STRING, constructToken(token))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json(instance.getDTO())));
    }

    @Test
    public void testGetInstanceWithoutLogin() throws Exception {
        mockMvc.perform(get("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteInstance() throws Exception {
        String token = extractToken(USERNAME, PASSWORD);

        mockMvc.perform(
                delete("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/")
                        .header(HEADER_STRING, constructToken(token))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteInstanceWithoutLogin() throws Exception {
        mockMvc.perform(delete("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
