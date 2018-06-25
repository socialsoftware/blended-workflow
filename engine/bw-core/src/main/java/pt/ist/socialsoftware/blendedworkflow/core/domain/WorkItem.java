package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkItemDTO;

public abstract class WorkItem extends WorkItem_Base {

	public void addPreWorkItemArgument(ProductInstanceDTO productInstanceDTO, DefPathCondition defPathCondition) {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this, defPathCondition);
		preWorkItemArgument.addProductInstance(FenixFramework.getDomainObject(productInstanceDTO.getExternalId()));
		addPreCondition(preWorkItemArgument);
	}

	public void addPostWorkItemArgument(ProductInstance productInstance, DefProductCondition defProductCondition) {
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(this, defProductCondition);
		postWorkItemArgument.addProductInstance(productInstance);
		addPostCondition(postWorkItemArgument);
	}

	public void delete() {
		setWorkflowInstance(null);

		getPreConditionSet().stream().forEach(dpc -> dpc.delete());
		getPostConditionSet().stream().forEach(dpc -> dpc.delete());

		deleteDomainObject();
	}

	public boolean holds(Set<DefPathCondition> defPathConditions, Set<DefProductCondition> defProductConditions) {
		return checkCompleteSetOfPreConditions() && preConditionsHold() && postConditionsHold();
	}

	protected boolean checkCompleteSetOfPreConditions() {
		Set<Path> preConditions = getPreConditionSet().stream().map(wia -> wia.getDefPathCondition().getPath())
				.collect(Collectors.toSet());
		for (DefPathCondition defPathCondition : definedPreConditions()) {
			if (!preConditions.contains(defPathCondition.getPath())) {
				throw new BWException(BWErrorType.PRE_WORK_ITEM_ARGUMENT,
						"DefPathCondition not defined " + defPathCondition.getPath().getValue());
			}
		}
		return true;
	}

	public boolean preConditionsHold() {
		return getPreConditionSet().stream().allMatch(wia -> wia.preHolds(getPostConditionSet()));
	}

	protected abstract boolean postConditionsHold();

	protected abstract Set<DefPathCondition> definedPreConditions();

	public void fillDTO(WorkItemDTO workItemDTO) {
		workItemDTO.setTimestamp(getCounter());
		workItemDTO
				.setPreArguments(
						getPreConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDTO().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining("\r\n")));
		workItemDTO
				.setPostArguments(
						getPostConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDTO().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining("\r\n")));
	}

}
