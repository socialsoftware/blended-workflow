package pt.ist.socialsoftware.blendedworkflow.core.controller;

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

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.UndefinedEntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkflowInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/instances")
public class InstanceController {
	private static Logger log = LoggerFactory.getLogger(InstanceController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDto[]> getWorkflowInstances(@PathVariable("specId") String specId) {
		log.debug("getWorkflowInstances specId:{}", specId);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstanceDto[] instances = edi.getWorkflowInstances(specId).stream().map(wi -> wi.getDTO())
				.sorted((wi1, wi2) -> wi1.getName().compareTo(wi2.getName()))
				.toArray(size -> new WorkflowInstanceDto[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDto> getWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		log.debug("getWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstance workflowInstance = edi.getWorkflowInstance(specId, name);

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<WorkflowInstanceDto> createWorkflowInstance(@PathVariable("specId") String specId,
			@RequestBody WorkflowInstanceDto workflowInstanceDto) {
		log.debug("createWorkflowInstance specId:{}, name:{}", specId, workflowInstanceDto.getName());
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstance workflowInstance = edi.createWorkflowInstance(specId, workflowInstanceDto.getName());

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		log.debug("deleteWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		edi.deleteWorkflowInstance(specId, name);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}/entity/mandatory", method = RequestMethod.GET)
	public ResponseEntity<EntityInstanceDto> getMandatoryEntityInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		log.debug("getMandatoryEntityInstance specId:{}, name:{}", specId, name);
		ExecutionInterface ei = this.factory.createExecutionInterface();

		EntityInstance entityInstance = ei.getMandatoryEntityInstance(specId, name);

		EntityInstanceDto entityInstanceDto = null;
		if (entityInstance == null) {
			DesignInterface di = this.factory.createDesignInterface();
			Entity entity = di.getMandatoryEntity(specId);
			entityInstanceDto = new UndefinedEntityInstanceDto(entity);
		} else {
			entityInstanceDto = new EntityInstanceDto(entityInstance);
		}

		return new ResponseEntity<>(entityInstanceDto, HttpStatus.OK);
	}

}
