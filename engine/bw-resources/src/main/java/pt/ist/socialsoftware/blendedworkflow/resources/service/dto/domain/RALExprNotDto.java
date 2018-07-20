package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprNot;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprNotDto extends RALExpressionDto {
	private RALExpressionDto expr;

	public RALExprNotDto() {
		
	}

	public RALExprNotDto(RALExpressionDto expr) {
		super();
		this.expr = expr;
	}

	public RALExpressionDto getExpr() {
		return expr;
	}

	public void setExpr(RALExpressionDto expr) {
		this.expr = expr;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: NOT");

		return new RALExprNot(resourceModel, getExpr().getRALExpresion(resourceModel, designer, specId));
	}
}