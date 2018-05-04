package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

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
    @JsonSubTypes.Type(value = RALExprDelegatesToPersonPositionExprDTO.class, name = "RALExprDelegatesToPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatesToPositionExprDTO.class, name = "RALExprDelegatesToPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatedByPersonPositionExprDTO.class, name = "RALExprDelegatedByPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatedByPositionExprDTO.class, name = "RALExprDelegatedByPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprNotDTO.class, name = "RALExprNotDTO"),
    @JsonSubTypes.Type(value = RALExprAndDTO.class, name = "RALExprAndDTO"),
    @JsonSubTypes.Type(value = RALExprOrDTO.class, name = "RALExprOrDTO"),
    @JsonSubTypes.Type(value = RALExprHasCapabilityDTO.class, name = "RALExprHasCapabilityDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryDTO.class, name = "RALExprHistoryDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryExecutingDTO.class, name = "RALExprHistoryExecutingDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryInformedDTO.class, name = "RALExprHistoryInformedDTO"),
    @JsonSubTypes.Type(value = RALExprCommonalityDTO.class, name = "RALExprCommonalityDTO"),
    @JsonSubTypes.Type(value = RALExprSharesPositionDTO.class, name = "RALExprSharesPositionDTO"),
    @JsonSubTypes.Type(value = RALExprSharesUnitDTO.class, name = "RALExprSharesUnitDTO"),
    @JsonSubTypes.Type(value = RALExprSharesRoleDTO.class, name = "RALExprSharesRoleDTO"),
    @JsonSubTypes.Type(value = RALExprPersonDTO.class, name = "RALExprPersonDTO"),
})
public class RALExpressionDTO {
    public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
        return null;
    }
}
