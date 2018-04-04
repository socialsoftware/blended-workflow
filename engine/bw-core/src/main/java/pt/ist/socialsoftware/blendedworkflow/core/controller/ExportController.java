package pt.ist.socialsoftware.blendedworkflow.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/export")
public class ExportController {
	private static Logger logger = LoggerFactory.getLogger(ExportController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> exportSpecification(@PathVariable("specId") String specId) {
		logger.debug("exportSpecification specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.export(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
