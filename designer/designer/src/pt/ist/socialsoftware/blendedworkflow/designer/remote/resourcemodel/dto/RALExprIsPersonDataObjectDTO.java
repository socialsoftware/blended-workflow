package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonDataObjectDTO extends RALExprPersonDTO {
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