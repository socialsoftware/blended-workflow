package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesRoleDTO extends RALExprCommonalityDTO {
	
	private String unit;

	public RALExprSharesRoleDTO() {
		
	}
	
	public RALExprSharesRoleDTO(AmountDTO amountDTO, RALExpressionDTO personExpr) {
		super(amountDTO, personExpr);
	}
	
	public RALExprSharesRoleDTO(AmountDTO amountDTO, RALExpressionDTO personExpr, String unit) {
		super(amountDTO, personExpr);
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: SHARES ROLE");


		RALExprCommonality.Amount amount = RALExprCommonality.Amount
				.fromString(getAmountDTO().toString());

		RALExpression expression = getPersonExpr().getRALExpresion(resourceModel, designer, specId);

		if (!(expression instanceof RALPersonExpression)) {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
					"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
		}

		RALExprSharesRole ralExprSharesRole = new RALExprSharesRole(resourceModel, amount,
				(RALPersonExpression) expression);

		if (getUnit() != null) {
			ralExprSharesRole.setUnit(resourceModel.getUnit(getUnit()));
		}

		return ralExprSharesRole;
	}
}