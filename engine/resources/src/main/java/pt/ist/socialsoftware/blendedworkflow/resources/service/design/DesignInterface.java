package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.*;

public class DesignInterface {
	private static Logger log = LoggerFactory.getLogger(DesignInterface.class);

	private static DesignInterface instance;

	public static DesignInterface getInstance() {
		if (instance == null) {
			instance = new DesignInterface();
		}
		return instance;
	}

	private pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface workflowDesigner;

	private DesignInterface() {
		workflowDesigner = pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance();
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public ResourceModel createResourceModel(String specId) {
		Specification spec = workflowDesigner.getSpecBySpecId(specId);
		ResourceModel resourceModel = new ResourceModel();
		spec.setResourceModel(resourceModel);

		return resourceModel;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Capability createCapability(CapabilityDTO capabilityDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(capabilityDTO.getSpecId());

		Capability capability = spec.getResourceModel().addCapability(capabilityDTO.getName(), capabilityDTO.getDescription());

		return capability;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Role createRole(RoleDTO roleDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(roleDTO.getSpecId());

		Role role = spec.getResourceModel().addRole(roleDTO.getName(), roleDTO.getDescription());

		return role;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Unit createUnit(UnitDTO unitDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(unitDTO.getSpecId());

		Unit unit = spec.getResourceModel().addUnit(unitDTO.getName(), unitDTO.getDescription());

		return unit;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Position createPosition(PositionDTO positionDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(positionDTO.getSpecId());

		Position position = spec.getResourceModel().addPosition(positionDTO.getName(),positionDTO.getDescription(), positionDTO.getUnit());

		return position;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Position initPosition(PositionDTO positionDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(positionDTO.getSpecId());

		Position position = spec.getResourceModel().initPosition(positionDTO.getName(),positionDTO.getRoles(), positionDTO.getDelegateToRelations(), positionDTO.getReportsTo());

		return position;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Person createPerson(PersonDTO personDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(personDTO.getSpecId());

		Person person = spec.getResourceModel().addPerson(personDTO.getName(),personDTO.getDescription(), personDTO.getPositions(), personDTO.getCapabilities());

		return person;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public void cleanResourceModel(String specId) {
		Specification spec = workflowDesigner.getSpecBySpecId(specId);

		spec.getResourceModel().clean();
	}

	//FIXME: Fix return
	@Atomic(mode = Atomic.TxMode.WRITE)
    public void relationEntityIsPerson(ResourceRelationDTO resourceRelationDTO) {
		Entity entity = workflowDesigner.getEntityByName(resourceRelationDTO.getSpecId(), resourceRelationDTO.getEntityName());
    }

	@Atomic(mode = Atomic.TxMode.WRITE)
	public void resourceRule(ResourceRuleDTO resourceRuleDTO) {

	}
}
