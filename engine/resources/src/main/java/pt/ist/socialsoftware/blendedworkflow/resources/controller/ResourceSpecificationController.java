package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcemodel")
public class ResourceSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceSpecificationController.class);

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> createResourceModel(@PathVariable("specId") String specId) {
		log.debug("CreateResourceModel: {}", specId);

		DesignInterface designer = DesignInterface.getInstance();

		designer.createResourceModel(specId);

		return new ResponseEntity<>(new Boolean(true), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/capabilities", method = RequestMethod.POST)
	public ResponseEntity<CapabilityDTO> createCapability(@PathVariable("specId") String specId,
													@RequestBody CapabilityDTO capabilityDTO) {
		log.debug("CreateCapability: {}, {}, {}", specId, capabilityDTO.getName(), capabilityDTO.getDescription());

		DesignInterface designer = DesignInterface.getInstance();

		Capability capability = designer.createCapability(capabilityDTO);

		return new ResponseEntity<>(capability.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	public ResponseEntity<Boolean> cleanResourceModel(@PathVariable("specId") String specId) {
		log.debug("checkDataModel specId:{}", specId);

		DesignInterface designer = DesignInterface.getInstance();

		designer.cleanResourceModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
