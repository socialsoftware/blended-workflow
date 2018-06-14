package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.GoalWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceGoalWorkItemDTO extends GoalWorkItemDTO implements ResourceWorkItemDTO {
    private Set<EntityIsPersonDTO> entityIsPersonDTOSet;

    public ResourceGoalWorkItemDTO(GoalWorkItemDTO goalWorkItemDTO) {
        super();
        setSpecId(goalWorkItemDTO.getSpecId());
        setSpecName(goalWorkItemDTO.getSpecName());
        setWorkflowInstanceName(goalWorkItemDTO.getWorkflowInstanceName());
        setDefinitionGroupSet(goalWorkItemDTO.getDefinitionGroupSet());
        setName(goalWorkItemDTO.getName());
        setTimestamp(goalWorkItemDTO.getTimestamp());
        setPreArguments(goalWorkItemDTO.getPreArguments());
        setPostArguments(goalWorkItemDTO.getPostArguments());

        entityIsPersonDTOSet = new HashSet<>();
    }

    

    @Override
    public Set<EntityIsPersonDTO> getEntityIsPersonDTOSet() {
        return entityIsPersonDTOSet;
    }

    @Override
    public void setEntityIsPersonDTOSet(Set<EntityIsPersonDTO> entityIsPersonDTOSet) {
        this.entityIsPersonDTOSet = entityIsPersonDTOSet;
    }

    @Override
    public GoalWorkItem executeGoal(WorkflowInstance workflowInstance, Goal goal) {
        GoalWorkItem goalWorkItem = super.executeGoal(workflowInstance, goal);

        goalWorkItem.getPostConditionSet().stream()
                .map(PostWorkItemArgument::getProductInstanceSet)
                .flatMap(Collection::stream)
                .filter(productInstance -> productInstance instanceof EntityInstance)
                .filter(productInstance -> productInstance.getEntity().getResourceModel().checkEntityIsPerson(productInstance.getProduct()))
                .map(productInstance -> (EntityInstance) productInstance)
                .forEach(entityInstance -> {
                    entityInstance.setPerson(new Person(
                            entityInstance.getEntity().getResourceModel(),
                            entityInstance.getExternalId(),
                            entityInstance.getExternalId()
                    ));
                });

        return goalWorkItem;
    }

    @Override
    public String print() {
        String result = super.print();
        for (EntityIsPersonDTO entityIsPersonDTO : getEntityIsPersonDTOSet()) {
            result = result + "EIP ENTITY: " + entityIsPersonDTO.getEntity().getName() + "\r\n";
            result = result + "EIP ENTITYINSTANCE: " + entityIsPersonDTO.getEntityInstance().getId() + "\r\n";
            result = result + "EIP PERSON CONTEXT: "
                    + entityIsPersonDTO.getPersonContext().stream()
                    .map(PersonDTO::getName)
                    .collect(Collectors.joining(";"))
                    + "\r\n";
            result = result + "EIP PERSON CHOSEN: " + entityIsPersonDTO.getPersonChosen().getName() + "\r\n";
        }
        return result;
    }
}
