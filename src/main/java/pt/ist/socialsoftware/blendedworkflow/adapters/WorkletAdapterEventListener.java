package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.Map;

import jvstm.Transaction;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletEventListener;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;

@SuppressWarnings("serial")
public class WorkletAdapterEventListener extends WorkletEventListener{
	
	private static Logger log = Logger.getLogger("bwServicelogger");
	
	public void WorkletEventListener() {}

	@Override
	public void itemLevelExceptionEvent(WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info("WorkletAdapater: " + ruleType + " for wir: " + wir + "failed with data " + JDOMUtil.elementToString(caseData));
		
		// Skipped or Defined = False	
		if (ruleType.equals(RuleType.ItemPreconstraint)) {
				Transaction.begin();
				// New workitem and precondition = false
				BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "FALSE");
				// New workitem and precondition = skipped
				// Do nothing
				Transaction.commit();
		}
	}
	
	@Override
	public void constraintSuccessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info("WorkletAdapater: " + ruleType + " for wir: " + wir + " succeeded!"); // with data " + JDOMUtil.elementToString(caseData));

		// Skipped or Defined = TRUE		
		if (ruleType.equals(RuleType.ItemPreconstraint)) {
			Transaction.begin();
			// New workitem and precondition = true
			BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "TRUE");
			// New workitem and precondition = skipped
//			BlendedWorkflow.getInstance().getWorkletAdapter().notifyNewTaskWorkItem(wir, "SKIPPED");
			Transaction.commit();
		}
	}
	
	@Override
	public void caseLevelExceptionEvent(String caseID, Element caseData, RuleType ruleType) {
		log.info("WorkletListener: " + ruleType + " for case: " + caseID + " raised.");
	}

	@Override
	public void selectionEvent(WorkItemRecord wir, Map<String, String> caseMap) {
		log.info("WorkletListener: selectionEvent for wir: " + wir + " raised.");
	}

	@Override
	public void shutdown() {
		log.info("WorkletListener: WorkletService shutdown.");
	}
	
}
