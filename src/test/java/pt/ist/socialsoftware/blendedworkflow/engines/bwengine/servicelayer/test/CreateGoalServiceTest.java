package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import junit.framework.JUnit4TestAdapter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class CreateGoalServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String ACTIVITY_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.yawl";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
//	private static String SECONDOPINION_ID = "Second Opinion.4";
	private static String SECONDOPINION_ID = "Second Opinion.5";
	private static String SECONDOPINION_NAME = "Second Opinion";
	private static String SECONDOPINION_DESCRIPTION = "Other doctor opinion.";
	private static String SECONDOPINION_CONDITION = "existsEntity(Episode)";
//	private static String SECONDOPINION_CONDITION = "existsEntity(Second Opinion) and existsAttribute(Second Opinion.Report.STRING.true)";
	private static String SECONDOPINION_PARENTGOAL_NAME_1 = "Write Medical Report";
	private static String SECONDOPINION_PARENTGOAL_NAME_2 = "Prescribe";
	private static String USER_ID = "BlendedWorkflow";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CreateGoalServiceTest.class);
	}

	private Mockery context = new Mockery() {
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
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				oneOf(workletAdapter).loadRdrSet(with(any(BWSpecification.class)));
				allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
				allowing(workletAdapter).notifyWorkItemPreConstraint(with(any(TaskWorkItem.class)));
				allowing(workletAdapter).addGoal(with(any(BWInstance.class)), with(any(Goal.class)));
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
				allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyPendingWorkItem(with(any(WorkItem.class)));
			}
		});

		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
		BlendedWorkflow.getInstance().setBwManager(bwManager);
		BlendedWorkflow.getInstance().setWorkListManager(workListManager);
		Transaction.commit();

		String bwSpecificationString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		String yawlSpecificationString = StringUtils.fileToString(ACTIVITY_FILENAME);
		new LoadBWSpecificationService(bwSpecificationString, yawlSpecificationString).call();

		Transaction.begin();
		BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
		Transaction.commit();

		new CreateBWInstanceService(bwSpecification.getOID(),"",USER_ID).call();
	}

	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void createGoalWithoutAffectingParentGoal() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		Goal parentGoal = goalModelInstance.getGoal(SECONDOPINION_PARENTGOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();

		new CreateGoalService(bwInstanceOID, SECONDOPINION_NAME, SECONDOPINION_DESCRIPTION, parentGoalOID, SECONDOPINION_CONDITION, USER_ID).call();

		boolean committed = false;
		try {
			Transaction.begin();

			Goal secondOpinion = goalModelInstance.getGoal(SECONDOPINION_NAME);
			WorkItem workItem = bwInstance.getWorkItem(SECONDOPINION_ID);

			assertEquals(7, goalModelInstance.getGoalsCount()); // Created 6 Goals on Load +1
			assertEquals(SECONDOPINION_NAME, secondOpinion.getName());
			assertEquals(SECONDOPINION_ID, workItem.getID());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	@Test
	public void createGoalAffectingParentGoal() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		Goal parentGoal = goalModelInstance.getGoal(SECONDOPINION_PARENTGOAL_NAME_2);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();

		new CreateGoalService(bwInstanceOID, SECONDOPINION_NAME, SECONDOPINION_DESCRIPTION,  parentGoalOID, SECONDOPINION_CONDITION, USER_ID).call();

		boolean committed = false;
		try {
			Transaction.begin();

			Goal secondOpinion = goalModelInstance.getGoal(SECONDOPINION_NAME);
			WorkItem workItem = bwInstance.getWorkItem(SECONDOPINION_ID);

			assertEquals(7, goalModelInstance.getGoalsCount()); // Created 6 Goals on Load +1
			assertEquals(SECONDOPINION_NAME, secondOpinion.getName());
			assertEquals(SECONDOPINION_ID, workItem.getID());

			assertEquals(GoalState.DEACTIVATED, parentGoal.getState());
			assertEquals(WorkItemState.GOAL_PENDING, parentGoal.getGoalWorkItem().getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
}