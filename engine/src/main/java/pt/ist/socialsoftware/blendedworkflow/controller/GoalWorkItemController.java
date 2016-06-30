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

import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.service.execution.ExecutionInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/goalworkitem")
public class GoalWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemController.class);

	@RequestMapping(value = "/next", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GoalWorkItemDTO[]> getGoalWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getGoalWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		GoalWorkItemDTO[] instances = edi.getPendingGoalWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new GoalWorkItemDTO[size]);

		return new ResponseEntity<GoalWorkItemDTO[]>(instances, HttpStatus.OK);
	}

}
