package pt.ist.socialsoftware.blendedworkflow.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.OrganizationalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.execution.LoadBWSpecificationService;

public class Bootstrap {

	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

	/**
	 * Populate the DataBase with the MedicalEpisode Specification.
	 */
	public static void populate() {
		String bwXML = StringUtils.fileToString(BWPropertiesManager.getProperty("medical.xml"));

		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		LoadBWSpecificationService service = new LoadBWSpecificationService(bwXML);
		bwExecutorService.runTask(service);
	}

	public static void createOraganizationalModel() {
		new OrganizationalModel();

		// AdminRole
		String name = "Admin";
		String description = "Administrator has acess to all features.";
		Boolean bwManagerAcess = true;
		Boolean organizationalManagerAcess = true;
		Boolean worklistManagerAcess = true;
		Role admin = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);

		// ManagerRole
		name = "Manager";
		description = "Manager has acess to the BWManager and OrganizationalManager.";
		bwManagerAcess = true;
		organizationalManagerAcess = true;
		worklistManagerAcess = false;
		Role manager = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);

		// UserRole
		name = "User";
		description = "User has acess ontly to the WorklistManager.";
		bwManagerAcess = false;
		organizationalManagerAcess = false;
		worklistManagerAcess = true;
		Role user = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);

		// User:Default
		String userName = "Blended Workflow";
		String userID = "BlendedWorkflow";
		String userPassword = "bw";
		String userNotes = "The default user of the system.";
		User blenededWorkflow = new User(userName, userID, userPassword, userNotes);
		blenededWorkflow.setRole(admin);

		// User:Davide
		userName = "Doctor";
		userID = "Doctor";
		userPassword = "12345";
		userNotes = "Admin of the system.";
		User davide = new User(userName, userID, userPassword, userNotes);
		davide.setRole(admin);

		// User:Manager
		userName = "Manager";
		userID = "Manager";
		userPassword = "12345";
		userNotes = "Test Manager.";
		User testManager = new User(userName, userID, userPassword, userNotes);
		testManager.setRole(manager);

		// User:User
		userName = "User";
		userID = "User";
		userPassword = "12345";
		userNotes = "Test User.";
		User testUser = new User(userName, userID, userPassword, userNotes);
		testUser.setRole(user);
	}

}
