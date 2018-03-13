package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatedByPersonPositionExprDTO extends RALExpressionDTO {
	private RALExprPersonDTO personExpr;

	public RALExprDelegatedByPersonPositionExprDTO() {
		
	}

	public RALExprDelegatedByPersonPositionExprDTO(RALExprPersonDTO personExpr) {
		this.personExpr = personExpr;
	}

	public RALExprPersonDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExprPersonDTO personExpr) {
		this.personExpr = personExpr;
	}
}