package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.AnyoneExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonDataObject;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonID;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonInDuty;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO.ResourceRuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
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
			ralExpressionDTO = new RALExprIsPersonInTaskDutyDTO(ResourceRuleType.fromAsgmtString(isPersonInDutyExpr.getTaskDuty()), isPersonInDutyExpr.getDataField());
			System.out.println("IsPersonInDuty " + isPersonInDutyExpr.getDataField());
		} else if (expression instanceof AnyoneExpr) {
			ralExpressionDTO = new RALExprAnyoneDTO();
			System.out.println("Anyone");
		}
		return ralExpressionDTO;
	}
}
