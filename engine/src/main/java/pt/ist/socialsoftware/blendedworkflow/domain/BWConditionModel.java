package pt.ist.socialsoftware.blendedworkflow.domain;

public class BWConditionModel extends BWConditionModel_Base {

    public void clean() {
        if (getSpecification().getGoalModel() != null)
            getSpecification().getGoalModel().clean();
        getEntityAchieveConditionSet().stream()
                .forEach(defEnt -> removeEntityAchieveCondition(defEnt));
        getEntityDependenceConditionSet().stream()
                .forEach(depEnt -> removeEntityDependenceCondition(depEnt));
        getEntityInvariantConditionSet().stream()
                .forEach(mul -> removeEntityInvariantCondition(mul));
        getAttributeAchieveConditionSet().stream()
                .forEach(defAtt -> removeAttributeAchieveCondition(defAtt));
        getAttributeDependenceConditionSet().stream()
                .forEach(depAtt -> removeAttributeDependenceCondition(depAtt));
        getAttributeInvariantConditionSet().stream()
                .forEach(rule -> removeAttributeInvariantCondition(rule));
    }

    public void delete() {
        clean();
        setSpecification(null);

        deleteDomainObject();
    }

}
