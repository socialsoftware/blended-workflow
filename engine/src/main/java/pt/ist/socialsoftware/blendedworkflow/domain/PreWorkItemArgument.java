package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PreWorkItemArgument extends PreWorkItemArgument_Base {

	@Override
	public void setWorkItemOfPre(WorkItem workItem) {
		checkInstanceConsistency(workItem, getProductInstanceSet());
		super.setWorkItemOfPre(workItem);
	}

	@Override
	public void setDefPathCondition(DefPathCondition defPathCondition) {
		checkProductConsistency(defPathCondition, getProductInstanceSet());
		super.setDefPathCondition(defPathCondition);
	}

	@Override
	public void addProductInstance(ProductInstance productInstance) {
		checkInstanceConsistency(getWorkItemOfPre(), productInstance);
		checkProductConsistency(getDefPathCondition(), productInstance);
		super.addProductInstance(productInstance);
	}

	public PreWorkItemArgument(WorkItem workItem, DefPathCondition defPathCondition) {
		setWorkItemOfPre(workItem);
		setDefPathCondition(defPathCondition);
	}

	public void delete() {
		setWorkItemOfPre(null);
		setDefPathCondition(null);

		getProductInstanceSet().stream().forEach(pi -> removeProductInstance(pi));

		deleteDomainObject();
	}

	public boolean preHolds(Set<PostWorkItemArgument> postWorkItemArguments) {
		// attribute instances are defined
		for (AttributeInstance attributeInstance : getProductInstanceSet().stream()
				.filter(AttributeInstance.class::isInstance).map(AttributeInstance.class::cast)
				.collect(Collectors.toSet())) {
			if (!attributeInstance.isDefined()) {
				throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY,
						"pre work item argument not specified for attribute "
								+ attributeInstance.getAttribute().getName());
			}
		}

		// the pre work item arguments should be completely defined
		if (getProductInstanceSet() == null || getProductInstanceSet().isEmpty()) {
			throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, "pre work item argument not specified.");
		}

		// all defined products are associated with pre products
		Set<ProductInstance> productInstances = postWorkItemArguments.stream()
				.filter(wia -> wia.getDefProductCondition().getSourceOfPath() == getDefPathCondition()
						.getSourceOfPath())
				.flatMap(wia -> wia.getProductInstanceSet().stream()).collect(Collectors.toSet());

		for (ProductInstance productInstance : productInstances) {
			if (!getProductInstanceSet().stream().allMatch(
					wia -> productInstance.getEntityInstance().holdsDefPathCondition(getDefPathCondition()))) {
				throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY,
						"pre work item argument not specified.");
			}
		}

		return true;
	}

}
