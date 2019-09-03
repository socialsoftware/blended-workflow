package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;

public class EntityInstanceDto {
	public enum Depth {
		DEEP, SHALLOW
	}


	private String externalId;
	private String id;
	private EntityDto entity;
	private List<AttributeInstanceDto> attributeInstances = new ArrayList<>();
	private List<LinkDto> links = new ArrayList<>();
	private ProductInstanceState state;

	public EntityInstanceDto() {
	}

	public EntityInstanceDto(EntityInstance entityInstance, Depth depth) {
		this.externalId = entityInstance.getExternalId();
		this.id = entityInstance.getId();
		this.entity = entityInstance.getEntity().getDto();
		this.state = entityInstance.getState();

		if (depth.equals(Depth.DEEP)) {
			this.attributeInstances = entityInstance.getEntity().getAttributeSet().stream()
					.sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
					.map(a -> getAttributeInstanceDto(entityInstance, a)).collect(Collectors.toList());
			this.setLinks(entityInstance.getEntity().getMulConditions().stream()
					.sorted((m1, m2) -> m1.getRolename().compareTo(m2.getRolename()))
					.map(m -> getLinkDto(entityInstance, m)).collect(Collectors.toList()));
		}
	}

	protected AttributeInstanceDto getAttributeInstanceDto(EntityInstance entityInstance, Attribute attribute) {
		Optional<AttributeInstance> attributeInstance = entityInstance.getAttributeInstanceByName(attribute.getName());
		if (attributeInstance.isPresent()) {
			return new AttributeInstanceDto(attributeInstance.get());
		} else {
			return new AttributeInstanceUndefDto(attribute, entityInstance);
		}
	}

	protected LinkDto getLinkDto(EntityInstance entityInstance, MulCondition mulCondition) {
		Set<EntityInstance> entityInstances = entityInstance.getEntityInstancesByRolename(mulCondition.getRolename());
		if (entityInstances.isEmpty()) {
			return new LinkUndefDto(mulCondition);
		} else {
			return new LinkDto(entityInstances, mulCondition);
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

	public List<AttributeInstanceDto> getAttributeInstances() {
		return this.attributeInstances;
	}

	public void setAttributeInstances(List<AttributeInstanceDto> attributeInstances) {
		this.attributeInstances = attributeInstances;
	}

	public List<LinkDto> getLinks() {
		return this.links;
	}

	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}
	
	public ProductInstanceState getState() {
		return this.state;
	}

	public void setState(ProductInstanceState state) {
		this.state = state;
	}

	public String print() {
		String id = getId() != null ? getId() : "undef";
		return getEntity().getName() + "[" + id + "]" + printToDefine() + "{\r\n"
				+ getAttributeInstances().stream().map(ai -> ai.print()).collect(Collectors.joining(",\r\n")) + "\r\n"
				+ getLinks().stream().map(l -> l.print()).collect(Collectors.joining(",\r\n")) + "\r\n}";
	}

	protected String printToDefine() {
		return "";
	}

	public String shortPrint() {
		return getEntity().getName() + "[" + getId() + "]";
	}

}
