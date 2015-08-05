package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class RelationDTO {
    public String name;
    public String entOneExtId;
    public String rolenameOne;
    public String cardinalityOne;
    public String entTwoExtId;
    public String rolenameTwo;
    public String cardinalityTwo;

    public RelationDTO(String name, String entOneExtId, String rolenameOne,
            String cardinalityOne, String entTwoExtId, String rolenameTwo,
            String cardinalityTwo) {
        this.name = name;
        this.entOneExtId = entOneExtId;
        this.rolenameOne = rolenameOne;
        this.cardinalityOne = cardinalityOne;
        this.entTwoExtId = entTwoExtId;
        this.rolenameTwo = rolenameTwo;
        this.cardinalityTwo = cardinalityTwo;
    }

}
