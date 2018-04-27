package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprReportsToPersonPositionExprDTO extends RALExpressionDTO {
	private boolean directly;
	private RALExprPersonDTO personExpr;

	public RALExprReportsToPersonPositionExprDTO() {
		
	}

	public RALExprReportsToPersonPositionExprDTO(RALExprPersonDTO personExpr, boolean directly) {
		this.personExpr = personExpr;
		this.directly = directly;
	}

	public boolean isDirectly() {
		return directly;
	}

	public void setDirectly(boolean directly) {
		this.directly = directly;
	}

	public RALExprPersonDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExprPersonDTO personExpr) {
		this.personExpr = personExpr;
	}
}