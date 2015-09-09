package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DEFEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/goalmodel")
public class GoalModelController {
    private static Logger log = LoggerFactory
            .getLogger(GoalModelController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanGoalModel(
            @PathVariable("specId") String specId) {
        log.debug("cleanGoalModel specId:{}", specId);

        DesignInterface adi = DesignInterface.getInstance();

        adi.cleanGoalModel(specId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/goals", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> createGoal(
            @PathVariable("specId") String specId,
            @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal specId:{}, name:{}, exists:{}",
                goalDTO.getSpecId(), goalDTO.getName());

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.createGoal(goalDTO);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO> getGoalByName(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("createGoal specId:{}, name:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.getGoalByName(specId, goalName);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> addSubGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName,
            @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal specId:{}, parentName:{}, childName:{}", specId,
                goalName, goalDTO.getName());

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.addSubGoal(specId, goalName, goalDTO.getName());

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/sucent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> associateEntityAchieveConditionToGoalSuc(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName,
            @PathVariable("path") String path) {
        log.debug("associateSucConditionToGoal specId:{}, goalName:{}, path:{}",
                specId, goalName, path);

        DesignInterface adi = DesignInterface.getInstance();

        DEFEntityCondition defEntityCondition = adi
                .associateEntityAchieveConditionToGoalSuccessCondition(specId,
                        goalName, path);

        return new ResponseEntity<DEFEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/actent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> associateEntityAchieveConditionToGoalAct(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("path") String path) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, path:{}",
                specId, goalExtId, path);

        DesignInterface adi = DesignInterface.getInstance();

        DEFEntityCondition defEntityCondition = adi
                .associateEntityAchieveConditionToGoalAtivationCondition(specId,
                        goalExtId, path);

        return new ResponseEntity<DEFEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/sucatt/{paths}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeAchieveConditionToGoalSuc(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("paths") String paths) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, paths:{}",
                specId, goalExtId, paths);

        String[] arraysPath = paths.split(",");

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateAttributeAchieveConditionToGoalSuccessCondition(specId,
                goalExtId,
                Arrays.asList(arraysPath).stream().collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/actatt/{paths}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeAchieveConditionToGoalAct(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("paths") String paths) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, paths:{}",
                specId, goalExtId, paths);

        String[] arraysPath = paths.split(",");

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateAttributeAchieveConditionToGoalActivationCondition(specId,
                goalExtId,
                Arrays.stream(arraysPath).collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateEntityInvariantConditionToGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @RequestBody MulConditionDTO mulConditionDTO) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, path:{}, cardinality:{}",
                specId, goalExtId, mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateMulConditionToGoalEntityInvariantCondition(specId,
                goalExtId, mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeInvariantConditionToGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @RequestBody RuleDTO ruleDTO) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, rule:{}",
                specId, goalExtId, ruleDTO.getName());

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateRuleConditionToGoalAttributeInvariantCondition(specId,
                goalExtId, ruleDTO.getName());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/merge", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> mergeGoals(
            @PathVariable("specId") String specId,
            @PathParam("goalNameOne") String goalNameOne,
            @PathParam("goalNameTwo") String goalNameTwo) {
        log.debug("mergeGoals specId:{}, goalNameOne:{}, goalNameTwo:{}",
                specId, goalNameOne, goalNameTwo);

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.mergeGoals(specId, goalNameOne, goalNameTwo);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

}
