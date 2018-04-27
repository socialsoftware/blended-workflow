package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesPositionDTO extends RALExprCommonalityDTO {
	public RALExprSharesPositionDTO() {
		
	}
	
	public RALExprSharesPositionDTO(AmountDTO amount, RALExpressionDTO personExpr) {
		super(amount, personExpr);
	}
}