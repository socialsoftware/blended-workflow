package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;

public class AttributeInstanceDto {
	private String externalId;
	private AttributeDto attribute;
	private String value;

	public AttributeInstanceDto() {
	}

	public AttributeInstanceDto(AttributeInstance attributeInstance) {
		this.externalId = attributeInstance.getExternalId();
		this.attribute = attributeInstance.getAttribute().getDTO();
		this.value = attributeInstance.getValue();
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public AttributeDto getAttribute() {
		return this.attribute;
	}

	public void setAttribute(AttributeDto attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
