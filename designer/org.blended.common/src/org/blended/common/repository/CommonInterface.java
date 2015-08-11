package org.blended.common.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.blended.common.repository.resttemplate.BWError;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.RestUtil;
import org.blended.common.repository.resttemplate.vo.AttributeGroupVO;
import org.blended.common.repository.resttemplate.vo.AttributeVO;
import org.blended.common.repository.resttemplate.vo.DEFEntityConditionVO;
import org.blended.common.repository.resttemplate.vo.DefAttributeConditionVO;
import org.blended.common.repository.resttemplate.vo.DependenceVO;
import org.blended.common.repository.resttemplate.vo.EntityVO;
import org.blended.common.repository.resttemplate.vo.GoalVO;
import org.blended.common.repository.resttemplate.vo.MulConditionVO;
import org.blended.common.repository.resttemplate.vo.ProductVO;
import org.blended.common.repository.resttemplate.vo.RelationVO;
import org.blended.common.repository.resttemplate.vo.RuleVO;
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

    public void deleteSpecification(String specId) {
        log.debug("deleteSpecification: {}", specId);

        final String uri = SERVER_ADDRESS + "/specs/{specId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.delete(uri, params);

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

    public AttributeVO createAttribute(AttributeVO attribueVO) {
        log.debug(
                "createAttribute: entitityExtId:{}, groupExtId:{}, {}, {}, {}",
                attribueVO.getEntityExtId(), attribueVO.getGroupExtId(),
                attribueVO.getName(), attribueVO.getType(),
                attribueVO.getIsMandatory());

        final String uri = SERVER_ADDRESS + "/attributes";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        AttributeVO result = restTemplate.postForObject(uri, attribueVO,
                AttributeVO.class);

        return result;
    }

    public AttributeGroupVO createAttributeGroup(
            AttributeGroupVO attributeGroupVO) {
        log.debug("createAttributeGroup: entitityExtId:{},  {}, {}",
                attributeGroupVO.getEntityExtId(), attributeGroupVO.getName(),
                attributeGroupVO.isMandatory());

        final String uri = SERVER_ADDRESS + "/groupattributes";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        AttributeGroupVO result = restTemplate.postForObject(uri,
                attributeGroupVO, AttributeGroupVO.class);

        return result;
    }

    public RelationVO createRelation(RelationVO relationVO) {
        log.debug("createRelation: {}, {}, {}", relationVO.getName(),
                relationVO.getEntOneName(), relationVO.getEntTwoName());

        final String uri = SERVER_ADDRESS + "/relations";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RelationVO result = restTemplate.postForObject(uri, relationVO,
                RelationVO.class);

        return result;
    }

    public RuleVO createRule(RuleVO ruleVO) {
        log.debug("createRule: {}, {}, {}", ruleVO.getDataModelExtId(),
                ruleVO.getName(), ruleVO.getExpression());

        final String uri = SERVER_ADDRESS + "/rules";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RuleVO result = restTemplate.postForObject(uri, ruleVO, RuleVO.class);

        return result;
    }

    public void printSpecificationModels(String specId) {
        log.debug("printSpecificationModels: {}", specId);

        final String uri = SERVER_ADDRESS + "/specs/{specId}/print";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.getForObject(uri, Boolean.class, params);
    }

    public void cleanConditionModel(String conditionModelExtId) {
        log.debug("cleanConditionModel: {}", conditionModelExtId);

        final String uri = SERVER_ADDRESS + "/conditionmodels/{extId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("extId", conditionModelExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public DEFEntityConditionVO createEntityAchieveCondition(
            DEFEntityConditionVO entityAchieveConditionVO) {
        log.debug("createEntityAchieveCondition: {}",
                entityAchieveConditionVO.getConditionModelExtId(),
                entityAchieveConditionVO.getEntityName(),
                entityAchieveConditionVO.isExists());

        final String uri = SERVER_ADDRESS + "/entityachieveconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri,
                entityAchieveConditionVO, DEFEntityConditionVO.class);

        return result;
    }

    public DependenceVO createEntityDependenceCondition(
            DependenceVO dependenceVO) {
        log.debug("createEntityDependenceCondition entityExtId:{}, path:{}",
                dependenceVO.getProductExtId(), dependenceVO.getPath());

        final String uri = SERVER_ADDRESS + "/entitydependenceconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class);

        return result;
    }

    public MulConditionVO createEntityInvariantCondition(
            MulConditionVO mulConditionVO) {
        log.debug("createEntityInvariantCondition rolePath:{}, cardinality:{}",
                mulConditionVO.getRolePath(), mulConditionVO.getCardinality());

        final String uri = SERVER_ADDRESS + "/entityinvariantconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        MulConditionVO result = restTemplate.postForObject(uri, mulConditionVO,
                MulConditionVO.class);

        return result;
    }

    public DefAttributeConditionVO createAttributeAchieveCondition(
            DefAttributeConditionVO defAttributeConditionVO) {
        log.debug("createAttributeAchieveCondition paths:{}, mandatory:{}",
                defAttributeConditionVO.getPaths().toString(),
                defAttributeConditionVO.isMandatory());

        final String uri = SERVER_ADDRESS + "/attributeachieveconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DefAttributeConditionVO result = restTemplate.postForObject(uri,
                defAttributeConditionVO, DefAttributeConditionVO.class);

        return result;
    }

    public RuleVO createAttributeInvariantCondition(RuleVO ruleVO) {
        log.debug(
                "createAttributeInvariantCondition conditionModelExtId:{}, name:{}",
                ruleVO.getConditionModelExtId(), ruleVO.getName());

        final String uri = SERVER_ADDRESS + "/attributeinvariantconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RuleVO result = restTemplate.postForObject(uri, ruleVO, RuleVO.class);

        return result;
    }

    public ProductVO getProduct(String dataModelExtId, Set<String> sourceAtts) {
        log.debug("getProduct: {}", sourceAtts.toString());

        final String uri = SERVER_ADDRESS
                + "/datamodels/{dataModelExtId}/products/{atts}/";

        String atts = sourceAtts.stream().collect(Collectors.joining(","));

        Map<String, String> params = new HashMap<String, String>();
        params.put("dataModelExtId", dataModelExtId);
        params.put("atts", atts);

        log.debug("getProduct: {}", atts);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                params);

        return productVO;
    }

    public DependenceVO createAttributeDependenceCondition(
            DependenceVO dependenceVO) {
        log.debug("createDependenceCondition productExtId:{}, path:{}",
                dependenceVO.getProductExtId(), dependenceVO.getPath());

        final String uri = SERVER_ADDRESS + "/attributedependenceconditions";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class);

        return result;
    }

    public EntityVO getEntityByName(String dataModelExtId, String entityName) {
        log.debug("getEntityByName: {}, {}", dataModelExtId, entityName);

        final String uri = SERVER_ADDRESS
                + "/datamodels/{dataModelExtId}/entities/{entityName}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("dataModelExtId", dataModelExtId);
        params.put("entityName", entityName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        EntityVO entityVO = restTemplate.getForObject(uri, EntityVO.class,
                params);

        return entityVO;
    }

    public void cleanGoalModel(String goalModelExtId) {
        log.debug("cleanGoalModel: {}", goalModelExtId);

        final String uri = SERVER_ADDRESS + "/goalmodels/{extId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("extId", goalModelExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public GoalVO createGoal(GoalVO goalVO) {
        log.debug("createGoal goalModelExtId:{}, name:{}",
                goalVO.getGoalModelExtId(), goalVO.getName());

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalVO.getGoalModelExtId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO result = restTemplate.postForObject(uri, goalVO, GoalVO.class,
                params);

        return result;
    }

    public GoalVO getGoalByName(String goalModelExtId, String goalName) {
        log.debug("getGoalByName: {}, {}", goalModelExtId, goalName);

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalName}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO goalVO = restTemplate.getForObject(uri, GoalVO.class, params);

        return goalVO;
    }

    public GoalVO addSubGoal(GoalVO goalVO) {
        log.debug("addSubGoal goalModelExtId:{}, extId:{}, name:{}",
                goalVO.getGoalModelExtId(), goalVO.getExtId(),
                goalVO.getName());

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{extId}/sub";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalVO.getGoalModelExtId());
        params.put("extId", goalVO.getExtId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO result = restTemplate.postForObject(uri, goalVO, GoalVO.class,
                params);

        return result;
    }

    public DEFEntityConditionVO associateEntityAchieveConditionToGoalSucCondition(
            String goalModelExtId, String goalExtId, String path) {
        log.debug(
                "associatedSucConditionToGoal goalModelExtId:{}, goalExtId:{}, path:{}",
                goalModelExtId, goalExtId, path);

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/sucent/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri, null,
                DEFEntityConditionVO.class, params);

        return result;
    }

    public DEFEntityConditionVO associateEntityAchieveConditionToGoalActCondition(
            String goalModelExtId, String goalExtId, String path) {
        log.debug(
                "associatedActConditionToGoal goalModelExtId:{}, goalExtId:{}, path:{}",
                goalModelExtId, goalExtId, path);

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/actent/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri, null,
                DEFEntityConditionVO.class, params);

        return result;
    }

    public void associateAttributeAchieveConditionToGoalActCondition(
            String goalModelExtId, String goalExtId, Set<String> paths) {
        log.debug(
                "associateAttributeAchieveConditionToGoalActCondition goalModelExtId:{}, goalExtId:{}, paths:{}",
                goalModelExtId, goalExtId, paths);

        String pathsParam = paths.stream().collect(Collectors.joining(","));

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/actatt/{paths}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);
        params.put("paths", pathsParam);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, null, String.class, params);
    }

    public void associateAttributeAchieveConditionToGoalSucCondition(
            String goalModelExtId, String goalExtId, Set<String> paths) {
        log.debug(
                "associateAttributeAchieveConditionToGoalActCondition goalModelExtId:{}, goalExtId:{}, paths:{}",
                goalModelExtId, goalExtId, paths);

        String pathsParam = paths.stream().collect(Collectors.joining(","));

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/sucatt/{paths}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);
        params.put("paths", pathsParam);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, null, String.class, params);
    }

    public void associateMulConditionToGoalEntityInvariantCondition(
            String goalModelExtId, String goalExtId,
            MulConditionVO mulConditionVO) {
        log.debug(
                "associateMulConditionToGoalEntityInvariantCondition goalModelExtId:{}, goalExtId:{}, path:{}, cardinality:{}",
                goalModelExtId, goalExtId, mulConditionVO.getRolePath(),
                mulConditionVO.getCardinality());

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/invent";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, mulConditionVO, MulConditionVO.class,
                params);
    }

    public void associateRuleConditionToGoalAttributeInvariantCondition(
            String goalModelExtId, String goalExtId, RuleVO ruleVO) {
        // TODO Auto-generated method stub
        log.debug(
                "associateRuleConditionToGoalAttributeInvariantCondition goalModelExtId:{}, goalExtId:{}, rule:{}",
                goalModelExtId, goalExtId, goalExtId, ruleVO.getName());

        final String uri = SERVER_ADDRESS
                + "/goalmodels/{goalModelExtId}/goals/{goalExtId}/invatt";

        Map<String, String> params = new HashMap<String, String>();
        params.put("goalModelExtId", goalModelExtId);
        params.put("goalExtId", goalExtId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, ruleVO, RuleVO.class, params);
    }

    public ProductVO getSourceOfPath(String specId, String path) {
        log.debug("getSourceOfPath specId:{} path:{}", specId, path);

        final String uri = SERVER_ADDRESS
                + "/specs/{specId}/pathsource/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                params);

        return productVO;
    }

    public ProductVO getTargetOfPath(String specId, String path) {
        log.debug("getTargetOfPath specId:{} path:{}", specId, path);
        final String uri = SERVER_ADDRESS
                + "/specs/{specId}/pathtarget/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                params);

        return productVO;
    }

    public Set<String> getDependencePaths(String specId, Set<String> paths) {
        log.debug("getDependencePaths paths:{}", paths);
        final String uri = SERVER_ADDRESS + "/specs/{specId}/pathdep/{paths}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("paths", paths.stream().collect(Collectors.joining(",")));

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        String[] result = restTemplate.getForObject(uri, String[].class,
                params);

        return Arrays.stream(result).collect(Collectors.toSet());
    }

}
