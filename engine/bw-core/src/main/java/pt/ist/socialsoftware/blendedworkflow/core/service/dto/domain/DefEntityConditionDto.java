package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefEntityConditionDto {
	private String specId;
	private String extId;
	private String entityName;
	private boolean exists;
	private boolean mandatory;
	private String path;

	public DefEntityConditionDto() {
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public boolean isExists() {
		return this.exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isMandatory() {
		return this.mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

}
