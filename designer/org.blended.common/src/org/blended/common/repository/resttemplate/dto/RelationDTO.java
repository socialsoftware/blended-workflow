package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationDTO {
    private String specId;
    private String extId;
    private String name;
    private String entOneExtId;
    private String entOneName;
    private String rolenameOne;
    private String cardinalityOne;
    private String entTwoExtId;
    private String entTwoName;
    private String rolenameTwo;
    private String cardinalityTwo;

    public RelationDTO() {
    }

    public RelationDTO(String specId, String name, String entOneName,
            String rolenameOne, String cardinalityOne, String entTwoName,
            String rolenameTwo, String cardinalityTwo) {
        this.specId = specId;
        this.extId = null;
        this.name = name;
        this.entOneExtId = null;
        this.setEntOneName(entOneName);
        this.rolenameOne = rolenameOne;
        this.cardinalityOne = cardinalityOne;
        this.entTwoExtId = null;
        this.setEntTwoName(entTwoName);
        this.rolenameTwo = rolenameTwo;
        this.cardinalityTwo = cardinalityTwo;
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

    public String getEntOneExtId() {
        return entOneExtId;
    }

    public void setEntOneExtId(String entOneExtId) {
        this.entOneExtId = entOneExtId;
    }

    public String getEntOneName() {
        return entOneName;
    }

    public void setEntOneName(String entOneName) {
        this.entOneName = entOneName;
    }

    public String getRolenameOne() {
        return rolenameOne;
    }

    public void setRolenameOne(String rolenameOne) {
        this.rolenameOne = rolenameOne;
    }

    public String getCardinalityOne() {
        return cardinalityOne;
    }

    public void setCardinalityOne(String cardinalityOne) {
        this.cardinalityOne = cardinalityOne;
    }

    public String getEntTwoExtId() {
        return entTwoExtId;
    }

    public void setEntTwoExtId(String entTwoExtId) {
        this.entTwoExtId = entTwoExtId;
    }

    public String getEntTwoName() {
        return entTwoName;
    }

    public void setEntTwoName(String entTwoName) {
        this.entTwoName = entTwoName;
    }

    public String getRolenameTwo() {
        return rolenameTwo;
    }

    public void setRolenameTwo(String rolenameTwo) {
        this.rolenameTwo = rolenameTwo;
    }

    public String getCardinalityTwo() {
        return cardinalityTwo;
    }

    public void setCardinalityTwo(String cardinalityTwo) {
        this.cardinalityTwo = cardinalityTwo;
    }

}
