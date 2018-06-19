package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.GoalWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceGoalWorkItemDTO extends GoalWorkItemDTO implements ResourceWorkItemDTO {
    private Set<EntityIsPersonDTO> entityIsPersonDTOSet;
    private UserDTO executionUser;

    public static ResourceGoalWorkItemDTO createGoalWorkItemDTO(WorkflowInstance workflowInstance, Goal goal) {
        ResourceGoalWorkItemDTO resourceGoalWorkItemDTO = new ResourceGoalWorkItemDTO(
                GoalWorkItemDTO.createGoalWorkItemDTO(workflowInstance, goal));
        ResourceModel resourceModel = goal.getGoalModel().getSpecification().getResourceModel();

        Set<EntityIsPersonDTO> entityIsPersonDTOSet = new HashSet<>();
        Set<PersonDTO> personContext = resourceModel.getPersonSet().stream().map(Person::getDTO).collect(Collectors.toSet());

        goal.getSuccessConditionSet().stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .map(DefEntityCondition::getEntities)
                .flatMap(Collection::stream)
                .filter(entity -> resourceModel.checkEntityIsPerson(entity))
                .forEach(entity -> entityIsPersonDTOSet.add(new EntityIsPersonDTO(entity.getDTO(), personContext)));
        resourceGoalWorkItemDTO.setEntityIsPersonDTOSet(entityIsPersonDTOSet);

        return resourceGoalWorkItemDTO;
    }

    public static ResourceGoalWorkItemDTO fillGoalWorkItemDTO(GoalWorkItemDTO goalWorkItemDTO, GoalWorkItem goalWorkItem) {
        ResourceGoalWorkItemDTO resourceGoalWorkItemDTO = new ResourceGoalWorkItemDTO(goalWorkItemDTO);

        Set<EntityIsPersonDTO> entityIsPersonDTOSet = new HashSet<>();

        goalWorkItem.getPostConditionSet().stream()
                .flatMap(postWorkItemArgument -> postWorkItemArgument.getProductInstanceSet().stream())
                .filter(EntityInstance.class::isInstance)
                .map(EntityInstance.class::cast)
                .filter(entityInstance -> entityInstance.getPerson() != null)
                .forEach(entityInstance -> entityIsPersonDTOSet.add(new EntityIsPersonDTO(entityInstance.getDTO(), entityInstance.getPerson().getDTO())));

        resourceGoalWorkItemDTO.setEntityIsPersonDTOSet(entityIsPersonDTOSet);

        resourceGoalWorkItemDTO.setExecutionUser(goalWorkItem.getExecutionUser().getDTO());

        return resourceGoalWorkItemDTO;
    }

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
    public UserDTO getExecutionUser() {
        return executionUser;
    }

    @Override
    public void setExecutionUser(UserDTO executionUser) {
        this.executionUser = executionUser;
    }

    @Override
    public GoalWorkItem executeGoal(WorkflowInstance workflowInstance, Goal goal) {
        GoalWorkItem goalWorkItem = super.executeGoal(workflowInstance, goal);

        for (EntityIsPersonDTO entityIsPersonDTO : getEntityIsPersonDTOSet()) {
            goalWorkItem.getPostConditionSet().stream()
                    .map(PostWorkItemArgument::getProductInstanceSet)
                    .flatMap(Collection::stream)
                    .filter(productInstance -> productInstance instanceof EntityInstance)
                    .filter(productInstance -> entityIsPersonDTO.getEntity().getName().equals(productInstance.getEntity().getName()))
                    .filter(productInstance -> productInstance.getEntity().getResourceModel().checkEntityIsPerson(productInstance.getProduct()))
                    .map(productInstance -> (EntityInstance) productInstance)
                    .forEach(entityInstance -> {
                        if (entityIsPersonDTO.getPersonChosen() != null) {
                            Person person = entityInstance.getEntity().getResourceModel().getPerson(entityIsPersonDTO.getPersonChosen().getName());
                            entityInstance.setPerson(person);
                        } else {
                            entityInstance.setPerson(new Person(
                                    entityInstance.getEntity().getResourceModel(),
                                    entityInstance.getExternalId(),
                                    entityInstance.getExternalId()
                            ));
                        }
                    });
        }

        return goalWorkItem;
    }

    @Override
    public String print() {
        String result = super.print();
        for (EntityIsPersonDTO entityIsPersonDTO : getEntityIsPersonDTOSet()) {
            result = result + "EIP ENTITY: " + ((entityIsPersonDTO.getEntity() != null) ? entityIsPersonDTO.getEntity().getName() : "") + "\r\n";
            result = result + "EIP PERSON CONTEXT: "
                    + entityIsPersonDTO.getPersonContext().stream()
                    .map(PersonDTO::getName)
                    .collect(Collectors.joining(";"))
                    + "\r\n";
            result = result + "EIP PERSON CHOSEN: " + ((entityIsPersonDTO.getPersonChosen() != null) ? entityIsPersonDTO.getPersonChosen().getName() : "") + "\r\n";
        }
        return result;
    }
}
