package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionDTO extends ProductDTO {
	private String name;
	private String unit;
	private List<String> roles;
	private List<String> delegateToRelations;
	private String reportsTo;

	public PositionDTO() {
	}

	public PositionDTO(String specId, String name, String unit) {
		setSpecId(specId);
		this.name = name;
		this.unit = unit;
	}


	public PositionDTO(String specId, String name, String unit, List<String> roles, List<String> delegateToRelations, String reportsTo) {
		setSpecId(specId);
		this.name = name;
		this.unit = unit;
		this.roles = roles;
		this.delegateToRelations = delegateToRelations;
		this.reportsTo = reportsTo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getDelegateToRelations() {
		return delegateToRelations;
	}

	public void setDelegateToRelations(List<String> delegateToRelations) {
		this.delegateToRelations = delegateToRelations;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	
	
}
