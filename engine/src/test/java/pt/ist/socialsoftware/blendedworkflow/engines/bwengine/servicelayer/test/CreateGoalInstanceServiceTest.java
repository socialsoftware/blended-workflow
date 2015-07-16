package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.AbstractServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateGoalInstanceService;

@RunWith(JMock.class)
public class CreateGoalInstanceServiceTest extends AbstractServiceTest {

    private static final String BWINSTANCE_ID = "Medical Appointment.1";

    private static final String GOAL_NAME_1 = "Obtain Patient Data";
    private static final String GOAL_NAME_2 = "Write Medical Prescription";

    @Before
    public void setUp() throws Exception {
        initializeSpecification();
        initializeBWInstance();
    }

    private void setUpData(DataModelInstance dataModelInstance,
            Boolean checkInValue) {
        Transaction.begin();
        // Episode.1
        BWEntity episodeType = dataModelInstance.getEntity("Episode").get();
        EntityInstance episodeOne = new EntityInstance(episodeType);
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Patient").get()
                        .getEntityInstancesSet());
        EntityInstance myPatient = entityInstances.get(0);
        new RelationInstance(
                dataModelInstance.getRelation("Patient has Episodes"),
                myPatient, episodeOne, myPatient.getNewRelationInstanceID());
        AttributeInstance episodeOneNumber = new AttributeInstance(
                episodeType.getAttribute("Number").orElse(null), episodeOne);
        AttributeInstance episodeOneReserveDate = new AttributeInstance(
                episodeType.getAttribute("Reserve Date").orElse(null),
                episodeOne);
        AttributeInstance episodeOneCheckIn = new AttributeInstance(
                episodeType.getAttribute("CheckIn").orElse(null), episodeOne);
        episodeOneNumber.setValue("1");
        episodeOneReserveDate.setValue("17/07/2012");
        episodeOneCheckIn.setValue(checkInValue.toString());
        Transaction.commit();
    }

    @Ignore
    @Test
    public void createOneGoalInstanceWithTrueActivateCondition()
            throws Exception {
        Transaction.begin();
        BWInstance bwInstance = BlendedWorkflow.getInstance()
                .getBWInstance(BWINSTANCE_ID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        AchieveGoal goal = goalModelInstance.getGoal(GOAL_NAME_1);
        Transaction.commit();

        setUpData(dataModelInstance, true);

        Transaction.begin();
        HashMap<String, String> entitiesOID = new HashMap<String, String>();
        String episode = dataModelInstance.getEntity("Episode").get()
                .getExternalId();
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Episode").get()
                        .getEntityInstancesSet());
        String episodeOne = entityInstances.get(0).getExternalId();
        String patientData = dataModelInstance.getEntity("Patient Data").get()
                .getExternalId();
        String patientDataNew = null;
        entitiesOID.put(episode, episodeOne);
        entitiesOID.put(patientData, patientDataNew);
        Transaction.commit();

        new CreateGoalInstanceService(bwInstance.getExternalId(),
                goal.getExternalId(), null, null, entitiesOID).call();

        Transaction.begin();
        GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Patient Data.1");
        GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Physical Data.2");
        GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Medical Data.3");
        GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance
                .getWorkItem("Measure Blood Pressure.4");
        Transaction.commit();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(GoalState.ACTIVATED, goalWorkItem1.getState());
            assertEquals(GoalState.ENABLED, goalWorkItem2.getState());
            assertEquals(GoalState.ACTIVATED, goalWorkItem3.getState());
            assertEquals(GoalState.ENABLED, goalWorkItem4.getState());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

    @Ignore
    @Test
    public void createOneGoalInstanceWithFalseActivateCondition()
            throws Exception {
        Transaction.begin();
        BWInstance bwInstance = BlendedWorkflow.getInstance()
                .getBWInstance(BWINSTANCE_ID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        AchieveGoal goal = goalModelInstance.getGoal(GOAL_NAME_1);
        Transaction.commit();

        setUpData(dataModelInstance, false);

        Transaction.begin();
        HashMap<String, String> entitiesOID = new HashMap<String, String>();
        String episode = dataModelInstance.getEntity("Episode").get()
                .getExternalId();
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Episode").get()
                        .getEntityInstancesSet());
        String episodeOne = entityInstances.get(0).getExternalId();
        String patientData = dataModelInstance.getEntity("Patient Data").get()
                .getExternalId();
        String patientDataNew = null;
        entitiesOID.put(episode, episodeOne);
        entitiesOID.put(patientData, patientDataNew);

        Transaction.commit();

        new CreateGoalInstanceService(bwInstance.getExternalId(),
                goal.getExternalId(), null, null, entitiesOID).call();

        Transaction.begin();
        GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Patient Data.1");
        GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Physical Data.2");
        GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Medical Data.3");
        GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance
                .getWorkItem("Measure Blood Pressure.4");
        Transaction.commit();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(1, 1);
            assertEquals(GoalState.PRE_GOAL, goalWorkItem1.getState());
            assertEquals(GoalState.NEW, goalWorkItem2.getState());
            assertEquals(GoalState.NEW, goalWorkItem3.getState());
            assertEquals(GoalState.NEW, goalWorkItem4.getState());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

    @Ignore
    @Test
    public void createOneGoalInstanceWithoutDisablingConditions()
            throws Exception {
        Transaction.begin();
        BWInstance bwInstance = BlendedWorkflow.getInstance()
                .getBWInstance(BWINSTANCE_ID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        AchieveGoal goal = goalModelInstance.getGoal(GOAL_NAME_1);
        Transaction.commit();

        setUpData(dataModelInstance, true);

        setUpData(dataModelInstance, false);

        Transaction.begin();
        HashMap<String, String> entitiesOID = new HashMap<String, String>();
        String episode = dataModelInstance.getEntity("Episode").get()
                .getExternalId();
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Episode").get()
                        .getEntityInstancesSet());
        String episodeOne = entityInstances.get(0).getExternalId();
        String patientData = dataModelInstance.getEntity("Patient Data").get()
                .getExternalId();
        String patientDataNew = null;
        entitiesOID.put(episode, episodeOne);
        entitiesOID.put(patientData, patientDataNew);

        Transaction.commit();

        new CreateGoalInstanceService(bwInstance.getExternalId(),
                goal.getExternalId(), null, null, entitiesOID).call();

        Transaction.begin();
        GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Patient Data.1");
        GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Physical Data.2");
        GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Medical Data.3");
        GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance
                .getWorkItem("Measure Blood Pressure.4");
        Transaction.commit();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(1, goalWorkItem1.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem2.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem3.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem4.getActivateConditionsSet().size());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

    @Ignore
    @Test
    public void createOneGoalInstanceDisablingConditions() throws Exception {
        Transaction.begin();
        BWInstance bwInstance = BlendedWorkflow.getInstance()
                .getBWInstance(BWINSTANCE_ID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        AchieveGoal goal = goalModelInstance.getGoal(GOAL_NAME_1);
        Transaction.commit();

        setUpData(dataModelInstance, true);

        Transaction.begin();
        HashMap<String, String> entitiesOID = new HashMap<String, String>();
        String episode = dataModelInstance.getEntity("Episode").get()
                .getExternalId();
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Episode").get()
                        .getEntityInstancesSet());
        String episodeOne = entityInstances.get(0).getExternalId();
        String patientData = dataModelInstance.getEntity("Patient Data").get()
                .getExternalId();
        String patientDataNew = null;
        entitiesOID.put(episode, episodeOne);
        entitiesOID.put(patientData, patientDataNew);

        Transaction.commit();

        new CreateGoalInstanceService(bwInstance.getExternalId(),
                goal.getExternalId(), new HashSet<String>(),
                new HashSet<String>(), entitiesOID).call();

        Transaction.begin();
        GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Patient Data.1");
        GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Physical Data.2");
        GoalWorkItem goalWorkItem3 = (GoalWorkItem) bwInstance
                .getWorkItem("Obtain Medical Data.3");
        GoalWorkItem goalWorkItem4 = (GoalWorkItem) bwInstance
                .getWorkItem("Measure Blood Pressure.4");
        Transaction.commit();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(0, goalWorkItem1.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem2.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem3.getActivateConditionsSet().size());
            assertEquals(1, goalWorkItem4.getActivateConditionsSet().size());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

    @Ignore
    @Test
    public void createOneGoalInstanceSubGoalsWithDifferentContext()
            throws Exception {
        Transaction.begin();
        BWInstance bwInstance = BlendedWorkflow.getInstance()
                .getBWInstance(BWINSTANCE_ID);
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
        AchieveGoal goal = goalModelInstance.getGoal(GOAL_NAME_2);
        Transaction.commit();

        setUpData(dataModelInstance, true);

        Transaction.begin();
        HashMap<String, String> entitiesOID = new HashMap<String, String>();
        String episode = dataModelInstance.getEntity("Episode").get()
                .getExternalId();
        List<EntityInstance> entityInstances = new ArrayList<EntityInstance>(
                dataModelInstance.getEntity("Episode").get()
                        .getEntityInstancesSet());
        String episodeOne = entityInstances.get(0).getExternalId();
        String medicalPrescription = dataModelInstance
                .getEntity("Medical Prescription").get().getExternalId();
        String medicalPrescriptionNew = null;
        String prescriptionMedication = dataModelInstance
                .getEntity("Prescription Medication").get().getExternalId();
        String prescriptionMedicationNew = null;
        entitiesOID.put(episode, episodeOne);
        entitiesOID.put(medicalPrescription, medicalPrescriptionNew);
        entitiesOID.put(prescriptionMedication, prescriptionMedicationNew);

        Transaction.commit();

        new CreateGoalInstanceService(bwInstance.getExternalId(),
                goal.getExternalId(), null, null, entitiesOID).call();

        Transaction.begin();
        GoalWorkItem goalWorkItem1 = (GoalWorkItem) bwInstance
                .getWorkItem("Write Medical Prescription.1");
        GoalWorkItem goalWorkItem2 = (GoalWorkItem) bwInstance
                .getWorkItem("Add Prescription Medication.2");
        Transaction.commit();

        boolean committed = false;
        try {
            Transaction.begin();

            assertEquals(GoalState.ACTIVATED, goalWorkItem1.getState());
            assertEquals(GoalState.ENABLED, goalWorkItem2.getState());

            Transaction.commit();
            committed = true;
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (!committed) {
                Transaction.abort();
            }
        }
    }

}

// private static String BWINSTANCE_ID = "Medical Appointment.1";
// private static String GOAL_NAME_1 = "Add Patient";
// private static String GOALWORKITEM_ID_2 = "Add Patient.2";
// private static String GOALWORKITEM_ID_3 = "Add Gender.3";
// private static String GOALWORKITEM_ID_4 = "Add Address.4";
// private static String GOALWORKITEM_ID_5 = "Add PhoneNumber.5";
// private static String GOALWORKITEM_ID_6 = "Add Patient.6";
// private static String GOALWORKITEM_ID_7 = "Add Gender.7";
// private static String GOALWORKITEM_ID_8 = "Add Address.8";
// private static String GOALWORKITEM_ID_9 = "Add PhoneNumber.9";
// private static String ENTITY_1_NAME = "Patient";
// private static String ENTITYINSTANCE_1_ID = "Patient.1";
// private static String ENTITYINSTANCE_2_ID = "Patient.2";
//
// @Before
// public void setUp() throws Exception {
// context.checking(new Expectations() {
// {
// oneOf(yawlAdapter).launchCase(with(any(String.class)));
// will(returnValue(YAWLCASE_ID));
// oneOf(bwManager).notifyCreatedBWInstance(with(any(BWInstance.class)));
// oneOf(bwManager).notifyLoadedBWSpecification(with(any(BWSpecification.class)));
// allowing(workListManager).notifySkippedWorkItem(with(any(WorkItem.class)));
// allowing(workListManager).notifyEnabledWorkItem(with(any(WorkItem.class)));
// allowing(workListManager).notifyPendingWorkItem(with(any(WorkItem.class)));
// }
// });
//
// BWSpecification bwSpecification = getBWSpecification(BWSPECIFICATION_NAME);
// new
// CreateBWInstanceService(bwSpecification.getExternalId(),"",USER_ID).call();
// }
//
// @Test
// public void createOneGoalInstance() throws Exception {
// Transaction.begin();
// BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
// BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
// String bwInstanceOID = bwInstance.getExternalId();
//
// GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
// AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
// String parentGoalOID = parentGoal.getExternalId();
//
// // EntityInstance entityInstance1 =
// bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
// // String entityInstance1OID = entityInstance1.getExternalId();
// Transaction.commit();
//
// // new CreateGoalInstanceService(bwInstanceOID, parentGoalOID,
// entityInstance1OID).call();
// new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
//
// boolean committed = false;
// try {
// Transaction.begin();
//
// assertEquals(1, 1);
//
// // GoalWorkItem goalWorkItem1 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_2);
// // GoalWorkItem goalWorkItem2 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_3);
// // GoalWorkItem goalWorkItem3 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_4);
// // GoalWorkItem goalWorkItem4 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_5);
// //
// // assertEquals(5, bwInstance.getWorkItemsCount());
// // for (WorkItem workItem : bwInstance.getWorkItems()) {
// // if (workItem.getClass().equals(GoalWorkItem.class)) {
// // assertEquals(WorkItemState.ACTIVATED, workItem.getState());
// // }
// // }
// //
// // // GoalWorkItem1
// // assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
// // assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
// // assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
// // assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
// // for (AttributeInstance attributeInstance :
// goalWorkItem1.getInputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// // for (AttributeInstance attributeInstance :
// goalWorkItem1.getOutputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// //
// // // GoalWorkItem2
// // assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
// // assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
// // assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
// // assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
// // for (AttributeInstance attributeInstance :
// goalWorkItem2.getInputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// // for (AttributeInstance attributeInstance :
// goalWorkItem2.getOutputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// //
// // // GoalWorkItem3
// // assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
// // assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
// // assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
// // assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
// // for (AttributeInstance attributeInstance :
// goalWorkItem3.getInputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// // for (AttributeInstance attributeInstance :
// goalWorkItem3.getOutputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// //
// // // GoalWorkItem4
// // assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
// // assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
// // assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
// // assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
// // for (AttributeInstance attributeInstance :
// goalWorkItem4.getInputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// // for (AttributeInstance attributeInstance :
// goalWorkItem4.getOutputAttributeInstances()) {
// // assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// // }
// //
// Transaction.commit();
// committed = true;
// } finally {
// if (!committed) {
// Transaction.abort();
// }
// }
// }
// /*
// @Test
// public void createTwoDiferentGoalInstance() throws Exception {
// Transaction.begin();
// BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
// BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
// String bwInstanceOID = bwInstance.getExternalId();
//
// GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
// AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
// String parentGoalOID = parentGoal.getExternalId();
// EntityInstance entityInstance1 =
// bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
// String entityInstance1OID = entityInstance1.getExternalId();
// Transaction.commit();
//
// new CreateGoalInstanceService(bwInstanceOID, parentGoalOID,
// entityInstance1OID).call();
// new CreateGoalInstanceService(bwInstanceOID, parentGoalOID, null).call();
//
//
// boolean committed = false;
// try {
// Transaction.begin();
// GoalWorkItem goalWorkItem1 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_2);
// GoalWorkItem goalWorkItem2 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_3);
// GoalWorkItem goalWorkItem3 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_4);
// GoalWorkItem goalWorkItem4 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_6);
// GoalWorkItem goalWorkItem5 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_7);
// GoalWorkItem goalWorkItem6 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_8);
// EntityInstance entityInstance2 =
// bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_2_ID);
//
//
// GoalWorkItem goalWorkItem7 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_5);
// GoalWorkItem goalWorkItem8 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_9);
//
// assertEquals(9, bwInstance.getWorkItemsCount());
// for (WorkItem workItem : bwInstance.getWorkItems()) {
// if (workItem.getClass().equals(GoalWorkItem.class))
// assertEquals(WorkItemState.ACTIVATED, workItem.getState());
// }
//
// // Test first call workItems with entityInstance1
// // GoalWorkItem1
// assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem1.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem1.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem2
// assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem2.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem2.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem3
// assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem3.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem3.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem?
// assertEquals(0, goalWorkItem7.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem7.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem7.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem7.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem7.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem7.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // Test second call workItems with entityInstance2
// // GoalWorkItem4
// assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem5.getInputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem5.getOutputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem5
// assertEquals(0, goalWorkItem5.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem5.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem5.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem5.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem5.getInputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem5.getOutputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem6
// assertEquals(0, goalWorkItem6.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem6.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem6.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem6.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem6.getInputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem6.getOutputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem8
// assertEquals(0, goalWorkItem8.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem8.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem8.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem8.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem8.getInputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem8.getOutputAttributeInstances()) {
// assertEquals(entityInstance2, attributeInstance.getEntityInstance());
// }
//
// assertEquals(1, goalWorkItem1.getActivateConditionsCount());
// assertEquals(1, goalWorkItem2.getActivateConditionsCount());
// assertEquals(1, goalWorkItem3.getActivateConditionsCount());
// assertEquals(1, goalWorkItem4.getActivateConditionsCount());
// assertEquals(1, goalWorkItem5.getActivateConditionsCount());
// assertEquals(1, goalWorkItem6.getActivateConditionsCount());
//
// Transaction.commit();
// committed = true;
// } finally {
// if (!committed) {
// Transaction.abort();
// }
// }
// }
//
// @Test
// public void createTwoEqualGoalInstance() throws Exception {
// Transaction.begin();
// BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
// BWInstance bwInstance = blendedWorkflow.getBWInstance(BWINSTANCE_ID);
// String bwInstanceOID = bwInstance.getExternalId();
//
// GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
// AchieveGoal parentGoal = goalModelInstance.getGoal(GOAL_NAME_1);
// String goalOID = parentGoal.getExternalId();
//
// EntityInstance entityInstance1 =
// bwInstance.getDataModelInstance().getEntity(ENTITY_1_NAME).getEntityInstance(ENTITYINSTANCE_1_ID);
// String entityInstance1OID = entityInstance1.getExternalId();
// Transaction.commit();
// new CreateGoalInstanceService(bwInstanceOID, goalOID,
// entityInstance1OID).call();
//
// Transaction.begin();
// DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
// Entity entity = dataModelInstance.getEntity(ENTITY_1_NAME);
// EntityInstance entityInstance =
// entity.getEntityInstance(ENTITYINSTANCE_1_ID);
// String entityInstanceOID = entityInstance.getExternalId();
// Transaction.commit();
// new CreateGoalInstanceService(bwInstanceOID, goalOID,
// entityInstanceOID).call();
//
// boolean committed = false;
// try {
// Transaction.begin();
//
// GoalWorkItem goalWorkItem1 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_2);
// GoalWorkItem goalWorkItem2 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_3);
// GoalWorkItem goalWorkItem3 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_4);
// GoalWorkItem goalWorkItem4 = (GoalWorkItem)
// bwInstance.getWorkItem(GOALWORKITEM_ID_5);
//
// assertEquals(5, bwInstance.getWorkItemsCount());
// for (WorkItem workItem : bwInstance.getWorkItems()) {
// if (workItem.getClass().equals(GoalWorkItem.class))
// assertEquals(WorkItemState.ACTIVATED, workItem.getState());
// }
//
// // GoalWorkItem1
// assertEquals(0, goalWorkItem1.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem1.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem1.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem1.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem1.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem1.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem2
// assertEquals(0, goalWorkItem2.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem2.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem2.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem2.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem2.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem2.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem3
// assertEquals(0, goalWorkItem3.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem3.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem3.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem3.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem3.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem3.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// // GoalWorkItem4
// assertEquals(0, goalWorkItem4.getInputAttributeInstancesCount());
// assertEquals(0, goalWorkItem4.getInputWorkItemArgumentsCount());
// assertEquals(1, goalWorkItem4.getOutputAttributeInstancesCount());
// assertEquals(1, goalWorkItem4.getOutputWorkItemArgumentsCount());
// for (AttributeInstance attributeInstance :
// goalWorkItem4.getInputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
// for (AttributeInstance attributeInstance :
// goalWorkItem4.getOutputAttributeInstances()) {
// assertEquals(entityInstance1, attributeInstance.getEntityInstance());
// }
//
// Transaction.commit();
// committed = true;
// } finally {
// if (!committed) {
// Transaction.abort();
// }
// }
// }*/
//
//
// }