package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonDTO extends RALExprPersonDTO {
	private String person;

	public RALExprIsPersonDTO() {
		
	}

	public RALExprIsPersonDTO(String person) {
		this.person = person;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}	
}