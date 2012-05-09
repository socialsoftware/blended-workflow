package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

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
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class ReDoGoalServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String ACTIVITY_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.yawl";

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String GOALWORKITEM_PRESCRIBE_ID = "Prescribe.3";
	private static String GOALWORKITEM_COLLECTDATA_ID = "Collect Data.1";
	private static String GOALWORKITEM_PHYSICALEXAMINATION_ID = "Physical Examination.2";
	
	private static String GOALWORKITEM_PRESCRIBE_INPUT_ATT1 = "Recipe";
	private static String GOALWORKITEM_PRESCRIBE_INPUT_VALUE1 = "Aspirin";
	private static String USER_ID = "BlendedWorkflow";

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ReDoGoalServiceTest.class);
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
	public void setUp() throws Exception {
		Bootstrap.initTestDB();

		yawlAdapter = context.mock(YAWLAdapter.class);
		workletAdapter = context.mock(WorkletAdapter.class);
		bwManager = context.mock(BWManager.class);
		workListManager = context.mock(WorkListManager.class);
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).loadSpecification(with(any(String.class)));
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				oneOf(workletAdapter).loadRdrSet(with(any(BWSpecification.class)));
				allowing(workletAdapter).notifyWorkItemContraintViolation(with(any(WorkItem.class)));
				allowing(workletAdapter).notifyWorkItemPreConstraint(with(any(TaskWorkItem.class)));
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
				allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyCompletedWorkItem(with(any(WorkItem.class)));
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
		new LoadBWSpecificationService(bwSpecificationString, yawlSpecificationString).call();

		Transaction.begin();
		BWSpecification bwSpecification = BlendedWorkflow.getInstance().getBWSpecification(BWSPECIFICATION_NAME);
		Transaction.commit();

		new CreateBWInstanceService(bwSpecification.getOID(),"",USER_ID).call();

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
	public void checkInOneWorkItem() throws Exception {
		WorkItem workItem = getWorkItem(GOALWORKITEM_PRESCRIBE_ID);
		long workItemOID = workItem.getOID();
		AttributeInstance attributeInstance = getAttributeInstance(GOALWORKITEM_PRESCRIBE_INPUT_ATT1);
		setWorkItemArgumentValue(workItem, attributeInstance, GOALWORKITEM_PRESCRIBE_INPUT_VALUE1);
		
		new CheckInWorkItemService(workItemOID).call();
		boolean committed = false;
		try {
			Transaction.begin();

			assertEquals(WorkItemState.CHECKED_IN, workItem.getState());
			
			for (WorkItemArgument workItemArgument : workItem.getConstrainViolationWorkItemArguments()) {
				assertEquals(GOALWORKITEM_PRESCRIBE_INPUT_VALUE1, workItemArgument.getValue());
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
	
	private AttributeInstance getAttributeInstance(String name) throws BlendedWorkflowException {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		AttributeInstance attributeInstance = null;
		for (Attribute attribute : dataModelInstance.getAttributes()) {
			if (attribute.getName().equals(name)) {
				attributeInstance = attribute.getAttributeInstances().get(0); // FIXME:
			}
		}
		Transaction.commit();
		return attributeInstance;
	}
	
	private void setWorkItemArgumentValue(WorkItem workItem,AttributeInstance attributeInstance, String value) {
		Transaction.begin();
		for (WorkItemArgument workItemArgument : workItem.getConstrainViolationWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().getID().equals(attributeInstance.getID())) {
				workItemArgument.setValue(value);
			}
		}
		Transaction.commit();
	}

}