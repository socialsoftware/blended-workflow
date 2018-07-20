package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRelationDto {
	private String specId;
	private String entityName;

	public ResourceRelationDto() {
	}

	public ResourceRelationDto(String specId, String entityName) {
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
