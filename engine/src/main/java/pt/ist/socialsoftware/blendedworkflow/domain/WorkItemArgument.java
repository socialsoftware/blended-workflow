package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class WorkItemArgument extends WorkItemArgument_Base {

	public void checkProductConsistency(DefProductCondition defProductCondition, ProductInstance productInstance) {
		if (defProductCondition != null && productInstance != null) {
			if (defProductCondition.getTargetOfPath() != productInstance.getProduct()) {
				throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY,
						defProductCondition.getTargetOfPath().getName() + "<>"
								+ productInstance.getProduct().getName());
			}
		}
	}

	public void checkInstanceConsistency(WorkItem workItem, ProductInstance productInstance) {
		if (workItem != null && productInstance != null) {
			if (workItem.getWorkflowInstance() != productInstance.getWorkflowInstance()) {
				throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, workItem.getWorkflowInstance().getName()
						+ "<>" + productInstance.getWorkflowInstance().getName());
			}
		}
	}

	public void checkProductConsistency(DefProductCondition defProductCondition,
			Set<ProductInstance> productInstanceSet) {
		if (defProductCondition != null && productInstanceSet != null) {
			productInstanceSet.stream().forEach(pi -> checkProductConsistency(defProductCondition, pi));
		}
	}

	public void checkInstanceConsistency(WorkItem workItem, Set<ProductInstance> productInstanceSet) {
		if (workItem != null && productInstanceSet != null) {
			productInstanceSet.stream().forEach(pi -> checkInstanceConsistency(workItem, pi));
		}
	}

}
