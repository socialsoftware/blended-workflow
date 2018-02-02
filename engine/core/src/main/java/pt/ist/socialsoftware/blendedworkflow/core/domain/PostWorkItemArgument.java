package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class PostWorkItemArgument extends PostWorkItemArgument_Base {

	@Override
	public void setWorkItemOfPost(WorkItem workItem) {
		checkInstanceConsistency(workItem, getProductInstanceSet());
		super.setWorkItemOfPost(workItem);
	}

	@Override
	public void setDefProductCondition(DefProductCondition defProductCondition) {
		checkProductConsistency(defProductCondition, getProductInstanceSet());
		super.setDefProductCondition(defProductCondition);
	}

	@Override
	public void addProductInstance(ProductInstance productInstance) {
		checkInstanceConsistency(getWorkItemOfPost(), productInstance);
		checkProductConsistency(getDefProductCondition(), productInstance);
		super.addProductInstance(productInstance);
	}

	public PostWorkItemArgument(WorkItem workItem, DefProductCondition defProductCondition) {
		setWorkItemOfPost(workItem);
		setDefProductCondition(defProductCondition);
	}

	public void delete() {
		setWorkItemOfPost(null);
		setDefProductCondition(null);

		getProductInstanceSet().stream().forEach(pi -> removeProductInstance(pi));

		deleteDomainObject();
	}

	public boolean productExistsAndHasCorrectType() {
		if (getProductInstanceSet() == null || getProductInstanceSet().isEmpty()) {
			throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, "post work item argument is empty");
		}

		for (ProductInstance productInstance : getProductInstanceSet()) {
			if (productInstance.getEntity() != getDefProductCondition().getSourceOfPath()) {
				throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY,
						"post work item argument " + productInstance.getEntity().getFullPath() + ":"
								+ getDefProductCondition().getTargetOfPath().getFullPath());
			}
		}

		return true;
	}

	public boolean attributeIsDefined() {
		return getProductInstanceSet().stream().filter(AttributeInstance.class::isInstance)
				.allMatch(ai -> ai.isDefined());
	}

	public boolean relationInstancesAreDefined(Set<MulCondition> mulConditions) {
		getProductInstanceSet().stream().filter(EntityInstance.class::isInstance).map(EntityInstance.class::cast)
				.allMatch(pi -> pi.isDefined(mulConditions));

		return true;
	}

}
