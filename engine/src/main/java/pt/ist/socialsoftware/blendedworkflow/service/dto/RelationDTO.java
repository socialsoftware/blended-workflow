package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class RelationDTO {
    private String extId;
    private String dataModelExtId;
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

    public RelationDTO(String dataModelExtId, String name, String entOneExtId,
            String rolenameOne, String cardinalityOne, String entTwoExtId,
            String rolenameTwo, String cardinalityTwo) {
        this.dataModelExtId = dataModelExtId;
        this.name = name;
        this.entOneExtId = entOneExtId;
        this.rolenameOne = rolenameOne;
        this.cardinalityOne = cardinalityOne;
        this.entTwoExtId = entTwoExtId;
        this.rolenameTwo = rolenameTwo;
        this.cardinalityTwo = cardinalityTwo;
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
