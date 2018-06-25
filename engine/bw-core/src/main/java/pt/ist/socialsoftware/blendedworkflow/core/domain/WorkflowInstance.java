package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkflowInstanceDTO;

public class WorkflowInstance extends WorkflowInstance_Base {

	@Override
	public void setName(String name) {
		checkUniqueName(name);
		super.setName(name);
	}

	public WorkflowInstance(Specification spec, String name) {
		setSpecification(spec);
		setName(name);
		setLogCounter(0);
	}

	private void checkUniqueName(String name) {
		if (name == null || name.equals("")) {
			throw new BWException(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, name);
		}

		if (getSpecification().getWorkflowInstanceSet().stream()
				.anyMatch(wi -> wi != this && wi.getName().equals(name))) {
			throw new BWException(BWErrorType.WORKFLOWINSTANCE_CONSISTENCY, name);
		}
	}

	public void delete() {
		setSpecification(null);
		getWorkItemSet().stream().forEach(wi -> wi.delete());
		getEntityInstanceSet().stream().forEach(ei -> ei.delete());

		deleteDomainObject();
	}

	public WorkflowInstanceDTO getDTO() {
		WorkflowInstanceDTO workflowInstanceDTO = new WorkflowInstanceDTO();
		workflowInstanceDTO.setSpecId(getSpecification().getSpecId());
		workflowInstanceDTO.setName(getName());

		return workflowInstanceDTO;
	}

	public int incLogCounter() {
		setLogCounter(getLogCounter() + 1);
		return getLogCounter();
	}

	public Set<EntityInstance> getEntityInstanceSet(Entity entity) {
		return getEntityInstanceSet().stream().filter(ei -> ei.getEntity() == entity).collect(Collectors.toSet());
	}

	public List<ActivityWorkItem> getLogActivityWorkItemList() {
		return getWorkItemSet().stream().filter(ActivityWorkItem.class::isInstance).map(ActivityWorkItem.class::cast)
				.sorted((wi1, wi2) -> Integer.compare(wi1.getCounter(), wi2.getCounter())).collect(Collectors.toList());
	}

	public List<GoalWorkItem> getLogGoalWorkItemList() {
		return getWorkItemSet().stream().filter(GoalWorkItem.class::isInstance).map(GoalWorkItem.class::cast)
				.sorted((wi1, wi2) -> Integer.compare(wi1.getCounter(), wi2.getCounter())).collect(Collectors.toList());
	}

	public List<GoalWorkItem> getLogGoalWorkItemSet() {
		return getWorkItemSet().stream().filter(GoalWorkItem.class::isInstance).map(GoalWorkItem.class::cast)
				.sorted((wi1, wi2) -> Integer.compare(wi1.getCounter(), wi2.getCounter())).collect(Collectors.toList());
	}

	public Set<Activity> getEnabledActivitySet() {
		return getSpecification().getActivityModel().getActivitySet().stream()
				.filter(a -> a.isEnabledForExecution(this)).collect(Collectors.toSet());
	}

	public Set<Goal> getEnabledGoalSet() {
		return getSpecification().getGoalModel().getGoalSet().stream().filter(g -> g.isEnabledForExecution(this))
				.collect(Collectors.toSet());
	}

}
