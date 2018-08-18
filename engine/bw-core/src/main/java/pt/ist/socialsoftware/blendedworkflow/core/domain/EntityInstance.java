package pt.ist.socialsoftware.blendedworkflow.core.domain;

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
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDto;

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
			getProductInstancesByListOfNames(getEntity().getListOfNamesInPath(defPathCondition));
		} catch (BWException bwe) {
			if (bwe.getError().equals(BWErrorType.NOT_ALL_PRODUCT_INSTANCES_DEFINED)) {
				return false;
			} else {
				throw bwe;
			}
		}

		return true;
	}

	public Set<ProductInstance> getProductInstancesByPath(String path) {
		DefPathCondition defPathCondition = DefPathCondition
				.getDefPathCondition(getEntity().getDataModel().getSpecification(), path);

		return getProductInstancesByListOfNames(getEntity().getListOfNamesInPath(defPathCondition));
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
		return getEntity().getMulConditions().stream().allMatch(m -> isInCardinality(m));
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
	public ProductInstanceDto getDto() {
		ProductInstanceDto productInstanceDto = new ProductInstanceDto();
		productInstanceDto.setProduct(getEntity().getDto());
		productInstanceDto.setExternalId(getExternalId());
		productInstanceDto.setId(getId());
		productInstanceDto.setPath(getEntity().getName());
		productInstanceDto.setValue(getEntity().getName() + "[" + getId() + "]");

		return productInstanceDto;
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
