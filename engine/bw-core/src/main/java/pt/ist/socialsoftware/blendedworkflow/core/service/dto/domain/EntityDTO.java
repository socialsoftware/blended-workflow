package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;

public class EntityDTO extends ProductDTO {
	private String name;
	private boolean exists;
	private boolean mandatory;

	public EntityDTO() {
	}

	public EntityDTO(String specId, String extId, String name, boolean exists, boolean mandatory) {
		super(specId, extId, ProductType.ENTITY.name());
		this.name = name;
		this.exists = exists;
		this.setMandatory(mandatory);
	}

	public EntityDTO(String specId, String name, boolean exists, boolean mandatory) {
		this(specId, null, name, exists, mandatory);
	}

	public EntityDTO(String specId, String name, boolean exists) {
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

}
