package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;

public class DependenceDto {
	private String specId;
	private String extId;
	private String product;
	private String productExtId;
	private String path;

	public DependenceDto() {
	}

	public DependenceDto(String specId, String product, String path) {
		this.specId = specId;
		this.product = product;
		this.path = path;
	}

	public DependenceDto(Dependence dependence) {
		this(dependence.getDataModel().getSpecification().getSpecId(), dependence.getProduct().getFullPath(),
				dependence.getPath().getValue());

		this.extId = dependence.getExternalId();
		this.productExtId = dependence.getProduct().getExternalId();
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String externalId) {
		this.extId = externalId;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductExtId() {
		return this.productExtId;
	}

	public void setProductExtId(String productExtId) {
		this.productExtId = productExtId;
	}

}
