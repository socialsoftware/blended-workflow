package pt.ist.socialsoftware.blendedworkflow.adapters;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class WorkletAdapter {
	
	// Worklet Types
	public enum RuleType {ItemPreconstrain, ItemConstraintViolation}
	class RDRNode {}

	private Logger log;
		
	public WorkletAdapter() {
		log = Logger.getLogger("bwServicelogger");
	}

	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		//TODO:
		log.info("notifyWorkItemContraintViolation");
	}

	public void notifyWorkItemSkippedWorkItem(WorkItem workItem) {
		//TODO:
		log.info("notifyWorkItemSkippedWorkItem");
	}
	
	public void loadRDRtrees() {
		//TODO:
		log.info("loadRDRtrees");
		
		// IF TaskModel
		// Get YAWL specID, and list of task
		// addNode with empty RDRNode to create RDRTree and root RDRNode
		// For all tasks
		// Get yawlTaskID
		// Parse preCondition to String -> create RDRConclusion ELement -> create RDRNode -> add node for RuleType.ItemPreConstrain
		// Parse postCondition to String -> create RDRConclusion ELement -> create RDRNode -> add node for RuleType.ConstrainViolation
		
		// IF GoalModel
		// Get Goal specID, and list of goals
		// addNode with empty RDRNode to create RDRTree and root RDRNode
		// For all goals
		// Get goal name
		// Parse condition to String -> create RDRConclusion ELement -> add node for RuleType.ConstrainViolation
	}
	
	void createRDRConclusionELement(String target, String action) {
		// TODO:
  	}
	
	// WorkletGateway Methods
	void exceptionRaisedEvent(WorkItemRecord wir, Element wirData, RuleType ruleType) {
		// TODO:
		// WIR exists on the list?
		// IF NO -> Its a new WIR and its the pre-condition notification
			// IF TRUE
				// HandleEnabledTaskWorkItemService
			// IF SKIPPED
				// HandleTaskPreActivityService
			// IF FALSE
				// Exception
		// IF YES -> Its the pos-condition notification
			// IF TRUE
				// notifyWorkitemCompleted();
			// IF SKIPPED
				// notifyWorkitemSkipped();
			// IF FALSE
				// HandleEnabledTaskWorkItemService
	}
	
	void process(String caseID, String specID, String taskID, String itemID, Element element, RuleType ruleType) {}
	
	void addNode(String specID, String taskID, RuleType ruleType, RDRNode rdrNode) {}

	void RdrNode(int id, RDRNode pParent, RDRNode pTrueChild, RDRNode pFalseChild, String pCondition, Element pConclusion, Element pCornerStone) {}
	
	void RdrConclusion(Element conc) {} 

}
