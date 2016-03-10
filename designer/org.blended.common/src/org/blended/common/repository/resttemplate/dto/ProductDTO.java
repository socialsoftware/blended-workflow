package org.blended.common.repository.resttemplate.dto;

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
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String producType) {
		this.productType = producType;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

}
