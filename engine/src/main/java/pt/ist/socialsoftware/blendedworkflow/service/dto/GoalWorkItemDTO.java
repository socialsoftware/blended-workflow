package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;

public class GoalWorkItemDTO extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemDTO.class);

	String goalName;

	public GoalWorkItemDTO() {
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public static GoalWorkItemDTO createGoalWorkItemDTO(WorkflowInstance workflowInstance, Goal goal) {
		GoalWorkItemDTO goalWorkItemDTO = new GoalWorkItemDTO();
		goalWorkItemDTO.setSpecId(workflowInstance.getSpecification().getSpecId());
		goalWorkItemDTO.setWorkflowInstanceName(workflowInstance.getName());
		goalWorkItemDTO.setDefinitionGroupSet(new HashSet<DefinitionGroupDTO>());
		goalWorkItemDTO.setGoalName(goal.getName());

		// get activity definition groups
		Map<Entity, List<DefProductCondition>> definitionGroupMap = goal.getSuccessConditionSet().stream()
				.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

		for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
			DefinitionGroupDTO definitionGroup = new DefinitionGroupDTO();
			goalWorkItemDTO.getDefinitionGroupSet().add(definitionGroup);

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
			Set<Entity> entityContexts = goal.getEntityContext(entityDefinitionGroup);
			// for each entity context of the product groups to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : goal.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					entityContextDTOs.add(EntityContextDTO.createEntityContextDTO(goal, entityContext, mulCondition,
							workflowInstance));
				}

				// entity already exists, some of its attributes are being
				// defined)
				if (entityDefinitionGroup == entityContext) {
					entityContextDTOs
							.add(EntityContextDTO.createEntityContextDTO(goal, entityContext, null, workflowInstance));
				}
			}

			// create inner relations
			Set<InnerRelationDTO> innerRelationDTOs = new HashSet<InnerRelationDTO>();
			definitionGroup.setInnerRelationSet(innerRelationDTOs);
			for (MulCondition mulCondition : goal.getInnerMulConditions(entityDefinitionGroup)) {
				innerRelationDTOs.add(InnerRelationDTO.createInnerRelationDTO(entityDefinitionGroup, mulCondition));
			}

			// create definition group instances
			Set<DefinitionGroupInstanceDTO> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDTO>();
			definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
		}
		return goalWorkItemDTO;
	}

}
