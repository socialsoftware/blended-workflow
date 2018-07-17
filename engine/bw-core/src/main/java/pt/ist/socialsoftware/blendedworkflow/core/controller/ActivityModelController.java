package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefProductConditionSetDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.AddActivityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.ExtractActivityDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/activitymodel")
public class ActivityModelController {
	private static Logger logger = LoggerFactory.getLogger(ActivityModelController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> cleanActivityModel(@PathVariable("specId") String specId) {
		logger.debug("cleanActivityModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.cleanActivityModel(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateActivityModel(@PathVariable("specId") String specId) {
		logger.debug("generateActivityModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		boolean result = adi.generateActivityModel(specId);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities", method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> createActivity(@PathVariable("specId") String specId,
			@RequestBody ActivityDTO activityDTO) {
		logger.debug("createActivity specId:{}, name:{}, description:{}", specId, activityDTO.getName(),
				activityDTO.getDescription());

		DesignInterface adi = this.factory.createDesignInterface();

		Activity activity = adi.createActivity(activityDTO);

		return new ResponseEntity<>(activity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/add", method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> addActivity(@PathVariable("specId") String specId,
			@RequestBody AddActivityDto request) {
		logger.debug("addActivity specId:{}, name:{}, description:{}, postConditions:{}", specId,
				request.getActivityName(), request.getDescription(),
				request.getPostConditionSet().stream().map(d -> d.getPath()).collect(Collectors.joining(",")));

		DesignInterface adi = this.factory.createDesignInterface();

		Activity activity = adi.addActivity(specId, request);

		return new ResponseEntity<>(activity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/{newName}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateActivityName(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @PathVariable("newName") String newName) {
		logger.debug("updateActivityName specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.updateActivityName(specId, activityName, newName);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public ResponseEntity<ActivityDTO[]> getActivitySet(@PathVariable("specId") String specId) {
		logger.debug("getActivitySet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<Activity> goals = adi.getActivities(specId);

		return new ResponseEntity<>(goals.stream().map(g -> g.getDTO()).toArray(ActivityDTO[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/pre", method = RequestMethod.GET)
	public ResponseEntity<DefPathConditionDTO[]> getActivityPreConditionSet(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityPreCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<DefPathCondition> preConditionSet = adi.getActivityPreCondition(specId, activityName);

		return new ResponseEntity<>(
				preConditionSet.stream().map(d -> d.getDTO(specId)).toArray(DefPathConditionDTO[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/pre/{path}/", method = RequestMethod.POST)
	public ResponseEntity<DefPathConditionDTO> associateDefPathToActivityPre(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @PathVariable("path") String path) {
		logger.debug("associateDefPathToActivityPre specId:{}, activityName:{}, path:{}", specId, activityName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		DefPathCondition defPathCondition = adi.associateDefPathToActivityPre(specId, activityName, path);

		return new ResponseEntity<>(defPathCondition.getDTO(specId), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/post", method = RequestMethod.GET)
	public ResponseEntity<DefProductConditionSetDto> getActivityPostCondition(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityPostCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<DefProductCondition> postConditionSet = adi.getActivityPostCondition(specId, activityName);

		DefProductConditionSetDto defConditionSetDTO = new DefProductConditionSetDto();
		defConditionSetDTO.setDefEnts(ConditionModel.getDefEntityConditions(postConditionSet).stream()
				.map(d -> d.getDTO()).collect(Collectors.toSet()));
		defConditionSetDTO.setDefAtts(ConditionModel.getDefAttributeConditions(postConditionSet).stream()
				.map(d -> d.getDto()).collect(Collectors.toSet()));

		return new ResponseEntity<>(defConditionSetDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postent/{path}/", method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDTO> associateEntityAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		logger.debug("associateEntityAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		DefEntityCondition defEntityCondition = adi.associateEntityToActivityPost(specId, activityName, path);

		return new ResponseEntity<>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postatt/{path}/", method = RequestMethod.POST)
	public ResponseEntity<DefAttributeConditionDTO> associateAttributeAchieveConditionToActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@PathVariable("path") String path) {
		logger.debug("associateAttributeAchieveConditionToActivityPost specId:{}, activityName:{}, path:{}", specId,
				activityName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		DefAttributeCondition defAttributeCondition = adi.associateAttributeToActivityPost(specId, activityName, path);

		return new ResponseEntity<>(defAttributeCondition.getDto(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", method = RequestMethod.GET)
	public ResponseEntity<MulConditionDTO[]> getActivityMultConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityMultConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<MulCondition> mulConditions = adi.getActivityMulConditions(specId, activityName);

		return new ResponseEntity<>(
				mulConditions.stream().map(mul -> mul.getDTO()).toArray(size -> new MulConditionDTO[size]),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postmul", method = RequestMethod.POST)
	public ResponseEntity<MulConditionDTO> associateMultiplicityToActivityPost(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @RequestBody MulConditionDTO mulConditionDTO) {
		logger.debug("associateActConditionToGoal specId:{}, activityName:{}, path:{}, cardinality:{}", specId,
				activityName, mulConditionDTO.getRolePath(), mulConditionDTO.getCardinality());

		DesignInterface adi = this.factory.createDesignInterface();

		MulCondition mulCondition = adi.associateMulToActivityPost(specId, activityName, mulConditionDTO.getRolePath(),
				mulConditionDTO.getCardinality());

		return new ResponseEntity<>(mulCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", method = RequestMethod.GET)
	public ResponseEntity<RuleDTO[]> getActivityRuleConditions(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityRuleConditions specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<Rule> rules = adi.getActivityRuleConditions(specId, activityName);

		logger.debug("getActivityRuleConditions specId:{}, activityName:{}, size:{}", specId, activityName,
				rules.size());

		return new ResponseEntity<>(rules.stream().map(rule -> rule.getDTO()).toArray(size -> new RuleDTO[size]),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/postrule", method = RequestMethod.POST)
	public ResponseEntity<RuleDTO> associateAttributeInvariantConditionActivityPost(
			@PathVariable("specId") String specId, @PathVariable("activityName") String activityName,
			@RequestBody RuleDTO ruleDTO) {
		logger.debug("associateAttributeInvariantConditionActivityPost specId:{}, activityName:{}, rule:{}", specId,
				activityName, ruleDTO.getName());

		DesignInterface adi = this.factory.createDesignInterface();

		Rule rule = adi.associateRuleToActivityPost(ruleDTO, activityName);

		return new ResponseEntity<>(rule.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkActivityModel(@PathVariable("specId") String specId) {
		logger.debug("checkActivityModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.checkActivityModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/merge", method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> mergeActivities(@PathVariable("specId") String specId,
			@RequestBody MergeOperationDto activityMergeDto) {
		logger.debug("mergeActivities specId:{}, newActivityName:{}, activityNameOne:{}, activityNameTwo:{}", specId,
				activityMergeDto.getNewName(), activityMergeDto.getNameOne(),
				activityMergeDto.getNameTwo());

		DesignInterface adi = this.factory.createDesignInterface();

		Activity activity = adi.mergeActivities(activityMergeDto);

		return new ResponseEntity<>(activity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/extract", method = RequestMethod.POST)
	public ResponseEntity<ActivityDTO> extractActivity(@PathVariable("specId") String specId,
			@RequestBody ExtractActivityDto request) {
		logger.debug("extractActivity specId:{}, newActivityName:{}, sourceActivityName:{}, entDefs:{}, attDefs:{}",
				specId, request.getNewActivityName(), request.getSourceActivityName(),
				request.getSuccessConditions().stream().map((def) -> def.getPath()).collect(Collectors.joining("|")));

		DesignInterface adi = this.factory.createDesignInterface();

		Activity activity = adi.extractActivity(specId, request.getNewActivityName(),
				"splitted from activity " + request.getSourceActivityName(), request.getSourceActivityName(),
				request.getSuccessConditions());

		return new ResponseEntity<>(activity.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/seq/add", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addSequenceConditionToActivity(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @RequestBody String path) {
		logger.debug("addSequenceConditionToActivity specId:{}, activityName:{}, path:{}", specId, activityName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.addSequenceConditionToActivity(specId, activityName, path);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activities/{activityName}/seq/rem", method = RequestMethod.POST)
	public ResponseEntity<Boolean> removeSequenceConditionToActivity(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName, @RequestBody String path) {
		logger.debug("removeSequenceConditionToActivity specId:{}, activityName:{}, path:{}", specId, activityName,
				path);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.removeSequenceConditionToActivity(specId, activityName, path);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/{activityName}/seq", method = RequestMethod.GET)
	public ResponseEntity<DefPathConditionDTO[]> getActivitySeqConditionSet(@PathVariable("specId") String specId,
			@PathVariable("activityName") String activityName) {
		logger.debug("getActivityPreCondition specId:{}, activityName:{}", specId, activityName);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<DefPathCondition> preConditionSet = adi.getActivitySeqCondition(specId, activityName);

		return new ResponseEntity<>(
				preConditionSet.stream().map(d -> d.getDTO(specId)).toArray(DefPathConditionDTO[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/graph", method = RequestMethod.GET)
	public ResponseEntity<GraphDTO> getActivityGraph(@PathVariable("specId") String specId) {
		logger.debug("getActivityGraph specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		GraphDTO activityGraph = adi.getActivityGraph(specId);

		return new ResponseEntity<>(activityGraph, HttpStatus.OK);
	}

}
