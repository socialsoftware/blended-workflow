import pt.ist.socialsoftware.blendedworkflow.domain;

public class CheckInWorkItemService {

String bwInstanceID;
String workItemID;
//Values values;
BWInstance bwInstance;
WorkItem workItem;

public CheckInWorkItemService (String bwInstanceID, String workItemID) {
//	, Values values) {
}
	this.bwInstanceID = bwInstanceID;
	this.workItemID = workItemID;
//	this.values = values;
}

public void execute() {

	// start transaction
	// get objects from external IDs
	this.bwInstance = getBWInstance(bwInstanceID);
	this.workItem = getWorkItem(bwInstance, workItemID);
	BWSpeci

	// check if workitem is in list of enabled workitems
	// change data instances...
	// commit transaction

}

private BWInstance getBWInstance(String bwInstanceID) {
	FROOT froot = XPTOFenixFramework.getRoot();
	for (BWSpecification bwSpecification : froot.getBWSpecification()) {
		for (BWInstance bwInstance : bwSpecification. getBWInstance()) {
			if bwInstance.getID().equals(this.bwInstanceID)
			return bwInstance;
		}
	}
}

//private getWorkItem (BWInstance bwInstance, WorkItemId workItemID) { 
//for (BWInstance bwInstance: WorkItem.getWorkItem())
//	for (BW)


}
