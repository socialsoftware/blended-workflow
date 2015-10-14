package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class DefAttributeConditionDTO {
    private String specId;
    private String path;
    private boolean mandatory;
    private String attributeExtId;

    public DefAttributeConditionDTO() {
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getAttributeExtId() {
        return attributeExtId;
    }

    public void setAttributeExtId(String attributeExtId) {
        this.attributeExtId = attributeExtId;
    }

}
