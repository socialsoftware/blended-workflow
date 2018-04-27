package pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeDTO extends ProductDTO {
	private String entityExtId;
	private String entityName;
	private String name;
	private String type;
	private boolean isMandatory;

	public AttributeDTO() {
	}

	public AttributeDTO(String specId, String productType, String entityExtId, String entityName, String name,
			String type, boolean isMandatory) {
		setSpecId(specId);
		setProductType(productType);

		this.entityExtId = entityExtId;
		this.entityName = entityName;
		this.name = name;
		this.type = type;
		this.isMandatory = isMandatory;
	}

	public String getEntityExtId() {
		return this.entityExtId;
	}

	public void setEntityExtId(String entityExtId) {
		this.entityExtId = entityExtId;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMandatory() {
		return this.isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
