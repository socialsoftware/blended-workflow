package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;

public class ConditionModel extends ConditionModel_Base {

    public void clean() {
        if (getSpecification().getGoalModel() != null)
            getSpecification().getGoalModel().clean();
        if (getSpecification().getTaskModel() != null)
            getSpecification().getTaskModel().clean();
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

    public Set<Product> getProductsOfDefConditions(
            Set<DefProductCondition> defConditions) {
        DataModel dataModel = this.getSpecification().getDataModel();
        return defConditions.stream()
                .map(d -> dataModel.getTargetOfPath(d.getPath()))
                .collect(Collectors.toSet());
    }

    public Set<Entity> getEntitiesOfDefEntitySet(
            Set<DefProductCondition> defEntities) {
        Set<Entity> entities = defEntities.stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .map((def) -> def.getEntity()).collect(Collectors.toSet());
        return entities;
    }

    public Set<Product> getProductsOfDefAttributeSet(
            Set<DefProductCondition> defAttributes) {
        Set<Product> attributes = defAttributes.stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .map((def) -> def.getAttributeOfDef())
                .collect(Collectors.toSet());
        return attributes;
    }

    public Set<Product> getProductsOfDefDependenceSet(
            Set<DefProductCondition> defDependencies) {
        Set<Product> products = defDependencies.stream()
                .filter(DefDependenceCondition.class::isInstance)
                .map(DefDependenceCondition.class::cast)
                .map((def) -> def.getTarget()).collect(Collectors.toSet());
        return products;
    }

    public Set<DefEntityCondition> getDefEntityConditions(
            Set<DefProductCondition> conditions) {
        return conditions.stream().filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .collect(Collectors.toSet());
    }

    public Set<DefAttributeCondition> getDefAttributeConditions(
            Set<DefProductCondition> conditions) {
        return conditions.stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .collect(Collectors.toSet());
    }

    public Set<DefDependenceCondition> getDefDependenceConditions(
            Set<DefProductCondition> conditions) {
        return conditions.stream()
                .filter(DefDependenceCondition.class::isInstance)
                .map(DefDependenceCondition.class::cast)
                .collect(Collectors.toSet());
    }

}
