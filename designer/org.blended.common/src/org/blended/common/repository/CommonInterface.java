package org.blended.common.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.blended.common.repository.resttemplate.BWError;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.RestUtil;
import org.blended.common.repository.resttemplate.vo.DependenceVO;
import org.blended.common.repository.resttemplate.vo.EntityVO;
import org.blended.common.repository.resttemplate.vo.SpecVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonInterface {
    private static Logger log = LoggerFactory.getLogger(CommonInterface.class);

    final static String SERVER_ADDRESS = "http://localhost:8080";

    private static CommonInterface instance = null;

    public static CommonInterface getInstance() {
        if (instance == null) {
            instance = new CommonInterface();
        }
        return instance;
    }

    public SpecVO getSpecBySpecId(String specId) {
        log.debug("getSpecBySpecId: {}", specId);

        final String uri = SERVER_ADDRESS + "/specs/{specId}";

        RestTemplate restTemplate = RestUtil.getRestTemplate();

        Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("specId", specId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri,
                HttpMethod.GET, entity, String.class, uriVariables);

        ObjectMapper mapper = new ObjectMapper();
        String responseBody = response.getBody();
        log.debug("getSpecBySpecId responseBody: {}", responseBody);
        try {
            if (RestUtil.isError(response.getStatusCode())) {
                BWError error = mapper.readValue(responseBody, BWError.class);
                throw new RepositoryException(error);
            } else {
                SpecVO specVO = mapper.readValue(responseBody, SpecVO.class);
                return specVO;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public SpecVO createSpec(SpecVO specVO) {
        log.debug("createSpec: {}, {}", specVO.getSpecId(), specVO.getName());

        final String uri = SERVER_ADDRESS + "/specs";

        RestTemplate restTemplate = RestUtil.getRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<SpecVO> entity = new HttpEntity<SpecVO>(specVO, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri,
                HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        String responseBody = response.getBody();
        log.debug("createSpec responseBody: {}", responseBody);
        try {
            if (RestUtil.isError(response.getStatusCode())) {
                BWError error = mapper.readValue(responseBody, BWError.class);
                throw new RepositoryException(error);
            } else {
                SpecVO newSpecVO = mapper.readValue(responseBody, SpecVO.class);
                return newSpecVO;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanDataModel(String dataModelExtId) {
        log.debug("cleanDataModel: {}", dataModelExtId);

        final String uri = SERVER_ADDRESS + "/datamodels/{extId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("extId", dataModelExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public EntityVO createEntity(EntityVO entityVO) {
        log.debug("createEntity: {}, {}, {}", entityVO.getDataModelExtId(),
                entityVO.getName(), entityVO.getExists());

        final String uri = SERVER_ADDRESS + "/entities";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        EntityVO result = restTemplate.postForObject(uri, entityVO,
                EntityVO.class);

        return result;
    }

    public DependenceVO createDependence(DependenceVO dependenceVO) {
        log.debug("createDependence: {}, {}", dependenceVO.getProductExtId(),
                dependenceVO.getPath());

        final String uri = SERVER_ADDRESS + "/dependencies";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class);

        return result;
    }

    public Set<DependenceVO> getDependencies(String dataModelExtId) {
        log.debug("getDependencies: {}", dataModelExtId);

        final String uri = SERVER_ADDRESS
                + "datamodels/{dataModelExtId}/dependencies";

        Map<String, String> params = new HashMap<String, String>();
        params.put("dataModelExtId", dataModelExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO[] result = restTemplate.getForObject(uri,
                DependenceVO[].class, params);

        return new HashSet<DependenceVO>(Arrays.asList(result));
    }

    public boolean checkDependence(String extId) {
        log.debug("checkDependence: {}", extId);

        final String uri = SERVER_ADDRESS + "/dependencies/{depExtId}/check";

        Map<String, String> params = new HashMap<String, String>();
        params.put("depExtId", extId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        Boolean response = restTemplate.getForObject(uri, Boolean.class,
                params);

        return response;
    }

    public void deleteDependence(String extId) {
        log.debug("checkDependence: {}", extId);

        final String uri = SERVER_ADDRESS + "/dependencies/{depExtId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("depExtId", extId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.delete(uri, params);
    }

    // public ProductDTO getSourceOfPath(String specId, String path) {
    // log.debug("getSourceOfPath Path:{}", path);
    // return adi.getSourceOfPath(specId, path);
    // }
    //
    // public ProductDTO getTargetOfPath(String specId, String path) {
    // log.debug("getTargetOfPath Path:{}", path);
    // return adi.getTargetOfPath(specId, path);
    // }
    //
    // public Set<String> getDependencePaths(String specId,
    // Set<String> sucConditions) {
    // log.debug("getDependencePaths Path:{}", sucConditions);
    // return adi.getDependencePaths(specId, sucConditions);
    // }

}
