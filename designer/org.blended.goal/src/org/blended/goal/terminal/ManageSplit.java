package org.blended.goal.terminal;

import static org.eclipse.emf.ecore.util.EcoreUtil.copy;
import static org.eclipse.emf.ecore.util.EcoreUtil.copyAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.common.Nothing;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.Queries;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import org.blended.goal.terminal.ManageJoin.NodesRelation;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.beust.jcommander.ParameterException;

public class ManageSplit {
    public static void goals(GoalModel model, String name, CommandSplit split)
            throws ParameterException, ValueException {
        // CHECKS
        List<String> successConditions1 = Arrays
                .asList(split.successConditions.split(";")); // different
                                                             // elements
        List<List<String>> successConditionsSplitted1 = new ArrayList<>(
                successConditions1.size());
        for (String suc : successConditions1) {
            List<String> part = Arrays.asList(suc.split(","));
            successConditionsSplitted1.add(part);
        }
        if (successConditionsSplitted1.size() < 1)
            throw new ParameterException(
                    "Parameter not valid: One existing goal expected to be splitted");
        // List<String> serializedSuccessConditions =
        // serializedSuccessConditions(successConditionsSplitted1);

        String goalName = split.goal;
        String newGoalName = split.name;
        String type = split.type;

        Optional<Goal> goalNameO = getGoalByName(model, goalName);
        Optional<Goal> newGoalNameO = getGoalByName(model, newGoalName);
        if (!goalNameO.isPresent())
            throw new ValueException(
                    "Parameter not valid: One existing goal expected to be splitted");
        if (newGoalNameO.isPresent())
            throw new ValueException(
                    "Parameter not valid: No existing goal expected with the same name");
        Goal goal = goalNameO.get();

        if (goal.getSuccessConditions().size() < 1)
            throw new ValueException(
                    "Parameter not valid: No source success conditions. It is not possible to split");
        List<EObject> succList1 = checkSuccessConditions(
                goal.getSuccessConditions(), successConditionsSplitted1);
        if (succList1 == null) {
            throw new ValueException(
                    "Parameter not valid: All Achieve conditions should exist in the source goal");
        }

        // CREATE NEW GOALS
        Goal newGoal1 = createNewGoal(model, "gTEMP");
        Goal newGoal2 = createNewGoal(model, newGoalName);

        manageSuccessConditions(goal.getSuccessConditions(), newGoal1, newGoal2,
                succList1);
        List<List<String>> successConditionsSplitted2 = getSucessConditionsSplitted(
                newGoal2);

        manageActivationConditions(goal, newGoal1, successConditionsSplitted1);
        manageActivationConditions(goal, newGoal2, successConditionsSplitted2);

        manageInvariantConditions(goal, newGoal1, successConditionsSplitted1);
        manageInvariantConditions(goal, newGoal2, successConditionsSplitted2);

        Optional<ValueException> rel1 = manageRelatives(model, goal, newGoal1,
                newGoal2, type);
        if (rel1.isPresent())
            throw rel1.get();

        // REMOVE THE ORIGINAL GOAL
        // model.getGoals().remove(goal);
        ConsoleManagement.write(name,
                "Operation performed. Goal " + goalName
                        + " Splitted by creating goal " + newGoalName + " ("
                        + type + ")");
    }

    public static Optional<Goal> getGoalByName(GoalModel model, String name) {
        Optional<Goal> goal = model.getGoals().stream()
                .filter(e -> e.getName().equals(name)).findFirst();
        return goal;
    }

    public static Goal createNewGoal(GoalModel model, String name) {
        GoalFactory factory = GoalFactory.eINSTANCE;
        Goal newGoal = factory.createGoal();
        newGoal.setName(name);
        model.getGoals().add(newGoal);
        return newGoal;
    }

