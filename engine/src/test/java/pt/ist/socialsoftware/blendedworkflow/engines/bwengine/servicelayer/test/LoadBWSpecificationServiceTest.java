package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.integration.junit4.JMock;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWGoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.execution.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class LoadBWSpecificationServiceTest extends AbstractServiceTest {

    @Ignore
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
        } catch (final BWException e) {
            fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }

    }

    private void assertLoadSpecificationResults() throws BWException {
        final BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        final BWSpecification bwSpecification = blendedWorkflow
                .getSpecByName(BWSPECIFICATION_NAME).orElse(null);
        final BWDataModel dataModel = bwSpecification.getDataModel();
        final BWGoalModel goalModel = bwSpecification.getGoalModel();
        final TaskModel taskModel = bwSpecification.getTaskModel();

        assertEquals(6, dataModel.getEntitiesSet().size());
        assertEquals(2, dataModel.getEntity("Patient").get()
                .getEntityInstancesSet().size());
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