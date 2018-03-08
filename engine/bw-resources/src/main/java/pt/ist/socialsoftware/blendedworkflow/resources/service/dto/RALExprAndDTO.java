package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprAndDTO extends RALExpressionDTO {
	private RALExpressionDTO leftExpr;
	private RALExpressionDTO rightExpr;

	public RALExprAndDTO() {
		
	}

	public RALExprAndDTO(RALExpressionDTO leftExpr, RALExpressionDTO rightExpr) {
		super();
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	public RALExpressionDTO getLeftExpr() {
		return leftExpr;
	}

	public void setLeftExpr(RALExpressionDTO leftExpr) {
		this.leftExpr = leftExpr;
	}

	public RALExpressionDTO getRightExpr() {
		return rightExpr;
	}

	public void setRightExpr(RALExpressionDTO rightExpr) {
		this.rightExpr = rightExpr;
	}
}