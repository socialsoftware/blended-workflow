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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.HandleCompletedWorkItemFromWorkletService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class HandleCompletedWorkItemFromWorkletServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String CREATE_BWINSTANCE_XML = "src/test/xml/MedicalEpisode/CreateBWInstanceInput.xml";

	private static String YAWLCASE_ID = "yawlCaseID";
//	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String ENABLED_TASK_NAME = "Check-in Patient";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(HandleCompletedWorkItemFromWorkletServiceTest.class);
	}

	private Mockery context = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
			setThreadingPolicy(new Synchroniser());
		}
	};

	private YAWLAdapter yawlAdapter = null;
	private WorkletAdapter workletAdapter = null;

	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();

		yawlAdapter = context.mock(YAWLAdapter.class);
		workletAdapter = context.mock(WorkletAdapter.class);
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
			}
		});

		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
		Transaction.commit();

		String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		String createBWInstanceInputString = StringUtils.fileToString(CREATE_BWINSTANCE_XML);

		LoadBWSpecificationService loadBWSpecificationService = new LoadBWSpecificationService(dataModelString);
		CreateBWInstanceService createBWInstanceService = new CreateBWInstanceService(createBWInstanceInputString);
		try {
			loadBWSpecificationService.execute();
			createBWInstanceService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		Bootstrap.cleanTestDB();
	}

	@Test
	public void completedWorkItem() throws BlendedWorkflowException {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
		Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
		TaskWorkItem taskWorkItem = new TaskWorkItem(bwInstance, task);
		Transaction.commit();
		
		HandleCompletedWorkItemFromWorkletService handleEnabledTaskWorkItemService = new HandleCompletedWorkItemFromWorkletService(taskWorkItem, "TRUE");
		try {
			handleEnabledTaskWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();
			
			TaskWorkItem workItem = task.getTaskWorkItem();
			assertEquals(WorkItemState.COMPLETED, workItem.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
	
	@Test
	public void skipWorkItem() throws BlendedWorkflowException {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
		Task task = taskModelInstance.getTask(ENABLED_TASK_NAME);
		TaskWorkItem taskWorkItem = new TaskWorkItem(bwInstance, task);
		Transaction.commit();
		
		HandleCompletedWorkItemFromWorkletService handleEnabledTaskWorkItemService = new HandleCompletedWorkItemFromWorkletService(taskWorkItem, "SKIPPED");
		try {
			handleEnabledTaskWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();
			
			TaskWorkItem workItem = task.getTaskWorkItem();
			assertEquals(WorkItemState.SKIPPED, workItem.getState());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

}

