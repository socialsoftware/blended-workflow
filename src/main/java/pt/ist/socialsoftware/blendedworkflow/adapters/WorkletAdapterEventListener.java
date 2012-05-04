package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletEventListener;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;

@SuppressWarnings("serial")
public class WorkletAdapterEventListener extends WorkletEventListener{

	private static Logger log = Logger.getLogger("WorkletAdapterEventListener");

	public void WorkletEventListener() {}

	/**
	 * Receive and process ItemPreconstraint and ItemConstraintViolation events.
	 */
	@Override
	public void itemLevelExceptionEvent(WorkItemRecord wir, Element caseData, RdrNode rdrNode, RuleType ruleType) {
		log.info("ItemLevelExceptionEvent for WorkItemRecord: " + wir + " begin.");
		
//		Transaction.begin();
//		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
//		ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(wir, caseData, rdrNode, ruleType);
//		bwExecutorService.runTask(task);
//		Transaction.commit();
				
		log.info("ItemLevelExceptionEvent for WorkItemRecord: " + wir + " end.");
	}

	/**
	 * Receive and process ConstrainSucess events, for Tasks with no ItemPreconstraint or ItemConstraintViolation constraints.
	 */
	@Override
	public void constraintSuccessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info("ConstraintSuccessEvent for WorkItemRecord: " + wir + " begin.");
//		log.info("caseData");
//		log.info(JDOMUtil.elementToString(caseData));
//		Transaction.begin();
//		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
//		ProcessConstrainSucessEvent task = new ProcessConstrainSucessEvent(caseID, wir, caseData, ruleType);
//		bwExecutorService.runTask(task);
//		Transaction.commit();
		
		log.info("ConstraintSuccessEvent for WorkItemRecord: " + wir + " end.");
	}

	@Override
	public void caseLevelExceptionEvent(String caseID, Element caseData,RdrNode rdrNode, RuleType ruleType) {}

	@Override
	public void selectionEvent(WorkItemRecord wir, Map<String, String> caseMap, RdrNode rdrNode) {}

	@Override
	public void shutdown() {
		log.info("WorkletService shutdown.");
	}

}
