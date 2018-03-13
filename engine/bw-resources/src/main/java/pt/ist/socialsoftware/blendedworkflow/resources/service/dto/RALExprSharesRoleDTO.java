package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesRoleDTO extends RALExprCommonalityDTO {
	
	private String unit;

	public RALExprSharesRoleDTO() {
		
	}
	
	public RALExprSharesRoleDTO(AmountDTO amountDTO, RALExpressionDTO personExpr) {
		super(amountDTO, personExpr);
	}
	
	public RALExprSharesRoleDTO(AmountDTO amountDTO, RALExpressionDTO personExpr, String unit) {
		super(amountDTO, personExpr);
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}