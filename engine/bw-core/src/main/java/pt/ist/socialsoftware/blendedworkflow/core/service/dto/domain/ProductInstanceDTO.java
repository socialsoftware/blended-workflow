package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class ProductInstanceDTO {
	private ProductDTO product;
	private String externalId;
	private String id;
	private String path;
	private String value;

	public ProductInstanceDTO() {
	}

	public ProductDTO getProduct() {
		return this.product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
