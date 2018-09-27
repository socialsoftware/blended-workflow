package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Comparator;
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

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceUndefDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkflowInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/instances")
public class InstanceController {
	private static Logger logger = LoggerFactory.getLogger(InstanceController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDto[]> getWorkflowInstances(@PathVariable("specId") String specId) {
		logger.debug("getWorkflowInstances specId:{}", specId);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstanceDto[] instances = edi.getWorkflowInstances(specId).stream().map(wi -> wi.getDTO())
				.sorted((wi1, wi2) -> wi1.getName().compareTo(wi2.getName()))
				.toArray(size -> new WorkflowInstanceDto[size]);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<WorkflowInstanceDto> getWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		logger.debug("getWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstance workflowInstance = edi.getWorkflowInstance(specId, name);

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<WorkItemDto> getInitWorkItem(@PathVariable("specId") String specId) {
		logger.debug("getInitWorkItem specId:{}", specId);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkItemDto workItem = edi.getInitWorkItem(specId);

		return new ResponseEntity<>(workItem, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<WorkflowInstanceDto> createWorkflowInstance(@PathVariable("specId") String specId,
			@RequestBody WorkItemDto workItemDto) {
		logger.debug("createWorkflowInstance specId:{}, name:{}", specId, workItemDto.getWorkflowInstanceName());
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkflowInstance workflowInstance = edi.createWorkflowInstance(workItemDto);

		return new ResponseEntity<>(workflowInstance.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteWorkflowInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		logger.debug("deleteWorkflowInstance specId:{}, name:{}", specId, name);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		edi.deleteWorkflowInstance(specId, name);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}/entities/mandatory", method = RequestMethod.GET)
	public ResponseEntity<EntityInstanceDto> getMandatoryEntityInstance(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		logger.debug("getMandatoryEntityInstance specId:{}, name:{}", specId, name);
		ExecutionInterface ei = this.factory.createExecutionInterface();

		EntityInstance entityInstance = ei.getMandatoryEntityInstance(specId, name);

		EntityInstanceDto entityInstanceDto = null;
		if (entityInstance == null) {
			DesignInterface di = this.factory.createDesignInterface();
			Entity entity = di.getMandatoryEntity(specId);
			entityInstanceDto = new EntityInstanceUndefDto(entity);
		} else {
			entityInstanceDto = new EntityInstanceDto(entityInstance, Depth.DEEP);
		}

		return new ResponseEntity<>(entityInstanceDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}/entities", method = RequestMethod.GET)
	public ResponseEntity<EntityInstanceDto[]> getEntityInstances(@PathVariable("specId") String specId,
			@PathVariable("name") String name) {
		logger.debug("getEntityInstances specId:{}, name:{}", specId, name);
		ExecutionInterface ei = this.factory.createExecutionInterface();

		Set<EntityInstance> entityInstances = ei.getEntityInstances(specId, name);

		EntityInstanceDto[] result = entityInstances.stream().map(i -> new EntityInstanceDto(i, Depth.DEEP))
				.sorted(Comparator.comparing(EntityInstanceDto::getId)).toArray(size -> new EntityInstanceDto[size]);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}/log", method = RequestMethod.GET)
	public ResponseEntity<WorkItemDto[]> getLogWorkItems(@PathVariable String specId, @PathVariable String name) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, name);
		ExecutionInterface edi = this.factory.createExecutionInterface();

		WorkItemDto[] instances = edi.getLogWorkItemDtoList(specId, name).stream().toArray(WorkItemDto[]::new);

		return new ResponseEntity<>(instances, HttpStatus.OK);
	}

}
