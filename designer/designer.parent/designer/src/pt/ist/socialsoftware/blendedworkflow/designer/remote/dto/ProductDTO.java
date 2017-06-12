package pt.ist.socialsoftware.blendedworkflow.designer.remote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
	public enum ProductType {
		ATTRIBUTE, ENTITY
	}

	private String specId;
	private String extId;
	private String productType;

	public ProductDTO() {
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

	public void setProductType(String producType) {
		this.productType = producType;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}
}
