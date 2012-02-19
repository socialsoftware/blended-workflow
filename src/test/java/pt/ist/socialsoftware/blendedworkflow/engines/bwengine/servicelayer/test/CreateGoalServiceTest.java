package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;

public class CreateGoalServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode.xml";
	private static String CREATE_BWINSTANCE_INPUT_DATA = "src/test/xml/MedicalEpisodeCreateBWInstanceInput.xml";
	private static String INPUT_DATA = "src/test/xml/MedicalEpisodeCreateGoalInput.xml";
	private static String BWINSTANCE_ID = "Medical Appointment.1";

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
		String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		String createBWInstanceInputString = StringUtils.fileToString(CREATE_BWINSTANCE_INPUT_DATA);

		LoadBWSpecificationService loadBWSpecificationService = new LoadBWSpecificationService(dataModelString);
		CreateBWInstanceService createBWInstanceService = new CreateBWInstanceService(createBWInstanceInputString);
		try {
			loadBWSpecificationService.execute();
			createBWInstanceService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			Set<BWSpecification> allBWSpecifications = blendedWorkflow.getBwSpecificationsSet();
			allBWSpecifications.clear();
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("CreateGoalServiceTest failed @TearDown.");
			}
		}
	}

	@Test
	public void createGoalService() {
		String createGoalInputString = StringUtils.fileToString(INPUT_DATA);
		CreateGoalService createGoalService = new CreateGoalService(createGoalInputString);
		try {
			createGoalService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

			assertEquals(7, goalModelInstance.getGoalsCount()); // Created 6 Goals on Load +1

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