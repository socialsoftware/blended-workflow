package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class ProductInstance extends ProductInstance_Base {

	public void delete() {
		getPreWorkItemArgumentSet().stream().forEach(wia -> wia.delete());
		setPostWorkItemArgument(null);

		deleteDomainObject();
	}

	public abstract WorkflowInstance getWorkflowInstance();

	public abstract Product getProduct();

	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		return new HashSet<ProductInstance>();
	}

}
