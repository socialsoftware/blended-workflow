package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceGoalWorkItemDto extends GoalWorkItemDto implements ResourceWorkItemDTO {
    private Set<EntityIsPersonDto> _entityIsPersonDtoSet;
    private UserDto executionUser;

    public static ResourceGoalWorkItemDto createGoalWorkItemDTO(WorkflowInstance workflowInstance, Goal goal) {
        ResourceGoalWorkItemDto resourceGoalWorkItemDto = new ResourceGoalWorkItemDto(
                GoalWorkItemDto.createGoalWorkItemDto(workflowInstance, goal));
        ResourceModel resourceModel = goal.getGoalModel().getSpecification().getResourceModel();

        Set<EntityIsPersonDto> entityIsPersonDtoSet = new HashSet<>();
        Set<PersonDto> personContext = resourceModel.getPersonSet().stream().map(Person::getDTO).collect(Collectors.toSet());

        goal.getSuccessConditionSet().stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .map(DefEntityCondition::getEntities)
                .flatMap(Collection::stream)
                .filter(entity -> resourceModel.checkEntityIsPerson(entity))
                .forEach(entity -> entityIsPersonDtoSet.add(new EntityIsPersonDto(entity.getDto(), personContext)));
        resourceGoalWorkItemDto.setEntityIsPersonDTOSet(entityIsPersonDtoSet);

        return resourceGoalWorkItemDto;
    }

    public static ResourceGoalWorkItemDto fillGoalWorkItemDTO(GoalWorkItemDto goalWorkItemDTO, GoalWorkItem goalWorkItem) {
        ResourceGoalWorkItemDto resourceGoalWorkItemDto = new ResourceGoalWorkItemDto(goalWorkItemDTO);

        Set<EntityIsPersonDto> entityIsPersonDtoSet = new HashSet<>();

        goalWorkItem.getPostConditionSet().stream()
                .flatMap(postWorkItemArgument -> postWorkItemArgument.getProductInstanceSet().stream())
                .filter(EntityInstance.class::isInstance)
                .map(EntityInstance.class::cast)
                .filter(entityInstance -> entityInstance.getPerson() != null)
                .forEach(entityInstance -> entityIsPersonDtoSet.add(new EntityIsPersonDto(entityInstance.getDto(), entityInstance.getPerson().getDTO())));

        resourceGoalWorkItemDto.setEntityIsPersonDTOSet(entityIsPersonDtoSet);

        if (goalWorkItem.getExecutionUser() != null) {
            resourceGoalWorkItemDto.setExecutionUser(goalWorkItem.getExecutionUser().getDTO());
        }

        return resourceGoalWorkItemDto;
    }

    public ResourceGoalWorkItemDto(GoalWorkItemDto goalWorkItemDTO) {
        super();
        setSpecId(goalWorkItemDTO.getSpecId());
        setSpecName(goalWorkItemDTO.getSpecName());
        setWorkflowInstanceName(goalWorkItemDTO.getWorkflowInstanceName());
        setEntityInstancesToDefine(goalWorkItemDTO.getEntityInstancesToDefine());
        setUnitOfWork(goalWorkItemDTO.getUnitOfWork());
        setName(goalWorkItemDTO.getName());
        setTimestamp(goalWorkItemDTO.getTimestamp());
        setPreArguments(goalWorkItemDTO.getPreArguments());
        setPostArguments(goalWorkItemDTO.getPostArguments());

        _entityIsPersonDtoSet = new HashSet<>();
    }

    

    @Override
    public Set<EntityIsPersonDto> getEntityIsPersonDTOSet() {
        return _entityIsPersonDtoSet;
    }

    @Override
    public void setEntityIsPersonDTOSet(Set<EntityIsPersonDto> entityIsPersonDtoSet) {
        this._entityIsPersonDtoSet = entityIsPersonDtoSet;
    }

    @Override
    public UserDto getExecutionUser() {
        return executionUser;
    }

    @Override
    public void setExecutionUser(UserDto executionUser) {
        this.executionUser = executionUser;
    }

    @Override
    public GoalWorkItem executeGoal(WorkflowInstance workflowInstance, Goal goal) {
        GoalWorkItem goalWorkItem = super.executeGoal(workflowInstance, goal);

        for (EntityIsPersonDto entityIsPersonDto : getEntityIsPersonDTOSet()) {
            goalWorkItem.getPostConditionSet().stream()
                    .map(PostWorkItemArgument::getProductInstanceSet)
                    .flatMap(Collection::stream)
                    .filter(productInstance -> productInstance instanceof EntityInstance)
                    .filter(productInstance -> entityIsPersonDto.getEntity().getName().equals(productInstance.getEntity().getName()))
                    .filter(productInstance -> productInstance.getEntity().getResourceModel().checkEntityIsPerson(productInstance.getProduct()))
                    .map(productInstance -> (EntityInstance) productInstance)
                    .forEach(entityInstance -> {
                        if (entityIsPersonDto.getPersonChosen() != null) {
                            Person person = entityInstance.getEntity().getResourceModel().getPerson(entityIsPersonDto.getPersonChosen().getName());
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
        for (EntityIsPersonDto entityIsPersonDto : getEntityIsPersonDTOSet()) {
            result = result + "EIP ENTITY: " + ((entityIsPersonDto.getEntity() != null) ? entityIsPersonDto.getEntity().getName() : "") + "\r\n";
            result = result + "EIP PERSON CONTEXT: "
                    + entityIsPersonDto.getPersonContext().stream()
                    .map(PersonDto::getName)
                    .collect(Collectors.joining(";"))
                    + "\r\n";
            result = result + "EIP PERSON CHOSEN: " + ((entityIsPersonDto.getPersonChosen() != null) ? entityIsPersonDto.getPersonChosen().getName() : "") + "\r\n";
        }
        return result;
    }
}
