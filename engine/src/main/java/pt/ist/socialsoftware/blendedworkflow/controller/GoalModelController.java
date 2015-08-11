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
@RequestMapping(value = "/specs/{specId}/goalmodel")
public class GoalModelController {
    private static Logger log = LoggerFactory
            .getLogger(GoalModelController.class);

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> cleanGoalModel(
            @PathVariable("specId") String specId) {
        log.debug("cleanGoalModel specId:{}", specId);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.cleanGoalModel(specId);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/goals", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> createGoal(
            @PathVariable("specId") String specId,
            @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal specId:{}, name:{}, exists:{}",
                goalDTO.getSpecId(), goalDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.createGoal(goalDTO);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO> getGoalByName(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("createGoal specId:{}, name:{}", specId, goalName);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.getGoalByName(specId, goalName);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{extId}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> addSubGoal(
            @PathVariable("specId") String specId,
            @PathVariable("extId") String extId, @RequestBody GoalDTO goalDTO) {
        log.debug("createGoal specId:{}, extId:{}, name:{}",
                goalDTO.getSpecId(), goalDTO.getExtId(), goalDTO.getName());

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        Goal goal = adi.addSubGoal(goalDTO.getSpecId(), extId,
                goalDTO.getName());

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalExtId}/sucent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DEFEntityConditionDTO> associateEntityAchieveConditionToGoalSuc(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("path") String path) {
        log.debug(
                "associateSucConditionToGoal specId:{}, goalExtId:{}, path:{}",
                specId, goalExtId, path);

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        DEFEntityCondition defEntityCondition = adi
                .associateEntityAchieveConditionToGoalSuccessCondition(specId,
                        goalExtId, path);

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

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

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

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

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

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

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

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

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

        AtomicDesignInterface adi = AtomicDesignInterface.getInstance();

        adi.associateRuleConditionToGoalAttributeInvariantCondition(specId,
                goalExtId, ruleDTO.getName());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

}
