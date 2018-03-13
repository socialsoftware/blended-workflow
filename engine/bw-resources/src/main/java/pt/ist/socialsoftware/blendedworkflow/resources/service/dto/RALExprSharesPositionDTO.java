package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesPositionDTO extends RALExprCommonalityDTO {
	public RALExprSharesPositionDTO() {
		
	}
	
	public RALExprSharesPositionDTO(AmountDTO amountDTO, RALExpressionDTO personExpr) {
		super(amountDTO, personExpr);
	}
}