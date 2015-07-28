package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class MulInvariantDTO {
    public SpecificationDTO specDTO;
    public String rolePath;
    public String cardinality;

    public MulInvariantDTO(String specId, String rolePath, String cardinality) {
        this.specDTO = new SpecificationDTO(specId);
        this.rolePath = rolePath;
        this.cardinality = cardinality;
    }
}
