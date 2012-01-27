package pt.ist.socialsoftware.blendedworkflow.servicelayer;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.servicelayer.CheckInWorkItemService;

public class CheckInWorkItemServiceTest extends TestCaseService {
	
	private static String BW_SPECIFICATION_ID = "1234";
	private static String BW_INSTANCE_ID = "567";
	
	public CheckInWorkItemServiceTest(String msg) {
		super(msg);
	}
	
	public CheckInWorkItemServiceTest() {
		super();
	}
	
	public void setUp() {
    	boolean committed = false;
    	try {
    		Transaction.begin();
    		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
//    		try {
    		    BWSpecification bwSpecification = new BWSpecification(BW_SPECIFICATION_ID, "Medical Appointment")
    			blendedWorkflow.addBwSpecification(bwSpecification);
    			bwSpecification.addBwInstance(new BWInstance(BW_INSTANCE_ID, "John Medical Appointment"))
//    		} catch (NameAlreadyExistsException nae) {
//    		}
    		Transaction.commit();
    		committed = true;
    	} finally {
    		if (!committed) {
    			Transaction.abort();
    			fail("Could not add new bw specification: " + name);
    		}
    	}
	}	
  
	protected void tearDown() {
		cleanBlendedWorkflow();
	}
  
	public void testCheckInWorkItemService() {
	    // Arrange
		CheckInWorkItemService checkInWorkItemService = new CheckInWorkItemService(BW_INSTANCE_ID,"WorkItemID");
		String exceptionContactName = null;
		
		// Act
		//try {
			checkInWorkItemService.execute();
			fail("Contact does not exist. Should have thrown an exception.");
		//} catch(ContactDoesNotExistException e) {
		//	exceptionContactName = e.getContactName();
		//}

	    //Assert
//		assertEquals(NON_EXISTING_CONTACT_NAME, exceptionContactName);
		assertTrue("Existing contact should not have been removed from phonebook", checkInWorkItemService.getBwInstance().getId());	
		assertFalse("Non-existing contact should not be in phonebook", checkInWorkItemService.getBwInstance(NON_EXISTING_CONTACT_NAME));	
	}
  
//	public void testRemoveExistentContact() {
//	    // Arrange		
//		RemoveContactService remService = new RemoveContactService(EXISTING_CONTACT_NAME);
//
//		// Act
//		try {
//			remService.call();
//		} catch(ContactDoesNotExistException e) {
//			fail("contact exist. Should not have thrown an exception.");
//		}
//
//	    //Assert
//		assertFalse("Contact has not been removed from phonebook", checkContact(EXISTING_CONTACT_NAME));	
//  }
}