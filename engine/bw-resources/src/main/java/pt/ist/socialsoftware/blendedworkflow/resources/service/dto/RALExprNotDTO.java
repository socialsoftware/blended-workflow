package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

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