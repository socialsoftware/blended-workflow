package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesRoleDTO extends RALExprCommonalityDTO {
	
	private String unit;

	public RALExprSharesRoleDTO() {
		
	}
	
	public RALExprSharesRoleDTO(Amount amount, RALExpressionDTO personExpr) {
		super(amount, personExpr);
	}
	
	public RALExprSharesRoleDTO(Amount amount, RALExpressionDTO personExpr, String unit) {
		super(amount, personExpr);
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}