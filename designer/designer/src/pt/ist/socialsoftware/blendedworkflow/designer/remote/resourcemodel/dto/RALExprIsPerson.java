package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPerson extends RALExpressionDTO {
	private String person;

	public RALExprIsPerson() {
		
	}

	public RALExprIsPerson(String person) {
		this.person = person;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}	
}