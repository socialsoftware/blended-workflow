package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DEFEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

@Controller
@RequestMapping(value = "/goalmodels")
public class GoalModelController {
    private static Logger log = LoggerFactory
            .getLogger(GoalModelController.class);

    @RequestMapping(value = "/{extId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanGoalModel(
            @PathVariable("extId") String extId) {
        log.debug("cleanGoalModel extId:{}", extId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.cleanGoalModel(extId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> createGoal(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal goalModelExtId:{}, name:{}, exists:{}",
                goalDTO.getGoalModelExtId(), goalDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.createGoal(goalDTO);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO> getGoalByName(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalName") String goalName) {
        log.debug("createGoal goalModelExtId:{}, name:{}", goalModelExtId,
                goalName);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.getGoalByName(goalModelExtId, goalName);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{extId}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> addSubGoal(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("extId") String extId, @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal goalModelExtId:{}, extId:{}, name:{}",
                goalDTO.getGoalModelExtId(), goalDTO.getExtId(),
                goalDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.addSubGoal(goalModelExtId, extId, goalDTO.getName());

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/sucent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> associateEntityAchieveConditionToGoalSuc(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("path") String path) {
        log.debug(
                "associateSucConditionToGoal goalModelExtId:{}, goalExtId:{}, path:{}",
                goalModelExtId, goalExtId, path);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        DEFEntityCondition defEntityCondition = adi
                .associateEntityAchieveConditionToGoalSuccessCondition(
                        goalModelExtId, goalExtId, path);

        return new ResponseEntity<DEFEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/actent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> associateEntityAchieveConditionToGoalAct(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("path") String path) {
        log.debug(
                "associateActConditionToGoal goalModelExtId:{}, goalExtId:{}, path:{}",
                goalModelExtId, goalExtId, path);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        DEFEntityCondition defEntityCondition = adi
                .associateEntityAchieveConditionToGoalAtivationCondition(
                        goalModelExtId, goalExtId, path);

        return new ResponseEntity<DEFEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/sucatt/{paths}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeAchieveConditionToGoalSuc(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("paths") String paths) {
        log.debug(
                "associateActConditionToGoal goalModelExtId:{}, goalExtId:{}, paths:{}",
                goalModelExtId, goalExtId, paths);

        String[] arraysPath = paths.split(",");

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.associateAttributeAchieveConditionToGoalSuccessCondition(
                goalModelExtId, goalExtId,
                Arrays.asList(arraysPath).stream().collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/actatt/{paths}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeAchieveConditionToGoalAct(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("paths") String paths) {
        log.debug(
                "associateActConditionToGoal goalModelExtId:{}, goalExtId:{}, paths:{}",
                goalModelExtId, goalExtId, paths);

        String[] arraysPath = paths.split(",");

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.associateAttributeAchieveConditionToGoalActivationCondition(
                goalModelExtId, goalExtId,
                Arrays.stream(arraysPath).collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateEntityInvariantConditionToGoal(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @RequestBody MulConditionDTO mulConditionDTO) {
        log.debug(
                "associateActConditionToGoal goalModelExtId:{}, goalExtId:{}, path:{}, cardinality:{}",
                goalModelExtId, goalExtId, mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.associateMulConditionToGoalEntityInvariantCondition(goalModelExtId,
                goalExtId, mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{goalModelExtId}/goals/{goalExtId}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeInvariantConditionToGoal(
            @PathVariable("goalModelExtId") String goalModelExtId,
            @PathVariable("goalExtId") String goalExtId,
            @RequestBody RuleDTO ruleDTO) {
        log.debug(
                "associateActConditionToGoal goalModelExtId:{}, goalExtId:{}, rule:{}",
                goalModelExtId, goalExtId, ruleDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.associateRuleConditionToGoalAttributeInvariantCondition(
                goalModelExtId, goalExtId, ruleDTO.getName());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

}
