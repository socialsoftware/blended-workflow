package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductInstanceDTO;

public abstract class ProductInstance extends ProductInstance_Base {

	public void delete() {
		getPreWorkItemArgumentSet().stream().forEach(wia -> wia.delete());
		setPostWorkItemArgument(null);

		deleteDomainObject();
	}

	public abstract WorkflowInstance getWorkflowInstance();

	public abstract Product getProduct();

	public abstract Entity getEntity();

	public abstract EntityInstance getEntityInstance();

	public abstract ProductInstanceDTO getDTO();

	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		return new HashSet<ProductInstance>();
	}

	public abstract boolean isDefined();

	public abstract boolean holdsPre(DefPathCondition defPathCondition);
}
