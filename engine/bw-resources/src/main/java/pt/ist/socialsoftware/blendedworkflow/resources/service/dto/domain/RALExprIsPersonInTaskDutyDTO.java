package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonInTaskDuty;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprIsPersonInTaskDutyDTO extends RALExprPersonDTO {
	private ResourceRuleDTO.ResourceRuleTypeDTO taskDuty;
	private String dataField;

	public RALExprIsPersonInTaskDutyDTO() {
		
	}

	public RALExprIsPersonInTaskDutyDTO(ResourceRuleDTO.ResourceRuleTypeDTO taskDuty, String dataField) {
		super();
		this.taskDuty = taskDuty;
		this.dataField = dataField;
	}

	public ResourceRuleDTO.ResourceRuleTypeDTO getTaskDuty() {
		return taskDuty;
	}

	public void setTaskDuty(ResourceRuleDTO.ResourceRuleTypeDTO taskDuty) {
		this.taskDuty = taskDuty;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}

	@Override
    public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: IS PERSON IN TASK DUTY");

		if (getDataField() == null) {
			throw new RMException(RMErrorType.INVALID_DATA_FIELD,
					"Invalid data field name used in IS PERSON IN TASK DUTY expression");
		}

		RALExpression.TaskDutyType taskDuty = RALExpression.TaskDutyType
				.fromResourceRuleString(getTaskDuty().toString());

		Product product = designer.getProduct(specId, getDataField());

		return new RALExprIsPersonInTaskDuty(resourceModel, taskDuty, getDataField());
	}
}