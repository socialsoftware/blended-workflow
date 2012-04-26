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
 * TODO: Test.
 * @author Davide Passinhas
 *
 */
@SuppressWarnings("serial")
public class WorkletAdapterEventListener extends WorkletEventListener{

	private static Logger log = Logger.getLogger("WorkletAdapterEventListener");

	public void WorkletEventListener() {}

	@Override
	public void itemLevelExceptionEvent(WorkItemRecord wir, Element caseData, RdrNode rdrNode, RuleType ruleType) {
		log.info("WAEL:itemLevelExceptionEvent:BEGIN" + Thread.currentThread().getId());
//		log.info(ruleType + " for wir: " + wir + " was "  + parseConclusion(rdrNode));
//		log.info("Data: "+ JDOMUtil.elementToString(caseData));
//		
//		ThreadPool.getThreadExecutor().execute(Thread.currentThread());
//		
//		Transaction.begin();
//		Boolean isworkItemCompleted = BlendedWorkflow.getInstance().getWorkletAdapter().isWorkItemCompleted(wir);
//		if (!isworkItemCompleted) {
//			if (ruleType.equals(RuleType.ItemPreconstraint)) {
//				if (parseConclusion(rdrNode).equals("TRUE")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().addNewWorkItemRecord(wir, "TRUE", true);
//				} else if (parseConclusion(rdrNode).equals("FALSE")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().addNewWorkItemRecord(wir, "FALSE", true);
//				} else if (parseConclusion(rdrNode).equals("SKIPPED")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().addNewWorkItemRecord(wir, "SKIPPED", true);
//				} else {
//					log.error(ruleType + " for wir: " + wir + " failed.");
//				}
////				BlendedWorkflow.getInstance().getWorkletAdapter().createNewTaskWorkItems();				
////				BlendedWorkflow.getInstance().getWorkletAdapter().processNewTaskWorkItems();
//			} else if (ruleType.equals(RuleType.ItemConstraintViolation)) {
//				if (parseConclusion(rdrNode).equals("TRUE")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "TRUE");
//				} else if (parseConclusion(rdrNode).equals("FALSE")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "FALSE");
//				} else if (parseConclusion(rdrNode).equals("SKIPPED")) {
//					BlendedWorkflow.getInstance().getWorkletAdapter().notifyConstraintViolationResult(wir, null, "SKIPPED");
//				} else {
//					log.error(ruleType + " for wir: " + wir + " failed.");
//				}
//			} else {
//				log.info(ruleType + " for wir: " + wir + " was "  + parseConclusion(rdrNode) + " do nothing...");
//			}
//		}
//		else {
//			log.info(ruleType + " for wir: " + wir + " was "  + parseConclusion(rdrNode) + " already completed...");
//		}
//		log.info("WAEL:itemLevelExceptionEvent:END");
//		Transaction.commit();		
	}

	/**
	 * ItemPreconstraint: No PreConstrain (i.e First Task only)
	 */
	@Override
	public void constraintSuccessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info(ruleType + " for wir: " + wir + " succeeded!");
//		ThreadPool.getThreadExecutor().execute(Thread.currentThread());
//		log.info("Data: "+ JDOMUtil.elementToString(caseData));
//
//		if (ruleType.equals(RuleType.ItemPreconstraint)) {
//			Transaction.begin();
//			BlendedWorkflow.getInstance().getWorkletAdapter().addNewWorkItemRecord(wir, "TRUE", true);
//			Transaction.commit();
//		} else {
//			log.error(ruleType + " for wir: " + wir + " succeeded.");
//		}
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
		if (conclusion.contains("complete") || conclusion.contains("TRUE"))
			return "TRUE";
		if (conclusion.contains("FALSE"))
			return "FALSE";
		else if (conclusion.contains("SKIPPED"))
			return "SKIPPED";
		else
			return "FAIL";
	}

}
