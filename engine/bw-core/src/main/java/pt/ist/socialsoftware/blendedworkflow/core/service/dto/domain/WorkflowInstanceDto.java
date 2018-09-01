package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class WorkflowInstanceDto {
	private String specId;
	private String name;

	public void WorkfowInstanceDTO() {

	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
