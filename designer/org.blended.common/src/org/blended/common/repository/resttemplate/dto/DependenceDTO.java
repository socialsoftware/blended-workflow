package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependenceDTO {
	private String specId;
	private String extId;
	private String product;
	private String path;

	public DependenceDTO() {
	}

	public DependenceDTO(String specId, String product, String path) {
		this.specId = specId;
		this.product = product;
		this.path = path;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String externalId) {
		this.extId = externalId;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
