package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.UnitDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RestUtil;

public class ResourceModelInterface {
	private static Logger logger = LoggerFactory.getLogger(ResourceModelInterface.class);

	final static String BASE_URL = "http://localhost:8080";

	private static ResourceModelInterface instance = null;

	public static ResourceModelInterface getInstance() {
		if (instance == null) {
			instance = new ResourceModelInterface();
		}
		return instance;
	}
	
	public Boolean cleanResourceModel(String specId) {
		logger.debug("cleanResourceModel: {}", specId);

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/clean";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		Boolean response = restTemplate.getForObject(uri, Boolean.class, params);

		return response;
	}
	
	public boolean createResourceModel(String specId, BWNotification notification) {
		logger.debug("createResourceModel");
		
		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/";

		Map<String, String> params = new HashMap<>();
		params.put("specId", specId);

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		Boolean result = null;
		try {
			result = restTemplate.postForObject(uri, true, Boolean.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
	
	public CapabilityDTO createCapability(CapabilityDTO capability, BWNotification notification) {
		logger.debug("createCapability: {}, {}", capability.getName(), capability.getDescription());

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/capabilities";

		Map<String, String> params = new HashMap<>();
		params.put("specId", capability.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		CapabilityDTO result = null;
		try {
			result = restTemplate.postForObject(uri, capability, CapabilityDTO.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
	
	public RoleDTO createRole(RoleDTO role, BWNotification notification) {
		logger.debug("createRole: {}, {}", role.getName(), role.getDescription());

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/roles";

		Map<String, String> params = new HashMap<>();
		params.put("specId", role.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		RoleDTO result = null;
		try {
			result = restTemplate.postForObject(uri, role, RoleDTO.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
	
	public UnitDTO createUnit(UnitDTO unit, BWNotification notification) {
		logger.debug("createUnit: {}, {}", unit.getName(), unit.getDescription());

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/units";

		Map<String, String> params = new HashMap<>();
		params.put("specId", unit.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		UnitDTO result = null;
		try {
			result = restTemplate.postForObject(uri, unit, UnitDTO.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
	
	public PersonDTO createPerson(PersonDTO person, BWNotification notification) {
		logger.debug("createPerson: {}, {}, {}, {}", person.getName(), person.getCapabilities(), person.getPositions());

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/persons";

		Map<String, String> params = new HashMap<>();
		params.put("specId", person.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		PersonDTO result = null;
		try {
			result = restTemplate.postForObject(uri, person, PersonDTO.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
	
	public PositionDTO createPosition(PositionDTO position, BWNotification notification) {
		logger.debug("createPerson: {}, {}, {}, {}, {}, {}", position.getName(), position.getUnit(), position.getRoles(), position.getDelegateToRelations(), position.getReportsTo());

		final String uri = BASE_URL + "/specs/{specId}/resourcemodel/positions";

		Map<String, String> params = new HashMap<>();
		params.put("specId", position.getSpecId());

		RestTemplate restTemplate = RestUtil.getRestTemplate();
		PositionDTO result = null;
		try {
			result = restTemplate.postForObject(uri, position, PositionDTO.class, params);
		} catch (RestClientException rce) {
			notification.addError(new BWError("REST connection", rce.getMessage()));
		} catch (Exception e) {
			notification.addError(new BWError("HTTP Error", "There was an error in the HTTP connection."));
		}
		
		return result;
	}
}
