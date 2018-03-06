package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprReportsToPersonPositionExprDTO extends RALExpressionDTO {
	private boolean directly;
	private RALExpressionDTO personExpr;

	public RALExprReportsToPersonPositionExprDTO() {
		
	}

	public RALExprReportsToPersonPositionExprDTO(RALExpressionDTO personExpr, boolean directly) {
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