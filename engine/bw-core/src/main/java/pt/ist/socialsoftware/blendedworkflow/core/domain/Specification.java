package pt.ist.socialsoftware.blendedworkflow.core.domain;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDTO;

public class Specification extends Specification_Base {

	@Override
	public void setSpecId(String specId) {
		checkSpecificationId(specId);
		super.setSpecId(specId);
	}

	@Override
	public void setName(String name) {
		checkSpecificationName(name);
		super.setName(name);
	}

	public Specification(String specId, String name) throws BWException {
		setSpecId(specId);
		setName(name);

		setDataModel(new DataModel());
		setConditionModel(new ConditionModel());
		setActivityModel(new ActivityModel());
		setGoalModel(new GoalModel());
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.addSpecification(this);
	}

	private void checkSpecificationId(String specId) {
		if ((specId == null) || specId.equals("")) {
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_ID, specId);
		}

		checkUniqueSpecificationId(specId);
	}

	private void checkUniqueSpecificationId(String specId) {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		boolean existsId = blendedWorkflow.getSpecificationSet().stream()
				.anyMatch(spec -> spec.getSpecId().equals(specId));
		if (existsId) {
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_ID, specId);
		}
	}

	private void checkSpecificationName(String name) {
		if ((name == null) || name.equals("")) {
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_NAME, name);
		}
	}

	@Atomic(mode = TxMode.WRITE)
	public void delete() {
		getWorkflowInstanceSet().stream().forEach(wi -> wi.delete());
		getGoalModel().delete();
		getActivityModel().delete();
		getConditionModel().delete();
		getDataModel().delete();

		setBlendedWorkflow(null);

		deleteDomainObject();
	}

	public SpecDTO getDTO() {
		SpecDTO specDTO = new SpecDTO();
		specDTO.setSpecId(getSpecId());
		specDTO.setName(getName());

		return specDTO;
	}

	public WorkflowInstance getWorkflowInstance(String name) {
		return getWorkflowInstanceSet().stream().filter(wi -> wi.getName().equals(name)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.UNKNOWN_INSTANCE, name));

	}

}