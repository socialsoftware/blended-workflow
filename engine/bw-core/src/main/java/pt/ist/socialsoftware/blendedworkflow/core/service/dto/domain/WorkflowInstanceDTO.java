package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class WorkflowInstanceDTO {
	private String specId;
	private String name;

	public void WorkfowInstanceDTO() {

	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
