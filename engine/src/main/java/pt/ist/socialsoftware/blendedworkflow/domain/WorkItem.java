package pt.ist.socialsoftware.blendedworkflow.domain;

public abstract class WorkItem extends WorkItem_Base {

	public void delete() {
		setWorkflowInstance(null);

		getPreConditionSet().stream().forEach(dpc -> dpc.delete());
		getPostConditionSet().stream().forEach(dpc -> dpc.delete());

		deleteDomainObject();
	}

}
