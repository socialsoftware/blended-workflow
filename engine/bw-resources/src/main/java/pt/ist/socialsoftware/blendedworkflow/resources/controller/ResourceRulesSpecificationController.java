package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceRelationDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceRuleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.utils.ResourcesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcerules")
public class ResourceRulesSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceRulesSpecificationController.class);

	@Inject
	private ResourcesFactory factory;

	@RequestMapping(value = "/relations", method = RequestMethod.POST)
	public ResponseEntity<ResourceRelationDto> createEntityIsPerson(@PathVariable("specId") String specId,
                                                                    @RequestBody ResourceRelationDto resourceRelationDTO) {
		log.debug("CreateEntityIsPerson: {}, {}", specId, resourceRelationDTO.getEntityName());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		designer.relationEntityIsPerson(resourceRelationDTO);

		return new ResponseEntity<>(resourceRelationDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public ResponseEntity<ResourceRuleDTO> createEntityIsPerson(@PathVariable("specId") String specId,
			@RequestBody ResourceRuleDTO resourceRuleDTO) {
		log.debug("CreateResourceRule: {}, {}, {}", specId, resourceRuleDTO.getDataField(), resourceRuleDTO.getType());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		designer.addResourceRule(resourceRuleDTO);

		return new ResponseEntity<>(resourceRuleDTO, HttpStatus.CREATED);
	}
}
