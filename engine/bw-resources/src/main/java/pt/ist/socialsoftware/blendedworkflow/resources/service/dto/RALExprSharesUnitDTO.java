package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesUnitDTO extends RALExprCommonalityDTO {


	public RALExprSharesUnitDTO() {
		
	}

	public RALExprSharesUnitDTO(Amount amount, RALExpressionDTO personExpr) {
		super(amount, personExpr);
	}
}