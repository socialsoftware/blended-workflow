package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class EntityAchieveConditionDTO {
    public String specId;
    public String entity;
    public boolean exists;

    public EntityAchieveConditionDTO(String specId, String entity,
            boolean exists) {
        this.specId = specId;
        this.entity = entity;
        this.exists = exists;
    }
}
