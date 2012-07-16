package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;

@RunWith(JMock.class)
public class CreateBWInstanceServiceTest extends AbstractServiceTest {

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String USER_ID = "BlendedWorkflow";


	@Test
	public void createOneBWInstance() throws Exception {
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
			}
		});

		BWSpecification bwSpecification = getBWSpecification(BWSPECIFICATION_NAME);
		new CreateBWInstanceService(bwSpecification.getOID(),"",USER_ID).call();

		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			assertEquals(6, dataModelInstance.getEntitiesCount());
			assertEquals(17, dataModelInstance.getAttributesCount());
			assertEquals(5, dataModelInstance.getRelationsCount());
			assertEquals(11, goalModelInstance.getAchieveGoalsCount());
			assertEquals(1, goalModelInstance.getMaintainGoalsCount());
			assertEquals(6, taskModelInstance.getTasksCount());
			assertEquals(1, bwInstance.getWorkItemsCount());
			
			assertEquals(YAWLCASE_ID, bwInstance.getYawlCaseID());

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

	@Override
	protected void assertResults() throws BlendedWorkflowException {
		// TODO Auto-generated method stub
		
	}

}