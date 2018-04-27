package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesUnitDTO extends RALExprCommonalityDTO {


	public RALExprSharesUnitDTO() {
		
	}

	public RALExprSharesUnitDTO(AmountDTO amountDTO, RALExpressionDTO personExpr) {
		super(amountDTO, personExpr);
	}
}