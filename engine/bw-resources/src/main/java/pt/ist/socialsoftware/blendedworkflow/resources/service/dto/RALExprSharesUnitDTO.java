package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprSharesUnitDTO extends RALExprCommonalityDTO {


	public RALExprSharesUnitDTO() {
		
	}

	public RALExprSharesUnitDTO(AmountDTO amountDTO, RALExpressionDTO personExpr) {
		super(amountDTO, personExpr);
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: SHARES UNIT");

		RALExprCommonality.Amount amount = RALExprCommonality.Amount
				.fromString(getAmountDTO().toString());

		RALExpression expression = getPersonExpr().getRALExpresion(resourceModel, designer, specId);

		if (!(expression instanceof RALPersonExpression)) {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
					"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
		}

		return new RALExprSharesUnit(resourceModel, amount, (RALPersonExpression) expression);
	}
}