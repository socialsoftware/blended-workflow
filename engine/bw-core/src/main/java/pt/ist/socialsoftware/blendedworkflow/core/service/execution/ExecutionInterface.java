package pt.ist.socialsoftware.blendedworkflow.core.service.execution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.GoalWorkItemDTO;

public class ExecutionInterface {
	private static Logger logger = LoggerFactory.getLogger(ExecutionInterface.class);

	private static ExecutionInterface instance;

	public static ExecutionInterface getInstance() {
		if (instance == null) {
			instance = new ExecutionInterface();
		}
		return instance;
	}

	protected ExecutionInterface() {
	}

	private BlendedWorkflow getBlendedWorkflow() {
		return BlendedWorkflow.getInstance();
	}

	private Specification getSpecification(String specId) {
		return getBlendedWorkflow().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.UNKNOWN_SPECIFICATION, specId));
	}

	public Set<WorkflowInstance> getWorkflowInstances(String specId) {
		return getSpecification(specId).getWorkflowInstanceSet();
	}

	public WorkflowInstance getWorkflowInstance(String specId, String name) {
		return getSpecification(specId).getWorkflowInstance(name);
	}

	public Activity getActivity(String specId, String activityName) {
		return getSpecification(specId).getActivityModel().getActivity(activityName);
	}

	public Goal getGoal(String specId, String goalName) {
		return getSpecification(specId).getGoalModel().getGoal(goalName);
	}

	@Atomic(mode = TxMode.WRITE)
	public WorkflowInstance createWorkflowInstance(String specId, String name) {
		return new WorkflowInstance(getSpecification(specId), name);
	}

	@Atomic(mode = TxMode.WRITE)
	public void deleteWorkflowInstance(String specId, String name) {
		getSpecification(specId).getWorkflowInstance(name).delete();

	}

	public Set<ActivityWorkItemDTO> getPendingActivityWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<ActivityWorkItemDTO> activityWorkItemDTOs = new HashSet<>();

		for (Activity activity : workflowInstance.getEnabledActivitySet()) {
			activityWorkItemDTOs.add(ActivityWorkItemDTO.createActivityWorkItemDTO(workflowInstance, activity));
		}

		return activityWorkItemDTOs;
	}

	public List<ActivityWorkItem> getLogActivityWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		return workflowInstance.getLogActivityWorkItemList();
	}

	@Atomic(mode = TxMode.WRITE)
	public void executeActivityWorkItem(ActivityWorkItemDTO activityWorkItemDTO) {
		WorkflowInstance workflowInstance = getWorkflowInstance(activityWorkItemDTO.getSpecId(),
				activityWorkItemDTO.getWorkflowInstanceName());
		Activity activity = getActivity(activityWorkItemDTO.getSpecId(), activityWorkItemDTO.getName());

		ActivityWorkItem activityWorkItem = activityWorkItemDTO.createActivityWorkItem(workflowInstance, activity);

		activityWorkItem.holds(activity.getPreConditionSet(), activity.getPostConditionSet());
	}

	public Set<GoalWorkItemDTO> getPendingGoalWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<GoalWorkItemDTO> goalWorkItemDTOs = new HashSet<>();

		for (Goal goal : workflowInstance.getEnabledGoalSet()) {
			goalWorkItemDTOs.add(GoalWorkItemDTO.createGoalWorkItemDTO(workflowInstance, goal));
		}

		return goalWorkItemDTOs;
	}

	public List<GoalWorkItem> getLogGoalWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		return workflowInstance.getLogGoalWorkItemList();
	}

	@Atomic(mode = TxMode.WRITE)
	public void executeGoalWorkItem(GoalWorkItemDTO goalWorkItemDTO) {
		WorkflowInstance workflowInstance = getWorkflowInstance(goalWorkItemDTO.getSpecId(),
				goalWorkItemDTO.getWorkflowInstanceName());
		Goal goal = getGoal(goalWorkItemDTO.getSpecId(), goalWorkItemDTO.getName());

		GoalWorkItem goalWorkItem = goalWorkItemDTO.createGoalWorkItem(workflowInstance, goal);

		goalWorkItem.holds(goal.getActivationConditionSet(), goal.getSuccessConditionSet());
	}

}
