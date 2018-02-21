package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.AnyoneExpr;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonDataObject;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonID;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.IsPersonInDuty;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.RALExpression;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExpressionDTO {
	public static RALExpressionDTO buildRALExpressionDTO(String specId, RALExpression expression) {
		RALExpressionDTO ralExpressionDTO = null;
		if (expression instanceof IsPersonID) {
			IsPersonID isPersonExpr = (IsPersonID) expression;
			ralExpressionDTO = new RALExprIsPerson(isPersonExpr.getPerson());
		} else if (expression instanceof IsPersonDataObject) {
			IsPersonDataObject isDataObjExpr = (IsPersonDataObject) expression;
			System.out.println("IsPersonDataObject " + isDataObjExpr.getDataField());
		} else if (expression instanceof IsPersonInDuty) {
			IsPersonInDuty isPersonInDutyExpr = (IsPersonInDuty) expression;
			System.out.println("IsPersonInDuty " + isPersonInDutyExpr.getDataField());
		} else if (expression instanceof AnyoneExpr) {
			System.out.println("Anyone");
		}
		return ralExpressionDTO;
	}
}
