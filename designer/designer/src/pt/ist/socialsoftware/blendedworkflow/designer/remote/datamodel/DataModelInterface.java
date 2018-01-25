package pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RepositoryException;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RestUtil;

public class DataModelInterface {
	private static Logger logger = LoggerFactory.getLogger(DataModelInterface.class);

	final static String BASE_URL = "http://localhost:8080";

	private static DataModelInterface instance = null;

	public static DataModelInterface getInstance() {
		if (instance == null) {
			instance = new DataModelInterface();
		}
		return instance;
	}

	public void deleteSpecification(String specId) {
		logger.debug("deleteSpecification: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		restTemplate.delete(uri, params);
	}

	public SpecDTO getSpecBySpecId(String specId) {
		logger.debug("getSpecBySpecId: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}";

		RestTemplate restTemplate = RestUtil.getRestTemplate();

		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("specId", specId);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class,
				uriVariables);

		ObjectMapper mapper = new ObjectMapper();
		String responseBody = response.getBody();
		logger.debug("getSpecBySpecId responseBody: {}", responseBody);
		try {
			if (RestUtil.isError(response.getStatusCode())) {
				BWError error = mapper.readValue(responseBody, BWError.class);
				throw new RepositoryException(error);
			} else {
				SpecDTO specVO = mapper.readValue(responseBody, SpecDTO.class);
				return specVO;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public SpecDTO createSpec(SpecDTO specVO) {
		logger.debug("createSpec: {}, {}", specVO.getSpecId(), specVO.getName());

		final String uri = BASE_URL + "/specs";

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		SpecDTO result = restTemplate.postForObject(uri, specVO, SpecDTO.class);

		return result;
	}

	public boolean checkBlendedWorkflowModel(String specId) {
		logger.debug("checkDataModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/datamodel/check";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		Boolean response = restTemplate.getForObject(uri, Boolean.class, params);

		return response;
	}

	public void cleanBlendedWorkflowModel(String specId) {
		logger.debug("cleanDataModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/datamodel";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		restTemplate.put(uri, null, params);
	}

	public EntityDTO createEntity(EntityDTO entityVO) {
		logger.debug("createEntity: {}, {}, {}", entityVO.getSpecId(), entityVO.getName(), entityVO.getExists());

		final String uri = BASE_URL + "/specs/{specId}/datamodel/entities";

		Map<String, String> params = new HashMap<>();
		params.put("specId", entityVO.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		EntityDTO result = restTemplate.postForObject(uri, entityVO, EntityDTO.class, params);

		return result;
	}

	public DependenceDTO createDependence(DependenceDTO dependenceVO) {
		logger.debug("createDependence: {}, {}", dependenceVO.getProduct(), dependenceVO.getPath());

		final String uri = BASE_URL + "/specs/{specId}/datamodel/dependencies";

		Map<String, String> params = new HashMap<>();
		params.put("specId", dependenceVO.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		DependenceDTO result = restTemplate.postForObject(uri, dependenceVO, DependenceDTO.class, params);

		return result;
	}

	public Set<DependenceDTO> getDependencies(String specId) {
		logger.debug("getDependencies: {}", specId);

		final String uri = BASE_URL + "specs/{specId}/datamodel/dependencies";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		DependenceDTO[] result = restTemplate.getForObject(uri, DependenceDTO[].class, params);

		return new HashSet<>(Arrays.asList(result));
	}

	public boolean checkDependence(String specId, String extId) {
		logger.debug("checkDependence: {}", extId);

		final String uri = BASE_URL + "/specs/{specId}/datamodel/dependencies/{depExtId}/check";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);
		params.put("depExtId", extId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		Boolean response = restTemplate.getForObject(uri, Boolean.class, params);

		return response;
	}

	public void deleteDependence(String specId, String extId) {
		logger.debug("deleteDependence: {}", extId);

		final String uri = BASE_URL + "/specs/{specId}/datamodel/dependencies/{depExtId}";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);
		params.put("depExtId", extId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		restTemplate.delete(uri, params);
	}

	public AttributeDTO createAttribute(AttributeDTO attribueDTO) {
		logger.debug("createAttribute: entitityExtId:{}, {}, {}, {}", attribueDTO.getEntityExtId(),
				attribueDTO.getName(), attribueDTO.getType(), attribueDTO.isMandatory());

		final String uri = BASE_URL + "/specs/{specId}/datamodel/attributes";

		Map<String, String> params = new HashMap<>();
		params.put("specId", attribueDTO.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		AttributeDTO result = restTemplate.postForObject(uri, attribueDTO, AttributeDTO.class, params);

		return result;
	}

	public RelationDTO createRelation(RelationDTO relationDTO) {
		logger.debug("createRelation: {}, {}, {}", relationDTO.getName(), relationDTO.getEntOneName(),
				relationDTO.getEntTwoName());

		final String uri = BASE_URL + "/specs/{specId}/datamodel/relations";

		Map<String, String> params = new HashMap<>();
		params.put("specId", relationDTO.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		RelationDTO result = restTemplate.postForObject(uri, relationDTO, RelationDTO.class, params);

		return result;
	}

	public RuleDTO createRule(RuleDTO ruleDTO) {
		logger.debug("createRule: {}, {}, {}", ruleDTO.getSpecId(), ruleDTO.getEntityName(), ruleDTO.getName(),
				ruleDTO.getExpression().toString());

		final String uri = BASE_URL + "/specs/{specId}/datamodel/rules";

		Map<String, String> params = new HashMap<>();
		params.put("specId", ruleDTO.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		RuleDTO result = restTemplate.postForObject(uri, ruleDTO, RuleDTO.class, params);

		return result;
	}

	public Boolean generateConditionModel(String specId) {
		logger.debug("generateConditionModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/conditionmodel";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		return restTemplate.postForObject(uri, null, Boolean.class, params);
	}

	public Boolean generateGoalModel(String specId) {
		logger.debug("generateGoalModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/goalmodel";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		return restTemplate.postForObject(uri, null, Boolean.class, params);
	}

	public Boolean generateActivityModel(String specId) {
		logger.debug("generateActivityModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/activitymodel";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		return restTemplate.postForObject(uri, null, Boolean.class, params);
	}

	public void printSpecificationModels(String specId) {
		logger.debug("printSpecificationModels: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/print";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		restTemplate.getForObject(uri, Boolean.class, params);
	}
}
