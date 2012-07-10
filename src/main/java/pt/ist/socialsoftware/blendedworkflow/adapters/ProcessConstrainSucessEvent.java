package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class ProcessConstrainSucessEvent implements Callable<String> {

	private static Logger log = Logger.getLogger("ConstrainSucessEventTask");

	private WorkItemRecord wir;
	private RuleType ruleType;

	public ProcessConstrainSucessEvent(String caseID, WorkItemRecord wir, Element caseData, RuleType ruleType) {
		this.wir = wir;
		this.ruleType = ruleType;
	}

	@Override
	public String call() throws Exception {
		log.info("Start for WorkItemRecord: " + wir);
	
		if (ruleType.equals(RuleType.ItemPreconstraint)) {
			Transaction.begin();
			BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemPreConditionResult(wir, "TRUE");
			Transaction.commit();
		} else if (ruleType.equals(RuleType.ItemConstraintViolation)) {
			Transaction.begin();
			BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemPostConditionResult(wir, null, "FALSE");
			Transaction.commit();
		} else {
			throw new BlendedWorkflowException(BlendedWorkflowError.UMANAGED_RULE_TYPE);
		
		}
		
		log.info("End for WorkItemRecord: " + wir);
		return "ConstrainSucessEventTask:Sucess";
	}

}
