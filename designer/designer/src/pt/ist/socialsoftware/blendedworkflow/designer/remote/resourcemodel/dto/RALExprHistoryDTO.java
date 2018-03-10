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
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO.ResourceRuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryDTO extends RALExpressionDTO {
	public enum Quantifier {
		LEAST("LEAST"), 
		MOST("MOST");
		
		private final String code;
		
		private Quantifier(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}
	
		public static Quantifier fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "LEAST":
					return Quantifier.LEAST;
				case "MOST":
					return Quantifier.MOST;
				default:
					throw new IllegalArgumentException();
			}
		}
	}

	public RALExprHistoryDTO() {

	}
}
