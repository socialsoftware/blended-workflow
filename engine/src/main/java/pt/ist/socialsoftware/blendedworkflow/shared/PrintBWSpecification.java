package pt.ist.socialsoftware.blendedworkflow.shared;

import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWGoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PrintBWSpecification {

    // private static Logger log = Logger.getLogger("PrintBWSpecification");

    private static BlendedWorkflow blendedWorkflow;
    private static BWSpecification bwSpecification;
    private static BWDataModel dataModelTemplate;
    private static BWGoalModel goalModel;
    private static TaskModel taskModel;
    private static BWInstance bwInstance;
    private static DataModelInstance dataModelInstance;
    private static GoalModelInstance goalModelInstance;
    private static TaskModelInstance taskModelInstance;

    private PrintBWSpecification() {
    }

    public static void all(String bwSpecificationName) throws BWException {
        init(bwSpecificationName);
        bwInstances(bwSpecificationName);
        dataModelTemplate(bwSpecificationName);
        dataModelType(bwSpecificationName);
        goalModelTemplate(bwSpecificationName);
        goalModelType(bwSpecificationName);
        taskModelTemplate(bwSpecificationName);
        taskModelType(bwSpecificationName);
        dataModelInstances(bwSpecificationName);
        workItemsWithAttributtes(bwSpecificationName);
    }

    private static void init(String bwSpecificationName) throws BWException {
        blendedWorkflow = BlendedWorkflow.getInstance();
        bwSpecification = blendedWorkflow.getSpecByName(bwSpecificationName)
                .orElse(null);
        dataModelTemplate = bwSpecification.getDataModel();
        goalModel = bwSpecification.getGoalModel();
        taskModel = bwSpecification.getTaskModel();

        bwInstance = blendedWorkflow.getBWInstance(bwSpecificationName + ".1"); // TODO:
                                                                                // only
                                                                                // 1
                                                                                // instance
        dataModelInstance = bwInstance.getDataModelInstance();
        goalModelInstance = bwInstance.getGoalModelInstance();
        taskModelInstance = bwInstance.getTaskModelInstance();
    }

    public static void bwInstances(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("BWInstances:");
        System.out.println(
                "----------------------------------------------------------");
        System.out.println(bwSpecification.getName() + " has "
                + bwSpecification.getBwInstancesSet().size() + " instance.");
        System.out.println(bwInstance.getID());
    }

    public static void dataModelTemplate(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("DataModel Template");
        System.out.println(
                "----------------------------------------------------------");
        for (BWEntity entity : dataModelTemplate.getEntitiesSet()) {
            System.out.println("Entity \"" + entity.getName() + "\" has "
                    + entity.getEntityInstancesSet().size() + " Instances");
            for (EntityInstance ei : entity.getEntityInstancesSet()) {
                System.out.println("EntityInstance \"" + ei.getID() + "\" is "
                        + ei.getState());
            }

            for (BWAttribute attribute : entity.getAttributesSet()) {
                System.out.println("Attribute \"" + attribute.getName() + "\"");
            }
            System.out.println(
                    "----------------------------------------------------------");
        }
        for (BWRelation relation : dataModelTemplate.getRelationsSet()) {
            System.out.println("Relation \"" + relation.getName() + "\"");
        }
    }

    public static void dataModelType(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("DataModel Type");
        System.out.println(
                "----------------------------------------------------------");
        for (BWEntity entity : dataModelInstance.getEntitiesSet()) {
            System.out.println("Entity \"" + entity.getName() + "\"");

            for (BWAttribute attribute : entity.getAttributesSet()) {
                System.out.println("Attribute \"" + attribute.getName() + "\"");
            }
            System.out.println(
                    "----------------------------------------------------------");
        }
        for (BWRelation relation : dataModelInstance.getRelationsSet()) {
            System.out.println("Relation \"" + relation.getName() + "\"");
        }
    }

    public static void goalModelTemplate(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("GoalModel Template");
        System.out.println(
                "----------------------------------------------------------");
        for (AchieveGoal goal : goalModel.getAchieveGoalsSet()) {
            System.out.println("Goal \"" + goal.getName() + "\" has \""
                    + goal.getGoalWorkItemsSet().size()
                    + "\" WorkItems and has " + goal.getSubGoalsSet().size()
                    + " subgoals.");
            for (Condition ac : goal.getActivateConditionsSet()) {
                System.out.println("ActivateCondition: " + ac);
            }
            System.out.println("SucessCondition: " + goal.getSucessCondition());
            System.out.println("User: " + goal.getUser().getID() + " Role: "
                    + goal.getRole().getName());
        }
    }

    public static void goalModelType(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("GoalModel Type");
        System.out.println(
                "----------------------------------------------------------");
        for (AchieveGoal goal : goalModelInstance.getAchieveGoalsSet()) {
            System.out.println("Goal \"" + goal.getName() + "\" has \""
                    + goal.getGoalWorkItemsSet().size()
                    + "\" WorkItems and has " + goal.getSubGoalsSet().size()
                    + " subgoals.");
            for (Condition ac : goal.getActivateConditionsSet()) {
                System.out.println("ActivateCondition: " + ac);
            }
            System.out.println("SucessCondition: " + goal.getSucessCondition());
            System.out.println("User: " + goal.getUser().getID() + " Role: "
                    + goal.getRole().getName());
        }
    }

    public static void taskModelTemplate(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("TaskModel Template");
        System.out.println(
                "----------------------------------------------------------");
        for (Task task : taskModel.getTasksSet()) {
            System.out.println("Task \"" + task.getName() + "\".");
            System.out.println("Pre Condition: " + task.getPreConstraint());
            System.out.println("Post Condition: " + task.getPostConstraint());
            System.out.println("User: " + task.getUser().getID() + " Role: "
                    + task.getRole().getName());
        }
    }

    public static void taskModelType(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("TaskModel Type");
        System.out.println(
                "----------------------------------------------------------");
        for (Task task : taskModelInstance.getTasksSet()) {
            System.out.println("Task \"" + task.getName() + "\".");
            System.out.println("Pre Condition: " + task.getPreConstraint());
            System.out.println("Post Condition: " + task.getPostConstraint());
            System.out.println("User: " + task.getUser().getID() + " Role: "
                    + task.getRole().getName());
        }
    }

    public static void dataModelInstances(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("DataModel Instance");
        System.out.println(
                "----------------------------------------------------------");
        for (BWEntity entity : dataModelInstance.getEntitiesSet()) {
            System.out.println("Entity \"" + entity.getName() + "\" has "
                    + entity.getEntityInstancesSet().size() + " instances.");
            if (entity.getEntityInstancesSet().size() > 0) {
                for (EntityInstance entityInstance : entity
                        .getEntityInstancesSet()) {
                    System.out.println(
                            "EntityInstance \"" + entityInstance.getID()
                                    + "\" is associated with " + entityInstance
                                            .getAttributeInstancesSet().size()
                                    + " AttributesInstances.");
                    for (AttributeInstance attributeInstance : entityInstance
                            .getAttributeInstancesSet()) {
                        System.out.println("AttributeInstance \""
                                + attributeInstance.getID() + "\" with value \""
                                + attributeInstance.getValue() + "\" and state "
                                + attributeInstance.getState() + ".");
                    }
                }
            }
            System.out.println(
                    "----------------------------------------------------------");
        }
        for (BWRelation relation : dataModelInstance.getRelationsSet()) {
            System.out.println("Relation \"" + relation.getName() + "\" has "
                    + relation.getRelationInstancesSet().size()
                    + " instances.");
            System.out.println(
                    "E1 \"" + relation.getEntityOne().getName() + "\"");
            System.out.println(
                    "E2 \"" + relation.getEntityTwo().getName() + "\"");
            if (relation.getRelationInstancesSet().size() > 0) {
                for (RelationInstance relationInstance : relation
                        .getRelationInstancesSet()) {
                    System.out.println("RelationInstance \""
                            + relationInstance.getID() + "\"");
                    System.out.println("EI1 \""
                            + relationInstance.getEntityInstanceOne().getID()
                            + "\"");
                    System.out.println("EI2 \""
                            + relationInstance.getEntityInstanceTwo().getID()
                            + "\"");
                }
            }
        }
    }

    public static void workItems(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("WorkItems");
        System.out.println(
                "----------------------------------------------------------");
        for (WorkItem workitem : bwInstance.getWorkItemsSet()) {
            if (workitem instanceof GoalWorkItem) {
                GoalWorkItem goalWorkItem = (GoalWorkItem) workitem;
                System.out
                        .println("GoalWorkItem \"" + workitem.getID() + "\" is "
                                + goalWorkItem.getState()
                                + " and is associated with " + workitem
                                        .getOutputAttributeInstancesSet().size()
                                + " attributeInstances.");
            }

            if (workitem instanceof TaskWorkItem) {
                TaskWorkItem taskWorkItem = (TaskWorkItem) workitem;
                System.out
                        .println("GoalWorkItem \"" + workitem.getID() + "\" is "
                                + taskWorkItem.getState()
                                + " and is associated with " + workitem
                                        .getOutputAttributeInstancesSet().size()
                                + " attributeInstances.");
            }
        }
    }

    public static void workItemsWithAttributtes(String bwSpecificationName)
            throws BWException {
        init(bwSpecificationName);
        System.out.println(
                "**************************************************************");
        System.out.println("WorkItems with Attributes");
        System.out.println(
                "----------------------------------------------------------");
        for (WorkItem workitem : bwInstance.getWorkItemsSet()) {
            if (workitem instanceof TaskWorkItem) {
                TaskWorkItem taskWorkItem = (TaskWorkItem) workitem;
                System.out.println("TaskWorkItem \"" + workitem.getID()
                        + "\" is " + taskWorkItem.getState());
            }
            if (workitem instanceof GoalWorkItem) {
                GoalWorkItem goalWorkItem = (GoalWorkItem) workitem;
                System.out.println("GoalWorkItem \"" + workitem.getID()
                        + "\" is " + goalWorkItem.getState());
            }

            System.out.println("Input: Associated with "
                    + workitem.getInputAttributeInstancesSet().size()
                    + " attributeInstances.");
            if (workitem.getInputAttributeInstancesSet().size() > 0) {
                for (AttributeInstance attributeInstance : workitem
                        .getInputAttributeInstancesSet()) {
                    System.out.println("EI"
                            + attributeInstance.getEntityInstance().getID()
                            + "AttributeInstance \"" + attributeInstance.getID()
                            + "\" with value \"" + attributeInstance.getValue()
                            + "\" and state " + attributeInstance.getState()
                            + ".");
                }
            }

            System.out.println("Output: Associated with "
                    + workitem.getOutputAttributeInstancesSet().size()
                    + " attributeInstances.");
            if (workitem.getOutputAttributeInstancesSet().size() > 0) {
                for (AttributeInstance attributeInstance : workitem
                        .getOutputAttributeInstancesSet()) {
                    System.out.println("EI: "
                            + attributeInstance.getEntityInstance().getID()
                            + " AttributeInstance \""
                            + attributeInstance.getID() + "\" with value \""
                            + attributeInstance.getValue() + "\" and state "
                            + attributeInstance.getState() + ".");
                }
            }
        }
    }
}