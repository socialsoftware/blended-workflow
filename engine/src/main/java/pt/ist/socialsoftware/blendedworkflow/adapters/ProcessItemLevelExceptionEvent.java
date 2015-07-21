package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.util.concurrent.Callable;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;

public class ProcessItemLevelExceptionEvent implements Callable<String> {

    private static Logger log = LoggerFactory
            .getLogger("ItemLevelExceptionEvent");

    private final WorkItemRecord wir;
    // private Element caseData;
    private final RdrNode rdrNode;
    private final RuleType ruleType;

    public ProcessItemLevelExceptionEvent(WorkItemRecord wir, Element caseData,
            RdrNode rdrNode, RuleType ruleType) {
        this.wir = wir;
        // this.caseData = caseData;
        this.rdrNode = rdrNode;
        this.ruleType = ruleType;
    }

    @Atomic(mode = TxMode.WRITE)
    @Override
    public String call() throws Exception {
        log.debug("-----------BEGIN----------------->"
                + parseConclusion(rdrNode) + " " + ruleType + " for " + wir);
        Thread.sleep(4000); // FIXME:??

        if (ruleType.equals(RuleType.ItemPreconstraint)) {
            if (parseConclusion(rdrNode).equals("TRUE")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPreConditionResult(wir, "TRUE");
            } else if (parseConclusion(rdrNode).equals("FALSE")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPreConditionResult(wir, "FALSE");
            } else if (parseConclusion(rdrNode).equals("SKIPPED")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPreConditionResult(wir, "SKIPPED");
            } else {
                log.error(ruleType + " for wir: " + wir + " failed.");
            }
        } else if (ruleType.equals(RuleType.ItemConstraintViolation)) {
            if (parseConclusion(rdrNode).equals("TRUE")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPostConditionResult(wir, "TRUE");
            } else if (parseConclusion(rdrNode).equals("FALSE")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPostConditionResult(wir, "FALSE");
            } else if (parseConclusion(rdrNode).equals("SKIPPED")) {
                BlendedWorkflow.getInstance().getWorkletAdapter()
                        .notifyWorkItemPostConditionResult(wir, "SKIPPED");
            } else {
                log.error(ruleType + " for wir: " + wir + " failed.");
            }
        }

        log.debug("---------END-------------->" + parseConclusion(rdrNode) + " "
                + ruleType + " for " + wir);
        return "ItemLevelExceptionEventTask:Sucess";
    }

    /**
     * Parse the RdrConclusion.
     * 
     * @param rdrNode
     *            the enabled RdrNode.
     * @return a string with the result.
     */
    public String parseConclusion(RdrNode rdrNode) {
        String conclusion = JDOMUtil.elementToString(rdrNode.getConclusion());
        if (conclusion.contains("SKIPPED"))
            return "SKIPPED";
        else if (conclusion.contains("FALSE"))
            return "FALSE";
        else if (conclusion.contains("complete") || conclusion.contains("TRUE"))
            return "TRUE";
        else
            log.error("Invalid Conclusion!");
        return "FAIL";
    }

}
