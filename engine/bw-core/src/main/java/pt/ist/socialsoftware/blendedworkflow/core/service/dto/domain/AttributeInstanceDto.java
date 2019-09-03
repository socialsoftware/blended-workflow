package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;

public class AttributeInstanceDto {
	private String externalId;
	private AttributeDto attribute;
	private String value;
	private List<DependenceInstanceDto> dependenceInstances;
	private ProductInstanceState state;

	private boolean toDefine = false;
	

	public AttributeInstanceDto() {
	}

	public AttributeInstanceDto(String externalId, AttributeDto attribute, String value,
			List<DependenceInstanceDto> dependenceInstances, ProductInstanceState state) {
		this.externalId = externalId;
		this.attribute = attribute;
		this.value = value;
		this.dependenceInstances = dependenceInstances;
		this.state = state;
	}

	public AttributeInstanceDto(AttributeInstance attributeInstance) {
		this(attributeInstance.getExternalId(), attributeInstance.getAttribute().getDto(), attributeInstance.getValue(),
				getDependenceInstances(attributeInstance.getAttribute(), attributeInstance), attributeInstance.getState());
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
	
	public ProductInstanceState getState() {
		return this.state;
	}

	public void setState(ProductInstanceState state) {
		this.state = state;
	}

	public boolean isToDefine() {
		return this.toDefine;
	}

	public void setToDefine(boolean toDefine) {
		this.toDefine = toDefine;
	}

	public String print() {
		return getAttribute().getName() + "[" + getValue() + "]";
	}
	
	public Optional<EntityInstance> getEntityInstance(WorkflowInstance workflowInstance) {
		return workflowInstance.getEntityInstanceSet().stream().filter(ei -> ei.hasAttributeInstance(getExternalId())).findFirst();
	}
	
	public Optional<AttributeInstance> getAttributeInstance(WorkflowInstance workflowInstance, String externalId) {
		return getEntityInstance(workflowInstance).get().getAttributeInstanceByExternalId(externalId);
	}
}
