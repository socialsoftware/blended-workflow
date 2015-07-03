package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.MockitoAbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MockitoLoadBWSpecificationServiceTest
        extends MockitoAbstractServiceTest {

    @Ignore
    @Test
    public void loadBWSpecification() throws Exception {
        initializeSpecification();

        boolean committed = false;
        try {
            Transaction.begin();

            assertLoadSpecificationResults();

            Transaction.commit();
            committed = true;
        } catch (final BWException e) {
            fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }

    private void assertLoadSpecificationResults() throws BWException {
        final BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        final Specification bwSpecification = blendedWorkflow
                .getSpecification(BWSPECIFICATION_NAME).orElse(null);
        final DataModel dataModel = bwSpecification.getDataModel();
        final GoalModel goalModel = bwSpecification.getGoalModel();
        final TaskModel taskModel = bwSpecification.getTaskModel();

        assertEquals(6, dataModel.getEntitiesSet().size());
        assertEquals(17, dataModel.getAttributesSet().size());
        assertEquals(5, dataModel.getRelationsSet().size());
        assertEquals(11, goalModel.getAchieveGoalsSet().size());
        assertEquals(1, goalModel.getMaintainGoalsSet().size());
        assertEquals(6, taskModel.getTasksSet().size());

        assertEquals(3, BlendedWorkflow.getInstance().getOrganizationalModel()
                .getRolesSet().size());
        assertEquals(4, BlendedWorkflow.getInstance().getOrganizationalModel()
                .getUsersSet().size());
    }
}
