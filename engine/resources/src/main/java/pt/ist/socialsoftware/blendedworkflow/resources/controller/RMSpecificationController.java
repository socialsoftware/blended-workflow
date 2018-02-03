package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;

@RestController
@RequestMapping(value = "/resources/")
public class RMSpecificationController {
	private static Logger log = LoggerFactory.getLogger(RMSpecificationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SpecDTO[]> getSpecs() {
		log.debug("getSpecs");

		DesignInterface adi = DesignInterface.getInstance();

		return null;
	}

}
