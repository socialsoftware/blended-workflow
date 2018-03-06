package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprReportedByPersonPositionExprDTO extends RALExpressionDTO {
	private boolean directly;
	private RALExpressionDTO personExpr;

	public RALExprReportedByPersonPositionExprDTO() {
		
	}

	public RALExprReportedByPersonPositionExprDTO(RALExpressionDTO personExpr, boolean directly) {
		this.personExpr = personExpr;
		this.directly = directly;
	}

	public boolean isDirectly() {
		return directly;
	}

	public void setDirectly(boolean directly) {
		this.directly = directly;
	}

	public RALExpressionDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}
}