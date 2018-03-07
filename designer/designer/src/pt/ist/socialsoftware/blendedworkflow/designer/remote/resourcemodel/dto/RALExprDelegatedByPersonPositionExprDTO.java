package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatedByPersonPositionExprDTO extends RALExpressionDTO {
	private RALExpressionDTO personExpr;

	public RALExprDelegatedByPersonPositionExprDTO() {
		
	}

	public RALExprDelegatedByPersonPositionExprDTO(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}

	public RALExpressionDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}
}