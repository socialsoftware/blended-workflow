package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

@RunWith(JMock.class)
public class CreateBWInstanceServiceTest extends AbstractServiceTest {

    private static String BWINSTANCE_ID = "Medical Appointment.1";

    @Before
    public void setUp() throws Exception {
        initializeSpecification();
        initializeBWInstance();
    }

    @Ignore
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
            assertEquals(2, dataModelInstance.getEntity("Patient").get()
                    .getEntityInstancesCount());
            assertEquals(17, dataModelInstance.getAttributesCount());
            assertEquals(5, dataModelInstance.getRelationsCount());
            assertEquals(11, goalModelInstance.getAchieveGoalsCount());
            assertEquals(1, goalModelInstance.getMaintainGoalsCount());
            assertEquals(6, taskModelInstance.getTasksCount());
            assertEquals(0, bwInstance.getWorkItemsCount());

            assertEquals(YAWLCASE_ID, bwInstance.getYawlCaseID());

            Transaction.commit();
            committed = true;
        } catch (BWException e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

}