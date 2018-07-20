package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;

public class DataModelDto {
	private String specId;
	private String specName;
	List<EntityDto> entities;
	List<RelationDto> associations;

	public DataModelDto(DataModel dataModel) {
		this.specId = dataModel.getSpecification().getSpecId();
		this.specName = dataModel.getSpecification().getName();
		this.entities = dataModel.getEntitySet().stream().sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
				.map(e -> new EntityDto(e)).collect(Collectors.toList());
		// TODO Auto-generated constructor stub
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return this.specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public List<EntityDto> getEntities() {
		return this.entities;
	}

	public void setEntities(List<EntityDto> entities) {
		this.entities = entities;
	}

	public List<RelationDto> getAssociations() {
		return this.associations;
	}

	public void setAssociations(List<RelationDto> associations) {
		this.associations = associations;
	}

}
