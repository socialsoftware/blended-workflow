package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jdom.Element;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.adapters.ProcessItemLevelExceptionEvent;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ProcessItemLevelExceptionEventTest extends AbstractServiceTest {
    TaskWorkItem bookingWorkItem = null;
    WorkItemRecord wir = new WorkItemRecord(YAWLCASE_ID, "",
            BWSPECIFICATION_NAME, "", "");
    Element conclusionFALSE = JDOMUtil.stringToElement(
            "<conclusion><action>FALSE</action><target>workitem</target></conclusion>");
    Element conclusionTRUE = JDOMUtil.stringToElement(
            "<conclusion><action>TRUE</action><target>workitem</target></conclusion>");
    Element conclusionSKIPPED = JDOMUtil.stringToElement(
            "<conclusion><action>SKIPPED</action><target>workitem</target></conclusion>");

    @Before
    public void setUp() throws Exception {
        initializeSpecification();
        initializeBWInstance();
        wir.setTaskName("Booking");

    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationFalse() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(workletGatewayClient).process(
                        with(any(WorkItemRecord.class)),
                        with(any(Element.class)), with(any(RuleType.class)),
                        with(any(String.class)));
            }
        });

        ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(
                wir, new Element("X"),
                new RdrNode("", conclusionFALSE, new Element("X")),
                RuleType.ItemPreconstraint);
        task.call();

        assertReceivePreConditionEvaluation(ActivityState.PRE_ACTIVITY);
    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationTrue() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(workletGatewayClient).process(
                        with(any(WorkItemRecord.class)),
                        with(any(Element.class)), with(any(RuleType.class)),
                        with(any(String.class)));
            }
        });

        ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(
                wir, new Element("X"),
                new RdrNode("", conclusionTRUE, new Element("X")),
                RuleType.ItemPreconstraint);
        task.call();

        assertReceivePreConditionEvaluation(ActivityState.NEW);
    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationSkipped() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(workletGatewayClient).process(
                        with(any(WorkItemRecord.class)),
                        with(any(Element.class)), with(any(RuleType.class)),
                        with(any(String.class)));
            }
        });

        ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(
                wir, new Element("X"),
                new RdrNode("", conclusionSKIPPED, new Element("X")),
                RuleType.ItemPreconstraint);
        task.call();

        assertReceivePreConditionEvaluation(ActivityState.PRE_ACTIVITY);
    }

    private void assertReceivePreConditionEvaluation(
            ActivityState workItemState) {
        boolean committed = false;
        try {
            Transaction.begin();
            BWSpecification bwSpecification = BlendedWorkflow.getInstance()
                    .getSpecByName(BWSPECIFICATION_NAME).orElse(null);
            List<BWInstance> bwInstances = new ArrayList<BWInstance>(
                    bwSpecification.getBwInstancesSet());
            BWInstance bwInstance = bwInstances.get(0);
            Set<WorkItem> workItemList = bwInstance.getWorkItemsSet();
            assertEquals(1, workItemList.size());
            for (WorkItem workItem : workItemList) {
                assertTrue(workItem instanceof TaskWorkItem);
                TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
                assertEquals(workItemState, taskWorkItem.getState());
                assertEquals(wir.getTaskName(),
                        taskWorkItem.getTask().getName());
            }
            Transaction.commit();
            committed = true;
        } catch (final BWException e) {
            fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }

    private void setUpBookingActivity() throws BWException {
        Transaction.begin();
        BWSpecification bwSpecification = BlendedWorkflow.getInstance()
                .getSpecByName(BWSPECIFICATION_NAME).orElse(null);
        List<BWInstance> bwInstances = new ArrayList<BWInstance>(
                bwSpecification.getBwInstancesSet());
        BWInstance bwInstance = bwInstances.get(0);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        TaskModel taskModel = bwInstance.getTaskModelInstance();
        BWEntity episodeType = dataModelInstance.getEntity("Episode").get();
        EntityInstance episodeOne = new EntityInstance(episodeType);
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Patient").get()
                        .getEntityInstancesSet());
        EntityInstance myPatient = entityInstances.get(0);
        new RelationInstance(
                dataModelInstance.getRelation("Patient has Episodes"),
                myPatient, episodeOne, myPatient.getNewRelationInstanceID());
        AttributeInstance episodeNumber = new AttributeInstance(
                episodeType.getAttribute("Number"), episodeOne);
        AttributeInstance episodeReserveDate = new AttributeInstance(
                episodeType.getAttribute("Reserve Date"), episodeOne);
        bookingWorkItem = new TaskWorkItem(bwInstance,
                taskModel.getTask("Booking"));
        WorkItemArgument argumentNumber = new WorkItemArgument(episodeNumber,
                "1", DataState.DEFINED);
        WorkItemArgument argumentReserveDate = new WorkItemArgument(
                episodeReserveDate, "17/07/2012", DataState.DEFINED);
        bookingWorkItem.addOutputAttributeInstances(episodeNumber);
        bookingWorkItem.addOutputAttributeInstances(episodeReserveDate);
        bookingWorkItem.addOutputWorkItemArguments(argumentNumber);
        bookingWorkItem.addOutputWorkItemArguments(argumentReserveDate);
        bookingWorkItem.setState(ActivityState.ENABLED);
        workletAdapter.associateWorkItemRecordWithTaskWorkItem(wir,
                bookingWorkItem);
        Transaction.commit();
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationFalse() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(workListManager).notifyEnabledWorkItem(bookingWorkItem);
            }
        });

        setUpBookingActivity();
        ProcessItemLevelExceptionEvent task = new ProcessItemLevelExceptionEvent(
                wir, new Element("X"),
                new RdrNode("", conclusionFALSE, new Element("X")),
                RuleType.ItemConstraintViolation);
        task.call();

        assertReceivePostConditionEvaluation(bookingWorkItem);
    }

    private void assertReceivePostConditionEvaluation(
            TaskWorkItem taskWorkItem) {
        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(ActivityState.ENABLED, taskWorkItem.getState());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }
}
