package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRuleDTO {
	public enum ResourceRuleType {
		RESPONSIBLE_FOR, INFORMS
	}
	
	private String specId;
	private String dataField;
	private ResourceRuleType type;
	private RALExpressionDTO expression;
	
	public ResourceRuleDTO() {
		super();
	}

	public ResourceRuleDTO(String specId, String dataField, ResourceRuleType type, RALExpressionDTO expression) {
		super();
		this.specId = specId;
		this.dataField = dataField;
		this.type = type;
		this.expression = expression;
	}

	public String getSpecId() {
		return specId;
	}
	
	public void setSpecId(String specId) {
		this.specId = specId;
	}
	
	public String getDataField() {
		return dataField;
	}
	
	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
	
	public ResourceRuleType getType() {
		return type;
	}
	
	public void setType(ResourceRuleType type) {
		this.type = type;
	}
	
	public RALExpressionDTO getExpression() {
		return expression;
	}
	
	public void setExpression(RALExpressionDTO expression) {
		this.expression = expression;
	}
}
