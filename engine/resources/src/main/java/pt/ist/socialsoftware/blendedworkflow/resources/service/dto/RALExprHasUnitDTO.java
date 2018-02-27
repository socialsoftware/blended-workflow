package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHasUnitDTO extends RALExpressionDTO {
	private String unit;

	public RALExprHasUnitDTO() {
		
	}

	public RALExprHasUnitDTO(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}