    public static List<List<String>> getSucessConditionsSplitted(Goal goal) {
        List<List<String>> succ = new ArrayList<List<String>>();

        for (EObject suc : goal.getSuccessConditions()) {
            if (suc instanceof EntityAchieveCondition) {
                EntityAchieveCondition o = (EntityAchieveCondition) suc;
                List<String> list = new ArrayList<String>();
                list.add(o.getName());
                succ.add(list);
            }
            if (suc instanceof NotMandatoryAttributeAchieveCondition) {
                NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition) suc;
                List<String> list = new ArrayList<String>();
                list.add(o.getAttribute());
                succ.add(list);
            }
        }

        return succ;
    }

    public static List<EObject> checkSuccessConditions(
            EList<EObject> goalSuccessConditions,
            List<List<String>> successConditionsSplitted) {
        List<EObject> result = new ArrayList<EObject>();
        Boolean exists = false;
        for (List<String> cond : successConditionsSplitted) {
            exists = false;
            for (EObject suc : goalSuccessConditions) {
                if (suc instanceof EntityAchieveCondition) {
                    EntityAchieveCondition o = (EntityAchieveCondition) suc;
                    if ((cond.size() == 1)
                            && (o.getName().equals(cond.get(0)))) {
                        result.add(suc); // it exists
                        exists = true;
                    }
                }
                if (suc instanceof NotMandatoryAttributeAchieveCondition) {
                    NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition) suc;
                    if (cond.contains(o.getAttribute())) {
                        result.add(suc); // it exists
                        exists = true;
                    }
                } else if (suc instanceof Nothing) {
                    Nothing o = (Nothing) suc;
                    // if ((cond.size() ==
                    // 1)&&(cond.get(0).equals(o.getName()))) {
                    if ((cond.size() == 1)
                            && (o.getName().equals(cond.get(0)))) {
                        result.add(suc); // it exists
                        exists = true;
                    }
                }
            }
            if (!exists)
                return null;
        }
        return result;
    }

    /**
     * I just need to copy all the success conditions, but taking into account
     * that I don't want any of them without a SUC value. At least SUC(Nothing)
     * 
     * @param goalSuccessConditions
     * @param newGoal
     * @param succList
     */
    public static void manageSuccessConditions(
            EList<EObject> goalSuccessConditions, Goal newGoal1, Goal newGoal2,
            List<EObject> succList) {
        newGoal1.getSuccessConditions().addAll(copyAll(succList));
        goalSuccessConditions.removeAll(succList); // we remove all the ones we
                                                   // are using
        if (goalSuccessConditions.size() == 0) { // I don't want that to be
                                                 // empty for any goal
            CommonFactory factory = CommonFactory.eINSTANCE;
            Nothing nothing = factory.createNothing();
            nothing.setName("nothing");
            goalSuccessConditions.add(nothing);
        }
        newGoal2.getSuccessConditions().addAll(copyAll(goalSuccessConditions)); // the
                                                                                // remaining
                                                                                // for
                                                                                // the
                                                                                // second
                                                                                // new
                                                                                // goal
    }

    /**
     * I just need to call to the engine to know given a SUC which conditions it
     * depends on That way I will copy such ACTs with the corresponding SUCs
     * 
     * @param goal
     * @param newGoal
     * @param serializedSuccessConditions
     */
    public static void manageActivationConditions(Goal goal, Goal newGoal,
            List<List<String>> successConditionsSplitted) {
        for (List<String> conds : successConditionsSplitted) {
            boolean more = true;
            if (more) {
                Set<String> dependencies = Queries
                        .getDependenciesForElement(new HashSet<String>(conds));

                for (EObject act : goal.getActivationConditions()) { // Example:
                                                                     // ACT(EDEF(Episode.patient))
                    if (act instanceof EntityAchieveCondition) {
                        EntityAchieveCondition o = (EntityAchieveCondition) act;
                        for (String dependence : dependencies) {
                            if (dependence.equals(o.getName())) {
                                newGoal.getActivationConditions()
                                        .add(copy(act));
                            }
                        }
                    } // if
                    else if (act instanceof NotMandatoryAttributeAchieveCondition) {
                        NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition) act;
                        for (String dependence : dependencies) {
                            if (dependence.equals(o.getAttribute())) {
                                // if any of the elements in the group has a
                                // dependence => we include the ACT
                                EObject actCopy = copy(act);
                                if (!newGoal.getActivationConditions()
                                        .contains(actCopy)) {
                                    if (more) {
                                        newGoal.getActivationConditions()
                                                .add(copy(act));
                                        more = false;
                                    }
                                }
                            }
                        }
                    } // else
                } // for
            } // for
        } // while more
    }

    /**
     * I just need to call to the engine to know given a SUC which invariants it
     * has That way I will copy such MUL/RULs with the corresponding SUCs
     * 
     * @param goal
     * @param newGoal
     * @param serializedSuccessConditions
     */
    public static void manageInvariantConditions(Goal goal, Goal newGoal,
            List<List<String>> successConditionsSplitted) {
        for (List<String> conds : successConditionsSplitted) {
            boolean more = true;
            if (more) {
                for (EObject inv : goal.getInvariantConditions()) {
                    if (inv instanceof EntityInvariantCondition) { // Example:
                                                                   // MUL(Episode.report,
                                                                   // 1)
                        EntityInvariantCondition o = (EntityInvariantCondition) inv;
                        if (conds.size() == 1) // Its and EntityInvariant ->
                                               // There should be only an
                                               // element
                            if (Queries.getEntityNameFrom(o.getName())
                                    .equals(conds.get(0))) {
                                newGoal.getInvariantConditions().add(copy(inv));
                            }
                    } // if
                    else if (inv instanceof AttributeInvariantCondition) {
                        AttributeInvariantCondition o = (AttributeInvariantCondition) inv;
                        List<String> list = new ArrayList<String>();
                        Queries.getDecomposedExpression(o.getExpression(),
                                list);
                        Set<String> dependencies = Queries
                                .getAttributesRelatedToRule(
                                        new HashSet<String>(list));
                        for (String c : conds) { // if any of the elements in
                                                 // the group has a dependence
                                                 // => we include the RUL
                            for (String dependence : dependencies) {
                                if (dependence.equals(c)) {
                                    EObject invCopy = copy(inv);
                                    if (!newGoal.getInvariantConditions()
                                            .contains(invCopy)) {
                                        if (more) {
                                            newGoal.getInvariantConditions()
                                                    .add(copy(inv));
                                            more = false;
                                        }
                                    }
                                }
                            }
                        }
                    } // else
                } // for
            } // for
        } // while more
    }

    public static Optional<ValueException> manageRelatives(GoalModel model,
            Goal goal, Goal newGoal1, Goal newGoal2, String type) {
        Optional<ValueException> result = Optional.empty();
        switch (type) {
        case "s":
        case "sibling": // the new node is a sibling of the previous one
            // we want the two nodes (g1 and g2) that are together now (g)
            // become siblins
            // we need to check whether g1 and g2 can be splitted. They cannot
            // if SUC(g2) is an attribute of (g1)
            if (!isNewSiblingPossible(newGoal1, newGoal2))
                result = Optional.of(new ValueException());
            else {
                changeInputReferencesSibling(model, goal, newGoal1, newGoal2); // instead
                                                                               // to
                                                                               // GOAL,
                                                                               // to
                                                                               // NEWGOAL1
                                                                               // and
                                                                               // NEWGOAL2
                changeOutputReferencesSibling(goal, newGoal1, newGoal2); // changing
                                                                         // references
                                                                         // from
                                                                         // GOAL
                                                                         // to
                                                                         // be
                                                                         // from
                                                                         // NEWGOAL1
                                                                         // or
                                                                         // NEWGOAL2
                                                                         // instead
            }
            break;
        case "c":
        case "child": // the new node is the child of the previous one
            // we want the two nodes (g1 and g2) that are together now (g)
            // become parent (g1) and child (g2)

            // OK -> we do (newGoal2 refers now to newGoal1 - newGoal2 contains
            // the SUC that have been specified in the command line)
            changeParentReferencesChild(model, goal, newGoal1, newGoal2);
            break;
        }
        return result;
    }

    public static void changeInputReferencesSibling(GoalModel model, Goal goal,
            Goal newGoal1, Goal newGoal2) {
        // INPUT REFERENCES
        for (Goal g : model.getGoals()) { // for each of the goals
            if (g.getChildrenGoals().contains(goal)) { // we need to study to
                                                       // which one is the
                                                       // reference to make
                                                       // changes
                for (EObject suc : g.getSuccessConditions()) {
                    if (suc instanceof EntityAchieveCondition) {
                        EntityAchieveCondition o = (EntityAchieveCondition) suc;
                        // if (o is an entity) && (newGoal1 is an attribute of
                        // that entity) -->
                        newGoal1.getSuccessConditions().stream()
                                .filter(e -> e instanceof NotMandatoryAttributeAchieveCondition)
                                .forEach(e -> {
                                    NotMandatoryAttributeAchieveCondition att = (NotMandatoryAttributeAchieveCondition) e;

                                    if (o.getName()
                                            .equals(Queries.getEntityNameFrom(
                                                    att.getAttribute()))
                                            && (!g.getChildrenGoals()
                                                    .contains(newGoal1))) {
                                        g.getChildrenGoals().add(newGoal1);
                                    }
                                });
                        // if (o is an entity) && (newGoal2 is an attribute of
                        // that entity) -->
                        newGoal2.getSuccessConditions().stream()
                                .filter(e -> e instanceof NotMandatoryAttributeAchieveCondition)
                                .forEach(e -> {
                                    NotMandatoryAttributeAchieveCondition att = (NotMandatoryAttributeAchieveCondition) e;

                                    if (o.getName()
                                            .equals(Queries.getEntityNameFrom(
                                                    att.getAttribute()))
                                            && (!g.getChildrenGoals()
                                                    .contains(newGoal2))) {
                                        g.getChildrenGoals().add(newGoal2);
                                    }
                                });
                    }
                    // else if (suc instanceof
                    // NotMandatoryAttributeAchieveCondition) {
                    // NotMandatoryAttributeAchieveCondition o =
                    // (NotMandatoryAttributeAchieveCondition)suc;
                    // }
                    else if (suc instanceof Nothing) {
                        // if (o is nothing) --> both of them if there have
                        // Entities
                        if (newGoal1.getSuccessConditions().stream()
                                .filter(e -> e instanceof EntityAchieveCondition)
                                .findFirst().isPresent())
                            if (!g.getChildrenGoals().contains(newGoal1))
                                g.getChildrenGoals().add(newGoal1);
                        if (newGoal2.getSuccessConditions().stream()
                                .filter(e -> e instanceof EntityAchieveCondition)
                                .findFirst().isPresent())
                            if (!g.getChildrenGoals().contains(newGoal2))
                                g.getChildrenGoals().add(newGoal2);
                    }
                } // for
                if ((!g.getChildrenGoals().contains(newGoal1))
                        && (!g.getChildrenGoals().contains(newGoal2))) {
                    // we didn't insert the dependency yet but we need at least
                    // one --> ask the user or use by default one of them
                    g.getChildrenGoals().add(newGoal2);
                }
                g.getChildrenGoals().remove(goal);
            } // if
        } // for
    }

    public static void changeOutputReferencesSibling(Goal goal, Goal newGoal1,
            Goal newGoal2) {
        // OUTPUT REFERENCES
        for (Goal g : goal.getChildrenGoals()) { // output references from the
                                                 // original node
            for (EObject suc : g.getSuccessConditions()) { // to identify the
                                                           // SUCs of the goals
                                                           // from goal
                if (suc instanceof NotMandatoryAttributeAchieveCondition) {
                    NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition) suc;
                    // checking if it is related to newGoal1
                    newGoal1.getSuccessConditions().stream()
                            .filter(e -> e instanceof EntityAchieveCondition)
                            .forEach(e -> {
                                EntityAchieveCondition ent = (EntityAchieveCondition) e;

                                if (ent.getName()
                                        .equals(Queries.getEntityNameFrom(
                                                o.getAttribute()))
                                        && (!newGoal1.getChildrenGoals()
                                                .contains(g))) {
                                    newGoal1.getChildrenGoals().add(g);
                                }
                            });
                    // checking if it is related to newGoal2
                    newGoal2.getSuccessConditions().stream()
                            .filter(e -> e instanceof EntityAchieveCondition)
                            .forEach(e -> {
                                EntityAchieveCondition ent = (EntityAchieveCondition) e;

                                if (ent.getName()
                                        .equals(Queries.getEntityNameFrom(
                                                o.getAttribute()))
                                        && (!newGoal2.getChildrenGoals()
                                                .contains(g))) {
                                    newGoal2.getChildrenGoals().add(g);
                                }
                            });
                }
            } // for
            if ((!newGoal1.getChildrenGoals().contains(g))
                    && (!newGoal2.getChildrenGoals().contains(g))) {
                // we didn't insert the dependency yet but we need at least one
                // --> ask the user or use by default one of them
                newGoal2.getChildrenGoals().add(g);
            }
            g.getChildrenGoals().remove(goal);
        } // for each children
    }

    public static boolean isNewSiblingPossible(Goal newGoal1, Goal newGoal2) {
        boolean result = true;

        for (EObject obj1 : newGoal1.getSuccessConditions()) {
            for (EObject obj2 : newGoal2.getSuccessConditions()) {
                if ((obj1 instanceof EntityAchieveCondition)
                        && (obj2 instanceof NotMandatoryAttributeAchieveCondition)) {
                    EntityAchieveCondition o1 = (EntityAchieveCondition) obj1;
                    NotMandatoryAttributeAchieveCondition o2 = (NotMandatoryAttributeAchieveCondition) obj2;
                    if (Queries.getEntityNameFrom(o2.getAttribute())
                            .equals(o1.getName()))
                        return false;
                }
                if ((obj2 instanceof EntityAchieveCondition)
                        && (obj1 instanceof NotMandatoryAttributeAchieveCondition)) {
                    EntityAchieveCondition o2 = (EntityAchieveCondition) obj2;
                    NotMandatoryAttributeAchieveCondition o1 = (NotMandatoryAttributeAchieveCondition) obj1;
                    if (Queries.getEntityNameFrom(o1.getAttribute())
                            .equals(o2.getName()))
                        return false;
                }
            }
        }

        return result;
    }

    public static void changeParentReferencesChild(GoalModel model, Goal goal,
            Goal newGoal1, Goal newGoal2) {
        for (Goal g : model.getGoals()) {
            if (g.getChildrenGoals().contains(goal)) {
                g.getChildrenGoals().add(newGoal2);
                g.getChildrenGoals().remove(goal);
            }
        }
        newGoal2.getChildrenGoals().add(newGoal1);
    }

    public static List<String> serializedSuccessConditions(
            List<List<String>> successConditionsSplitted) {
        List<String> result = new ArrayList<String>();
        for (List<String> conds : successConditionsSplitted) { // Example:
                                                               // [Medication.name
                                                               // Medication.quantity
                                                               // Medication.heartImpact]
                                                               // [Report]
            for (String cond : conds) { // Example: [Medication.name
                                        // Medication.quantity
                                        // Medication.heartImpact Report]
                if (!result.contains(cond)) // we don't want to repeat any
                                            // element inside. Example
                                            // [Medication Report]
                    result.add(cond);
            } // for
        } // for
        return result;
    }

    public static NodesRelation getTypeOfRelation(GoalModel model, Goal goal1,
            Goal goal2) {
        NodesRelation relation = NodesRelation.NotValid;

        for (Goal goal : model.getGoals()) {
            if (goal.getChildrenGoals().contains(goal1)
                    && goal.getChildrenGoals().contains(goal2)) {
                relation = NodesRelation.Horizontal;
                break;
            }
            if (goal.equals(goal1) && goal.getChildrenGoals().contains(goal2)) {
                relation = NodesRelation.VerticalParent;
                break;
            }
            if (goal.equals(goal2) && goal.getChildrenGoals().contains(goal1)) {
                relation = NodesRelation.VerticalChild;
                break;
            }
        }

        return relation;
    }

    private boolean isPossibleToSplit(GoalModel goal, Goal source,
            Goal newGoal1, Goal newGoal2) {
        boolean result = false;
        return result;
    }

}
