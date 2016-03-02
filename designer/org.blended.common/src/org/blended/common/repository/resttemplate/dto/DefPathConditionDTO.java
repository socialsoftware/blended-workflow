package org.blended.common.repository.resttemplate.dto;

import org.blended.common.common.CommonFactory;
import org.blended.common.common.PathDefinition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefPathConditionDTO {
	private String specId;
	private String path;

	public DefPathConditionDTO() {
	};

	public PathDefinition buildPathDefinition() {
		CommonFactory factory = CommonFactory.eINSTANCE;

		PathDefinition pathDefinition = factory.createPathDefinition();
		pathDefinition.setPath(path);
		return pathDefinition;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
