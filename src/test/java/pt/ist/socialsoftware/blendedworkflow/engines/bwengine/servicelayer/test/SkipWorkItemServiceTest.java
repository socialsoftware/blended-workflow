package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.PrintBWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;

public class SkipWorkItemServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String CREATE_BWINSTANCE_XML = "src/test/xml/MedicalEpisode/CreateBWInstanceInput.xml";
	private static String SKIP_COLLECTDATA_XML = "src/test/xml/MedicalEpisode/SkipCollectData.xml";
	private static String SKIP_PHYSICALEXAMINATION_XML = "src/test/xml/MedicalEpisode/SkipPhysicalExamination.xml";
	private static String SKIP_PRESCRIBE_XML = "src/test/xml/MedicalEpisode/SkipPrescribe.xml";
	private static String SKIP_OBSERVEPATIENT_XML = "src/test/xml/MedicalEpisode/SkipObservePatient.xml";
	private static String SKIP_WRITEMEDICALREPORT_XML = "src/test/xml/MedicalEpisode/SkipWriteMedicalReport.xml";
	private static String SKIP_DIAGNOSEPATIENT_XML = "src/test/xml/MedicalEpisode/SkipDiagnosePatient.xml";

	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String GOAL_WORKITEM_SK = "Prescribe.3";

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
		String createBWInstanceInputString = StringUtils.fileToString(CREATE_BWINSTANCE_XML);

		LoadBWSpecificationService loadBWSpecificationService = new LoadBWSpecificationService(dataModelString);
		CreateBWInstanceService createBWInstanceService = new CreateBWInstanceService(createBWInstanceInputString);
		try {
			loadBWSpecificationService.execute();
			createBWInstanceService.execute();

			// FIXME Change Workitem state to ENABLED because worklet is not verifying conditions
//			Transaction.begin();
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_SK);
//			workItem.setState(WorkItemState.ENABLED);
//			Transaction.commit();

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
				fail("SkipWorkItemServiceTest failed @TearDown.");
			}
		}
	}

	@Test
	public void skipOneWorkItem() {
		String skipWorkItemInputString = StringUtils.fileToString(SKIP_PRESCRIBE_XML);
		SkipWorkItemService skipWorkItemService = new SkipWorkItemService(skipWorkItemInputString);
		try {
			skipWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_SK);

//			assertEquals(WorkItemState.SKIPPED, workItem.getState());
			assertEquals(WorkItemState.COMPLETED, workItem.getState());

			for (AttributeInstance attributeInstance : workItem.getAttributeInstances()) {
				assertEquals(DataState.SKIPPED, attributeInstance.getState());
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

	@Test
	public void skipAllWorkItems() {
		String skipWorkItemInputString = StringUtils.fileToString(SKIP_COLLECTDATA_XML);
		SkipWorkItemService skipWorkItemService = new SkipWorkItemService(skipWorkItemInputString);
		String skipWorkItemInputString2 = StringUtils.fileToString(SKIP_PHYSICALEXAMINATION_XML);
		SkipWorkItemService skipWorkItemService2 = new SkipWorkItemService(skipWorkItemInputString2);
		String skipWorkItemInputString3 = StringUtils.fileToString(SKIP_PRESCRIBE_XML);
		SkipWorkItemService skipWorkItemService3 = new SkipWorkItemService(skipWorkItemInputString3);
		String skipWorkItemInputString4 = StringUtils.fileToString(SKIP_OBSERVEPATIENT_XML);
		SkipWorkItemService skipWorkItemService4 = new SkipWorkItemService(skipWorkItemInputString4);
		String skipWorkItemInputString5 = StringUtils.fileToString(SKIP_WRITEMEDICALREPORT_XML);
		SkipWorkItemService skipWorkItemService5 = new SkipWorkItemService(skipWorkItemInputString5);
		String skipWorkItemInputString6 = StringUtils.fileToString(SKIP_DIAGNOSEPATIENT_XML);
		SkipWorkItemService skipWorkItemService6 = new SkipWorkItemService(skipWorkItemInputString6);
		try {
			skipWorkItemService.execute();
			skipWorkItemService2.execute();
			skipWorkItemService3.execute();
			skipWorkItemService4.execute();
			skipWorkItemService5.execute();
			skipWorkItemService6.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		boolean committed = false;
		try {
			Transaction.begin();

			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);

			for (WorkItem workItem : bwInstance.getWorkItems()) {
				assertEquals(WorkItemState.COMPLETED, workItem.getState());
				for (AttributeInstance attributeInstance : workItem.getAttributeInstances()) {
					assertEquals(DataState.SKIPPED, attributeInstance.getState());
				}
			}

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