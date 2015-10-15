package pt.ist.socialsoftware.blendedworkflow.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefDependenceConditionDTO {
    private String specId;
    private String path;

    public DefDependenceConditionDTO() {
    }

    public DefDependenceConditionDTO(String specId, String path) {
        this.specId = specId;
        this.path = path;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
