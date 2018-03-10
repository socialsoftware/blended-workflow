package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprHistoryExecutingDTO.Quantifier;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryInformedDTO extends RALExpressionDTO {
	private Quantifier quantifier;
	private String dataField;

	public RALExprHistoryInformedDTO() {
		
	}

	public RALExprHistoryInformedDTO(Quantifier quantifier, String dataField) {
		super();
		this.quantifier = quantifier;
		this.dataField = dataField;
	}

	public Quantifier getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(Quantifier quantifier) {
		this.quantifier = quantifier;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}