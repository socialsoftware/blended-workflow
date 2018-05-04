package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportedByPosition;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprReportedByPositionExprDTO extends RALExpressionDTO {
	private boolean directly;
	private String position;

	public RALExprReportedByPositionExprDTO() {
		
	}

	public RALExprReportedByPositionExprDTO(String position, boolean directly) {
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
		// log.debug("RALExpression Type: REPORTS TO POSITION");

		return new RALExprReportedByPosition(
				resourceModel,
				resourceModel.getPosition(getPosition()),
				isDirectly()
		);
	}
}