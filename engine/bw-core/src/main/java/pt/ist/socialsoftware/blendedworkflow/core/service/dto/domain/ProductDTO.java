package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class ProductDTO {
	private String specId;
	private String extId;
	private String productType;

	public ProductDTO(String specId, String extId, String productType) {
		this.specId = specId;
		this.extId = extId;
		this.productType = productType;
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
		return productType;
	}

	public void setProductType(String type) {
		this.productType = type;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

}
