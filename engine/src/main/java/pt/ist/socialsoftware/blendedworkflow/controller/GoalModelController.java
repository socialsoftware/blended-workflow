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

import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.req.ExtractGoalReq;

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

    @RequestMapping(value = "/goals/{goalName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO> getGoalByName(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("createGoal specId:{}, name:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.getGoalByName(specId, goalName);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.OK);
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

    @RequestMapping(value = "/goals/{goalName}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO[]> getSubGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getSubGoal specId:{}, goalName:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        GoalDTO[] subGoals = adi.getSubGoals(specId, goalName).stream()
                .map((goal) -> goal.getDTO())
                .toArray(size -> new GoalDTO[size]);

        return new ResponseEntity<GoalDTO[]>(subGoals, HttpStatus.OK);
    }

    @RequestMapping(value = "/goals/{goalName}/sup", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<GoalDTO> getParentGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getParentGoal specId:{}, goalName:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        Goal parentGoal = adi.getParentGoal(specId, goalName);
        GoalDTO parentGoalDTO;
        if (parentGoal == null)
            parentGoalDTO = new GoalDTO(specId);
        else
            parentGoalDTO = parentGoal.getDTO();

        return new ResponseEntity<GoalDTO>(parentGoalDTO, HttpStatus.OK);
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

    @RequestMapping(value = "/goals/{goalName}/sucent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DefEntityConditionDTO[]> getGoalSucEntityAchieveConditions(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalSucEntityAchieveConditions specId:{}, goalName:{}",
                specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        DefEntityConditionDTO[] defs = adi
                .getGoalSuccessEntitySet(specId, goalName).stream()
                .map((def) -> def.getDTO())
                .toArray(size -> new DefEntityConditionDTO[size]);

        return new ResponseEntity<DefEntityConditionDTO[]>(defs, HttpStatus.OK);
    }

    @RequestMapping(value = "/goals/{goalName}/sucent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToGoalSuc(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName,
            @PathVariable("path") String path) {
        log.debug("associateSucConditionToGoal specId:{}, goalName:{}, path:{}",
                specId, goalName, path);

        DesignInterface adi = DesignInterface.getInstance();

        DefEntityCondition defEntityCondition = adi
                .associateEntityToGoalSuccess(specId, goalName, path);

        return new ResponseEntity<DefEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/sucatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DefAttributeConditionDTO[]> getGoalSucAttributeAchieveConditions(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalSucAttributeAchieveConditions specId:{}, goalName:{}",
                specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        DefAttributeConditionDTO[] defs = adi
                .getGoalSuccessAttributeSet(specId, goalName).stream()
                .map((def) -> def.getDTO())
                .toArray(size -> new DefAttributeConditionDTO[size]);

        return new ResponseEntity<DefAttributeConditionDTO[]>(defs,
                HttpStatus.OK);
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

        adi.associateAttributeToGoalSuccess(specId, goalExtId,
                Arrays.asList(arraysPath).stream().collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/actent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DefEntityConditionDTO[]> getGoalActEntityAchieveConditions(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalActEntityAchieveConditions specId:{}, goalName:{}",
                specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        DefEntityConditionDTO[] defs = adi
                .getGoalActivationEntitySet(specId, goalName).stream()
                .map((def) -> def.getDTO())
                .toArray(size -> new DefEntityConditionDTO[size]);

        return new ResponseEntity<DefEntityConditionDTO[]>(defs, HttpStatus.OK);
    }

    @RequestMapping(value = "/goals/{goalExtId}/actent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToGoalAct(
            @PathVariable("specId") String specId,
            @PathVariable("goalExtId") String goalExtId,
            @PathVariable("path") String path) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalExtId:{}, path:{}",
                specId, goalExtId, path);

        DesignInterface adi = DesignInterface.getInstance();

        DefEntityCondition defEntityCondition = adi
                .associateEntityToGoalAtivation(specId, goalExtId, path);

        return new ResponseEntity<DefEntityConditionDTO>(
                defEntityCondition.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/actatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<DefAttributeConditionDTO[]> getGoalActAttributeAchieveConditions(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalActAttributeAchieveConditions specId:{}, goalName:{}",
                specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        DefAttributeConditionDTO[] defs = adi
                .getGoalActivationAttributeSet(specId, goalName).stream()
                .map((def) -> def.getDTO())
                .toArray(size -> new DefAttributeConditionDTO[size]);

        return new ResponseEntity<DefAttributeConditionDTO[]>(defs,
                HttpStatus.OK);
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

        adi.associateAttributeToGoalActivation(specId, goalExtId,
                Arrays.stream(arraysPath).collect(Collectors.toSet()));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<MulConditionDTO[]> getGoalMulInvSet(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalMulInvSet specId:{}, goalName:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        MulConditionDTO[] defs = adi.getGoalMulInvSet(specId, goalName).stream()
                .map((def) -> def.getDTO())
                .toArray(size -> new MulConditionDTO[size]);

        return new ResponseEntity<MulConditionDTO[]>(defs, HttpStatus.OK);
    }

    @RequestMapping(value = "/goals/{goalName}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateEntityInvariantConditionToGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName,
            @RequestBody MulConditionDTO mulConditionDTO) {
        log.debug(
                "associateActConditionToGoal specId:{}, goalName:{}, path:{}, cardinality:{}",
                specId, goalName, mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateMulToGoalInvariant(specId, goalName,
                mulConditionDTO.getRolePath(),
                mulConditionDTO.getCardinality());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/{goalName}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<RuleDTO[]> getGoalRuleInvSet(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName) {
        log.debug("getGoalRuleInvSet specId:{}, goalName:{}", specId, goalName);

        DesignInterface adi = DesignInterface.getInstance();

        RuleDTO[] defs = adi.getGoalRuleInvSet(specId, goalName).stream()
                .map((def) -> def.getDTO()).toArray(size -> new RuleDTO[size]);

        return new ResponseEntity<RuleDTO[]>(defs, HttpStatus.OK);
    }

    @RequestMapping(value = "/goals/{goalName}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> associateAttributeInvariantConditionToGoal(
            @PathVariable("specId") String specId,
            @PathVariable("goalName") String goalName,
            @RequestBody RuleDTO ruleDTO) {
        log.debug("associateActConditionToGoal specId:{}, goalName:{}, rule:{}",
                specId, goalName, ruleDTO.getName());

        DesignInterface adi = DesignInterface.getInstance();

        adi.associateRuleToGoalInvariant(specId, goalName, ruleDTO.getName());

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/merge", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> mergeGoals(
            @PathVariable("specId") String specId,
            @PathParam("newGoalName") String newGoalName,
            @PathParam("goalNameOne") String goalNameOne,
            @PathParam("goalNameTwo") String goalNameTwo) {
        log.debug(
                "mergeGoals specId:{}, newGoalName:{}, goalNameOne:{}, goalNameTwo:{}",
                specId, newGoalName, goalNameOne, goalNameTwo);

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.mergeGoals(specId, newGoalName, goalNameOne,
                goalNameTwo);

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/extractchild", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> extractChildGoal(
            @PathVariable("specId") String specId,
            @RequestBody ExtractGoalReq req) {
        log.debug(
                "extractChildGoal specId:{}, newGoalName:{}, sourceGoalName:{}, defEnts:{}, defAtts:{}",
                specId, req.getNewGoalName(), req.getSourceGoalName(),
                req.getSuccessCondition().getDefEnts().stream()
                        .map((def) -> def.getEntityName()).collect(
                                Collectors.joining(",")),
                req.getSuccessCondition().getDefAtts().stream()
                        .map((def) -> def.getPaths().stream()
                                .collect(Collectors.joining(",")))
                        .collect(Collectors.joining("|")));

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.extractChildGoal(specId, req.getNewGoalName(),
                req.getSourceGoalName(), req.getSuccessCondition());

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/goals/extractsibling", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> extractSiblingGoal(
            @PathVariable("specId") String specId,
            @RequestBody ExtractGoalReq req) {
        log.debug(
                "extractSiblingGoal specId:{}, newGoalName:{}, sourceGoalName:{}, defEnts:{}, defAtts:{}",
                specId, req.getNewGoalName(), req.getSourceGoalName(),
                req.getSuccessCondition().getDefEnts().stream()
                        .map((def) -> def.getEntityName()).collect(
                                Collectors.joining(",")),
                req.getSuccessCondition().getDefAtts().stream()
                        .map((def) -> def.getPaths().stream()
                                .collect(Collectors.joining(",")))
                        .collect(Collectors.joining("|")));

        DesignInterface adi = DesignInterface.getInstance();

        Goal goal = adi.extractSiblingGoal(specId, req.getNewGoalName(),
                req.getSourceGoalName(), req.getSuccessCondition());

        return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
    }

}
