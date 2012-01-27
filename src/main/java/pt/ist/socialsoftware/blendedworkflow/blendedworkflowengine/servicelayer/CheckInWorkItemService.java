import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.*;

public class CheckInWorkItemService {

String bwInstanceID;
String workItemID;
//Values values;
BWInstance bwInstance;
WorkItem workItem;

public CheckInWorkItemService (String bwInstanceID, String workItemID) {
//	, Values values) {
	this.bwInstanceID = bwInstanceID;
	this.workItemID = workItemID;
//	this.values = values;
}

@Atomic
public void execute() {

	// get objects from external IDs
	BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
	this.bwInstance = blendedWorkflow. getBWInstance(bwInstanceID);
	
//	this.workItem = getWorkItem(bwInstance, workItemID);
	
	// check if workitem is in list of enabled workitems
	// change data instances...
	// commit transaction

}

public BWInstance getBwInstance() {
	return this.bwInstance;
}


//private getWorkItem (BWInstance bwInstance, WorkItemId workItemID) { 
//for (BWInstance bwInstance: WorkItem.getWorkItem())
//	for (BW)


}
