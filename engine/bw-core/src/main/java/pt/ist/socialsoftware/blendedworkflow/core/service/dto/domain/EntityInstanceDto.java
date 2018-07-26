package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;

public class EntityInstanceDto {
	private String externalId;
	private String id;
	private EntityDto entity;
	private List<AttributeInstanceDto> attributes;

	public EntityInstanceDto() {
	}

	public EntityInstanceDto(EntityInstance entityInstance) {
		this.externalId = entityInstance.getExternalId();
		this.setId(entityInstance.getId());
		this.entity = entityInstance.getEntity().getDTO();
		this.attributes = entityInstance.getEntity().getAttributeSet().stream()
				.sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
				.map(a -> getAttributeInstanceDto(entityInstance, a)).collect(Collectors.toList());
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntityDto getEntity() {
		return this.entity;
	}

	public void setEntity(EntityDto entity) {
		this.entity = entity;
	}

	public List<AttributeInstanceDto> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<AttributeInstanceDto> attributes) {
		this.attributes = attributes;
	}

	private AttributeInstanceDto getAttributeInstanceDto(EntityInstance entityInstance, Attribute attribute) {
		Optional<AttributeInstance> attributeInstance = entityInstance.getAttributeInstanceByName(attribute.getName());
		if (attributeInstance.isPresent()) {
			return new AttributeInstanceDto(attributeInstance.get());
		} else {
			return new UndefinedAttributeInstanceDto(attribute);
		}
	}

}
