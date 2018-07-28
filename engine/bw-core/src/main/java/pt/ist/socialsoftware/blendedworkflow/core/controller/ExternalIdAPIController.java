package pt.ist.socialsoftware.blendedworkflow.core.controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
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

	@GetMapping(value = "/entityinstance/{entityInstanceExtId}/dependence/{dependenceExtId}")
	public ResponseEntity<EntityInstanceDto[]> getEntityInstancesForDependence(
			@PathVariable("entityInstanceExtId") String entityInstanceExtId,
			@PathVariable("dependenceExtId") String dependenceExtId) {
		log.debug("getEntityInstancesForDependence entityInstanceExtId:{}, dependenceExtId:{}", entityInstanceExtId,
				dependenceExtId);

		EntityInstance entityInstance = FenixFramework.getDomainObject(entityInstanceExtId);

		Dependence dependence = FenixFramework.getDomainObject(dependenceExtId);

		Set<ProductInstance> productInstances;
		try {
			productInstances = entityInstance.getProductInstancesByPath(dependence.getPath().getValue());
		} catch (BWException bwe) {
			productInstances = new HashSet<>();
		}

		EntityInstanceDto[] results = productInstances.stream()
				.map(pi -> new EntityInstanceDto(pi.getEntityInstance(), Depth.DEEP))
				.sorted(Comparator.comparing(EntityInstanceDto::getId)).toArray(EntityInstanceDto[]::new);

		return new ResponseEntity<EntityInstanceDto[]>(results, HttpStatus.OK);
	}

}
