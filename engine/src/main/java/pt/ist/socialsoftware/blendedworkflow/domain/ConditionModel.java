package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;

public class ConditionModel extends ConditionModel_Base {

    public void clean() {
        if (getSpecification().getGoalModel() != null)
            getSpecification().getGoalModel().clean();
        getEntityAchieveConditionSet().stream()
                .forEach(defEnt -> removeEntityAchieveCondition(defEnt));
        getEntityDependenceConditionSet().stream()
                .forEach(depEnt -> removeEntityDependenceCondition(depEnt));
        getEntityInvariantConditionSet().stream()
                .forEach(mul -> removeEntityInvariantCondition(mul));
        getAttributeAchieveConditionSet().stream()
                .forEach(defAtt -> removeAttributeAchieveCondition(defAtt));
        getAttributeDependenceConditionSet().stream()
                .forEach(depAtt -> removeAttributeDependenceCondition(depAtt));
        getAttributeInvariantConditionSet().stream()
                .forEach(rule -> removeAttributeInvariantCondition(rule));
    }

    public void delete() {
        clean();
        setSpecification(null);

        deleteDomainObject();
    }

    public void generateConditions() {
        DataModel dataModel = getSpecification().getDataModel();

        dataModel.getEntitySet().stream().filter(e -> !e.getExists())
                .forEach(e -> DefEntityCondition.getDefEntity(e));

        dataModel.getAttributeSet().stream()
                .filter(AttributeBasic.class::isInstance)
                .map(AttributeBasic.class::cast)
                .filter(a -> a.getAttributeGroup() == null)
                .forEach(a -> DefAttributeCondition.getDefAttribute(a));

        dataModel.getAttributeSet().stream()
                .filter(AttributeGroup.class::isInstance)
                .map(AttributeGroup.class::cast)
                .forEach(a -> DefAttributeCondition.getDefAttribute(a));

        dataModel.getRelationBWSet().stream()
                .forEach(r -> MulCondition.createMUlConditions(r));

        dataModel.getRuleSet().stream()
                .forEach(r -> this.addAttributeInvariantCondition(r));

        dataModel.getDependenceSet().stream()
                .filter(d -> d.getProduct().getProductType()
                        .equals(ProductType.ENTITY))
                .forEach(d -> this.addEntityDependenceCondition(d));

        dataModel.getDependenceSet().stream()
                .filter(d -> !d.getProduct().getProductType()
                        .equals(ProductType.ENTITY))
                .forEach(d -> this.addAttributeDependenceCondition(d));

    }

    public static Set<Product> getProductsOfDefConditions(
            Set<Condition> defConditions) {
        Set<Product> products = new HashSet<Product>();
        products.addAll(getEntitiesOfDefEntitySet(defConditions));
        products.addAll(getProductsOfDefAttributeSet(defConditions));

        return products;
    }

    public static Set<Entity> getEntitiesOfDefEntitySet(
            Set<Condition> defEntities) {
        Set<Entity> entities = defEntities.stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .map((def) -> def.getEntity()).collect(Collectors.toSet());
        return entities;
    }

    public static Set<Product> getProductsOfDefAttributeSet(
            Set<Condition> defAttributes) {
        Set<Product> attributes = defAttributes.stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .map((def) -> def.getAttributeOfDef())
                .collect(Collectors.toSet());
        return attributes;
    }

    public static Set<DefEntityCondition> getDefEntityConditions(
            Set<Condition> conditions) {
        return conditions.stream().filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .collect(Collectors.toSet());
    }

    public static Set<DefAttributeCondition> getDefAttributeConditions(
            Set<Condition> conditions) {
        return conditions.stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .collect(Collectors.toSet());
    }

}
