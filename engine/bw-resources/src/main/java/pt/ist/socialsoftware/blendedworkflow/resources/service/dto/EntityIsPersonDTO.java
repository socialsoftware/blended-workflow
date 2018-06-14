package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;

import java.util.Set;

public class EntityIsPersonDTO {
    private Entity entity;
    private EntityInstance entityInstance;
    private Set<PersonDTO> personContext;
    private PersonDTO personChosen;

    public EntityIsPersonDTO(Entity entity, Set<PersonDTO> personContext) {
        this.entity = entity;
        this.personContext = personContext;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public EntityInstance getEntityInstance() {
        return entityInstance;
    }

    public void setEntityInstance(EntityInstance entityInstance) {
        this.entityInstance = entityInstance;
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
