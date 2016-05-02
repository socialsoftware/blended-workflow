package pt.ist.socialsoftware.blendedworkflow.domain;

public class PreWorkItemArgument extends PreWorkItemArgument_Base {

	@Override
	public void setWorkItemOfPre(WorkItem workItem) {
		checkInstanceConsistency(workItem, getProductInstance());
		super.setWorkItemOfPre(workItem);
	}

	@Override
	public void setDefPathCondition(DefPathCondition defPathCondition) {
		checkProductConsistency(defPathCondition, getProductInstance());
		super.setDefPathCondition(defPathCondition);
	}

	@Override
	public void setProductInstance(ProductInstance productInstance) {
		checkInstanceConsistency(getWorkItemOfPre(), productInstance);
		checkProductConsistency(getDefPathCondition(), productInstance);
		super.setProductInstance(productInstance);
	}

	public PreWorkItemArgument(WorkItem workItem, DefPathCondition defPathCondition, ProductInstance productInstance) {
		setWorkItemOfPre(workItem);
		setDefPathCondition(defPathCondition);
		setProductInstance(productInstance);
	}

	public void delete() {
		setWorkItemOfPre(null);
		setDefPathCondition(null);

		setProductInstance(null);

		deleteDomainObject();
	}

}
