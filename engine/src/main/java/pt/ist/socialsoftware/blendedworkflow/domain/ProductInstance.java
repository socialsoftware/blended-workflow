package pt.ist.socialsoftware.blendedworkflow.domain;

public abstract class ProductInstance extends ProductInstance_Base {

	public void delete() {
		getWorkItem().delete();
	}

	public abstract WorkflowInstance getWorkflowInstance();

	public abstract Product getProduct();

}
