package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class EntityInstance extends EntityInstance_Base {

	@Override
	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		checkConsistency(workflowInstance, getEntity());
		super.setWorkflowInstance(workflowInstance);
	}

	@Override
	public void setEntity(Entity entity) {
		checkConsistency(getWorkflowInstance(), entity);
		super.setEntity(entity);
	}

	public EntityInstance(WorkflowInstance workflowInstance, Entity entity) {
		setWorkflowInstance(workflowInstance);
		setEntity(entity);
	}

	private void checkConsistency(WorkflowInstance workflowInstance, Entity entity) {
		if (workflowInstance != null && entity != null) {
			if (workflowInstance.getSpecification() != entity.getDataModel().getSpecification()) {
				throw new BWException(BWErrorType.ENTITYINSTANCE_CONSISTENCY,
						workflowInstance.getName() + ":" + entity.getName());
			}
		}
	}

	@Override
	public void delete() {
		setEntity(null);
		getAttributeInstanceSet().forEach(ai -> ai.delete());
		getRelationInstanceOfOneSet().stream().forEach(ri -> ri.delete());
		getRelationInstanceOfTwoSet().stream().forEach(ri -> ri.delete());

		super.delete();
	}

	@Override
	public Product getProduct() {
		return getEntity();
	}

	public boolean holdsDefPathCondition(DefPathCondition defPathCondition) {
		LinkedList<String> namesInPath = new LinkedList<String>(
				Arrays.asList(defPathCondition.getPath().getValue().split("\\.")));

		if (defPathCondition.getPath().getSource() != getEntity()
				&& defPathCondition.getPath().getAdjacent() == getEntity()) {
			namesInPath.removeFirst();
		} else {
			assert false : "holdsDefPathCondition path does not start or has adjacent of the entity instance type";
		}

		// the first element refers the entity
		namesInPath.remove(0);

		try {
			getProductInstancesByPath(namesInPath);
		} catch (BWException bwe) {
			if (bwe.getError().equals(BWErrorType.NOT_ALL_PRODUCT_INSTANCES_DEFINED)) {
				return false;
			} else {
				throw bwe;
			}
		}

		return true;
	}

	public Set<ProductInstance> getProductInstancesByPath(List<String> namesInPath) {
		Set<ProductInstance> productInstances = new HashSet<ProductInstance>();
		productInstances.add(this);

		for (String name : namesInPath) {
			Set<ProductInstance> tmpProductInstances = new HashSet<ProductInstance>();
			for (ProductInstance productInstance : productInstances) {
				Set<ProductInstance> stepProductInstances = productInstance.getProductInstanceSetByName(name);
				if (stepProductInstances.isEmpty()) {
					throw new BWException(BWErrorType.NOT_ALL_PRODUCT_INSTANCES_DEFINED, name);
				} else {
					tmpProductInstances.addAll(stepProductInstances);
				}
			}
			productInstances = tmpProductInstances;
		}

		return productInstances;
	}

	public Set<EntityInstance> getEntityInstanceSetByRolename(String rolename) {
		return Stream.concat(
				getRelationInstanceOfOneSet().stream()
						.filter(ri -> ri.getRelationType().getRoleNameTwo().equals(rolename))
						.map(ri -> ri.getEntityInstanceTwo()),
				getRelationInstanceOfTwoSet().stream()
						.filter(ri -> ri.getRelationType().getRoleNameOne().equals(rolename))
						.map(ri -> ri.getEntityInstanceOne()))
				.collect(Collectors.toSet());

	}

	public Optional<AttributeInstance> getAttributeInstanceByName(String name) {
		return getAttributeInstanceSet().stream().filter(ai -> ai.getAttribute().getName().equals(name)).findFirst();
	}

	@Override
	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		if (getAttributeInstanceByName(name).isPresent()) {
			Set<ProductInstance> productInstances = new HashSet<ProductInstance>();
			productInstances.add(getAttributeInstanceByName(name).get());
			return productInstances;
		}

		// the data model guarantees that the name is unique for attributes and
		// roles
		return getEntityInstanceSetByRolename(name).stream().collect(Collectors.toSet());
	}

}
