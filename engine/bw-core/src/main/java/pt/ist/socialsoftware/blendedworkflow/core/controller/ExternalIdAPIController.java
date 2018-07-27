package pt.ist.socialsoftware.blendedworkflow.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

@RestController
public class ExternalIdAPIController {
	private static Logger log = LoggerFactory.getLogger(DataModelController.class);

	@GetMapping(value = "/entityinstance/{externalId}")
	public ResponseEntity<EntityInstanceDto> getEntityInstanceByExternalId(
			@PathVariable("externalId") String externalId) {
		log.debug("getEntityInstanceByExternalId externalId:{}", externalId);

		EntityInstance entityInstance = FenixFramework.getDomainObject(externalId);

		return new ResponseEntity<EntityInstanceDto>(new EntityInstanceDto(entityInstance, Depth.DEEP), HttpStatus.OK);
	}

}
