package pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDTO extends ProductDTO {
	private String name;
	private boolean exists;
	private boolean mandatory;

	public EntityDTO() {
	}

	public EntityDTO(String specId, String name, boolean exists, boolean mandatory) {
		setSpecId(specId);
		this.name = name;
		this.exists = exists;
		this.mandatory = mandatory;
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

	public boolean isMandatory() {
		return this.mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
}
