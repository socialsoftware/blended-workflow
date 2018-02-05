package pt.ist.socialsoftware.blendedworkflow.resources.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Position;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Unit;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.UnitDTO;

@RestController
@RequestMapping(value = "/specs/{specId}/resourcemodel")
public class ResourceSpecificationController {
	private static Logger log = LoggerFactory.getLogger(ResourceSpecificationController.class);

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> createResourceModel(@PathVariable("specId") String specId) {
		log.debug("CreateResourceModel: {}", specId);

		DesignInterface designer = DesignInterface.getInstance();

		designer.createResourceModel(specId);

		return new ResponseEntity<>(new Boolean(true), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/capabilities", method = RequestMethod.POST)
	public ResponseEntity<CapabilityDTO> createCapability(@PathVariable("specId") String specId,
														  @RequestBody CapabilityDTO capabilityDTO) {
		log.debug("CreateCapability: {}, {}, {}", specId, capabilityDTO.getName(), capabilityDTO.getDescription());

		DesignInterface designer = DesignInterface.getInstance();

		Capability capability = designer.createCapability(capabilityDTO);

		return new ResponseEntity<>(capability.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<RoleDTO> createRole(@PathVariable("specId") String specId,
														  @RequestBody RoleDTO roleDTO) {
		log.debug("CreateRole: {}, {}, {}", specId, roleDTO.getName(), roleDTO.getDescription());

		DesignInterface designer = DesignInterface.getInstance();

		Role role = designer.createRole(roleDTO);

		return new ResponseEntity<>(role.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/units", method = RequestMethod.POST)
	public ResponseEntity<UnitDTO> createUnit(@PathVariable("specId") String specId,
											  @RequestBody UnitDTO unitDTO) {
		log.debug("CreateUnit: {}, {}, {}", specId, unitDTO.getName(), unitDTO.getDescription());

		DesignInterface designer = DesignInterface.getInstance();

		Unit unit = designer.createUnit(unitDTO);

		return new ResponseEntity<>(unit.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/positions", method = RequestMethod.POST)
	public ResponseEntity<PositionDTO> createPosition(@PathVariable("specId") String specId,
											  @RequestBody PositionDTO positionDTO) {
		log.debug("CreatePosition: {}, {}", specId, positionDTO.getName());

		DesignInterface designer = DesignInterface.getInstance();

		Position position= designer.createPosition(positionDTO);

		return new ResponseEntity<>(position.getDTO(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	public ResponseEntity<Boolean> cleanResourceModel(@PathVariable("specId") String specId) {
		log.debug("cleanResourceModel specId:{}", specId);

		DesignInterface designer = DesignInterface.getInstance();

		designer.cleanResourceModel(specId);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
