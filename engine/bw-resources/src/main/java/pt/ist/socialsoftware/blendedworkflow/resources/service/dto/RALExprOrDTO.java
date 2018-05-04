package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprAnd;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprOr;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprOrDTO extends RALExpressionDTO {
	private RALExpressionDTO leftExpr;
	private RALExpressionDTO rightExpr;

	public RALExprOrDTO() {
		
	}

	public RALExprOrDTO(RALExpressionDTO leftExpr, RALExpressionDTO rightExpr) {
		super();
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	public RALExpressionDTO getLeftExpr() {
		return leftExpr;
	}

	public void setLeftExpr(RALExpressionDTO leftExpr) {
		this.leftExpr = leftExpr;
	}

	public RALExpressionDTO getRightExpr() {
		return rightExpr;
	}

	public void setRightExpr(RALExpressionDTO rightExpr) {
		this.rightExpr = rightExpr;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		return new RALExprOr(resourceModel,
				getLeftExpr().getRALExpresion(resourceModel, designer, specId),
				getRightExpr().getRALExpresion(resourceModel, designer, specId)
		);
	}
}