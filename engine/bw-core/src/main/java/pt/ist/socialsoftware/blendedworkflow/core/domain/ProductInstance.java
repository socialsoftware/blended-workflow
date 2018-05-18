package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ProductInstanceDTO;

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
		return new HashSet<>();
	}

	public abstract boolean isDefined();

	public abstract boolean holdsPre(DefPathCondition defPathCondition);

	public WorkItem getCreatorWorkItem() {
		return getPostWorkItemArgument() != null ? getPostWorkItemArgument().getWorkItemOfPost() : null;
	}

	public Set<WorkItem> getReadWorkItemSet() {
		return getPreWorkItemArgumentSet() != null
				? getPreWorkItemArgumentSet().stream().map(pwia -> pwia.getWorkItemOfPre()).collect(Collectors.toSet())
				: null;
	}

}
