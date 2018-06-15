package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DefinitionGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceActivityWorkItemDTO extends ActivityWorkItemDTO implements ResourceWorkItemDTO {
    private Set<EntityIsPersonDTO> entityIsPersonDTOSet;

    public static ResourceActivityWorkItemDTO createActivityWorkItemDTO(WorkflowInstance workflowInstance, Activity activity) {
        ResourceActivityWorkItemDTO resourceActivityWorkItemDTO = new ResourceActivityWorkItemDTO(
                ActivityWorkItemDTO.createActivityWorkItemDTO(workflowInstance, activity));
        ResourceModel resourceModel = activity.getActivityModel().getSpecification().getResourceModel();

        Set<EntityIsPersonDTO> entityIsPersonDTOSet = new HashSet<>();
        Set<PersonDTO> personContext = resourceModel.getPersonSet().stream().map(Person::getDTO).collect(Collectors.toSet());

        activity.getPostEntities().stream()
                .filter(entity -> resourceModel.checkEntityIsPerson(entity))
                .forEach(entity -> entityIsPersonDTOSet.add(new EntityIsPersonDTO(entity.getDTO(), personContext)));
        resourceActivityWorkItemDTO.setEntityIsPersonDTOSet(entityIsPersonDTOSet);

        return resourceActivityWorkItemDTO;
    }

    public static ResourceActivityWorkItemDTO fillActivityWorkItemDTO(ActivityWorkItemDTO activityWorkItemDTO, ActivityWorkItem activityWorkItem) {
        ResourceActivityWorkItemDTO resourceActivityWorkItemDTO = new ResourceActivityWorkItemDTO(activityWorkItemDTO);

        Set<EntityIsPersonDTO> entityIsPersonDTOSet = new HashSet<>();

        activityWorkItem.getPostConditionSet().stream()
                .flatMap(postWorkItemArgument -> postWorkItemArgument.getProductInstanceSet().stream())
                .filter(EntityInstance.class::isInstance)
                .map(EntityInstance.class::cast)
                .filter(entityInstance -> entityInstance.getPerson() != null)
                .forEach(entityInstance -> entityIsPersonDTOSet.add(new EntityIsPersonDTO(entityInstance.getDTO(), entityInstance.getPerson().getDTO())));

        resourceActivityWorkItemDTO.setEntityIsPersonDTOSet(entityIsPersonDTOSet);

        return resourceActivityWorkItemDTO;
    }

    public ResourceActivityWorkItemDTO(ActivityWorkItemDTO activityWorkItemDTO) {
        super();
        setSpecId(activityWorkItemDTO.getSpecId());
        setSpecName(activityWorkItemDTO.getSpecName());
        setWorkflowInstanceName(activityWorkItemDTO.getWorkflowInstanceName());
        setDefinitionGroupSet(activityWorkItemDTO.getDefinitionGroupSet());
        setName(activityWorkItemDTO.getName());
        setTimestamp(activityWorkItemDTO.getTimestamp());
        setPreArguments(activityWorkItemDTO.getPreArguments());
        setPostArguments(activityWorkItemDTO.getPostArguments());

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
    public ActivityWorkItem executeActivity(WorkflowInstance workflowInstance, Activity activity) {
        ActivityWorkItem activityWorkItem = super.executeActivity(workflowInstance, activity);

        for (EntityIsPersonDTO entityIsPersonDTO : getEntityIsPersonDTOSet()) {
            activityWorkItem.getPostConditionSet().stream()
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

        return activityWorkItem;
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
