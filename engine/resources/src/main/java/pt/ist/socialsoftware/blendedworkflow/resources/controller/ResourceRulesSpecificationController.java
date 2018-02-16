package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.*;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcerules")
public class ResourceRulesSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceRulesSpecificationController.class);

	@RequestMapping(value = "/relations", method = RequestMethod.POST)
	public ResponseEntity<ResourceRelationDTO> createEntityIsPerson(@PathVariable("specId") String specId,
														  @RequestBody ResourceRelationDTO resourceRelationDTO) {
		log.debug("CreateEntityIsPerson: {}, {}", specId, resourceRelationDTO.getEntityName());

		DesignInterface designer = DesignInterface.getInstance();

		designer.relationEntityIsPerson(resourceRelationDTO);

		return new ResponseEntity<>(resourceRelationDTO, HttpStatus.CREATED);
	}
}
