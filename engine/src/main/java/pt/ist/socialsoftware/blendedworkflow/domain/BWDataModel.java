package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWDataModel extends BWDataModel_Base {

    public enum DataState {
        DEFINED, UNDEFINED, SKIPPED
    }

    /**
     * Clone the DataModel.
     */
    public DataModelInstance cloneDataModel() throws BWException {
        DataModelInstance newDataModelInstance = new DataModelInstance();
        BWEntity relationEntityOne;
        BWEntity relationEntityTwo;

        for (BWEntity entity : getEntitiesSet()) {
            entity.cloneEntity(newDataModelInstance);
        }

        // Get relation -> Get new Entities -> Clone with new Entities
        for (BWRelation relation : getRelationsSet()) {
            relationEntityOne = newDataModelInstance
                    .getEntity(relation.getEntityOne().getName()).get();
            relationEntityTwo = newDataModelInstance
                    .getEntity(relation.getEntityTwo().getName()).get();
            relation.cloneRelation(newDataModelInstance, relationEntityOne,
                    relationEntityTwo);
        }
        return newDataModelInstance;
    }

    public Optional<BWEntity> getEntity(String name) {
        return getEntitiesSet().stream()
                .filter(ent -> ent.getName().equals(name)).findFirst();
    }

    public BWRelation getRelation(String name) {
        for (BWRelation relation : getRelationsSet()) {
            if (relation.getName().equals(name)) {
                return relation;
            }
        }
        return null;
    }

    public void clean() {
        if (getSpecification().getConditionModel() != null)
            getSpecification().getConditionModel().clean();
        getRuleSet().stream().forEach(rule -> rule.delete());
        getDependenceSet().stream().forEach(dep -> dep.delete());
        getEntitiesSet().stream().forEach(ent -> ent.delete());
    }

    public void delete() {
        clean();
        setSpecification(null);
        deleteDomainObject();
    }

    public BWEntity createEntity(String entityName, Boolean exists) {
        return new BWEntity(this, entityName, exists);
    }

    public BWRule createRule(String name, Condition condition) {
        return new BWRule(this, name, condition);
    }

    public BWRule getRule(String name) {
        return getRuleSet().stream().filter(rule -> rule.getName().equals(name))
                .findFirst().orElse(null);
    }

    public BWProduct getTargetOfPath(String path) {
        List<String> pathLeft = Arrays.stream(path.split("\\."))
                .collect(Collectors.toList());
        BWEntity entity = getEntity(pathLeft.get(0)).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME,
                        pathLeft.get(0)));
        pathLeft.remove(0);
        return entity.getNext(pathLeft, path);
    }

}