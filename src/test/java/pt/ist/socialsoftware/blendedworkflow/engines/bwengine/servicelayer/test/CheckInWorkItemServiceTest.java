package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

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
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;

public class CheckInWorkItemServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode.xml";
	private static String CREATE_BWINSTANCE_INPUT_DATA = "src/test/xml/MedicalEpisodeCreateBWInstanceInput.xml";
	private static String INPUT_DATA = "src/test/xml/MedicalEpisodeCheckInInput.xml";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String GOAL_WORKITEM_CI = "Prescribe.3";

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
			
			// FIXME Change Workitem state to ENABLED because worklet is not verifying conditions
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI);
			workItem.setState(WorkItemState.ENABLED);
			Transaction.commit();
			
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
				fail("CheckInWorkItemServiceTest failed @TearDown.");
			}
		}
	}

	@Test
	public void checkInWorkItemService() {
		String checkInWorkItemInputString = StringUtils.fileToString(INPUT_DATA);
		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(checkInWorkItemInputString);
		try {			
			checkInWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();
			
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI);

			assertEquals(WorkItemState.CHECKED_IN, workItem.getState());
			for (AttributeInstance attributeInstance : workItem.getAttributeInstances()) {
				assertEquals(DataState.DEFINED, attributeInstance.getState());
			}

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