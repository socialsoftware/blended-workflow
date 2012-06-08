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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.ManageGoalConditionService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class ManageGoalConditionServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	
	private static String GOAL_NAME_1 = "Add Patient";
	private static String GOALWORKITEM_ID_1 = "Add Patient.2";
	private static String USER_ID = "BlendedWorkflow";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ManageGoalConditionServiceTest.class);
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
				allowing(workletAdapter).addGoal(with(any(BWInstance.class)), with(any(AchieveGoal.class)));
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
		new LoadBWSpecificationService(bwSpecificationString).call();

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
	public void createOneGoalInstance() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();

		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();
		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
		
		// Verify that all workItems have 1 ActivateCondition
		boolean committed = false;
		try {
			Transaction.begin();
			assertEquals(5, bwInstance.getWorkItemsCount());
			for (WorkItem workItem : bwInstance.getWorkItems()) {
				if (workItem.getClass().equals(GoalWorkItem.class))
					assertEquals(1, ((GoalWorkItem) workItem).getActivateConditionsCount());
			}
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
		
		// Remove the first WorkItem ActivateCondition
		Transaction.begin();
		GoalWorkItem firstGoalWorkItem = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_1);
		Condition firstActivateCondition = firstGoalWorkItem.getActivateConditions().get(0); //FIRST
		long firstGoalWorkItemOID = firstGoalWorkItem.getOID();
		long firstActivateConditionOID = firstActivateCondition.getOID();
		Transaction.commit();
		new ManageGoalConditionService(firstGoalWorkItemOID, firstActivateConditionOID).call();

		// Verify that only the first WorkItem has no ActivateConditions
		committed = false;
		try {
			Transaction.begin();
			for (WorkItem workItem : bwInstance.getWorkItems()) {
				if (workItem.getClass().equals(GoalWorkItem.class)) {
					if (workItem.getOID() == firstGoalWorkItemOID) {
						assertEquals(0, ((GoalWorkItem) workItem).getActivateConditionsCount());
					} else {
						assertEquals(1, ((GoalWorkItem) workItem).getActivateConditionsCount());
					}
				}
			}

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
}