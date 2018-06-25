package pt.ist.socialsoftware.blendedworkflow.core.controller;

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

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/goalworkitem")
public class GoalWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public ResponseEntity<GoalWorkItemDTO[]> getGoalWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getGoalWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = factory.createExecutionInterface();

		GoalWorkItemDTO[] instances = edi.getPendingGoalWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new GoalWorkItemDTO[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<GoalWorkItemDTO[]> getLogGoalWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = factory.createExecutionInterface();

		GoalWorkItemDTO[] instances = edi.getLogGoalWorkItemSet(specId, instanceName).stream().map(owi -> owi.getDTO())
				.toArray(size -> new GoalWorkItemDTO[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> executeGoalWorkItem(@PathVariable String specId,
			@RequestBody GoalWorkItemDTO goalWorkItemDTO) {
		logger.debug("executeGoalWorkItem specId:{}, instanceName:{}, goalName:{}", specId,
				goalWorkItemDTO.getWorkflowInstanceName(), goalWorkItemDTO.getName());
		logger.debug("executeGoalWorkItem activityWorkItemDTO:{}", goalWorkItemDTO.print());
		ExecutionInterface edi = factory.createExecutionInterface();

		edi.executeGoalWorkItem(goalWorkItemDTO);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

}
