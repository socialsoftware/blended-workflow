package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class DataModel extends DataModel_Base {
	private static Logger logger = LoggerFactory.getLogger(DataModel.class);

	public enum DataState {
		DEFINED, UNDEFINED, SKIPPED
	}

	public DataModel() {
		setInstanceCounter(0);
	}

	public String incInstanceCounter() {
		setInstanceCounter(getInstanceCounter() + 1);
		return getInstanceCounter().toString();
	}

	public Optional<Entity> getEntity(String name) {
		return getEntitySet().stream().filter(ent -> ent.getName().equals(name)).findFirst();
	}

	public RelationBW getRelation(String name) {
		for (RelationBW relation : getRelationBWSet()) {
			if (relation.getName().equals(name)) {
				return relation;
			}
		}
		return null;
	}

	public void clean() {
		if (getSpecification().getConditionModel() != null) {
			getSpecification().getConditionModel().clean();
		}
		if (getSpecification().getGoalModel() != null) {
			getSpecification().getGoalModel().clean();
		}
		if (getSpecification().getActivityModel() != null) {
			getSpecification().getActivityModel().clean();
		}
		getDependenceSet().stream().forEach(dep -> dep.delete());
		getEntitySet().stream().forEach(ent -> ent.delete());
		getAttributeSet().stream().forEach(a -> a.delete());
		getDefPathConditionSet().stream().forEach(d -> d.delete());
		getPathSet().stream().forEach(p -> p.delete());
	}

	public void delete() {
		clean();
		setSpecification(null);
		deleteDomainObject();
	}

	public Entity createEntity(String entityName, boolean exists, boolean mandatory) {
		return new Entity(this, entityName, exists, mandatory);
	}

	public Product getSourceOfPath(String path) {
		List<String> pathLeft = Arrays.stream(path.split("\\.")).collect(Collectors.toList());
		Entity entity = getEntity(pathLeft.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, pathLeft.get(0)));
		return entity;
	}

	public Product getTargetOfPath(String path) {
		List<String> pathLeft = Arrays.stream(path.split("\\.")).collect(Collectors.toList());
		Entity entity = getEntity(pathLeft.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, pathLeft.get(0)));
		pathLeft.remove(0);
		return entity.getNext(pathLeft, path);
	}

	public Set<Attribute> getAttributeBasicSet() {
		return getAttributeSet().stream().filter(Attribute.class::isInstance).map(Attribute.class::cast)
				.collect(Collectors.toSet());
	}

	public void check() {
		checkUniqueMandatoryEntity();
		checkPaths();
		checkDependences();
	}

	private void checkUniqueMandatoryEntity() {
		if (getEntitySet().stream().filter(e -> e.getMandatory()).count() != 1) {
			throw new BWException(BWErrorType.NOT_UNIQUE_MANDATORY_ENTITY, getSpecification().getName());
		}
	}

	public void checkDependences() {
		Map<Product, Set<Product>> productDependencies = getProductDependencies();

		for (Product product : productDependencies.keySet()) {
			checkCycles(product, productDependencies);
		}
	}

	private void checkCycles(Product product, Map<Product, Set<Product>> productDependencies) {
		Set<Product> visitedProducts = new HashSet<>();
		List<Product> nextProducts = new ArrayList<>();
		nextProducts.add(product);
		while (!nextProducts.isEmpty()) {
			Product next = nextProducts.get(0);

			if (productDependencies.get(next) != null) {

				if (productDependencies.get(next).contains(product)) {
					throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, product.getFullPath());
				}

				nextProducts.addAll(productDependencies.get(next).stream().filter(p -> !visitedProducts.contains(p))
						.collect(Collectors.toSet()));

				visitedProducts.addAll(productDependencies.get(next));
			}

			nextProducts.remove(0);
		}

	}

	private Map<Product, Set<Product>> getProductDependencies() {
		Map<Product, Set<Product>> productDependencies = new HashMap<>();

		for (Dependence dependence : getDependenceSet()) {
			Set<Product> products = productDependencies.get(dependence.getProduct());

			if (products == null) {
				products = new HashSet<>();
			}
			products.addAll(dependence.getPath().getProductsInPath());

			if (dependence.getProduct() == dependence.getPath().getSource()) {
				products.remove(dependence.getProduct());
			}

			productDependencies.put(dependence.getProduct(), products);
		}

		for (Attribute attribute : getEntitySet().stream().flatMap(e -> e.getAttributeSet().stream())
				.collect(Collectors.toSet())) {
			Set<Product> products = productDependencies.get(attribute);

			if (products == null) {
				products = new HashSet<>();
			}

			products.add(attribute.getEntity());

			productDependencies.put(attribute, products);
		}

		logger.debug("getProductDependencies {}",
				productDependencies.entrySet().stream()
						.map(e -> e.getKey().getName() + ":"
								+ e.getValue().stream().map(t -> t.getName()).collect(Collectors.joining(",")))
						.collect(Collectors.joining(";")));

		return productDependencies;
	}

	public void checkPaths() {
		getDependenceSet().stream().forEach(d -> d.checkPath());

		getEntitySet().stream().flatMap(e -> e.getRuleSet().stream()).forEach(r -> r.checkPaths());
	}

	public Set<Rule> getRules() {
		return getEntitySet().stream().flatMap(e -> e.getRuleSet().stream()).collect(Collectors.toSet());
	}

}