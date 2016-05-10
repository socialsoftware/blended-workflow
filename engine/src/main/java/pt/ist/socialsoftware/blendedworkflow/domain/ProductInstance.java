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

	public abstract EntityInstance getEntityInstance();

	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		return new HashSet<ProductInstance>();
	}

	public abstract boolean isDefined();

	public abstract boolean holdsPost(DefProductCondition defProductCondition, Set<MulCondition> mulConditionSet);

	public abstract boolean holdsPre(DefPathCondition defPathCondition);
}
