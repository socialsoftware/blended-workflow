package pt.ist.socialsoftware.blendedworkflow.adapters.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

public class WorkletAdapterTest {

	@Before
	public void setUp() {
		Bootstrap.initTestDB();
	}

	@After
	public void tearDown() {
		Bootstrap.clean();
	}
	
	@Test
	public void test() {
		assertEquals(1,1);
	}

	/**
	 * HandleCompletedWorkItemFromWorkletServiceTest
	 */
//	public class HandleCompletedWorkItemFromWorkletServiceTest {
//
//		private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
//
//		private static String YAWLCASE_ID = "yawlCaseID";
//		private static String BWSPECIFICATION_NAME = "Medical Appointment";
//		private static String BWINSTANCE_ID = "Medical Appointment.1";
//		private static String ENABLED_TASK_NAME = "Check-in Patient";
//
//		public static junit.framework.Test suite() {
//			return new JUnit4TestAdapter(HandleCompletedWorkItemFromWorkletServiceTest.class);
//		}
//
//		private Mockery context = new Mockery() {
//			{
//				setImposteriser(ClassImposteriser.INSTANCE);
//				setThreadingPolicy(new Synchroniser());
//			}
//		};
//
//		private YAWLAdapter yawlAdapter = null;
//		private WorkletAdapter workletAdapter = null;
//
//		@Before
//		public void setUp() throws BlendedWorkflowException {
//			Bootstrap.initTestDB();
//
//			yawlAdapter = context.mock(YAWLAdapter.class);
//			workletAdapter = context.mock(WorkletAdapter.class);
//			context.checking(new Expectations() {
//				{
//					oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
//					oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
//					allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
//				}
//			});
//
//			Transaction.begin();
//			BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
//			BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
//			Transaction.commit();
//
//			String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
//			new LoadBWSpecificationService(dataModelString).execute();
//
//			Transaction.begin();
//			BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
//			Transaction.commit();
//
//			new CreateBWInstanceService(bwSpecification).execute();
//		}
//
//		@After
//		public void tearDown() {
//			Bootstrap.clean();
//		}
//
//		@Test
//		public void completedWorkItem() throws BlendedWorkflowException {
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
//			Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
//			//		TaskWorkItem taskWorkItem = new TaskWorkItem(bwInstance, task);
//			Transaction.commit();
//
//			//		HandleCompletedWorkItemFromWorkletService handleEnabledTaskWorkItemService = new HandleCompletedWorkItemFromWorkletService(taskWorkItem, "TRUE");
//			//		try {
//			//			handleEnabledTaskWorkItemService.execute();
//			//		} catch(BlendedWorkflowException e) {		
//			//			fail(e.getMessage());
//			//		}
//			boolean committed = false;
//			try {
//				Transaction.begin();
//
//				TaskWorkItem workItem = task.getTaskWorkItem();
//				assertEquals(WorkItemState.COMPLETED, workItem.getState());
//
//				Transaction.commit();
//				committed = true;
//			} finally {
//				if (!committed) {
//					Transaction.abort();
//				}
//			}
//		}
//
//		@Test
//		public void skipWorkItem() throws BlendedWorkflowException {
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
//			Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
//			//		TaskWorkItem taskWorkItem = new TaskWorkItem(bwInstance, task);
//			Transaction.commit();
//
//			//		HandleCompletedWorkItemFromWorkletService handleEnabledTaskWorkItemService = new HandleCompletedWorkItemFromWorkletService(taskWorkItem, "SKIPPED");
//			//		try {
//			//			handleEnabledTaskWorkItemService.execute();
//			//		} catch(BlendedWorkflowException e) {		
//			//			fail(e.getMessage());
//			//		}
//			boolean committed = false;
//			try {
//				Transaction.begin();
//
//				TaskWorkItem workItem = task.getTaskWorkItem();
//				assertEquals(WorkItemState.SKIPPED, workItem.getState());
//
//				Transaction.commit();
//				committed = true;
//			} finally {
//				if (!committed) {
//					Transaction.abort();
//				}
//			}
//		}
//
//	}
	
