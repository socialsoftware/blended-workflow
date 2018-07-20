package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import java.util.Set;

public interface ResourceWorkItemDTO {
    Set<EntityIsPersonDTO> getEntityIsPersonDTOSet();
    void setEntityIsPersonDTOSet(Set<EntityIsPersonDTO> entityIsPersonDTOSet);
    UserDTO getExecutionUser();
    void setExecutionUser(UserDTO executionUser);
}
