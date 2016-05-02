package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class WorkItemArgument extends WorkItemArgument_Base {

	public void checkProductConsistency(DefProductCondition defProductCondition, ProductInstance productInstance) {
		if (defProductCondition != null && productInstance != null) {
			if (defProductCondition.getTargetOfPath() != productInstance.getProduct()) {
				throw new BWException(BWErrorType.WORKITEMARGUMENT_CONSISTENCY,
						defProductCondition.getTargetOfPath().getName() + "<>"
								+ productInstance.getProduct().getName());
			}
		}
	}

	public void checkInstanceConsistency(WorkItem workItem, ProductInstance productInstance) {
		if (workItem != null && productInstance != null) {
			if (workItem.getWorkflowInstance() != productInstance.getWorkflowInstance()) {
				throw new BWException(BWErrorType.WORKITEMARGUMENT_CONSISTENCY, workItem.getWorkflowInstance().getName()
						+ "<>" + productInstance.getWorkflowInstance().getName());
			}
		}
	}

}
