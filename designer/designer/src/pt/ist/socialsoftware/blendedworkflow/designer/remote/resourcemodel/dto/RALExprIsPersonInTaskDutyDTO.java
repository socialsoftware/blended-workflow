package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO.ResourceRuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonInTaskDutyDTO extends RALExpressionDTO {
	private ResourceRuleType taskDuty;
	private String dataField;

	public RALExprIsPersonInTaskDutyDTO() {
		
	}

	public RALExprIsPersonInTaskDutyDTO(ResourceRuleType taskDuty, String dataField) {
		super();
		this.taskDuty = taskDuty;
		this.dataField = dataField;
	}

	public ResourceRuleType getTaskDuty() {
		return taskDuty;
	}

	public void setTaskDuty(ResourceRuleType taskDuty) {
		this.taskDuty = taskDuty;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}