package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;

public abstract class Product extends Product_Base {
    public enum ProductType {
        ATTRIBUTE, ATTRIBUTE_GROUP, ENTITY
    }

    public Dependence createDependence(String value) {
        return new Dependence(this.getEntity().getDataModel(), this, value);
    }

    public abstract Entity getEntity();

    public abstract Condition getDefCondition();

    public abstract ProductType getProductType();

    public abstract Product getNext(List<String> pathLeft, String path);

    public void delete() {
        getDependenceSet().stream().forEach(dep -> dep.delete());

        deleteDomainObject();
    }

    public abstract ProductDTO getDTO();

}
