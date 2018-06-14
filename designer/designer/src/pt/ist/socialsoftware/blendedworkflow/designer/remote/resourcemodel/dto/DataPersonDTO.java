package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPersonDTO {
	public String name;
	public Map<String, String> assignments;
	
	public DataPersonDTO(String name, Map<String, String> assignments) {
		this.name = name;
		this.assignments = assignments;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getAssignments() {
		return assignments;
	}
	public void setAssignments(Map<String, String> assignments) {
		this.assignments = assignments;
	}
	
	
}