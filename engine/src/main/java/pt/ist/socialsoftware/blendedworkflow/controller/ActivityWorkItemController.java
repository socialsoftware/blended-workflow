package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.service.execution.ExecutionInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/activity")
public class ActivityWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDTO[]> getActivityWorkItems(@PathVariable("specId") String specId,
			@PathVariable String instanceName) {
		logger.debug("getActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		ActivityWorkItemDTO[] instances = edi.getPendingActivityWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new ActivityWorkItemDTO[size]);

		return new ResponseEntity<ActivityWorkItemDTO[]>(instances, HttpStatus.OK);
	}

}
