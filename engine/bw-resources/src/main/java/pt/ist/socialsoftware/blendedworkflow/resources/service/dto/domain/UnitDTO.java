package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitDTO {
    private String specId;
    private String name;
    private String description;

    public UnitDTO() {

    }

    public UnitDTO(String specId, String name, String description) {
        setSpecId(specId);
        this.name = name;
        this.description = description;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
