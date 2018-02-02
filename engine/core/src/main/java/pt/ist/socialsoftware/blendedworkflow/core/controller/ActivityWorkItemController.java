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

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/activityworkitem")
public class ActivityWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemController.class);

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDTO[]> getNextActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getNextActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		ActivityWorkItemDTO[] instances = edi.getPendingActivityWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new ActivityWorkItemDTO[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDTO[]> getLogActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		ActivityWorkItemDTO[] instances = edi.getLogActivityWorkItemSet(specId, instanceName).stream()
				.map(awi -> awi.getDTO()).toArray(size -> new ActivityWorkItemDTO[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> executeActivityWorkItem(@PathVariable String specId,
			@RequestBody ActivityWorkItemDTO activityWorkItemDTO) {
		logger.debug("executeActivityActivityWorkItem specId:{}, instanceName:{}, activityName:{}", specId,
				activityWorkItemDTO.getWorkflowInstanceName(), activityWorkItemDTO.getName());
		logger.debug("executeActivityWorkItem activityWorkItemDTO:{}", activityWorkItemDTO.print());
		ExecutionInterface edi = ExecutionInterface.getInstance();

		edi.executeActivityWorkItem(activityWorkItemDTO);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

}
