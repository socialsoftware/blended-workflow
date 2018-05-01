package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.WorkflowInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.AbstractSpecTest1Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.ist.socialsoftware.blendedworkflow.resources.security.SecurityConstants.HEADER_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InstanceWorkflowTest extends AbstractSpecTest1Test {
    private Logger logger = LoggerFactory.getLogger(InstanceWorkflowTest.class);

    private final String SPEC_ID = "spec-test-1";
    private final String WORKFLOW_ID = "workflow-1";
    private WorkflowInstanceDTO workflowInstanceDTO;

    @Before
    public void init() {
        workflowInstanceDTO = new WorkflowInstanceDTO();
        workflowInstanceDTO.setSpecId(SPEC_ID);
        workflowInstanceDTO.setName(WORKFLOW_ID);
    }

    @Test
    public void testGetInstances() throws Exception {
        String token = extractToken("Guilherme", "Guilherme");

        mockMvc.perform(
                    get("/specs/" + SPEC_ID + "/instances/")
                            .header(HEADER_STRING, constructToken(token))
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetInstancesWithoutLogin() throws Exception {
        mockMvc.perform(get("/specs/" + SPEC_ID + "/instances/")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateInstance() throws Exception {
        String token = extractToken("Guilherme", "Guilherme");

        mockMvc.perform(
                post("/specs/" + SPEC_ID + "/instances/")
                        .header(HEADER_STRING, constructToken(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(workflowInstanceDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(json(workflowInstanceDTO)));
    }

    @Test
    public void testCreateInstanceWithoutLogin() throws Exception {
        mockMvc.perform(
                post("/specs/" + SPEC_ID + "/instances/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(workflowInstanceDTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetInstance() throws Exception {
        testCreateInstance();

        String token = extractToken("Guilherme", "Guilherme");

        MvcResult result = mockMvc.perform(
                get("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/")
                        .header(HEADER_STRING, constructToken(token))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json(workflowInstanceDTO)))
                .andReturn();
    }

    @Test
    public void testGetInstanceWithoutLogin() throws Exception {
        testCreateInstance();

        mockMvc.perform(get("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteInstance() throws Exception {
        testCreateInstance();

        String token = extractToken("Guilherme", "Guilherme");

        mockMvc.perform(
                delete("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/")
                        .header(HEADER_STRING, constructToken(token))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteInstanceWithoutLogin() throws Exception {
        testCreateInstance();

        mockMvc.perform(delete("/specs/" + SPEC_ID + "/instances/" + WORKFLOW_ID + "/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
