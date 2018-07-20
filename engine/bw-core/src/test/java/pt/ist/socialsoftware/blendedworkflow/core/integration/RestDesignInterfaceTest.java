package pt.ist.socialsoftware.blendedworkflow.core.integration;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Expression.ExpressionAtom;
import pt.ist.socialsoftware.blendedworkflow.core.filter.TransactionFilter;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RuleDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@WebAppConfiguration
public class RestDesignInterfaceTest {
	private static final Logger logger = LoggerFactory.getLogger(RestDesignInterfaceTest.class);

	@Autowired
	private TransactionFilter transactionFilter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private static final String SPEC_ID = "id1";
	private static final String SPEC_NAME = "name for integration tests";
	private static final String ENTITY_ONE_NAME = "First Entity";
	private static final String ATTRIBUTE_TWO_NAME_STRING = "att2";

	@Before
	public void setUp() {
		logger.debug("LocalSystemTest::setUp");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).addFilter(this.transactionFilter)
				.build();
	}

	@After
	@Atomic
	public void tearDown() {
		logger.debug("LocalSystemTest::tearDown");
		BlendedWorkflow.getInstance().delete();
	}

	@Test
	public void walktroughTest() throws Exception {
		this.mockMvc.perform(get("/specs/{specId}", "notexists")).andExpect(status().is4xxClientError());

		ObjectMapper mapper = new ObjectMapper();

		SpecDto specDTO = new SpecDto(SPEC_ID, SPEC_NAME);
		this.mockMvc
				.perform(post("/specs").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsBytes(specDTO)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.specId", is(SPEC_ID))).andExpect(jsonPath("$.name", is(SPEC_NAME)));

		this.mockMvc.perform(get("/specs/{specId}", SPEC_ID)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value(SPEC_NAME));

		EntityDto entityOneDTO = new EntityDto(SPEC_ID, ENTITY_ONE_NAME, false);
		MvcResult result = this.mockMvc
				.perform(post("/specs/{specId}/datamodel/entities", SPEC_ID)
						.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(entityOneDTO)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value(ENTITY_ONE_NAME)).andExpect(jsonPath("$.exists").value(false))
				.andReturn();
		entityOneDTO = mapper.readValue(result.getResponse().getContentAsString(), EntityDto.class);

		EntityDto entityTwoDTO = new EntityDto(SPEC_ID, "entityTwo", false);
		result = this.mockMvc
				.perform(post("/specs/{specId}/datamodel/entities", SPEC_ID)
						.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(entityTwoDTO)))
				.andExpect(status().isCreated()).andReturn();
		entityTwoDTO = mapper.readValue(result.getResponse().getContentAsString(), EntityDto.class);

		AttributeDto attributeOneDTO = new AttributeDto(SPEC_ID, entityOneDTO.getExtId(), null, "att1", "Boolean",
				true);
		result = this.mockMvc
				.perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsBytes(attributeOneDTO)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("att1")).andExpect(jsonPath("$.type").value("Boolean"))
				.andExpect(jsonPath("$.mandatory").value(true)).andReturn();
		attributeOneDTO = mapper.readValue(result.getResponse().getContentAsString(), AttributeDto.class);

		AttributeDto attributeTwoDTO = new AttributeDto(SPEC_ID, entityOneDTO.getExtId(), null,
				ATTRIBUTE_TWO_NAME_STRING, "String", false);
		this.mockMvc.perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(attributeTwoDTO)))
				.andExpect(status().isCreated());

		AttributeDto attributeThreeDTO = new AttributeDto(SPEC_ID, entityOneDTO.getExtId(), null, "att3", "Number",
				true);
		this.mockMvc.perform(post("/specs/{specId}/datamodel/attributes", SPEC_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(attributeThreeDTO)))
				.andExpect(status().isCreated());

		RelationDto relationDTO = new RelationDto(SPEC_ID, "relation name", entityOneDTO.getExtId(), "first", "1",
				entityTwoDTO.getExtId(), "second", "*");
		this.mockMvc
				.perform(post("/specs/{specId}/datamodel/relations", SPEC_ID)
						.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(relationDTO)))
				.andExpect(status().isCreated());

		ExpressionDto expressionDTO = new ExpressionDto(SPEC_ID, ComparisonOperator.GREATER,
				new ExpressionDto(SPEC_ID, ExpressionAtom.STRING, "today"), new ExpressionDto(SPEC_ID,
						ExpressionAtom.ATT_VALUE, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME_STRING));
		RuleDto ruleDTO = new RuleDto(SPEC_ID, ENTITY_ONE_NAME, "myRule", expressionDTO);
		this.mockMvc.perform(post("/specs/{specId}/datamodel/rules", SPEC_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(ruleDTO)))
				.andExpect(status().isCreated());
	}

}
