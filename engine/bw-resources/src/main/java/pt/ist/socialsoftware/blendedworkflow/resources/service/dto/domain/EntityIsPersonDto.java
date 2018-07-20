package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDto;

import java.util.Set;

public class EntityIsPersonDto {
    private EntityDto entity;
    private ProductInstanceDto entityInstance;
    private Set<PersonDto> personContext;
    private PersonDto personChosen;

    public EntityIsPersonDto(ProductInstanceDto entityInstance, PersonDto personChosen) {
        this.entityInstance = entityInstance;
        this.personChosen = personChosen;
    }

    public EntityIsPersonDto(EntityDto entity, Set<PersonDto> personContext) {
        this.entity = entity;
        this.personContext = personContext;
    }

    public EntityDto getEntity() {
        return entity;
    }

    public void setEntity(EntityDto entity) {
        this.entity = entity;
    }

    public Set<PersonDto> getPersonContext() {
        return personContext;
    }

    public void setPersonContext(Set<PersonDto> personContext) {
        this.personContext = personContext;
    }

    public PersonDto getPersonChosen() {
        return personChosen;
    }

    public void setPersonChosen(PersonDto personChosen) {
        this.personChosen = personChosen;
    }

    public ProductInstanceDto getEntityInstance() {
        return entityInstance;
    }

    public void setEntityInstance(ProductInstanceDto entityInstance) {
        this.entityInstance = entityInstance;
    }
}
