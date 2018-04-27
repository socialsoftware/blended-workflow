package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Position;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Unit;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.UnitDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.utils.ResourcesFactory;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcemodel")
public class ResourceModelSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceModelSpecificationController.class);

	@Inject
	private ResourcesFactory factory;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> createResourceModel(@PathVariable("specId") String specId) {
		log.debug("CreateResourceModel: {}", specId);

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		designer.createResourceModel(specId);

		return new ResponseEntity<>(new Boolean(true), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/capabilities", method = RequestMethod.POST)
	public ResponseEntity<CapabilityDTO> createCapability(@PathVariable("specId") String specId,
			@RequestBody CapabilityDTO capabilityDTO) {
		log.debug("CreateCapability: {}, {}, {}", specId, capabilityDTO.getName(), capabilityDTO.getDescription());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Capability capability = designer.createCapability(capabilityDTO);

		return new ResponseEntity<>(capability.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<RoleDTO> createRole(@PathVariable("specId") String specId, @RequestBody RoleDTO roleDTO) {
		log.debug("CreateRole: {}, {}, {}", specId, roleDTO.getName(), roleDTO.getDescription());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Role role = designer.createRole(roleDTO);

		return new ResponseEntity<>(role.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/units", method = RequestMethod.POST)
	public ResponseEntity<UnitDTO> createUnit(@PathVariable("specId") String specId, @RequestBody UnitDTO unitDTO) {
		log.debug("CreateUnit: {}, {}, {}", specId, unitDTO.getName(), unitDTO.getDescription());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Unit unit = designer.createUnit(unitDTO);

		return new ResponseEntity<>(unit.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/positions", method = RequestMethod.POST)
	public ResponseEntity<PositionDTO> createPosition(@PathVariable("specId") String specId,
			@RequestBody PositionDTO positionDTO) {
		log.debug("CreatePosition: {}, {}", specId, positionDTO.getName());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Position position = designer.createPosition(positionDTO);

		return new ResponseEntity<>(position.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/positions/init", method = RequestMethod.POST)
	public ResponseEntity<PositionDTO> initPosition(@PathVariable("specId") String specId,
			@RequestBody PositionDTO positionDTO) {
		log.debug("InitPosition: {}, {}", specId, positionDTO.getName());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Position position = designer.initPosition(positionDTO);

		return new ResponseEntity<>(position.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<PersonDTO> createPersons(@PathVariable("specId") String specId,
			@RequestBody PersonDTO personDTO) {
		log.debug("CreatePerson: {}, {}", specId, personDTO.getName());

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		Person person = designer.createPerson(personDTO);

		return new ResponseEntity<>(person.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	public ResponseEntity<Boolean> cleanResourceModel(@PathVariable("specId") String specId) {
		log.debug("cleanResourceModel specId:{}", specId);

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		designer.cleanResourceModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public ResponseEntity<Boolean> generateModels(@PathVariable("specId") String specId) {
		log.debug("GenerateModels: {}", specId);

		DesignResourcesInterface designer = this.factory.createDesignInterface();

		boolean result = designer.generateEnrichedModels(specId);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
