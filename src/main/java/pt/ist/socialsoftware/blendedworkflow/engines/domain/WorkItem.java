package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class WorkItem extends WorkItem_Base {
	
	public enum WorkItemState {ENABLED, COMPLETED};

	public WorkItem(String workItemID) {
		setId(workItemID);
		setState(WorkItemState.ENABLED);
	}
	
	@Override
	public void setState(WorkItemState state) {
		super.setState(state);
		if (getState() == WorkItemState.COMPLETED) {
			// notify worklet
		}	
	}

}