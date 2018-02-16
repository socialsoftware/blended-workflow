package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRelationDTO {
	private String specId;
	private String entityName;

	public ResourceRelationDTO() {
	}

	public ResourceRelationDTO(String specId, String entityName) {
		super();
		this.specId = specId;
		this.entityName = entityName;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
