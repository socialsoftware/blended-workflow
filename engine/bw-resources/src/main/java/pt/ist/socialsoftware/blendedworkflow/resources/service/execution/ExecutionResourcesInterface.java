package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.HashSet;
import java.util.Set;

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

	public Set<ActivityWorkItemDTO> getPendingActivityWorkItemSet(String specId, String instanceName) {
		WorkflowInstance workflowInstance = getWorkflowInstance(specId, instanceName);

		Set<ActivityWorkItemDTO> activityWorkItemDTOs = new HashSet<>();

		User user = User.getAuthenticatedUser().orElseThrow(() -> new RMException(RMErrorType.NO_LOGIN));
		Person person = user.getPerson(workflowInstance.getSpecification());

		for (Activity activity : workflowInstance.getEnabledActivitySet()) {
			if (activity.getResponsibleFor().hasEligiblePerson(person, workflowInstance)) {
				activityWorkItemDTOs.add(ActivityWorkItemDTO.createActivityWorkItemDTO(workflowInstance, activity));
			}
		}

		return activityWorkItemDTOs;
	}

}
