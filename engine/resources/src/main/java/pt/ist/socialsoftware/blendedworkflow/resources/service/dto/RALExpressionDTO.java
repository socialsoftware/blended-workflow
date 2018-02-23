package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "profileType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RALExprAnyoneDTO.class, name = "RALExprAnyoneDTO"),
        @JsonSubTypes.Type(value = RALExprIsPersonDTO.class, name = "RALExprIsPersonDTO"),
        @JsonSubTypes.Type(value = RALExprIsPersonDataObjectDTO.class, name = "RALExprIsPersonDataObjectDTO"),
        @JsonSubTypes.Type(value = RALExprIsPersonInTaskDutyDTO.class, name = "RALExprIsPersonInTaskDutyDTO"),
})
public class RALExpressionDTO {

}
