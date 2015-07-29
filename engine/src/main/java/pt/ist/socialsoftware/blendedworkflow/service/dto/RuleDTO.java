package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class RuleDTO {
    public SpecificationDTO specDTO;
    public String name;
    public ExpressionDTO expDTO;

    public RuleDTO(String specId, String name, ExpressionDTO expDTO) {
        this.specDTO = new SpecificationDTO(specId);
        this.name = name;
        this.expDTO = expDTO;
    }

    public RuleDTO(String specId, String name) {
        this.specDTO = new SpecificationDTO(specId);
        this.name = name;
        this.expDTO = null;
    }

}
