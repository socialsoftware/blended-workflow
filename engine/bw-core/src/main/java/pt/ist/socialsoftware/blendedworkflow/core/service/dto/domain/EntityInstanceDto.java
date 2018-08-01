package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;

public class EntityInstanceDto {
	public enum Depth {
		DEEP, SHALLOW
	}

	private String externalId;
	private String id;
	private EntityDto entity;
	private List<AttributeInstanceDto> attributes = new ArrayList<>();
	private List<LinkDto> links = new ArrayList<>();

	public EntityInstanceDto() {
	}

	public EntityInstanceDto(EntityInstance entityInstance, Depth depth) {
		this.externalId = entityInstance.getExternalId();
		this.id = entityInstance.getId();
		this.entity = entityInstance.getEntity().getDTO();

		if (depth.equals(Depth.DEEP)) {
			this.attributes = entityInstance.getEntity().getAttributeSet().stream()
					.sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
					.map(a -> getAttributeInstanceDto(entityInstance, a)).collect(Collectors.toList());
			this.setLinks(entityInstance.getEntity().getMulConditions().stream()
					.sorted((m1, m2) -> m1.getRolename().compareTo(m2.getRolename()))
					.map(m -> new LinkDto(entityInstance, m)).collect(Collectors.toList()));
		}
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

	public List<LinkDto> getLinks() {
		return this.links;
	}

	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}

}
