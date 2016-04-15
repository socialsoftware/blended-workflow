package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

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

	public Specification(String specId, String name, String author, String description, String version, String UID)
			throws BWException {
		setSpecId(specId);
		setName(name);
		setAuthor(author);
		setDescription(description);
		setVersion(version);
		setUID(UID);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		setCreationDate(dateFormat.format(Calendar.getInstance().getTime()));

		setDataModel(new DataModel());
		setConditionModel(new ConditionModel());
		setActivityModel(new ActivityModel());
		setGoalModel(new GoalModel());
		setInstanceCounter(0);
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.addSpecification(this);
	}

	private void checkSpecificationId(String specId) {
		if ((specId == null) || specId.equals(""))
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_ID, specId);

		checkUniqueSpecificationId(specId);
	}

	private void checkUniqueSpecificationId(String specId) {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		boolean existsId = blendedWorkflow.getSpecificationSet().stream()
				.anyMatch(spec -> spec.getSpecId().equals(specId));
		if (existsId)
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_ID, specId);
	}

	private void checkSpecificationName(String name) {
		if ((name == null) || name.equals(""))
			throw new BWException(BWErrorType.INVALID_SPECIFICATION_NAME, name);
	}

	public int generateInstanceId() {
		setInstanceCounter(getInstanceCounter() + 1);
		return getInstanceCounter();
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

}