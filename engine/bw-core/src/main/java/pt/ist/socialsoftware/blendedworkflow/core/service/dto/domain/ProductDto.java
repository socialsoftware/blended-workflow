package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class ProductDto {
	private String specId;
	private String extId;
	private String productType;

	public ProductDto(String specId, String extId, String productType) {
		this.specId = specId;
		this.extId = extId;
		this.productType = productType;
	}

	public ProductDto() {
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String type) {
		this.productType = type;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

}
