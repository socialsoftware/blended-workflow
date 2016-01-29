package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class DataModel extends DataModel_Base {

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
		getRuleSet().stream().forEach(rule -> rule.delete());
		getDependenceSet().stream().forEach(dep -> dep.delete());
		getEntitySet().stream().forEach(ent -> ent.delete());
		getAttributeSet().stream().forEach(a -> a.delete());
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

	public Rule createRule(String name, Condition condition) {
		return new Rule(this, name, condition);
	}

	public Rule getRule(String name) {
		return getRuleSet().stream().filter(rule -> rule.getName().equals(name)).findFirst().orElse(null);
	}

	@Deprecated
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

}