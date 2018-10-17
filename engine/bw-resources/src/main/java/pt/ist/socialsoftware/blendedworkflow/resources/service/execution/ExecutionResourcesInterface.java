package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GoalWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.WorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.DashboardDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceActivityWorkItemDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceEntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.ResourceGoalWorkItemDto;

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

	/**
	 *
	 * Get next work items
	 *
	 */


	@Override
	protected Set<Activity> getPendingActivitySet(WorkflowInstance workflowInstance) {
		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		return super.getPendingActivitySet(workflowInstance).stream()
				.filter(activity -> person.getName().equals("Admin") || activity.getResponsibleFor() == null ||
						activity.getResponsibleFor().hasEligiblePerson(person, workflowInstance, activity.getPostProducts()))
				.collect(toSet());
	}

	@Override
	protected Set<Goal> getPendingGoalSet(WorkflowInstance workflowInstance) {
		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		return super.getPendingGoalSet(workflowInstance).stream()
				.filter(goal -> person.getName().equals("Admin") || goal.getResponsibleFor() == null ||
						goal.getResponsibleFor().hasEligiblePerson(person, workflowInstance,
							goal.getSuccessConditionSet().stream().map(DefProductCondition::getTargetOfPath).collect(toSet())
						))
				.collect(toSet());
	}

	@Override
	public Set<ActivityWorkItemDto> getPendingActivityWorkItemSet(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<ActivityWorkItemDto> activityWorkItemDTOs = new HashSet<>();

		for (Activity activity : getPendingActivitySet(workflowInstance)) {
			activityWorkItemDTOs.add(ResourceActivityWorkItemDto.createActivityWorkItemDTO(workflowInstance, activity, person));
		}

		return activityWorkItemDTOs;
	}

	@Override
	public Set<GoalWorkItemDto> getPendingGoalWorkItemSet(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<GoalWorkItemDto> goalWorkItemDTOs = new HashSet<>();

		for (Goal goal : getPendingGoalSet(workflowInstance)) {
			goalWorkItemDTOs.add(ResourceGoalWorkItemDto.createGoalWorkItemDTO(workflowInstance, goal, person));
		}

		return goalWorkItemDTOs;
	}

	/**
	 *
	 * Execute work items
	 *
	 */

	@Override
	@Atomic(mode = Atomic.TxMode.WRITE)
	public ActivityWorkItem executeActivityWorkItem(ActivityWorkItemDto activityWorkItemDTO) {
		ActivityWorkItem activityWI = super.executeActivityWorkItem(activityWorkItemDTO);

		Specification spec = BlendedWorkflow.getInstance().getSpecById(activityWorkItemDTO.getSpecId())
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		if (activityWI.getActivity().getResponsibleFor() != null && !person.getName().equals("Admin")) {
			if (!activityWI.getActivity().getResponsibleFor().hasEligiblePerson(person, activityWI.getWorkflowInstance(),
					activityWI.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet())
			)) {
				throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
			}

		}

		activityWI.setExecutionUser(user);

		return activityWI;
	}

	@Override
	@Atomic(mode = Atomic.TxMode.WRITE)
	public GoalWorkItem executeGoalWorkItem(GoalWorkItemDto goalWorkItemDTO) {
		GoalWorkItem goalWI = super.executeGoalWorkItem(goalWorkItemDTO);

		Specification spec = BlendedWorkflow.getInstance().getSpecById(goalWorkItemDTO.getSpecId())
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);


		if (goalWI.getGoal().getResponsibleFor() != null && !person.getName().equals("Admin")) {
			if (!goalWI.getGoal().getResponsibleFor().hasEligiblePerson(person, goalWI.getWorkflowInstance(),
					goalWI.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet())
			)) {
				throw new RMException(RMErrorType.PERSON_IS_NOT_ELIGIBLE);
			}
		}

		goalWI.setExecutionUser(user);

		return goalWI;
	}

	/**
	 *
	 * Get work items history log
	 *
	 */

	@Override
	public List<ActivityWorkItem> getLogActivityWorkItemSet(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return super.getLogActivityWorkItemSet(specId, instanceName).stream()
				.filter(wi -> wi.getActivity().getInforms() == null || wi.getActivity().getInforms().hasEligiblePerson(person, workflowInstance,
						wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet())
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
						wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet())
				))
				.collect(toList());
	}

	@Override
	public List<ActivityWorkItemDto> getLogActivityWorkItemDtoSet(String specId, String instanceName) {
		return getLogActivityWorkItemSet(specId, instanceName).stream()
				.map(activityWorkItem -> ResourceActivityWorkItemDto.fillActivityWorkItemDTO(activityWorkItem.getDto(), activityWorkItem))
				.collect(toList());
	}

	@Override
	public List<GoalWorkItemDto> getLogGoalWorkItemDTOSet(String specId, String instanceName) {
		return getLogGoalWorkItemSet(specId, instanceName).stream()
				.map(goalWorkItem -> ResourceGoalWorkItemDto.fillGoalWorkItemDTO(goalWorkItem.getDto(), goalWorkItem))
				.collect(toList());
	}

	@Override
	public List<WorkItemDto> getLogWorkItemDtoList(String specId, String instanceName) {
		return super.getLogWorkItemList(specId, instanceName).stream()
				.map(workitem -> {
					if (workitem instanceof ActivityWorkItem) {
                        ActivityWorkItem awi = (ActivityWorkItem) workitem;
						return ResourceActivityWorkItemDto.fillActivityWorkItemDTO(awi.getDto(), awi);
					} else if (workitem instanceof GoalWorkItem) {
						GoalWorkItem gwi = (GoalWorkItem) workitem;
						return ResourceGoalWorkItemDto.fillGoalWorkItemDTO(gwi.getDto(), gwi);
					} else {
					    return workitem.getDto();
                    }
				})
				.collect(toList());
	}

	@Override
	protected List<WorkItem> getLogWorkItemList(String specId, String instanceName) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return super.getLogWorkItemList(specId, instanceName).stream()
				.filter(wi -> {
					if (wi instanceof GoalWorkItem) {
						GoalWorkItem gwi = (GoalWorkItem) wi;
						return person.getName().equals("Admin") || gwi.getGoal().getInforms() == null || gwi.getGoal().getInforms().hasEligiblePerson(person, workflowInstance,
								wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet()));
					} else if (wi instanceof  ActivityWorkItem) {
						ActivityWorkItem awi = (ActivityWorkItem) wi;
						return person.getName().equals("Admin") || awi.getActivity().getInforms() == null || awi.getActivity().getInforms().hasEligiblePerson(person, workflowInstance,
								wi.getPostConditionSet().stream().map(postWorkItemArgument -> postWorkItemArgument.getDefProductCondition().getTargetOfPath()).collect(toSet()));
					} else {
						return true;
					}
				}
				).collect(toList());
	}

	/**
	 *
	 * Get entity instances for Data view
	 *
	 */

	@Override
	public Set<EntityInstance> getEntityInstances(String specId, String name) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, name);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return super.getEntityInstances(specId, name).stream()
				.filter(entityInstance -> entityInstance.getEntity().getInforms() == null ||
						entityInstance.getEntity().getInforms().hasEligiblePerson(person, workflowInstance, new HashSet<>()))

				.collect(toSet());
	}

	@Override
	public Set<EntityInstanceDto> getEntityInstancesDto(String specId, String name) {
		Specification spec = BlendedWorkflow.getInstance().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID));

		WorkflowInstance workflowInstance = getWorkflowInstance(specId, name);

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(spec);

		return getEntityInstances(specId, name).stream().map(i -> new ResourceEntityInstanceDto(i, EntityInstanceDto.Depth.DEEP, person)).collect(toSet());
		//return super.getEntityInstancesDto(specId, name);
	}

	/**
	 *
	 * Get User dashboard
	 *
	 */

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
