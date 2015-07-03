package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jvstm.Transaction;

import org.jdom.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;

import pt.ist.socialsoftware.blendedworkflow.MockitoAbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.adapters.ProcessItemLevelExceptionEvent;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MockitoProcessItemLevelExceptionEventTest extends
        MockitoAbstractServiceTest {
    private TaskWorkItem bookingWorkItem = null;
    private final WorkItemRecord wir = new WorkItemRecord(YAWLCASE_ID, "",
            BWSPECIFICATION_NAME, "", "");
    private final Element conclusionFALSE = JDOMUtil
            .stringToElement("<conclusion><action>FALSE</action><target>workitem</target></conclusion>");
    private final Element conclusionTRUE = JDOMUtil
            .stringToElement("<conclusion><action>TRUE</action><target>workitem</target></conclusion>");
    private final Element conclusionSKIPPED = JDOMUtil
            .stringToElement("<conclusion><action>SKIPPED</action><target>workitem</target></conclusion>");

    @Before
    public void setUp() throws Exception {
        initializeSpecification();
        initializeBWInstance();
        wir.setTaskName("Booking");
    }

    private void sendPreConditionEvent(Element conclusion) throws Exception {
        new ProcessItemLevelExceptionEvent(wir, new Element("X"), new RdrNode(
                "", conclusion, new Element("X")), RuleType.ItemPreconstraint)
                .call();
        // FIXME twice for both PRE and POST verification?
        verify(workletGatewayClient).process(eq(wir), any(Element.class),
                any(RuleType.class), anyString());
    }

    private void sendPostConditionEvent(Element conclusion) throws Exception {
        new ProcessItemLevelExceptionEvent(wir, new Element("X"), new RdrNode(
                "", conclusion, new Element("X")),
                RuleType.ItemConstraintViolation).call();
    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationFalse() throws Exception {
        sendPreConditionEvent(conclusionFALSE);

        // assertReceivePreConditionEvaluation(ActivityState.PRE_FALSE);
        assertReceivePreConditionEvaluation(ActivityState.PRE_ACTIVITY);
    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationTrue() throws Exception {
        sendPreConditionEvent(conclusionTRUE);

        // assertReceivePreConditionEvaluation(ActivityState.NEW);
        assertReceivePreConditionEvaluation(ActivityState.ENABLED);
    }

    @Ignore
    @Test
    public void receivePreConditionEvaluationSkipped() throws Exception {
        sendPreConditionEvent(conclusionSKIPPED);

        // assertReceivePreConditionEvaluation(ActivityState.PRE_TASK);
        assertReceivePreConditionEvaluation(ActivityState.PRE_ACTIVITY);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationFalseEnabledState()
            throws Exception {
        receivePostConditionEvaluation(conclusionFALSE, ActivityState.ENABLED,
                ActivityState.ENABLED);
        verify(workListManager).notifyEnabledWorkItem(bookingWorkItem);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationFalsePreActState()
            throws Exception {
        receivePostConditionEvaluation(conclusionFALSE,
                ActivityState.PRE_ACTIVITY, ActivityState.PRE_ACTIVITY);
        verify(workListManager).notifyEnabledWorkItem(bookingWorkItem);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationTrueEnabledState()
            throws Exception {
        receivePostConditionEvaluation(conclusionTRUE, ActivityState.ENABLED,
                ActivityState.COMPLETED);
        verify(workListManager).notifyCompletedWorkItem(bookingWorkItem);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationTruePreActState()
            throws Exception {
        receivePostConditionEvaluation(conclusionTRUE,
                ActivityState.PRE_ACTIVITY, ActivityState.COMPLETED);
        verify(workListManager).notifyCompletedWorkItem(bookingWorkItem);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationSkippedEnabledState()
            throws Exception {
        receivePostConditionEvaluation(conclusionSKIPPED,
                ActivityState.ENABLED, ActivityState.SKIPPED);
        verify(workListManager).notifyCompletedWorkItem(bookingWorkItem);
    }

    @Ignore
    @Test
    public void receivePostConditionEvaluationSkippedPreActState()
            throws Exception {
        receivePostConditionEvaluation(conclusionSKIPPED,
                ActivityState.PRE_ACTIVITY, ActivityState.SKIPPED);
        verify(workListManager).notifyCompletedWorkItem(bookingWorkItem);
    }

    private void setUpBookingActivity(ActivityState state)
            throws BWException {
        Transaction.begin();

        // final BWSpecification bwSpecification =
        // BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
        // final BWInstance bwInstance =
        // bwSpecification.getBwInstances().get(0);
        final DataModelInstance dataModelInstance = bwInstance
                .getDataModelInstance();
        final TaskModel taskModel = bwInstance.getTaskModelInstance();

        final Entity episodeType = dataModelInstance.getEntity("Episode");
        // final EntityInstance episodeOne = new
        // EntityInstance(dataModelInstance, episodeType);
        final EntityInstance episodeOne = new EntityInstance(episodeType);
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Patient").getEntityInstancesSet());
        final EntityInstance myPatient = entityInstances.get(0);
        new RelationInstance(
                dataModelInstance.getRelation("Patient has Episodes"),
                myPatient, episodeOne, myPatient.getNewRelationInstanceID());

        final AttributeInstance episodeNumber = new AttributeInstance(
                episodeType.getAttribute("Number"), episodeOne);
        final AttributeInstance episodeReserveDate = new AttributeInstance(
                episodeType.getAttribute("Reserve Date"), episodeOne);
        bookingWorkItem = new TaskWorkItem(bwInstance,
                taskModel.getTask("Booking"));
        final WorkItemArgument argumentNumber = new WorkItemArgument(
                episodeNumber, "1", DataState.DEFINED);
        final WorkItemArgument argumentReserveDate = new WorkItemArgument(
                episodeReserveDate, "17/07/2012", DataState.DEFINED);
        bookingWorkItem.addOutputAttributeInstances(episodeNumber);
        bookingWorkItem.addOutputAttributeInstances(episodeReserveDate);
        bookingWorkItem.addOutputWorkItemArguments(argumentNumber);
        bookingWorkItem.addOutputWorkItemArguments(argumentReserveDate);
        bookingWorkItem.setState(state);
        workletAdapter.associateWorkItemRecordWithTaskWorkItem(wir,
                bookingWorkItem);

        Transaction.commit();
    }

    private void receivePostConditionEvaluation(Element conclusion,
            ActivityState initialState, ActivityState expectedState)
            throws Exception {
        setUpBookingActivity(initialState);
        sendPostConditionEvent(conclusion);

        assertReceivePostConditionEvaluation(bookingWorkItem, expectedState);
    }

    private void assertReceivePreConditionEvaluation(ActivityState workItemState) {
        boolean committed = false;
        try {
            Transaction.begin();

            // final BWSpecification bwSpecification =
            // BlendedWorkflow.getInstance().getBWSpecification(
            // BWSPECIFICATION_NAME);
            // final BWInstance bwInstance =
            // bwSpecification.getBwInstances().get(0);

            final Set<WorkItem> workItemList = bwInstance.getWorkItems();
            assertEquals(1, workItemList.size());
            for (final WorkItem workItem : workItemList) {
                assertTrue(workItem instanceof TaskWorkItem);
                final TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
                assertEquals(workItemState, taskWorkItem.getState());
                assertEquals(wir.getTaskName(), taskWorkItem.getTask()
                        .getName());
            }

            Transaction.commit();
            committed = true;
            // } catch (final BlendedWorkflowException e) {
            // fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }

    private void assertReceivePostConditionEvaluation(
            TaskWorkItem taskWorkItem, ActivityState expectedState) {
        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(expectedState, taskWorkItem.getState());

            Transaction.commit();
            committed = true;
            // } catch (final BlendedWorkflowException e) {
            // fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }

}
