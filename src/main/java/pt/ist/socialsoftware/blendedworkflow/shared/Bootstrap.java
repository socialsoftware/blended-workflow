package pt.ist.socialsoftware.blendedworkflow.shared;

import static org.junit.Assert.fail;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.OrganizationalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

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
	public static void init() {
		FenixFramework.initialize(new Config() {{
			dbAlias = PropertiesManager.getProperty("dbAlias");
			domainModelPath = PropertiesManager.getProperty("dml.filename");
			repositoryType = RepositoryType.BERKELEYDB;
			rootClass = BlendedWorkflow.class;
		}});
		createOraganizationalManager();
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
		createOraganizationalManager();
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
		try {
			new LoadBWSpecificationService(bwXML, yawlXML).execute();
		} catch (BlendedWorkflowException e) {
			e.printStackTrace();
		}
	}
	
	public static void createOraganizationalManager() {
		Transaction.begin();
		new OrganizationalModel();
		Transaction.commit();
	}

}
