package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.JUnit4TestAdapter;
import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateNewGoalService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class CreateNewGoalServiceTest {

    private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";

    private static String YAWLCASE_ID = "yawlCaseID";
    private static String BWSPECIFICATION_NAME = "Medical Appointment";
    private static String BWINSTANCE_ID = "Medical Appointment.1";

    private static String NEWGOAL_NAME = "Second Opinion";
    private static String NEWGOAL_DESCRIPTION = "Medical Episode Second Opinion";
    private static String NEWGOAL_SUCESS_CONDITION = "existsEntity(Episode)";
    private static String NEWGOAL_ACTIVATE_CONDITION = "true";
    private static String NEWGOAL_PARENTGOAL_NAME = "Create Episode";
    private static String ENTITY_NAME = "Episode";

    // private static String SECONDOPINION_ID = "Second Opinion.4";
    // private static String SECONDOPINION_ID = "Second Opinion.5";
    // private static String SECONDOPINION_NAME = "Second Opinion";
    // private static String SECONDOPINION_DESCRIPTION =
    // "Other doctor opinion.";
    // private static String SECONDOPINION_CONDITION = "existsEntity(Episode)";
    // private static String SECONDOPINION_CONDITION =
    // "existsEntity(Second Opinion) and existsAttribute(Second
    // Opinion.Report.STRING.true)";
    // private static String SECONDOPINION_PARENTGOAL_NAME_1 =
    // "Write Medical Report";
    // private static String SECONDOPINION_PARENTGOAL_NAME_2 = "Prescribe";
    private static String USER_ID = "BlendedWorkflow";

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CreateNewGoalServiceTest.class);
    }

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }
    };

    private YAWLAdapter yawlAdapter = null;
    private WorkletAdapter workletAdapter = null;
    private BWManager bwManager = null;
    private WorkListManager workListManager = null;

    @Before
    public void setUp() throws Exception {
        Bootstrap.initTestDB();

        yawlAdapter = context.mock(YAWLAdapter.class);
        workletAdapter = context.mock(WorkletAdapter.class);
        bwManager = context.mock(BWManager.class);
        workListManager = context.mock(WorkListManager.class);
        context.checking(new Expectations() {
            {
                oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
                oneOf(yawlAdapter).launchCase(with(any(String.class)));
                will(returnValue(YAWLCASE_ID));
                oneOf(workletAdapter)
                        .loadRdrSet(with(any(BWSpecification.class)));
                allowing(workletAdapter).requestWorkItemPostConditionEvaluation(
                        with(any(TaskWorkItem.class)));
                allowing(workletAdapter).requestWorkItemPreConditionEvaluation(
                        with(any(TaskWorkItem.class)));
                oneOf(bwManager)
                        .notifyCreatedBWInstance(with(any(BWInstance.class)));
                oneOf(bwManager).notifyLoadedBWSpecification(
                        with(any(BWSpecification.class)));
                allowing(workListManager)
                        .notifyEnabledWorkItem(with(any(WorkItem.class)));
            }
        });

        Transaction.begin();
        BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
        BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
        BlendedWorkflow.getInstance().setBwManager(bwManager);
        BlendedWorkflow.getInstance().setWorkListManager(workListManager);
        Transaction.commit();

        String bwSpecificationString = StringUtils
                .fileToString(BWSPECIFICATION_FILENAME);
        new LoadBWSpecificationService(bwSpecificationString).call();

        Transaction.begin();
        BWSpecification bwSpecification = BlendedWorkflow.getInstance()
                .getSpecById(BWSPECIFICATION_NAME).orElse(null);
        Transaction.commit();

        new CreateBWInstanceService(bwSpecification.getExternalId(), "",
                USER_ID).call();
    }

    @After
    public void tearDown() {
        Bootstrap.clean();
    }

    @Ignore
    @Test
    public void createGoalWithoutAffectingParentGoal() throws Exception {
        Transaction.begin();
        BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
        String bwInstanceOID = bwInstance.getExternalId();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        Goal parentGoal = goalModelInstance.getGoal(NEWGOAL_PARENTGOAL_NAME);
        String parentGoalOID = parentGoal.getExternalId();
        BWEntity entity = bwInstance.getDataModelInstance()
                .getEntity(ENTITY_NAME).get();
        String entityOID = entity.getExternalId();

        ArrayList<String> activateConditions = new ArrayList<String>();
        activateConditions.add(NEWGOAL_ACTIVATE_CONDITION);
        Transaction.commit();

        new CreateNewGoalService(bwInstanceOID, NEWGOAL_NAME,
                NEWGOAL_DESCRIPTION, parentGoalOID, NEWGOAL_SUCESS_CONDITION,
                activateConditions, entityOID, USER_ID).call();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(14, goalModelInstance.getGoalSet().size());
            Goal newGoal = goalModelInstance.getGoal(NEWGOAL_NAME);
            assertEquals(NEWGOAL_NAME, newGoal.getName());
            assertEquals(NEWGOAL_DESCRIPTION, newGoal.getDescription());
            assertEquals(NEWGOAL_SUCESS_CONDITION,
                    newGoal.getSuccessConditionSet().toString());
            assertEquals(1, newGoal.getActivationConditionSet().size());
            assertEquals(NEWGOAL_PARENTGOAL_NAME,
                    newGoal.getParentGoal().getName());
            assertEquals(ENTITY_NAME, newGoal.getEntityContext().getName());

            Transaction.commit();
            committed = true;
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

    /*
     * 
     * @Test public void createGoalWithoutAffectingParentGoal() throws Exception
     * { Transaction.begin(); BlendedWorkflow blendedWorkflow =
     * BlendedWorkflow.getInstance(); BWInstance bwInstance =
     * blendedWorkflow.getBWInstance(BWINSTANCE_ID); String bwInstanceOID =
     * bwInstance.getOID(); GoalModelInstance goalModelInstance =
     * bwInstance.getGoalModelInstance(); AchieveGoal parentGoal =
     * goalModelInstance.getGoal(SECONDOPINION_PARENTGOAL_NAME_1); String
     * parentGoalOID = parentGoal.getOID(); Transaction.commit();
     * 
     * new CreateNewGoalService(bwInstanceOID, SECONDOPINION_NAME,
     * SECONDOPINION_DESCRIPTION, parentGoalOID, SECONDOPINION_CONDITION,
     * USER_ID).call();
     * 
     * boolean committed = false; try { Transaction.begin();
     * 
     * AchieveGoal secondOpinion =
     * goalModelInstance.getGoal(SECONDOPINION_NAME); WorkItem workItem =
     * bwInstance.getWorkItem(SECONDOPINION_ID);
     * 
     * assertEquals(7, goalModelInstance.getAchieveGoalsCount()); // Created 6
     * Goals on Load +1 assertEquals(SECONDOPINION_NAME,
     * secondOpinion.getName()); assertEquals(SECONDOPINION_ID,
     * workItem.getID());
     * 
     * Transaction.commit(); committed = true; } finally { if (!committed) {
     * Transaction.abort(); } } }
     * 
     * @Test public void createGoalAffectingParentGoal() throws Exception {
     * Transaction.begin(); BlendedWorkflow blendedWorkflow =
     * BlendedWorkflow.getInstance(); BWInstance bwInstance =
     * blendedWorkflow.getBWInstance(BWINSTANCE_ID); String bwInstanceOID =
     * bwInstance.getOID(); GoalModelInstance goalModelInstance =
     * bwInstance.getGoalModelInstance(); AchieveGoal parentGoal =
     * goalModelInstance.getGoal(SECONDOPINION_PARENTGOAL_NAME_2); String
     * parentGoalOID = parentGoal.getOID(); Transaction.commit();
     * 
     * new CreateNewGoalService(bwInstanceOID, SECONDOPINION_NAME,
     * SECONDOPINION_DESCRIPTION, parentGoalOID, SECONDOPINION_CONDITION,
     * USER_ID).call();
     * 
     * boolean committed = false; try { Transaction.begin();
     * 
     * AchieveGoal secondOpinion =
     * goalModelInstance.getGoal(SECONDOPINION_NAME); WorkItem workItem =
     * bwInstance.getWorkItem(SECONDOPINION_ID);
     * 
     * assertEquals(7, goalModelInstance.getAchieveGoalsCount()); // Created 6
     * Goals on Load +1 assertEquals(SECONDOPINION_NAME,
     * secondOpinion.getName()); assertEquals(SECONDOPINION_ID,
     * workItem.getID());
     * 
     * assertEquals(GoalState.DEACTIVATED, parentGoal.getState()); //
     * assertEquals(WorkItemState.GOAL_PENDING,
     * parentGoal.getGoalWorkItem().getState()); //FIMXE:
     * 
     * Transaction.commit(); committed = true; } finally { if (!committed) {
     * Transaction.abort(); } } }
     */
}