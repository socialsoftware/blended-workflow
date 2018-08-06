package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductDto;

public abstract class Product extends Product_Base {
	private static Logger logger = LoggerFactory.getLogger(Product.class);

	public enum ProductType {
		ATTRIBUTE, ENTITY
	}

	public Dependence createDependence(String value) {
		return new Dependence(this.getEntity().getDataModel(), this, value);
	}

	public abstract String getFullPath();

	public abstract Entity getEntity();

	public abstract DefProductCondition getDefCondition();

	public abstract ProductType getProductType();

	public abstract Product getNext(List<String> pathLeft, String path);

	public abstract ProductDto getDto();

	public void delete() {
		getDependenceSet().stream().forEach(dep -> dep.delete());

		deleteDomainObject();
	}

}
