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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

public class ActivityWorkItemDto extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemDto.class);

	public static ActivityWorkItemDto createActivityWorkItemDTO(WorkflowInstance workflowInstance, Activity activity) {
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
			DefProductConditionSetDto defProductConditionSet = new DefProductConditionSetDto();
			definitionGroup.setDefProductConditionSet(defProductConditionSet);
			defProductConditionSet.setDefEnts(
					definitionGroupMap.get(entityDefinitionGroup).stream().filter(DefEntityCondition.class::isInstance)
							.map(DefEntityCondition.class::cast).map(d -> d.getDTO()).collect(Collectors.toSet()));
			defProductConditionSet.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
					.map(d -> d.getDto()).collect(Collectors.toSet()));

			// create entity contexts
			Set<EntityContextDto> entityContextDTOs = new HashSet<EntityContextDto>();
			definitionGroup.setEntityContextSet(entityContextDTOs);
			Set<Entity> entityContexts = activity.getEntityContext(entityDefinitionGroup);
			// for each entity context of the product groups to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : activity.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					entityContextDTOs.add(EntityContextDto.createEntityContextDTO(activity, entityContext, mulCondition,
							workflowInstance));
				}

				// entity already exists, some of its attributes are being
				// defined)
				if (entityDefinitionGroup == entityContext) {
					entityContextDTOs.add(
							EntityContextDto.createEntityContextDTO(activity, entityContext, null, workflowInstance));
				}
			}

			// create inner relations
			Set<InnerRelationDto> innerRelationDTOs = new HashSet<InnerRelationDto>();
			definitionGroup.setInnerRelationSet(innerRelationDTOs);
			for (MulCondition mulCondition : activity.getInnerMulConditions(entityDefinitionGroup)) {
				innerRelationDTOs.add(InnerRelationDto.createInnerRelationDTO(entityDefinitionGroup, mulCondition));
			}

			// create definition group instances
			Set<DefinitionGroupInstanceDTO> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDTO>();
			definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
		}
		return activityWorkItemDTO;
	}

	public ActivityWorkItem executeActivity(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		executeWorkItem(workflowInstance, activityWorkItem);

		return activityWorkItem;
	}

}
