package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Set;

public class WorkItemDTO {
	private String specId;
	private String specName;
	private String workflowInstanceName;
	private Set<DefinitionGroupDTO> definitionGroupSet;

	public WorkItemDTO() {
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getWorkflowInstanceName() {
		return workflowInstanceName;
	}

	public void setWorkflowInstanceName(String workflowInstanceName) {
		this.workflowInstanceName = workflowInstanceName;
	}

	public Set<DefinitionGroupDTO> getDefinitionGroupSet() {
		return definitionGroupSet;
	}

	public void setDefinitionGroupSet(Set<DefinitionGroupDTO> definitionGroupSet) {
		this.definitionGroupSet = definitionGroupSet;
	}
}
