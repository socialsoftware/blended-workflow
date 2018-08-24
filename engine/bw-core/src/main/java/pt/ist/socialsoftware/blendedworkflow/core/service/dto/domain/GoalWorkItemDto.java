package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class GoalWorkItemDto extends WorkItemDto {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemDto.class);

	public static GoalWorkItemDto createGoalWorkItemDto(WorkflowInstance workflowInstance, Goal goal) {
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
				definitionGroup.setDefEnt(definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
						.map(d -> d.getDTO()).findFirst().orElse(null));
				definitionGroup.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
						.map(d -> d.getDto()).collect(Collectors.toSet()));

				// create entity contexts
				Set<EntityContextDto> entityContextDtos = new HashSet<EntityContextDto>();
				definitionGroup.setEntityContextSet(entityContextDtos);
				Set<Entity> entityContexts = goal.getEntityContextForDefinitionGroup(entityDefinitionGroup);
				int counter = 0;
				// for each entity context of the product groups to be created
				for (Entity entityContext : entityContexts) {
					// entity already exists, some of its attributes are being
					// defined)
					if (entityDefinitionGroup == entityContext) {
						entityContextDtos.add(EntityContextDto.createEntityContextDTO(counter++, goal, entityContext,
								null, workflowInstance));
					}
				}

				// create definition group instances
				Set<DefinitionGroupInstanceDto> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDto>();
				definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
			}

			// FOLLOW THE DOMAIN MODEL APPROACH
			for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {

				// get entity contexts
				Set<Entity> entityContexts = goal.getEntityContextForDefinitionGroup(entityDefinitionGroup);

				// get entity to be defined
				Entity entityToDefine = definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
						.map(d -> d.getEntity()).findFirst().orElse(null);

				// get attributes to be defined
				Set<Attribute> attributesToDefine = definitionGroupMap.get(entityDefinitionGroup).stream()
						.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
						.map(d -> d.getAttributeOfDef()).collect(Collectors.toSet());

				// create entity instance dto to be defined
				EntityInstanceToDefineDto entityInstanceToDefineDto = null;

				if (entityToDefine != null) {
					// a new entity instance is going to be created
					entityInstanceToDefineDto = new EntityInstanceToDefineDto(entityToDefine);
				} else {
					// only attributes and links are going to be defined, it is necessary to define
					// their context
					entityInstanceToDefineDto = new EntityInstanceToDefineDto(entityDefinitionGroup,
							goal.getEntityInstanceContext(workflowInstance, entityDefinitionGroup));
					entityContexts.remove(entityDefinitionGroup);
				}

				// add attributes to define
				for (Attribute attribute : attributesToDefine) {
					new AttributeInstanceToDefineDto(entityInstanceToDefineDto, attribute);
				}

				// fill entity instance dto with undef attributes
				entityInstanceToDefineDto.fillUndefAttributeInstances(entityDefinitionGroup);

				// fill entity instance dto undef links
				entityInstanceToDefineDto.fillUndefLinks(entityDefinitionGroup);

				goalWorkItemDto.getEntityInstancesToDefine().add(entityInstanceToDefineDto);
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
				Set<Entity> entityContexts = goal.getEntityContextForDefinitionGroup(entityDefinitionGroup);
				int counter = 0;
				// for each entity context of the product groups to be created
				for (Entity entityContext : entityContexts) {
					for (MulCondition mulCondition : goal.getMulConditionFromEntityToEntity(entityDefinitionGroup,
							entityContext)) {
						entityContextDTOs.add(EntityContextDto.createEntityContextDTO(counter++, goal, entityContext,
								mulCondition, workflowInstance));
					}

					// entity already exists, some of its attributes are being
					// defined)
					if (entityDefinitionGroup == entityContext) {
						entityContextDTOs.add(EntityContextDto.createEntityContextDTO(counter++, goal, entityContext,
								null, workflowInstance));
					}
				}

				// create inner relations
				Set<InnerRelationDto> innerRelationDTOs = new HashSet<InnerRelationDto>();
				definitionGroup.setInnerRelationSet(innerRelationDTOs);
				for (MulCondition mulCondition : goal.getEntityInvariantConditionSet().stream()
						.filter(m -> m.getSourceEntity() == entityDefinitionGroup).collect(Collectors.toSet())) {
					InnerRelationDto innerRelationDto = InnerRelationDto.createInnerRelationDTO(entityDefinitionGroup,
							mulCondition);
					innerRelationDto.getEntityInstanceSet()
							.addAll(goal.getInstanceContext(workflowInstance).get(mulCondition.getTargetEntity())
									.stream().map(ei -> new EntityInstanceDto(ei, Depth.DEEP))
									.collect(Collectors.toSet()));
					innerRelationDTOs.add(innerRelationDto);
				}

				// create definition group instances
				Set<DefinitionGroupInstanceDto> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDto>();
				definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
			}

			// FOLLOW THE DOMAIN MODEL APPROACH
			for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {

				// create entity instance dto to be defined
				EntityInstanceToDefineDto entityInstanceToDefineDto = new EntityInstanceToDefineDto(
						entityDefinitionGroup, goal.getEntityInstanceContext(workflowInstance, entityDefinitionGroup));

				// fill entity instance dto with undef attributes
				entityInstanceToDefineDto.fillUndefAttributeInstances(entityDefinitionGroup);

				// for each inner relation
				for (MulCondition mulCondition : goal.getInnerMulConditions(entityDefinitionGroup)) {
					new LinkToDefineDto(entityInstanceToDefineDto, mulCondition);
				}

				// fill entity instance dto undef links
				entityInstanceToDefineDto.fillUndefLinks(entityDefinitionGroup);

				goalWorkItemDto.getEntityInstancesToDefine().add(entityInstanceToDefineDto);
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
