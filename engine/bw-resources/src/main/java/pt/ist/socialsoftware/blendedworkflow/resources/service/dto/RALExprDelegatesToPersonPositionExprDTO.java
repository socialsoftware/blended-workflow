package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatesToPersonPositionExprDTO extends RALExpressionDTO {
	private RALExpressionDTO personExpr;

	public RALExprDelegatesToPersonPositionExprDTO() {
		
	}

	public RALExprDelegatesToPersonPositionExprDTO(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}

	public RALExpressionDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}
}