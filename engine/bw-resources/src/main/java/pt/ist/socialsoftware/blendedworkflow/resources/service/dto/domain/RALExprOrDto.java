package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprOr;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprOrDto extends RALExpressionDto {
	private RALExpressionDto leftExpr;
	private RALExpressionDto rightExpr;

	public RALExprOrDto() {
		
	}

	public RALExprOrDto(RALExpressionDto leftExpr, RALExpressionDto rightExpr) {
		super();
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	public RALExpressionDto getLeftExpr() {
		return leftExpr;
	}

	public void setLeftExpr(RALExpressionDto leftExpr) {
		this.leftExpr = leftExpr;
	}

	public RALExpressionDto getRightExpr() {
		return rightExpr;
	}

	public void setRightExpr(RALExpressionDto rightExpr) {
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