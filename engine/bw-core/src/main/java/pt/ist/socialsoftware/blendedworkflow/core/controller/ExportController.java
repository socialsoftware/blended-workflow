package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;

@RestController
@RequestMapping(value = "/specs/{specId}/export")
public class ExportController {
	private static Logger logger = LoggerFactory.getLogger(ExportController.class);

	@Inject
	private ModulesFactory factory;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> exportSpecification(HttpServletResponse response,
			@PathVariable("specId") String specId) {
		logger.debug("exportSpecification specId:{}", specId);

		DesignInterface adi = this.factory.createDesignInterface();

		String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

		File directory = new File(testModelsDirectory);
		String filename = specId + ".xml";
		File file = new File(directory, filename);

		try (PrintWriter out = new PrintWriter(file)) {
			out.println(adi.export(specId));
		} catch (FileNotFoundException e) {
			throw new BWException(BWErrorType.FILE_ERROR, "export specification");
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
