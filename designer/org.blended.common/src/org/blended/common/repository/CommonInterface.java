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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonInterface {
    private static Logger log = LoggerFactory.getLogger(CommonInterface.class);

    final static String BASE_URL = "http://localhost:8080";

    private static CommonInterface instance = null;

    public static CommonInterface getInstance() {
        if (instance == null) {
            instance = new CommonInterface();
        }
        return instance;
    }

    public void deleteSpecification(String specId) {
        log.debug("deleteSpecification: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.delete(uri, params);

    }

    public SpecVO getSpecBySpecId(String specId) {
        log.debug("getSpecBySpecId: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}";

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

        final String uri = BASE_URL + "/specs";

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

    public void cleanDataModel(String specId) {
        log.debug("cleanDataModel: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}/datamodel";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public EntityVO createEntity(EntityVO entityVO) {
        log.debug("createEntity: {}, {}, {}", entityVO.getSpecId(),
                entityVO.getName(), entityVO.getExists());

        final String uri = BASE_URL + "/specs/{specId}/datamodel/entities";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", entityVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        EntityVO result = restTemplate.postForObject(uri, entityVO,
                EntityVO.class, params);

        return result;
    }

    public DependenceVO createDependence(DependenceVO dependenceVO) {
        log.debug("createDependence: {}, {}", dependenceVO.getProductExtId(),
                dependenceVO.getPath());

        final String uri = BASE_URL + "/specs/{specId}/datamodel/dependencies";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", dependenceVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class, params);

        return result;
    }

    public Set<DependenceVO> getDependencies(String specId) {
        log.debug("getDependencies: {}", specId);

        final String uri = BASE_URL + "specs/{specId}/datamodel/dependencies";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO[] result = restTemplate.getForObject(uri,
                DependenceVO[].class, params);

        return new HashSet<DependenceVO>(Arrays.asList(result));
    }

    public boolean checkDependence(String specId, String extId) {
        log.debug("checkDependence: {}", extId);

        final String uri = BASE_URL
                + "/specs/{specId}/datamodel/dependencies/{depExtId}/check";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("depExtId", extId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        Boolean response = restTemplate.getForObject(uri, Boolean.class,
                params);

        return response;
    }

    public void deleteDependence(String specId, String extId) {
        log.debug("deleteDependence: {}", extId);

        final String uri = BASE_URL
                + "/specs/{specId}/datamodel/dependencies/{depExtId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("depExtId", extId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.delete(uri, params);
    }

    public AttributeVO getAttribute(String specId, String extId) {
        log.debug("getAttribute specId:{} extId:{}", specId, extId);

        final String uri = BASE_URL
                + "specs/{specId}/datamodel/attributes/{extId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("extId", extId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        AttributeVO result = restTemplate.getForObject(uri, AttributeVO.class,
                params);

        return result;
    }

    public AttributeVO createAttribute(AttributeVO attribueVO) {
        log.debug(
                "createAttribute: entitityExtId:{}, groupExtId:{}, {}, {}, {}",
                attribueVO.getEntityExtId(), attribueVO.getGroupExtId(),
                attribueVO.getName(), attribueVO.getType(),
                attribueVO.getIsMandatory());

        final String uri = BASE_URL + "/specs/{specId}/datamodel/attributes";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", attribueVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        AttributeVO result = restTemplate.postForObject(uri, attribueVO,
                AttributeVO.class, params);

        return result;
    }

    public AttributeGroupVO createAttributeGroup(
            AttributeGroupVO attributeGroupVO) {
        log.debug("createAttributeGroup: entitityExtId:{},  {}, {}",
                attributeGroupVO.getEntityExtId(), attributeGroupVO.getName(),
                attributeGroupVO.isMandatory());

        final String uri = BASE_URL
                + "/specs/{specId}/datamodel/attributegroups";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", attributeGroupVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        AttributeGroupVO result = restTemplate.postForObject(uri,
                attributeGroupVO, AttributeGroupVO.class, params);

        return result;
    }

    public RelationVO createRelation(RelationVO relationVO) {
        log.debug("createRelation: {}, {}, {}", relationVO.getName(),
                relationVO.getEntOneName(), relationVO.getEntTwoName());

        final String uri = BASE_URL + "/specs/{specId}/datamodel/relations";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", relationVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RelationVO result = restTemplate.postForObject(uri, relationVO,
                RelationVO.class, params);

        return result;
    }

    public RuleVO createRule(RuleVO ruleVO) {
        log.debug("createRule: {}, {}, {}", ruleVO.getSpecId(),
                ruleVO.getName(), ruleVO.getExpression());

        final String uri = BASE_URL + "/specs/{specId}/datamodel/rules";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", ruleVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RuleVO result = restTemplate.postForObject(uri, ruleVO, RuleVO.class,
                params);

        return result;
    }

    public void printSpecificationModels(String specId) {
        log.debug("printSpecificationModels: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}/print";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.getForObject(uri, Boolean.class, params);
    }

    public void cleanConditionModel(String specId) {
        log.debug("cleanConditionModel: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}/conditionmodel";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public DEFEntityConditionVO createEntityAchieveCondition(
            DEFEntityConditionVO entityAchieveConditionVO) {
        log.debug("createEntityAchieveCondition: {}, {}, {}",
                entityAchieveConditionVO.getSpecId(),
                entityAchieveConditionVO.getEntityName(),
                entityAchieveConditionVO.isExists());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/entityachieveconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", entityAchieveConditionVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri,
                entityAchieveConditionVO, DEFEntityConditionVO.class, params);

        return result;
    }

    public DependenceVO createEntityDependenceCondition(
            DependenceVO dependenceVO) {
        log.debug("createEntityDependenceCondition entityExtId:{}, path:{}",
                dependenceVO.getProductExtId(), dependenceVO.getPath());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/entitydependenceconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", dependenceVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class, params);

        return result;
    }

    public MulConditionVO createEntityInvariantCondition(
            MulConditionVO mulConditionVO) {
        log.debug("createEntityInvariantCondition rolePath:{}, cardinality:{}",
                mulConditionVO.getRolePath(), mulConditionVO.getCardinality());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/entityinvariantconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", mulConditionVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        MulConditionVO result = restTemplate.postForObject(uri, mulConditionVO,
                MulConditionVO.class, params);

        return result;
    }

    public DefAttributeConditionVO createAttributeAchieveCondition(
            DefAttributeConditionVO defAttributeConditionVO) {
        log.debug("createAttributeAchieveCondition paths:{}, mandatory:{}",
                defAttributeConditionVO.getPaths().toString(),
                defAttributeConditionVO.isMandatory());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/attributeachieveconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", defAttributeConditionVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DefAttributeConditionVO result = restTemplate.postForObject(uri,
                defAttributeConditionVO, DefAttributeConditionVO.class, params);

        return result;
    }

    public DependenceVO createAttributeDependenceCondition(
            DependenceVO dependenceVO) {
        log.debug("createDependenceCondition productExtId:{}, path:{}",
                dependenceVO.getProductExtId(), dependenceVO.getPath());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/attributedependenceconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", dependenceVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DependenceVO result = restTemplate.postForObject(uri, dependenceVO,
                DependenceVO.class, params);

        return result;
    }

    public RuleVO createAttributeInvariantCondition(RuleVO ruleVO) {
        log.debug(
                "createAttributeInvariantCondition conditionModelExtId:{}, name:{}",
                ruleVO.getSpecId(), ruleVO.getName());

        final String uri = BASE_URL
                + "/specs/{specId}/conditionmodel/attributeinvariantconditions";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", ruleVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RuleVO result = restTemplate.postForObject(uri, ruleVO, RuleVO.class,
                params);

        return result;
    }

    public ProductVO getProduct(String specId, Set<String> sourceAtts) {
        log.debug("getProduct: {}", sourceAtts.toString());

        final String uri = BASE_URL
                + "/specs/{specId}/datamodel/products/{atts}/";

        String atts = sourceAtts.stream().collect(Collectors.joining(","));

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("atts", atts);

        log.debug("getProduct: {}", atts);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                params);

        return productVO;
    }

    public EntityVO getEntityByName(String specId, String entityName) {
        log.debug("getEntityByName: {}, {}", specId, entityName);

        final String uri = BASE_URL
                + "/specs/{specId}/datamodel/entities/{entityName}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("entityName", entityName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        EntityVO entityVO = restTemplate.getForObject(uri, EntityVO.class,
                params);

        return entityVO;
    }

    public void cleanGoalModel(String specId) {
        log.debug("cleanGoalModel: {}", specId);

        final String uri = BASE_URL + "/specs/{specId}/goalmodel";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.put(uri, null, params);
    }

    public GoalVO getGoalByName(String specId, String goalName) {
        log.debug("getGoalByName: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO goalVO = restTemplate.getForObject(uri, GoalVO.class, params);

        return goalVO;
    }

    public GoalVO createGoal(GoalVO goalVO) {
        log.debug("createGoal specId:{}, name:{}", goalVO.getSpecId(),
                goalVO.getName());

        final String uri = BASE_URL + "/specs/{specId}/goalmodel/goals";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", goalVO.getSpecId());

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO result = restTemplate.postForObject(uri, goalVO, GoalVO.class,
                params);

        return result;
    }

    public Set<GoalVO> getSubGoals(String specId, String goalName) {
        log.debug("getsubGoals: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sub";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO[] goalsVO = restTemplate.getForObject(uri, GoalVO[].class,
                params);

        return new HashSet<GoalVO>(Arrays.asList(goalsVO));
    }

    public GoalVO getParentGoal(String specId, String goalName) {
        log.debug("getParentGoal: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sup";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO goalVO = restTemplate.getForObject(uri, GoalVO.class, params);

        return goalVO;
    }

    public GoalVO addSubGoal(String parentName, GoalVO goalVO) {
        log.debug("addSubGoal specId:{}, parentName:{}, childName:{}",
                goalVO.getSpecId(), parentName, goalVO.getName());

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sub";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", goalVO.getSpecId());
        params.put("goalName", parentName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        GoalVO result = restTemplate.postForObject(uri, goalVO, GoalVO.class,
                params);

        return result;
    }

    public Set<DEFEntityConditionVO> getGoalSuccessEntitySet(String specId,
            String goalName) {
        log.debug("getGoalSucEntityAchieveCondition: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sucent";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO[] defsVO = restTemplate.getForObject(uri,
                DEFEntityConditionVO[].class, params);

        return new HashSet<DEFEntityConditionVO>(Arrays.asList(defsVO));
    }

    public DEFEntityConditionVO associateEntityToGoalSuccess(String specId,
            String goalName, String path) {
        log.debug(
                "associatedSucConditionToGoal specId:{}, goalName:{}, path:{}",
                specId, goalName, path);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sucent/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri, null,
                DEFEntityConditionVO.class, params);

        return result;
    }

    public Set<DefAttributeConditionVO> getGoalSuccessAttributeSet(
            String specId, String goalName) {
        log.debug("getGoalSuccessAttributeSet: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sucatt";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DefAttributeConditionVO[] defsVO = restTemplate.getForObject(uri,
                DefAttributeConditionVO[].class, params);

        return new HashSet<DefAttributeConditionVO>(Arrays.asList(defsVO));
    }

    public void associateAttributeToGoalSuccess(String specId, String goalName,
            Set<String> paths) {
        log.debug(
                "associateAttributeAchieveConditionToGoalActCondition specId:{}, goalName:{}, paths:{}",
                specId, goalName, paths);

        String pathsParam = paths.stream().collect(Collectors.joining(","));

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/sucatt/{paths}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);
        params.put("paths", pathsParam);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, null, String.class, params);
    }

    public Set<DEFEntityConditionVO> getGoalActivationEntitySet(String specId,
            String goalName) {
        log.debug("getGoalActivationEntitySet: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/actent";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO[] defsVO = restTemplate.getForObject(uri,
                DEFEntityConditionVO[].class, params);

        return new HashSet<DEFEntityConditionVO>(Arrays.asList(defsVO));
    }

    public DEFEntityConditionVO associateEntityToGoalActivation(String specId,
            String goalName, String path) {
        log.debug(
                "associatedActConditionToGoal specId:{}, goalName:{}, path:{}",
                specId, goalName, path);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/actent/{path}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);
        params.put("path", path);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DEFEntityConditionVO result = restTemplate.postForObject(uri, null,
                DEFEntityConditionVO.class, params);

        return result;
    }

    public Set<DefAttributeConditionVO> getGoalActivationAttributeSet(
            String specId, String goalName) {
        log.debug("getGoalActivationAttributeSet: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/actatt";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        DefAttributeConditionVO[] defsVO = restTemplate.getForObject(uri,
                DefAttributeConditionVO[].class, params);

        return new HashSet<DefAttributeConditionVO>(Arrays.asList(defsVO));
    }

    public void associateAttributeToGoalActivation(String specId,
            String goalName, Set<String> paths) {
        log.debug(
                "associateAttributeAchieveConditionToGoalActCondition specId:{}, goalName:{}, paths:{}",
                specId, goalName, paths);

        String pathsParam = paths.stream().collect(Collectors.joining(","));

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/actatt/{paths}/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);
        params.put("paths", pathsParam);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, null, String.class, params);
    }

    public Set<MulConditionVO> getGoalMulInvSet(String specId,
            String goalName) {
        log.debug("getGoalMulInvSet: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/invent";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        MulConditionVO[] mulsVO = restTemplate.getForObject(uri,
                MulConditionVO[].class, params);

        return new HashSet<MulConditionVO>(Arrays.asList(mulsVO));
    }

    public void associateMulToGoalInvariant(String specId, String goalName,
            MulConditionVO mulConditionVO) {
        log.debug(
                "associateMulConditionToGoalEntityInvariantCondition specId:{}, goalName:{}, path:{}, cardinality:{}",
                specId, goalName, mulConditionVO.getRolePath(),
                mulConditionVO.getCardinality());

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/invent";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, mulConditionVO, MulConditionVO.class,
                params);
    }

    public Set<RuleVO> getGoalRuleInvSet(String specId, String goalName) {
        log.debug("getGoalRuleInvSet: {}, {}", specId, goalName);

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/invatt";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        RuleVO[] rulesVO = restTemplate.getForObject(uri, RuleVO[].class,
                params);

        return new HashSet<RuleVO>(Arrays.asList(rulesVO));
    }

    public void associateRuleToGoalInvariant(String specId, String goalName,
            RuleVO ruleVO) {
        log.debug(
                "associateRuleConditionToGoalAttributeInvariantCondition specId:{}, goalName:{}, rule:{}",
                specId, goalName, ruleVO.getName());

        final String uri = BASE_URL
                + "/specs/{specId}/goalmodel/goals/{goalName}/invatt";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);
        params.put("goalName", goalName);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        restTemplate.postForObject(uri, ruleVO, RuleVO.class, params);
    }

    public GoalVO mergeGoals(String specId, String goalNameOne,
            String goalNameTwo) {
        log.debug("mergeGoals specId:{}, goalNameOne:{}, goalNameTwo:{}",
                specId, goalNameOne, goalNameTwo);

        final String uri = BASE_URL + "/specs/{specId}/goalmodel/goals/merge";

        Map<String, String> params = new HashMap<String, String>();
        params.put("specId", specId);

        MultiValueMap<String, Object> variablesMap = new LinkedMultiValueMap<String, Object>();
        variablesMap.add("goalNameOne", goalNameOne);
        variablesMap.add("goalNameTwo", goalNameTwo);

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        return restTemplate.postForObject(uri, variablesMap, GoalVO.class,
                params);
    }

    public ProductVO getSourceOfPath(String specId, String path) {
        log.debug("getSourceOfPath specId:{} path:{}", specId, path);

        final String uri = BASE_URL + "/specs/{specId}/pathsource?path={path}";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                specId, path);

        return productVO;
    }

    public ProductVO getTargetOfPath(String specId, String path) {
        log.debug("getTargetOfPath specId:{} path:{}", specId, path);

        final String uri = BASE_URL + "/specs/{specId}/pathtarget?path={path}";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        ProductVO productVO = restTemplate.getForObject(uri, ProductVO.class,
                specId, path);

        return productVO;
    }

    public Set<String> getDependencePaths(String specId, Set<String> paths) {
        log.debug("getDependencePaths paths:{}", paths);

        final String uri = BASE_URL + "/specs/{specId}/pathdep?paths={paths}";

        RestTemplate restTemplate = RestUtil.getRestTemplate();
        String[] result = restTemplate.getForObject(uri, String[].class, specId,
                paths.stream().collect(Collectors.joining(",")));

        return Arrays.stream(result).collect(Collectors.toSet());
    }

}
