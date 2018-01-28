package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitDTO extends ProductDTO {
	private String name;
	private String description;

	public UnitDTO() {
	}
	
	public UnitDTO(String specId, String name, String description) {
		setSpecId(specId);
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
