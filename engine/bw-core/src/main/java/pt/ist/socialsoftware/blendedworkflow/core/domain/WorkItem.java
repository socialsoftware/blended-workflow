package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkItemDto;

public abstract class WorkItem extends WorkItem_Base {

	public void addPreWorkItemArgument(ProductInstanceDto productInstanceDto, DefPathCondition defPathCondition) {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this, defPathCondition);
		preWorkItemArgument.addProductInstance(FenixFramework.getDomainObject(productInstanceDto.getExternalId()));
		addPreCondition(preWorkItemArgument);
	}

	public void addPreWorkItemArgument(ProductInstance productInstance, DefPathCondition defPathCondition) {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(this, defPathCondition);
		preWorkItemArgument.addProductInstance(productInstance);
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

	public abstract WorkItemDto getDto();

	public final void fillDto(WorkItemDto workItemDto) {
		workItemDto.setTimestamp(getCounter());
		workItemDto
				.setPreArguments(
						getPreConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDto().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining("\r\n")));
		workItemDto
				.setPostArguments(
						getPostConditionSet()
								.stream().map(pwia -> pwia.getProductInstanceSet().stream()
										.map(pi -> pi.getDto().getValue()).collect(Collectors.joining(",")))
								.collect(Collectors.joining("\r\n")));
	}

}
