package pt.ist.socialsoftware.blendedworkflow.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;

@RestController
@RequestMapping(value = "/specs")
public class SpecificationController {
	private static Logger log = LoggerFactory.getLogger(SpecificationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SpecDTO[]> getSpecs() {
		log.debug("getSpecs");

		DesignInterface adi = DesignInterface.getInstance();

		SpecDTO[] specs = adi.getSpecs().stream().map(s -> s.getDTO())
				.sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).toArray(size -> new SpecDTO[size]);

		return new ResponseEntity<>(specs, HttpStatus.OK);
	}

	@RequestMapping(value = "/{specId}", method = RequestMethod.GET)
	public ResponseEntity<SpecDTO> getSpecBySpecId(@PathVariable("specId") String specId) {
		log.debug("getSpecBySpecId sepcId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		Specification spec = adi.getSpecBySpecId(specId);

		return new ResponseEntity<>(spec.getDTO(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SpecDTO> createSpec(@RequestBody SpecDTO specDTO) {
		log.debug("createSpec specId:{}, name:{}", specDTO.getSpecId(), specDTO.getName());
		DesignInterface adi = DesignInterface.getInstance();

		Specification spec = adi.createSpecification(specDTO);

		return new ResponseEntity<>(spec.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{specId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSpecification(@PathVariable("specId") String specId) {
		log.debug("deleteSpecification specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.deleteSpecification(specId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{specId}/print", method = RequestMethod.GET)
	public ResponseEntity<Boolean> printSpecModels(@PathVariable("specId") String specId) {
		log.debug("printSpecModels specId:{}", specId);

		DesignInterface adi = DesignInterface.getInstance();

		adi.printSpecificationModels(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
