package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.ExtractGoalDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Capability;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Position;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Unit;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.req.ResourcesMergeOperationDto;
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

		RALExpression ralExpression = ralExpressionDTO.getRALExpresion(resourceModel, this, specId);

        if (ralExpression == null) {
		    throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_DTO_TYPE, "Invalid RALExpressionDTO type");
        }

		return ralExpression;
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

	@Override
	public Activity mergeActivities(MergeOperationDto mergeOperationDto) {
		Specification spec = getSpecBySpecId(mergeOperationDto.getSpecId());
		Activity activityOne = getActivityByName(spec, mergeOperationDto.getNameOne());
		Activity activityTwo = getActivityByName(spec, mergeOperationDto.getNameTwo());

		RALExpression responsibleExpr1 = activityOne.getResponsibleFor();
		RALExpression responsibleExpr2 = activityTwo.getResponsibleFor();
		RALExpression informsExpr1 = activityOne.getInforms();
		RALExpression informsExpr2 = activityTwo.getInforms();

		spec.getResourceModel().cleanActivity(activityOne);
		spec.getResourceModel().cleanActivity(activityTwo);

		Activity activityMerged = super.mergeActivities(mergeOperationDto);
		if (mergeOperationDto instanceof ResourcesMergeOperationDto) {
			return spec.getResourceModel().mergeActivities(
					responsibleExpr1, responsibleExpr2,
					informsExpr1, informsExpr2,
					activityMerged, ((ResourcesMergeOperationDto) mergeOperationDto).getPolicy());
		} else {
			return activityMerged;
		}
	}

	@Override
	public Goal mergeGoals(MergeOperationDto mergeOperationDto) {
		Specification spec = getSpecBySpecId(mergeOperationDto.getSpecId());
		Goal goalOne = getGoalByName(spec.getSpecId(), mergeOperationDto.getNameOne());
		Goal goalTwo = getGoalByName(spec.getSpecId(), mergeOperationDto.getNameTwo());

		RALExpression responsibleExpr1 = goalOne.getResponsibleFor();
		RALExpression responsibleExpr2 = goalTwo.getResponsibleFor();
		RALExpression informsExpr1 = goalOne.getInforms();
		RALExpression informsExpr2 = goalTwo.getInforms();

		spec.getResourceModel().cleanGoal(goalOne);
		spec.getResourceModel().cleanGoal(goalTwo);

		Goal goalMerged = super.mergeGoals(mergeOperationDto);
		if (mergeOperationDto instanceof ResourcesMergeOperationDto) {
			return spec.getResourceModel().mergeGoals(
					responsibleExpr1, responsibleExpr2,
					informsExpr1, informsExpr2,
					goalMerged, ((ResourcesMergeOperationDto) mergeOperationDto).getPolicy());
		} else {
			return goalMerged;
		}
	}

	@Override
	public Activity extractActivity(String specId, String newActivityName, String description, String sourceActivityName, Set<DefPathConditionDTO> successCondition) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, sourceActivityName);

		RALExpression responsibleFor = activity.getResponsibleFor();
		RALExpression informedAbout = activity.getInforms();

		activity.setResponsibleFor(null);
		activity.setInforms(null);

		Activity newActivity = super.extractActivity(specId, newActivityName, description, sourceActivityName, successCondition);

		Activity fromActivity = spec.getActivityModel().getActivitySet().stream()
				.filter(activity1 -> activity1.getName().equals(sourceActivityName))
				.findFirst()
				.orElseThrow(() -> new RMException(RMErrorType.ACTIVITY_NOT_FOUND_IN_EXTRACT));

		fromActivity.setResponsibleFor(responsibleFor);
		fromActivity.setInforms(informedAbout);

		newActivity.setResponsibleFor(responsibleFor);
		newActivity.setInforms(informedAbout);

		return newActivity;
	}

	@Override
	public Goal extractGoal(String specId, ExtractGoalDto extractGoalDto) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, extractGoalDto.getSourceGoalName());

		Goal newGoal = super.extractGoal(specId, extractGoalDto);

		newGoal.setResponsibleFor(goal.getResponsibleFor());
		newGoal.setInforms(goal.getInforms());

		return newGoal;
	}
}
