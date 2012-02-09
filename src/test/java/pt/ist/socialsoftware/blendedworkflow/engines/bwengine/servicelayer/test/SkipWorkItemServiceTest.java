package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;

public class SkipWorkItemServiceTest {

	private static String BW_SPECIFICATION_ID = "BWSpec-1";
	private static String BW_SPECIFICATION_NAME = "Medical Appointment";
	private static String BW_INSTANCE_ID = "BWInstance-1.1";
	private static String BW_INSTANCE_NAME = "John Medical Appointment";
	private static String TASK_ID_CHECK = "TASK-C";
	private static String TASK_NAME_CHECK = "gather information";
	private static String TASK_ID_VIOLATED = "TASK-V";
	private static String TASK_NAME_VIOLATED = "examine";
	private static String WORK_ITEM_ID_CHECK = "WorkItemID_C";
	private static String WORK_ITEM_ID_VIOLATED = "WorkItemID_V";

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
		boolean committed = false;
		try {
			Transaction.begin();

			// define vars
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWSpecification bwSpecification = new BWSpecification(BW_SPECIFICATION_ID, BW_SPECIFICATION_NAME);
			BWInstance bwInstance = new BWInstance(BW_INSTANCE_ID, BW_INSTANCE_NAME);
			TaskWorkItem workItemCheckedIn = new TaskWorkItem(WORK_ITEM_ID_CHECK);
			TaskWorkItem workItemConstraintViolated = new TaskWorkItem(WORK_ITEM_ID_VIOLATED);
			workItemCheckedIn.setState(WorkItemState.ENABLED);
			workItemConstraintViolated.setState(WorkItemState.ENABLED);
			AttributeInstance attInstance1 = new AttributeInstance("att1");
			AttributeInstance attInstance2 = new AttributeInstance("att2");

			// relations
			blendedWorkflow.addBwSpecification(bwSpecification);
			bwSpecification.addBwInstance(bwInstance);
			bwInstance.addWorkItem(workItemCheckedIn);
			bwInstance.addWorkItem(workItemConstraintViolated);
			workItemCheckedIn.addAttributeInstance(attInstance1);
			workItemCheckedIn.addAttributeInstance(attInstance2);
			workItemConstraintViolated.addAttributeInstance(attInstance1);

			Transaction.commit();
			committed = true;
		} 
		catch (Exception e) {
			fail(e.getMessage());
		}
		finally {
			if (!committed) 
			{
				Transaction.abort();
				fail("Could not add new bw specification: " + BW_SPECIFICATION_ID);
			}
		}
	}

	@After
	public void tearDown() {
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			Set<BWSpecification> allBWSpecifications = blendedWorkflow.getBwSpecificationSet();
			allBWSpecifications.clear();
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Could not clean blended workflow");
			}
		}
	}

	private BWInstance getBWInstance(String bwInstanceID) throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		return blendedWorkflow.getBWInstance(bwInstanceID);
	}

	@Test
	public void changedWorkItemState() {
		HashMap<String,String> values = new HashMap<String,String>();
		values.put("att1","value1");
		values.put("att2","value2");
		// Arrange
		SkipWorkItemService checkInWorkItemService = new SkipWorkItemService(BW_INSTANCE_ID, WORK_ITEM_ID_CHECK);
		// Act
		try {
			checkInWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		// Assert
		boolean committed = false;
		try {
			Transaction.begin();
			BWInstance bwInstance = getBWInstance(BW_INSTANCE_ID);
			WorkItem workItem = bwInstance.getWorkItem(WORK_ITEM_ID_CHECK);
			assertEquals(workItem.getState(),WorkItemState.SKIPPED); // skipped workitem is skipped??
			workItem = bwInstance.getWorkItem(WORK_ITEM_ID_VIOLATED);
			assertEquals(workItem.getState(),WorkItemState.CONSTRAINT_VIOLATION); // affected workitem is skipped??
			Transaction.commit();
			committed = true;
		} catch (BlendedWorkflowException e) {
			fail(e.getMessage());
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	/*@Test(expected=BlendedWorkflowException.class)
	public void nonExistentBWInstance() throws BlendedWorkflowException {
		HashMap<String,String> values = new HashMap<String,String>();
		values.put("att1","value1");
		values.put("att2","value2");
		// Arrange
		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(BW_INSTANCE_ID,WORK_ITEM_ID_CHECK,values);
		// Act
		try {
			checkInWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		// Assert
		boolean committed = false;
		try {
			Transaction.begin();
			getBWInstance("non Exist");
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}*/

}