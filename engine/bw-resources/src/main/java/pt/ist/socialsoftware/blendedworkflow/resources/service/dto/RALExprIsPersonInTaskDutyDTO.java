package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.ResourceRuleDTO.ResourceRuleTypeDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonInTaskDutyDTO extends RALExpressionDTO {
	private ResourceRuleTypeDTO taskDuty;
	private String dataField;

	public RALExprIsPersonInTaskDutyDTO() {
		
	}

	public RALExprIsPersonInTaskDutyDTO(ResourceRuleTypeDTO taskDuty, String dataField) {
		super();
		this.taskDuty = taskDuty;
		this.dataField = dataField;
	}

	public ResourceRuleTypeDTO getTaskDuty() {
		return taskDuty;
	}

	public void setTaskDuty(ResourceRuleTypeDTO taskDuty) {
		this.taskDuty = taskDuty;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}