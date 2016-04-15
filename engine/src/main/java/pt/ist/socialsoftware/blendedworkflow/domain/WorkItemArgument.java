package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class WorkItemArgument extends WorkItemArgument_Base {

	@Override
	public void setWorkItemOfPre(WorkItem workItem) {
		checkInstanceConsistency(workItem, getProductInstance());
		super.setWorkItemOfPre(workItem);
	}

	@Override
	public void setWorkItemOfPost(WorkItem workItem) {
		checkInstanceConsistency(workItem, getProductInstance());
		super.setWorkItemOfPost(workItem);
	}

	@Override
	public void setDefProductCondition(DefProductCondition defProductCondition) {
		checkProductConsistency(defProductCondition, getProductInstance());
		super.setDefProductCondition(defProductCondition);
	}

	@Override
	public void setProductInstance(ProductInstance productInstance) {
		checkInstanceConsistency(getWorkItemOfPost() != null ? getWorkItemOfPost() : getWorkItemOfPre(),
				productInstance);
		checkProductConsistency(getDefProductCondition(), productInstance);
		super.setProductInstance(productInstance);
	}

	public WorkItemArgument initPreWorkItem(WorkItem workItem, DefPathCondition defPathCondition,
			ProductInstance productInstance, String value) {
		setWorkItemOfPre(workItem);
		setWorkItemOfPost(null);
		setDefProductCondition(defPathCondition);
		setProductInstance(productInstance);
		setValue(value);
		return this;
	}

	public WorkItemArgument initPostWorkItem(WorkItem workItem, DefProductCondition defProductCondition) {
		setWorkItemOfPre(null);
		setWorkItemOfPost(workItem);
		setDefProductCondition(defProductCondition);
		return this;
	}

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

	public void delete() {
		setWorkItemOfPre(null);
		setWorkItemOfPost(null);

		setProductInstance(null);
		setDefProductCondition(null);
	}

}
