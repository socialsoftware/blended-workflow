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
        @JsonSubTypes.Type(value = RALExprHasPositionDTO.class, name = "RALExprHasPositionDTO"),
        @JsonSubTypes.Type(value = RALExprHasUnitDTO.class, name = "RALExprHasUnitDTO"),
        @JsonSubTypes.Type(value = RALExprHasRoleDTO.class, name = "RALExprHasRoleDTO"),
        @JsonSubTypes.Type(value = RALExprReportsToPersonPositionExprDTO.class, name = "RALExprReportsToPersonPositionExprDTO"),
        @JsonSubTypes.Type(value = RALExprReportsToPositionExprDTO.class, name = "RALExprReportsToPositionExprDTO"),
        @JsonSubTypes.Type(value = RALExprReportedByPersonPositionExprDTO.class, name = "RALExprReportedByPersonPositionExprDTO"),
        @JsonSubTypes.Type(value = RALExprReportedByPositionExprDTO.class, name = "RALExprReportedByPositionExprDTO"),
})
public class RALExpressionDTO {

}
