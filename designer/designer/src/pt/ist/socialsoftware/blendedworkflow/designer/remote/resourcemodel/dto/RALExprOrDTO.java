package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprOrDTO extends RALExpressionDTO {
	private RALExpressionDTO leftExpr;
	private RALExpressionDTO rightExpr;

	public RALExprOrDTO() {
		
	}

	public RALExprOrDTO(RALExpressionDTO leftExpr, RALExpressionDTO rightExpr) {
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