package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;

public class SkipWorkItemServiceTest {

	private static String BW_INSTANCE_ID = "Medical Appointment.1";
	private static String GOAL_WORKITEM_SK = "Examine.2";

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
	public void skipWorkItemService() {
		SkipWorkItemService skipWorkItemService = new SkipWorkItemService(BW_INSTANCE_ID, GOAL_WORKITEM_SK);
		try {
			skipWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
	}

}