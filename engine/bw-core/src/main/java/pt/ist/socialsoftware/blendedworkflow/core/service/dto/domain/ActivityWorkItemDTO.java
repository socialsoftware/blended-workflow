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

public class ActivityWorkItemDTO extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemDTO.class);

	public static ActivityWorkItemDTO createActivityWorkItemDTO(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItemDTO activityWorkItemDTO = new ActivityWorkItemDTO();
		activityWorkItemDTO.setSpecId(workflowInstance.getSpecification().getSpecId());
		activityWorkItemDTO.setWorkflowInstanceName(workflowInstance.getName());
		activityWorkItemDTO.setDefinitionGroupSet(new HashSet<DefinitionGroupDTO>());
		activityWorkItemDTO.setName(activity.getName());

		// get activity definition groups
		Map<Entity, List<DefProductCondition>> definitionGroupMap = activity.getPostConditionSet().stream()
				.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

		for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
			DefinitionGroupDTO definitionGroup = new DefinitionGroupDTO();
			activityWorkItemDTO.getDefinitionGroupSet().add(definitionGroup);

			// create all def products conditions associated with the entity
			DefProductConditionSetDTO defProductConditionSet = new DefProductConditionSetDTO();
			definitionGroup.setDefProductConditionSet(defProductConditionSet);
			defProductConditionSet.setDefEnts(
					definitionGroupMap.get(entityDefinitionGroup).stream().filter(DefEntityCondition.class::isInstance)
							.map(DefEntityCondition.class::cast).map(d -> d.getDTO()).collect(Collectors.toSet()));
			defProductConditionSet.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
					.map(d -> d.getDTO()).collect(Collectors.toSet()));

			// create entity contexts
			Set<EntityContextDTO> entityContextDTOs = new HashSet<EntityContextDTO>();
			definitionGroup.setEntityContextSet(entityContextDTOs);
			Set<Entity> entityContexts = activity.getEntityContext(entityDefinitionGroup);
			// for each entity context of the product groups to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : activity.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					entityContextDTOs.add(EntityContextDTO.createEntityContextDTO(activity, entityContext, mulCondition,
							workflowInstance));
				}

				// entity already exists, some of its attributes are being
				// defined)
				if (entityDefinitionGroup == entityContext) {
					entityContextDTOs.add(
							EntityContextDTO.createEntityContextDTO(activity, entityContext, null, workflowInstance));
				}
			}

			// create inner relations
			Set<InnerRelationDTO> innerRelationDTOs = new HashSet<InnerRelationDTO>();
			definitionGroup.setInnerRelationSet(innerRelationDTOs);
			for (MulCondition mulCondition : activity.getInnerMulConditions(entityDefinitionGroup)) {
				innerRelationDTOs.add(InnerRelationDTO.createInnerRelationDTO(entityDefinitionGroup, mulCondition));
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
