package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryInformedDTO extends RALExprHistoryDTO {
	private QuantifierDTO quantifierDTO;
	private String dataField;

	public RALExprHistoryInformedDTO() {
		
	}

	public RALExprHistoryInformedDTO(QuantifierDTO quantifierDTO, String dataField) {
		super();
		this.quantifierDTO = quantifierDTO;
		this.dataField = dataField;
	}

	public QuantifierDTO getQuantifierDTO() {
		return quantifierDTO;
	}

	public void setQuantifierDTO(QuantifierDTO quantifierDTO) {
		this.quantifierDTO = quantifierDTO;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}