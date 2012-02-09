package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

public class TaskWorkItem extends TaskWorkItem_Base {
    
    public  TaskWorkItem() {
        super();
    }
    
	public TaskWorkItem(String workItemID) {
		setId(workItemID);
		setState(WorkItemState.ENABLED);
	}

	public String getElementId() {
		return "getTaskInstance().getId()";
	}
	
	
	
    
}
