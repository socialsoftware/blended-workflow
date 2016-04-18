package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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

}
