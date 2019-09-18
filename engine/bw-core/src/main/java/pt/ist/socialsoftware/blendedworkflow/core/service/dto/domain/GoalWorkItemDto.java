package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

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

public class GoalWorkItemDto extends WorkItemDto {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemDto.class);

	public static GoalWorkItemDto createGoalWorkItemDto(WorkflowInstance workflowInstance, Goal goal) {
		GoalWorkItemDto goalWorkItemDto = new GoalWorkItemDto();
		goalWorkItemDto.setSpecId(workflowInstance.getSpecification().getSpecId());
		goalWorkItemDto.setWorkflowInstanceName(workflowInstance.getName());
		goalWorkItemDto.setName(goal.getName());
		
		// get product goal definition groups
		if (!goal.getSuccessConditionSet().isEmpty()) {

			Map<Entity, List<DefProductCondition>> definitionGroupMap = goal.getSuccessConditionSet().stream()
					.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

			for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
				
				// get entity contexts
				Set<Entity> entityContexts = goal.getEntityContextForDefinitionGroup(entityDefinitionGroup, workflowInstance);
				
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
					if (entityContexts.isEmpty())
						entityInstanceToDefineDto = new EntityInstanceToDefineDto(entityToDefine);
					// a skipped entity instance is going to be defined
					else
						entityInstanceToDefineDto = new EntityInstanceToDefineDto(
								goal.getEntityInstanceContextForNewEntityGoal(workflowInstance, entityToDefine), entityToDefine);
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
