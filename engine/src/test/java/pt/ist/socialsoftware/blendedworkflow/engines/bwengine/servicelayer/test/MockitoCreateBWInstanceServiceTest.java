package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jvstm.Transaction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.MockitoAbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MockitoCreateBWInstanceServiceTest extends
        MockitoAbstractServiceTest {

    @Before
    public void setUp() throws Exception, BWException {
        initializeSpecification();
    }

    @Ignore
    @Test
    public void createOneBWInstance() throws Exception {
        initializeBWInstance();

        boolean committed = false;
        try {
            Transaction.begin();

            final BlendedWorkflow blendedWorkflow = BlendedWorkflow
                    .getInstance();
            final BWInstance bwInstance = blendedWorkflow
                    .getBWInstance(BWINSTANCE_ID);
            final DataModelInstance dataModelInstance = bwInstance
                    .getDataModelInstance();
            final GoalModelInstance goalModelInstance = bwInstance
                    .getGoalModelInstance();
            final TaskModelInstance taskModelInstance = bwInstance
                    .getTaskModelInstance();

            assertEquals(6, dataModelInstance.getEntitiesSet().size());
            assertEquals(17, dataModelInstance.getAttributesSet().size());
            assertEquals(5, dataModelInstance.getRelationsSet().size());
            assertEquals(11, goalModelInstance.getAchieveGoalsSet().size());
            assertEquals(1, goalModelInstance.getMaintainGoalsSet().size());
            assertEquals(6, taskModelInstance.getTasksSet().size());
            assertEquals(0, bwInstance.getWorkItemsSet().size());

            assertEquals(YAWLCASE_ID, bwInstance.getYawlCaseID());

            Transaction.commit();
            committed = true;
        } catch (final BWException e) {
            fail(e.getMessage());
        } finally {
            if (!committed)
                Transaction.abort();
        }
    }

}
