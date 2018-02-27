package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
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

	public ResourceModel getResourceModelFromSpecId(String specId) {
		return workflowDesigner.getSpecBySpecId(specId).getResourceModel();
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

	@Atomic(mode = Atomic.TxMode.WRITE)
    public Entity relationEntityIsPerson(ResourceRelationDTO resourceRelationDTO) {
		Specification spec = workflowDesigner.getSpecBySpecId(resourceRelationDTO.getSpecId());
		return spec.getResourceModel().addEntityIsPerson(resourceRelationDTO.getEntityName());
    }

	@Atomic(mode = Atomic.TxMode.WRITE)
	public void addResourceRule(ResourceRuleDTO resourceRuleDTO) {
		switch (resourceRuleDTO.getType()) {
			case HAS_RESPONSIBLE:
				addResponsibleForRule(resourceRuleDTO.getSpecId(), resourceRuleDTO.getDataField(), resourceRuleDTO.getExpression());
				break;
			case INFORMS:
				addInformsForRule(resourceRuleDTO.getSpecId(), resourceRuleDTO.getDataField(), resourceRuleDTO.getExpression());
				break;
			default:
		}
	}

	private void addInformsForRule(String specId, String path, RALExpressionDTO expression) {
		RALExpression ralExpression = createRALExpression(specId, expression);
		Product product = workflowDesigner.getProduct(specId, path);
		product.setInforms(ralExpression);
	}

	private void addResponsibleForRule(String specId, String path, RALExpressionDTO expression) {
		RALExpression ralExpression = createRALExpression(specId, expression);
		Product product = workflowDesigner.getProduct(specId, path);
		product.setResponsibleFor(ralExpression);
	}

	private RALExpression createRALExpression(String specId, RALExpressionDTO ralExpressionDTO) {
		ResourceModel resourceModel = getResourceModelFromSpecId(specId);
		if (ralExpressionDTO instanceof RALExprAnyoneDTO) {

			log.debug("RALExpression Type: ANYONE");
			return new RALExprAnyone(resourceModel);

		} else if (ralExpressionDTO instanceof RALExprIsPersonDTO) {
			log.debug("RALExpression Type: IS PERSON");

			RALExprIsPersonDTO ralExprIsPersonDTO = (RALExprIsPersonDTO) ralExpressionDTO;

			return new RALExprIsPerson(
				resourceModel,
				resourceModel.getPerson(ralExprIsPersonDTO.getPerson())
			);

		} else if (ralExpressionDTO instanceof RALExprIsPersonDataObjectDTO) {

			log.debug("RALExpression Type: IS PERSON IN DATA FIELD");

			RALExprIsPersonDataObjectDTO ralExprIsPersonDataObjectDTO = (RALExprIsPersonDataObjectDTO) ralExpressionDTO;

			Product product = workflowDesigner.getProduct(specId, ralExprIsPersonDataObjectDTO.getDataField());

			if (!(product instanceof Attribute)) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD, "The data field in the IS PERSON IN DATA FIELD expression is not an attribute");
			}

			return new RALExprIsPersonDataObject(
				resourceModel,
				(Attribute) product
			);

		} else if (ralExpressionDTO instanceof RALExprIsPersonInTaskDutyDTO) {

			log.debug("RALExpression Type: IS PERSON IN TASK DUTY");

			RALExprIsPersonInTaskDutyDTO ralExprIsPersonInTaskDutyDTO = (RALExprIsPersonInTaskDutyDTO) ralExpressionDTO;

			RALExpression.TaskDutyType taskDuty = RALExpression.TaskDutyType.fromResourceRuleString(ralExprIsPersonInTaskDutyDTO.getTaskDuty().toString());

			Product product = workflowDesigner.getProduct(specId, ralExprIsPersonInTaskDutyDTO.getDataField());

			return new RALExprIsPersonInTaskDuty(resourceModel, taskDuty, product);
		} else if (ralExpressionDTO instanceof RALExprHasPositionDTO) {

			log.debug("RALExpression Type: HAS POSITION");

			RALExprHasPositionDTO ralExprHasPositionDTO = (RALExprHasPositionDTO) ralExpressionDTO;

			return new RALExprHasPosition(
					resourceModel,
					resourceModel.getPosition(ralExprHasPositionDTO.getPosition())
			);
		} else {

			//throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_DTO_TYPE, "Invalid RALExpressionDTO type");
			return null;
		}
	}
}
