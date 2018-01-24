package pt.ist.socialsoftware.blendedworkflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.service.design.ResourceInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.PersonDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcemodel")
public class ResourceModelController {
	private static Logger log = LoggerFactory.getLogger(ResourceModelController.class);

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<PersonDTO> createPerson(@PathVariable("specId") String specId,
														@RequestBody PersonDTO personDTO) {
		log.debug("createPerson entityExtId:{}, spec: {}, name:{}, bodytext:{}", personDTO.getExtId(), personDTO.getSpecId(), personDTO.getName(),
				personDTO.getBody());

		ResourceInterface adi = ResourceInterface.getInstance();

		// FIXME: Person person = adi.createPerson(attDTO);

		return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
	}
}
