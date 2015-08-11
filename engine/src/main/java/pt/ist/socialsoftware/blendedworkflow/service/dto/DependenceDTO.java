package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class DependenceDTO {
    private String specId;
    private String extId;
    private String productExtId;
    private String path;

    public DependenceDTO() {
    }

    public DependenceDTO(String productExtId, String path) {
        this.productExtId = productExtId;
        this.path = path;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String externalId) {
        this.extId = externalId;
    }

    public String getProductExtId() {
        return productExtId;
    }

    public void setProductExtId(String productExtId) {
        this.productExtId = productExtId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

}
