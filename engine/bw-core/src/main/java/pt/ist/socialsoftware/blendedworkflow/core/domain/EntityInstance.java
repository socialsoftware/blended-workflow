package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ProductInstanceDTO;

public class EntityInstance extends EntityInstance_Base {
	private static Logger logger = LoggerFactory.getLogger(EntityInstance.class);

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
		setId(entity.getDataModel().incInstanceCounter());
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
		setWorkflowInstance(null);
		getAttributeInstanceSet().forEach(ai -> ai.delete());
		getRelationInstanceOfOneSet().stream().forEach(ri -> ri.delete());
		getRelationInstanceOfTwoSet().stream().forEach(ri -> ri.delete());

		super.delete();
	}

	@Override
	public Product getProduct() {
		return getEntity();
	}

	@Override
	public EntityInstance getEntityInstance() {
		return this;
	}

	public Boolean holdsDefPathConditions(Set<DefPathCondition> preConditionSet) {
		return preConditionSet.stream().map(d -> this.holdsDefPathCondition(d)).reduce(true, (b1, b2) -> b1 && b2);
	}

	public boolean holdsDefPathCondition(DefPathCondition defPathCondition) {
		try {
			getProductInstancesByPath(defPathCondition);
		} catch (BWException bwe) {
			if (bwe.getError().equals(BWErrorType.NOT_ALL_PRODUCT_INSTANCES_DEFINED)) {
				return false;
			} else {
				throw bwe;
			}
		}

		return true;
	}

	public Set<ProductInstance> getProductInstancesByPath(DefPathCondition defPathCondition) {
		List<String> namesInPath = new ArrayList<>(Arrays.asList(defPathCondition.getPath().getValue().split("\\.")));

		if (defPathCondition.getPath().getSource() != getEntity()
				&& defPathCondition.getPath().getAdjacent() != getEntity()) {
			assert false : "neither the first nor the second element in the path, "
					+ defPathCondition.getPath().getValue() + ", refer to the entity of this instance, "
					+ getEntity().getName();
		}

		// the first element refers the entity
		if (defPathCondition.getPath().getSource() == getEntity()) {
			namesInPath.remove(0);
		}

		// the second element refers the entity
		if (defPathCondition.getPath().getSource() != getEntity()
				&& defPathCondition.getPath().getAdjacent() == getEntity()) {
			namesInPath.remove(0);
			namesInPath.remove(0);
		}

		return getProductInstancesByListOfNames(namesInPath);
	}

	public Set<ProductInstance> getProductInstancesByPath(String path) {
		DefPathCondition defPathCondition = DefPathCondition
				.getDefPathCondition(getEntity().getDataModel().getSpecification(), path);

		return getProductInstancesByPath(defPathCondition);
	}

	private Set<ProductInstance> getProductInstancesByListOfNames(List<String> namesInPath) {
		Set<ProductInstance> productInstances = new HashSet<>();
		productInstances.add(this);

		for (String name : namesInPath) {
			Set<ProductInstance> tmpProductInstances = new HashSet<>();
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

	public Optional<AttributeInstance> getAttributeInstanceByName(String name) {
		return getAttributeInstanceSet().stream().filter(ai -> ai.getAttribute().getName().equals(name)).findFirst();
	}

	@Override
	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		if (getAttributeInstanceByName(name).isPresent()) {
			Set<ProductInstance> productInstances = new HashSet<>();
			productInstances.add(getAttributeInstanceByName(name).get());
			return productInstances;
		}

		// the data model guarantees that the name is unique for attributes and
		// roles
		return getEntityInstancesByRolename(name).stream().map(ProductInstance.class::cast).collect(Collectors.toSet());
	}

	public boolean isDefined(Attribute attribute) {
		if (getEntity().getAttributeSet().contains(attribute)) {
			return getAttributeInstanceByName(attribute.getName()).isPresent();
		}
		return true;
	}

	public Set<RelationInstance> getRelationInstanceSet() {
		return Stream.concat(getRelationInstanceOfOneSet().stream(), getRelationInstanceOfTwoSet().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isDefined() {
		return getEntity().getMultConditions().stream().allMatch(m -> isInCardinality(m));
	}

	public boolean isDefined(Set<MulCondition> mulConditionSet) {
		return mulConditionSet.stream().allMatch(m -> isInCardinality(m));
	}

	private boolean isInCardinality(MulCondition mulCondition) {
		int numberOfInstances = getEntityInstancesByRolename(mulCondition.getRolename()).size();

		if (numberOfInstances < mulCondition.getCardinality().getMinValue()
				|| numberOfInstances > mulCondition.getCardinality().getMaxValue()) {
			throw new BWException(BWErrorType.RELATIONINSTANCE_CONSISTENCY,
					"cardinality " + numberOfInstances + " " + mulCondition.getTargetEntity().getName() + " "
							+ mulCondition.getCardinality().getMinValue() + " "
							+ mulCondition.getCardinality().getMaxValue());
		}

		return true;
	}

	@Override
	public boolean holdsPre(DefPathCondition defPathCondition) {
		if (getEntity() != defPathCondition.getSourceOfPath()) {
			return false;
		}

		return false;
	}

	@Override
	public ProductInstanceDTO getDTO() {
		ProductInstanceDTO productInstanceDTO = new ProductInstanceDTO();
		productInstanceDTO.setProduct(getEntity().getDTO());
		productInstanceDTO.setExternalId(getExternalId());
		productInstanceDTO.setId(getId());
		productInstanceDTO.setPath(getEntity().getName());
		productInstanceDTO.setValue(getEntity().getName() + "[" + getId() + "]");

		return productInstanceDTO;
	}

	public Set<EntityInstance> getEntityInstancesByRolename(String rolename) {
		return getRelationInstanceSet().stream().filter(ri -> ri.hasRolename(rolename))
				.map(ri -> ri.getEntityInstanceByRolename(this, rolename)).collect(Collectors.toSet());
	}

	public boolean attributesNotDefined(Set<Attribute> attributes) {
		// none of attributes are defined for this instance
		return attributes.stream().filter(a -> a.getEntity() == getEntity()).noneMatch(a -> isDefined(a));
	}

	public boolean canBeAssociatedWithNewEntityInstance(Set<MulCondition> mulConditions) {
		return mulConditions.stream().filter(m -> m.getTargetEntity() == getEntity())
				.map(m -> m.getSymmetricMulCondition()).noneMatch(
						m -> getEntityInstancesByRolename(m.getRolename()).size() == m.getCardinality().getMaxValue());
	}

}
