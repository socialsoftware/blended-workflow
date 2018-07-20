package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {
    private String specId;
    private String name;
    private String description;
    private List<String> capabilities;
    private List<String> positions;

    public PersonDto() {
    }

    public PersonDto(String specId, String name, String description) {
        this.specId = specId;
        this.name = name;
        this.description = description;
    }

    public PersonDto(String specId, String name, String description, List<String> capabilities,
                     List<String> positions) {
        this.specId = specId;
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
        this.positions = positions;
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

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }


}