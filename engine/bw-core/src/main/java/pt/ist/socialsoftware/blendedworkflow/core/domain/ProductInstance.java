package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDto;

public abstract class ProductInstance extends ProductInstance_Base {
	public enum ProductInstanceState {
		DEFINED, UNDEFINED, SKIPPED
	}

	public void delete() {
		getPreWorkItemArgumentSet().stream().forEach(wia -> wia.delete());
		getPostWorkItemArgumentSet().stream().forEach(wia -> wia.delete());

		deleteDomainObject();
	}

	public abstract WorkflowInstance getWorkflowInstance();

	public abstract Product getProduct();

	public abstract Entity getEntity();

	public abstract EntityInstance getEntityInstance();

	public abstract ProductInstanceDto getDto();

	public Set<ProductInstance> getProductInstanceSetByName(String name) {
		return new HashSet<>();
	}

	public abstract boolean isDefined();

	public abstract boolean holdsPre(DefPathCondition defPathCondition);

	public Set<WorkItem> getReadWorkItemSet() {
		return getPreWorkItemArgumentSet() != null
				? getPreWorkItemArgumentSet().stream().map(pwia -> pwia.getWorkItemOfPre()).collect(Collectors.toSet())
				: null;
	}

	public Set<ProductInstance> getDependentProductInstances(Dependence dependence) {
		Set<ProductInstance> productInstances;
		try {
			productInstances = getEntityInstance().getProductInstancesByPath(dependence.getPath().getValue());
		} catch (BWException bwe) {
			productInstances = new HashSet<>();
		}

		return productInstances;
	}

}
