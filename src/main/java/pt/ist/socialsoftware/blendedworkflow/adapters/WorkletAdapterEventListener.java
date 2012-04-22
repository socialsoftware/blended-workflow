package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletEventListener;

/**
 * TODO: Clean and Test.
 * @author Davide Passinhas
 *
 */
@SuppressWarnings("serial")
public class WorkletAdapterEventListener extends WorkletEventListener{

	private static Logger log = Logger.getLogger("WorkletAdapterEventListener");

	public void WorkletEventListener() {}

	@Override
	public void itemLevelExceptionEvent(WorkItemRecord wir, Element caseData, RdrNode rdrNode, RuleType ruleType) {
		log.info(ruleType + " for wir: " + wir + " was "  + parseConclusion(rdrNode));
		log.info("Data"+ JDOMUtil.elementToString(wir.getDataList()));

//		Transaction.begin();
//		if (ruleType.equals(RuleType.ItemPreconstraint)) {
//			if (parseConclusion(rdrNode).equals("TRUE")) {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "TRUE");
//			} else if (parseConclusion(rdrNode).equals("FALSE")) {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "FALSE");
//			} else {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "SKIPPED");
//			}
//		} else if (ruleType.equals(RuleType.ItemConstraintViolation)) {
//			if (parseConclusion(rdrNode).equals("TRUE")) {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "TRUE");
//			} else if (parseConclusion(rdrNode).equals("FALSE")) {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "FALSE");
//			} else {
//				BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "SKIPPED");
//			}
//		}
//		Transaction.commit();		
	}

	/**
	 * ItemPreconstraint: No PreConstrain (i.e First Task)
	 */
	@Override
	public void constraintSuccessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info(ruleType + " for wir: " + wir + " succeeded!");
		
//		if (ruleType.equals(RuleType.ItemPreconstraint)) {
//			Transaction.begin();
//			BlendedWorkflow.getInstance().getWorkletAdapter().addWorkItemRecord(wir, "TRUE", true);
//			Transaction.commit();
//		} else if (ruleType.equals(RuleType.ItemConstraintViolation)) {
//			Transaction.begin();
//			BlendedWorkflow.getInstance().getWorkletAdapter().addWorkItemRecord(wir, "FALSE", false);
//			Transaction.commit();
//		}
		
		//test
//		Transaction.begin();
//		try {
//			BlendedWorkflow.getInstance().getWorkletAdapter().processWIRTrueMA(wir);
//		} catch (IOException e) {
//			log.info("fail process");
//		}
//		Transaction.commit();
	}

	@Override
	public void caseLevelExceptionEvent(String caseID, Element caseData,RdrNode rdrNode, RuleType ruleType) {}

	@Override
	public void selectionEvent(WorkItemRecord wir, Map<String, String> caseMap, RdrNode rdrNode) {}

	@Override
	public void shutdown() {
		log.info("WorkletService shutdown.");
	}
	
	/*******************************
	 * Support methods
	 *******************************/
	public String parseConclusion(RdrNode rdrNode) {
		String conclusion = JDOMUtil.elementToString(rdrNode.getConclusion());
		if (conclusion.contains("complete"))
			return "TRUE";
		if (conclusion.contains("false") || conclusion.contains("failure"))
			return "FALSE";
		else 
			return "SKIPPED";
	}

}
