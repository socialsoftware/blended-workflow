package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcesmodel")
public class ResourceSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceSpecificationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SpecDTO[]> getSpecs() {
		log.debug("getSpecs");

		DesignInterface adi = DesignInterface.getInstance();

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
