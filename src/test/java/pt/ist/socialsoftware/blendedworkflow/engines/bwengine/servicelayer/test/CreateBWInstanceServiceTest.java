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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class CreateBWInstanceServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String ACTIVITY_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.yawl.xml";

//	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CreateBWInstanceServiceTest.class);
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
	private WorkListManager workListManager = null;

	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();

		yawlAdapter = context.mock(YAWLAdapter.class);
		workletAdapter = context.mock(WorkletAdapter.class);
		bwManager = context.mock(BWManager.class);
		workListManager = context.mock(WorkListManager.class);
		context.checking(new Expectations() {
			{
//				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
			}
		});

		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
		BlendedWorkflow.getInstance().setBwManager(bwManager);
		BlendedWorkflow.getInstance().setWorkListManager(workListManager);
		Transaction.commit();

		String bwSpecificationString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		String yawlSpecificationString = StringUtils.fileToString(ACTIVITY_FILENAME);
		new LoadBWSpecificationService(bwSpecificationString, yawlSpecificationString).execute();
	}

	@After
	public void tearDown() throws Exception {
		Bootstrap.clean();
	}

	@Test
	public void createOneBWInstance() throws BlendedWorkflowException {
		context.checking(new Expectations() {
			{
//				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
			}
		});

		BWSpecification bwSpecification = getBWSpecification(BWSPECIFICATION_NAME);
		new CreateBWInstanceService(bwSpecification.getOID()).execute();

		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			assertEquals(5, dataModelInstance.getEntitiesCount());
			assertEquals(13, dataModelInstance.getAttributesCount());
			assertEquals(4, dataModelInstance.getRelationsCount());
			assertEquals(6, goalModelInstance.getGoalsCount());
			assertEquals(5, taskModelInstance.getTasksCount());
//			assertEquals(3, bwInstance.getWorkItemsCount());
//			assertEquals(YAWLCASE_ID, bwInstance.getYawlCaseID());

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

	private BWSpecification getBWSpecification(String name) throws BlendedWorkflowException {
		Transaction.begin();
		BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(name);
		Transaction.commit();
		return bwSpecification;
	}

}