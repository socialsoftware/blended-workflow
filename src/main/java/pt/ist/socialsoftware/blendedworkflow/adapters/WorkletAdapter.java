package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class WorkletAdapter {

	// FIXME Worklet Types
	public enum RuleType {ItemPreconstrain, ItemConstraintViolation}
	class RDRNode {}

	// FIXME not persitent
	private ArrayList<WorkItemRecord> yawlEnabledWorkItems = new ArrayList<WorkItemRecord>();

	private Logger log;

	public WorkletAdapter() {
		log = Logger.getLogger("bwServicelogger");
	}

	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		//TODO:
		log.info("notifyWorkItemContraintViolation");
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
	void exceptionRaisedEvent(WorkItemRecord wir, Element wirData, RuleType ruleType) throws BlendedWorkflowException {
		BWInstance bwInstance = BlendedWorkflow.getInstance().getYAWLBWInstance(wir.getCaseID());
		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

		String taskName = wir.getTaskName();
		Task task = taskModelInstance.getTask(taskName);
		String result = ""; // TODO get evaluation result
		TaskWorkItem taskWorkItem = null;
		if (!yawlEnabledWorkItems.contains(wir)){ // PreConstrain

			if (result.equals("TRUE")) {
				taskWorkItem = new TaskWorkItem(bwInstance, task);
				taskWorkItem.notifyPreConstrain();
			}
			else if (result.equals("SKIPPED")) {
				taskWorkItem = new TaskWorkItem(bwInstance, task);
				taskWorkItem.notifyPreTask();
			} else if (result.equals("FALSE")) {
				throw new BlendedWorkflowException(BlendedWorkflowError.FALSE_PRE_CONSTRAIN);
			}
		}
		else { // PostConstrain
			taskWorkItem = taskModelInstance.getTask(taskName).getTaskWorkItem();
			
			if (result.equals("TRUE")) {
				taskWorkItem.notifyCompleted();
			}
			else if (result.equals("SKIPPED")) {
				taskWorkItem.notifySkipped();

			} else if (result.equals("FALSE")) {
				taskWorkItem.notifyEnabled();
			}
		}
	}
	
	void process(String caseID, String specID, String taskID, String itemID, Element element, RuleType ruleType) {}

	void addNode(String specID, String taskID, RuleType ruleType, RDRNode rdrNode) {}

	void RdrNode(int id, RDRNode pParent, RDRNode pTrueChild, RDRNode pFalseChild, String pCondition, Element pConclusion, Element pCornerStone) {}

	void RdrConclusion(Element conc) {} 
}
