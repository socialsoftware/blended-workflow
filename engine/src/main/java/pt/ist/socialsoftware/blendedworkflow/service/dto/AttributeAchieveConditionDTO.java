package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Set;

public class AttributeAchieveConditionDTO {
    public SpecificationDTO specDTO;
    public Set<String> paths;
    public boolean mandatory;

    public AttributeAchieveConditionDTO(String specId, Set<String> paths,
            boolean mandatory) {
        this.specDTO = new SpecificationDTO(specId);
        this.paths = paths;
        this.mandatory = mandatory;
    }
}
