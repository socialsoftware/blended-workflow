package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class ProductDTO {
    private String extId;
    private String dataModelExtId;
    private String producType;

    public ProductDTO(String extId, String dataModelExtId, String productType) {
        this.extId = extId;
        this.dataModelExtId = dataModelExtId;
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

    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
    }

    public String getProductType() {
        return producType;
    }

    public void setProductType(String type) {
        this.producType = type;
    }

}
