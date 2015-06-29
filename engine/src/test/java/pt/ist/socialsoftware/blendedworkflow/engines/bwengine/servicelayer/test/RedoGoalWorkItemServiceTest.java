//package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;
//
//import static org.junit.Assert.*;
//
//import org.jmock.Expectations;
//import org.jmock.Mockery;
//import org.jmock.integration.junit4.JMock;
//import org.jmock.lib.concurrent.Synchroniser;
//import org.jmock.lib.legacy.ClassImposteriser;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import junit.framework.JUnit4TestAdapter;
//
//import pt.ist.fenixframework.pstm.Transaction;
//
//import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
//import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
//import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.EnableGoalWorkItemsService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.RedoGoalWorkItemService;
//import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
//import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
//import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
//import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;
//
//@RunWith(JMock.class)
//public class RedoGoalWorkItemServiceTest {
//
//	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
//	
//	private static String YAWLCASE_ID = "yawlCaseID";
//	private static String BWSPECIFICATION_NAME = "Medical Appointment";
//	private static String BWINSTANCE_ID = "Medical Appointment.1";
//
//	private static String GOAL_NAME_1 = "Add Patient";
//	private static String GOALWORKITEM_ID_3 = "Add Address.4";
//
//	private static String ENTITY_1_NAME = "Patient";
//	private static String ENTITYINSTANCE_1_ID = "Patient.1";
//
//	private static String ENTITYINSTANCE_1_ATT_3_ID = "Address.3";
//	private static String ENTITYINSTANCE_1_ATT_3_VALUE_1 = "Lisbon";
//	private static String ENTITYINSTANCE_1_ATT_3_VALUE_2 = "Oporto";
//	
//	private static String SKIPPED_VALUE = "$SKIPPED$";
//
//	private static String USER_ID = "BlendedWorkflow";
//
//	public static junit.framework.Test suite() {
//		return new JUnit4TestAdapter(RedoGoalWorkItemServiceTest.class);
//	}
//
//	private Mockery context = new Mockery() {
//		{
//			setImposteriser(ClassImposteriser.INSTANCE);
//			setThreadingPolicy(new Synchroniser());
//		}
//	};
//
//	private YAWLAdapter yawlAdapter = null;
//	private WorkletAdapter workletAdapter = null;
//	private BWManager bwManager = null;
//	private WorkListManager workListManager = null;
//
//	@Before
//	public void setUp() throws Exception {
//		Bootstrap.initTestDB();
//
//		yawlAdapter = context.mock(YAWLAdapter.class);
//		workletAdapter = context.mock(WorkletAdapter.class);
//		bwManager = context.mock(BWManager.class);
//		workListManager = context.mock(WorkListManager.class);
//		context.checking(new Expectations() {
//			{
//				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
//				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
//				oneOf(workletAdapter).loadRdrSet(with(any(BWSpecification.class)));
//				allowing(workletAdapter).requestWorkItemPostConditionEvaluation(with(any(TaskWorkItem.class)));
//				allowing(workletAdapter).requestWorkItemPreConditionEvaluation(with(any(TaskWorkItem.class)));
//				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
//				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
//				allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
//				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
//				allowing(workListManager).notifyReEnabledWorkItem(with(any(GoalWorkItem.class)));
//				allowing(workListManager).notifyCompletedWorkItem(with(any(WorkItem.class)));
//				allowing(workListManager).notifyPendingWorkItem(with(any(WorkItem.class)));
//			}
//		});
//
//		Transaction.begin();
//		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
//		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
//		BlendedWorkflow.getInstance().setBwManager(bwManager);
//		BlendedWorkflow.getInstance().setWorkListManager(workListManager);
//		Transaction.commit();
//
//		String bwSpecificationString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
//		new LoadBWSpecificationService(bwSpecificationString).call();
//
//		Transaction.begin();
//		BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
//		Transaction.commit();
//
//		new CreateBWInstanceService(bwSpecification.getOID(),"",USER_ID).call();
//	}
//
//	@After
//	public void tearDown() {
//		Bootstrap.clean();
//	}
//	
//	@Test
//	public void redoCompletedWorkItem() throws Exception {
//		Transaction.begin();
//		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//		long bwInstanceOID = bwInstance.getOID();
//		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
//		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
//		long parentGoalOID = parentGoal.getOID();
//		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
//		long entityInstance1OID = entityInstance1.getOID();
//		Transaction.commit();
//
//		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, entityInstance1OID).call();
//		new EnableGoalWorkItemsService(bwInstanceOID).call();
//
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_1
//		Transaction.begin();
//		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		long workItem3OID = workItem3.getOID();
//		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
//				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE_1);
//				workItemArgument.setState(DataState.DEFINED);
//			}
//		}
//		Transaction.commit();
//		new CheckInWorkItemService(workItem3OID).call();
//
//		Transaction.begin();
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_1
//		assertEquals(WorkItemState.COMPLETED, workItem3.getState());
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_1, workItemArgument.getValue());
//			assertEquals(DataState.DEFINED, workItemArgument.getState());
//		}
//		assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_1, attributeInstance3.getValue());
//		assertEquals(DataState.DEFINED, attributeInstance3.getState());
//		Transaction.commit();
//		
//		new RedoGoalWorkItemService(workItem3OID, USER_ID).call();
//		
//		Transaction.begin();
//		workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		assertEquals(WorkItemState.RE_ACTIVATED, workItem3.getState());
//		Transaction.commit();
//		
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_2
//		Transaction.begin();
//		workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		workItem3OID = workItem3.getOID();
//		entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
//		attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
//				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE_2);
//				workItemArgument.setState(DataState.DEFINED);
//			}
//		}
//		Transaction.commit();
//		new CheckInWorkItemService(workItem3OID).call();
//		
//		Transaction.begin();
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_2
//		assertEquals(WorkItemState.COMPLETED, workItem3.getState());
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_2, workItemArgument.getValue());
//			assertEquals(DataState.DEFINED, workItemArgument.getState());
//		}
//		assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_2, attributeInstance3.getValue());
//		assertEquals(DataState.DEFINED, attributeInstance3.getState());
//		Transaction.commit();
//	}
//	
//	@Test
//	public void redoSkippedWorkItem() throws Exception {
//		Transaction.begin();
//		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//		long bwInstanceOID = bwInstance.getOID();
//		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
//		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
//		long parentGoalOID = parentGoal.getOID();
//		
//		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
//		long entityInstance1OID = entityInstance1.getOID();
//		Transaction.commit();
//
//		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, entityInstance1OID).call();
//		new EnableGoalWorkItemsService(bwInstanceOID).call();
//
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_1
//		Transaction.begin();
//		WorkItem workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		long workItem3OID = workItem3.getOID();
//		Transaction.commit();
//		new SkipWorkItemService(workItem3OID).call();
//
//		Transaction.begin();
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_1
//		AttributeInstance attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
//		assertEquals(WorkItemState.SKIPPED, workItem3.getState());
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			assertEquals(SKIPPED_VALUE, workItemArgument.getValue());
//			assertEquals(DataState.SKIPPED, workItemArgument.getState());
//		}
//		assertEquals(SKIPPED_VALUE, attributeInstance3.getValue());
//		assertEquals(DataState.SKIPPED, attributeInstance3.getState());
//		Transaction.commit();
//		
//		new RedoGoalWorkItemService(workItem3OID, USER_ID).call();
//		
//		Transaction.begin();
//		workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		assertEquals(WorkItemState.RE_ACTIVATED, workItem3.getState());
//		Transaction.commit();
//		
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_2
//		Transaction.begin();
//		workItem3 = bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//		workItem3OID = workItem3.getOID();
//		entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
//		attributeInstance3 = entityInstance1.getAttributeInstance(ENTITYINSTANCE_1_ATT_3_ID);
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance3.getID())) {
//				workItemArgument.setValue(ENTITYINSTANCE_1_ATT_3_VALUE_2);
//				workItemArgument.setState(DataState.DEFINED);
//			}
//		}
//		Transaction.commit();
//		new CheckInWorkItemService(workItem3OID).call();
//		
//		Transaction.begin();
//		//WorkItem3 - AddAdress with ENTITYINSTANCE_1_ATT_3_VALUE_2
//		assertEquals(WorkItemState.COMPLETED, workItem3.getState());
//		for (WorkItemArgument workItemArgument : workItem3.getOutputWorkItemArguments()) {
//			assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_2, workItemArgument.getValue());
//			assertEquals(DataState.DEFINED, workItemArgument.getState());
//		}
//		assertEquals(ENTITYINSTANCE_1_ATT_3_VALUE_2, attributeInstance3.getValue());
//		assertEquals(DataState.DEFINED, attributeInstance3.getState());
//		Transaction.commit();
//	}
//
//
//}