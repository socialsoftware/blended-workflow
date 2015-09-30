package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;

public class Goal extends Goal_Base {
    private static Logger log = LoggerFactory.getLogger(Goal.class);

    public enum GoalRelation {
        CHILD, PARENT, SIBLING, OTHER;
    }

    @Override
    public void setName(String name) {
        checkUniqueGoalName(name);
        super.setName(name);
    }

    public Goal(GoalModel goalModel, String name) {
        setGoalModel(goalModel);
        setName(name);
        setParentGoal(null);
    }

    /**
     * Create the GoalTree root Goal.
     */
    public Goal(GoalModel goalModel, String name, String description,
            Condition condition, Entity context) throws BWException {
        setGoalModel(goalModel);
        setName(name);
        setDescription(description);
        addSuccessCondition(condition);
        setParentGoal(null);
        setEntityContext(context);
    }

    /**
     * Create a Goal.
     */
    public Goal(GoalModel goalModel, Goal parentGoal, String name,
            String description, Condition condition, Entity context)
                    throws BWException {
        setGoalModel(goalModel);
        setName(name);
        setDescription(description);
        addSuccessCondition(condition);
        setParentGoal(parentGoal);
        setEntityContext(context);
    }

    private void checkUniqueGoalName(String name) throws BWException {
        for (Goal goal : this.getGoalModel().getGoalSet()) {
            if (goal.getName() != null && goal != this
                    && goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
        for (MaintainGoal goal : this.getGoalModel().getMaintainGoalsSet()) {
            if (goal.getName() != null && goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
    }

    public void cloneGoal(GoalModelInstance goalModelInstance)
            throws BWException {
        Condition newSucessCondition = null;
        Condition condition = getSuccessConditionSet().stream().findFirst()
                .get();
        if (condition != null) {
            newSucessCondition = condition.cloneCondition(goalModelInstance);
        }

        // Get EntityTypeContext from Template
        BWInstance bwInstance = goalModelInstance.getBwInstance();
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        Entity newEntityContext = null;
        for (Entity entity : dataModelInstance.getEntitySet()) {
            if (getEntityContext().getName().equals(entity.getName())) {
                newEntityContext = entity;
            }
        }

        Goal newGoal = new Goal(goalModelInstance, getName(), getDescription(),
                newSucessCondition, newEntityContext);
        newGoal.setUser(getUser());
        newGoal.setRole(getRole());

        for (Condition activateCondition : getActivationConditionSet()) {
            Condition newActivateCondition = activateCondition
                    .cloneCondition(goalModelInstance);
            newGoal.addActivationCondition(newActivateCondition);
        }
    }

    /**
     * Get the Goal condition data to use in the use interface.
     * 
     * @return a string with the condition data entities.
     */
    public String getConstraintData() {
        Set<Entity> entities = getSuccessConditionSet().stream().findFirst()
                .get().getEntities();
        Set<AttributeBasic> attributes = getSuccessConditionSet().stream()
                .findFirst().get().getAttributeBasicSet();
        String dataString = "";

        // Add Attribute entities
        for (AttributeBasic attribute : attributes) {
            entities.add(attribute.getEntity());
        }

        // Create String
        int count = 0;
        for (Entity entity : entities) {
            if (entities.size() == 1) {
                dataString += entity.getName();
            } else if (count < entities.size() - 1) {
                dataString += entity.getName() + ", ";
                ;
            } else {
                dataString += entity.getName();
            }
            count++;
        }
        return dataString;
    }

    public String getPreConstraintData() {
        List<Condition> activateConditions = new ArrayList<Condition>(
                getActivationConditionSet());
        Set<Entity> entities = activateConditions.get(0).getEntities(); // FIXME:
                                                                        // Only
                                                                        // First
                                                                        // ActivateConditionData
        Set<AttributeBasic> attributes = activateConditions.get(0)
                .getAttributeBasicSet();
        String dataString = "";

        // Add Attribute entities
        for (AttributeBasic attribute : attributes) {
            entities.add(attribute.getEntity());
        }

        // Create String
        int count = 0;
        for (Entity entity : entities) {
            if (entities.size() == 1) {
                dataString += entity.getName();
            } else if (count < entities.size() - 1) {
                dataString += entity.getName() + ", ";
                ;
            } else {
                dataString += entity.getName();
            }
            count++;
        }
        return dataString;
    }

    /**
     * Check GoalWorkitems in the ACTIVATED state.
     */
    public void checkActivated(BWInstance bwInstance) {
        if (!getGoalWorkItemsSet().isEmpty()) {
            for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
                if (goalWorkItem.getState().equals(GoalState.ACTIVATED)) {
                    goalWorkItem.notifyDataChanged();
                }
            }
        }
    }

    /**
     * Check if exists a goalWorkitem for a given context
     * 
     * @param goal
     * @param entityInstanceContext
     * @return
     */
    public Boolean goalWorkItemsExistForTheContext(
            EntityInstance entityInstanceContext) {
        Boolean alreadyExists = false;
        for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
            if (goalWorkItem.getEntityInstanceContext()
                    .equals(entityInstanceContext)) {
                alreadyExists = true;
            }
        }
        return alreadyExists;
    }

    // TODO:
    public Set<Entity> getSubGoalsContext() {
        Set<Entity> result = new HashSet<Entity>();
        // result.add(getEntityContext());
        for (Goal subGoal : getSubGoalSet()) {
            result.add(subGoal.getEntityContext());
        }

        return result;
    }

    public void delete() {
        setGoalModel(null);
        setParentGoal(null);
        getSubGoalSet().stream().forEach(sub -> removeSubGoal(sub));
        getSuccessConditionSet().stream()
                .forEach(suc -> removeSuccessCondition(suc));
        getActivationConditionSet().stream()
                .forEach(act -> removeActivationCondition(act));
        getEntityInvariantConditionSet().stream()
                .forEach(mul -> removeEntityInvariantCondition(mul));
        getAttributeInvariantConditionSet().stream()
                .forEach(rule -> removeAttributeInvariantCondition(rule));

        deleteDomainObject();
    }

    public GoalDTO getDTO() {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setSpecId(getGoalModel().getSpecification().getSpecId());
        goalDTO.setExtId(getExternalId());
        goalDTO.setName(getName());

        return goalDTO;
    }

    public GoalRelation getGoalRelation(Goal goalTwo) {
        if (getSubGoalSet().contains(goalTwo))
            return GoalRelation.CHILD;

        if (goalTwo.getSubGoalSet().contains(this))
            return GoalRelation.PARENT;

        if ((getParentGoal() != null)
                && (getParentGoal().getSubGoalSet().contains(goalTwo)))
            return GoalRelation.SIBLING;

        return GoalRelation.OTHER;
    }

    public void purgeActivationCondition() {
        for (Condition cond : getActivationConditionSet()) {
            if (getSuccessConditionSet().contains(cond))
                removeActivationCondition(cond);
        }

    }

    private Stream<Goal> flattened() {
        return Stream.concat(Stream.of(this),
                getSubGoalSet().stream().flatMap(Goal::flattened));
    }

    public void checkCanMergeChild(Goal childGoal) {
        Optional<Goal> oGoal = flattened().filter(goal -> (goal != this
                && goal != childGoal
                && checkGoalActIntersectsAnotherGoalSuc(childGoal, goal)))
                .findAny();

        if (oGoal.isPresent()) {
            throw new BWException(BWErrorType.UNMERGEABLE_GOALS);
        }
    }

    private boolean checkGoalActIntersectsAnotherGoalSuc(Goal childGoal,
            Goal goal) {
        for (Condition act : childGoal.getActivationConditionSet()) {
            if (goal.getSuccessConditionSet().contains(act)) {
                return true;
            }
        }
        return false;
    }

    public Goal extractChild(String newGoalName,
            Set<Condition> successConditions) {
        checkConditionsNotEmpty(successConditions);
        checkConditionsExistInSource(successConditions);
        removeConditionsFromSource(successConditions);

        checkCanExtractChild(successConditions);

        Goal newGoal = new Goal(getGoalModel(), newGoalName);
        successConditions.stream()
                .forEach((def) -> newGoal.addSuccessCondition(def));
        newGoal.setParentGoal(this);

        addActivationConditions();
        newGoal.addActivationConditions();

        addMultiplicityConditions();
        newGoal.addMultiplicityConditions();

        addRuleConditions();
        newGoal.addRuleConditions();

        return newGoal;
    }

    public Goal extractSibling(String newGoalName,
            Set<Condition> successConditions) {
        checkConditionsNotEmpty(successConditions);
        checkConditionsExistInSource(successConditions);
        removeConditionsFromSource(successConditions);

        checkCanExtractSibling(successConditions);

        Goal newGoal = new Goal(getGoalModel(), newGoalName);

        successConditions.stream()
                .forEach((def) -> newGoal.addSuccessCondition(def));
        newGoal.setParentGoal(getParentGoal());

        addActivationConditions();
        newGoal.addActivationConditions();

        addMultiplicityConditions();
        newGoal.addMultiplicityConditions();

        addRuleConditions();
        newGoal.addRuleConditions();

        return newGoal;
    }

    private void addActivationConditions() {
        Set<Condition> defs = ConditionModel
                .getProductsOfDefConditions(getSuccessConditionSet()).stream()
                .flatMap((p) -> p.getDependenceSet().stream())
                .map((d) -> d.getTarget().getDefCondition())
                .collect(Collectors.toSet());

        for (Condition def : defs) {
            if (!getSuccessConditionSet().contains(def))
                addActivationCondition(def);
        }
    }

    private void addRuleConditions() {
        for (Rule rule : getGoalModel().getSpecification().getDataModel()
                .getRuleSet()) {
            if (getSuccessConditionSet().stream()
                    .flatMap((c) -> c.getAttributeBasicSet().stream())
                    .anyMatch((a) -> rule.getAttributeBasicSet().contains(a))) {
                addAttributeInvariantCondition(rule);
            }
        }
    }

    private void addMultiplicityConditions() {
        Set<Entity> entities = ConditionModel
                .getEntitiesOfDefEntitySet(getSuccessConditionSet());

        entities.stream().flatMap((e) -> e.getMultConditions().stream())
                .forEach((m) -> addEntityInvariantCondition(m));
    }

    private void checkCanExtractSibling(Set<Condition> successConditions) {
        checkIsNotTopGoal();
        checkSiblingsAttributeConstraint(successConditions);
    }

    private void checkCanExtractChild(Set<Condition> successConditions) {
        checkParentChildAttributeConstraint(successConditions);
        checkDependenceConstraint(successConditions);
    }

    private void checkIsNotTopGoal() {
        if (getParentGoal() == null)
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkIsNotTopGoal");
    }

    private void checkConditionsNotEmpty(Set<Condition> successConditions) {
        if (successConditions.isEmpty())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkConditionsNotEmpty");
    }

    private void checkConditionsExistInSource(
            Set<Condition> successConditions) {
        Optional<Condition> oCond = successConditions.stream()
                .filter((def) -> !getSuccessConditionSet().contains(def))
                .findFirst();

        if (oCond.isPresent())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkConditionsExistInSource:" + oCond.get().getSubPath());

    }

    private void removeConditionsFromSource(Set<Condition> successConditions) {
        successConditions.stream()
                .forEach((def) -> removeSuccessCondition(def));
        getActivationConditionSet().stream()
                .forEach((def) -> removeActivationCondition(def));
        getEntityInvariantConditionSet().stream()
                .forEach((mul) -> removeEntityInvariantCondition(mul));
        getAttributeInvariantConditionSet().stream()
                .forEach((rul) -> removeAttributeInvariantCondition(rul));

        if (getSuccessConditionSet().isEmpty())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkNotAllConditionsAreExtracted");

    }

    private void checkSiblingsAttributeConstraint(
            Set<Condition> successConditions) {
        Set<Condition> conditionsLeft = new HashSet<Condition>(
                getSuccessConditionSet());
        conditionsLeft.removeAll(successConditions);
        checkSiblingsAttributeConstraintBasic(successConditions,
                conditionsLeft);
        checkSiblingsAttributeConstraintBasic(conditionsLeft,
                successConditions);
    }

    private void checkSiblingsAttributeConstraintBasic(
            Set<Condition> successConditionsOne,
            Set<Condition> successConditionsTwo) {
        Set<Entity> entities = ConditionModel
                .getEntitiesOfDefEntitySet(successConditionsOne);

        Optional<Entity> oEntity = successConditionsTwo.stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .map((def) -> def.getAttributeOfDef().getEntity())
                .filter((e) -> entities.contains(e)).findFirst();

        if (oEntity.isPresent())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkSiblingsAttributeConstraintBasic:"
                            + oEntity.get().getName());
    }

    private void checkParentChildAttributeConstraint(
            Set<Condition> successConditions) {
        Set<Entity> entities = ConditionModel
                .getEntitiesOfDefEntitySet(successConditions);

        Optional<Entity> oEntity = flattened()
                .flatMap((g) -> g.getSuccessConditionSet().stream()
                        .filter(DefAttributeCondition.class::isInstance))
                .map(DefAttributeCondition.class::cast)
                .map((def) -> def.getAttributeOfDef().getEntity())
                .filter((e) -> entities.contains(e)).findFirst();

        if (oEntity.isPresent())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkParentChildAttributeConstraint:"
                            + oEntity.get().getName());
    }

    private void checkDependenceConstraint(Set<Condition> successConditions) {
        DataModel dataModel = getGoalModel().getSpecification().getDataModel();

        Set<Condition> topSuccessConditions = new HashSet<Condition>();
        topSuccessConditions.addAll(getSuccessConditionSet());
        topSuccessConditions.removeAll(successConditions);

        log.debug("topSuccessConditions size:{}", topSuccessConditions.size());

        Set<Product> topProducts = ConditionModel
                .getProductsOfDefConditions(topSuccessConditions);
        log.debug("topProducts {}", topProducts.stream().map((p) -> p.getName())
                .collect(Collectors.joining(",")));

        Set<Product> succProducts = ConditionModel
                .getProductsOfDefConditions(successConditions);
        log.debug("succProducts {}", succProducts.stream()
                .map((p) -> p.getName()).collect(Collectors.joining(",")));

        Optional<Product> oProduct = topProducts.stream()
                .flatMap((p) -> p.getDependenceSet().stream())
                .map((d) -> dataModel.getTargetOfPath(d.getPath()))
                .filter((p) -> succProducts.contains(p)).findFirst();

        if (oProduct.isPresent())
            throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
                    "checkDependenceConstraint:" + oProduct.get().getName());

    }

}