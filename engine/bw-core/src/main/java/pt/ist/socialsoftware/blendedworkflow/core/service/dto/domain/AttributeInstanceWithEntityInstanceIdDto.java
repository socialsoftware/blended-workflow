package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class AttributeInstanceWithEntityInstanceIdDto {
	private AttributeInstanceDto attributeInstance;
	private String entityInstanceId;
	
	public AttributeInstanceWithEntityInstanceIdDto() {
		
	}

	public AttributeInstanceDto getAttributeInstance() {
		return attributeInstance;
	}

	public void setAttributeInstance(AttributeInstanceDto attributeInstance) {
		this.attributeInstance = attributeInstance;
	}

	public String getEntityInstanceId() {
		return entityInstanceId;
	}

	public void setEntityInstanceId(String entityInstanceId) {
		this.entityInstanceId = entityInstanceId;
	}
}
