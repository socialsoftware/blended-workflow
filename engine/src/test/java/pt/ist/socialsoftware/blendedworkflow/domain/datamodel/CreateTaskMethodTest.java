package pt.ist.socialsoftware.blendedworkflow.domain.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateTaskMethodTest extends TeardownRollbackTest {
    private static final String ANOTHER_DESCRIPTION = "another description";
    private static final String SPEC_ID = "Spec ID";
    private static final String TASK_ONE = "TaskOne";
    private static final String TASK_TWO = "TaskTwo";

    TaskModel taskModel;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        taskModel = spec.getTaskModel();
        new Task(taskModel, TASK_ONE, "this is a description");
    }

    @Test
    public void success() {
        Task task = taskModel.createTask(TASK_TWO, ANOTHER_DESCRIPTION);

        assertEquals(TASK_TWO, task.getName());
        assertEquals(ANOTHER_DESCRIPTION, task.getDescription());
    }

    @Test
    public void duplicateName() {
        try {
            taskModel.createTask(TASK_ONE, ANOTHER_DESCRIPTION);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_TASK_NAME, bwe.getError());
            assertEquals(TASK_ONE, bwe.getMessage());
        }
    }

}