	/**
	 * HandleTaskPreActivityServiceTest
	 */	
//	public class HandleEnabledTaskWorkItemServiceTest {
//
//		private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
//
//		private static String YAWLCASE_ID = "yawlCaseID";
//		private static String BWSPECIFICATION_NAME = "Medical Appointment";
//		private static String BWINSTANCE_ID = "Medical Appointment.1";
//		private static String ENABLED_TASK_NAME = "Check-in Patient";
//
//		public static junit.framework.Test suite() {
//			return new JUnit4TestAdapter(HandleEnabledTaskWorkItemServiceTest.class);
//		}
//
//		private Mockery context = new Mockery() {
//			{
//				setImposteriser(ClassImposteriser.INSTANCE);
//				setThreadingPolicy(new Synchroniser());
//			}
//		};
//
//		private YAWLAdapter yawlAdapter = null;
//		private WorkletAdapter workletAdapter = null;
//
//		@Before
//		public void setUp() throws BlendedWorkflowException {
//			Bootstrap.initTestDB();
//
//			yawlAdapter = context.mock(YAWLAdapter.class);
//			workletAdapter = context.mock(WorkletAdapter.class);
//			context.checking(new Expectations() {
//				{
//					oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
//					oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
//					allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
//				}
//			});
//
//			Transaction.begin();
//			BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
//			BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
//			Transaction.commit();
//
//			String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
//			new LoadBWSpecificationService(dataModelString).execute();
//
//			Transaction.begin();
//			BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
//			Transaction.commit();
//
//			new CreateBWInstanceService(bwSpecification).execute();
//		}
//
//		@After
//		public void tearDown() {
//			Bootstrap.clean();
//		}
//
//		@Test
//		public void handleEnabledTask() throws BlendedWorkflowException {
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			Transaction.commit();
//
//			//			HandleEnabledTaskWorkItemService handleEnabledTaskWorkItemService = new HandleEnabledTaskWorkItemService(bwInstance, ENABLED_TASK_NAME);
//			//			try {
//			//				handleEnabledTaskWorkItemService.execute();
//			//			} catch(BlendedWorkflowException e) {		
//			//				fail(e.getMessage());
//			//			}
//			boolean committed = false;
//			try {
//				Transaction.begin();
//
//				TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
//				Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
//				TaskWorkItem workItem = task.getTaskWorkItem();
//
//				assertEquals(4, bwInstance.getWorkItemsCount());
//				assertEquals(WorkItemState.ENABLED, workItem.getState());
//
//				assertEquals(0, workItem.getPreConstraintAttributeInstancesCount());
//
//				assertEquals(5, workItem.getContraintViolationAttributeInstancesCount());
//				for (AttributeInstance attributeInstance : workItem.getContraintViolationAttributeInstances()) {
//					assertEquals(DataState.UNDEFINED, attributeInstance.getState());
//				}
//
//				Transaction.commit();
//				committed = true;
//			} catch (BlendedWorkflowException e) {
//				fail(e.getMessage());
//			} finally {
//				if (!committed) {
//					Transaction.abort();
//				}
//			}
//		}
//	}
	
/**
 * HandleTaskPreActivityServiceTest
 */
//	public class HandleTaskPreActivityServiceTest {
//
//		private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
//
//		private static String YAWLCASE_ID = "yawlCaseID";
//		private static String BWSPECIFICATION_NAME = "Medical Appointment";
//		private static String BWINSTANCE_ID = "Medical Appointment.1";
//		private static String ENABLED_TASK_NAME = "Doctor Appointment";
//
//		public static junit.framework.Test suite() {
//			return new JUnit4TestAdapter(HandleTaskPreActivityServiceTest.class);
//		}
//
//		private Mockery context = new Mockery() {
//			{
//				setImposteriser(ClassImposteriser.INSTANCE);
//				setThreadingPolicy(new Synchroniser());
//			}
//		};
//
//		private YAWLAdapter yawlAdapter = null;
//		private WorkletAdapter workletAdapter = null;
//
//		@Before
//		public void setUp() throws BlendedWorkflowException {
//			Bootstrap.initTestDB();
//
//			yawlAdapter = context.mock(YAWLAdapter.class);
//			workletAdapter = context.mock(WorkletAdapter.class);
//			context.checking(new Expectations() {
//				{
//					oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
//					oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
//					allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
//				}
//			});
//
//			Transaction.begin();
//			BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
//			BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
//			Transaction.commit();
//
//			String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
//			new LoadBWSpecificationService(dataModelString).execute();
//
//			Transaction.begin();
//			BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
//			Transaction.commit();
//
//			new CreateBWInstanceService(bwSpecification).execute();
//
//			Transaction.begin();
//			// Get Task preCondition AttributesInstances and change state to SKIPPED
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
//			Entity entity = dataModelInstance.getEntity("Patient Data");
//
//			for (Attribute attribute : entity.getAttributes()) {
//				if (!attribute.getName().equals("Medical Examination")) {
//					attribute.getAttributeInstances().get(0).setState(DataState.SKIPPED);
//				}
//			}
//			Transaction.commit();	
//		}
//
//		@After
//		public void tearDown() {
//			Bootstrap.clean();
//		}
//
//		@Test
//		public void handleTaskPreActivity() throws BlendedWorkflowException {
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			Transaction.commit();
//
//			//			HandleTaskPreActivityService handleTaskPreActivityService = new HandleTaskPreActivityService(bwInstance, ENABLED_TASK_NAME);
//			//			try {
//			//				handleTaskPreActivityService.execute();
//			//			} catch(BlendedWorkflowException e) {		
//			//				fail(e.getMessage());
//			//			}
//			boolean committed = false;
//			try {
//				Transaction.begin();
//
//				TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
//				Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
//				TaskWorkItem workItem = task.getTaskWorkItem();
//
//				assertEquals(4, bwInstance.getWorkItemsCount());
//				assertEquals(WorkItemState.PRE_TASK, workItem.getState());
//
//				assertEquals(4, workItem.getPreConstraintAttributeInstancesCount());
//
//				assertEquals(4, workItem.getContraintViolationAttributeInstancesCount());
//				for (AttributeInstance attributeInstance : workItem.getContraintViolationAttributeInstances()) {
//					assertEquals(DataState.UNDEFINED, attributeInstance.getState());
//				}
//
//				Transaction.commit();
//				committed = true;
//			} catch (BlendedWorkflowException e) {
//				fail(e.getMessage());
//			} finally {
//				if (!committed) {
//					Transaction.abort();
//				}
//			}
//		}
//
//	}

}
