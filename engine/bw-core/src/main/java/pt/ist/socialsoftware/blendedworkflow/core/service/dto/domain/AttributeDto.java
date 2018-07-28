package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;

public class AttributeDto extends ProductDto {
	private String entityExtId;
	private String entityName;
	private String name;
	private String type;
	private boolean mandatory;
	private List<DependenceDto> dependencies;

	public AttributeDto() {
	}

	public AttributeDto(String specId, String externalId, String entityExtId, String entityName, String name,
			String type, boolean mandatory, Set<Dependence> dependencies) {
		super(specId, externalId, type);
		this.entityExtId = entityExtId;
		this.entityName = entityName;
		this.name = name;
		this.type = type;
		if (dependencies != null) {
			this.dependencies = dependencies.stream()
					.sorted((d1, d2) -> d1.getPath().getValue().compareTo(d2.getPath().getValue()))
					.map(d -> new DependenceDto(d)).collect(Collectors.toList());
		}
	}

	public AttributeDto(String specId, String entityExtId, String entityName, String name, String type,
			boolean mandatory) {
		this(specId, null, entityExtId, entityName, name, type, mandatory, null);
	}

	public AttributeDto(Attribute attribute) {
		this(attribute.getEntity().getDataModel().getSpecification().getSpecId(), attribute.getExternalId(),
				attribute.getEntity().getExternalId(), attribute.getEntity().getName(), attribute.getName(),
				attribute.getType().toString(), attribute.getIsMandatory(), attribute.getDependenceSet());
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
		return this.mandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.mandatory = isMandatory;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DependenceDto> getDependencies() {
		return this.dependencies;
	}

	public void setDependencies(List<DependenceDto> dependencies) {
		this.dependencies = dependencies;
	}

}
