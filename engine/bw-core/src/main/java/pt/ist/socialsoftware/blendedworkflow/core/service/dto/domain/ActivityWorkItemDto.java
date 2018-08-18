package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ActivityWorkItemDto extends WorkItemDto {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemDto.class);

	public static ActivityWorkItemDto createActivityWorkItemDto(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItemDto activityWorkItemDTO = new ActivityWorkItemDto();
		activityWorkItemDTO.setSpecId(workflowInstance.getSpecification().getSpecId());
		activityWorkItemDTO.setWorkflowInstanceName(workflowInstance.getName());
		activityWorkItemDTO.setDefinitionGroupSet(new HashSet<DefinitionGroupDto>());
		activityWorkItemDTO.setName(activity.getName());

		// get activity definition groups
		Map<Entity, List<DefProductCondition>> definitionGroupMap = activity.getPostConditionSet().stream()
				.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

		for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
			DefinitionGroupDto definitionGroup = new DefinitionGroupDto();
			activityWorkItemDTO.getDefinitionGroupSet().add(definitionGroup);

			// create all def products conditions associated with the entity
			definitionGroup.setDefEnt(
					definitionGroupMap.get(entityDefinitionGroup).stream().filter(DefEntityCondition.class::isInstance)
							.map(DefEntityCondition.class::cast).map(d -> d.getDTO()).findFirst().orElse(null));
			definitionGroup.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
					.map(d -> d.getDto()).collect(Collectors.toSet()));

			// create entity contexts
			Set<EntityContextDto> entityContextDTOs = new HashSet<EntityContextDto>();
			definitionGroup.setEntityContextSet(entityContextDTOs);
			Set<Entity> entityContexts = activity.getEntityContextForDefinitionGroup(entityDefinitionGroup);
			int counter = 0;
			// for each entity context of the product groups to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : activity.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					entityContextDTOs.add(EntityContextDto.createEntityContextDTO(counter++, activity, entityContext,
							mulCondition, workflowInstance));
				}

				// entity already exists, some of its attributes are being
				// defined)
				if (entityDefinitionGroup == entityContext) {
					entityContextDTOs.add(EntityContextDto.createEntityContextDTO(counter++, activity, entityContext,
							null, workflowInstance));
				}
			}

			// create inner relations
			Set<InnerRelationDto> innerRelationDTOs = new HashSet<InnerRelationDto>();
			definitionGroup.setInnerRelationSet(innerRelationDTOs);
			for (MulCondition mulCondition : activity.getInnerMulConditions(entityDefinitionGroup)) {
				innerRelationDTOs.add(InnerRelationDto.createInnerRelationDTO(entityDefinitionGroup, mulCondition));
			}

			// create definition group instances
			Set<DefinitionGroupInstanceDto> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDto>();
			definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
		}

		// FOLLOW THE DOMAIN MODEL APPROACH
		for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {

			// get entity contexts
			Set<Entity> entityContexts = activity.getEntityContextForDefinitionGroup(entityDefinitionGroup);

			// get entity to be defined
			Entity entityToDefine = definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
					.map(d -> d.getEntity()).findFirst().orElse(null);

			// get attributes to be defined
			Set<Attribute> attributesToDefine = definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
					.map(d -> d.getAttributeOfDef()).collect(Collectors.toSet());

			// get inner mulconditions to be defined
			Set<MulCondition> mulConditionsToDefine = activity.getInnerMulConditions(entityDefinitionGroup);

			// create entity instance dto to be defined
			EntityInstanceToDefineDto entityInstanceToDefineDto = null;

			if (entityToDefine != null) {
				// a new entity instance is going to be created
				entityInstanceToDefineDto = new EntityInstanceToDefineDto(entityToDefine);
			} else if (entityContexts.size() == 1) {
				// only attributes are going to be defined, it is necessary to define the
				// contexts
				Entity entity = entityContexts.stream().findFirst().get();
				entityInstanceToDefineDto = new EntityInstanceToDefineDto(entity,
						activity.getEntityInstanceContext(workflowInstance, entityDefinitionGroup));
				entityContexts.remove(entity);
			} else {
				throw new BWException(BWErrorType.CANNOT_GENERATE_GROUP_DEFINITION,
						"the group does not have a entity context neither an entity to define");
			}

			// add attributes to define
			for (Attribute attribute : attributesToDefine) {
				new AttributeInstanceToDefineDto(entityInstanceToDefineDto, attribute);
			}

			// fill entity instance dto with undef attributes
			entityInstanceToDefineDto.fillUndefAttributeInstances(entityDefinitionGroup);

			// add links to define

			// for each entity context of the product groups to be created except the one
			// associated to the entity to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : activity.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					new LinkToDefineDto(entityInstanceToDefineDto,
							activity.getEntityInstanceContext(workflowInstance, entityDefinitionGroup), mulCondition);
				}
			}

			// for each inner relation
			for (MulCondition mulCondition : activity.getInnerMulConditions(entityDefinitionGroup)) {
				new LinkToDefineDto(entityInstanceToDefineDto, mulCondition);
			}

			// fill entity instance dto undef links
			entityInstanceToDefineDto.fillUndefLinks(entityDefinitionGroup);

			activityWorkItemDTO.getEntityInstancesToDefine().add(entityInstanceToDefineDto);
		}

		return activityWorkItemDTO;
	}

	public ActivityWorkItem executeActivity(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		executeWorkItem(workflowInstance, activityWorkItem);

		return activityWorkItem;
	}

}
