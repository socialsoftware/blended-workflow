package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.DashboardDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceGoalWorkItemDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
				.filter(activity -> activity.getResponsibleFor() == null || activity.getResponsibleFor().hasEligiblePerson(person, workflowInstance, activity.getPostProducts()))
				.collect(toSet());
	}

	@Override
	protected Set<Goal> getPendingGoalSet(WorkflowInstance workflowInstance) {
		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		return super.getPendingGoalSet(workflowInstance).stream()
				.filter(goal -> goal.getResponsibleFor() == null || goal.getResponsibleFor().hasEligiblePerson(person, workflowInstance,
						goal.getSuccessConditionSet().stream().map(DefProductCondition::getTargetOfPath).collect(Collectors.toSet())
				))
				.collect(toSet());
	}

	@Override
	public Set<ActivityWorkItemDto> getPendingActivityWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<ActivityWorkItemDto> activityWorkItemDTOs = new HashSet<>();

		for (Activity activity : getPendingActivitySet(workflowInstance)) {
			activityWorkItemDTOs.add(ResourceActivityWorkItemDto.createActivityWorkItemDTO(workflowInstance, activity));
		}

		return activityWorkItemDTOs;
	}

	@Override
	public Set<GoalWorkItemDto> getPendingGoalWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<GoalWorkItemDto> goalWorkItemDTOs = new HashSet<>();

		for (Goal goal : getPendingGoalSet(workflowInstance)) {
			goalWorkItemDTOs.add(ResourceGoalWorkItemDto.createGoalWorkItemDTO(workflowInstance, goal));
		}

		return goalWorkItemDTOs;
	}

	@Override
	@Atomic(mode = Atomic.TxMode.WRITE)
	public ActivityWorkItem executeActivityWorkItem(ActivityWorkItemDto activityWorkItemDTO) {
		ActivityWorkItem activityWI = super.executeActivityWorkItem(activityWorkItemDTO);

		if (activityWI.getActivity().getResponsibleFor() != null) {
			Specification spec = BlendedWorkflow.getInstance().getSpecById(activityWorkItemDTO.getSpecId())
					.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

			User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
			Person person = user.getPerson(spec);

			if (activityWI.getActivity().getResponsibleFor() != null && !activityWI.getActivity().getResponsibleFor().hasEligiblePerson(person, activityWI.getWorkflowInstance(),
					activityWI.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(Collectors.toSet())
			)) {
				throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
			}

			activityWI.setExecutionUser(user);
		}

		return activityWI;
	}

	@Override
	@Atomic(mode = Atomic.TxMode.WRITE)
	public GoalWorkItem executeGoalWorkItem(GoalWorkItemDto goalWorkItemDTO) {
		GoalWorkItem goalWI = super.executeGoalWorkItem(goalWorkItemDTO);

		if (goalWI.getGoal().getResponsibleFor() != null) {
			Specification spec = BlendedWorkflow.getInstance().getSpecById(goalWorkItemDTO.getSpecId())
					.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

			User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
			Person person = user.getPerson(spec);

			if (!goalWI.getGoal().getResponsibleFor().hasEligiblePerson(person, goalWI.getWorkflowInstance(),
					goalWI.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(Collectors.toSet())
			)) {
				throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
			}

			goalWI.setExecutionUser(user);
		}

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
				.filter(wi -> wi.getActivity().getInforms() == null || wi.getActivity().getInforms().hasEligiblePerson(person, workflowInstance,
						wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(Collectors.toSet())
				))
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
				.filter(wi -> wi.getGoal().getInforms() == null || wi.getGoal().getInforms().hasEligiblePerson(person, workflowInstance,
						wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(Collectors.toSet())
				))
				.collect(toList());
	}

	@Override
	public List<ActivityWorkItemDto> getLogActivityWorkItemDtoSet(String specId, String instanceName) {
		return getLogActivityWorkItemSet(specId, instanceName).stream()
				.map(activityWorkItem -> ResourceActivityWorkItemDto.fillActivityWorkItemDTO(activityWorkItem.getDto(), activityWorkItem))
				.collect(Collectors.toList());
	}

	@Override
	public List<GoalWorkItemDto> getLogGoalWorkItemDTOSet(String specId, String instanceName) {
		return getLogGoalWorkItemSet(specId, instanceName).stream()
				.map(goalWorkItem -> ResourceGoalWorkItemDto.fillGoalWorkItemDTO(goalWorkItem.getDto(), goalWorkItem))
				.collect(Collectors.toList());
	}

	public DashboardDto getDashboard() {
		BlendedWorkflow blended = BlendedWorkflow.getInstance();

		Set<ActivityWorkItemDto> activityWorkItemDtos = new HashSet<>();
		Set<GoalWorkItemDto> goalWorkItemDtos = new HashSet<>();

		blended.getSpecificationSet().stream()
			.forEach(spec -> {
				spec.getWorkflowInstanceSet().stream()
					.forEach(wi -> {
						activityWorkItemDtos.addAll(getPendingActivityWorkItemSet(spec.getSpecId(), wi.getName()));
						goalWorkItemDtos.addAll(getPendingGoalWorkItemSet(spec.getSpecId(), wi.getName()));
					});
			});

		return new DashboardDto(activityWorkItemDtos, goalWorkItemDtos);
	}
}
