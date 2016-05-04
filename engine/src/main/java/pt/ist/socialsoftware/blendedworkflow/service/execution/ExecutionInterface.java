package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityWorkItemDTO;

public class ExecutionInterface {
	private static Logger logger = LoggerFactory.getLogger(ExecutionInterface.class);

	private static ExecutionInterface instance;

	public static ExecutionInterface getInstance() {
		if (instance == null) {
			instance = new ExecutionInterface();
		}
		return instance;
	}

	private ExecutionInterface() {
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

		Set<ActivityWorkItemDTO> activityWorkItemDTOs = new HashSet<ActivityWorkItemDTO>();

		for (Activity activity : workflowInstance.getEnabledActivitySet()) {
			ActivityWorkItemDTO activityWorkItemDTO = new ActivityWorkItemDTO();
			activityWorkItemDTO.setSpecId(specId);
			activityWorkItemDTO.setInstanceName(instanceName);
			activityWorkItemDTO.setActivityName(activity.getName());
			activityWorkItemDTO.setContext(new HashMap<String, Set<String>>());
			Map<Entity, Set<EntityInstance>> context = activity.getInstanceContext(workflowInstance);
			for (Entity entity : context.keySet()) {
				activityWorkItemDTO.getContext().put(entity.getName(),
						context.get(entity).stream().map(ei -> ei.getExternalId()).collect(Collectors.toSet()));
			}

			activityWorkItemDTOs.add(activityWorkItemDTO);
		}

		return activityWorkItemDTOs;
	}
}
