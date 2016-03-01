package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class Path extends Path_Base {

	public Path(DataModel dataModel, String value) {
		setValue(value);
		setDataModel(dataModel);
	}

	public void delete() {
		setDataModel(null);

		setDependence(null);
		setAttributeBoolCondition(null);
		setAttributeValueExpression(null);
		setDefProductCondition(null);

		deleteDomainObject();
	}

	public boolean check() {
		List<String> pathLeft = Arrays.stream(getValue().split("\\.")).collect(Collectors.toList());

		if (pathLeft.size() == 0) {
			throw new BWException(BWErrorType.INVALID_PATH, getValue());
		}

		String prefix = pathLeft.get(0);

		Optional<Entity> entity = getDataModel().getEntity(prefix);
		if (!entity.isPresent()) {
			throw new BWException(BWErrorType.INVALID_PATH,
					getValue() + " requires to have the Entity name as prefix: " + prefix);
		}

		pathLeft.remove(0);

		Product product = entity.get().getNext(pathLeft, getValue());

		return (product != null);
	}

	public Product getTarget() {
		List<String> pathLeft = Arrays.stream(getValue().split("\\.")).collect(Collectors.toList());
		Entity entity = getDataModel().getEntity(pathLeft.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, pathLeft.get(0)));
		pathLeft.remove(0);
		return entity.getNext(pathLeft, getValue());
	}

	public Entity getSource() {
		List<String> path = Arrays.stream(getValue().split("\\.")).collect(Collectors.toList());
		Entity entity = getDataModel().getEntity(path.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, path.get(0)));
		return entity;
	}

	public Entity getAdjacent() {
		List<String> path = Arrays.stream(getValue().split("\\.")).collect(Collectors.toList());
		Entity entity = getDataModel().getEntity(path.get(0))
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, path.get(0)));

		return entity.getEntityByRolename(path.get(1));
	}

}
