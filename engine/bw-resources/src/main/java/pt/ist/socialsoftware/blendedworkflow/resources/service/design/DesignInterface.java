package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

			if (!(product instanceof Entity)) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD, "The data field in the IS PERSON IN DATA FIELD expression is not an attribute");
			}

			return new RALExprIsPersonDataObject(
				resourceModel,
				(Entity) product
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

		} else if (ralExpressionDTO instanceof RALExprHasUnitDTO) {

			log.debug("RALExpression Type: HAS UNIT");

			RALExprHasUnitDTO ralExprHasUnitDTO = (RALExprHasUnitDTO) ralExpressionDTO;

			return new RALExprHasUnit(
					resourceModel,
					resourceModel.getUnit(ralExprHasUnitDTO.getUnit())
			);

		} else if (ralExpressionDTO instanceof RALExprHasRoleDTO) {

			log.debug("RALExpression Type: HAS ROLE");

			RALExprHasRoleDTO ralExprHasRoleDTO = (RALExprHasRoleDTO) ralExpressionDTO;

			RALExprHasRole ralExprHasRole = new RALExprHasRole(
					resourceModel,
					resourceModel.getRole(ralExprHasRoleDTO.getRole())
			);

			if (ralExprHasRoleDTO.getUnit() != null) {
				ralExprHasRole.setUnit(resourceModel.getUnit(ralExprHasRoleDTO.getUnit()));
			}

			return ralExprHasRole;

		} else if (ralExpressionDTO instanceof RALExprReportsToPersonPositionExprDTO) {

			log.debug("RALExpression Type: REPORTS TO PERSON POSITION");

			RALExprReportsToPersonPositionExprDTO ralExprReportsToPersonPositionExprDTO =
					(RALExprReportsToPersonPositionExprDTO) ralExpressionDTO;

			// FIXME: Bad code
			RALPersonExpression personExpression = (RALPersonExpression) createRALExpression(
					specId,
					ralExprReportsToPersonPositionExprDTO.getPersonExpr());

			return new RALExprReportsToPersonPosition(
					resourceModel,
					personExpression,
					ralExprReportsToPersonPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportsToPositionExprDTO) {

			log.debug("RALExpression Type: REPORTS TO POSITION");

			RALExprReportsToPositionExprDTO ralExprReportsToPositionExprDTO =
					(RALExprReportsToPositionExprDTO) ralExpressionDTO;

			return new RALExprReportsToPosition(
					resourceModel,
					resourceModel.getPosition(ralExprReportsToPositionExprDTO.getPosition()),
					ralExprReportsToPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportedByPersonPositionExprDTO) {

			log.debug("RALExpression Type: REPORTS TO PERSON POSITION");

			RALExprReportedByPersonPositionExprDTO ralExprReportedByPersonPositionExprDTO =
					(RALExprReportedByPersonPositionExprDTO) ralExpressionDTO;

			// FIXME: Bad code
			RALPersonExpression personExpression = (RALPersonExpression) createRALExpression(
					specId,
					ralExprReportedByPersonPositionExprDTO.getPersonExpr());

			return new RALExprReportedByPersonPosition(
					resourceModel,
					personExpression,
					ralExprReportedByPersonPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportedByPositionExprDTO) {

			log.debug("RALExpression Type: REPORTS TO POSITION");

			RALExprReportedByPositionExprDTO ralExprReportedByPositionExprDTO =
					(RALExprReportedByPositionExprDTO) ralExpressionDTO;

			return new RALExprReportedByPosition(
					resourceModel,
					resourceModel.getPosition(ralExprReportedByPositionExprDTO.getPosition()),
					ralExprReportedByPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprDelegatedByPersonPositionExprDTO) {

			log.debug("RALExpression Type: DELEGATED BY PERSON POSITION");

			RALExprDelegatedByPersonPositionExprDTO ralExprDelegatedByPersonPositionExprDTO =
					(RALExprDelegatedByPersonPositionExprDTO) ralExpressionDTO;

			// FIXME: Bad code
			RALPersonExpression personExpression = (RALPersonExpression) createRALExpression(
					specId,
					ralExprDelegatedByPersonPositionExprDTO.getPersonExpr());

			return new RALExprDelegatedByPersonPosition(
					resourceModel,
					personExpression);

		} else if (ralExpressionDTO instanceof RALExprDelegatedByPositionExprDTO) {

			log.debug("RALExpression Type: DELEGATED BY POSITION");

			RALExprDelegatedByPositionExprDTO ralExprDelegatedByPositionExprDTO =
					(RALExprDelegatedByPositionExprDTO) ralExpressionDTO;

			return new RALExprDelegatedByPosition(
					resourceModel,
					resourceModel.getPosition(ralExprDelegatedByPositionExprDTO.getPosition()));

		} else if (ralExpressionDTO instanceof RALExprDelegatesToPersonPositionExprDTO) {

			log.debug("RALExpression Type: DELEGATES TO PERSON POSITION");

			RALExprDelegatesToPersonPositionExprDTO ralExprDelegatesToPersonPositionExprDTO =
					(RALExprDelegatesToPersonPositionExprDTO) ralExpressionDTO;

			// FIXME: Bad code
			RALPersonExpression personExpression = (RALPersonExpression) createRALExpression(
					specId,
					ralExprDelegatesToPersonPositionExprDTO.getPersonExpr());

			return new RALExprDelegatesToPersonPosition(
					resourceModel,
					personExpression);

		} else if (ralExpressionDTO instanceof RALExprDelegatesToPositionExprDTO) {

			log.debug("RALExpression Type: DELEGATES TO POSITION");

			RALExprDelegatesToPositionExprDTO ralExprDelegatesToPositionExprDTO =
					(RALExprDelegatesToPositionExprDTO) ralExpressionDTO;

			return new RALExprDelegatesToPosition(
					resourceModel,
					resourceModel.getPosition(ralExprDelegatesToPositionExprDTO.getPosition()));

		} else if (ralExpressionDTO instanceof RALExprNotDTO) {

			log.debug("RALExpression Type: NOT");

			RALExprNotDTO ralExprNotDTO = (RALExprNotDTO) ralExpressionDTO;

			return new RALExprNot(
					resourceModel,
					createRALExpression(specId, ralExprNotDTO.getExpr()));

		} else {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_DTO_TYPE, "Invalid RALExpressionDTO type");
		}
	}
}
