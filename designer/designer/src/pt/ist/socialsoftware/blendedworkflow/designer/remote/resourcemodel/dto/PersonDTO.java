package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO extends ProductDTO {
	private String name;
	private String body;

	public PersonDTO() {
	}

	public PersonDTO(String specId, String name) {
		setSpecId(specId);
		this.name = name;
	}
	
	public PersonDTO(String specId, String name, String body) {
		setSpecId(specId);
		this.name = name;
		this.body = body;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
