package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class RelationDTO {
    public String name;
    public EntityDTO entOneDTO;
    public String rolenameOne;
    public String cardinalityOne;
    public EntityDTO entTwoDTO;
    public String rolenameTwo;
    public String cardinalityTwo;

    public RelationDTO(String name, EntityDTO entOneDTO, String rolenameOne,
            String cardinalityOne, EntityDTO entTwoDTO, String rolenameTwo,
            String cardinalityTwo) {
        this.name = name;
        this.entOneDTO = entOneDTO;
        this.rolenameTwo = rolenameOne;
        this.cardinalityOne = cardinalityOne;
        this.entTwoDTO = entTwoDTO;
        this.rolenameTwo = rolenameTwo;
        this.cardinalityTwo = cardinalityTwo;
    }

    public RelationDTO(String specId, String name, String entNameOne,
            String rolenameOne, String cardinalityOne, String entNameTwo,
            String rolenameTwo, String cardinalityTwo) {
        this.name = name;
        this.entOneDTO = new EntityDTO(specId, entNameOne);
        this.rolenameOne = rolenameOne;
        this.cardinalityOne = cardinalityOne;
        this.entTwoDTO = new EntityDTO(specId, entNameTwo);
        this.rolenameTwo = rolenameTwo;
        this.cardinalityTwo = cardinalityTwo;
    }

}
