package pt.ist.socialsoftware.blendedworkflow.designer.remote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecDTO {
	private String specId;
	private String name;

	public SpecDTO() {
	}

	public SpecDTO(String specId, String name) {
		this.specId = specId;
		this.name = name;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
