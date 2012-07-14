package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.*;

import junit.framework.JUnit4TestAdapter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(JMock.class)
public class CreateGoalInstanceServiceTest extends AbstractServiceTest {

	private static String YAWLCASE_ID = "yawlCaseID";
	private static String BWINSTANCE_ID = "Medical Appointment.1";
	private static String USER_ID = "BlendedWorkflow";
	private static String GOAL_NAME_1 = "Add Patient";

//	private static String GOALWORKITEM_ID_2 = "Add Patient.2";
//	private static String GOALWORKITEM_ID_3 = "Add Gender.3";
//	private static String GOALWORKITEM_ID_4 = "Add Address.4";
//	private static String GOALWORKITEM_ID_5 = "Add PhoneNumber.5";
//	private static String GOALWORKITEM_ID_6 = "Add Patient.6";
//	private static String GOALWORKITEM_ID_7 = "Add Gender.7";
//	private static String GOALWORKITEM_ID_8 = "Add Address.8";
//	private static String GOALWORKITEM_ID_9 = "Add PhoneNumber.9";
	
//	private static String ENTITY_1_NAME = "Patient";
//	private static String ENTITYINSTANCE_1_ID = "Patient.1";
//	private static String ENTITYINSTANCE_2_ID = "Patient.2";


	@Before
	public void setUp() throws Exception {
		context.checking(new Expectations() {
			{
				oneOf(yawlAdapter).launchCase(with(any(String.class))); will(returnValue(YAWLCASE_ID));
				oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
				oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
				allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
				allowing(workListManager).notifyPendingWorkItem(with(any(WorkItem.class)));
			}
		});

		BWSpecification bwSpecification = getBWSpecification(BWSPECIFICATION_NAME);
		new CreateBWInstanceService(bwSpecification.getOID(),"",USER_ID).call();
	}

	@Test
	public void createOneGoalInstance() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();

		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		
//		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
//		long entityInstance1OID = entityInstance1.getOID();
		Transaction.commit();

//		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, entityInstance1OID).call();
		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();

