package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistory;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistoryInformed;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

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

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: HAS HISTORY INFORMED");

		if (getDataField() == null) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"Invalid data field name used in HistoryInformed expression");
		}

		Product product = designer.getProduct(specId, getDataField());

		if (!(product instanceof Entity)) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"The data field in the HAS HISTORY INFORMED expression is not an attribute");
		}

		RALExprHistory.Quantifier quantifier = RALExprHistory.Quantifier
				.fromString(getQuantifierDTO().toString());

		return new RALExprHistoryInformed(resourceModel, quantifier, product);
	}
}