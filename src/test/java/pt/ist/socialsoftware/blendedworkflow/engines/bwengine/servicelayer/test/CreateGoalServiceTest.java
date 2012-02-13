package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;

public class CreateGoalServiceTest {

	private static String BW_INSTANCE_ID = "Medical Appointment.1";
	private static String PARENT_GOAL_NAME = "Cure";
	private static String NEW_GOAL_NAME = "Treat";
	private static String NEW_GOAL_CONDITION = "existsEntity(Medic)";

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
	}

	@After
	public void tearDown() {
	}

	@Test
	public void createGoalService() {
		CreateGoalService createGoalService = new CreateGoalService(BW_INSTANCE_ID, PARENT_GOAL_NAME, NEW_GOAL_NAME, NEW_GOAL_CONDITION);
		try {
			createGoalService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
	}

}
