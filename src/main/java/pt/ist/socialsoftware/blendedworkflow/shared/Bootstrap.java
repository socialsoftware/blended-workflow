package pt.ist.socialsoftware.blendedworkflow.shared;

import static org.junit.Assert.fail;

import java.util.Set;

import jvstm.Atomic;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class Bootstrap {

	private static boolean initialized = false;
	private static boolean notInitialized = true;

	public static void checkState(BWPresentation bwPresentation) {
		Config config = FenixFramework.getConfig();

		if (config != null) {
			initialized = true;
		}

		if (initialized) {
//			Transaction.begin();
//			BlendedWorkflow.getInstance().getBwManager().setBwPresentation(bwPresentation);
//			BlendedWorkflow.getInstance().getWorkListManager().setBwPresentation(bwPresentation);
//			BlendedWorkflow.getInstance().getBwManager().updateBWPresentation();
//			BlendedWorkflow.getInstance().getWorkListManager().updateBWPresentation();
//			Transaction.commit();
		}
		else {	
//			Bootstrap.init(bwPresentation);
//			System.out.println("not initialized");
//			Transaction.begin();
//			BlendedWorkflow.getInstance().getBwManager().setBwPresentation(bwPresentation);
//			BlendedWorkflow.getInstance().getWorkListManager().setBwPresentation(bwPresentation);
//			Transaction.commit();
		}
	}

	public static void init(BWPresentation bwPresentation) {
		FenixFramework.initialize(new Config() {{
			dbAlias = PropertiesManager.getProperty("dbAlias");
			domainModelPath = PropertiesManager.getProperty("dml.filename");
			repositoryType = RepositoryType.BERKELEYDB;
			rootClass = BlendedWorkflow.class;
		}});

		setBWPresentation(bwPresentation);
	}

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
	}

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

	public static void populate() {
		String bwXML = StringUtils.fileToString(PropertiesManager.getProperty("medical.xml"));
		String yawlXML = StringUtils.fileToString(PropertiesManager.getProperty("medical.yawl"));
		try {
			new LoadBWSpecificationService(bwXML, yawlXML).execute();
		} catch (BlendedWorkflowException e) {
			e.printStackTrace();
		}
	}

	@Atomic
	private static void setBWPresentation(BWPresentation bwPresentation) {
		BlendedWorkflow.getInstance().getBwManager().setBwPresentation(bwPresentation);
		BlendedWorkflow.getInstance().getWorkListManager().setBwPresentation(bwPresentation);
	}

}
