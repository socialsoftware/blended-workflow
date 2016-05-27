package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class ProductInstanceDTO {
	private ProductDTO product;
	private String externalId;
	private String path;
	private String value;

	public ProductInstanceDTO() {
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

}
