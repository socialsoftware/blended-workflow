package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class MulInvariantDTO {
    public SpecDTO specDTO;
    public String rolePath;
    public String cardinality;

    public MulInvariantDTO(String specId, String rolePath, String cardinality) {
        this.specDTO = new SpecDTO(specId);
        this.rolePath = rolePath;
        this.cardinality = cardinality;
    }
}
