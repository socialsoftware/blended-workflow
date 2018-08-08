package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;

public class AttributeInstanceDto {
	private String externalId;
	private AttributeDto attribute;
	private String value;
	private List<DependenceInstanceDto> dependenceInstances;

	public AttributeInstanceDto(String externalId, AttributeDto attribute, String value,
			List<DependenceInstanceDto> dependenceInstances) {
		this.externalId = externalId;
		this.attribute = attribute;
		this.value = value;
		this.dependenceInstances = dependenceInstances;
	}

	public AttributeInstanceDto(AttributeInstance attributeInstance) {
		this(attributeInstance.getExternalId(), attributeInstance.getAttribute().getDto(), attributeInstance.getValue(),
				getDependenceInstances(attributeInstance.getAttribute(), attributeInstance));
	}

	protected static List<DependenceInstanceDto> getDependenceInstances(Attribute attribute,
			ProductInstance productInstance) {
		return attribute.getDependenceSet().stream()
				.sorted((d1, d2) -> d1.getPath().getValue().compareTo(d2.getPath().getValue()))
				.map(d -> new DependenceInstanceDto(d, productInstance)).collect(Collectors.toList());
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

	public List<DependenceInstanceDto> getDependenceInstances() {
		return this.dependenceInstances;
	}

	public void setDependenceInstances(List<DependenceInstanceDto> dependenceInstances) {
		this.dependenceInstances = dependenceInstances;
	}

}
