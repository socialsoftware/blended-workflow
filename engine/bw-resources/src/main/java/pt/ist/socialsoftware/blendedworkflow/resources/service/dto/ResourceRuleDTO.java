package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRuleDTO {
	public enum ResourceRuleTypeDTO {
		HAS_RESPONSIBLE("has responsible"), 
		INFORMS("informs");
		
		private final String code;
		
		private ResourceRuleTypeDTO(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}

		public static ResourceRuleTypeDTO fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "has responsible":
					return ResourceRuleTypeDTO.HAS_RESPONSIBLE;
				case "informs":
					return ResourceRuleTypeDTO.INFORMS;
				default:
					throw new IllegalArgumentException();
			}
		}
		
		public static ResourceRuleTypeDTO fromAsgmtString(String code) throws IllegalArgumentException {
			switch (code) {
			case "RESPONSIBLE FOR":
				return ResourceRuleTypeDTO.HAS_RESPONSIBLE;
			case "INFORMED ABOUT":
				return ResourceRuleTypeDTO.INFORMS;
			default:
				throw new IllegalArgumentException();
		}
		}
	}
	
	private String specId;
	private String dataField;
	private ResourceRuleTypeDTO type;
	private RALExpressionDTO expression;
	
	public ResourceRuleDTO() {
		super();
	}

	public ResourceRuleDTO(String specId, String dataField, ResourceRuleTypeDTO type, RALExpressionDTO expression) {
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
	
	public ResourceRuleTypeDTO getType() {
		return type;
	}
	
	public void setType(ResourceRuleTypeDTO type) {
		this.type = type;
	}
	
	public RALExpressionDTO getExpression() {
		return expression;
	}
	
	public void setExpression(RALExpressionDTO expression) {
		this.expression = expression;
	}
}
