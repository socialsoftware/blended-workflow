package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Set;

public class AttributeAchieveConditionDTO {
    public SpecDTO specDTO;
    public Set<String> paths;
    public boolean mandatory;

    public AttributeAchieveConditionDTO(String specId, Set<String> paths,
            boolean mandatory) {
        this.specDTO = new SpecDTO(specId);
        this.paths = paths;
        this.mandatory = mandatory;
    }
}
