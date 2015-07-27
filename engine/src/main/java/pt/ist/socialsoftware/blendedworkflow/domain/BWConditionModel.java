package pt.ist.socialsoftware.blendedworkflow.domain;

public class BWConditionModel extends BWConditionModel_Base {

    public void delete() {
        setSpecification(null);

        getEntityAchieveConditionSet().stream()
                .forEach(defEnt -> defEnt.setConditionModel(null));
        getEntityDependenceConditionSet().stream()
                .forEach(depEnt -> depEnt.setConditionModelForEntDep(null));
        getEntityInvariantConditionSet().stream().forEach(mul -> mul.delete());
        getAttributeAchieveConditionSet().stream()
                .forEach(defAtt -> defAtt.setConditionModel(null));
        getAttributeDependenceConditionSet().stream()
                .forEach(depAtt -> depAtt.setConditionModelForEntDep(null));
        getAttributeInvariantConditionSet().stream()
                .forEach(rule -> rule.setConditionModel(null));

        deleteDomainObject();
    }

}
