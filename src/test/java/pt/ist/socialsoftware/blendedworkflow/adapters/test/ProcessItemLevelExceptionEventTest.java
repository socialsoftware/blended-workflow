package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import jvstm.Transaction;

import org.jdom.Element;
import org.junit.Before;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;

import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.adapters.ProcessItemLevelExceptionEvent;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class ProcessItemLevelExceptionEventTest extends AbstractServiceTest {
	WorkItemRecord wir = new WorkItemRecord(YAWLCASE_ID, "",
			BWSPECIFICATION_NAME, "", "");
	Element conclusionFALSE = JDOMUtil
			.stringToElement("<conclusion><action>FALSE</action><target>workitem</target></conclusion>");

	@Before
	public void setUp() throws Exception {
		initializeSpecification();
		initializeBWInstance();
		wir.setTaskName("Booking");
	}

	@Test
	public void receivePreConditionEvaluationFalse() throws Exception {
		ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(
				wir, new Element("X"), new RdrNode("", conclusionFALSE,
						new Element("X")), RuleType.ItemPreconstraint);
		task.call();

		assertReceivePreConditionEvaluationFalse();
	}

	private void assertReceivePreConditionEvaluationFalse() {
		boolean committed = false;
		try {
			Transaction.begin();
			BWSpecification bwSpecification = BlendedWorkflow.getInstance()
					.getBWSpecification(BWSPECIFICATION_NAME);
			BWInstance bwInstance = bwSpecification.getBwInstances().get(0);
			List<WorkItem> workItemList = bwInstance.getWorkItems();
			assertEquals(1, workItemList.size());
			for (WorkItem workItem : workItemList) {
				assertTrue(workItem instanceof TaskWorkItem);
				TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
				assertEquals(WorkItemState.PRE_TASK, taskWorkItem.getState());
				assertEquals(wir.getTaskName(), taskWorkItem.getTask()
						.getName());
			}
			Transaction.commit();
			committed = true;
		} catch (final BlendedWorkflowException e) {
			fail(e.getMessage());
		} finally {
			if (!committed)
				Transaction.abort();
		}
	}
}
