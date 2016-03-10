package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

public abstract class Product extends Product_Base {
	private static Logger logger = LoggerFactory.getLogger(Product.class);

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

	public boolean dependsOnCreationOf(Entity entity) {
		Set<Product> visited = new HashSet<Product>();
		visited.add(entity);

		return dependsOnCreationOf(entity, visited);
	}

	private boolean dependsOnCreationOf(Entity entity, Set<Product> visited) {
		Set<Product> nextProducts = getDependenceSet().stream().map(d -> d.getTarget()).collect(Collectors.toSet());

		if (nextProducts.stream().map(p -> p.getEntity()).anyMatch(e -> e == entity)) {
			return true;
		} else {
			Set<Product> nextVisited = new HashSet<Product>(visited);
			nextVisited.addAll(nextProducts);
			for (Product nextProduct : nextProducts) {
				if (!visited.contains(nextProduct) && nextProduct.dependsOnCreationOf(entity, nextVisited)) {
					return true;
				}
			}
			return false;
		}
	}

	public abstract boolean cannotBeDefinedBefore(Product source);

}
