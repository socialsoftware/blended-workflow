package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.AndExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.AnyoneExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.CapabilityExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.CompoundExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.CompoundType;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.DelegatedByPersonPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.DelegatedByPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.DelegatesToPersonPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.DelegatesToPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.HasPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.HasRoleExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.HasUnitExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.HistoryExecutingExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.HistoryInformedExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonDataObject;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonID;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonInDuty;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.NotExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.OrExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ReportedByPersonPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ReportedByPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ReportsToPersonPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ReportsToPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.SharesPositionExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.SharesRoleExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.SharesUnitExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO.ResourceRuleTypeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.services.BlendedWorkflowGrammarAccess.DeniableExprElements;

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
	public static RALExpressionDTO buildRALExpressionDTO(String specId, RALExpression expression) {
		RALExpressionDTO ralExpressionDTO = null;
		
		if (expression instanceof IsPersonID) {
			
			IsPersonID isPersonExpr = (IsPersonID) expression;
			ralExpressionDTO = new RALExprIsPersonDTO(isPersonExpr.getPerson());
			
		} else if (expression instanceof IsPersonDataObject) {
			
			IsPersonDataObject isDataObjExpr = (IsPersonDataObject) expression;
			ralExpressionDTO = new RALExprIsPersonDataObjectDTO(isDataObjExpr.getDataField());
			
		} else if (expression instanceof IsPersonInDuty) {
			
			IsPersonInDuty isPersonInDutyExpr = (IsPersonInDuty) expression;
			ralExpressionDTO = new RALExprIsPersonInTaskDutyDTO(ResourceRuleTypeDTO.fromAsgmtString(isPersonInDutyExpr.getTaskDuty()), isPersonInDutyExpr.getDataField());
		
		} else if (expression instanceof AnyoneExpr) {
		
			ralExpressionDTO = new RALExprAnyoneDTO();
		
		} else if (expression instanceof HasPositionExpr) {
		
			HasPositionExpr hasPositionExpr = (HasPositionExpr) expression;
			ralExpressionDTO = new RALExprHasPositionDTO(hasPositionExpr.getPosition().getName());
		
		} else if (expression instanceof HasUnitExpr) {
		
			HasUnitExpr hasUnitExpr = (HasUnitExpr) expression;
			ralExpressionDTO = new RALExprHasUnitDTO(hasUnitExpr.getUnit().getName());
		
		} else if (expression instanceof HasRoleExpr) {
		
			HasRoleExpr hasRoleExpr = (HasRoleExpr) expression;
			RALExprHasRoleDTO dto = new RALExprHasRoleDTO(hasRoleExpr.getRole().getName());
			if (hasRoleExpr.getUnit() != null) {
				dto.setUnit(hasRoleExpr.getUnit().getName());
			}
			ralExpressionDTO = dto;
		
		} else if (expression instanceof ReportsToPersonPositionExpr) {
	
			ReportsToPersonPositionExpr reportsExpr = (ReportsToPersonPositionExpr) expression;
			// FIXME : BAD CODE
			RALExprPersonDTO personDTO = (RALExprPersonDTO) RALExpressionDTO.buildRALExpressionDTO(specId, reportsExpr.getPersonExpr());
			ralExpressionDTO = new RALExprReportsToPersonPositionExprDTO(personDTO, reportsExpr.isDirectly());
	
		} else if (expression instanceof ReportsToPositionExpr) {
		
			ReportsToPositionExpr reportsExpr = (ReportsToPositionExpr) expression;
			ralExpressionDTO = new RALExprReportsToPositionExprDTO(reportsExpr.getPosition().getName(), reportsExpr.isDirectly());
		
		} else if (expression instanceof ReportedByPersonPositionExpr) {
	
			ReportedByPersonPositionExpr reportedExpr = (ReportedByPersonPositionExpr) expression;
			// FIXME : BAD CODE
			RALExprPersonDTO personDTO = (RALExprPersonDTO) RALExpressionDTO.buildRALExpressionDTO(specId, reportedExpr.getPersonExpr());
			ralExpressionDTO = new RALExprReportedByPersonPositionExprDTO(personDTO, reportedExpr.isDirectly());
	
		} else if (expression instanceof ReportedByPositionExpr) {
		
			ReportedByPositionExpr reportedExpr = (ReportedByPositionExpr) expression;
			ralExpressionDTO = new RALExprReportedByPositionExprDTO(reportedExpr.getPosition().getName(), reportedExpr.isDirectly());
		
		} else if (expression instanceof DelegatesToPersonPositionExpr) {
	
			DelegatesToPersonPositionExpr delegatesExpr = (DelegatesToPersonPositionExpr) expression;
			// FIXME : BAD CODE
			RALExprPersonDTO personDTO = (RALExprPersonDTO) RALExpressionDTO.buildRALExpressionDTO(specId, delegatesExpr.getPersonExpr());
			ralExpressionDTO = new RALExprDelegatesToPersonPositionExprDTO(personDTO);
	
		} else if (expression instanceof DelegatesToPositionExpr) {
		
			DelegatesToPositionExpr delegatesExpr = (DelegatesToPositionExpr) expression;
			ralExpressionDTO = new RALExprDelegatesToPositionExprDTO(delegatesExpr.getPosition().getName());
		
		} else if (expression instanceof DelegatedByPersonPositionExpr) {
	
			DelegatedByPersonPositionExpr delegatedExpr = (DelegatedByPersonPositionExpr) expression;
			// FIXME : BAD CODE
			RALExprPersonDTO personDTO = (RALExprPersonDTO) RALExpressionDTO.buildRALExpressionDTO(specId, delegatedExpr.getPersonExpr());
			ralExpressionDTO = new RALExprDelegatedByPersonPositionExprDTO(personDTO);
	
		} else if (expression instanceof DelegatedByPositionExpr) {
		
			DelegatedByPositionExpr delegatedExpr = (DelegatedByPositionExpr) expression;
			ralExpressionDTO = new RALExprDelegatedByPositionExprDTO(delegatedExpr.getPosition().getName());
		
		} else if (expression instanceof NotExpr) {
			
			NotExpr notExpr = (NotExpr) expression;
			ralExpressionDTO = new RALExprNotDTO(RALExpressionDTO.buildRALExpressionDTO(specId, notExpr.getExpr()));
			
		} else if (expression instanceof CompoundExpr) {
			
			CompoundExpr compoundExpr = (CompoundExpr) expression;
			RALExpressionDTO leftExpr = RALExpressionDTO.buildRALExpressionDTO(specId, compoundExpr.getLeftExpr());
			CompoundType rightExpr = compoundExpr.getRightExpr();
			if (rightExpr instanceof AndExpr) {
				AndExpr andExpr = (AndExpr) rightExpr;
				ralExpressionDTO = new RALExprAndDTO(
						leftExpr,
						RALExpressionDTO.buildRALExpressionDTO(specId, andExpr.getExpr())
					);
			} else if (rightExpr instanceof OrExpr) {		
				OrExpr orExpr = (OrExpr) rightExpr;
				ralExpressionDTO = new RALExprOrDTO(
						leftExpr,
						RALExpressionDTO.buildRALExpressionDTO(specId, orExpr.getExpr())
					);
			}
			
		} else if (expression instanceof CapabilityExpr) {
			
			CapabilityExpr capabilityExpr = (CapabilityExpr) expression;
			ralExpressionDTO = new RALExprHasCapabilityDTO(capabilityExpr.getCapability().getName());
		
		} else if (expression instanceof HistoryExecutingExpr) {
			
			HistoryExecutingExpr historyExecutingExpr = (HistoryExecutingExpr) expression;
			ralExpressionDTO = new RALExprHistoryExecutingDTO(
					RALExprHistoryDTO.QuantifierDTO.fromString(historyExecutingExpr.getQuantifier()),
					historyExecutingExpr.getDataField()
				);
		
		} else if (expression instanceof HistoryInformedExpr) {
			
			HistoryInformedExpr historyInformedExpr = (HistoryInformedExpr) expression;
			ralExpressionDTO = new RALExprHistoryInformedDTO(
					RALExprHistoryDTO.QuantifierDTO.fromString(historyInformedExpr.getQuantifier()),
					historyInformedExpr.getDataField()
				);
		
		} else if (expression instanceof SharesPositionExpr) {
			
			SharesPositionExpr sharesPositionExpr = (SharesPositionExpr) expression;
			ralExpressionDTO = new RALExprSharesPositionDTO(
					RALExprCommonalityDTO.AmountDTO.fromString(sharesPositionExpr.getAmount()),
					RALExpressionDTO.buildRALExpressionDTO(specId, sharesPositionExpr.getPerson())
				);
		
		} else if (expression instanceof SharesUnitExpr) {
			
			SharesUnitExpr sharesUnitExpr = (SharesUnitExpr) expression;
			ralExpressionDTO = new RALExprSharesUnitDTO(
					RALExprCommonalityDTO.AmountDTO.fromString(sharesUnitExpr.getAmount()),
					RALExpressionDTO.buildRALExpressionDTO(specId, sharesUnitExpr.getPerson())
				);
		
		} else if (expression instanceof SharesRoleExpr) {
			
			SharesRoleExpr sharesRoleExpr = (SharesRoleExpr) expression;
			RALExprSharesRoleDTO dto = new RALExprSharesRoleDTO(
					RALExprCommonalityDTO.AmountDTO.fromString(sharesRoleExpr.getAmount()),
					RALExpressionDTO.buildRALExpressionDTO(specId, sharesRoleExpr.getPerson())
				);
			
			if (sharesRoleExpr.getUnit() != null) {
				dto.setUnit(sharesRoleExpr.getUnit().getName());
			}
			
			ralExpressionDTO = dto;
		
		} 
		
		return ralExpressionDTO;
	}
}
