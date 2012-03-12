package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.JUnit4TestAdapter;

import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@RunWith(JMock.class)
public class CheckInWorkItemServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String GOALWORKITEM_PRESCRIBE_ID = "Prescribe.3";
	private static String GOALWORKITEM_COLLECTDATA_ID = "Collect Data.1";
	private static String GOALWORKITEM_PHYSICALEXAMINATION_ID = "Physical Examination.2";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CheckInWorkItemServiceTest.class);
	}

	private Mockery context = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
			setThreadingPolicy(new Synchroniser());
		}
	};

	private YAWLAdapter yawlAdapter = null;
	private WorkletAdapter workletAdapter = null;

	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();

		yawlAdapter = context.mock(YAWLAdapter.class);
		workletAdapter = context.mock(WorkletAdapter.class);
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
			}
		});

		Transaction.begin();
		BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
		BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
		Transaction.commit();

		String dataModelString = StringUtils.fileToString(BWSPECIFICATION_FILENAME);
		new LoadBWSpecificationService(dataModelString).execute();

		Transaction.begin();
		BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
		Transaction.commit();

		new CreateBWInstanceService(bwSpecification).execute();

		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		WorkItem workItem = bwInstance.getWorkItem(GOALWORKITEM_COLLECTDATA_ID);
		workItem.setState(WorkItemState.ENABLED);
		workItem = bwInstance.getWorkItem(GOALWORKITEM_PHYSICALEXAMINATION_ID);
		workItem.setState(WorkItemState.ENABLED);
		workItem = bwInstance.getWorkItem(GOALWORKITEM_PRESCRIBE_ID);
		workItem.setState(WorkItemState.ENABLED);
		Transaction.commit();
	}

	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void checkInOneWorkItem() throws BlendedWorkflowException {
		WorkItem workItem = getWorkItem(GOALWORKITEM_PRESCRIBE_ID);
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("Recipe.1", "Aspirin");
		new CheckInWorkItemService(workItem, values).execute();

		boolean committed = false;
		try {
			Transaction.begin();

			assertEquals(WorkItemState.CHECKED_IN, workItem.getState());
			for (AttributeInstance attributeInstance : workItem.getContraintViolationAttributeInstances()) {
				assertEquals(DataState.DEFINED, attributeInstance.getState());
			}

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	private WorkItem getWorkItem(String ID) throws BlendedWorkflowException {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		WorkItem workItem = bwInstance.getWorkItem(ID);
		Transaction.commit();
		return workItem;
	}

}