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
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefProductConditionSetDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.req.AddActivityReq;

@RestController
@RequestMapping(value = "/specs/{specId}/activitymodel")
public class ActivityModelController {
	private static Logger log = LoggerFactory.getLogger(ActivityModelController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanActivityModel(@PathVariable("specId") String specId) {
		log.debug("cleanActivityModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.cleanActivityModel(specId);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/activities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> createActivity(@PathVariable("specId") String specId,
			@RequestBody ActivityDTO activityDTO) {
		log.debug("createActivity specId:{}, name:{}, description:{}", specId, activityDTO.getName(),
				activityDTO.getDescription());

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.createActivity(activityDTO);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> addActivity(@PathVariable("specId") String specId,
			@RequestBody AddActivityReq request) {
		log.debug("addActivity specId:{}, name:{}, description:{}", specId, request.getActivityName(),
				request.getDescription());

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.addActivity(specId, request);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ActivityDTO[]> getActivitySet(@PathVariable("specId") String specId) {
		log.debug("getActivitySet specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Task> goals = adi.getActivities(specId);

		return new ResponseEntity<ActivityDTO[]>(goals.stream().map(g -> g.getDTO()).toArray(ActivityDTO[]::new),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/pre", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefProductConditionSetDTO> getActivityPreCondition(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		log.debug("getActivityPreCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<DefProductCondition> preConditionSet = adi.getActivityPreCondition(specId, activityName);

		ConditionModel conditionModel = adi.getSpecBySpecId(specId).getConditionModel();

		DefProductConditionSetDTO defConditionSetDTO = new DefProductConditionSetDTO();
		defConditionSetDTO.setDefEnts(conditionModel.getDefEntityConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefAtts(conditionModel.getDefAttributeConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefDeps(conditionModel.getDefDependenceConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));

		return new ResponseEntity<DefProductConditionSetDTO>(defConditionSetDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/preent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToActivityPre(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		log.debug("associateEntityAchieveConditionToActivityPre specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefEntityCondition defEntityCondition = adi.associateEntityToActivityPre(specId, activityName, path);

		return new ResponseEntity<DefEntityConditionDTO>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/preatt/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefAttributeConditionDTO> associateAttributeAchieveConditionToActivityPre(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		log.debug("associateActConditionToGoal specId:{}, activityName:{}, path:{}", specId, activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefAttributeCondition defAttributeCondition = adi.associateAttributeToActivityPre(specId, activityName, path);

		return new ResponseEntity<DefAttributeConditionDTO>(defAttributeCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/post", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<DefProductConditionSetDTO> getActivityPostCondition(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		log.debug("getActivityPostCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<DefProductCondition> preConditionSet = adi.getActivityPostCondition(specId, activityName);

		ConditionModel conditionModel = adi.getSpecBySpecId(specId).getConditionModel();

		DefProductConditionSetDTO defConditionSetDTO = new DefProductConditionSetDTO();
		defConditionSetDTO.setDefEnts(conditionModel.getDefEntityConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefAtts(conditionModel.getDefAttributeConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefDeps(conditionModel.getDefDependenceConditions(preConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));

		return new ResponseEntity<DefProductConditionSetDTO>(defConditionSetDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postent/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		log.debug("associateEntityAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefEntityCondition defEntityCondition = adi.associateEntityToActivityPost(specId, activityName, path);

		return new ResponseEntity<DefEntityConditionDTO>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postatt/{path}/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DefAttributeConditionDTO> associateAttributeAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		log.debug("associateAttributeAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = DesignInterface.getInstance();

		DefAttributeCondition defAttributeCondition = adi.associateAttributeToActivityPost(specId, activityName, path);

		return new ResponseEntity<DefAttributeConditionDTO>(defAttributeCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<MulConditionDTO[]> getActivityMultConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		log.debug("getActivityMultConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<MulCondition> mulConditions = adi.getActivityMulConditions(specId, activityName);

		return new ResponseEntity<MulConditionDTO[]>(
				mulConditions.stream().map(mul -> mul.getDTO()).toArray(size -> new MulConditionDTO[size]),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<MulConditionDTO> associateMultiplicityToActivityPost(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @RequestBody MulConditionDTO mulConditionDTO) {
		log.debug("associateActConditionToGoal specId:{}, activityName:{}, path:{}, cardinality:{}", specId,
				activityName, mulConditionDTO.getRolePath(), mulConditionDTO.getCardinality());

		DesignInterface adi = DesignInterface.getInstance();

		MulCondition mulCondition = adi.associateMulToActivityPost(specId, activityName, mulConditionDTO.getRolePath(),
				mulConditionDTO.getCardinality());

		return new ResponseEntity<MulConditionDTO>(mulCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<RuleDTO[]> getActivityRuleConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		log.debug("getActivityRuleConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = DesignInterface.getInstance();

		Set<Rule> rules = adi.getActivityRuleConditions(specId, activityName);

		return new ResponseEntity<RuleDTO[]>(
				rules.stream().map(rule -> rule.getDTO()).toArray(size -> new RuleDTO[size]), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<RuleDTO> associateAttributeInvariantConditionActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@RequestBody RuleDTO ruleDTO) {
		log.debug("associateActConditionToGoal specId:{}, activityName:{}, rule:{}", specId, activityName,
				ruleDTO.getName());

		DesignInterface adi = DesignInterface.getInstance();

		Rule rule = adi.associateRuleToActivityPost(specId, activityName, ruleDTO.getName());

		return new ResponseEntity<RuleDTO>(rule.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkActivityModel(@PathVariable("specId") String specId) {
		log.debug("checkActivityModel specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		boolean result = adi.checkActivityModel(specId);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/merge", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> mergeGoals(@PathVariable("specId") String specId,
			@PathParam("newActivityName") String newActivityName, @PathParam("activityNameOne") String activityNameOne,
			@PathParam("activityNameTwo") String activityNameTwo) {
		log.debug("mergeGoals specId:{}, newActivityName:{}, activityNameOne:{}, activityNameTwo:{}", specId,
				newActivityName, activityNameOne, activityNameTwo);

		DesignInterface adi = DesignInterface.getInstance();

		Task task = adi.mergeActivities(specId, newActivityName, "merged: " + activityNameOne + " " + activityNameTwo,
				activityNameOne, activityNameTwo);

		return new ResponseEntity<ActivityDTO>(task.getDTO(), HttpStatus.CREATED);
	}

}
