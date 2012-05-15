package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.JUnit4TestAdapter;

import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.EnableGoalWorkItemsService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class CheckInWorkItemServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String ACTIVITY_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.yawl";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";

	private static String GOAL_NAME_1 = "Add Patient";
	private static String GOALWORKITEM_ID_1 = "Add Patient.1";
	private static String GOALWORKITEM_ID_2 = "Add Gender.2";
	private static String GOALWORKITEM_ID_3 = "Add Address.3";

	private static String ENTITY_1_NAME = "Patient";
	private static String ENTITYINSTANCE_1_ID = "Patient.1";

	private static String ENTITYINSTANCE_1_ATT_1_ID = "Name.1";
	private static String ENTITYINSTANCE_1_ATT_2_ID = "Gender.2";
	private static String ENTITYINSTANCE_1_ATT_3_ID = "Address.3";
	private static String ENTITYINSTANCE_1_ATT_1_VALUE = "John";
	private static String ENTITYINSTANCE_1_ATT_2_VALUE = "male";
	private static String ENTITYINSTANCE_1_ATT_3_VALUE = "Lisbon";

	private static String UNDEFINED_VALUE = "$UNDEFINED$";

	private static String USER_ID = "BlendedWorkflow";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CheckInWorkItemServiceTest.class);
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
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
				allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyCompletedWorkItem(with(any(WorkItem.class)));
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
	public void checkInOneWorkItem() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();

		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
		new EnableGoalWorkItemsService(bwInstanceOID).call();

		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();

		new CheckInWorkItemService(workItem3OID).call();

		boolean committed = false;
		try {
			Transaction.begin();
			WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
			WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
			AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
			AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
			attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
			
			//WorkItem1 - AddPatient
			assertEquals(WorkItemState.GOAL_PENDING, workItem1.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance1.getState());
			
			
			//WorkItem2 - AddGender
			assertEquals(WorkItemState.ENABLED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance2.getState());
			
			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.COMPLETED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.DEFINED, attributeInstance3.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	@Test
	public void checkInTwoWorkItems() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();

		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
		new EnableGoalWorkItemsService(bwInstanceOID).call();

		// WorkItem3 - AddAdress
		Transaction.begin();
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();
		new CheckInWorkItemService(workItem3OID).call();

		//WorkItem2 - AddGender
		Transaction.begin();
		entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
		long workItem2OID = workItem2.getOID();
		AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
		for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance2.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_2_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();
		new CheckInWorkItemService(workItem2OID).call();

		boolean committed = false;
		try {
			Transaction.begin();
			WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
			AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
			attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
			attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
			
			//WorkItem1 - AddPatient
			assertEquals(WorkItemState.ENABLED, workItem1.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance1.getState());

			// WorkItem2 - AddGender
			assertEquals(WorkItemState.COMPLETED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_2_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_2_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.DEFINED, attributeInstance2.getState());

			// WorkItem3 - AddAdress
			assertEquals(WorkItemState.COMPLETED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.DEFINED, attributeInstance3.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	@Test
	public void checkInAllWorkItems() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		Transaction.commit();
		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
		new EnableGoalWorkItemsService(bwInstanceOID).call();

		//WorkItem3 - AddAdress
		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();
		new CheckInWorkItemService(workItem3OID).call();

		//WorkItem2 - AddGender
		Transaction.begin();
		WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
		long workItem2OID = workItem2.getOID();
		entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
		for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance2.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_2_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();
		new CheckInWorkItemService(workItem2OID).call();

		//WorkItem1 - AddPatient
		Transaction.begin();
		WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
		long workItem1OID = workItem1.getOID();
		entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
		for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance1.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_1_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		Transaction.commit();
		new CheckInWorkItemService(workItem1OID).call();

		boolean committed = false;
		try {
			Transaction.begin();

			//WorkItem1 - AddPatient
			assertEquals(WorkItemState.COMPLETED, workItem1.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_1_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_1_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.DEFINED, attributeInstance1.getState());

			//WorkItem2 - AddGender
			assertEquals(WorkItemState.COMPLETED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_2_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_2_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.DEFINED, attributeInstance2.getState());

			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.COMPLETED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, workItemArgument.getValue());
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.DEFINED, attributeInstance3.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}


}