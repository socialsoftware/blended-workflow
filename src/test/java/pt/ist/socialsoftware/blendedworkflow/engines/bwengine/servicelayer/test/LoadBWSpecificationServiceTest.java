package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;

import junit.framework.JUnit4TestAdapter;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class LoadBWSpecificationServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String ACTIVITY_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.yawl";
	
	private static String BWSPECIFICATION_NAME = "Medical Appointment";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LoadBWSpecificationServiceTest.class);
	}

	private Mockery context = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
			setThreadingPolicy(new Synchroniser());
		}
	};

	private YAWLAdapter yawlAdapter = null;
	private WorkletAdapter workletAdapter = null;
	private BWManager bwManager = null;

	@Before
	public void setUp() {
		Bootstrap.initTestDB();
		yawlAdapter = context.mock(YAWLAdapter.class);
		workletAdapter = context.mock(WorkletAdapter.class);
		bwManager = context.mock(BWManager.class);

		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
		BlendedWorkflow.getInstance().setBwManager(bwManager);
		Transaction.commit();
	}

	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void loadBWSpecification() throws Exception {
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(workletAdapter).loadRdrSet(with(any(BWSpecification.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
			}
		});

		String bwSpecificationString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		String yawlSpecificationString = StringUtils.fileToString(ACTIVITY_FILENAME);
		new LoadBWSpecificationService(bwSpecificationString, yawlSpecificationString).call();
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWSpecification bwSpecification = blendedWorkflow.getBWSpecification(BWSPECIFICATION_NAME);
			DataModel dataModel = bwSpecification.getDataModel();
			GoalModel goalModel = bwSpecification.getGoalModel();

			assertEquals(5, dataModel.getEntitiesCount());
			assertEquals(14, dataModel.getAttributesCount());
			assertEquals(4, dataModel.getRelationsCount());
			assertEquals(11, goalModel.getAchieveGoalsCount());
			assertEquals(3, goalModel.getMaintainGoalsCount());

			assertEquals(3,BlendedWorkflow.getInstance().getOrganizationalModel().getRolesCount());
			assertEquals(4,BlendedWorkflow.getInstance().getOrganizationalModel().getUsersCount());
			
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