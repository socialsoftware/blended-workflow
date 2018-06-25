package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDTO;

import java.util.Set;

public class EntityIsPersonDTO {
    private EntityDTO entity;
    private ProductInstanceDTO entityInstance;
    private Set<PersonDTO> personContext;
    private PersonDTO personChosen;

    public EntityIsPersonDTO(ProductInstanceDTO entityInstance, PersonDTO personChosen) {
        this.entityInstance = entityInstance;
        this.personChosen = personChosen;
    }

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

    public ProductInstanceDTO getEntityInstance() {
        return entityInstance;
    }

    public void setEntityInstance(ProductInstanceDTO entityInstance) {
        this.entityInstance = entityInstance;
    }
}
