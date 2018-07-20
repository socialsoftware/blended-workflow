package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDto {
	private String specId;
	private String entityName;
	private String extId;
	private String name;
	private ExpressionDto expression;
	private String toStringExpression;

	public RuleDto() {
	}

	public RuleDto(String specId, String entityName, String name, ExpressionDto expDTO) {
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

	public ExpressionDto getExpression() {
		return expression;
	}

	public void setExpression(ExpressionDto expDTO) {
		this.expression = expDTO;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getToStringExpression() {
		return toStringExpression;
	}

	public void setToStringExpression(String toStringExpression) {
		this.toStringExpression = toStringExpression;
	}

}
