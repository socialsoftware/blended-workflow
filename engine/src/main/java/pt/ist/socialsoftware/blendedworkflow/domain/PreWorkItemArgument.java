package pt.ist.socialsoftware.blendedworkflow.domain;

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

}
