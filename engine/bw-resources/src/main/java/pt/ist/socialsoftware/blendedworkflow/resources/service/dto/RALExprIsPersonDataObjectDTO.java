package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonDataObjectDTO extends RALExpressionDTO {
	private String dataField;

	public RALExprIsPersonDataObjectDTO() {
		
	}

	public RALExprIsPersonDataObjectDTO(String dataField) {
		this.dataField = dataField;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}