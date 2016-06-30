package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;

public class ConditionModel extends ConditionModel_Base {
	private static Logger logger = LoggerFactory.getLogger(ConditionModel.class);

	public void clean() {
		if (getSpecification().getGoalModel() != null)
			getSpecification().getGoalModel().clean();
		if (getSpecification().getActivityModel() != null)
			getSpecification().getActivityModel().clean();
		getEntityAchieveConditionSet().stream().forEach(defEnt -> removeEntityAchieveCondition(defEnt));
		getEntityDependenceConditionSet().stream().forEach(depEnt -> removeEntityDependenceCondition(depEnt));
		getEntityInvariantConditionSet().stream().forEach(mul -> removeEntityInvariantCondition(mul));
		getAttributeAchieveConditionSet().stream().forEach(defAtt -> removeAttributeAchieveCondition(defAtt));
		getAttributeDependenceConditionSet().stream().forEach(depAtt -> removeAttributeDependenceCondition(depAtt));
		getAttributeInvariantConditionSet().stream().forEach(rule -> removeAttributeInvariantCondition(rule));
	}

	public void delete() {
		clean();
		setSpecification(null);

		deleteDomainObject();
	}

	public void generateConditions() {
		DataModel dataModel = getSpecification().getDataModel();

		dataModel.getEntitySet().stream().forEach(e -> DefEntityCondition.getDefEntityCondition(e));

		dataModel.getAttributeSet().stream().filter(Attribute.class::isInstance).map(Attribute.class::cast)
				.filter(a -> !a.getEntity().getExists())
				.forEach(a -> DefAttributeCondition.getDefAttributeCondition(a));

		dataModel.getRelationBWSet().stream().forEach(r -> MulCondition.createMUlConditions(r));

		dataModel.getEntitySet().stream().flatMap(e -> e.getRuleSet().stream())
				.forEach(r -> this.addAttributeInvariantCondition(r));

		dataModel.getDependenceSet().stream().filter(d -> d.getProduct().getProductType().equals(ProductType.ENTITY))
				.forEach(d -> this.addEntityDependenceCondition(d));

		dataModel.getDependenceSet().stream().filter(d -> !d.getProduct().getProductType().equals(ProductType.ENTITY))
				.forEach(d -> this.addAttributeDependenceCondition(d));

		getEntityInvariantConditionSet().stream()
				.forEach(m -> DefPathCondition.getDefPathCondition(getSpecification(), m.getPath()));
	}

	public Set<DefProductCondition> getAllProductionConditions() {
		Set<DefProductCondition> allDefConditions = new HashSet<DefProductCondition>(getEntityAchieveConditionSet()
				.stream().filter(d -> !d.getEntity().getExists()).collect(Collectors.toSet()));

		allDefConditions.addAll(getAttributeAchieveConditionSet().stream()
				.filter(d -> !d.getAttributeOfDef().getEntity().getExists()).collect(Collectors.toSet()));

		return allDefConditions;
	}

	static public Set<DefEntityCondition> getDefEntityConditions(Set<? extends DefProductCondition> conditions) {
		return conditions.stream().filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
				.collect(Collectors.toSet());
	}

	static public Set<DefAttributeCondition> getDefAttributeConditions(Set<? extends DefProductCondition> conditions) {
		return conditions.stream().filter(DefAttributeCondition.class::isInstance)
				.map(DefAttributeCondition.class::cast).collect(Collectors.toSet());
	}

}
