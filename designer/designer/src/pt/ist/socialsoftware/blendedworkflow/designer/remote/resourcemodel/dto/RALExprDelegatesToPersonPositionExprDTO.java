package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

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