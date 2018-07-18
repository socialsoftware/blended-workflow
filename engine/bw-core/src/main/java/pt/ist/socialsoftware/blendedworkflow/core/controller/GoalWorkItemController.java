package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/goalworkitem")
public class GoalWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public ResponseEntity<GoalWorkItemDto[]> getGoalWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getGoalWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		GoalWorkItemDto[] instances = edi.getPendingGoalWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new GoalWorkItemDto[size]);

		logger.debug("getGoalWorkItems activityDTOs: {}",
				Stream.of(instances).map(aw -> aw.print()).collect(Collectors.joining("\n\n")));

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<GoalWorkItemDto[]> getLogGoalWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		GoalWorkItemDTO[] instances = edi.getLogGoalWorkItemDTOSet(specId, instanceName).stream()
				.toArray(GoalWorkItemDTO[]::new);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> executeGoalWorkItem(@PathVariable String specId,
			@RequestBody GoalWorkItemDto goalWorkItemDTO) {
		logger.debug("executeGoalWorkItem specId:{}, instanceName:{}, goalName:{}", specId,
				goalWorkItemDTO.getWorkflowInstanceName(), goalWorkItemDTO.getName());
		logger.debug("executeGoalWorkItem goalWorkItemDTO:{}", goalWorkItemDTO.print());
		ExecutionInterface edi = this.factory.createExecutionInterface();

		edi.executeGoalWorkItem(goalWorkItemDTO);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

}
