package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportsToPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprReportsToPositionExprDto extends RALExpressionDto {
	private boolean directly;
	private String position;

	public RALExprReportsToPositionExprDto() {
		
	}

	public RALExprReportsToPositionExprDto(String position, boolean directly) {
		super();
		this.position = position;
		this.directly = directly;
	}

	public boolean isDirectly() {
		return directly;
	}

	public void setDirectly(boolean directly) {
		this.directly = directly;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		return new RALExprReportsToPosition(
				resourceModel,
				resourceModel.getPosition(getPosition()),
				isDirectly()
		);
	}
}