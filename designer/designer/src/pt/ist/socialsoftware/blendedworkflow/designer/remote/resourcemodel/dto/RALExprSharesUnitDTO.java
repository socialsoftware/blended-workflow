package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesUnitDTO extends RALExprCommonalityDTO {


	public RALExprSharesUnitDTO() {
		
	}

	public RALExprSharesUnitDTO(AmountDTO amount, RALExpressionDTO personExpr) {
		super(amount, personExpr);
	}
}