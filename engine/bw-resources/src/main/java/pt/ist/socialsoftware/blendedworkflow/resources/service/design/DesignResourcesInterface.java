package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Position;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprAnd;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprAnyone;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprCommonality;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatedByPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatedByPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatesToPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatesToPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHasCapability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHasPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHasRole;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHasUnit;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistory;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistoryExecuting;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistoryInformed;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPerson;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonDataObject;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonInTaskDuty;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprNot;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprOr;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportedByPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportedByPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportsToPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportsToPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprSharesPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprSharesRole;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprSharesUnit;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALPersonExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Unit;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprAndDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprAnyoneDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprDelegatedByPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprDelegatedByPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprDelegatesToPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprDelegatesToPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHasCapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHasPositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHasRoleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHasUnitDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHistoryExecutingDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprHistoryInformedDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprIsPersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprIsPersonDataObjectDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprIsPersonInTaskDutyDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprNotDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprOrDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprReportedByPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprReportedByPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprReportsToPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprReportsToPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprSharesPositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprSharesRoleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprSharesUnitDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.ResourceRelationDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.ResourceRuleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.UnitDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.xml.ResourceXmlExport;

public class DesignResourcesInterface extends DesignInterface {
	private static Logger log = LoggerFactory.getLogger(DesignResourcesInterface.class);

	private static DesignResourcesInterface instance;

	public static DesignResourcesInterface getInstance() {
		if (instance == null) {
			instance = new DesignResourcesInterface();
		}
		return instance;
	}

	protected DesignResourcesInterface() {
	}

