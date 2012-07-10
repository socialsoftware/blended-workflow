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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.EnableGoalWorkItemsService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class SkipWorkItemServiceTest {

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

	private static String ENTITYINSTANCE_1_ATT_1_ID = "Name.1";
	private static String ENTITYINSTANCE_1_ATT_2_ID = "Gender.2";
	private static String ENTITYINSTANCE_1_ATT_3_ID = "Address.3";
	private static String ENTITYINSTANCE_1_ATT_4_ID = "PhoneNumber.4";
	private static String ENTITYINSTANCE_1_ATT_1_VALUE = "John";
	private static String ENTITYINSTANCE_1_ATT_2_VALUE = "male";
	private static String ENTITYINSTANCE_1_ATT_3_VALUE = "Lisbon";
	
	private static String UNDEFINED_VALUE = "$UNDEFINED$";
	private static String SKIPPED_VALUE = "$SKIPPED$";
	
	private static String USER_ID = "BlendedWorkflow";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SkipWorkItemServiceTest.class);
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
				allowing(workletAdapter).requestWorkItemPostConditionEvaluation(with(any(WorkItem.class)));
				allowing(workletAdapter).requestWorkItemPreConstraint(with(any(TaskWorkItem.class)));
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
	public void skipOneWorkItem() throws Exception {
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

		//WorkItem3 - AddAdress
		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem3OID).call();

		boolean committed = false;
		try {
			Transaction.begin();
			WorkItem workItem4 = bwInstance.getWorkItem(GOALWORKITEM_ID_4);
			WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
			WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
			AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
			AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
			AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
			AttributeInstance attributeInstance4 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_4_ID);
			
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
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance2.getState());
			
			//WorkItem4 - AddPhoneNUmber
			assertEquals(WorkItemState.ENABLED, workItem4.getState());
			for (WorkItemArgument workItemArgument : workItem4.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance4.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance4.getState());

			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.SKIPPED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance3.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
	
	@Test
	public void skipSubGoalsWorkItems() throws Exception {
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

		//WorkItem3 - AddAdress
		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem3OID).call();

		//WorkItem2 - AddGender
		Transaction.begin();
		WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
		long workItem2OID = workItem2.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem2OID).call();
		
		Transaction.begin();
		WorkItem workItem4 = bwInstance.getWorkItem(GOALWORKITEM_ID_4);
		long workItem4OID = workItem4.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem4OID).call();
		
		boolean committed = false;
		try {
			Transaction.begin();
			WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
			AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
			AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
			AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
			AttributeInstance attributeInstance4 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_4_ID);
			
			//WorkItem1 - AddPatient
			assertEquals(WorkItemState.PRE_GOAL, workItem1.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(UNDEFINED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.UNDEFINED, workItemArgument.getState());
			}
			assertEquals(UNDEFINED_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.UNDEFINED, attributeInstance1.getState());

			//WorkItem2 - AddGender
			assertEquals(WorkItemState.SKIPPED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance2.getState());

			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.SKIPPED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance3.getState());
			
			//WorkItem4 - AddPhoneNUmber
			assertEquals(WorkItemState.SKIPPED, workItem4.getState());
			for (WorkItemArgument workItemArgument : workItem4.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance4.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance4.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	@Test
	public void skipAllWorkItems() throws Exception {
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

		//WorkItem3 - AddAdress
		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem3OID).call();

		//WorkItem2 - AddGender
		Transaction.begin();
		WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
		long workItem2OID = workItem2.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem2OID).call();

		Transaction.begin();
		WorkItem workItem4 = bwInstance.getWorkItem(GOALWORKITEM_ID_4);
		long workItem4OID = workItem4.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem4OID).call();
		
		//WorkItem1 - AddPatient
		Transaction.begin();
		WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
		long workItem1OID = workItem1.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem1OID).call();

		boolean committed = false;
		try {
			Transaction.begin();
			AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
			AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
			AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
			AttributeInstance attributeInstance4 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_4_ID);
			
			//WorkItem1 - AddPatient
			assertEquals(WorkItemState.SKIPPED, workItem1.getState());
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance1.getState());

			//WorkItem2 - AddGender
			assertEquals(WorkItemState.SKIPPED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance2.getState());

			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.SKIPPED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance3.getState());
			
			//WorkItem4 - AddPhoneNUmber
			assertEquals(WorkItemState.SKIPPED, workItem4.getState());
			for (WorkItemArgument workItemArgument : workItem4.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			assertEquals(SKIPPED_VALUE, attributeInstance4.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance4.getState());
			
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
	
	@Test
	public void preGoal() throws Exception {
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

		//WorkItem3 - AddAdress
		Transaction.begin();
		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
		long workItem3OID = workItem3.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem3OID).call();

		//WorkItem2 - AddGender
		Transaction.begin();
		WorkItem workItem2 = bwInstance.getWorkItem(GOALWORKITEM_ID_2);
		long workItem2OID = workItem2.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem2OID).call();
		
		Transaction.begin();
		WorkItem workItem4 = bwInstance.getWorkItem(GOALWORKITEM_ID_4);
		long workItem4OID = workItem4.getOID();
		Transaction.commit();
		new SkipWorkItemService(workItem4OID).call();
		
		//WorkItem1 - AddPatient
		Transaction.begin();
		WorkItem workItem1 = bwInstance.getWorkItem(GOALWORKITEM_ID_1);
		AttributeInstance attributeInstance1 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_1_ID);
		AttributeInstance attributeInstance2 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_2_ID);
		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
		AttributeInstance attributeInstance4 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_4_ID);
		long workItem1OID = workItem1.getOID();
		
		// PreGoal
		for (WorkItemArgument workItemArgument : workItem1.getInputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance2.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_2_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE);
				workItemArgument.setState(DataState.DEFINED);
			}
		}
		
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
			for (WorkItemArgument workItemArgument : workItem1.getInputWorkItemArguments()) {
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}
			for (WorkItemArgument workItemArgument : workItem1.getOutputWorkItemArguments()) {
				assertEquals(DataState.DEFINED, workItemArgument.getState());
			}

			//WorkItem2 - AddGender
			assertEquals(WorkItemState.SKIPPED, workItem2.getState());
			for (WorkItemArgument workItemArgument : workItem2.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}

			//WorkItem3 - AddAdress
			assertEquals(WorkItemState.SKIPPED, workItem3.getState());
			for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			
			//WorkItem4 - AddPhoneNUmber
			assertEquals(WorkItemState.SKIPPED, workItem4.getState());
			for (WorkItemArgument workItemArgument : workItem4.getOutputWorkItemArguments()) {
				assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
				assertEquals(DataState.SKIPPED, workItemArgument.getState());
			}
			
			assertEquals(ENTITYINSTANCE_1_ATT_1_VALUE, attributeInstance1.getValue());
			assertEquals(DataState.DEFINED, attributeInstance1.getState());
			assertEquals(ENTITYINSTANCE_1_ATT_2_VALUE, attributeInstance2.getValue());
			assertEquals(DataState.DEFINED, attributeInstance2.getState());
			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE, attributeInstance3.getValue());
			assertEquals(DataState.DEFINED, attributeInstance3.getState());
			assertEquals(SKIPPED_VALUE, attributeInstance4.getValue());
			assertEquals(DataState.SKIPPED, attributeInstance4.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

}