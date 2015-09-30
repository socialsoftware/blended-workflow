package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class TaskModel extends TaskModel_Base {
    private static Logger log = LoggerFactory.getLogger(TaskModel.class);

    /**
     * Clone the TaskModel
     */
    public void cloneTaskModel(TaskModelInstance newTaskModelInstance)
            throws BWException {
        for (Task task : getTasksSet()) {
            task.cloneTask(newTaskModelInstance);
        }
    }

    public Task createTask(String name, String description) {
        return new Task(this, name, description);
    }

    public Task getTask(String name) throws BWException {
        for (Task task : getTasksSet()) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_TASK_NAME, name);
    }

    public void clean() {
        getTasksSet().stream().forEach(t -> t.delete());
    }

    public void delete() {
        clean();
        setSpecification(null);

        deleteDomainObject();
    }

    public Task addTask(String taskName, String taskDescription,
            Set<Condition> postConditionSet) {
        checkNonEmptyPreCondition(postConditionSet);

        checkModelNotCompletelyCreated();

        checkPostConditionsNotUsed(postConditionSet);

        Task task = createTask(taskName, taskDescription);
        postConditionSet.stream().forEach(c -> task.addPostCondition(c));

        applyAttributeEntityDependenceToPre(postConditionSet, task);

        applyDependenceConditionsToPre(postConditionSet, task);

        applyMultiplicityToPostAndPre(postConditionSet, task);

        applyRuleConditionToPostAndPre(task);

        return task;
    }

    private void applyMultiplicityToPostAndPre(Set<Condition> postConditionSet,
            Task task) {
        Set<Entity> definedEntities = getTasksSet().stream()
                .flatMap(t -> ConditionModel
                        .getDefEntityConditions(t.getPostConditionSet())
                        .stream())
                .map(def -> def.getEntity()).collect(Collectors.toSet());

        Set<RelationBW> relations = ConditionModel
                .getDefEntityConditions(postConditionSet).stream()
                .flatMap(d -> d.getEntity().getRelationSet().stream())
                .filter(r -> definedEntities.containsAll(r.getEntitySet()))
                .collect(Collectors.toSet());

        relations.stream().forEach(r -> task.getMultiplicityInvariantSet()
                .addAll(r.getMulConditionSet()));

        relations.stream().flatMap(r -> r.getEntitySet().stream())
                .filter(ent -> !task.getPostConditionSet()
                        .contains(DefEntityCondition.getDefEntity(ent)))
                .forEach(ent -> task
                        .addPreCondition(DefEntityCondition.getDefEntity(ent)));
    }

    private void applyRuleConditionToPostAndPre(Task task) {
        Set<Attribute> definedAttributes = getTasksSet().stream()
                .flatMap(t -> ConditionModel
                        .getDefAttributeConditions(t.getPostConditionSet())
                        .stream())
                .flatMap(def -> def.getAttributeOfDef().getAttributeBasicSet()
                        .stream())
                .collect(Collectors.toSet());

        getSpecification().getDataModel().getRuleSet().stream()
                .filter(r -> r.getTaskWithRule() == null && definedAttributes
                        .containsAll(r.getAttributeBasicSet()))
                .forEach(r -> task.addRuleInvariant(r));

        task.getRuleInvariantSet().stream()
                .flatMap(r -> r.getAttributeSet().stream())
                .filter(att -> !task.getPostConditionSet()
                        .contains(DefAttributeCondition.getDefAttribute(att))
                        && !task.getPreConditionSet()
                                .contains(DefAttributeCondition
                                        .getDefAttribute(att)))
                .forEach(att -> task.addPreCondition(
                        DefAttributeCondition.getDefAttribute(att)));
    }

    private void applyDependenceConditionsToPre(Set<Condition> postConditionSet,
            Task task) {
        Set<Product> postProducts = ConditionModel
                .getProductsOfDefConditions(postConditionSet);
        getSpecification().getDataModel().getDependenceSet().stream()
                .filter(d -> postProducts.contains(d.getProduct())
                        && !postProducts.contains(d.getTarget()))
                .forEach(d -> task
                        .addPreCondition(d.getTarget().getDefCondition()));
    }

    private void applyAttributeEntityDependenceToPre(
            Set<Condition> postConditionSet, Task task) {
        ConditionModel.getDefAttributeConditions(postConditionSet).stream()
                .filter(def -> !ConditionModel
                        .getDefEntityConditions(postConditionSet)
                        .contains(DefEntityCondition.getDefEntity(
                                def.getAttributeOfDef().getEntity())))
                .forEach(def -> task.addPreCondition(DefEntityCondition
                        .getDefEntity(def.getAttributeOfDef().getEntity())));
    }

    private void checkPostConditionsNotUsed(Set<Condition> postConditionSet) {
        if (getTasksSet().stream()
                .flatMap(t -> t.getPostConditionSet().stream())
                .anyMatch(c -> postConditionSet.contains(c)))
            throw new BWException(BWErrorType.CANNOT_ADD_TASK,
                    "condition already used");
    }

    private void checkModelNotCompletelyCreated() {
        ConditionModel conditionModel = getSpecification().getConditionModel();
        if (!conditionModel.getEntityAchieveConditionSet().stream()
                .anyMatch(c -> c.getTaskWithPostCondition() == null)
                && !conditionModel.getAttributeAchieveConditionSet().stream()
                        .anyMatch(c -> c.getTaskWithPostCondition() == null))
            throw new BWException(BWErrorType.CANNOT_ADD_TASK,
                    "all achieve conditions already belong to a post condition");
    }

    private void checkNonEmptyPreCondition(Set<Condition> postConditionSet) {
        if (postConditionSet.isEmpty())
            throw new BWException(BWErrorType.CANNOT_ADD_TASK,
                    "empty post condition set");
    }

    public boolean checkModel() {
        checkAllConditionsAreUsedInPost();

        return getTasksSet().stream().map(t -> t.checkConsistency())
                .reduce(true, (b1, b2) -> b1 && b2);
    }

    private void checkAllConditionsAreUsedInPost() {
        ConditionModel conditionModel = getSpecification().getConditionModel();

        Set<Condition> allConditions = new HashSet<Condition>(
                conditionModel.getEntityAchieveConditionSet().stream()
                        .filter(d -> !d.getEntity().getExists())
                        .collect(Collectors.toSet()));

        allConditions.addAll(conditionModel.getAttributeAchieveConditionSet()
                .stream().collect(Collectors.toSet()));
        allConditions.removeAll(getTasksSet().stream()
                .flatMap(t -> t.getPostConditionSet().stream())
                .collect(Collectors.toSet()));
        if (!allConditions.isEmpty())
            throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
                    allConditions.stream().map(c -> c.getSubPath())
                            .collect(Collectors.joining(",")));

        allConditions = new HashSet<Condition>(
                conditionModel.getEntityInvariantConditionSet());
        allConditions
                .removeAll(
                        getTasksSet()
                                .stream().flatMap(t -> t
                                        .getMultiplicityInvariantSet().stream())
                                .collect(Collectors.toSet()));
        if (!allConditions.isEmpty())
            throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
                    allConditions.stream().map(c -> c.getSubPath())
                            .collect(Collectors.joining(",")));

        Set<Rule> allRules = new HashSet<Rule>(
                conditionModel.getAttributeInvariantConditionSet());
        allRules.removeAll(getTasksSet().stream()
                .flatMap(t -> t.getRuleInvariantSet().stream())
                .collect(Collectors.toSet()));
        if (!allRules.isEmpty())
            throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
                    allRules.stream().map(r -> r.getName())
                            .collect(Collectors.joining(",")));
    }

}