	public ResourceModel getResourceModelFromSpecId(String specId) {
		return getSpecBySpecId(specId).getResourceModel();
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public ResourceModel createResourceModel(String specId) {
		Specification spec = getSpecBySpecId(specId);
		ResourceModel resourceModel = new ResourceModel();
		spec.setResourceModel(resourceModel);

		return resourceModel;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Capability createCapability(CapabilityDTO capabilityDTO) {
		Specification spec = getSpecBySpecId(capabilityDTO.getSpecId());

		Capability capability = spec.getResourceModel().addCapability(capabilityDTO.getName(),
				capabilityDTO.getDescription());

		return capability;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Role createRole(RoleDTO roleDTO) {
		Specification spec = getSpecBySpecId(roleDTO.getSpecId());

		Role role = spec.getResourceModel().addRole(roleDTO.getName(), roleDTO.getDescription());

		return role;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Unit createUnit(UnitDTO unitDTO) {
		Specification spec = getSpecBySpecId(unitDTO.getSpecId());

		Unit unit = spec.getResourceModel().addUnit(unitDTO.getName(), unitDTO.getDescription());

		return unit;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Position createPosition(PositionDTO positionDTO) {
		Specification spec = getSpecBySpecId(positionDTO.getSpecId());

		Position position = spec.getResourceModel().addPosition(positionDTO.getName(), positionDTO.getDescription(),
				positionDTO.getUnit());

		return position;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Position initPosition(PositionDTO positionDTO) {
		Specification spec = getSpecBySpecId(positionDTO.getSpecId());

		Position position = spec.getResourceModel().initPosition(positionDTO.getName(), positionDTO.getRoles(),
				positionDTO.getDelegateToRelations(), positionDTO.getReportsTo());

		return position;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Person createPerson(PersonDTO personDTO) {
		Specification spec = getSpecBySpecId(personDTO.getSpecId());

		Person person = spec.getResourceModel().addPerson(personDTO.getName(), personDTO.getDescription(),
				personDTO.getPositions(), personDTO.getCapabilities());

		return person;
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public void cleanResourceModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		spec.getResourceModel().clean();
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public Entity relationEntityIsPerson(ResourceRelationDTO resourceRelationDTO) {
		Specification spec = getSpecBySpecId(resourceRelationDTO.getSpecId());
		return spec.getResourceModel().addEntityIsPerson(resourceRelationDTO.getEntityName());
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public void addResourceRule(ResourceRuleDTO resourceRuleDTO) {
		switch (resourceRuleDTO.getType()) {
		case HAS_RESPONSIBLE:
			addResponsibleForRule(resourceRuleDTO.getSpecId(), resourceRuleDTO.getDataField(),
					resourceRuleDTO.getExpression());
			break;
		case INFORMS:
			addInformsForRule(resourceRuleDTO.getSpecId(), resourceRuleDTO.getDataField(),
					resourceRuleDTO.getExpression());
			break;
		default:
		}
	}

	private void addInformsForRule(String specId, String path, RALExpressionDTO expression) {
		RALExpression ralExpression = createRALExpression(specId, expression);
		Product product = getProduct(specId, path);
		product.setInforms(ralExpression);
	}

	private void addResponsibleForRule(String specId, String path, RALExpressionDTO expression) {
		RALExpression ralExpression = createRALExpression(specId, expression);
		Product product = getProduct(specId, path);
		product.setResponsibleFor(ralExpression);
	}

	public RALExpression createRALExpression(String specId, RALExpressionDTO ralExpressionDTO) {
		ResourceModel resourceModel = getResourceModelFromSpecId(specId);
		if (ralExpressionDTO instanceof RALExprAnyoneDTO) {

			// log.debug("RALExpression Type: ANYONE");
			return new RALExprAnyone(resourceModel);

		} else if (ralExpressionDTO instanceof RALExprIsPersonDTO) {
			// log.debug("RALExpression Type: IS PERSON");

			RALExprIsPersonDTO ralExprIsPersonDTO = (RALExprIsPersonDTO) ralExpressionDTO;

			return new RALExprIsPerson(resourceModel, resourceModel.getPerson(ralExprIsPersonDTO.getPerson()));

		} else if (ralExpressionDTO instanceof RALExprIsPersonDataObjectDTO) {

			// log.debug("RALExpression Type: IS PERSON IN DATA FIELD");

			RALExprIsPersonDataObjectDTO ralExprIsPersonDataObjectDTO = (RALExprIsPersonDataObjectDTO) ralExpressionDTO;

			if (ralExprIsPersonDataObjectDTO.getDataField() == null) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"Invalid data field name used in IS PERSON IN DATA FIELD expression");
			}

			Product product = getProduct(specId, ralExprIsPersonDataObjectDTO.getDataField());

			if (!(product instanceof Entity)) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"The data field in the IS PERSON IN DATA FIELD expression is not an attribute");
			}

			return new RALExprIsPersonDataObject(resourceModel, (Entity) product);

		} else if (ralExpressionDTO instanceof RALExprIsPersonInTaskDutyDTO) {

			// log.debug("RALExpression Type: IS PERSON IN TASK DUTY");

			RALExprIsPersonInTaskDutyDTO ralExprIsPersonInTaskDutyDTO = (RALExprIsPersonInTaskDutyDTO) ralExpressionDTO;

			if (ralExprIsPersonInTaskDutyDTO.getDataField() == null) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"Invalid data field name used in IS PERSON IN TASK DUTY expression");
			}

			RALExpression.TaskDutyType taskDuty = RALExpression.TaskDutyType
					.fromResourceRuleString(ralExprIsPersonInTaskDutyDTO.getTaskDuty().toString());

			Product product = getProduct(specId, ralExprIsPersonInTaskDutyDTO.getDataField());

			return new RALExprIsPersonInTaskDuty(resourceModel, taskDuty, product);
		} else if (ralExpressionDTO instanceof RALExprHasPositionDTO) {

			// log.debug("RALExpression Type: HAS POSITION");

			RALExprHasPositionDTO ralExprHasPositionDTO = (RALExprHasPositionDTO) ralExpressionDTO;

			return new RALExprHasPosition(resourceModel,
					resourceModel.getPosition(ralExprHasPositionDTO.getPosition()));

		} else if (ralExpressionDTO instanceof RALExprHasUnitDTO) {

			// log.debug("RALExpression Type: HAS UNIT");

			RALExprHasUnitDTO ralExprHasUnitDTO = (RALExprHasUnitDTO) ralExpressionDTO;

			return new RALExprHasUnit(resourceModel, resourceModel.getUnit(ralExprHasUnitDTO.getUnit()));

		} else if (ralExpressionDTO instanceof RALExprHasRoleDTO) {

			// log.debug("RALExpression Type: HAS ROLE");

			RALExprHasRoleDTO ralExprHasRoleDTO = (RALExprHasRoleDTO) ralExpressionDTO;

			RALExprHasRole ralExprHasRole = new RALExprHasRole(resourceModel,
					resourceModel.getRole(ralExprHasRoleDTO.getRole()));

			if (ralExprHasRoleDTO.getUnit() != null) {
				ralExprHasRole.setUnit(resourceModel.getUnit(ralExprHasRoleDTO.getUnit()));
			}

			return ralExprHasRole;

		} else if (ralExpressionDTO instanceof RALExprReportsToPersonPositionExprDTO) {

			// log.debug("RALExpression Type: REPORTS TO PERSON POSITION");

			RALExprReportsToPersonPositionExprDTO ralExprReportsToPersonPositionExprDTO = (RALExprReportsToPersonPositionExprDTO) ralExpressionDTO;

			RALExpression expression = createRALExpression(specId,
					ralExprReportsToPersonPositionExprDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprReportsToPersonPosition(resourceModel, (RALPersonExpression) expression,
					ralExprReportsToPersonPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportsToPositionExprDTO) {

			RALExprReportsToPositionExprDTO ralExprReportsToPositionExprDTO = (RALExprReportsToPositionExprDTO) ralExpressionDTO;

			return new RALExprReportsToPosition(resourceModel,
					resourceModel.getPosition(ralExprReportsToPositionExprDTO.getPosition()),
					ralExprReportsToPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportedByPersonPositionExprDTO) {

			// log.debug("RALExpression Type: REPORTS TO PERSON POSITION");

			RALExprReportedByPersonPositionExprDTO ralExprReportedByPersonPositionExprDTO = (RALExprReportedByPersonPositionExprDTO) ralExpressionDTO;

			RALExpression expression = createRALExpression(specId,
					ralExprReportedByPersonPositionExprDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprReportedByPersonPosition(resourceModel, (RALPersonExpression) expression,
					ralExprReportedByPersonPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprReportedByPositionExprDTO) {

			// log.debug("RALExpression Type: REPORTS TO POSITION");

			RALExprReportedByPositionExprDTO ralExprReportedByPositionExprDTO = (RALExprReportedByPositionExprDTO) ralExpressionDTO;

			return new RALExprReportedByPosition(resourceModel,
					resourceModel.getPosition(ralExprReportedByPositionExprDTO.getPosition()),
					ralExprReportedByPositionExprDTO.isDirectly());

		} else if (ralExpressionDTO instanceof RALExprDelegatedByPersonPositionExprDTO) {

			// log.debug("RALExpression Type: DELEGATED BY PERSON POSITION");

			RALExprDelegatedByPersonPositionExprDTO ralExprDelegatedByPersonPositionExprDTO = (RALExprDelegatedByPersonPositionExprDTO) ralExpressionDTO;

			RALExpression expression = createRALExpression(specId,
					ralExprDelegatedByPersonPositionExprDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprDelegatedByPersonPosition(resourceModel, (RALPersonExpression) expression);

		} else if (ralExpressionDTO instanceof RALExprDelegatedByPositionExprDTO) {

			// log.debug("RALExpression Type: DELEGATED BY POSITION");

			RALExprDelegatedByPositionExprDTO ralExprDelegatedByPositionExprDTO = (RALExprDelegatedByPositionExprDTO) ralExpressionDTO;

			return new RALExprDelegatedByPosition(resourceModel,
					resourceModel.getPosition(ralExprDelegatedByPositionExprDTO.getPosition()));

		} else if (ralExpressionDTO instanceof RALExprDelegatesToPersonPositionExprDTO) {

			// log.debug("RALExpression Type: DELEGATES TO PERSON POSITION");

			RALExprDelegatesToPersonPositionExprDTO ralExprDelegatesToPersonPositionExprDTO = (RALExprDelegatesToPersonPositionExprDTO) ralExpressionDTO;

			RALExpression expression = createRALExpression(specId,
					ralExprDelegatesToPersonPositionExprDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprDelegatesToPersonPosition(resourceModel, (RALPersonExpression) expression);

		} else if (ralExpressionDTO instanceof RALExprDelegatesToPositionExprDTO) {

			// log.debug("RALExpression Type: DELEGATES TO POSITION");

			RALExprDelegatesToPositionExprDTO ralExprDelegatesToPositionExprDTO = (RALExprDelegatesToPositionExprDTO) ralExpressionDTO;

			return new RALExprDelegatesToPosition(resourceModel,
					resourceModel.getPosition(ralExprDelegatesToPositionExprDTO.getPosition()));

		} else if (ralExpressionDTO instanceof RALExprNotDTO) {

			// log.debug("RALExpression Type: NOT");

			RALExprNotDTO ralExprNotDTO = (RALExprNotDTO) ralExpressionDTO;

			return new RALExprNot(resourceModel, createRALExpression(specId, ralExprNotDTO.getExpr()));

		} else if (ralExpressionDTO instanceof RALExprAndDTO) {

			// log.debug("RALExpression Type: AND");

			RALExprAndDTO ralExprAndDTO = (RALExprAndDTO) ralExpressionDTO;

			return new RALExprAnd(resourceModel, createRALExpression(specId, ralExprAndDTO.getLeftExpr()),
					createRALExpression(specId, ralExprAndDTO.getRightExpr()));

		} else if (ralExpressionDTO instanceof RALExprOrDTO) {

			// log.debug("RALExpression Type: OR");

			RALExprOrDTO ralExprOrDTO = (RALExprOrDTO) ralExpressionDTO;

			return new RALExprOr(resourceModel, createRALExpression(specId, ralExprOrDTO.getLeftExpr()),
					createRALExpression(specId, ralExprOrDTO.getRightExpr()));

		} else if (ralExpressionDTO instanceof RALExprHasCapabilityDTO) {

			// log.debug("RALExpression Type: HAS CAPABILITY");

			RALExprHasCapabilityDTO ralExprHasCapabilityDTO = (RALExprHasCapabilityDTO) ralExpressionDTO;

			return new RALExprHasCapability(resourceModel,
					resourceModel.getCapability(ralExprHasCapabilityDTO.getCapability()));

		} else if (ralExpressionDTO instanceof RALExprHistoryExecutingDTO) {

			// log.debug("RALExpression Type: HAS HISTORY EXECUTING");

			RALExprHistoryExecutingDTO ralExprHistoryExecutingDTO = (RALExprHistoryExecutingDTO) ralExpressionDTO;

			if (ralExprHistoryExecutingDTO.getDataField() == null) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"Invalid data field name used in HistoryExecuting expression");
			}

			Product product = getProduct(specId, ralExprHistoryExecutingDTO.getDataField());

			if (!(product instanceof Entity)) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"The data field in the HAS HISTORY EXECUTING expression is not an attribute");
			}

			RALExprHistory.Quantifier quantifier = RALExprHistory.Quantifier
					.fromString(ralExprHistoryExecutingDTO.getQuantifierDTO().toString());
			return new RALExprHistoryExecuting(resourceModel, quantifier, product);
		} else if (ralExpressionDTO instanceof RALExprHistoryInformedDTO) {

			// log.debug("RALExpression Type: HAS HISTORY INFORMED");

			RALExprHistoryInformedDTO ralExprHistoryInformedDTO = (RALExprHistoryInformedDTO) ralExpressionDTO;

			if (ralExprHistoryInformedDTO.getDataField() == null) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"Invalid data field name used in HistoryInformed expression");
			}

			Product product = getProduct(specId, ralExprHistoryInformedDTO.getDataField());

			if (!(product instanceof Entity)) {
				throw new RMException(RMErrorType.INVALID_DATA_FIELD,
						"The data field in the HAS HISTORY INFORMED expression is not an attribute");
			}

			RALExprHistory.Quantifier quantifier = RALExprHistory.Quantifier
					.fromString(ralExprHistoryInformedDTO.getQuantifierDTO().toString());

			return new RALExprHistoryInformed(resourceModel, quantifier, product);
		} else if (ralExpressionDTO instanceof RALExprSharesPositionDTO) {

			// log.debug("RALExpression Type: SHARES POSITION");

			RALExprSharesPositionDTO ralExprSharesPositionDTO = (RALExprSharesPositionDTO) ralExpressionDTO;

			RALExprCommonality.Amount amount = RALExprCommonality.Amount
					.fromString(ralExprSharesPositionDTO.getAmountDTO().toString());

			RALExpression expression = createRALExpression(specId, ralExprSharesPositionDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprSharesPosition(resourceModel, amount, (RALPersonExpression) expression);

		} else if (ralExpressionDTO instanceof RALExprSharesUnitDTO) {

			// log.debug("RALExpression Type: SHARES UNIT");

			RALExprSharesUnitDTO ralExprSharesUnitDTO = (RALExprSharesUnitDTO) ralExpressionDTO;

			RALExprCommonality.Amount amount = RALExprCommonality.Amount
					.fromString(ralExprSharesUnitDTO.getAmountDTO().toString());

			RALExpression expression = createRALExpression(specId, ralExprSharesUnitDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			return new RALExprSharesUnit(resourceModel, amount, (RALPersonExpression) expression);

		} else if (ralExpressionDTO instanceof RALExprSharesRoleDTO) {

			// log.debug("RALExpression Type: SHARES ROLE");

			RALExprSharesRoleDTO ralExprSharesRoleDTO = (RALExprSharesRoleDTO) ralExpressionDTO;

			RALExprCommonality.Amount amount = RALExprCommonality.Amount
					.fromString(ralExprSharesRoleDTO.getAmountDTO().toString());

			RALExpression expression = createRALExpression(specId, ralExprSharesRoleDTO.getPersonExpr());

			if (!(expression instanceof RALPersonExpression)) {
				throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
						"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
			}

			RALExprSharesRole ralExprSharesRole = new RALExprSharesRole(resourceModel, amount,
					(RALPersonExpression) expression);

			if (ralExprSharesRoleDTO.getUnit() != null) {
				ralExprSharesRole.setUnit(resourceModel.getUnit(ralExprSharesRoleDTO.getUnit()));
			}

			return ralExprSharesRole;

		} else {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_DTO_TYPE, "Invalid RALExpressionDTO type");
		}
	}

	@Atomic(mode = Atomic.TxMode.WRITE)
	public boolean generateEnrichedModels(String specId) {
		Specification spec = getSpecBySpecId(specId);

		Set<Entity> entitySet = spec.getDataModel().getEntitySet();

		generateEnrichedActivities(entitySet);

		generateEnrichedGoals(entitySet);

		return true;
	}

	private void generateEnrichedActivities(Set<Entity> entitySet) {
		for (Entity entity : entitySet) {
			Activity activity = entity.getDefEntityCondition().getActivityWithPostCondition();
			activity.setResponsibleFor(entity.getResponsibleFor());
			activity.setInforms(entity.getInforms());

			// Update entity's attributes
			for (Attribute attribute : entity.getAttributeSet()) {
				Activity attributeActivity = attribute.getDefAttributeCondition().getActivityWithPostCondition();
				attributeActivity.setResponsibleFor(attribute.getResponsibleFor());
				attributeActivity.setInforms(attribute.getInforms());
			}
		}
	}

	private void generateEnrichedGoals(Set<Entity> entitySet) {
		for (Entity entity : entitySet) {
			Goal goal = entity.getDefEntityCondition().getSuccessConditionGoal();
			if (goal != null) {
				goal.setResponsibleFor(entity.getResponsibleFor());
				goal.setInforms(entity.getInforms());
			}

			// Update entity's attributes
			for (Attribute attribute : entity.getAttributeSet()) {
				Goal attributeGoal = attribute.getDefAttributeCondition().getSuccessConditionGoal();
				if (attributeGoal != null) {
					attributeGoal.setResponsibleFor(attribute.getResponsibleFor());
					attributeGoal.setInforms(attribute.getInforms());
				}
			}
		}
	}

	@Override
	public String export(String specId) {
		Specification spec = getSpecBySpecId(specId);

		ResourceXmlExport exporter = new ResourceXmlExport();

		return exporter.export(spec);
		// logger.debug(exporter.export(spec));
	}
}
