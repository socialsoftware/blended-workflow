package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;
import org.junit.* ;
import static org.junit.Assert.* ;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.*;

public class RemoveBWSpecificationServiceTest {
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

	private static String EXISTING_ID = "VALID";
	private static String NON_EXISTING_ID = "INVALID";
	private static String EXISTING_NAME = "NAME";

	@Before
	public void setUp() {
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow bw = BlendedWorkflow.getInstance();
			try {
				bw.addBwSpecification(new BWSpecification (EXISTING_ID, EXISTING_NAME));
			} catch (BWSpecificationException e) {
			}
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("\nCould not add new bw specification: " + EXISTING_ID);
			}
		}
	}	

	@After
	public void tearDown() {
		boolean committed = false;
		try {
			Transaction.begin();
			BlendedWorkflow bw = BlendedWorkflow.getInstance();
			Set<BWSpecification> allBWSpecifications = bw.getBwSpecificationSet();
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

	/*@Test
	public void testRemoveInexistentContact() {
	    // Arrange
		RemoveContactService remService = new RemoveContactService(NON_EXISTING_CONTACT_NAME);
		String exceptionContactName = null;

		// Act
		try {
			remService.call();
			fail("Contact does not exist. Should have thrown an exception.");
		} catch(ContactDoesNotExistException e) {
			exceptionContactName = e.getContactName();
		}

	    //Assert
		assertEquals(NON_EXISTING_CONTACT_NAME, exceptionContactName);
		assertTrue("Existing contact should not have been removed from phonebook", checkContact(EXISTING_CONTACT_NAME));	
		assertFalse("Non-existing contact should not be in phonebook", checkContact(NON_EXISTING_CONTACT_NAME));	
	}*/


	@Test
	public void testRemoveExistentBWSpecification() {

		// Arrange		
		RemoveBWSpecificationService remService = new RemoveBWSpecificationService(EXISTING_ID, EXISTING_NAME);

		// Act
		try {
			remService.call();
		} catch(BWSpecificationException e) {
			fail("bw specification exist. Should not have thrown an exception.");
		}

		//check contact method com transaction - funciona!
		boolean committed = false;
		boolean res;
		try {
			Transaction.begin();
			BlendedWorkflow bw = BlendedWorkflow.getInstance();
			//res = phonebook.hasContact(EXISTING_CONTACT_NAME);
			res = bw.hasBwSpecification(new BWSpecification (EXISTING_ID, EXISTING_NAME));
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Could not check blended workflow");
			}
		}

		//check contact method sem transaction - nao funciona!
		/*boolean res;
		BlendedWorkflow bw = BlendedWorkflow.getInstance();
		res = bw.hasBwSpecification(new BWSpecification (EXISTING_ID, EXISTING_NAME));*/

		//Assert
		//assertFalse("Contact has not been removed from phonebook", res);	
	}
}