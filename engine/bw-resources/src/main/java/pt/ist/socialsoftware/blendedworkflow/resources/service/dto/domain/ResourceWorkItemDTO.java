package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import java.util.Set;

public interface ResourceWorkItemDTO {
    Set<EntityIsPersonDto> getEntityIsPersonDTOSet();
    void setEntityIsPersonDTOSet(Set<EntityIsPersonDto> entityIsPersonDtoSet);
    UserDto getExecutionUser();
    void setExecutionUser(UserDto executionUser);
}
