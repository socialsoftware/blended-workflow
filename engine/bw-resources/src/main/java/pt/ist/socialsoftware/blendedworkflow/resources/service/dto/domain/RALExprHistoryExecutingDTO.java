package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistory;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistoryExecuting;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryExecutingDTO extends RALExprHistoryDTO {
	private QuantifierDTO quantifierDTO;
	private String dataField;

	public RALExprHistoryExecutingDTO() {
		
	}

	public RALExprHistoryExecutingDTO(QuantifierDTO quantifierDTO, String dataField) {
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
		// log.debug("RALExpression Type: HAS HISTORY EXECUTING");

		if (getDataField() == null) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"Invalid data field name used in HistoryExecuting expression");
		}

		Product product = designer.getProduct(specId, getDataField());

		if (!(product instanceof Entity)) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"The data field in the HAS HISTORY EXECUTING expression is not an attribute");
		}

		RALExprHistory.Quantifier quantifier = RALExprHistory.Quantifier
				.fromString(getQuantifierDTO().toString());
		return new RALExprHistoryExecuting(resourceModel, quantifier, product);
	}
}