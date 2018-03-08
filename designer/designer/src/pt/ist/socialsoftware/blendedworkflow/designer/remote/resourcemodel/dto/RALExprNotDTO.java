package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprNotDTO extends RALExpressionDTO {
	private RALExpressionDTO expr;

	public RALExprNotDTO() {
		
	}

	public RALExprNotDTO(RALExpressionDTO expr) {
		super();
		this.expr = expr;
	}

	public RALExpressionDTO getExpr() {
		return expr;
	}

	public void setExpr(RALExpressionDTO expr) {
		this.expr = expr;
	}
	
}