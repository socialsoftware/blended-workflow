package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.MockitoAbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class MockitoCreateBWInstanceServiceTest extends MockitoAbstractServiceTest {

	@Before
	public void setUp() throws Exception, BlendedWorkflowException {
		initializeSpecification();
	}

	@Test
	public void createOneBWInstance() throws Exception {
		initializeBWInstance();

		boolean committed = false;
		try {
			Transaction.begin();

			final BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			final BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			final DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			final GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
			final TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			assertEquals(6, dataModelInstance.getEntitiesCount());
			assertEquals(17, dataModelInstance.getAttributesCount());
			assertEquals(5, dataModelInstance.getRelationsCount());
			assertEquals(11, goalModelInstance.getAchieveGoalsCount());
			assertEquals(1, goalModelInstance.getMaintainGoalsCount());
			assertEquals(6, taskModelInstance.getTasksCount());
			assertEquals(0, bwInstance.getWorkItemsCount());

			assertEquals(YAWLCASE_ID, bwInstance.getYawlCaseID());

			Transaction.commit();
			committed = true;
		} catch (final BlendedWorkflowException e) {
			fail(e.getMessage());
		} finally {
			if (!committed)
				Transaction.abort();
		}
	}

}
