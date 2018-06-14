package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.EntityDTO;

import java.util.Set;

public class EntityIsPersonDTO {
    private EntityDTO entity;
    private Set<PersonDTO> personContext;
    private PersonDTO personChosen;

    public EntityIsPersonDTO(EntityDTO entity, Set<PersonDTO> personContext) {
        this.entity = entity;
        this.personContext = personContext;
    }

    public EntityDTO getEntity() {
        return entity;
    }

    public void setEntity(EntityDTO entity) {
        this.entity = entity;
    }

    public Set<PersonDTO> getPersonContext() {
        return personContext;
    }

    public void setPersonContext(Set<PersonDTO> personContext) {
        this.personContext = personContext;
    }

    public PersonDTO getPersonChosen() {
        return personChosen;
    }

    public void setPersonChosen(PersonDTO personChosen) {
        this.personChosen = personChosen;
    }
}
