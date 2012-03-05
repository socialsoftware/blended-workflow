package pt.ist.socialsoftware.blendedworkflow.adapters;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class WorkletAdapter {

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
	
	private void createRDRConclusionELement(String target, String action) {
		// TODO:
  	}
	
	// WorkletGateway Methods
//	private void process(String caseID, String specID, String taskID, String itemID, ELement element, RuleType ruleType) {}

//	private void addNode(String specID, String taskID, RuleType ruleType, RDRNode rdrNode) {}
	
//	public RdrNode(int id, RdrNode pParent, RdrNode pTrueChild, RdrNode pFalseChild, String pCondition, Element pConclusion, Element pCornerStone)

//	RdrConclusion(Element conc) 

}
