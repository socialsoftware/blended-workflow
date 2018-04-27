package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryExecutingDTO extends RALExprHistoryDTO {
	private RALExprHistoryDTO.QuantifierDTO quantifier;
	private String dataField;

	public RALExprHistoryExecutingDTO() {
		
	}

	public RALExprHistoryExecutingDTO(RALExprHistoryDTO.QuantifierDTO quantifier, String dataField) {
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