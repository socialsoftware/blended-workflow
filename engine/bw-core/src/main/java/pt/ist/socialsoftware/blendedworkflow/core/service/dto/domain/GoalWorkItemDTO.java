package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

public class GoalWorkItemDto extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemDto.class);

	public static GoalWorkItemDto createGoalWorkItemDTO(WorkflowInstance workflowInstance, Goal goal) {
		GoalWorkItemDto goalWorkItemDto = new GoalWorkItemDto();
		goalWorkItemDto.setSpecId(workflowInstance.getSpecification().getSpecId());
		goalWorkItemDto.setWorkflowInstanceName(workflowInstance.getName());
		goalWorkItemDto.setDefinitionGroupSet(new HashSet<DefinitionGroupDto>());
		goalWorkItemDto.setName(goal.getName());

		// get product goal definition groups
		if (!goal.getSuccessConditionSet().isEmpty()) {

			Map<Entity, List<DefProductCondition>> definitionGroupMap = goal.getSuccessConditionSet().stream()
					.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

			for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
				DefinitionGroupDto definitionGroup = new DefinitionGroupDto();
				goalWorkItemDto.getDefinitionGroupSet().add(definitionGroup);

				// create all def products conditions associated with the entity
				DefProductConditionSetDto defProductConditionSet = new DefProductConditionSetDto();
				definitionGroup.setDefProductConditionSet(defProductConditionSet);
				defProductConditionSet.setDefEnts(definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
						.map(d -> d.getDTO()).collect(Collectors.toSet()));
				defProductConditionSet.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
						.map(d -> d.getDto()).collect(Collectors.toSet()));

				// create entity contexts
				Set<EntityContextDto> entityContextDtos = new HashSet<EntityContextDto>();
				definitionGroup.setEntityContextSet(entityContextDtos);
				Set<Entity> entityContexts = goal.getEntityContext(entityDefinitionGroup);
				// for each entity context of the product groups to be created
				for (Entity entityContext : entityContexts) {
					// entity already exists, some of its attributes are being
					// defined)
					if (entityDefinitionGroup == entityContext) {
						entityContextDtos.add(
								EntityContextDto.createEntityContextDTO(goal, entityContext, null, workflowInstance));
					}
				}

				// create definition group instances
				Set<DefinitionGroupInstanceDto> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDto>();
				definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
			}
		}
		// get association goal definition groups

		if (!goal.getEntityInvariantConditionSet().isEmpty()) {
			Map<Entity, List<DefPathCondition>> definitionGroupMap = goal.getActivationConditionSet().stream()
					.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

			for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
				DefinitionGroupDto definitionGroup = new DefinitionGroupDto();
				goalWorkItemDto.getDefinitionGroupSet().add(definitionGroup);

				// // create entity contexts
				Set<EntityContextDto> entityContextDTOs = new HashSet<EntityContextDto>();
				definitionGroup.setEntityContextSet(entityContextDTOs);
				Set<Entity> entityContexts = goal.getEntityContext(entityDefinitionGroup);
				// for each entity context of the product groups to be created
				for (Entity entityContext : entityContexts) {
					for (MulCondition mulCondition : goal.getMulConditionFromEntityToEntity(entityDefinitionGroup,
							entityContext)) {
						entityContextDTOs.add(EntityContextDto.createEntityContextDTO(goal, entityContext, mulCondition,
								workflowInstance));
					}

					// entity already exists, some of its attributes are being
					// defined)
					if (entityDefinitionGroup == entityContext) {
						entityContextDTOs.add(
								EntityContextDto.createEntityContextDTO(goal, entityContext, null, workflowInstance));
					}
				}

				// create inner relations
				Set<InnerRelationDto> innerRelationDTOs = new HashSet<InnerRelationDto>();
				definitionGroup.setInnerRelationSet(innerRelationDTOs);
				for (MulCondition mulCondition : goal.getEntityInvariantConditionSet().stream()
						.filter(m -> m.getSourceEntity() == entityDefinitionGroup).collect(Collectors.toSet())) {
					InnerRelationDto innerRelationDTO = InnerRelationDto.createInnerRelationDTO(entityDefinitionGroup,
							mulCondition);
					innerRelationDTO.getProductInstanceSet()
							.addAll(goal.getInstanceContext(workflowInstance).get(mulCondition.getTargetEntity())
									.stream().map(ei -> ei.getDTO()).collect(Collectors.toSet()));
					innerRelationDTOs.add(innerRelationDTO);
				}

				// create definition group instances
				Set<DefinitionGroupInstanceDto> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDto>();
				definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
			}

		}

		return goalWorkItemDto;
	}

	public GoalWorkItem executeGoal(WorkflowInstance workflowInstance, Goal goal) {
		GoalWorkItem goalWorkItem = new GoalWorkItem(workflowInstance, goal);

		executeWorkItem(workflowInstance, goalWorkItem);

		return goalWorkItem;
	}

}
