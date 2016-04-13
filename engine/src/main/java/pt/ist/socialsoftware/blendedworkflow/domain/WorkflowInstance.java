package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class WorkflowInstance extends WorkflowInstance_Base {

	@Override
	public void setName(String name) {
		checkUniqueName(name);
		super.setName(name);
	}

	public WorkflowInstance(Specification spec, String name) {
		setSpecification(spec);
		setName(name);
	}

	private void checkUniqueName(String name) {
		if (name == null || name.equals("")) {
			throw new BWException(BWErrorType.EMPTY_WORKFLOW_INSTANCE_NAME, name);
		}

		if (getSpecification().getWorkflowInstanceSet().stream()
				.anyMatch(wi -> wi != this && wi.getName().equals(name))) {
			throw new BWException(BWErrorType.DUPLICATE_WORKFLOW_INSTANCE_NAME, name);
		}
	}

}
