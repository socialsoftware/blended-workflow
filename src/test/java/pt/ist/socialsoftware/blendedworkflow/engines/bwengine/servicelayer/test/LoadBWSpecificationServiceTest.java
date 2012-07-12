package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jdom.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import com.sun.tools.javac.util.List;

import junit.framework.JUnit4TestAdapter;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.YAWLSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class LoadBWSpecificationServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisodeTest.xml";
	
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
	private WorkletGatewayClient workletGatewayClient = null;
	private BWManager bwManager = null;

	@Before
	public void setUp() {
		Bootstrap.initTestDB();
		yawlAdapter = context.mock(YAWLAdapter.class);
//		workletAdapter = context.mock(WorkletAdapter.class);
		workletGatewayClient = context.mock(WorkletGatewayClient.class);
		workletAdapter = new WorkletAdapter(workletGatewayClient);
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
		YSpecificationID yawlSpec = new YSpecificationID("UID_3213f8b4-5757-4674-a6a2-415aa191ca91","1.0","MedicalAppointment");
		final ArrayList<YSpecificationID> specIds = new ArrayList<YSpecificationID>();
		specIds.add(yawlSpec);
		
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(yawlAdapter).getLoadedActivitySpecs(); will(returnValue(specIds));
				allowing(workletGatewayClient).addNode(with(any(YSpecificationID.class)),with(any(String.class)),with(any(RuleType.class)),with(any(RdrNode.class)),with(any(String.class)));
//				oneOf(workletAdapter).loadRdrSet(with(any(BWSpecification.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
			}
		});

		String bwSpecificationString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		new LoadBWSpecificationService(bwSpecificationString).call();
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWSpecification bwSpecification = blendedWorkflow.getBWSpecification(BWSPECIFICATION_NAME);
			DataModel dataModel = bwSpecification.getDataModel();
			GoalModel goalModel = bwSpecification.getGoalModel();
			TaskModel taskModel = bwSpecification.getTaskModel();

			assertEquals(6, dataModel.getEntitiesCount());
			assertEquals(17, dataModel.getAttributesCount());
			assertEquals(5, dataModel.getRelationsCount());
			assertEquals(11, goalModel.getAchieveGoalsCount());
			assertEquals(1, goalModel.getMaintainGoalsCount());
			assertEquals(6, taskModel.getTasksCount());

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