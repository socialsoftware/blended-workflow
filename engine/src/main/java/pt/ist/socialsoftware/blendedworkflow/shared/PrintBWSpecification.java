package pt.ist.socialsoftware.blendedworkflow.shared;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.OldAttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldBWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.OldDataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.OldEntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.OldRelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.OldTaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldTaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItem;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PrintBWSpecification {

    // private static Logger log = Logger.getLogger("PrintBWSpecification");

    private static BlendedWorkflow blendedWorkflow;
    private static Specification bwSpecification;
    private static DataModel dataModelTemplate;
    private static GoalModel goalModel;
    private static ActivityModel taskModel;
    private static OldBWInstance bwInstance;
    private static OldDataModelInstance dataModelInstance;
    private static OldGoalModelInstance goalModelInstance;
    private static OldTaskModelInstance taskModelInstance;

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
        bwSpecification = blendedWorkflow.getSpecById(bwSpecificationName)
                .orElse(null);
        dataModelTemplate = bwSpecification.getDataModel();
        goalModel = bwSpecification.getGoalModel();
        taskModel = bwSpecification.getActivityModel();

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
                + bwSpecification.getOldBwInstancesSet().size() + " instance.");
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
        for (Entity entity : dataModelTemplate.getEntitySet()) {
            System.out.println("Entity \"" + entity.getName() + "\" has "
                    + entity.getOldEntityInstanceSet().size() + " Instances");
            for (OldEntityInstance ei : entity.getOldEntityInstanceSet()) {
                System.out.println("EntityInstance \"" + ei.getID() + "\" is "
                        + ei.getState());
            }

            for (Attribute attribute : entity.getAttributeBasicSet()) {
                System.out.println("Attribute \"" + attribute.getName() + "\"");
            }
            System.out.println(
                    "----------------------------------------------------------");
        }
        for (RelationBW relation : dataModelTemplate.getRelationBWSet()) {
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
        for (Entity entity : dataModelInstance.getEntitySet()) {
            System.out.println("Entity \"" + entity.getName() + "\"");

            for (Attribute attribute : entity.getAttributeBasicSet()) {
                System.out.println("Attribute \"" + attribute.getName() + "\"");
            }
            System.out.println(
                    "----------------------------------------------------------");
        }
        for (RelationBW relation : dataModelInstance.getRelationBWSet()) {
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
        for (Goal goal : goalModel.getGoalSet()) {
            System.out.println("Goal \"" + goal.getName() + "\" has \""
                    + goal.getOldGoalWorkItemSet().size()
                    + "\" WorkItems and has " + goal.getSubGoalSet().size()
                    + " subgoals.");
            for (Condition ac : goal.getActivationConditionSet()) {
                System.out.println("ActivateCondition: " + ac);
            }
            System.out
                    .println("SucessCondition: " + goal.getSuccessCondition());
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
        for (Goal goal : goalModelInstance.getGoalSet()) {
            System.out.println("Goal \"" + goal.getName() + "\" has \""
                    + goal.getOldGoalWorkItemSet().size()
                    + "\" WorkItems and has " + goal.getSubGoalSet().size()
                    + " subgoals.");
            for (Condition ac : goal.getActivationConditionSet()) {
                System.out.println("ActivateCondition: " + ac);
            }
            System.out
                    .println("SucessCondition: " + goal.getSuccessCondition());
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
        for (Activity task : taskModel.getActivitySet()) {
            System.out.println("Task \"" + task.getName() + "\".");
            System.out.println("Pre Condition: ");
            task.getPreConditionSet().stream()
                    .forEach((cond) -> System.out.println(cond));
            System.out.println("Post Condition: ");
            task.getPostConditionSet().stream()
                    .forEach((cond) -> System.out.println(cond));
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
        for (Activity task : taskModelInstance.getActivitySet()) {
            System.out.println("Task \"" + task.getName() + "\".");
            System.out.println("Pre Condition: ");
            task.getPreConditionSet().stream()
                    .forEach((cond) -> System.out.println(cond));
            System.out.println("Post Condition: ");
            task.getPostConditionSet().stream()
                    .forEach((cond) -> System.out.println(cond));
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
        for (Entity entity : dataModelInstance.getEntitySet()) {
            System.out.println("Entity \"" + entity.getName() + "\" has "
                    + entity.getOldEntityInstanceSet().size() + " instances.");
            if (entity.getOldEntityInstanceSet().size() > 0) {
                for (OldEntityInstance entityInstance : entity
                        .getOldEntityInstanceSet()) {
                    System.out.println(
                            "EntityInstance \"" + entityInstance.getID()
                                    + "\" is associated with " + entityInstance
                                            .getAttributeInstancesSet().size()
                                    + " AttributesInstances.");
                    for (OldAttributeInstance attributeInstance : entityInstance
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
        for (RelationBW relation : dataModelInstance.getRelationBWSet()) {
            System.out.println("Relation \"" + relation.getName() + "\" has "
                    + relation.getOldRelationInstanceSet().size()
                    + " instances.");
            System.out.println(
                    "E1 \"" + relation.getEntityOne().getName() + "\"");
            System.out.println(
                    "E2 \"" + relation.getEntityTwo().getName() + "\"");
            if (relation.getOldRelationInstanceSet().size() > 0) {
                for (OldRelationInstance relationInstance : relation
                        .getOldRelationInstanceSet()) {
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
        for (OldWorkItem workitem : bwInstance.getWorkItemsSet()) {
            if (workitem instanceof OldGoalWorkItem) {
                OldGoalWorkItem goalWorkItem = (OldGoalWorkItem) workitem;
                System.out
                        .println("GoalWorkItem \"" + workitem.getID() + "\" is "
                                + goalWorkItem.getState()
                                + " and is associated with " + workitem
                                        .getOutputAttributeInstancesSet().size()
                                + " attributeInstances.");
            }

            if (workitem instanceof OldTaskWorkItem) {
                OldTaskWorkItem taskWorkItem = (OldTaskWorkItem) workitem;
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
        for (OldWorkItem workitem : bwInstance.getWorkItemsSet()) {
            if (workitem instanceof OldTaskWorkItem) {
                OldTaskWorkItem taskWorkItem = (OldTaskWorkItem) workitem;
                System.out.println("TaskWorkItem \"" + workitem.getID()
                        + "\" is " + taskWorkItem.getState());
            }
            if (workitem instanceof OldGoalWorkItem) {
                OldGoalWorkItem goalWorkItem = (OldGoalWorkItem) workitem;
                System.out.println("GoalWorkItem \"" + workitem.getID()
                        + "\" is " + goalWorkItem.getState());
            }

            System.out.println("Input: Associated with "
                    + workitem.getInputAttributeInstancesSet().size()
                    + " attributeInstances.");
            if (workitem.getInputAttributeInstancesSet().size() > 0) {
                for (OldAttributeInstance attributeInstance : workitem
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
                for (OldAttributeInstance attributeInstance : workitem
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