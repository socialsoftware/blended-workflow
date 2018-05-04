package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprDelegatesToPersonPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALPersonExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatesToPersonPositionExprDTO extends RALExpressionDTO {
	private RALExprPersonDTO personExpr;

	public RALExprDelegatesToPersonPositionExprDTO() {
		
	}

	public RALExprDelegatesToPersonPositionExprDTO(RALExprPersonDTO personExpr) {
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
		// log.debug("RALExpression Type: DELEGATES TO PERSON POSITION");


		RALExpression expression = getPersonExpr().getRALExpresion(resourceModel, designer, specId);

		if (!(expression instanceof RALPersonExpression)) {
			throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_CAST,
					"Invalid RALExpression cast to " + RALPersonExpression.class.getName());
		}

		return new RALExprDelegatesToPersonPosition(resourceModel, (RALPersonExpression) expression);
	}
}