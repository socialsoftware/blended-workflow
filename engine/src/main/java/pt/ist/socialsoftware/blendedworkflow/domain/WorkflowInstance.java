package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.WorkflowInstanceDTO;

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
	}

	public WorkflowInstanceDTO getDTO() {
		WorkflowInstanceDTO workflowInstanceDTO = new WorkflowInstanceDTO();
		workflowInstanceDTO.setSpecId(getSpecification().getSpecId());
		workflowInstanceDTO.setName(getName());

		return workflowInstanceDTO;
	}

}
