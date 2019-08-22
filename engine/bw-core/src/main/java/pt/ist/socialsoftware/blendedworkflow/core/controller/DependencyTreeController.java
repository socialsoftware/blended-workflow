package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.List;

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

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeInstanceWithEntityInstanceIdDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/dependencytree")
public class DependencyTreeController {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemController.class);

	@Inject
	private ModulesFactory factory;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> defineDependentAttributeInstances(@PathVariable String specId,
			@PathVariable String instanceName, @RequestBody List<List<AttributeInstanceDto>> dependencyTree) {
		logger.debug("defineDependentAttributeInstances specId:{}, instanceName:{}", specId, instanceName);
		
		ExecutionInterface edi = this.factory.createExecutionInterface();
	
		edi.defineDependentAttributeInstances(specId, instanceName, dependencyTree);

		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<List<List<AttributeInstanceDto>>> getDependencyTree(@PathVariable String specId,
			@PathVariable String instanceName, @RequestBody AttributeInstanceWithEntityInstanceIdDto attributeInstanceWithEntityInstanceIdDto) {
		logger.debug("getDependencyTree specId:{}, instanceName:{}", specId, instanceName);
		
		ExecutionInterface edi = this.factory.createExecutionInterface();
		
		List<List<AttributeInstanceDto>> dependencyTreeDto = edi.getDependencyTree(specId, instanceName, attributeInstanceWithEntityInstanceIdDto);
		
		return new ResponseEntity<>(dependencyTreeDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/dependentattributeinstances", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<List<AttributeInstanceDto>> getDependentAttributeInstances(@PathVariable String specId,
			@PathVariable String instanceName, @RequestBody AttributeInstanceWithEntityInstanceIdDto attributeInstanceWithEntityInstanceIdDto) {
		logger.debug("getDependentAttributeInstances specId:{}, instanceName:{}", specId, instanceName);
		
		ExecutionInterface edi = this.factory.createExecutionInterface();
		
		List<AttributeInstanceDto> dependentAttributeInstances = edi.getDependentAttributeInstances(specId, instanceName, attributeInstanceWithEntityInstanceIdDto);
		
		return new ResponseEntity<>(dependentAttributeInstances, HttpStatus.OK);
	}
}
