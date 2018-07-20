package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRelationDTO {
	private String specId;
	private String entityName;
	private List<DataPersonDTO> persons;
	
	public ResourceRelationDTO() {
	}

	public ResourceRelationDTO(String specId, String entityName) {
		super();
		this.specId = specId;
		this.entityName = entityName;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<DataPersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<DataPersonDTO> persons) {
		this.persons = persons;
	}

}
