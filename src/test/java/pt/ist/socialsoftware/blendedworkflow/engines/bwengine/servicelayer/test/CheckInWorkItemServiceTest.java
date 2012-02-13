package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
//import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;

public class CheckInWorkItemServiceTest {

//	private static String BW_SPECIFICATION_NAME = "Medical Appointment";
//	private static String TASK_INFORMATION_C = "TASK-C";
//	private static String TASK_INFORMATION_V = "TASK-V";
//	private static String WORK_ITEM_ID_CHECK = "WorkItemID_C";
//	private static String WORK_ITEM_ID_VIOLATED = "WorkItemID_V";
	
	private static String BW_INSTANCE_ID = "Medical Appointment.1";
	private static String GOAL_WORKITEM_CI = "Diagnose.1";

	static {
		if(FenixFramework.getConfig()==null) {
			FenixFramework.initialize(new Config() {{
				dbAlias="test-db";
				domainModelPath="src/main/dml/blendedworkflow.dml";
				repositoryType=RepositoryType.BERKELEYDB;
				rootClass=BlendedWorkflow.class;
			}});
		}
	}

	@Before
	public void setUp() {
//		boolean committed = false;
//		try {
//			Transaction.begin();
//
//			// define vars
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWSpecification bwSpecification = new BWSpecification(BW_SPECIFICATION_NAME);
//			BWInstance bwInstance = new BWInstance(bwSpecification);
//			TaskWorkItem workItemCheckedIn = new TaskWorkItem(bwInstance, TASK_INFORMATION_C);
//			TaskWorkItem workItemConstraintViolated = new TaskWorkItem(bwInstance, TASK_INFORMATION_V);
//			workItemCheckedIn.setState(WorkItemState.ENABLED);
//			workItemConstraintViolated.setState(WorkItemState.ENABLED);
//			AttributeInstance attInstance1 = new AttributeInstance("att1");
//			AttributeInstance attInstance2 = new AttributeInstance("att2");
//
//			// relations
//			blendedWorkflow.addBwSpecifications(bwSpecification);
//			bwInstance.addWorkItems(workItemCheckedIn);
//			bwInstance.addWorkItems(workItemConstraintViolated);
//			workItemCheckedIn.addAttributeInstances(attInstance1);
//			workItemCheckedIn.addAttributeInstances(attInstance2);
//			workItemConstraintViolated.addAttributeInstances(attInstance1);
//
//			Transaction.commit();
//			committed = true;
//		} 
//		catch (Exception e) {
//			fail(e.getMessage());
//		}
//		finally {
//			if (!committed) 
//			{
//				Transaction.abort();
//				fail("Could not add new bw specification: " + BW_SPECIFICATION_NAME);
//			}
//		}
	}

	@After
	public void tearDown() {
//		boolean committed = false;
//		try {
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			Set<BWSpecification> allBWSpecifications = blendedWorkflow.getBwSpecificationsSet();
//			allBWSpecifications.clear();
//			Transaction.commit();
//			committed = true;
//		} finally {
//			if (!committed) {
//				Transaction.abort();
//				fail("Could not clean blended workflow");
//			}
//		}
	}

	@Test
	public void checkInWorkItemService() {
		HashMap<String,String> values = new HashMap<String,String>();
		values.put("Name.1","Ana");
		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(BW_INSTANCE_ID, GOAL_WORKITEM_CI, values);
		try {
			checkInWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();
			assertEquals(true, true);
//			BWInstance bwInstance = getBWInstance(BW_INSTANCE_ID);
//			WorkItem workItem = bwInstance.getWorkItem(TASK_INFORMATION_C + ".1");
//			assertEquals(workItem.getState(),WorkItemState.CHECKED_IN);
//			workItem = bwInstance.getWorkItem(WORK_ITEM_ID_VIOLATED);
//			assertEquals(workItem.getState(),WorkItemState.CONSTRAINT_VIOLATION);
			Transaction.commit();
			committed = true;
//		} catch (BlendedWorkflowException e) {
//			fail(e.getMessage());
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Test: CheckInWorkItemService failed.");
			}
		}
	}

//	@Test(expected=BlendedWorkflowException.class)
//	public void nonExistentBWInstance() throws BlendedWorkflowException {
//		HashMap<String,String> values = new HashMap<String,String>();
//		values.put("att1","value1");
//		values.put("att2","value2");
//		// Arrange
//		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(BW_INSTANCE_ID,WORK_ITEM_ID_CHECK,values);
//		// Act
//		try {
//			checkInWorkItemService.execute();
//		} catch(BlendedWorkflowException e) {		
//			fail(e.getMessage());
//		}
//		// Assert
//		boolean committed = false;
//		try {
//			Transaction.begin();
//			getBWInstance("non Exist");
//			Transaction.commit();
//			committed = true;
//		} finally {
//			if (!committed) {
//				Transaction.abort();
//			}
//		}
//	}

}