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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.EnableGoalWorkItemsService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class EnableGoalWorkItemsServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	
	private static String GOAL_NAME_1 = "Add Patient";
	private static String GOALWORKITEM_ID_1 = "Add Patient.2";
	private static String GOALWORKITEM_ID_2 = "Add Gender.3";
	private static String GOALWORKITEM_ID_3 = "Add Address.4";
	private static String GOALWORKITEM_ID_4 = "Add PhoneNumber.5";
	
	private static String ENTITY_1_NAME = "Patient";
	private static String ENTITYINSTANCE_1_ID = "Patient.1";

	private static String USER_ID = "BlendedWorkflow";
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EnableGoalWorkItemsServiceTest.class);
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
	public void enableGoalWorkItemsWithAllActivateConditions() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();

		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		long entityInstance1OID = entityInstance1.getOID();
		Transaction.commit();
		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, entityInstance1OID).call();
		new EnableGoalWorkItemsService(bwInstanceOID).call();
		
		boolean committed = false;
		try {
			Transaction.begin();
			assertEquals(5, bwInstance.getWorkItemsCount());
			
			GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_1);
			GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_2);
			GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_3);
			GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_4);

			// GoalWorkItem1
			assertEquals(WorkItemState.GOAL_PENDING, goalWorkItem1.getState());
			assertEquals(2, goalWorkItem1.getInputAttributeInstancesCount());
			assertEquals(2, goalWorkItem1.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem1.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem1.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem2
			assertEquals(WorkItemState.ENABLED, goalWorkItem2.getState());
			assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem2.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem2.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem3
			assertEquals(WorkItemState.ENABLED, goalWorkItem3.getState());
			assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem3.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem3.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem4
			assertEquals(WorkItemState.ENABLED, goalWorkItem4.getState());
			assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem4.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem4.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
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