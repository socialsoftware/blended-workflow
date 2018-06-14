package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.GoalWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ExecutionResourcesInterface extends ExecutionInterface {
	private static Logger logger = LoggerFactory.getLogger(ExecutionResourcesInterface.class);

	private static ExecutionResourcesInterface instance;

	public static ExecutionResourcesInterface getInstance() {
		if (instance == null) {
			instance = new ExecutionResourcesInterface();
		}
		return instance;
	}

	protected ExecutionResourcesInterface() {

	}

	@Override
	protected Set<Activity> getPendingActivitySet(WorkflowInstance workflowInstance) {
		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		return super.getPendingActivitySet(workflowInstance).stream()
				.filter(activity -> activity.getResponsibleFor().hasEligiblePerson(person, workflowInstance))
				.collect(toSet());
	}

	@Override
	protected Set<Goal> getPendingGoalSet(WorkflowInstance workflowInstance) {
		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		return super.getPendingGoalSet(workflowInstance).stream()
				.filter(goal -> goal.getResponsibleFor().hasEligiblePerson(person, workflowInstance))
				.collect(toSet());
	}

	@Override
	public ActivityWorkItem executeActivityWorkItem(ActivityWorkItemDTO activityWorkItemDTO) {
		ActivityWorkItem activityWI = super.executeActivityWorkItem(activityWorkItemDTO);

		Specification spec = BlendedWorkflow.getInstance().getSpecById(activityWorkItemDTO.getSpecId())
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		if (!activityWI.getActivity().getResponsibleFor().hasEligiblePerson(person, activityWI.getWorkflowInstance())) {
			throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
		}

		activityWI.setExecutionUser(user);

		return activityWI;
	}

	@Override
	public GoalWorkItem executeGoalWorkItem(GoalWorkItemDTO goalWorkItemDTO) {
		GoalWorkItem goalWI = super.executeGoalWorkItem(goalWorkItemDTO);

		Specification spec = BlendedWorkflow.getInstance().getSpecById(goalWorkItemDTO.getSpecId())
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		if (!goalWI.getGoal().getResponsibleFor().hasEligiblePerson(person, goalWI.getWorkflowInstance())) {
			throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
		}

		goalWI.setExecutionUser(user);

		return goalWI;
	}

	@Override
	public List<ActivityWorkItem> getLogActivityWorkItemSet(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return super.getLogActivityWorkItemSet(specId, instanceName).stream()
				.filter(wi -> wi.getActivity().getInforms().hasEligiblePerson(person, workflowInstance))
				.collect(toList());
	}

	@Override
	public List<GoalWorkItem> getLogGoalWorkItemSet(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return super.getLogGoalWorkItemSet(specId, instanceName).stream()
				.filter(wi -> wi.getGoal().getInforms().hasEligiblePerson(person, workflowInstance))
				.collect(toList());
	}

	@Override
	public Set<ActivityWorkItemDTO> getPendingActivityWorkItemSet(String specId, String instanceName) {
		return super.getPendingActivityWorkItemSet(specId, instanceName);
	}

	@Override
	public Set<GoalWorkItemDTO> getPendingGoalWorkItemSet(String specId, String instanceName) {
		return super.getPendingGoalWorkItemSet(specId, instanceName);
	}
}
