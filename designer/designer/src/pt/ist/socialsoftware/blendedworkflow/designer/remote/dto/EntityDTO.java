package pt.ist.socialsoftware.blendedworkflow.designer.remote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDTO extends ProductDTO {
	private String name;
	private boolean exists;

	public EntityDTO() {
	}

	public EntityDTO(String specId, String name, boolean exists) {
		setSpecId(specId);
		this.name = name;
		this.exists = exists;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getExists() {
		return this.exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}
}
