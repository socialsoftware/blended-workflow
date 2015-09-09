package pt.ist.socialsoftware.blendedworkflow.integration;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.Application;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.filter.TransactionFilter;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO.Type;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RestDesignInterfaceTest {
    private static final Logger logger = LoggerFactory
            .getLogger(RestDesignInterfaceTest.class);

    @Autowired
    private TransactionFilter transactionFilter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static final String SPEC_ID = "id1";
    private static final String SPEC_NAME = "name";
    private static final String ENTITY_ONE_NAME = "First Entity";
    private static final String ATTRIBUTE_TWO_NAME_STRING = "att2";

    @Before
    public void setUp() {
        logger.debug("LocalSystemTest::setUp");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(transactionFilter).build();
    }

    @After
    @Atomic
    public void tearDown() {
        logger.debug("LocalSystemTest::tearDown");
        BlendedWorkflow.getInstance().delete();
    }

    @Test
    public void walktrough() throws Exception {
        mockMvc.perform(get("/specs/{specId}", "notexists"))
                .andExpect(status().is4xxClientError());

        ObjectMapper mapper = new ObjectMapper();

        SpecDTO specDTO = new SpecDTO(SPEC_ID, SPEC_NAME);
        mockMvc.perform(
                post("/specs").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(specDTO)))
                .andExpect(status().isCreated())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.specId", is(SPEC_ID)))
                .andExpect(jsonPath("$.name", is(SPEC_NAME)));

        mockMvc.perform(get("/specs/{specId}", SPEC_ID))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value(SPEC_NAME));

        EntityDTO entityOneDTO = new EntityDTO(SPEC_ID, ENTITY_ONE_NAME, false);
        MvcResult result = mockMvc
                .perform(post("/specs/{specId}/datamodel/entities", SPEC_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(entityOneDTO)))
                .andExpect(status().isCreated())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value(ENTITY_ONE_NAME))
                .andExpect(jsonPath("$.exists").value(false)).andReturn();
        entityOneDTO = mapper.readValue(
                result.getResponse().getContentAsString(), EntityDTO.class);

        EntityDTO entityTwoDTO = new EntityDTO(SPEC_ID, "entityTwo", false);
        result = mockMvc
                .perform(post("/specs/{specId}/datamodel/entities", SPEC_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(entityTwoDTO)))
                .andExpect(status().isCreated()).andReturn();
        entityTwoDTO = mapper.readValue(
                result.getResponse().getContentAsString(), EntityDTO.class);

        AttributeDTO attributeOneDTO = new AttributeDTO(SPEC_ID,
                entityOneDTO.getExtId(), null, "att1", "Boolean", true);
        result = mockMvc
                .perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(attributeOneDTO)))
                .andExpect(status().isCreated())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("att1"))
                .andExpect(jsonPath("$.type").value("Boolean"))
                .andExpect(jsonPath("$.isMandatory").value(true)).andReturn();
        attributeOneDTO = mapper.readValue(
                result.getResponse().getContentAsString(), AttributeDTO.class);

        AttributeDTO attributeTwoDTO = new AttributeDTO(SPEC_ID,
                entityOneDTO.getExtId(), null, ATTRIBUTE_TWO_NAME_STRING,
                "String", false);
        mockMvc.perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(attributeTwoDTO)))
                .andExpect(status().isCreated());

        AttributeDTO attributeThreeDTO = new AttributeDTO(SPEC_ID,
                entityOneDTO.getExtId(), null, "att3", "Number", true);
        mockMvc.perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(attributeThreeDTO)))
                .andExpect(status().isCreated());

        RelationDTO relationDTO = new RelationDTO(SPEC_ID, "relation name",
                entityOneDTO.getExtId(), "first", "1", entityTwoDTO.getExtId(),
                "second", "*");
        mockMvc.perform(post("/specs/{specId}/datamodel/relations", SPEC_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(relationDTO)))
                .andExpect(status().isCreated());

        ExpressionDTO expressionDTO = new ExpressionDTO(SPEC_ID, Type.GREATER,
                new ExpressionDTO(SPEC_ID, Type.STRING, "today"),
                new ExpressionDTO(SPEC_ID, Type.ATT_VALUE,
                        ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME_STRING));
        RuleDTO ruleDTO = new RuleDTO(SPEC_ID, "myRule", expressionDTO);
        mockMvc.perform(post("/specs/{specId}/datamodel/rules", SPEC_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(ruleDTO)))
                .andExpect(status().isCreated());
    }

}
