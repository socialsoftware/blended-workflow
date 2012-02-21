package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.PrintBWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;

public class LoadBWSpecificationServiceTest {
	
	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	
	private static String BWSPECIFICATION_NAME = "Medical Appointment";

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
				fail("LoadBWSpecificationServiceTest failed @TearDown.");
			}
		}
	}

	@Test
	public void loadBWSpecification() {
		String loadBWSpecificationInputString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		
		LoadBWSpecificationService loadBWSpecificationService = new LoadBWSpecificationService(loadBWSpecificationInputString);
		try {
			loadBWSpecificationService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();
			
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWSpecification bwSpecification = blendedWorkflow.getBWSpecification(BWSPECIFICATION_NAME);
			DataModel dataModel = bwSpecification.getDataModel();
			GoalModel goalModel = bwSpecification.getGoalModel();
			
			assertEquals(5, dataModel.getEntitiesCount());
			assertEquals(13, dataModel.getAttributesCount());
			assertEquals(4, dataModel.getRelationsCount());
			assertEquals(6, goalModel.getGoalsCount());
			
//			PrintBWSpecification.all(BWSPECIFICATION_NAME);
			
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