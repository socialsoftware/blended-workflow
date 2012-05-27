package pt.ist.socialsoftware.blendedworkflow.shared;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.OrganizationalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;

public class Bootstrap {

	private static boolean notInitialized = true;
	
	/**
	 * Check if the database is initialized.
	 * @return
	 */
	public static Boolean isInitialized() {
		Config config = FenixFramework.getConfig();
		if (config == null) {
			return false;
		} else 
			return true;
	}
	
	/**
	 * Initializes the Database.
	 */
	// PropertiesManager.getProperty("dbAlias");
	public static void init() {
		FenixFramework.initialize(new Config() {{
			dbAlias = "C:/Users/User/Desktop/bwdb/" + (((Calendar.getInstance().getTime() +"").replaceAll(" ", "")).replaceAll(":", "")); // FIXME: Test proposes only.
			domainModelPath = PropertiesManager.getProperty("dml.filename");
			repositoryType = RepositoryType.BERKELEYDB;
			rootClass = BlendedWorkflow.class;
		}});
		createOraganizationalModel();
	}

	/**
	 * Initializes the Test Database.
	 */
	public static void initTestDB() {
		if (notInitialized)  {
			FenixFramework.initialize(new Config() {{
				dbAlias="test-db";
				domainModelPath="src/main/dml/blendedworkflow.dml";
				repositoryType=RepositoryType.BERKELEYDB;
				rootClass=BlendedWorkflow.class;
			}});
		}
		notInitialized = false;
		createOraganizationalModel();
	}

	/**
	 * Clean the created Database.
	 */
	public static void clean() {
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
				fail("Clean database.");
			}
		}
	}

	/**
	 * Populate the DataBase with the MedicalEpisode Specification.
	 */
	public static void populate() {
		String bwXML = StringUtils.fileToString(PropertiesManager.getProperty("medical.xml"));
		String yawlXML = StringUtils.fileToString(PropertiesManager.getProperty("medical.yawl"));
		
		Transaction.begin();
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		LoadBWSpecificationService service = new LoadBWSpecificationService(bwXML, yawlXML);
		bwExecutorService.runTask(service);
		Transaction.commit();
	}
	
	public static void createOraganizationalModel() {
		Transaction.begin();
		new OrganizationalModel();
		
		//AdminRole
		String name = "Admin";
		String description = "Administrator has acess to all features.";
		Boolean bwManagerAcess = true;
		Boolean organizationalManagerAcess = true;
		Boolean worklistManagerAcess = true;
		Role admin = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);
		
		//ManagerRole
		name = "Manager";
		description = "Manager has acess to the BWManager and OrganizationalManager.";
		bwManagerAcess = true;
		organizationalManagerAcess = true;
		worklistManagerAcess = false;
		Role manager = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);
		
		//UserRole
		name = "User";
		description = "User has acess ontly to the WorklistManager.";
		bwManagerAcess = false;
		organizationalManagerAcess = false;
		worklistManagerAcess = true;
		Role user = new Role(name, description, bwManagerAcess, organizationalManagerAcess, worklistManagerAcess);
		
		//User:Default
		String userName = "Blended Workflow";
		String userID = "BlendedWorkflow";
		String userPassword = "bw";
		String userNotes = "The default user of the system.";
		User blenededWorkflow = new User(userName, userID, userPassword, userNotes);
		blenededWorkflow.setRole(admin);
		
		//User:Davide
		userName = "Doctor";
		userID = "Doctor";
		userPassword = "12345";
		userNotes = "Admin of the system.";
		User davide = new User(userName, userID, userPassword, userNotes);
		davide.setRole(admin);
		
		//User:Manager
		userName = "Manager";
		userID = "Manager";
		userPassword = "12345";
		userNotes = "Test Manager.";
		User testManager = new User(userName, userID, userPassword, userNotes);
		testManager.setRole(manager);
		
		//User:User
		userName = "User";
		userID = "User";
		userPassword = "12345";
		userNotes = "Test User.";
		User testUser = new User(userName, userID, userPassword, userNotes);
		testUser.setRole(user);
		
		Transaction.commit();
	}

}
