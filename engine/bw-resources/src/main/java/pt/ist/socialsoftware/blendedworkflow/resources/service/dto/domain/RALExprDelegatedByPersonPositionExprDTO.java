package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatedByPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALPersonExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatedByPersonPositionExprDTO extends RALExpressionDTO {
	private RALExprPersonDTO personExpr;

	public RALExprDelegatedByPersonPositionExprDTO() {
		
	}

	public RALExprDelegatedByPersonPositionExprDTO(RALExprPersonDTO personExpr) {
		this.personExpr = personExpr;
	}

	public RALExprPersonDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExprPersonDTO personExpr) {
		this.personExpr = personExpr;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: DELEGATED BY PERSON POSITION");

		RALExpression expression = getPersonExpr().getRALExpresion(resourceModel, designer, specId);

		if (!(expression instanceof RALPersonExpression)) {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
					"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
		}

		return new RALExprDelegatedByPersonPosition(resourceModel, (RALPersonExpression) expression);
	}
}