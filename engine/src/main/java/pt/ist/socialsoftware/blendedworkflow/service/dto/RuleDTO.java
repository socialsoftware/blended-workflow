package pt.ist.socialsoftware.blendedworkflow.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDTO {
	private String specId;
	private String entityName;
	private String extId;
	private String name;
	private ExpressionDTO expression;

	public RuleDTO() {
	}

	public RuleDTO(String specId, String entityName, String name, ExpressionDTO expDTO) {
		this.specId = specId;
		this.entityName = entityName;
		this.extId = null;
		this.name = name;
		this.expression = expDTO;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpressionDTO getExpression() {
		return expression;
	}

	public void setExpression(ExpressionDTO expDTO) {
		this.expression = expDTO;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
