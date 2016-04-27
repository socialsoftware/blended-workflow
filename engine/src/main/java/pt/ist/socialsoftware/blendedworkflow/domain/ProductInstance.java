package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class ProductInstance extends ProductInstance_Base {

	public void delete() {
		getWorkItem().delete();
	}

	public abstract WorkflowInstance getWorkflowInstance();

	public abstract Product getProduct();

	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		return new HashSet<ProductInstance>();
	}

}