		boolean committed = false;
		try {
			Transaction.begin();
			
			assertEquals(1, 1);
			
//			GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_2);
//			GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_3);
//			GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_4);
//			GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_5);
//			
//			assertEquals(5, bwInstance.getWorkItemsCount());
//			for (WorkItem workItem : bwInstance.getWorkItems()) {
//				if (workItem.getClass().equals(GoalWorkItem.class)) {
//					assertEquals(WorkItemState.ACTIVATED, workItem.getState());
//				}
//			}
//
//			// GoalWorkItem1
//			assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
//			assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
//			assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
//			assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
//			for (AttributeInstance attributeInstance : goalWorkItem1.getInputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//			for (AttributeInstance attributeInstance : goalWorkItem1.getOutputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//
//			// GoalWorkItem2
//			assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
//			assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
//			assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
//			assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
//			for (AttributeInstance attributeInstance : goalWorkItem2.getInputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//			for (AttributeInstance attributeInstance : goalWorkItem2.getOutputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//
//			// GoalWorkItem3
//			assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
//			assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
//			assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
//			assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
//			for (AttributeInstance attributeInstance : goalWorkItem3.getInputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//			for (AttributeInstance attributeInstance : goalWorkItem3.getOutputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//			
//			// GoalWorkItem4
//			assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
//			assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
//			assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
//			assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
//			for (AttributeInstance attributeInstance : goalWorkItem4.getInputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//			for (AttributeInstance attributeInstance : goalWorkItem4.getOutputAttributeInstances()) {
//				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
//			}
//
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}
/*
	@Test
	public void createTwoDiferentGoalInstance() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();

		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long parentGoalOID = parentGoal.getOID();
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		long entityInstance1OID = entityInstance1.getOID();
		Transaction.commit();

		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, entityInstance1OID).call();
		new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();


		boolean committed = false;
		try {
			Transaction.begin();
			GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_2);
			GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_3);
			GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_4);
			GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_6);
			GoalWorkItem goalWorkItem5 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_7);
			GoalWorkItem goalWorkItem6 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_8);
			EntityInstance entityInstance2 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_2_ID);

			
			GoalWorkItem goalWorkItem7 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_5);
			GoalWorkItem goalWorkItem8 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_9);
			
			assertEquals(9, bwInstance.getWorkItemsCount());
			for (WorkItem workItem : bwInstance.getWorkItems()) {
				if (workItem.getClass().equals(GoalWorkItem.class)) 
					assertEquals(WorkItemState.ACTIVATED, workItem.getState());
			}

			// Test first call workItems with entityInstance1
			// GoalWorkItem1
			assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem1.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem1.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem2
			assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem2.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem2.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}

			// GoalWorkItem3
			assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem3.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem3.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem?
			assertEquals(0, goalWorkItem7.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem7.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem7.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem7.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem7.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem7.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// Test second call workItems with entityInstance2
			// GoalWorkItem4
			assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem5.getInputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem5.getOutputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}

			// GoalWorkItem5
			assertEquals(0, goalWorkItem5.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem5.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem5.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem5.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem5.getInputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem5.getOutputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}

			// GoalWorkItem6
			assertEquals(0, goalWorkItem6.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem6.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem6.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem6.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem6.getInputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem6.getOutputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem8
			assertEquals(0, goalWorkItem8.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem8.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem8.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem8.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem8.getInputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem8.getOutputAttributeInstances()) {
				assertEquals(entityInstance2, attributeInstance.getEntityInstance());
			}
			
			assertEquals(1, goalWorkItem1.getActivateConditionsCount());
			assertEquals(1, goalWorkItem2.getActivateConditionsCount());
			assertEquals(1, goalWorkItem3.getActivateConditionsCount());
			assertEquals(1, goalWorkItem4.getActivateConditionsCount());
			assertEquals(1, goalWorkItem5.getActivateConditionsCount());
			assertEquals(1, goalWorkItem6.getActivateConditionsCount());

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}

	@Test
	public void createTwoEqualGoalInstance() throws Exception {
		Transaction.begin();
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
		long bwInstanceOID = bwInstance.getOID();

		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
		long goalOID = parentGoal.getOID();
		
		EntityInstance entityInstance1 = bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
		long entityInstance1OID = entityInstance1.getOID();
		Transaction.commit();
		new CreateGoalInstanceService(bwInstanceOID, goalOID, entityInstance1OID).call();

		Transaction.begin();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(ENTITY_1_NAME);
		EntityInstance entityInstance = entity.getEntityInstance(ENTITYINSTANCE_1_ID);
		long entityInstanceOID = entityInstance.getOID();
		Transaction.commit();
		new CreateGoalInstanceService(bwInstanceOID, goalOID, entityInstanceOID).call();

		boolean committed = false;
		try {
			Transaction.begin();
			
			GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_2);
			GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_3);
			GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_4);
			GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance.getWorkItem(GOALWORKITEM_ID_5);
			
			assertEquals(5, bwInstance.getWorkItemsCount());
			for (WorkItem workItem : bwInstance.getWorkItems()) {
				if (workItem.getClass().equals(GoalWorkItem.class)) 
				assertEquals(WorkItemState.ACTIVATED, workItem.getState());
			}

			// GoalWorkItem1
			assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem1.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem1.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}

			// GoalWorkItem2
			assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem2.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem2.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}

			// GoalWorkItem3
			assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem3.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem3.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			
			// GoalWorkItem4
			assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
			assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
			assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
			assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
			for (AttributeInstance attributeInstance : goalWorkItem4.getInputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}
			for (AttributeInstance attributeInstance : goalWorkItem4.getOutputAttributeInstances()) {
				assertEquals(entityInstance1, attributeInstance.getEntityInstance());
			}

			Transaction.commit();
			committed = true;
		} finally {
			if (!committed) {
				Transaction.abort();
			}
		}
	}*/

	@Override
	protected void assertResults() throws BlendedWorkflowException {
		// TODO Auto-generated method stub
		
	}

}