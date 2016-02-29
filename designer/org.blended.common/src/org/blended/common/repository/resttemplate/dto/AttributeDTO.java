package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeDTO extends ProductDTO {
	private String entityExtId;
	private String entityName;
	private String groupExtId;
	private String groupName;
	private String name;
	private String type;
	private boolean isMandatory;

	public AttributeDTO() {
	}

	public AttributeDTO(String specId, String productType, String entityExtId, String entityName, String groupExtId,
			String groupName, String name, String type, boolean isMandatory) {
		setSpecId(specId);
		setProductType(productType);

		this.entityExtId = entityExtId;
		this.entityName = entityName;
		this.groupExtId = groupExtId;
		this.groupName = groupName;
		this.name = name;
		this.type = type;
		this.isMandatory = isMandatory;
	}

	public String getEntityExtId() {
		return entityExtId;
	}

	public void setEntityExtId(String entityExtId) {
		this.entityExtId = entityExtId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getGroupExtId() {
		return groupExtId;
	}

	public void setGroupExtId(String groupExtId) {
		this.groupExtId = groupExtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
