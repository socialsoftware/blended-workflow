package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class LoadBWSpecificationServiceTest extends AbstractServiceTest {
	
	@Test
	public void loadBWSpecification() throws Exception {

		final String bwSpecificationString = StringUtils
				.fileToString(BWSPECIFICATION_FILENAME);
		new LoadBWSpecificationService(bwSpecificationString).call();
		
		boolean committed = false;
		try {
			Transaction.begin();

			assertLoadSpecificationResults();

			Transaction.commit();
			committed = true;
		} catch (final BlendedWorkflowException e) {
			fail(e.getMessage());
		} finally {
			if (!committed)
				Transaction.abort();
		}

	}

	private void assertLoadSpecificationResults() throws BlendedWorkflowException {
		final BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		final BWSpecification bwSpecification = blendedWorkflow
				.getBWSpecification(BWSPECIFICATION_NAME);
		final DataModel dataModel = bwSpecification.getDataModel();
		final GoalModel goalModel = bwSpecification.getGoalModel();
		final TaskModel taskModel = bwSpecification.getTaskModel();

		assertEquals(6, dataModel.getEntitiesCount());
		assertEquals(2, dataModel.getEntity("Patient").getEntityInstancesCount());
		assertEquals(17, dataModel.getAttributesCount());
		assertEquals(5, dataModel.getRelationsCount());
		assertEquals(11, goalModel.getAchieveGoalsCount());
		assertEquals(1, goalModel.getMaintainGoalsCount());
		assertEquals(6, taskModel.getTasksCount());

		assertEquals(3, BlendedWorkflow.getInstance().getOrganizationalModel()
				.getRolesCount());
		assertEquals(4, BlendedWorkflow.getInstance().getOrganizationalModel()
				.getUsersCount());	
	}

}