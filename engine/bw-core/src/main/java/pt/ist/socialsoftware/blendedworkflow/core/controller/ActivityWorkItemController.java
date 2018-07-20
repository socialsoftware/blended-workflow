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

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/activityworkitem")
public class ActivityWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDto[]> getNextActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getNextActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		ActivityWorkItemDto[] instances = edi.getPendingActivityWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new ActivityWorkItemDto[size]);

		logger.debug("getNextActivityWorkItems activityDTOs: {}",
				Stream.of(instances).map(aw -> aw.print()).collect(Collectors.joining("\n\n")));

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDto[]> getLogActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		ActivityWorkItemDto[] instances = edi.getLogActivityWorkItemSet(specId, instanceName).stream()
				.map(awi -> awi.getDTO()).toArray(size -> new ActivityWorkItemDto[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> executeActivityWorkItem(@PathVariable String specId,
			@RequestBody ActivityWorkItemDto activityWorkItemDTO) {
		logger.debug("executeActivityActivityWorkItem specId:{}, instanceName:{}, activityName:{}", specId,
				activityWorkItemDTO.getWorkflowInstanceName(), activityWorkItemDTO.getName());
		logger.debug("executeActivityWorkItem activityWorkItemDTO:{}", activityWorkItemDTO.print());
		ExecutionInterface edi = this.factory.createExecutionInterface();

		edi.executeActivityWorkItem(activityWorkItemDTO);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

}
