package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jvstm.Transaction;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletEventListener;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;
import pt.ist.socialsoftware.blendedworkflow.shared.PropertiesManager;

//TODO: TEST
@SuppressWarnings("serial")
public class WorkletAdapterEventListener extends WorkletEventListener{
	
	protected Boolean yawlFlow = Boolean.parseBoolean(PropertiesManager.getProperty("yawl.Flow"));
	private static Logger log = Logger.getLogger("WorkletAdapterEventListener");
	private ConcurrentHashMap<String, Boolean> startedCases = new ConcurrentHashMap<String, Boolean>();

	public void WorkletEventListener() {
	}

	/**
	 * Receive and process ItemPreconstraint and ItemConstraintViolation events.
	 */
	@Override
	public void itemLevelExceptionEvent(WorkItemRecord wir, Element caseData, RdrNode rdrNode, RuleType ruleType) {
		log.info("ItemLevelExceptionEvent for WorkItemRecord: " + wir + " begin.");
		
		if (yawlFlow) {
			Transaction.begin();
			BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
			ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(wir, caseData, rdrNode, ruleType);
			bwExecutorService.runTask(task);
			Transaction.commit();
		}
				
		log.info("ItemLevelExceptionEvent for WorkItemRecord: " + wir + " end.");
	}

	/**
	 * Receive and process ConstrainSucess events, for Tasks with no ItemPreconstraint or ItemConstraintViolation constraints.
	 */
	@Override
	public void constraintSuccessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		log.info("ConstraintSuccessEvent for WorkItemRecord: " + wir + " begin.");

		if (yawlFlow) {
			log.debug("IN");
			Transaction.begin();
			if (ruleType.equals(RuleType.ItemPreconstraint)) {
				log.debug("ItemPreconstraint");
				// First Task with no precondition
				log.debug(!startedCases.containsKey(caseID));
				if (!startedCases.containsKey(caseID)) {
					startedCases.put(caseID, true);
					log.debug(!startedCases.containsKey(caseID));
					BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
					ProcessConstrainSucessEvent task = new ProcessConstrainSucessEvent(caseID, wir, caseData, ruleType);
					bwExecutorService.runTask(task);
				} else {
					log.info("PreCondition was False");
				}
			} else {

				BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
				ProcessConstrainSucessEvent task = new ProcessConstrainSucessEvent(caseID, wir, caseData, ruleType);
				bwExecutorService.runTask(task);
			}
			Transaction.commit();
		}
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
