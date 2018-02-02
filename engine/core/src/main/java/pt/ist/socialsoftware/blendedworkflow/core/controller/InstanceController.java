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

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.WorkflowInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/instances")
public class InstanceController {
	private static Logger log = LoggerFactory.getLogger(InstanceController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDTO[]> getWorkflowInstances(@PathVariable("specId") String specId) {
		log.debug("getWorkflowInstances specId:{}", specId);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		WorkflowInstanceDTO[] instances = edi.getWorkflowInstances(specId).stream().map(wi -> wi.getDTO())
				.sorted((wi1, wi2) -> wi1.getName().compareTo(wi2.getName()))
				.toArray(size -> new WorkflowInstanceDTO[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDTO> getWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		log.debug("getWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		WorkflowInstance workflowInstance = edi.getWorkflowInstance(specId, name);

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<WorkflowInstanceDTO> createWorkflowInstance(@PathVariable("specId") String specId,
			@RequestBody WorkflowInstanceDTO workflowInstanceDTO) {
		log.debug("createWorkflowInstance specId:{}, name:{}", specId, workflowInstanceDTO.getName());
		ExecutionInterface edi = ExecutionInterface.getInstance();

		WorkflowInstance workflowInstance = edi.createWorkflowInstance(specId, workflowInstanceDTO.getName());

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		log.debug("deleteWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		edi.deleteWorkflowInstance(specId, name);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
