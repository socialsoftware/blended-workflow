package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Set;
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
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.req.ExtractGoalReq;

@RestController
@RequestMapping(value = "/specs/{specId}/goalmodel")
public class GoalModelController {
	private static Logger logger = LoggerFactory.getLogger(GoalModelController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanGoalModel(@PathVariable("specId") String specId) {
		logger.debug("cleanGoalModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.cleanGoalModel(specId);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateGoalModel(@PathVariable("specId") String specId) {
		logger.debug("generateGoalModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		boolean result = adi.generateGoalModel(specId);

		return new ResponseEntity<Boolean>(result, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/{newName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Void> updateGoalName(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @PathVariable("newName") String newName) {
		logger.debug("updateGoalName specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		adi.updateGoalName(specId, goalName, newName);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/goals", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GoalDTO[]> getGoalSet(@PathVariable("specId") String specId) {
		logger.debug("getGoalSet specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Goal> goals = adi.getGoals(specId);

		return new ResponseEntity<GoalDTO[]>(goals.stream().map(g -> g.getDTO()).toArray(GoalDTO[]::new),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GoalDTO> getGoalByName(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalByName specId:{}, name:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.getGoalByName(specId, goalName);

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/goals", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<GoalDTO> createGoal(@PathVariable("specId") String specId, @RequestBody GoalDTO goalDTO) {
		logger.debug("createGoal specId:{}, name:{}, exists:{}", goalDTO.getSpecId(), goalDTO.getName());

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.createGoal(goalDTO);

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GoalDTO[]> getSubGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getSubGoal specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		GoalDTO[] subGoals = adi.getSubGoals(specId, goalName).stream().map((goal) -> goal.getDTO())
				.toArray(size -> new GoalDTO[size]);

		return new ResponseEntity<GoalDTO[]>(subGoals, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/sup", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GoalDTO> getParentGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getParentGoal specId:{}, goalName:{}", specId, goalName);

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
	public ResponseEntity<GoalDTO> addSubGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @RequestBody GoalDTO goalDTO) {
		logger.debug("createGoal specId:{}, parentName:{}, childName:{}", specId, goalName, goalDTO.getName());

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.addSubGoal(specId, goalName, goalDTO.getName());

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/sucent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefEntityConditionDTO[]> getGoalSucEntityAchieveConditions(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName) {
		logger.debug("getGoalSucEntityAchieveConditions specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		DefEntityConditionDTO[] defs = adi.getGoalSuccessEntitySet(specId, goalName).stream().map((def) -> def.getDTO())
				.toArray(size -> new DefEntityConditionDTO[size]);

		return new ResponseEntity<DefEntityConditionDTO[]>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/sucent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToGoalSuc(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName,
			@PathVariable("path") String path) {
		logger.debug("associateSucConditionToGoal specId:{}, goalName:{}, path:{}", specId, goalName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefEntityCondition defEntityCondition = adi.associateEntityToGoalSuccess(specId, goalName, path);

		return new ResponseEntity<DefEntityConditionDTO>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/sucatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefAttributeConditionDTO[]> getGoalSucAttributeAchieveConditions(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName) {
		logger.debug("getGoalSucAttributeAchieveConditions specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		DefAttributeConditionDTO[] defs = adi.getGoalSuccessAttributeSet(specId, goalName).stream()
				.map((def) -> def.getDTO()).toArray(size -> new DefAttributeConditionDTO[size]);

		return new ResponseEntity<DefAttributeConditionDTO[]>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalExtId}/sucatt/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> associateAttributeAchieveConditionToGoalSuc(@PathVariable("specId") String specId,
			@PathVariable("goalExtId") String goalExtId, @PathVariable("path") String path) {
		logger.debug("associateActConditionToGoal specId:{}, goalExtId:{}, path:{}", specId, goalExtId, path);

		DesignInterface adi = DesignInterface.getInstance();

		adi.associateAttributeToGoalSuccess(specId, goalExtId, path);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/act", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefPathConditionDTO[]> getGoalActDefPathConditionSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalActDefPathConditionSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		DefPathConditionDTO[] paths = adi.getGoalActivationDefPathConditionSet(specId, goalName).stream()
				.map((def) -> def.getDTO(specId)).toArray(size -> new DefPathConditionDTO[size]);

		return new ResponseEntity<DefPathConditionDTO[]>(paths, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/act/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefPathConditionDTO> associateDefPathConditionToGoalAct(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @PathVariable("path") String path) {
		logger.debug("associateDefPathConditionToGoalAct specId:{}, goalName:{}, path:{}", specId, goalName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefPathCondition defPathCondition = adi.associateDefPathConditionToGoalActivation(specId, goalName, path);

		return new ResponseEntity<DefPathConditionDTO>(defPathCondition.getDTO(specId), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<MulConditionDTO[]> getGoalMulInvSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalMulInvSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		MulConditionDTO[] defs = adi.getGoalMulInvSet(specId, goalName).stream().map((def) -> def.getDTO())
				.toArray(size -> new MulConditionDTO[size]);

		return new ResponseEntity<MulConditionDTO[]>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/invent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> associateEntityInvariantConditionToGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @RequestBody MulConditionDTO mulConditionDTO) {
		logger.debug("associateActConditionToGoal specId:{}, goalName:{}, path:{}, cardinality:{}", specId, goalName,
				mulConditionDTO.getRolePath(), mulConditionDTO.getTargetCardinality());

		DesignInterface adi = DesignInterface.getInstance();

		adi.associateMulToGoalInvariant(specId, goalName, mulConditionDTO.getRolePath(),
				mulConditionDTO.getTargetCardinality());

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<RuleDTO[]> getGoalRuleInvSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalRuleInvSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = DesignInterface.getInstance();

		RuleDTO[] defs = adi.getGoalRuleInvSet(specId, goalName).stream().map((def) -> def.getDTO())
				.toArray(size -> new RuleDTO[size]);

		return new ResponseEntity<RuleDTO[]>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/invatt", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> associateAttributeInvariantConditionToGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @RequestBody RuleDTO ruleDTO) {
		logger.debug("associateActConditionToGoal specId:{}, goalName:{}, rule:{}", specId, goalName,
				ruleDTO.getName());

		DesignInterface adi = DesignInterface.getInstance();

		adi.associateRuleToGoalInvariant(ruleDTO, goalName);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/merge", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<GoalDTO> mergeGoals(@PathVariable("specId") String specId,
			@PathParam("newGoalName") String newGoalName, @PathParam("goalNameOne") String goalNameOne,
			@PathParam("goalNameTwo") String goalNameTwo) {
		logger.debug("mergeGoals specId:{}, newGoalName:{}, goalNameOne:{}, goalNameTwo:{}", specId, newGoalName,
				goalNameOne, goalNameTwo);

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.mergeGoals(specId, newGoalName, goalNameOne, goalNameTwo);

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/extractchild", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<GoalDTO> extractChildGoal(@PathVariable("specId") String specId,
			@RequestBody ExtractGoalReq req) {
		logger.debug("extractChildGoal specId:{}, newGoalName:{}, sourceGoalName:{}, defs:{}", specId,
				req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions().stream().map((def) -> def.getPath()).collect(Collectors.joining("|")));

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.extractChildGoal(specId, req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions());

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/extractparent", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<GoalDTO> extractParentGoal(@PathVariable("specId") String specId,
			@RequestBody ExtractGoalReq req) {
		logger.debug("extractParentGoal specId:{}, newGoalName:{}, sourceGoalName:{}, defs:{}", specId,
				req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions().stream().map((def) -> def.getPath()).collect(Collectors.joining("|")));

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.extractParentGoal(specId, req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions());

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/extractsibling", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<GoalDTO> extractSiblingGoal(@PathVariable("specId") String specId,
			@RequestBody ExtractGoalReq req) {
		logger.debug("extractSiblingGoal specId:{}, newGoalName:{}, sourceGoalName:{}, defs:{}", specId,
				req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions().stream().map((def) -> def.getPath()).collect(Collectors.joining("|")));

		DesignInterface adi = DesignInterface.getInstance();

		Goal goal = adi.extractSiblingGoal(specId, req.getNewGoalName(), req.getSourceGoalName(),
				req.getSuccessConditions());

		return new ResponseEntity<GoalDTO>(goal.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/graph", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GraphDTO> getGoalGraph(@PathVariable("specId") String specId) {
		logger.debug("getGoalGraph specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		GraphDTO graph = adi.getGoalModelGraph(specId);

		return new ResponseEntity<GraphDTO>(graph, HttpStatus.OK);
	}

}
