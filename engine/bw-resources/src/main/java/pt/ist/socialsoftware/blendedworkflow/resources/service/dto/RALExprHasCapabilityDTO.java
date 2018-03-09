package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHasCapabilityDTO extends RALExpressionDTO {
	private String capability;

	public RALExprHasCapabilityDTO() {
		
	}

	public RALExprHasCapabilityDTO(String capability) {
		this.capability = capability;
	}

	public String getCapability() {
		return capability;
	}

	public void setCapability(String capability) {
		this.capability = capability;
	}
	
	

	
}