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
