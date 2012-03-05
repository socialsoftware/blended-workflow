package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;

import junit.framework.JUnit4TestAdapter;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

@RunWith(JMock.class)
public class LoadBWSpecificationServiceTest {
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LoadBWSpecificationServiceTest.class);
	}
	
	private Mockery context = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};
	
	private YAWLAdapter yawlAdapter = null;
	
	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	
	private static String BWSPECIFICATION_NAME = "Medical Appointment";

	@Before
	public void setUp() {
		Bootstrap.init();
		yawlAdapter = context.mock(YAWLAdapter.class);
		
		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		Transaction.commit();
	}

	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void loadBWSpecification() throws BlendedWorkflowException {
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
			}
		});
		
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