package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.ArrayList;

import jvstm.Atomic;

import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

public class HandleTaskPreActivityService {

	private BWInstance bwInstance;
	private String taskName;

	public HandleTaskPreActivityService(BWInstance bwInstance, String taskName) {
		this.bwInstance = bwInstance;
		this.taskName = taskName;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		
		// Create TaskWorkitem
		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
		Task task = taskModelInstance.getTask(this.taskName);
		TaskWorkItem taskWorkItem = new TaskWorkItem(this.bwInstance, task);
		taskWorkItem.setState(WorkItemState.PRE_TASK);
		
		// Create Task PreConstrain Skipped AttributeInstances
		ArrayList<AttributeInstance> skippedAttributeInstance = new ArrayList<AttributeInstance>();
		for (AttributeInstance attributeInstance : taskWorkItem.getPreConstraintAttributeInstances()) {
			if (attributeInstance.getState() == DataState.SKIPPED) {
				skippedAttributeInstance.add(attributeInstance);
			}
		}
		
		// Create XML with pre-activity + TaskWorkItem
		
		// FIXME: must receive XML
		WorkListManager.getInstance().notifyEnabledWorkItem(taskWorkItem);
		
	}


	
}
