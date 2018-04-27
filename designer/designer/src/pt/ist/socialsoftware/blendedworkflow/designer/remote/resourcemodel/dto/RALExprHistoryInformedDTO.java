package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprHistoryDTO.QuantifierDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryInformedDTO extends RALExprHistoryDTO {
	private RALExprHistoryDTO.QuantifierDTO quantifier;
	private String dataField;

	public RALExprHistoryInformedDTO() {
		
	}

	public RALExprHistoryInformedDTO(RALExprHistoryDTO.QuantifierDTO quantifier, String dataField) {
		super();
		this.quantifier = quantifier;
		this.dataField = dataField;
	}

	public RALExprHistoryDTO.QuantifierDTO getQuantifierDTO() {
		return quantifier;
	}

	public void setQuantifierDTO(RALExprHistoryDTO.QuantifierDTO quantifier) {
		this.quantifier = quantifier;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}