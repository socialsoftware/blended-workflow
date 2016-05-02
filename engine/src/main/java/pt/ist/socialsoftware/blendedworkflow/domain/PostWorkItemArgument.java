package pt.ist.socialsoftware.blendedworkflow.domain;

public class PostWorkItemArgument extends PostWorkItemArgument_Base {

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
		checkInstanceConsistency(getWorkItemOfPost(), productInstance);
		checkProductConsistency(getDefProductCondition(), productInstance);
		super.setProductInstance(productInstance);
	}

	public PostWorkItemArgument(WorkItem workItem, DefProductCondition defProductCondition) {
		setWorkItemOfPost(workItem);
		setDefProductCondition(defProductCondition);
	}

	public void delete() {
		setWorkItemOfPost(null);
		setDefProductCondition(null);

		setProductInstance(null);

		deleteDomainObject();
	}

}
