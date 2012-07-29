package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

@RunWith(JMock.class)
public class CreateBWInstanceServiceTest extends AbstractServiceTest {

	private static String BWINSTANCE_ID = "Medical Appointment.1";

	@Before
	public void setUp() throws Exception {
		initializeSpecification();
		initializeBWInstance();
	}

	@Test
	public void createOneBWInstance() throws Exception {

		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow
					.getBWInstance(BWINSTANCE_ID);
			DataModelInstance dataModelInstance = bwInstance
					.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance
					.getGoalModelInstance();
			TaskModelInstance taskModelInstance = bwInstance
					.getTaskModelInstance();

			assertEquals(6, dataModelInstance.getEntitiesCount());
			assertEquals(2, dataModelInstance.getEntity("Patient").getEntityInstancesCount());
			assertEquals(17, dataModelInstance.getAttributesCount());
			assertEquals(5, dataModelInstance.getRelationsCount());
			assertEquals(11, goalModelInstance.getAchieveGoalsCount());
			assertEquals(1, goalModelInstance.getMaintainGoalsCount());
			assertEquals(6, taskModelInstance.getTasksCount());
			assertEquals(0, bwInstance.getWorkItemsCount());

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

}