package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalServiceTest {

	private static String BW_SPECIFICATION_ID = "BWSpec-1";
	private static String BW_SPECIFICATION_NAME = "Medical Appointment";
	private static String BW_INSTANCE_ID = "BWInstance-1.1";
	private static String BW_INSTANCE_NAME = "John Medical Appointment";
	private static String DATA_MODEL_URI = "DATAMODEL-1";
	private static String GOAL_ID = "GOAL-1";
	private static String GOAL_NAME = "Diagnose";
	private static String ENTITY_NAME = "Person";
	private static String ATTRIBUTE_NAME = "Age";

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

			// define vars
			BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
			BWSpecification bwSpecification = new BWSpecification(BW_SPECIFICATION_ID, BW_SPECIFICATION_NAME);
			BWInstance bwInstance = new BWInstance(BW_INSTANCE_ID, BW_INSTANCE_NAME);
			DataModel dataModel = new DataModel(DATA_MODEL_URI);

			// relations
			blendedWorkflow.addBwSpecification(bwSpecification);
			bwSpecification.addBwInstance(bwInstance);
			bwSpecification.setDataModel(dataModel);

			Transaction.commit();
			committed = true;
		} 
		catch (Exception e) {
			fail(e.getMessage());
		}
		finally {
			if (!committed) 
			{
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

	private BWInstance getBWInstance(String bwInstanceID) throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		return blendedWorkflow.getBWInstance(bwInstanceID);
	}

	@Test
	public void changedWorkItemState() {
		// Arrange
		CreateGoalService checkInWorkItemService = new CreateGoalService(BW_INSTANCE_ID, GOAL_ID, GOAL_NAME, ENTITY_NAME, ATTRIBUTE_NAME);
		// Act
		try {
			checkInWorkItemService.execute();
		} catch(BlendedWorkflowException e) {		
			fail(e.getMessage());
		}
		// Assert
		boolean committed = false;
		try {
			Transaction.begin();
			BWInstance bwInstance = getBWInstance(BW_INSTANCE_ID);
			BWSpecification bwSpecification = bwInstance.getBwSpecification();
			DataModel dataModel = bwSpecification.getDataModel();
			for (Entity ent : dataModel.getEntity()){
				System.out.println("ID: " + ent.getId() + " |NAME: " + ent.getName());
			}
			for (Attribute att : dataModel.getAttribute()){
				System.out.println("ID: " + att.getId() + " |NAME: " + att.getName());
			}	
			assertEquals(dataModel.getEntityCount(),1);		
			assertEquals(dataModel.getAttributeCount(),1);
			Transaction.commit();
			committed = true;
		} catch (BlendedWorkflowException e) {
			fail(e.getMessage());
		} finally {
			if (!committed) {
				Transaction.abort();
				fail("Could not clean blended workflow");
			}
		}
	}

}
