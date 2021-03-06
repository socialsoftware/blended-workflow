package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Set;

import javax.inject.Inject;

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

import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.ExtractGoalDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/goalmodel")
public class GoalModelController {
	private static Logger logger = LoggerFactory.getLogger(GoalModelController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> cleanGoalModel(@PathVariable("specId") String specId) {
		logger.debug("cleanGoalModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.cleanGoalModel(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateGoalModel(@PathVariable("specId") String specId) {
		logger.debug("generateGoalModel specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		boolean result = adi.generateGoalModel(specId);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/view", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateView(@PathVariable("specId") String specId, @RequestBody GoalModelDto goalModelDto) {
		logger.debug("updateView specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.updateView(specId, goalModelDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/rename/{newName}", method = RequestMethod.POST)
	public ResponseEntity<Void> updateGoalName(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @PathVariable("newName") String newName) {
		logger.debug("updateGoalName specId:{}, goalName:{}, newName{}", specId, goalName, newName);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.updateGoalName(specId, goalName, newName);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/goals", method = RequestMethod.GET)
	public ResponseEntity<GoalDto[]> getGoalSet(@PathVariable("specId") String specId) {
		logger.debug("getGoalSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<Goal> goals = adi.getGoals(specId);

		return new ResponseEntity<>(goals.stream().map(g -> new GoalDto(g)).toArray(GoalDto[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/product", method = RequestMethod.GET)
	public ResponseEntity<GoalDto[]> getProductGoalSet(@PathVariable("specId") String specId) {
		logger.debug("getProductGoalSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<ProductGoal> goals = adi.getProductGoals(specId);

		return new ResponseEntity<>(goals.stream().map(g -> new GoalDto(g)).toArray(GoalDto[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/association", method = RequestMethod.GET)
	public ResponseEntity<GoalDto[]> getAssociationGoalSet(@PathVariable("specId") String specId) {
		logger.debug("getProductGoalSet specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		Set<AssociationGoal> goals = adi.getAssociationGoals(specId);

		return new ResponseEntity<>(goals.stream().map(g -> new GoalDto(g)).toArray(GoalDto[]::new), HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}", method = RequestMethod.GET)
	public ResponseEntity<GoalDto> getGoalByName(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalByName specId:{}, name:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		Goal goal = adi.getGoalByName(specId, goalName);

		return new ResponseEntity<>(new GoalDto(goal), HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/sucent", method = RequestMethod.GET)
	public ResponseEntity<DefEntityConditionDto[]> getGoalSucEntityAchieveConditions(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName) {
		logger.debug("getGoalSucEntityAchieveConditions specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		DefEntityConditionDto[] defs = adi.getGoalSuccessEntitySet(specId, goalName).stream().map((def) -> def.getDTO())
				.toArray(size -> new DefEntityConditionDto[size]);

		return new ResponseEntity<>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/sucent/{path}/", method = RequestMethod.POST)
	public ResponseEntity<DefEntityConditionDto> associateEntityAchieveConditionToGoalSuc(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName,
			@PathVariable("path") String path) {
		logger.debug("associateSucConditionToGoal specId:{}, goalName:{}, path:{}", specId, goalName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		DefEntityCondition defEntityCondition = adi.associateEntityToGoalSuccess(specId, goalName, path);

		return new ResponseEntity<>(defEntityCondition.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/sucatt", method = RequestMethod.GET)
	public ResponseEntity<DefAttributeConditionDto[]> getGoalSucAttributeAchieveConditions(
			@PathVariable("specId") String specId, @PathVariable("goalName") String goalName) {
		logger.debug("getGoalSucAttributeAchieveConditions specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		DefAttributeConditionDto[] defs = adi.getGoalSuccessAttributeSet(specId, goalName).stream()
				.map((def) -> def.getDto()).toArray(size -> new DefAttributeConditionDto[size]);

		return new ResponseEntity<>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalExtId}/sucatt/{path}/", method = RequestMethod.POST)
	public ResponseEntity<String> associateAttributeAchieveConditionToGoalSuc(@PathVariable("specId") String specId,
			@PathVariable("goalExtId") String goalExtId, @PathVariable("path") String path) {
		logger.debug("associateActConditionToGoal specId:{}, goalExtId:{}, path:{}", specId, goalExtId, path);

		DesignInterface adi = this.factory.createDesignInterface();

		adi.associateAttributeToGoalSuccess(specId, goalExtId, path);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/act", method = RequestMethod.GET)
	public ResponseEntity<DefPathConditionDto[]> getGoalActDefPathConditionSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalActDefPathConditionSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		DefPathConditionDto[] paths = adi.getGoalActivationDefPathConditionSet(specId, goalName).stream()
				.map((def) -> def.getDTO(specId)).toArray(size -> new DefPathConditionDto[size]);

		return new ResponseEntity<>(paths, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/act/{path}/", method = RequestMethod.POST)
	public ResponseEntity<DefPathConditionDto> associateDefPathConditionToGoalAct(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @PathVariable("path") String path) {
		logger.debug("associateDefPathConditionToGoalAct specId:{}, goalName:{}, path:{}", specId, goalName, path);

		DesignInterface adi = this.factory.createDesignInterface();

		DefPathCondition defPathCondition = adi.associateDefPathConditionToGoalActivation(specId, goalName, path);

		return new ResponseEntity<>(defPathCondition.getDTO(specId), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/invent", method = RequestMethod.GET)
	public ResponseEntity<MulConditionDto[]> getGoalMulInvSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalMulInvSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		MulConditionDto[] defs = adi.getGoalMulInvSet(specId, goalName).stream().map((def) -> def.getDto())
				.toArray(size -> new MulConditionDto[size]);

		return new ResponseEntity<>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/relations", method = RequestMethod.GET)
	public ResponseEntity<RelationDto[]> getGoalRelations(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalRelations specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		RelationDto[] rels = adi.getGoalRelations(specId, goalName).stream().map(r -> r.getDTO())
				.toArray(size -> new RelationDto[size]);

		return new ResponseEntity<>(rels, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/invent", method = RequestMethod.POST)
	public ResponseEntity<String> associateEntityInvariantConditionToGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @RequestBody MulConditionDto mulConditionDTO) {
		logger.debug("associateActConditionToGoal specId:{}, goalName:{}, path:{}, cardinality:{}", specId, goalName,
				mulConditionDTO.getRolePath(), mulConditionDTO.getCardinality());

		DesignInterface adi = this.factory.createDesignInterface();

		adi.associateMulToGoalInvariant(specId, goalName, mulConditionDTO.getRolePath(),
				mulConditionDTO.getCardinality());

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/{goalName}/invatt", method = RequestMethod.GET)
	public ResponseEntity<RuleDto[]> getGoalRuleInvSet(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName) {
		logger.debug("getGoalRuleInvSet specId:{}, goalName:{}", specId, goalName);

		DesignInterface adi = this.factory.createDesignInterface();

		RuleDto[] defs = adi.getGoalRuleInvSet(specId, goalName).stream().map((def) -> def.getDTO())
				.toArray(size -> new RuleDto[size]);

		return new ResponseEntity<>(defs, HttpStatus.OK);
	}

	@RequestMapping(value = "/goals/{goalName}/invatt", method = RequestMethod.POST)
	public ResponseEntity<String> associateAttributeInvariantConditionToGoal(@PathVariable("specId") String specId,
			@PathVariable("goalName") String goalName, @RequestBody RuleDto ruleDTO) {
		logger.debug("associateActConditionToGoal specId:{}, goalName:{}, rule:{}", specId, goalName,
				ruleDTO.getName());

		DesignInterface adi = this.factory.createDesignInterface();

		adi.associateRuleToGoalInvariant(ruleDTO, goalName);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/merge", method = RequestMethod.POST)
	public ResponseEntity<GoalDto> mergeGoals(@PathVariable("specId") String specId,
			@RequestBody MergeOperationDto mergeOperationDto) {
		logger.debug("mergeGoals specId:{}, newGoalName:{}, goalNameOne:{}, goalNameTwo:{}", specId,
				mergeOperationDto.getNewName(), mergeOperationDto.getNameOne(), mergeOperationDto.getNameTwo());

		DesignInterface adi = this.factory.createDesignInterface();

		Goal goal = adi.mergeGoals(mergeOperationDto);

		return new ResponseEntity<>(new GoalDto(goal), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/extract", method = RequestMethod.POST)
	public ResponseEntity<GoalDto> extractProductGoal(@PathVariable("specId") String specId,
			@RequestBody ExtractGoalDto req) {
		logger.debug("extractGoal specId:{}, newGoalName:{}, sourceGoalName:{}", specId, req.getNewGoalName(),
				req.getSourceGoalName());

		DesignInterface adi = this.factory.createDesignInterface();

		Goal goal = adi.extractGoal(specId, req);

		return new ResponseEntity<>(new GoalDto(goal), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/goals/graph", method = RequestMethod.GET)
	public ResponseEntity<GraphDto> getGoalGraph(@PathVariable("specId") String specId) {
		logger.debug("getGoalGraph specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		GraphDto graph = adi.getGoalModelGraph(specId);

		return new ResponseEntity<>(graph, HttpStatus.OK);
	}

	@RequestMapping(value = "/graph/vis", method = RequestMethod.GET)
	public ResponseEntity<GraphVisDto> getGoalGraphVis(@PathVariable("specId") String specId) {
		logger.debug("getGoalGraphVis specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		GraphVisDto graph = adi.getGoalModelGraphVis(specId);

		return new ResponseEntity<>(graph, HttpStatus.OK);
	}

}
