package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import java.util.Set;

public interface ResourceWorkItemDTO {
    Set<EntityIsPersonDTO> getEntityIsPersonDTOSet();
    void setEntityIsPersonDTOSet(Set<EntityIsPersonDTO> entityIsPersonDTOSet);
}
