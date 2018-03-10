package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryInformedDTO extends RALExprHistoryDTO {
	private RALExprHistoryDTO.Quantifier quantifier;
	private String dataField;

	public RALExprHistoryInformedDTO() {
		
	}

	public RALExprHistoryInformedDTO(RALExprHistoryDTO.Quantifier quantifier, String dataField) {
		super();
		this.quantifier = quantifier;
		this.dataField = dataField;
	}

	public RALExprHistoryDTO.Quantifier getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(RALExprHistoryDTO.Quantifier quantifier) {
		this.quantifier = quantifier;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}