package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonDataObject;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

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

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException, BWException {
		// log.debug("RALExpression Type: IS PERSON IN DATA FIELD");

		if (getDataField() == null) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"Invalid data field name used in IS PERSON IN DATA FIELD expression");
		}

		Product product = designer.getProduct(specId, getDataField());

		if (!(product instanceof Entity)) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"The data field in the IS PERSON IN DATA FIELD expression is not an attribute");
		}

		return new RALExprIsPersonDataObject(resourceModel, getDataField());
	}
}