package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRuleDTO {
	public enum ResourceRuleType {
		HAS_RESPONSIBLE("has responsible"), 
		INFORMS("informs");
		
		private final String code;
		
		private ResourceRuleType(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}

		public static ResourceRuleType fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "has responsible":
					return ResourceRuleType.HAS_RESPONSIBLE;
				case "informs":
					return ResourceRuleType.INFORMS;
				default:
					throw new IllegalArgumentException();
			}
		}
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
