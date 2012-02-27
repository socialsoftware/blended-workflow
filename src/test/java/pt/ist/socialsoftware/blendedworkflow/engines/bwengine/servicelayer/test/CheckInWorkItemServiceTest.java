package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.PrintBWSpecification;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.StringUtils;
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

public class CheckInWorkItemServiceTest {

	private static String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisode.xml";
	private static String CREATE_BWINSTANCE_XML = "src/test/xml/MedicalEpisode/CreateBWInstanceInput.xml";
	private static String CHECKIN_COLLECTDATA_XML = "src/test/xml/MedicalEpisode/CheckInCollectData.xml";
	private static String CHECKIN_PHYSICALEXAMINATION_XML = "src/test/xml/MedicalEpisode/CheckInPhysicalExamination.xml";
	private static String CHECKIN_PRESCRIBE_XML = "src/test/xml/MedicalEpisode/CheckInPrescribe.xml";
	private static String CHECKIN_OBSERVEPATIENT_XML = "src/test/xml/MedicalEpisode/CheckInObservePatient.xml";
	private static String CHECKIN_WRITEMEDICALREPORT_XML = "src/test/xml/MedicalEpisode/CheckInWriteMedicalReport.xml";
	private static String CHECKIN_DIAGNOSEPATIENT_XML = "src/test/xml/MedicalEpisode/CheckInDiagnosePatient.xml";
	
	private static String BWSPECIFICATION_NAME = "Medical Appointment";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String GOAL_WORKITEM_CI_3 = "Prescribe.3";
	private static String GOAL_WORKITEM_CI_1 = "Collect Data.1";
	private static String GOAL_WORKITEM_CI_2 = "Physical Examination.2";

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
			
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI_1);
			workItem.setState(WorkItemState.ENABLED);
			workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI_2);
			workItem.setState(WorkItemState.ENABLED);
			workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI_3);
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
	public void checkInOneWorkItem() {
		String checkInWorkItemInputString = StringUtils.fileToString(CHECKIN_PRESCRIBE_XML);
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
			WorkItem workItem = bwInstance.getWorkItem(GOAL_WORKITEM_CI_3);
			
			assertEquals(WorkItemState.CHECKED_IN, workItem.getState());
			
			for (AttributeInstance attributeInstance : workItem.getContraintViolationAttributeInstances()) {
				assertEquals(DataState.DEFINED, attributeInstance.getState());
			}
			
//			PrintBWSpecification.workItemsWithAttributtes(BWSPECIFICATION_NAME);

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
	
//	@Test
//	public void checkInAllWorkItems() {
//		String checkInWorkItemInputString = StringUtils.fileToString(CHECKIN_COLLECTDATA_XML);
//		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(checkInWorkItemInputString);
//		String checkInWorkItemInputString2 = StringUtils.fileToString(CHECKIN_PHYSICALEXAMINATION_XML);
//		CheckInWorkItemService checkInWorkItemService2 = new CheckInWorkItemService(checkInWorkItemInputString2);
//		String checkInWorkItemInputString3 = StringUtils.fileToString(CHECKIN_PRESCRIBE_XML);
//		CheckInWorkItemService checkInWorkItemService3 = new CheckInWorkItemService(checkInWorkItemInputString3);
//		String checkInWorkItemInputString4 = StringUtils.fileToString(CHECKIN_OBSERVEPATIENT_XML);
//		CheckInWorkItemService checkInWorkItemService4 = new CheckInWorkItemService(checkInWorkItemInputString4);
//		String checkInWorkItemInputString5 = StringUtils.fileToString(CHECKIN_WRITEMEDICALREPORT_XML);
//		CheckInWorkItemService checkInWorkItemService5 = new CheckInWorkItemService(checkInWorkItemInputString5);
//		String checkInWorkItemInputString6 = StringUtils.fileToString(CHECKIN_DIAGNOSEPATIENT_XML);
//		CheckInWorkItemService checkInWorkItemService6 = new CheckInWorkItemService(checkInWorkItemInputString6);
//		try {
//			checkInWorkItemService.execute();
//			checkInWorkItemService2.execute();
//			checkInWorkItemService3.execute();
//			checkInWorkItemService4.execute();
//			checkInWorkItemService5.execute();
//			checkInWorkItemService6.execute();
//		} catch(BlendedWorkflowException e) {		
//			fail(e.getMessage());
//		}
//		boolean committed = false;
//		try {
//			Transaction.begin();
//
//			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//			BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
//
//			for (WorkItem workItem : bwInstance.getWorkItems()) {
//				assertEquals(WorkItemState.COMPLETED, workItem.getState());
//				for (AttributeInstance attributeInstance : workItem.getAttributeInstances()) {
//					assertEquals(DataState.DEFINED, attributeInstance.getState());
//				}
//			}
//
//			//			PrintBWSpecification.all(BWSPECIFICATION_NAME);
//
//			Transaction.commit();
//			committed = true;
//		} catch (BlendedWorkflowException e) {
//			fail(e.getMessage());
//		} finally {
//			if (!committed) {
//				Transaction.abort();
//			}
//		}
//	}
	
}