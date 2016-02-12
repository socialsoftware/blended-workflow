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

import pt.ist.socialsoftware.blendedworkflow.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefProductConditionSetDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.req.AddActivityReq;
import pt.ist.socialsoftware.blendedworkflow.service.req.ExtractActivityReq;

@RestController
@RequestMapping(value = "/specs/{specId}/activitymodel")
public class ActivityModelController {
	private static Logger logger = LoggerFactory.getLogger(ActivityModelController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanActivityModel(@PathVariable("specId") String specId) {
		logger.debug("cleanActivityModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.cleanActivityModel(specId);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateActivityModel(@PathVariable("specId") String specId) {
		logger.debug("generateActivityModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		boolean result = adi.generateActivityModel(specId);

		return new ResponseEntity<Boolean>(result, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> createActivity(@PathVariable("specId") String specId,
			@RequestBody ActivityDTO activityDTO) {
		logger.debug("createActivity specId:{}, name:{}, description:{}", specId, activityDTO.getName(),
				activityDTO.getDescription());

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.createActivity(activityDTO);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> addActivity(@PathVariable("specId") String specId,
			@RequestBody AddActivityReq request) {
		logger.debug("addActivity specId:{}, name:{}, description:{}", specId, request.getActivityName(),
				request.getDescription());

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.addActivity(specId, request);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ActivityDTO[]> getActivitySet(@PathVariable("specId") String specId) {
		logger.debug("getActivitySet specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Task> goals = adi.getActivities(specId);

		return new ResponseEntity<ActivityDTO[]>(goals.stream().map(g -> g.getDTO()).toArray(ActivityDTO[]::new),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/pre", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ExpressionDTO[]> getActivityPreCondition(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityPreCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<DefPathCondition> preConditionSet = adi.getActivityPreCondition(specId, activityName);

		return new ResponseEntity<ExpressionDTO[]>(
				preConditionSet.stream().map(d -> d.getDTO(specId)).toArray(ExpressionDTO[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/pre/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ExpressionDTO> associateDefPathToActivityPre(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @PathVariable("path") String path) {
		logger.debug("associateDefPathToActivityPre specId:{}, activityName:{}, path:{}", specId, activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefPathCondition defPathCondition = adi.associateDefPathToActivityPre(specId, activityName, path);

		return new ResponseEntity<ExpressionDTO>(defPathCondition.getDTO(specId), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/post", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefProductConditionSetDTO> getActivityPostCondition(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityPostCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<DefProductCondition> postConditionSet = adi.getActivityPostCondition(specId, activityName);

		ConditionModel conditionModel = adi.getSpecBySpecId(specId).getConditionModel();

		DefProductConditionSetDTO defConditionSetDTO = new DefProductConditionSetDTO();
		defConditionSetDTO.setDefEnts(conditionModel.getDefEntityConditions(postConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefAtts(conditionModel.getDefAttributeConditions(postConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));

		return new ResponseEntity<DefProductConditionSetDTO>(defConditionSetDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		logger.debug("associateEntityAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefEntityCondition defEntityCondition = adi.associateEntityToActivityPost(specId, activityName, path);

		return new ResponseEntity<DefEntityConditionDTO>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postatt/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefAttributeConditionDTO> associateAttributeAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		logger.debug("associateAttributeAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefAttributeCondition defAttributeCondition = adi.associateAttributeToActivityPost(specId, activityName, path);

		return new ResponseEntity<DefAttributeConditionDTO>(defAttributeCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<MulConditionDTO[]> getActivityMultConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityMultConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<MulCondition> mulConditions = adi.getActivityMulConditions(specId, activityName);

		return new ResponseEntity<MulConditionDTO[]>(
				mulConditions.stream().map(mul -> mul.getDTO()).toArray(size -> new MulConditionDTO[size]),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<MulConditionDTO> associateMultiplicityToActivityPost(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @RequestBody MulConditionDTO mulConditionDTO) {
		logger.debug("associateActConditionToGoal specId:{}, activityName:{}, path:{}, cardinality:{}", specId,
				activityName, mulConditionDTO.getRolePath(), mulConditionDTO.getCardinality());

		DesignInterface adi = DesignInterface.getInstance();

		MulCondition mulCondition = adi.associateMulToActivityPost(specId, activityName, mulConditionDTO.getRolePath(),
				mulConditionDTO.getCardinality());

		return new ResponseEntity<MulConditionDTO>(mulCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<RuleDTO[]> getActivityRuleConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityRuleConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Rule> rules = adi.getActivityRuleConditions(specId, activityName);

		logger.debug("getActivityRuleConditions specId:{}, activityName:{}, size:{}", specId, activityName,
				rules.size());

		return new ResponseEntity<RuleDTO[]>(
				rules.stream().map(rule -> rule.getDTO()).toArray(size -> new RuleDTO[size]), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<RuleDTO> associateAttributeInvariantConditionActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@RequestBody RuleDTO ruleDTO) {
		logger.debug("associateActConditionToGoal specId:{}, activityName:{}, rule:{}", specId, activityName,
				ruleDTO.getName());

		DesignInterface adi = DesignInterface.getInstance();

		Rule rule = adi.associateRuleToActivityPost(specId, activityName, ruleDTO.getName());

		return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkActivityModel(@PathVariable("specId") String specId) {
		logger.debug("checkActivityModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		boolean result = adi.checkActivityModel(specId);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/merge", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> mergeActivities(@PathVariable("specId") String specId,
			@PathParam("newActivityName") String newActivityName, @PathParam("activityNameOne") String activityNameOne,
			@PathParam("activityNameTwo") String activityNameTwo) {
		logger.debug("mergeActivities specId:{}, newActivityName:{}, activityNameOne:{}, activityNameTwo:{}", specId,
				newActivityName, activityNameOne, activityNameTwo);

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.mergeActivities(specId, newActivityName, "merged: " + activityNameOne + " " + activityNameTwo,
				activityNameOne, activityNameTwo);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/extract", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> extractActivity(@PathVariable("specId") String specId,
			@RequestBody ExtractActivityReq request) {
		logger.debug("extractActivity specId:{}, newActivityName:{}, sourceActivityName:{}, entDefs:{}, attDefs:{}",
				specId, request.getNewActivityName(), request.getSourceActivityName(),
				request.getSuccessCondition().getDefEnts().stream().map((def) -> def.getEntityName())
						.collect(Collectors.joining(",")),
				request.getSuccessCondition().getDefAtts().stream().map((def) -> def.getPath())
						.collect(Collectors.joining("|")));

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.extractActivity(specId, request.getNewActivityName(),
				"splitted from activity " + request.getSourceActivityName(), request.getSourceActivityName(),
				request.getSuccessCondition());

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

}
