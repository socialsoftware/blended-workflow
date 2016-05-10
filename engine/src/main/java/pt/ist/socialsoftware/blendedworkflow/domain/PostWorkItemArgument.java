package pt.ist.socialsoftware.blendedworkflow.domain;

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

}
