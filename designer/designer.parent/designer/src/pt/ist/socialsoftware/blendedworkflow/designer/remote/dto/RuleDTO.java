package pt.ist.socialsoftware.blendedworkflow.designer.remote.dto;

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

	public RuleDTO(String specId, String entityName, String name, ExpressionDTO expression) {
		this.specId = specId;
		this.entityName = entityName;
		this.extId = null;
		this.name = name;
		this.expression = expression;
	}

	public RuleDTO(String specId, String entityName, String name) {
		this.specId = specId;
		this.entityName = entityName;
		this.extId = null;
		this.name = name;
		this.expression = null;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpressionDTO getExpression() {
		return this.expression;
	}

	public void setExpression(ExpressionDTO expression) {
		this.expression = expression;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
