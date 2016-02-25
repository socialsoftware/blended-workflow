package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class DataModel extends DataModel_Base {
	private static Logger logger = LoggerFactory.getLogger(DataModel.class);

	public enum DataState {
		DEFINED, UNDEFINED, SKIPPED
	}

	/**
	 * Clone the DataModel.
	 */
	public DataModelInstance cloneDataModel() throws BWException {
		DataModelInstance newDataModelInstance = new DataModelInstance();
		Entity relationEntityOne;
		Entity relationEntityTwo;

		for (Entity entity : getEntitySet()) {
			entity.cloneEntity(newDataModelInstance);
		}

		// Get relation -> Get new Entities -> Clone with new Entities
		for (RelationBW relation : getRelationBWSet()) {
			relationEntityOne = newDataModelInstance.getEntity(relation.getEntityOne().getName()).get();
			relationEntityTwo = newDataModelInstance.getEntity(relation.getEntityTwo().getName()).get();
			relation.cloneRelation(newDataModelInstance, relationEntityOne, relationEntityTwo);
		}
		return newDataModelInstance;
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
		if (getSpecification().getConditionModel() != null)
			getSpecification().getConditionModel().clean();
		if (getSpecification().getGoalModel() != null)
			getSpecification().getGoalModel().clean();
		if (getSpecification().getTaskModel() != null)
			getSpecification().getTaskModel().clean();
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

	public Entity createEntity(String entityName, Boolean exists) {
		return new Entity(this, entityName, exists);
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

	public Set<AttributeBasic> getAttributeBasicSet() {
		return getAttributeSet().stream().filter(AttributeBasic.class::isInstance).map(AttributeBasic.class::cast)
				.collect(Collectors.toSet());
	}

	public void check() {
		checkPaths();
		checkCircularities();
	}

	public void checkCircularities() {
		checkDirectCircularities();

		checkCreationCircularities();
	}

	private void checkCreationCircularities() {
		List<Dependence> dependencies = getDependenceSet().stream()
				.filter(d -> d.getProduct().getProductType().equals(Product.ProductType.ENTITY))
				.collect(Collectors.toList());
		for (Dependence dependence : dependencies) {
			Set<Product> entities = getIntermediateEntities(dependence);
			// logger.debug("checkCreationCircularities
			// intermediateEntities:{}",
			// entities.stream().map(e ->
			// e.getName()).collect(Collectors.joining(",")));

			for (Product entity : entities) {
				Set<Product> visitedProducts = new HashSet<Product>();
				if (!checkProductCanBeCreatedAfterProduct(dependence.getProduct(), entity, visitedProducts))
					throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, dependence.getPath().getValue());
			}
		}
	}

	private boolean checkProductCanBeCreatedAfterProduct(Product afterProduct, Product beforeProduct,
			Set<Product> visitedProducts) {
		// logger.debug("checkProductCanBeCreatedAfterProduct afterProduct:{},
		// beforeProduct:{}", afterProduct.getName(),
		// beforeProduct.getName());
		Set<Product> nextProducts = beforeProduct.getDependenceSet().stream().map(d -> d.getPath().getTargetOfPath())
				.filter(p -> !visitedProducts.contains(p)).collect(Collectors.toSet());

		if (productSimulatenouslyCreatedWithOneOProducts(afterProduct, nextProducts)) {
			return false;
		} else {
			visitedProducts.addAll(nextProducts);
		}

		for (Product product : nextProducts) {
			checkProductCanBeCreatedAfterProduct(afterProduct, product, visitedProducts);
		}

		return true;
	}

	private Set<Product> getIntermediateEntities(Dependence dependence) {
		String[] pathParts = dependence.getPath().getValue().split("\\.");
		String path = pathParts[0];

		Set<Product> entities = new HashSet<Product>();
		for (int i = 1; i < pathParts.length - 1; i++) {
			path = path + "." + pathParts[i];
			entities.add(getTargetOfPath(path));
		}

		return entities;
	}

	private Set<Product> checkDirectCircularities() {
		List<Dependence> dependencies = new ArrayList<Dependence>(getDependenceSet());
		Set<Product> visitedProducts = null;

		while (!dependencies.isEmpty()) {
			Dependence dependence = dependencies.get(0);
			visitedProducts = new HashSet<Product>();
			visitedProducts.add(dependence.getProduct());

			removeNonCircularPaths(dependence, dependencies, visitedProducts);
		}
		return visitedProducts;
	}

	private void removeNonCircularPaths(Dependence dependence, List<Dependence> dependencies,
			Set<Product> visitedProducts) {
		Product product = dependence.getPath().getTargetOfPath();

		logger.debug("removeNonCircularPaths path:{}, product:{}, dependencies:{}", dependence.getPath().getValue(),
				product.getName(), product.getDependenceSet().size());

		if (productSimulatenouslyCreatedWithOneOProducts(product, visitedProducts)) {
			throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, dependence.getPath().getValue());
		} else {
			dependencies.remove(dependence);
			visitedProducts.add(product);
		}

		for (Dependence dep : product.getDependenceSet()) {
			removeNonCircularPaths(dep, dependencies, visitedProducts);
		}
	}

	private boolean productSimulatenouslyCreatedWithOneOProducts(Product product, Set<Product> products) {
		if (products.contains(product))
			return true;

		if (product.getProductType().equals(ProductType.ENTITY))
			return false;

		Attribute att = (Attribute) product;

		return products.stream().filter(Attribute.class::isInstance).map(Attribute.class::cast)
				.anyMatch(a -> a.getAttributeBasicSet().contains(att) || att.getAttributeBasicSet().contains(a));
	}

	public void checkPaths() {
		getDependenceSet().stream().forEach(d -> d.checkPath());

		getEntitySet().stream().flatMap(e -> e.getRuleSet().stream()).forEach(r -> r.checkPaths());
	}

}