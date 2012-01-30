package pt.ist.socialsoftware.blendedworkflow.servicelayer.test;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.domain.*;
import pt.ist.socialsoftware.blendedworkflow.exception.*;

import pt.ist.socialsoftware.blendedworkflow.blendedworkflowengine.servicelayer.CheckInWorkItemService;

public class CheckInWorkItemServiceTest {

	private static String BW_SPECIFICATION_ID = "BWSpec-1";
	private static String BW_INSTANCE_ID = "BWInstance-1.1";

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
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			try {
				BWSpecification bwSpecification = new BWSpecification(BW_SPECIFICATION_ID, "Medical Appointment");
				blendedWorkflow.addBwSpecification(bwSpecification);
				BWInstance bwInstance = new BWInstance(BW_INSTANCE_ID, "John Medical Appointment");
				//bwSpecification.addBwInstance(bwInstance);
			} catch (BWSpecificationException bwiae) {
			}
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Could not add new bw specification: " + BW_SPECIFICATION_ID);
			}
		}
	}

	@After
	public void tearDown() {
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			Set<BWSpecification> allBWSpecifications = blendedWorkflow.getBwSpecificationSet();
			allBWSpecifications.clear();
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Could not clean blended workflow");
			}
		}
	}

	@Test
	public void testExistentBWSpecification() {
		// Arrange
		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(BW_INSTANCE_ID,"WorkItemID");
		String exceptionBWSpecification = null;

		// Act
		try {
			checkInWorkItemService.execute();
			fail("BWSpecification does not exist. Should have thrown an exception.");
		} catch(BWSpecificationException e) {
			exceptionBWSpecification = e.getBWSpecification();
		}

		//Assert
		assertEquals("Existing bw instance", checkInWorkItemService.getBwInstance().getId());	
		//assertEquals(NON_EXISTING_CONTACT_NAME, exceptionContactName);
		//assertTrue("Existing contact should not have been removed from phonebook", checkContact(EXISTING_CONTACT_NAME));	
		//assertFalse("Non-existing contact should not be in phonebook", checkContact(NON_EXISTING_CONTACT_NAME));	
	}

}