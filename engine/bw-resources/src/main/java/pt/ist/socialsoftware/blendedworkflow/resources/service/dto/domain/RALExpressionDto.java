package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

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
    @JsonSubTypes.Type(value = RALExprAnyoneDto.class, name = "RALExprAnyoneDTO"),
    @JsonSubTypes.Type(value = RALExprIsPersonDto.class, name = "RALExprIsPersonDTO"),
    @JsonSubTypes.Type(value = RALExprIsPersonDataObjectDto.class, name = "RALExprIsPersonDataObjectDTO"),
    @JsonSubTypes.Type(value = RALExprIsPersonInTaskDutyDto.class, name = "RALExprIsPersonInTaskDutyDTO"),
    @JsonSubTypes.Type(value = RALExprHasPositionDto.class, name = "RALExprHasPositionDTO"),
    @JsonSubTypes.Type(value = RALExprHasUnitDto.class, name = "RALExprHasUnitDTO"),
    @JsonSubTypes.Type(value = RALExprHasRoleDto.class, name = "RALExprHasRoleDTO"),
    @JsonSubTypes.Type(value = RALExprReportsToPersonPositionExprDto.class, name = "RALExprReportsToPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprReportsToPositionExprDto.class, name = "RALExprReportsToPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprReportedByPersonPositionExprDto.class, name = "RALExprReportedByPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprReportedByPositionExprDto.class, name = "RALExprReportedByPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatesToPersonPositionExprDto.class, name = "RALExprDelegatesToPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatesToPositionExprDto.class, name = "RALExprDelegatesToPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatedByPersonPositionExprDto.class, name = "RALExprDelegatedByPersonPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprDelegatedByPositionExprDto.class, name = "RALExprDelegatedByPositionExprDTO"),
    @JsonSubTypes.Type(value = RALExprNotDto.class, name = "RALExprNotDTO"),
    @JsonSubTypes.Type(value = RALExprAndDto.class, name = "RALExprAndDTO"),
    @JsonSubTypes.Type(value = RALExprOrDto.class, name = "RALExprOrDTO"),
    @JsonSubTypes.Type(value = RALExprHasCapabilityDto.class, name = "RALExprHasCapabilityDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryDto.class, name = "RALExprHistoryDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryExecutingDto.class, name = "RALExprHistoryExecutingDTO"),
    @JsonSubTypes.Type(value = RALExprHistoryInformedDto.class, name = "RALExprHistoryInformedDTO"),
    @JsonSubTypes.Type(value = RALExprCommonalityDto.class, name = "RALExprCommonalityDTO"),
    @JsonSubTypes.Type(value = RALExprSharesPositionDto.class, name = "RALExprSharesPositionDTO"),
    @JsonSubTypes.Type(value = RALExprSharesUnitDto.class, name = "RALExprSharesUnitDTO"),
    @JsonSubTypes.Type(value = RALExprSharesRoleDto.class, name = "RALExprSharesRoleDTO"),
    @JsonSubTypes.Type(value = RALExprPersonDto.class, name = "RALExprPersonDTO"),
})
public class RALExpressionDto {
    public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
        return null;
    }
}
