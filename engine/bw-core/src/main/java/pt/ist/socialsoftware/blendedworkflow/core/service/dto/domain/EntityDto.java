package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;

public class EntityDto extends ProductDto {
	private String name;

	private boolean exists;
	private boolean mandatory;
	private List<AttributeDto> attributes;

	public EntityDto() {
	}

	public EntityDto(String specId, String extId, String name, boolean exists, boolean mandatory) {
		super(specId, extId, ProductType.ENTITY.name());
		this.name = name;
		this.exists = exists;
		this.setMandatory(mandatory);
	}

	public EntityDto(Entity entity) {
		this(entity.getDataModel().getSpecification().getSpecId(), entity.getExternalId(), entity.getName(),
				entity.getExists(), entity.getMandatory());

		this.attributes = entity.getAttributeSet().stream().sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
				.map(a -> new AttributeDto(a)).collect(Collectors.toList());
	}

	public EntityDto(String specId, String name, boolean exists, boolean mandatory) {
		this(specId, null, name, exists, mandatory);
	}

	public EntityDto(String specId, String name, boolean exists) {
		this(specId, null, name, exists, false);
	}

	public boolean getExists() {
		return this.exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMandatory() {
		return this.mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public List<AttributeDto> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<AttributeDto> attributes) {
		this.attributes = attributes;
	}

}
