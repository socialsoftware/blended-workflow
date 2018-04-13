package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.PropertiesManager;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@RestController
@RequestMapping(value = "/specs/{specId}/export/resources")
public class ExportResourcesController {
	private static Logger logger = LoggerFactory.getLogger(ExportResourcesController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> exportResourceSpecification(HttpServletResponse response,
			@PathVariable("specId") String specId) {
		logger.debug("exportSpecification specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		String testModelsDirectory = PropertiesManager.getProperties().getProperty("test.models.dir");

		File directory = new File(testModelsDirectory);
		String filename = specId + ".xml";
		File file = new File(directory, filename);

		try (PrintWriter out = new PrintWriter(file)) {
			out.println(adi.export(specId));
		} catch (FileNotFoundException e) {
			throw new RMException(RMErrorType.FILE_ERROR, "export specification");
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
