package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHasRoleDTO extends RALExpressionDTO {
	private String role;
	private String unit;

	public RALExprHasRoleDTO() {
		
	}
	
	public RALExprHasRoleDTO(String role) {
		super();
		this.role = role;
	}

	public RALExprHasRoleDTO(String role, String unit) {
		this.role = role;
		this.unit = unit;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}