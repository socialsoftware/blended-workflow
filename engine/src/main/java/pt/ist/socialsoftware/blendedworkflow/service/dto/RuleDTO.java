package pt.ist.socialsoftware.blendedworkflow.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDTO {
    private String extId;
    private String dataModelExtId;
    private String conditionModelExtId;
    private String name;
    private ExpressionDTO expression;

    public RuleDTO() {
    }

    public RuleDTO(String dataModelExtId, String name, ExpressionDTO expDTO) {
        this.extId = null;
        this.dataModelExtId = dataModelExtId;
        this.conditionModelExtId = null;
        this.name = name;
        this.expression = expDTO;
    }

    public RuleDTO(String dataModelExtId, String name) {
        this.extId = null;
        this.dataModelExtId = dataModelExtId;
        this.conditionModelExtId = null;
        this.name = name;
        this.expression = null;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
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

}
