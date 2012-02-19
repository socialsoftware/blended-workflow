package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;

public class CreateBWInstanceServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode.xml";
	private static String INPUT_DATA = "src/test/xml/MedicalEpisodeCreateBWInstanceInput.xml";
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
	public void setUp() throws Exception {
		String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);

		LoadBWSpecificationService loadBWSpecificationService = new LoadBWSpecificationService(dataModelString);
		try {
			loadBWSpecificationService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
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
				fail("CreateBWInstanceServiceTest failed @TearDown.");
			}
		}
	}

	@Test
	public void createBWInstanceService() {
		String createBWInstanceInputString = StringUtils.fileToString(INPUT_DATA);
		CreateBWInstanceService createBWInstanceService = new CreateBWInstanceService(createBWInstanceInputString);
		try {
			createBWInstanceService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

			assertEquals(5, dataModelInstance.getEntitiesCount());
			assertEquals(13, dataModelInstance.getAttributesCount());
			assertEquals(4, dataModelInstance.getRelationsCount());
			assertEquals(6, goalModelInstance.getGoalsCount());

			assertEquals(3, bwInstance.getWorkItemsCount()); // FIXME should not done here

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
	
//	@Test(expected=BlendedWorkflowException.class)
//	public void nonExistentBWInstance() throws BlendedWorkflowException {
//		String createBWInstanceInputString = StringUtils.fileToString(INPUT_DATA);
//		CreateBWInstanceService createBWInstanceService = new CreateBWInstanceService(createBWInstanceInputString);
//		try {
//			createBWInstanceService.execute();
//		} catch(BlendedWorkflowException e) {		
//			fail(e.getMessage());
//		}
//		boolean committed = false;
//		try {
//			Transaction.begin();
//
//			Transaction.commit();
//			committed = true;
//		} finally {
//			if (!committed) {
//				Transaction.abort();
//			}
//		}
//	}

}