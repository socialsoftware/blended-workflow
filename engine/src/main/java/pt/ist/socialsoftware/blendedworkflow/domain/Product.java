package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

public abstract class Product extends Product_Base {
	public enum ProductType {
		ATTRIBUTE_BASIC, ATTRIBUTE_GROUP, ENTITY
	}

	public Dependence createDependence(String value) {
		return new Dependence(this.getEntity().getDataModel(), this, value);
	}

	public abstract String getFullPath();

	public abstract Entity getEntity();

	public abstract DefProductCondition getDefCondition();

	public abstract ProductType getProductType();

	public abstract Product getNext(List<String> pathLeft, String path);

	public abstract boolean canBeDefinedBefore(Product product);

	public abstract boolean isCreatedTogether(Product product);

	public abstract ProductDTO getDTO();

	public void delete() {
		getDependenceSet().stream().forEach(dep -> dep.delete());

		deleteDomainObject();
	}

	public boolean isEntityAndExists() {
		if (getProductType().equals(ProductType.ENTITY) && ((Entity) this).getExists()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canBeDefinedBeforeProducts(Set<Product> products) {
		return products.stream().allMatch(p -> canBeDefinedBefore(p));
	}

}
