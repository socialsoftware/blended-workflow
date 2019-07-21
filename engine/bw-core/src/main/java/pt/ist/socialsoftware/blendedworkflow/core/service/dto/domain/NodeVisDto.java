package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeVisDto {
	private String id;
	private String label;
	private String description;

	public NodeVisDto() {
	}

	public NodeVisDto(String id, String label, String title) {
		this.id = id;
		this.label = label;
		this.description = title;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
