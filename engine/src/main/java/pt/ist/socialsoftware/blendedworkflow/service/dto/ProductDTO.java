package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class ProductDTO {
    private String specId;
    private String extId;
    private String producType;

    public ProductDTO(String specId, String extId, String productType) {
        this.specId = specId;
        this.extId = extId;
        this.producType = productType;
    }

    public ProductDTO() {
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getProductType() {
        return producType;
    }

    public void setProductType(String type) {
        this.producType = type;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

}
