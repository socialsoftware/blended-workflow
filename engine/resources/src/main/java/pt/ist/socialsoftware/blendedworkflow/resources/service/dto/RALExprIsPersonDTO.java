package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonDTO extends RALExpressionDTO {
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