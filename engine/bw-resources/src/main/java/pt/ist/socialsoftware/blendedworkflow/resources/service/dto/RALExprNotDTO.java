package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprNot;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprNotDTO extends RALExpressionDTO {
	private RALExpressionDTO expr;

	public RALExprNotDTO() {
		
	}

	public RALExprNotDTO(RALExpressionDTO expr) {
		super();
		this.expr = expr;
	}

	public RALExpressionDTO getExpr() {
		return expr;
	}

	public void setExpr(RALExpressionDTO expr) {
		this.expr = expr;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: NOT");

		return new RALExprNot(resourceModel, getExpr().getRALExpresion(resourceModel, designer, specId));
	}
}